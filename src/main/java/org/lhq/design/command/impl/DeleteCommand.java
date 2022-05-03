package org.lhq.design.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.command.Command;
import org.lhq.design.command.Receiver;

@Slf4j
public class DeleteCommand implements Command {
    private Receiver receiver;
    private String commandName;


    public DeleteCommand (Receiver receiver,String commandName){
        super();
        this.receiver =receiver;
        this.commandName = commandName;
    }

    @Override
    public void execute() {
        receiver.action(commandName);
    }
}
