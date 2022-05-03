package org.lhq.design.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.command.Receiver;
@Slf4j
public class PowerShellReceiver implements Receiver {
    @Override
    public void action(String commandName) {
        log.info("PowerShell执行了:{}",commandName);
    }
}
