package org.lhq.design.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.ICommodity;

import java.util.Map;
@Slf4j
public class CardCommodityService implements ICommodity {
	@Override
	public void sendCommodity(String userId, String commodityId, String bizId, Map<String, String> extType) {
		log.info("请求参数[爱奇艺兑换卡] => uId：{} commodityId：{} bizId：{} extMap：{}", userId, commodityId, bizId, extType);
	}
}
