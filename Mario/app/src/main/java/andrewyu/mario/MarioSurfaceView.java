package andrewyu.mario;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Andrew Yu on 5/17/2015.
 */
public class MarioSurfaceView extends SurfaceView implements SurfaceHolder.Callback, TimeConscious{
    public MarioThread renderThread;
    public MarioSurfaceView (Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated (SurfaceHolder holder){
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

    @Override
    public void tick(Canvas c){

    }


}
