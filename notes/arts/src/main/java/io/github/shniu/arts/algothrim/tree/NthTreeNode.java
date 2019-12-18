package io.github.shniu.arts.algothrim.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * N 叉树
 */
public class NthTreeNode {
    int val;
    private List<NthTreeNode> children;

    public NthTreeNode() {
    }

    public NthTreeNode(int val) {
        this(val, null);
    }

    public NthTreeNode(int val, List<NthTreeNode> children) {
        this.val = val;
        this.children = children;
    }

    public void addChild(NthTreeNode node) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(node);
    }

    public List<NthTreeNode> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return children != null;
    }
}
