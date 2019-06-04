package io.github.shniu.springbootgracefulshutdown.hook;

/**
 * Java 语言底层有机制能捕获到 OS 的 SIGINT/ SIGTERM 停止指令的, 具体是通过 Runtime.getRuntime().addShutdownHook()
 * 向 JVM 中注册一个 Shutdown hook 线程, 当 JVM 收到停止信号后, 该线程将被激活运行, 这时候我们就可以向其他线程发出中断指令,
 * 进而快速而优雅地关闭整个程序.
 */
public class ShutdownHook {
    public static void main(String[] args) {
        // Just test
        Thread mainThread = Thread.currentThread();

        // Register shutdown hook
        Runtime.getRuntime().addShutdownHook(new ShutdownHookSample(mainThread));

        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            // ...
        }
    }

    private static class ShutdownHookSample extends Thread {

        private Thread mainThread;

        public ShutdownHookSample(Thread thread) {
            this.mainThread = thread;
        }

        @Override
        public void run() {
            // 给主线程发送一个中断信号
            mainThread.interrupt();

            try {
                mainThread.join();
            } catch (InterruptedException e) {
                // ...
            }
        }

    }
}
