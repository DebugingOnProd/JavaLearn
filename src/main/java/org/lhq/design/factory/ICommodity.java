package org.lhq.design.factory;

import java.util.Map;

public interface ICommodity {
	void sendCommodity(String userId, String commodityId, String bizId, Map<String,String> extType)	;
}
