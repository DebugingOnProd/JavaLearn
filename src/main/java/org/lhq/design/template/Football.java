package org.lhq.design.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Football implements Game{
    @Override
    public void initialize() {
        log.info("游戏足球初始化完毕，可以开始游戏");
    }

    @Override
    public void startPlay() {
        log.info("开始游戏");
    }

    @Override
    public void endPlay() {
        log.info("游戏结束");
    }
}
