package org.lhq.design.bridge.pay.mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayFace implements PayMode{
    @Override
    public boolean security(String uId) {
        log.info("刷脸支付");
        return true;
    }
}
