package com.gamemobile.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamemobile.game.Application;
import com.gamemobile.game.actors.ActorButton;

import com.gamemobile.game.utils.ScreenConstants;
import com.gamemobile.game.utils.SplashDoors;


public class LoseScreen extends AbstractScreen {

    private Texture background;

    private ActorButton btnBackMenu;
    private boolean isScreenFinish;
    private long startTime;



    public LoseScreen(Application app) {
        super(app);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        SplashDoors.setDoorClose();
        createCamera();
        createStageGame();
        app.batch.setProjectionMatrix(getCamera().combined);
        app.shapeBatch.setProjectionMatrix(getCamera().combined);
        background = new Texture("images/backgrounds/losebackground.png");
        isScreenFinish = false;



        btnBackMenu = new ActorButton(120f, 120f, ActorButton.ButtonTag.LOSE_BACK_MAINMENU);

        getStageGame().addActor(btnBackMenu);

        Gdx.input.setInputProcessor(getStageGame());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.begin();
        app.batch.draw(background,0,0,Application.DESKTOP_WIDTH,Application.DESKTOP_HEIGHT);
        SplashDoors.drawDoor(app.batch);
        app.batch.end();

        if(!isScreenFinish) {

            if (SplashDoors.checkDoorOpen()) {
                getStageGame().act();
                updateButtonBackMenuClick();
                getStageGame().draw();
            } else {
                SplashDoors.openTheSplashDoor(5f);
            }
        }
        if(isScreenFinish){
            SplashDoors.closeTheSplashDoor(10f);

            if(TimeUtils.millis()/1000 - startTime >= 2) {
                app.gsm.setScreen(ScreenConstants.MAINMENU_SCREEN);
                dispose();
            }
        }

    }

    private void updateButtonBackMenuClick(){
        btnBackMenu.updateButtonTouched();
        if(btnBackMenu.isTouched()){
            isScreenFinish = true;
            startTime = TimeUtils.millis()/1000;
        }
    }

    @Override
    public void pause() {
            }

    @Override
    public void resume(){
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        btnBackMenu.remove();

        super.dispose();
    }
}
