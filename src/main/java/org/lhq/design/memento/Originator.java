package org.lhq.design.memento;

import lombok.Data;

@Data
public class Originator {
    private String history;

    public Memento saveStateToMemento(){
        return new Memento(history);
    }

    public void getStateFromMemento(Memento Memento){
        history = Memento.getHistory();
    }
}
