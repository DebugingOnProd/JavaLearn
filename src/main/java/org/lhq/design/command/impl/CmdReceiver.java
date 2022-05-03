package org.lhq.design.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.command.Receiver;
@Slf4j
public class CmdReceiver implements Receiver {
    @Override
    public void action(String commandName) {
        log.info("CMD执行了:{}",commandName);
    }
}
