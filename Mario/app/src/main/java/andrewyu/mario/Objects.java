package andrewyu.mario;

import android.graphics.Bitmap;
import android.graphics.Rect;

/*
 * Created by Ohyehyu on 5/18/2015.
 */
public class Objects {
    public enum Type{floor, background, brick}
    Type type;
    public boolean breakable;
    public Bitmap bitmap;
    public Rect Dst;
    public boolean content;

    public Objects(Bitmap bit, Type type, boolean content, boolean breakable, int x, int y, int dx, int dy){
        this.breakable = breakable;
        this.content = content;
        this.type = type;
        bitmap = bit;
        Dst = new Rect(x,y,x+dx,y+dy);
    }
    public void moving(int Width){
        Dst.offset(-(Width / 35), 0);
    }
}
