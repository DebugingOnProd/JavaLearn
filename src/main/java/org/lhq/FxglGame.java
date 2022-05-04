package org.lhq;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class FxglGame extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Java小游戏");
        settings.setAppIcon("img/msft.png");
    }
}
