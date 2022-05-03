package org.lhq.design.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.command.Order;
import org.lhq.design.command.Receiver;

@Slf4j
public class BuyOrder implements Order {
    private Receiver receiver;


    public BuyOrder (Receiver receiver){
        super();
        this.receiver =receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
