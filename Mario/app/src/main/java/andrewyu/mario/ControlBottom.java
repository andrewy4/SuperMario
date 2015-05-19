package andrewyu.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/*
 * Created by Andrew Yu on 5/18/2015.
 */
public class ControlBottom {

    private Bitmap leftArrow;
    private Bitmap rightArrow;
    private Bitmap jumpBottom;
    private Paint paint = new Paint();
    Rect leftArrowDst, rightArrowDst, jumpDst;

    public ControlBottom(Bitmap leftArrow, Bitmap rightArrow, Bitmap jumpBottom, float Width, float Height){

        this.leftArrow = leftArrow;
        this.rightArrow = rightArrow;
        this.jumpBottom = jumpBottom;
        leftArrowDst = new Rect(0,(int)(Height-2*Width/25),(int)(2*Width/25),(int)(Height));
        rightArrowDst = new Rect ((int)(4*Width/25),(int)(Height-2*Width/25),(int)(6*Width/25),(int)(Height));
        jumpDst = new Rect ((int) (Width-2*Width/25), (int)(Height-2*Width/25), (int)(Width),(int)(Height));
    }

    public void render(Canvas c){
        c.drawBitmap(leftArrow, null,leftArrowDst,paint);
        c.drawBitmap(rightArrow,null,rightArrowDst,paint);
        c.drawBitmap(jumpBottom,null,jumpDst,paint);
    }


}
