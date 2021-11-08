package org.lhq.dataStructure;

import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.TreeNode;

import java.util.LinkedList;

/**
 * @author Hades
 */
@Slf4j
public class TreeLearn {
    public <T> TreeNode<T> createBinaryTree(LinkedList<T> trees){
            TreeNode<T> node = null;
            if (trees == null || trees.isEmpty()){
                return null;
            }
            T val = trees.removeFirst();
            log.info("生成二叉树,节点:{}",val);
            if (!(val.equals('#'))){
                node = new TreeNode(val,null,null);
                node.setLeftNode(createBinaryTree(trees));
                node.setRightNode(createBinaryTree(trees));
            }
            return node;
    }
    /**
     * 二叉树的层次遍历
     * @param root 根结点
     */
    public <T> void levelTraversal(TreeNode<T> root){
        if (root==null){
            return;
        }
        LinkedList<TreeNode> list =new LinkedList<>();
        list.add(root);
        TreeNode currentNode;
        while (!list.isEmpty()){
            currentNode = list.poll();

        log.info("当前节点的值:{}",currentNode.getValue());
        if (currentNode.getLeftNode()!=null){
            list.add(currentNode.getLeftNode());
        }
        if (currentNode.getRightNode() != null){
            list.add(currentNode.getRightNode());
        }
        }
    }
    public <T> void preorderTraversal(TreeNode<T> root){
        TreeNode<T> node = root;
        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        while (node != null||!stack.isEmpty()){
            if (node!=null){
                stack.push(node);
                log.info("本节点的值是:{}",node.getValue());
                node = node.getLeftNode();
            }else {
                node = stack.pop();
                //log.info("本节点的值是:{}",node.getValue());
                node = node.getRightNode();
            }
        }

    }
}
