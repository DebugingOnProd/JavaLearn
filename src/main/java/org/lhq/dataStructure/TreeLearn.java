package org.lhq.dataStructure;

import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.TreeNode;

import java.util.*;

/**
 * @author Hades
 */
@Slf4j
public class TreeLearn {
    public <T> TreeNode<T> createBinaryTree(LinkedList<T> trees){
            TreeNode node = null;
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
        Console.log();
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

    /**
     * 先序遍历
     * @param root
     * @param <T>
     */
    public <T> List<T> preorderTraversal(TreeNode<T> root){
        TreeNode<T> node = root;
        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        ArrayList<T> result = new ArrayList<>();
        // 如果节点不为空或者栈不为空
        while (node != null||!stack.isEmpty()){
            // 节点不为空
            if (node != null){
                // 往栈里压节点
                stack.push(node);
                log.info("本节点的值是:{}",node.getValue());
                result.add(node.getValue());
                //当前节点等于左子节点
                node = node.getLeftNode();
            }else {
                //栈中推出一个元素
                node = stack.pop();
                // 节点指针等于右子节点
                node = node.getRightNode();
            }
        }
        return result;

    }

    /**
     * 后序遍历
     * @param root
     * @param <T>
     */
    public <T> List<T> postOrderTraversal(TreeNode<T> root){
        ArrayList<T> result = new ArrayList<>();
        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        TreeNode<T> cur = root;
        TreeNode<T> prev= null;
        // 栈不为空或者 当前节点不为空
        while (!stack.isEmpty()||cur!=null){
            //当前节点不为空的时候
            while (cur!=null){
                //栈中推入当前节点
                stack.push(cur);
                // 当前节点等于左子接节点
                cur = cur.getLeftNode();
            }
            // 取出栈顶节点
            TreeNode<T> top = stack.peek();
            if (top.getRightNode() == null||top.getRightNode() == prev){
                result.add(top.getValue());
                prev=top;
                stack.pop();
            }else {
                cur = top.getRightNode();
            }
        }
        log.info("{}",result);
        return result;
    }
}
