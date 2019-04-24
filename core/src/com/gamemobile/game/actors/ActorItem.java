package com.gamemobile.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamemobile.game.utils.PlayerInfo;
import com.gamemobile.game.utils.ShopConstants;
import com.gamemobile.game.utils.TextNoBackground;


public class ActorItem extends Actor {

    public enum ItemTag{
        BOMB,
        CLOCK,
        SODA
    }

    private ItemTag itemTag;

    private ActorButton btnItem;

    private TextNoBackground moneyText;

    private int money;

    private boolean isBought;

    private boolean isEnable;



    public ActorItem(String path, int money, float x, float y, float width, float height, ItemTag itemTag){
        btnItem = new ActorButton(path, x, y, width, height);
        this.moneyText = new TextNoBackground(Color.GOLD, 25);
        this.money = money;
        isBought = false;
        this.isEnable = true;

        this.itemTag = itemTag;
    }



    public ItemTag getItemTag() {
        return itemTag;
    }

    public boolean isBought() {
        return isBought;
    }

    public ActorButton getBtnItem() {
        return btnItem;
    }

    public int getMoney() {
        return money;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    private void updatePlayerBag(){
        if(itemTag.equals(ItemTag.BOMB)){
            PlayerInfo.setCurrentBombNum(PlayerInfo.getCurrentBombNum() + 1);
        }
        if(itemTag.equals(ItemTag.CLOCK)){
            PlayerInfo.getBag().setTimeBonus(ShopConstants.TIME_BONUS);
        }
        if(itemTag.equals(ItemTag.SODA)){
            PlayerInfo.getBag().setSodaPower(ShopConstants.SODA_POWER);
        }
    }

    @Override
    public boolean remove() {
        btnItem.remove();

        moneyText.dispose();
        return super.remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isEnable){
            btnItem.updateButtonTouched();
            if(btnItem.isTouched()){
                isEnable = false;
                isBought = true;

                PlayerInfo.setCurrentMoney(PlayerInfo.getCurrentMoney() - money);
                updatePlayerBag();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(isEnable){
            btnItem.draw(batch, parentAlpha);
            moneyText.drawText(batch, "$" + money, btnItem.getX() + 20f, btnItem. getY() - 10f);
        }
    }
}