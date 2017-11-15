package edu.dlsu.mobidev.gem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;

import java.util.ArrayList;

/**
 * Created by asus on 11/7/2017.
 */

public class BallManager {
    //Higher index = lower on screen = higher y value
    private ArrayList<Ball> balls;
    private int color;

    private int score = 0;

    private long startTime;
    private long initTime;

    private boolean isPlayer;

    MediaPlayer mpLol = new MediaPlayer();



    public BallManager(boolean isPlayer){

        this.isPlayer = isPlayer;

        balls = new ArrayList<Ball>();

        startTime = initTime = System.currentTimeMillis();

    }


    public boolean playerCollide(RectPlayer player){
        for(Ball ob : balls){
            if(ob.playercollide(player)) {
                balls.remove(ob);
                return true;
            }
        }
        return false;
    }
    public void addBall(int x, int y, boolean isPlayer) {
        balls.add(new Ball(x,y,isPlayer));

    }

    public ArrayList<Ball> getBalls(){
        return balls;
    }


    public void draw(Canvas canvas){
        for(Ball ob : balls)
            ob.draw(canvas);

    }

    public void update(){

        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime - initTime)/1000.0))*  Constants.SCREEN_HEIGHT/(10000.0f);
        for (Ball ob : balls){
            if(isPlayer)
                ob.decrementY(speed * elapsedTime);
            else
                ob.incrementY(speed * elapsedTime);
        }
        if(isPlayer && !balls.isEmpty()){
            if(balls.get(0).getPosY() <= 0 ){
                balls.remove(0);
            }
        }
        else if(!isPlayer && !balls.isEmpty()){
            if(balls.get(0).getPosY() >= Constants.SCREEN_HEIGHT ){
                balls.remove(0);
            }
        }


    }

}
