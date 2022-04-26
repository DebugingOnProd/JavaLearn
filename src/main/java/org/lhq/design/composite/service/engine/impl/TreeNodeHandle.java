package org.lhq.design.composite.service.engine.impl;

import org.lhq.design.composite.aggregates.TreeRich;
import org.lhq.design.composite.service.engine.AbstractEngineBase;
import org.lhq.design.composite.vo.EngineResult;
import org.lhq.design.composite.vo.TreeNode;

import java.util.Map;

public class TreeNodeHandle extends AbstractEngineBase {
    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId, decisionMatter);


        return new EngineResult(userId,treeId,treeNode.getTreeNodeId(),treeNode.getNodeValue());
    }
}
