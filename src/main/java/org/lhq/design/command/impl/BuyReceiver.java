package org.lhq.design.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.command.Receiver;
@Slf4j
public class BuyReceiver implements Receiver {
    @Override
    public void action() {
        log.info("执行了购买命令");
    }
}
