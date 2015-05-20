package andrewyu.mario;

import android.graphics.Bitmap;
import android.graphics.Rect;

/*
 * Created by Ohyehyu on 5/18/2015.
 */
public class Objects {
    public enum Type{floor, background, brick, monster}
    Type type;
    public boolean breakable;
    public Bitmap bitmap;
    public Rect Dst;
    public boolean content;

    public Objects(Bitmap bit, Type thing, boolean content, boolean breakable, int x, int y, int dx, int dy){
        this.breakable = breakable;
        this.content = content;
        this.type = thing;
        bitmap = bit;
        Dst = new Rect(x,y,x+dx,y+dy);
    }
    public void moving(int Width){
        Dst.offset(-(Width / 35), 0);
    }

    public void monsterMoving(){

    }
}
