package com.gamemobile.game.utils;

import com.badlogic.gdx.Gdx;
import com.gamemobile.game.Application;


public class ScreenConstants {
    public static final float TRANSFORM_X = (Gdx.app.getGraphics().getWidth() * 1.0f)/ (Application.DESKTOP_WIDTH * 1.0f);
    public static final float TRANSFORM_Y = (Gdx.app.getGraphics().getHeight() * 1.0f)/ (Application.DESKTOP_HEIGHT * 1.0f);

    public static final float HUMAN_X = Application.DESKTOP_WIDTH/2 - 40f;
    public static final float HUNAM_Y =  Application.DESKTOP_HEIGHT - 105;
    public static final float HUMAN_WIDTH = 110f;
    public static final float HUNAM_HEIGHT = 95f;

    public static final int FINISH_SCREEN = -4;
    public static final int SHOP_SCREEN = -3;
    public static final int LOSE_SCREEN = -2;
    public static final int WIN_SCREEN = -1;
   // public static final int FINISH_SCREEN = -3;
   // public static final int SHOP_SCREEN = -2;
  //  public static final int LOSE_SCREEN = -1;
    public static final int MAINMENU_SCREEN = 0;
    public static final int[] PLAY_LEVEL_SCREEN = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
}
