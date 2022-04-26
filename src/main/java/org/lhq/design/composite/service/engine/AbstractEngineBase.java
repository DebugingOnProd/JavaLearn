package org.lhq.design.composite.service.engine;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.composite.aggregates.TreeRich;
import org.lhq.design.composite.service.logic.LogicFilter;
import org.lhq.design.composite.vo.EngineResult;
import org.lhq.design.composite.vo.TreeNode;
import org.lhq.design.composite.vo.TreeRoot;

import java.util.Map;

@Slf4j
public abstract class AbstractEngineBase extends EngineConfig implements IEngine {
    @Override
    public abstract EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter);


    protected TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId, String userId, Map<String, String> decisionMatter) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        Long treeRootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNode = treeNodeMap.get(treeRootNodeId);
        while (treeNode.getNodeType().equals(1)) {
            String ruleKey = treeNode.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(treeId, userId, decisionMatter);
            Long filter = logicFilter.filter(matterValue, treeNode.getTreeNodeLinkList());
            treeNode = treeNodeMap.get(filter);
            log.info("决策引擎 :{}==> userId:{} \\ treeId:{} \\ treeNode:{} \\ ruleKey:{} \\ matterValue:{} ",
                    treeRoot.getTreeName(),
                    userId,
                    treeId,
                    treeNode.getTreeNodeId(),
                    ruleKey,
                    matterValue);
        }
        return treeNode;
    }
}
