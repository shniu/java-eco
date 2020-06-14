package com.digcredit.core.fsm.token;

import org.springframework.util.StringUtils;

/**
 * Vertex
 * Created by Administrator on 2018/12/28 0028.
 */
public class Vertex<V extends VertexContext> {

    private String label;
    private V context;

    public Vertex(String label){
        assert !StringUtils.isEmpty(label);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public V getContext() {
        return context;
    }

    public void setContext(V context) {
        this.context = context;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long id = 0;

        for (char c : label.toCharArray()) {
            id += (long) c;
        }

        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        return label.equals(other.label);
    }

    @Override
    public String toString(){
        return label;
    }
}
