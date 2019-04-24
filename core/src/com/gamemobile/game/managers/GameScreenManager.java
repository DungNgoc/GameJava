package com.gamemobile.game.managers;

import com.gamemobile.game.Application;
import com.gamemobile.game.screens.AbstractScreen;
import com.gamemobile.game.screens.MainMenuScreen;
import com.gamemobile.game.screens.ScreenPlayLevel1;
import com.gamemobile.game.utils.ScreenConstants;

public class GameScreenManager {
    public final Application app;

    private AbstractScreen gameScreen;

    public GameScreenManager(final Application app) {
        this.app = app;

        setScreen(ScreenConstants.MAINMENU_SCREEN);

    }

    public AbstractScreen getNextScreen(int level) {
        gameScreen = null;

        if (level == ScreenConstants.MAINMENU_SCREEN) {
            gameScreen = new MainMenuScreen(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[0]) {
            gameScreen = new ScreenPlayLevel1(this.app);
        }


        return gameScreen;
    }

    public void setScreen(int level) {
        app.setScreen(getNextScreen(level));
    }

    public void dispose() {

    }
}
