package org.lhq.design.bridge.pay.channel;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.bridge.pay.mode.PayMode;

import java.math.BigDecimal;

@Slf4j
public class WxPay extends Pay {
    public WxPay(PayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String userId, String tradeId, BigDecimal amount) {
        log.info("微信转账开始: userId:{},tradeId:{},amoubt:{}",userId,tradeId,amount);
        boolean security = payMode.security(userId);
        if (!security){
            log.warn("校验不通过无法转账");
            return "500";
        }
        log.info("校验通过,转账成功");
        return "200";
    }
}
