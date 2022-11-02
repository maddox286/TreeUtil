package org.example.tree;

import java.util.List;

/**
 * 抽象树节点 定义方法所需的必要参数
 *
 * @param <T>
 * @author maddox
 */
public abstract class AbstractTreeNode<T> extends AbstractNodeFlag {
    String name, label;

    List<T> childNodes;

    public AbstractTreeNode() {
    }

    public AbstractTreeNode(String id, String pid) {
        this.id = id;
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<T> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<T> childNodes) {
        this.childNodes = childNodes;
    }


    @Override
    public String toString() {
        return "AbstractTreeNode{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", childNodes=" + childNodes +
                ", id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }
}