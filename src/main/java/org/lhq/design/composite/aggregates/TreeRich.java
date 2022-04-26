package org.lhq.design.composite.aggregates;

import lombok.Data;
import org.lhq.design.composite.vo.TreeNode;
import org.lhq.design.composite.vo.TreeRoot;

import java.util.Map;

@Data
public class TreeRich {
    private TreeRoot treeRoot;                          //树根信息
    private Map<Long, TreeNode> treeNodeMap;        //树节点ID -> 子节点

    public TreeRich(TreeRoot treeRoot, Map<Long, TreeNode> treeNodeMap) {
        this.treeRoot = treeRoot;
        this.treeNodeMap = treeNodeMap;
    }
}
