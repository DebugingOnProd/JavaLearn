package org.lhq.dataStructure;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lhq.entity.TreeNode;

import java.util.LinkedList;

@Slf4j
class TreeLearnTest {
    private TreeLearn treeLearn = new TreeLearn();

    @Test
    void levelTraversal() {
        LinkedList<Character> treeNodes = new LinkedList<>();
        String data = "ABDH##I##E##CF#J##G##";
        char[] chars = data.toCharArray();
        for (char c : chars) {
            treeNodes.add(c);
        }
        TreeNode<Character> binaryTree = treeLearn.createBinaryTree(treeNodes);
        //层次遍历
        log.info("二叉树的层次遍历");
        var levelTraversal = treeLearn.levelTraversal(binaryTree);
        log.info("{}",levelTraversal);
        //先序遍历
        log.info("二叉树的先序遍历");
        var pre = treeLearn.preOrderTraversal(binaryTree);
        log.info("{}",pre);
        // 二叉树的后序遍历
        log.info("二叉树的后序遍历");
        var list = treeLearn.postOrderTraversal(binaryTree);
        log.info("{}",list);
    }
}