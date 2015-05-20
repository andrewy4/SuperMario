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
    private Bitmap setting;
    private Paint paint = new Paint();
    Rect leftArrowDst, rightArrowDst, jumpDst, settingDst;

    public ControlBottom(Bitmap leftArrow, Bitmap rightArrow, Bitmap jumpBottom, Bitmap setting, float Width, float Height){
        this.setting = setting;
        this.leftArrow = leftArrow;
        this.rightArrow = rightArrow;
        this.jumpBottom = jumpBottom;
        leftArrowDst = new Rect(0,(int)(Height-2*Width/22),(int)(2*Width/22),(int)(Height));
        rightArrowDst = new Rect ((int)(4*Width/22),(int)(Height-2*Width/22),(int)(6*Width/22),(int)(Height));
        jumpDst = new Rect ((int) (Width-2*Width/22), (int)(Height-2*Width/22), (int)(Width),(int)(Height));
        settingDst = new Rect((int) (Width-2*Width/22), 0,(int)Width, (int)(2*Width/22));
    }

    public void render(Canvas c){
        c.drawBitmap(leftArrow, null,leftArrowDst,paint);
        c.drawBitmap(rightArrow,null,rightArrowDst,paint);
        c.drawBitmap(jumpBottom,null,jumpDst,paint);
        c.drawBitmap(setting,null,settingDst,paint);
    }


}
