package andrewyu.mario;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Andrew Yu on 5/17/2015.
 */
public class MarioSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    public MarioThread renderThread;
    public Stage1 stage1;
    private float Width;
    private float Height;
    public MarioSurfaceView (Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated (SurfaceHolder holder){
        Width = getWidth();
        Height = getHeight();
        stage1 = new Stage1(this, Width, Height);
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

                break;
            case MotionEvent.ACTION_UP:
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

    }


}
