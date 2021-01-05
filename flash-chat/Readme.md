
# Flash chat

## Features

### 通用

- 定义 Message 格式

1 先使用自定义的二进制数据通信格式

```                
| version | command type | content length | payload |
    1          1               4              n

- payload 使用 json 字符串的字节数组
```
    
2. 再考虑使用 ProtoBuf

- 引入日志框架

### 客户端

1. 客户端应该有一个等待输入的 UI，可以使用命令行来做，需要使用独立的线程去做 uiThread
2. 执行用户登录，连接到 chatServer
3. 选择一个好友进行聊天
4. 解析输入的内容，调用 chatClient 发送数据给 chatServer
5. 退出当前的聊天界面，回到主界面
6. 好友上线通知，好友下线通知
7. 客户端因网络问题导致的连接断开，进行重连处理

### 服务端

1. 处理客户端登录
2. 把消息发送给目标用户
3. 客户端上下线管理
4. 好友消息离线存储，待好友上线后推送

## 思路

所有通信的网络处理部分都应该交给 IM Client 去处理，在 IM Client 之上去开发实际的应用，也就是说 UI 使用 client 去收发数据，UI 发送和接收的
都是 Command 对象，通过 Future<Command> 获取响应

此外，每个发出去的命令都应该有序列号，这样我们就知道 response 是针对哪个 request 了
维护一个在途请求的集合

ui 可以持有 client 的引用，然后调用 client 的相关接口收发数据；
ui 发送的应该是命令，这个也是 ui 和 client 之间传递的消息

```
interface Connection {

}

// 通用 API
// 同步调用 API
imClient.send(login message);
imClient.send(chat message);
// 异步调用 API
CompletableFuture<Command> resp = imClient.sendAsync(message);
resp.onCompletion(() -> {
  // ...
});

// 面向业务的 API
imClient.login();
imClient.chat();

imClient.addFriend();
imClient.listFriends();
imClient.deleteFriend();
```