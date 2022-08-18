package org.lhq.design.visitor;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.visitor.impl.Computer;
import org.lhq.design.visitor.impl.Keyboard;
import org.lhq.design.visitor.impl.Monitor;
@Slf4j
public class ComputerPartDisplayVisitor implements ComputerPartVisitor{
    @Override
    public void visitor(Keyboard keyboard) {
        log.info("显示昂贵的机械键盘");
    }

    @Override
    public void visitor(Monitor monitor) {
        log.info("显示显示器");
    }

    @Override
    public void visitor(Computer computer) {
        log.info("显示主机");
    }
}
