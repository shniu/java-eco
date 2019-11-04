# fsm

### Graph 实现

```

// 代码参考 src/ com.digcredit.core.fsm.token 下面
// 使用参考 test/ com.digcredit.core.fsm.token DefaultGraphTest
// ---
// 测试结果
15:32:15.176 [main] INFO com.digcredit.core.fsm.token.DefaultGraph - payee 支付 payer 100000.0
15:32:15.182 [main] INFO com.digcredit.core.fsm.token.DefaultGraph - payer 转融成本 pool 8000.0
15:32:15.182 [main] INFO com.digcredit.core.fsm.token.DefaultGraph - pool 返利 payee 6000.0
15:32:15.182 [main] INFO com.digcredit.core.fsm.token.DefaultGraph - pool 流通奖励 issuer1 1200.0
15:32:15.182 [main] INFO com.digcredit.core.fsm.token.DefaultGraph - pool 代理商奖励 issuer1Pxoxy 300.0
15:32:15.183 [main] INFO com.digcredit.core.fsm.token.DefaultGraph - pool 邀请奖励 payerInviter 10.0
15:32:15.183 [main] INFO com.digcredit.core.fsm.token.DefaultGraphTest - payer directed vertex: [pool]
15:32:15.183 [main] INFO com.digcredit.core.fsm.token.DefaultGraphTest - payee directed vertex: [payer]
15:32:15.183 [main] INFO com.digcredit.core.fsm.token.DefaultGraphTest - pool directed vertex: [payee, issuer1, issuer1Pxoxy, payerInviter]
15:32:15.183 [main] INFO com.digcredit.core.fsm.token.DefaultGraphTest - issuer directed vertex: []
15:32:15.183 [main] INFO com.digcredit.core.fsm.token.DefaultGraphTest - issuer1Pxoxy directed vertex: []
15:32:15.183 [main] INFO com.digcredit.core.fsm.token.DefaultGraphTest - payerInviter directed vertex: []

```
