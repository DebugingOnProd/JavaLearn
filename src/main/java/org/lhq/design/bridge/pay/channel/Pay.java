package org.lhq.design.bridge.pay.channel;

import org.lhq.design.bridge.pay.mode.PayMode;

import java.math.BigDecimal;

public abstract class Pay {
    protected PayMode payMode;

    public Pay(PayMode payMode){
        this.payMode = payMode;
    }

    public abstract String transfer(String userId, String tradeId, BigDecimal amount);
}
