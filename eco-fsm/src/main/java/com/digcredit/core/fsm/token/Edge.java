package com.digcredit.core.fsm.token;

/**
 * Edge
 * Created by Administrator on 2018/12/28 0028.
 */
public class Edge<V extends VertexContext, E extends EdgeContext> {

    private boolean isDirected = true;
    private Vertex<V> source;
    private Vertex<V> target;
    private E context;

    public Edge(Vertex<V> source, Vertex<V> target, E context) {
        this.source = source;
        this.target = target;
        this.context = context;
    }

    public Vertex<V> getSource() {
        return source;
    }

    public void setSource(Vertex<V> source) {
        this.source = source;
    }

    public Vertex<V> getTarget() {
        return target;
    }

    public void setTarget(Vertex<V> target) {
        this.target = target;
    }

    public E getContext() {
        return context;
    }

    public void setContext(E context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "isDirected=" + isDirected +
                ", source=" + source +
                ", target=" + target +
                ", context.weight=" + context.getWeight() +
                ", context.type=" + context.getType() +
                '}';
    }
}
