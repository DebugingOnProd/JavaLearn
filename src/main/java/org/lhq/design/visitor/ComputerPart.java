package org.lhq.design.visitor;

public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
