package org.lhq.design.composite.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EngineResult {
    private boolean isSuccess; //执行结果
    private String userId;   //用户ID
    private Long treeId;     //规则树ID
    private Long nodeId;   //果实节点ID
    private String nodeValue;//果实节点值


    public EngineResult(String userId, Long treeId, Long treeNodeId, String nodeValue) {
        this.userId = userId;
        this.treeId = treeId;
        this.nodeId = treeNodeId;
        this.nodeValue = nodeValue;
    }
}
