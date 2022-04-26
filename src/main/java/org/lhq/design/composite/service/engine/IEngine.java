package org.lhq.design.composite.service.engine;

import org.lhq.design.composite.aggregates.TreeRich;
import org.lhq.design.composite.vo.EngineResult;

import java.util.Map;

public interface IEngine {
    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);
}
