package org.lhq.design.composite.service.logic.impl;


import org.lhq.design.composite.service.logic.AbstractBaseLogic;

import java.util.Map;

public class UserGenderFilter extends AbstractBaseLogic {

    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("gender");
    }

}
