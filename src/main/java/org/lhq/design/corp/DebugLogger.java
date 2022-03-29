package org.lhq.design.corp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugLogger extends AbstractLogger {
    public DebugLogger (int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
      log.debug("调试输出::{}",message);
    }
}
