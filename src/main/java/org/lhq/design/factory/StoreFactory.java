package org.lhq.design.factory;

import org.lhq.design.factory.impl.CardCommodityService;
import org.lhq.design.factory.impl.CouponCommodityService;

public class StoreFactory {
	public ICommodity getCommodityService(Integer commodityType) {
		if (commodityType == 1) return new CouponCommodityService();
		if (commodityType == 2) return  new CardCommodityService();
		if (0 == commodityType) return null;
		throw new RuntimeException("不存在的商品服务类型");
	}
}
