package org.lhq.design.visitor.impl;

import org.lhq.design.visitor.ComputerPart;
import org.lhq.design.visitor.ComputerPartVisitor;

import java.util.ArrayList;
import java.util.List;

public class Computer implements ComputerPart {
    private final List<ComputerPart> computerPartList;

    public Computer(){
        computerPartList = new ArrayList<>();
        computerPartList.add(new Keyboard());
        computerPartList.add(new Monitor());
    }

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartList.forEach(item -> item.accept(computerPartVisitor));
        computerPartVisitor.visitor(this);
    }
}
