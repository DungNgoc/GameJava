package com.gamemobile.game.miniscreens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamemobile.game.Application;
import com.gamemobile.game.actors.ActorButton;
import com.gamemobile.game.utils.SplashDoors;
import com.gamemobile.game.utils.TextNoBackground;


public class TimeOutDisplay extends Actor {
    private MiniScreenState miniScreenState;
    private Texture background;
    private TextNoBackground timeOutText;
    private Texture timeOutSign;

    private long startTime;
    private ActorButton btnChangeScreen;

    public TimeOutDisplay(){
        background = new Texture("images/backgrounds/minidisplay.png");
        timeOutSign = new Texture("images/textureobjects/timeout.png");
        timeOutText = new TextNoBackground(Color.GOLD, 50);
        miniScreenState = MiniScreenState.HIDE;

        startTime = 0;
        btnChangeScreen = new ActorButton(120f, 120f, ActorButton.ButtonTag.TIMEOUT_CHANGE_SCREEN);
    }

    public void showDisplay(){
        btnChangeScreen.setButtonState(ActorButton.ButtonState.ENABLED);
        miniScreenState = MiniScreenState.SHOW;
    }

    public MiniScreenState getMiniScreenState() {
        return miniScreenState;
    }

    @Override
    public boolean remove() {
        background.dispose();
        timeOutText.dispose();
        timeOutSign.dispose();

        return super.remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(miniScreenState.equals(MiniScreenState.SHOW)){

            btnChangeScreen.updateButtonTouched();
            if(btnChangeScreen.isTouched()){
                miniScreenState = MiniScreenState.FREEZE;
                startTime = TimeUtils.millis()/1000;
            }
        }
        if(miniScreenState.equals(MiniScreenState.FREEZE)){
            SplashDoors.closeTheSplashDoor(10f);
            if(SplashDoors.checkDoorClose() && TimeUtils.millis()/1000 - startTime >= 2){
                miniScreenState = MiniScreenState.FINISH;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(miniScreenState.equals(MiniScreenState.SHOW)){
            batch.draw(background, 0, 0, Application.DESKTOP_WIDTH, Application.DESKTOP_HEIGHT);
            batch.draw(timeOutSign, Application.DESKTOP_WIDTH / 2 - 125f, Application.DESKTOP_HEIGHT/2 - 50f, 250f, 250f);
            timeOutText.drawText(batch, "Finish Level!", Application.DESKTOP_WIDTH / 2 - 190f, Application.DESKTOP_HEIGHT/2 - 50f);
            btnChangeScreen.draw(batch, parentAlpha);
        }
    }
}
