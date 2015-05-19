package andrewyu.mario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Andrew Yu on 5/17/2015.
 */
public class MarioSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    public MarioThread renderThread;
    public Stage1 stage1;
    private float Width;
    private float Height;
    private boolean walk;
    private ControlBottom controlBottom;
    public MarioSurfaceView (Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated (SurfaceHolder holder){
        walk = false;
        Width = getWidth();
        Height = getHeight();
        ArrayList<Bitmap> mario = new ArrayList<>();
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap sky = BitmapFactory.decodeResource(getResources(), R.drawable.stage1sky, options);
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariostand, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalk1, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalk2, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalk3, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariostandleft, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalkleft1, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalkleft2, options));
        mario.add(BitmapFactory.decodeResource(getResources(), R.drawable.mariowalkleft3, options));

        Bitmap ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground, options);
        Bitmap cloud1 = BitmapFactory.decodeResource(getResources(), R.drawable.cloud1, options);
        Bitmap cloud2 = BitmapFactory.decodeResource(getResources(), R.drawable.cloud2, options);
        Bitmap cloud3 = BitmapFactory.decodeResource(getResources(), R.drawable.cloud3, options);
        Bitmap breakableBrick = BitmapFactory.decodeResource(getResources(), R.drawable.breakablebrick, options);
        Bitmap questionMarkBrick = BitmapFactory.decodeResource(getResources(), R.drawable.questionmarkbrick, options);
        Bitmap emptyQuestionBrick = BitmapFactory.decodeResource(getResources(), R.drawable.emptyquestionbrick, options);
        Bitmap castle = BitmapFactory.decodeResource(getResources(), R.drawable.castle, options);

        Bitmap leftArrow = BitmapFactory.decodeResource(getResources(), R.drawable.leftarrow, options);
        Bitmap rightArrow = BitmapFactory.decodeResource(getResources(), R.drawable.rightarrow, options);
        Bitmap jumpBottom = BitmapFactory.decodeResource(getResources(), R.drawable.jumpbottom, options);
        stage1 = new Stage1(mario,sky,ground,cloud1,cloud2,cloud3,breakableBrick,questionMarkBrick,emptyQuestionBrick,castle, Width, Height);
        controlBottom = new ControlBottom(leftArrow, rightArrow, jumpBottom, Width, Height);
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
        switch(e. getAction()){
            case MotionEvent.ACTION_DOWN :
                walk = true;
                break;
            case MotionEvent.ACTION_UP:
                walk = false;
                stage1.move =0;
                break;
        }
        return true;
    }

    @Override
    public void onDraw ( Canvas c ) {
        super . onDraw ( c ) ;
// Draw everything ( restricted to the displayed rectangle ) .
    }

    public void renderGame(Canvas c){
        stage1.renderGame(c);
        controlBottom.render(c);
        if(walk == true){
            stage1.walking();
        }

    }


}
