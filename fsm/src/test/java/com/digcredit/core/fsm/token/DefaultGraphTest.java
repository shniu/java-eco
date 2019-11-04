package com.digcredit.core.fsm.token;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Graph test
 *
 * Created by Administrator on 2018/12/28 0028.
 */

@Slf4j
public class DefaultGraphTest {
    @Test
    public void addVertex() throws Exception {

    }

    @Test
    public void addEdge() throws Exception {

    }

    @Test
    public void main() {
        // Vertex
        UserVertexContext payeeContext = new UserVertexContext("payee");
        UserVertexContext payerContext = new UserVertexContext("payer");
        UserVertexContext poolContext = new UserVertexContext("pool");
        UserVertexContext issuer1Context = new UserVertexContext("issuer1");
        UserVertexContext issuer1ProxyContext = new UserVertexContext("issuer1Pxoxy");
        UserVertexContext payerInviterContext = new UserVertexContext("payerInviter");

        // Edge
        WeightEdgeContext payeeToPayer = new WeightEdgeContext(1.0, "支付");
        WeightEdgeContext payerToPool = new WeightEdgeContext(0.08, "转融成本");
        WeightEdgeContext poolToPayee = new WeightEdgeContext(0.06, "返利");
        WeightEdgeContext poolToPayerInviter = new WeightEdgeContext(0.0001, "邀请奖励");
        WeightEdgeContext poolToIssuer1 = new WeightEdgeContext(0.012, "流通奖励");
        WeightEdgeContext poolToIssuer1Proxy = new WeightEdgeContext(0.003, "代理商奖励");

        // Build graph
        Graph<UserVertexContext, WeightEdgeContext> graph = new DefaultGraph<>();
        graph.addVertex(payeeContext);
        graph.addVertex(payerContext);
        graph.addVertex(poolContext);
        graph.addVertex(issuer1Context);
        graph.addVertex(issuer1ProxyContext);
        graph.addVertex(payerInviterContext);

        graph.addEdge(payeeContext, payerContext, payeeToPayer);
        graph.addEdge(payerContext, poolContext, payerToPool);
        graph.addEdge(poolContext, payeeContext, poolToPayee);
        graph.addEdge(poolContext, issuer1Context, poolToIssuer1);
        graph.addEdge(poolContext, issuer1ProxyContext, poolToIssuer1Proxy);
        graph.addEdge(poolContext, payerInviterContext, poolToPayerInviter);

        graph.bfs(100000);

        log.info("payer directed vertex: {}", graph.getDirectedAdjacentVertices(graph.getVertex(payerContext)));
        log.info("payee directed vertex: {}", graph.getDirectedAdjacentVertices(graph.getVertex(payeeContext)));
        log.info("pool directed vertex: {}", graph.getDirectedAdjacentVertices(graph.getVertex(poolContext)));
        log.info("issuer directed vertex: {}", graph.getDirectedAdjacentVertices(graph.getVertex(issuer1Context)));
        log.info("issuer1Pxoxy directed vertex: {}", graph.getDirectedAdjacentVertices(graph.getVertex(issuer1ProxyContext)));
        log.info("payerInviter directed vertex: {}", graph.getDirectedAdjacentVertices(graph.getVertex(payerInviterContext)));
    }

    class UserVertexContext implements VertexContext {

        private String label;

        public UserVertexContext(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

    class WeightEdgeContext implements EdgeContext {

        private double weight;
        private String type;

        public WeightEdgeContext(double weight, String type) {
            this.weight = weight;
            this.type = type;
        }

        @Override
        public double getWeight() {
            return weight;
        }

        @Override
        public String getType() {
            return type;
        }
    }

}