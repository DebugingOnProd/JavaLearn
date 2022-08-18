package org.lhq.design.visitor;

import org.lhq.design.visitor.impl.Computer;
import org.lhq.design.visitor.impl.Keyboard;
import org.lhq.design.visitor.impl.Monitor;

public interface ComputerPartVisitor {
    void visitor(Keyboard keyboard);
    void visitor(Monitor monitor);
    void visitor(Computer computer);
}
