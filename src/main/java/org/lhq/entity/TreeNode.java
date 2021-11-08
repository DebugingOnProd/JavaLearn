package org.lhq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Hades
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TreeNode<T> {
    private T value;
    private TreeNode<T> leftNode;
    private TreeNode<T> rightNode;
    public TreeNode (TreeNode<T> treeNode){
        this.leftNode = treeNode.leftNode;
        this.rightNode = treeNode.rightNode;
        this.value = treeNode.value;
    }

}
