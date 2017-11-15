package edu.dlsu.mobidev.gem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.media.MediaPlayer;
import android.view.MotionEvent;

/**
 * Created by asus on 11/7/2017.
 */

public class GameplayScene implements Scene {

    private Rect r = new Rect();

    private RectPlayer player;
    private Point playerPoint;
    private long playerFireTime;
    private BallManager ballManager;
    private Rect playerHp;

    private boolean gameOver;
    private long gameOverTime;

    private RectPlayer enemy;
    private Point enemyPoint;
    private long enemyFireTime;
    private BallManager enemyBalls;
    private Rect enemyHp;

    private BitmapFactory bf;
    private Bitmap arrowLeft;
    private Bitmap arrowRight;
    private Bitmap playerSpecialTimer;


    private MediaPlayer mpSound;
    private int right;
    private int enemyRight;

    private int playerHpChange;
    private int enemyHpChange;
    private int specialFireTime;
    private long timePassed;



    public GameplayScene(){
        reset();

    }

    public void reset(){
        gameOver = false;
        right = 0;
        playerHpChange = 15;
        enemyHpChange = 85;
        enemyRight = 15;


        player = new RectPlayer(true);
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,85*Constants.SCREEN_HEIGHT/100);
        player.update(playerPoint);


        ballManager = new BallManager(true);

        mpSound = MediaPlayer.create(Constants.CURRENT_CONTEXT,R.raw.lose);

        enemy = new RectPlayer(false);
        enemyPoint = new Point(Constants.SCREEN_WIDTH / 2, (int) 5* Constants.SCREEN_HEIGHT / 100);
        enemy.update(enemyPoint);

        bf = new BitmapFactory();
        arrowLeft = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.arrowleft);
        arrowRight = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.arrowright);
        playerSpecialTimer = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.timer);



        playerHp = new Rect(Constants.SCREEN_WIDTH*15/100,
                Constants.SCREEN_HEIGHT*93/100,
                Constants.SCREEN_WIDTH*45/100,
                Constants.SCREEN_HEIGHT*96/100);

        enemyHp = new Rect(Constants.SCREEN_WIDTH*55/100,
                Constants.SCREEN_HEIGHT*93/100,
                Constants.SCREEN_WIDTH*85/100,
                Constants.SCREEN_HEIGHT*96/100);


        specialFireTime = 20;
        timePassed = System.currentTimeMillis();




        enemyFireTime = playerFireTime = System.currentTimeMillis();



        ballManager = new BallManager(true);
        enemyBalls = new BallManager(false);


    }

    public void enemyFire(){

        enemyBalls.addBall(enemyPoint.x, enemyPoint.y, false);

    }

    public void updateEnemy(){



        if(enemyPoint.x >= Constants.SCREEN_WIDTH - 50){

            enemyRight = -15;
        }
        else if (enemyPoint.x <= 50){
            enemyRight = 15;
        }


        enemyPoint.set(enemyRight + enemyPoint.x,enemyPoint.y);





        if((enemyPoint.x >= playerPoint.x - 50 && enemyPoint.x <= playerPoint.x + 50)&& System.currentTimeMillis() - enemyFireTime >= 500){
            enemyFire();
            enemyFireTime = System.currentTimeMillis();
            mpSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.fire);
            mpSound.start();
            mpSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                }
            });
        }


    }

    public boolean isGameOver(){
        if(ballManager.playerCollide(enemy)){


            enemyHpChange -= 6;
            enemyHp.right = Constants.SCREEN_WIDTH*enemyHpChange/100;
            if (enemy.loseHealth()){
                mpSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.lose);
                mpSound.start();
                mpSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                    }
                });
                return true;
            }
            else {
                mpSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.hit);
                mpSound.start();
                mpSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                    }
                });
            }
        }
        else if(enemyBalls.playerCollide(player)){
            playerHpChange += 6;
            playerHp.left = Constants.SCREEN_WIDTH* playerHpChange /100;
            if(player.loseHealth()){
                mpSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.lose);
                mpSound.start();
                mpSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                    }
                });
                return true;
            }
            else {
                mpSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.hit);
                mpSound.start();
                mpSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                    }
                });
            }

        }
        return false;
    }

    @Override
    public void update() {
        if(!gameOver) {

            updateEnemy();

            if(playerPoint.x >= Constants.SCREEN_WIDTH - 50)
                playerPoint.set(playerPoint.x - 50, playerPoint.y);
            else if(playerPoint.x <= 50)
                playerPoint.set(playerPoint.x + 50, playerPoint.y);
            if(playerPoint.x < Constants.SCREEN_WIDTH && playerPoint.x > 0)
                playerPoint.set(playerPoint.x + right, playerPoint.y);

            player.update(playerPoint);
            enemy.update(enemyPoint);
            enemyBalls.update();
            ballManager.update();

            if(System.currentTimeMillis() - timePassed >= 1000 && specialFireTime > 0){
                specialFireTime--;
                timePassed = System.currentTimeMillis();
            }


            if(isGameOver())
                gameOver = true;




            gameOverTime = System.currentTimeMillis();
        }

    }

    @Override
    public void draw(Canvas canvas) {

        Paint border, hpPlayer,hpEnemy;
        border =  new Paint();
        hpPlayer = new Paint();
        hpEnemy = new Paint();

        canvas.drawColor(Color.WHITE);
        player.draw(canvas);

        ballManager.draw(canvas);
        enemy.draw(canvas);
        enemyBalls.draw(canvas);

        canvas.drawBitmap(arrowRight,null,new Rect(Constants.SCREEN_WIDTH*85/100,
                Constants.SCREEN_HEIGHT*90/100,
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT),new Paint());
        canvas.drawBitmap(arrowLeft,null,new Rect(0,Constants.SCREEN_HEIGHT*90/100,
                Constants.SCREEN_WIDTH*15/100,
                Constants.SCREEN_HEIGHT),new Paint());
        canvas.drawBitmap(playerSpecialTimer,null,new Rect(Constants.SCREEN_WIDTH*46/100,
                Constants.SCREEN_HEIGHT*91/100,
                Constants.SCREEN_WIDTH*54/100,
                Constants.SCREEN_HEIGHT*98/100),new Paint());


        hpPlayer.setShader(new LinearGradient(playerHp.left,0,playerHp.right,0,Color.rgb(0,0,200),Color.rgb(0,0,170),Shader.TileMode.CLAMP));
        hpEnemy.setShader(new LinearGradient(enemyHp.left,0,enemyHp.right,0,Color.rgb(170,0,0),Color.rgb(200,0,0),Shader.TileMode.CLAMP));
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(5);
        border.setColor(Color.BLACK);


        canvas.drawRect(playerHp,hpPlayer);
        canvas.drawRect(enemyHp,hpEnemy);
        canvas.drawRect(playerHp,border);
        canvas.drawRect(enemyHp,border);

        drawTimer(canvas);


        if(gameOver){
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);

            if(player.getHealth() <= 0 && enemy.getHealth() <= 0)
                drawCenterText(canvas,paint,"DRAW");
            else if(player.getHealth() <= 0 )
                drawCenterText(canvas,paint,"ENEMY WINS");
            else if(enemy.getHealth() <= 0 )
                drawCenterText(canvas,paint,"PLAYER WINS");
        }



    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                System.out.println(event.getX());
                if (!gameOver && event.getX() <= Constants.SCREEN_WIDTH * 0.15 && event.getY() >= Constants.SCREEN_HEIGHT *0.9) {
                    right = -15;
                } else if (!gameOver && event.getX() >= Constants.SCREEN_WIDTH * 0.85 && event.getY() >= Constants.SCREEN_HEIGHT *0.9 ){
                    right = 15;
                }
                else if(!gameOver
                        && event.getX() >= Constants.SCREEN_WIDTH * 0.46
                        && event.getX() <= Constants.SCREEN_WIDTH * 0.54
                        && event.getY() >= Constants.SCREEN_HEIGHT * 0.91){
                    if(specialFireTime <= 0){
                        specialFireTime = 20;
                        timePassed = System.currentTimeMillis();
                    }
                }
                else if(!gameOver && System.currentTimeMillis() - playerFireTime >= 500){
                    ballManager.addBall(playerPoint.x,playerPoint.y,true);
                    mpSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.fire);
                    mpSound.start();
                    mpSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                        }
                    });
                    playerFireTime = System.currentTimeMillis();


                }

                if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000){
                    Intent intent = new Intent(Constants.CURRENT_CONTEXT,matchResultsSingle.class);
                    Constants.CURRENT_CONTEXT.startActivity(intent);
                    ((Activity) Constants.CURRENT_CONTEXT).finish();

                }


                break;



            case MotionEvent.ACTION_UP:
                right = 0;
                break;


        }
    }


    private void drawTimer(Canvas canvas) {
        Paint paint = new Paint();
        String text = specialFireTime+"";
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        canvas.getClipBounds(r);
        paint.getTextBounds(text, 0, text.length(), r);
        canvas.drawText(text, Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT*95/100, paint);

    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);

    }
}
