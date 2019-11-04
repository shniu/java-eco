package com.digcredit.core.fsm.token;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Created by Administrator on 2018/12/28 0028.
 */
@Slf4j
public class VertexTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testHashCode() {
        Vertex payee = newVertexContext(() -> "payee");
        Vertex payer = newVertexContext(() -> "payer");
        Vertex bouns = newVertexContext(() -> "bouns");
        Vertex issuer = newVertexContext(() -> "issuer");
        log.info("payee.hashcode={}", payee.hashCode());
        log.info("payer.hashcode={}", payer.hashCode());
        log.info("bouns.hashcode={}", bouns.hashCode());
        log.info("issuer.hashcode={}", issuer.hashCode());
    }

    private Vertex newVertexContext(VertexContext vertexContext) {
        Vertex vertex = new Vertex(vertexContext.getLabel());
        //noinspection unchecked
        vertex.setContext(vertexContext);
        return vertex;
    }

}