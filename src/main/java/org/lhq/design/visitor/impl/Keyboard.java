package org.lhq.design.visitor.impl;

import org.lhq.design.visitor.ComputerPart;
import org.lhq.design.visitor.ComputerPartVisitor;

public class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visitor(this);
    }
}
