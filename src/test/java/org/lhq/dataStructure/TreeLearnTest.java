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
        String datas = "ABDH##I##E##CF#J##G##";
        char[] chars = datas.toCharArray();
        for (char c : chars) {
            treeNodes.add(c);
        }
        TreeNode binaryTree = treeLearn.createBinaryTree(treeNodes);
        //层次遍历
        log.info("二叉树的层次遍历");
        treeLearn.levelTraversal(binaryTree);
        //先序遍历
        log.info("二叉树的先序遍历");
        treeLearn.preorderTraversal(binaryTree);
    }
}