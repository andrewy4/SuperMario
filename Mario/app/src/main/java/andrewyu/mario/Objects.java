package andrewyu.mario;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;

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
    private boolean left;
    private boolean right;
    public boolean endingPoint;

    public Objects(Bitmap bit, Type thing, boolean content, boolean breakable, boolean end, int x, int y, int dx, int dy){
        endingPoint = end;
        this.breakable = breakable;
        this.content = content;
        this.type = thing;
        bitmap = bit;
        Dst = new Rect(x,y,x+dx,y+dy);
        left = true;
        right = false;
    }
    public void moving(int Width){
        Dst.offset(-(Width / 35), 0);
    }

    public void monsterMoving(int Width, int Height, ArrayList<Objects> stage, int gravity){
        boolean drop = true;
        for(int i = 0; i<stage.size();i++){
            if(stage.get(i).type!= Objects.Type.background && stage.get(i).type!= Objects.Type.monster && Dst.intersects(Dst,stage.get(i).Dst)){
                if(Dst.centerY()<stage.get(i).Dst.top){
                    drop = false;
                }
            }
        }
        if(drop)
            Dst.offset(0,gravity);
        if(Dst.centerX()<= Width && Dst.centerY()<=Height){
            if(left)
                for(int i=0; i<stage.size();i++){
                    if (stage.get(i).type != Objects.Type.background && stage.get(i).type!= Objects.Type.monster && Dst.intersects(Dst, stage.get(i).Dst) && Dst.centerX() >= stage.get(i).Dst.right && Dst.centerY() >= stage.get(i).Dst.top && Dst.centerY() <= stage.get(i).Dst.bottom){
                        left = false;
                        right = true;
                    }
                }
            if(right)
                for(int i=0; i<stage.size();i++){
                    if (stage.get(i).type != Objects.Type.background && stage.get(i).type!= Objects.Type.monster && Dst.intersects(Dst, stage.get(i).Dst) && Dst.centerX() <= stage.get(i).Dst.left && Dst.centerY() >= stage.get(i).Dst.top && Dst.centerY() <= stage.get(i).Dst.bottom){
                        left = true;
                        right = false;
                    }
                }

            if(left && !drop){
                Dst.offset(-(Width/90),0);
            }
            if(right && !drop){
                Dst.offset((Width/90),0);
            }
        }

    }
}
