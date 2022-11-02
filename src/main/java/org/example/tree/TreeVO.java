package org.example.tree;

import java.io.Serializable;

/**
 * 树状结构的实体类
 *
 * @author maddox
 * @version 1.0
 * @date 2022/7/27
 **/
public class TreeVO<T> extends AbstractTreeNode<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    Object treeInfo;

    public TreeVO() {
    }

    public Object getTreeInfo() {
        return treeInfo;
    }

    public void setTreeInfo(Object treeInfo) {
        this.treeInfo = treeInfo;
    }
}
