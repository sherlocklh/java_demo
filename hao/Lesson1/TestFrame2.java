package hao.Lesson1;

import java.awt.*;

public class TestFrame2 {
    public static void main(String[] args) {
        MyFrame myFrame1=new MyFrame(100,100,100,100,Color.blue);
        MyFrame myFrame2=new MyFrame(200,100,100,100,Color.red);
        MyFrame myFrame3=new MyFrame(200,200,100,100,Color.yellow);
        MyFrame myFrame4=new MyFrame(100,200,100,100,Color.black);


    }

    static class MyFrame extends Frame{
        static int id=0;
        public MyFrame(int x,int y,int w,int h,Color color){
            super("MyFrame"+(++id));
            setBackground(color);
            setBounds(x,y,w,h);
            setVisible(true);
        }
    }
}
