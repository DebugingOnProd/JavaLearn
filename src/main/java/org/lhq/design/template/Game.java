package org.lhq.design.template;

public interface Game {
    void initialize();
    void startPlay();
    void endPlay();

    //模板
    default void play(){

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}
