package andrewyu.mario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/*
 * Created by Andrew Yu on 5/17/2015.
 */
public class MarioSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    float Width;
    float Height;
    public MarioThread renderThread;
    public Stage1 stage1;
    private boolean walkRight, walkLeft,jump;
    private ControlBottom controlBottom;
    private Bitmap startMenu;
    private Bitmap BG;
    private Bitmap GG;
    private Rect fullScreen, backDst, restartDst;
    private boolean start;
    private Paint paint;
    private int condition;
    private boolean paused;
    public Bitmap sky;
    public Bitmap goomba;
    public Bitmap ground;
    public Bitmap cloud1;
    public Bitmap cloud2;
    public Bitmap cloud3;
    public Bitmap breakableBrick;
    public Bitmap questionMarkBrick;
    public Bitmap emptyQuestionBrick;
    public Bitmap castle;
    public Bitmap pause;
    public Bitmap back;
    public Bitmap restart;

    public ArrayList<Bitmap> mario = new ArrayList<>();
    public MarioSurfaceView (Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated (SurfaceHolder holder){
        start = false;
        paused = false;
        condition = 0;
        walkLeft = false;
        walkRight = false;
        jump = false;
        Width = getWidth();
        Height = getHeight();

        BitmapFactory.Options options = new BitmapFactory.Options();
        fullScreen = new Rect(0,0, getWidth(),getHeight());
        backDst = new Rect((int) Width/4,(int) Height/6, (int)(Width/4*3),(int)(Height/2.1));
        restartDst = new Rect((int) Width/4,(int)(Height/6*2.5), (int)(Width/4*3),(int)(Height- Height*0.2));
        paint = new Paint();
        startMenu = BitmapFactory.decodeResource(getResources(), R.drawable.startmenu, options);
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.gameover, options);
        GG = BitmapFactory.decodeResource(getResources(), R.drawable.winending, options);
        sky = BitmapFactory.decodeResource(getResources(), R.drawable.stage1sky, options);
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariostand, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalk1, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalk2, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalk3, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariostandleft, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalkleft1, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalkleft2, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalkleft3, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariorightjump, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.marioleftjump, options));

        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba1, options);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground, options);
        cloud1 = BitmapFactory.decodeResource(getResources(), R.drawable.cloud1, options);
        cloud2 = BitmapFactory.decodeResource(getResources(), R.drawable.cloud2, options);
        cloud3 = BitmapFactory.decodeResource(getResources(), R.drawable.cloud3, options);
        breakableBrick = BitmapFactory.decodeResource(getResources(), R.drawable.breakablebrick, options);
        questionMarkBrick = BitmapFactory.decodeResource(getResources(), R.drawable.questionmarkbrick, options);
        emptyQuestionBrick = BitmapFactory.decodeResource(getResources(), R.drawable.emptyquestionbrick, options);
        castle = BitmapFactory.decodeResource(getResources(), R.drawable.castle, options);
        pause = BitmapFactory.decodeResource(getResources(), R.drawable.paused, options);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.backtogame, options);
        restart = BitmapFactory.decodeResource(getResources(), R.drawable.settingstartmenu, options);
        Bitmap leftArrow = BitmapFactory.decodeResource(getResources(), R.drawable.leftarrow, options);
        Bitmap rightArrow = BitmapFactory.decodeResource(getResources(), R.drawable.rightarrow, options);
        Bitmap jumpBottom = BitmapFactory.decodeResource(getResources(), R.drawable.jumpbottom, options);
        Bitmap setting = BitmapFactory.decodeResource(getResources(), R.drawable.setting, options);
        stage1 = new Stage1(mario,goomba,sky,ground,cloud1,cloud2,cloud3,breakableBrick,questionMarkBrick,emptyQuestionBrick,castle, Width, Height);
        controlBottom = new ControlBottom(leftArrow, rightArrow, jumpBottom,setting, Width, Height);
        renderThread = new MarioThread( this);
        renderThread.start();

    }

    @Override
    public void surfaceChanged ( SurfaceHolder holder ,int format , int width , int height ) {
        // Respond to surface changes , e . g . , aspect ratio changes .
    }
    @Override
    public void surfaceDestroyed ( SurfaceHolder holder ) {
        // The cleanest way to stop a thread is by interrupting it .
        // BubbleShooterThread regularly checks its interrupt flag .
        renderThread . interrupt () ;
    }
    @Override
    public boolean onTouchEvent (MotionEvent e){
        if(!paused && !start && condition ==0){
            switch(e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    start = true;

                    break;
                case MotionEvent.ACTION_UP:
                    stage1 = new Stage1(mario,goomba,sky,ground,cloud1,cloud2,cloud3,breakableBrick,questionMarkBrick,emptyQuestionBrick,castle, Width, Height);
            }
        }
        if(condition!=0){
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        start = false;
                        condition =0;
                        paused = false;
                        break;



            }
        }
        if(!paused && start && condition ==0) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (controlBottom.leftArrowDst.contains((int) e.getX(), (int) e.getY())) {
                        stage1.move = 4;
                        walkLeft = true;
                    }
                    if (controlBottom.rightArrowDst.contains((int) e.getX(), (int) e.getY())) {
                        stage1.move = 0;
                        walkRight = true;
                    }
                    if (controlBottom.jumpDst.contains((int) e.getX(), (int) e.getY())) {
                        if (!stage1.drop) {
                            if (stage1.move == 4)
                                stage1.move = 9;
                            if (stage1.move == 0)
                                stage1.move = 8;
                            jump = true;
                        }
                    }
                    if(controlBottom.settingDst.contains((int)e.getX(), (int)e.getY())){
                        paused = true;
                        start = false;
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    if (walkLeft || stage1.move == 9)
                        stage1.move = 4;
                    if (walkRight || stage1.move == 8)
                        stage1.move = 0;
                    walkLeft = false;
                    walkRight = false;
                    jump = false;
                    stage1.jumpCounter = 0;
                    break;
            }
        }
        if(paused && !start && condition ==0){
            switch(e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(backDst.contains((int)e.getX(),(int)e.getY())){
                        start = true;
                        paused = false;
                    }
                    if(restartDst.contains((int)e.getX(),(int)e.getY())){
                        paused = false;
                        start = false;
                }

            }
        }
        return true;
    }

    @Override
    public void onDraw ( Canvas c ) {
        super . onDraw ( c ) ;
// Draw everything ( restricted to the displayed rectangle ) .
    }

    public void renderGame(Canvas c){

        if(!start){
            c.drawBitmap(startMenu, null,fullScreen, paint);
        }
        if(start && condition ==0) {
            condition = stage1.gameOver();
            stage1.renderGame(c);
            controlBottom.render(c);
            if (walkRight || walkLeft || jump) {
                stage1.action(walkLeft, walkRight, jump);

            }
        }
        if(condition ==1){
            c.drawBitmap(BG,null,fullScreen,paint);
        }
        if(condition ==2){
            c.drawBitmap(GG,null,fullScreen,paint);
        }
        if(paused){
            c.drawBitmap(pause, null, fullScreen, paint);
            c.drawBitmap(back,null,backDst,paint);
            c.drawBitmap(restart,null,restartDst,paint);

        }

    }


}
