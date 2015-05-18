package andrewyu.mario;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/*
 * Created by Andrew Yu on 5/17/2015.
 */
public class Stage1{
    private Bitmap sky;
    private ArrayList<Bitmap> mario = new ArrayList<>();
    private int Width;
    private int Height;
    private Paint paint;
    private Rect skyDst;
    private Rect marioSize;
    private int sizeDiff;
    public int move;
    private ArrayList<Objects> floor = new ArrayList<>();
    private ArrayList<Objects> backgroundObjects = new ArrayList<>();
    private ArrayList<Objects> blocks = new ArrayList<>();
    public ArrayList<Objects> stage1 = new ArrayList<>();

    public Stage1(MarioSurfaceView view, float Width, float Height){
        move = 0;
        this.Width = (int)Width;
        this.Height = (int)Height;
        int marioDiff = this.Width/12;
        sizeDiff = this.Width/25;
        BitmapFactory.Options options = new BitmapFactory.Options();
        sky = BitmapFactory.decodeResource(view.getResources(), R.drawable.stage1sky, options);
        mario.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.mariostand, options));
        mario.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.mariowalk1, options));
        mario.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.mariowalk2, options));
        mario.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.mariowalk3, options));
        Bitmap ground = BitmapFactory.decodeResource(view.getResources(), R.drawable.ground, options);
        Bitmap cloud1 = BitmapFactory.decodeResource(view.getResources(), R.drawable.cloud1, options);
        Bitmap cloud2 = BitmapFactory.decodeResource(view.getResources(), R.drawable.cloud2, options);
        Bitmap cloud3 = BitmapFactory.decodeResource(view.getResources(), R.drawable.cloud3, options);
        Bitmap breakableBrick = BitmapFactory.decodeResource(view.getResources(), R.drawable.breakablebrick, options);
        Bitmap questionMarkBrick = BitmapFactory.decodeResource(view.getResources(), R.drawable.questionmarkbrick, options);
        Bitmap emptyQuestionBrick = BitmapFactory.decodeResource(view.getResources(), R.drawable.emptyquestionbrick, options);
        Bitmap castle = BitmapFactory.decodeResource(view.getResources(), R.drawable.castle, options);


        paint = new Paint();
        skyDst = new Rect(0,0,this.Width, this.Height);
        marioSize = new Rect((this.Width/2)-marioDiff, (this.Height/2) - marioDiff,(this.Width/2)+marioDiff, (this.Height/2)+marioDiff);
        for(int i = 0; i <100; i++) {
            if(i == 40 || i == 41 || i == 59 || i == 60 || i == 75|| i == 76 || i == 77)
                continue;
            floor.add(new Objects(ground, false, false, sizeDiff * i, this.Height - sizeDiff, sizeDiff, sizeDiff));
            if(i == 80){
                for(int x = i; x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 2*sizeDiff, sizeDiff, sizeDiff));
                }
                for(int x = i+1;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 3*sizeDiff, sizeDiff, sizeDiff));
                }
                for(int x = i+2;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 4*sizeDiff, sizeDiff, sizeDiff));
                }
                for(int x = i+3;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 5*sizeDiff, sizeDiff, sizeDiff));
                }
                for(int x = i+4;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 6*sizeDiff, sizeDiff, sizeDiff));
                }
                for(int x = i+5;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 7*sizeDiff, sizeDiff, sizeDiff));
                }
                for(int x = i+6;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 8*sizeDiff, sizeDiff, sizeDiff));
                }
                for(int x = i+7;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, false, false, sizeDiff * x, this.Height - 9*sizeDiff, sizeDiff, sizeDiff));
                }
            }
        }
        for(int i = 0; i< 100; i++){
            if(i == 7 || i == 20 || i == 40 || i == 55 || i == 60 || i == 72 || i == 89)
                backgroundObjects.add(new Objects(cloud1,false,false,sizeDiff*i,this.Height/16,this.Width/9,this.Width/9));
            if(i == 13 ||  i == 26 ||  i == 35 ||  i == 76)
                backgroundObjects.add(new Objects(cloud2,false,false,sizeDiff*i,this.Height/22,this.Width/9,this.Width/9));
            if(i == 15 || i == 30 || i == 92)
                backgroundObjects.add(new Objects(cloud3,false,false,sizeDiff*i,this.Height/19,this.Width/5,this.Width/9));
            if(i == 92)
                backgroundObjects.add(new Objects(castle,false,false,sizeDiff * i, this.Height - 8*sizeDiff, (6)*sizeDiff, (7)*sizeDiff));
        }

        for(int i = 0; i<100;i++){
            if(i == 10) {
                blocks.add(new Objects(breakableBrick, false,true, sizeDiff * i, this.Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, true,false, sizeDiff * (i+1), this.Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(breakableBrick, false,true, sizeDiff * (i+2), this.Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, true,false, sizeDiff * (i+3), this.Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(breakableBrick, false,true, sizeDiff * (i+4), this.Height - (5 * sizeDiff), sizeDiff, sizeDiff));
            }
            if(i ==50){
                blocks.add(new Objects(breakableBrick, false,true, sizeDiff * i, this.Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, true,false, sizeDiff * (i+1), this.Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(breakableBrick, false,true, sizeDiff * (i+2), this.Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                for(int x = i+3; x<67;x++){
                    if(x == 60 || x == 61 || x == 62)
                        continue;
                    blocks.add(new Objects(breakableBrick, false,true, sizeDiff * x, this.Height - 9 * sizeDiff, sizeDiff, sizeDiff));
                }
            }
            if(i == 67){
                blocks.add(new Objects(breakableBrick, false,true, sizeDiff * i, this.Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, true,false, sizeDiff * i, this.Height - 9 * sizeDiff, sizeDiff, sizeDiff));
                for(int x = i+2; x<(i+5); x++){
                    blocks.add(new Objects(breakableBrick, false,true, sizeDiff * x, this.Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                }
            }

        }
        for(int i =0; i<floor.size();i++){
            stage1.add(floor.get(i));
        }
        for(int i =0; i<backgroundObjects.size();i++){
            stage1.add(backgroundObjects.get(i));
        }
        for(int i =0; i<blocks.size();i++){
            stage1.add(blocks.get(i));
        }


    }


    public void background(Canvas c){
        c.drawBitmap(sky,null,skyDst,paint);
    }
    public void walking(Canvas c){


        for(int i = 0; i<stage1.size();i++){
            stage1.get(i).moving(Width);
        }
        c.drawBitmap(mario.get(move), null, marioSize, paint);
        move ++;
        if(move ==4){
            move = 1;
        }
    }


    public void renderGame(Canvas c) {
        background(c);

        for(int i =0; i<stage1.size();i++){
            c.drawBitmap(stage1.get(i).bitmap,null,stage1.get(i).Dst,paint);
        }
        walking(c);

    }
}
