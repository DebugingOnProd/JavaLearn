package org.lhq.design.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.command.Order;
import org.lhq.design.command.Receiver;

@Slf4j
public class SaleOrder implements Order {
    private Receiver receiver;


    public SaleOrder (Receiver receiver){
        super();
        this.receiver =receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
