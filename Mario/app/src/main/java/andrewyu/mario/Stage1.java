package andrewyu.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/*
 * Created by Andrew Yu on 5/17/2015.
 */
public class Stage1{
    public boolean drop;
    private Bitmap sky;
    private ArrayList<Bitmap> mario, marioWhite, marioRed, marioGreen;
    private int Width;
    private int Height;
    private int evolveTo;
    private Paint paint;
    private Rect skyDst;
    private Rect marioDst;
    public int move;
    public int gravity;
    int marioDiff, jumpCounter;
    private Bitmap emptyBrick;



    public ArrayList<Objects> stage1 = new ArrayList<>();

    public Stage1(ArrayList<Bitmap> marioRed, ArrayList<Bitmap> marioWhite, ArrayList<Bitmap> marioGreen, Bitmap goomba, Bitmap shell, Bitmap sky, Bitmap ground,Bitmap cloud1,Bitmap cloud2,Bitmap cloud3,Bitmap breakableBrick,Bitmap questionMarkBrick,Bitmap emptyQuestionBrick,Bitmap tube,Bitmap castle, Bitmap flower, float Width, float h){
        ArrayList<Objects> floor = new ArrayList<>();
        ArrayList<Objects> backgroundObjects = new ArrayList<>();
        ArrayList<Objects> blocks = new ArrayList<>();
        evolveTo = 0;
        jumpCounter = 0;
        move =0;
        this.Width = (int)Width;
        this.Height = (int)h;
        gravity = Height/49;
        marioDiff = this.Width/45;
        int sizeDiff = this.Width/25;
        this.marioRed = marioRed;
        this.marioWhite = marioWhite;
        this.marioGreen = marioGreen;
        this.mario = this.marioRed;
        this.sky = sky;
        this.emptyBrick = emptyQuestionBrick;

        paint = new Paint();
        skyDst = new Rect(0,0,this.Width, Height);
        marioDst = new Rect(0,  (Height/2), 2*marioDiff,(Height/2+2*marioDiff));

        for(int i = 0; i <100; i++) {
            if( i == 40 || i == 41  || i == 59 || i == 60 || i == 75|| i == 76 || i == 77)
                continue;
            floor.add(new Objects(ground, Objects.Type.floor,false, false,false, sizeDiff * i,  Height - sizeDiff, sizeDiff, sizeDiff));
            if(i == 27){
                floor.add(new Objects(tube, Objects.Type.floor, false, false, false,sizeDiff *i,(Height -5*sizeDiff),sizeDiff*2,4*sizeDiff));
                floor.add(new Objects(tube, Objects.Type.floor, false, false, false,sizeDiff *(i+8),(Height -5*sizeDiff),sizeDiff*2,4*sizeDiff));
            }
            if(i == 80){
                for(int x = i; x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, Objects.Type.floor,false, false,false, sizeDiff * x,  Height - 2*sizeDiff, sizeDiff, sizeDiff));
                }

                for(int x = i+2;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, Objects.Type.floor,false, false,false, sizeDiff * x,  Height - 4*sizeDiff, sizeDiff, sizeDiff));
                }

                for(int x = i+4;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, Objects.Type.floor,false, false,false, sizeDiff * x,  Height - 6*sizeDiff, sizeDiff, sizeDiff));
                }

                for(int x = i+6;x<89;x++){
                    floor.add(new Objects(emptyQuestionBrick, Objects.Type.floor,false, false,false, sizeDiff * x,  Height - 8*sizeDiff, sizeDiff, sizeDiff));
                }

                for(int x = 3; x<10; x+=2) {
                    floor.add(new Objects(emptyQuestionBrick, Objects.Type.floor, false, false,false, sizeDiff * 88,  Height - x * sizeDiff, sizeDiff, sizeDiff));
                }
            }
        }
        for(int i = 0; i< 100; i++){
            if(i == 7 || i == 20 || i == 40 || i == 55 || i == 60 || i == 72 || i == 89)
                backgroundObjects.add(new Objects(cloud1,Objects.Type.background,false,false,false,sizeDiff*i, Height/16,this.Width/9,this.Width/9));
            if(i == 13 ||  i == 26 ||  i == 35 ||  i == 76)
                backgroundObjects.add(new Objects(cloud2,Objects.Type.background,false,false,false,sizeDiff*i, Height/22,this.Width/9,this.Width/9));
            if(i == 15 || i == 30 || i == 92)
                backgroundObjects.add(new Objects(cloud3,Objects.Type.background,false,false,false,sizeDiff*i, Height/19,this.Width/5,this.Width/9));
            if(i == 92)
                backgroundObjects.add(new Objects(castle,Objects.Type.background,false,false,true,sizeDiff * i,  Height - 8*sizeDiff, (6)*sizeDiff, (7)*sizeDiff));
        }

        for(int i = 0; i<100;i++){
            if(i == 10) {
                blocks.add(new Objects(breakableBrick, Objects.Type.brick,false,true,false, sizeDiff * i,  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(flower, Objects.Type.evolve, false,false,false, sizeDiff * (i+1),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, Objects.Type.brick, true,false,false, sizeDiff * (i+1),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(breakableBrick, Objects.Type.brick, false,true,false, sizeDiff * (i+2),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(flower, Objects.Type.evolve, false,false,false, sizeDiff * (i+3),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, Objects.Type.brick, true,false,false, sizeDiff * (i+3),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(breakableBrick, Objects.Type.brick, false,true,false, sizeDiff * (i+4),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
            }
            if(i ==50){
                blocks.add(new Objects(breakableBrick, Objects.Type.brick, false,true,false, sizeDiff * i,  Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                blocks.add(new Objects(flower, Objects.Type.evolve, false,false,false, sizeDiff * (i+1),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, Objects.Type.brick, true,false,false, sizeDiff * (i+1),  Height - (5 * sizeDiff), sizeDiff, sizeDiff));
                blocks.add(new Objects(breakableBrick, Objects.Type.brick, false,true,false, sizeDiff * (i+2),  Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                for(int x = i+3; x<67;x++){
                    if(x == 60 || x == 61 || x == 62)
                        continue;
                    blocks.add(new Objects(breakableBrick, Objects.Type.brick, false,true,false, sizeDiff * x,  Height - 9 * sizeDiff, sizeDiff, sizeDiff));
                }
            }
            if(i == 67){
                blocks.add(new Objects(breakableBrick, Objects.Type.brick, false,true,false, sizeDiff * i,  Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                blocks.add(new Objects(flower, Objects.Type.evolve, false,false,false, sizeDiff * i,  Height - 9 * sizeDiff, sizeDiff, sizeDiff));
                blocks.add(new Objects(questionMarkBrick, Objects.Type.brick, true,false,false, sizeDiff * i,  Height - 9 * sizeDiff, sizeDiff, sizeDiff));
                for(int x = i+2; x<(i+5); x++){
                    blocks.add(new Objects(breakableBrick, Objects.Type.brick, false,true,false, sizeDiff * x,  Height - 5 * sizeDiff, sizeDiff, sizeDiff));
                }
            }

        }
        for(int i =10; i<15;i+=2){
            stage1.add(new Objects(goomba, Objects.Type.monster, false, true,false, sizeDiff *i, Height - 2*sizeDiff, sizeDiff, sizeDiff));
        }
        for(int i = 45; i<52; i+=2){
            stage1.add(new Objects(goomba, Objects.Type.monster, false, true,false, sizeDiff *i, Height - 2*sizeDiff, sizeDiff, sizeDiff));
        }
        for(int i = 63; i<68; i+=2){
            blocks.add(new Objects(goomba, Objects.Type.monster, false,true,false, sizeDiff * i,  Height - 10 * sizeDiff, sizeDiff, sizeDiff));
        }
        for(int i = 85; i<89; i+=2){
            blocks.add(new Objects(goomba, Objects.Type.monster, false,true,false, sizeDiff * i, 0, sizeDiff, sizeDiff));
        }
        for(int i = 31; i<34;i+=2){
            stage1.add(new Objects(shell, Objects.Type.monster, false, true,false, sizeDiff *i, Height - 2*sizeDiff, sizeDiff, sizeDiff));
        }

        for(int i =0; i<backgroundObjects.size();i++){
            stage1.add(backgroundObjects.get(i));
        }

        for(int i =0; i<blocks.size();i++){
            stage1.add(blocks.get(i));
        }
        for(int i =0; i<floor.size();i++){
            stage1.add(floor.get(i));
        }


    }


    public void background(Canvas c){
        c.drawBitmap(sky,null,skyDst,paint);
    }

    public void action(boolean left, boolean right, boolean jump) {
        boolean stop = false;
        if (right) {
            for(int i =0; i<stage1.size();i++) {
                if (stage1.get(i).type != Objects.Type.evolve && stage1.get(i).type != Objects.Type.background && stage1.get(i).type!= Objects.Type.monster && marioDst.intersects(marioDst, stage1.get(i).Dst) && marioDst.centerX() <= stage1.get(i).Dst.left && marioDst.centerY() >= stage1.get(i).Dst.top && marioDst.centerY() <= stage1.get(i).Dst.bottom) {
                    stop = true;
                }
            }
            if (marioDst.centerX() < Width / 2) {

                if(!stop)
                    marioDst.offset((Width / 45), 0);
            }
                if (marioDst.centerX() >= Width / 2) {
                        if(!stop)
                            for(int i=0;i<stage1.size();i++)
                                stage1.get(i).moving(Width);
                }
                move++;
                if (move == 4) {
                    move = 1;
                }
        }

            if (left) {
                for(int i =0; i<stage1.size();i++) {
                    if (stage1.get(i).type != Objects.Type.evolve && stage1.get(i).type != Objects.Type.background && stage1.get(i).type!= Objects.Type.monster && marioDst.intersects(marioDst, stage1.get(i).Dst) && marioDst.centerX() >= stage1.get(i).Dst.right && marioDst.centerY() >= stage1.get(i).Dst.top && marioDst.centerY() <= stage1.get(i).Dst.bottom) {
                        stop = true;
                    }
                }
                if (marioDst.left >= marioDiff && !stop) {
                    marioDst.offset(-(Width) / 45, 0);
                }
                move++;
                if (move == 8) {
                    move = 5;
                }

            }
            if (jump) {
                jumpCounter++;
                for(int i =0; i<stage1.size();i++) {
                    if (stage1.get(i).type != Objects.Type.evolve && stage1.get(i).type != Objects.Type.monster && stage1.get(i).type != Objects.Type.background && marioDst.intersects(marioDst, stage1.get(i).Dst) && marioDst.centerY() >= stage1.get(i).Dst.bottom && marioDst.centerX() >= stage1.get(i).Dst.left && marioDst.centerX() <= stage1.get(i).Dst.right) {
                        jumpCounter = 11;
                    }
                }
                for(int i=0;i<stage1.size();i++) {
                    if (stage1.get(i).breakable && marioDst.intersects(marioDst, stage1.get(i).Dst) && marioDst.centerX() < stage1.get(i).Dst.right && marioDst.centerX() > stage1.get(i).Dst.left && marioDst.centerY()> stage1.get(i).Dst.bottom) {
                        stage1.remove(i);
                        jumpCounter = 11;
                    }
                        if(stage1.get(i).content && marioDst.intersects(marioDst,stage1.get(i).Dst) && marioDst.centerX()<stage1.get(i).Dst.right && marioDst.centerX()>stage1.get(i).Dst.left && marioDst.centerY()> stage1.get(i).Dst.bottom){
                            stage1.get(i).bitmap = emptyBrick;
                            stage1.get(i).content = false;
                            if(stage1.get(i-1).type == Objects.Type.evolve)
                                stage1.get(i-1).Dst.offset(0,-Width/25);
                    }
                }
                if (jumpCounter < 10) {
                    marioDst.offset(0, -(int) (1.5 * marioDiff));
                }


            }

    }

    public void evolve(){
        for(int i=0;i<stage1.size();i++){
            if(stage1.get(i).type == Objects.Type.evolve && marioDst.intersects(marioDst, stage1.get(i).Dst) && marioDst.centerY()<stage1.get(i).Dst.bottom && !stage1.get(i+1).content){
                if(evolveTo == 1) {
                    mario = marioGreen;
                    evolveTo = 2;
                }
                else if(evolveTo == 0){
                    mario = marioWhite;
                    evolveTo = 1;
                }
                stage1.remove(i);

            }
        }
    }

    public void gravity(){
        drop = true;
        for(int i =0;i<stage1.size();i++) {
            if(stage1.get(i).type == Objects.Type.monster && marioDst.intersects(marioDst, stage1.get(i).Dst) && marioDst.centerY()<stage1.get(i).Dst.top && marioDst.centerX()<stage1.get(i).Dst.right && marioDst.centerX()>stage1.get(i).Dst.left) {
                stage1.remove(i);
            }
            if(stage1.get(i).type != Objects.Type.evolve && stage1.get(i).type!= Objects.Type.background && stage1.get(i).type!= Objects.Type.monster && marioDst.intersects(marioDst,stage1.get(i).Dst)){
                if(marioDst.centerY()<stage1.get(i).Dst.top){
                    drop = false;
                }
            }
        }
        if(drop){
            marioDst.offset(0,gravity);
        }
    }

    public int gameOver(){
        int condition = 0;

        if(marioDst.centerY()>Height)
            condition = 1;
        for(int i =0; i<stage1.size();i++){
            if(stage1.get(i).endingPoint){
                if(marioDst.centerX() >= stage1.get(i).Dst.centerX())
                    condition = 2;
            }
            if(stage1.get(i).type == Objects.Type.monster && marioDst.intersects(marioDst, stage1.get(i).Dst) && marioDst.centerY()>=stage1.get(i).Dst.top){
                if(evolveTo == 2)
                {
                    evolveTo--;
                    mario = marioWhite;
                    stage1.remove(i);
                    return 0;
                }
                else if(evolveTo == 1)
                {
                    evolveTo--;
                    mario = marioRed;
                    stage1.remove(i);
                    return 0;
                }
                condition = 1;
            }
        }

        return condition;

    }


    public void renderGame(Canvas c) {
        background(c);


        for(int i =0; i<stage1.size();i++){
            if(stage1.get(i).type== Objects.Type.monster){
                stage1.get(i).monsterMoving(Width,Height,stage1,gravity);
            }
            c.drawBitmap(stage1.get(i).bitmap, null, stage1.get(i).Dst, paint);
        }
        gravity();
        evolve();
        c.drawBitmap(mario.get(move), null, marioDst, paint);


    }
}
