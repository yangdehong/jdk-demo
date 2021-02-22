package com.ydh.redsheep.datastruts.redblack;

import lombok.Data;
import lombok.ToString;

/**
 * 红黑树结点
 */
@Data
@ToString
public class RBTreeNode {
    private int key;
    private boolean isBlack;
    private RBTreeNode left;
    private RBTreeNode right;
    private  RBTreeNode parent;

    public RBTreeNode(int key) {
        this.key = key;
        isBlack=false;// 新增节点默认为红色
    }

}
