package org.lhq.design.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *备忘录,放要保存对象
 */
@Data
@AllArgsConstructor
public class Memento {
    private String history;

}
