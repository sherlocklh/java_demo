package hao.Lesson1;

import java.awt.*;

public class TestFrame {
    public static void main(String[] args) {
        Frame frame=new Frame("我的第一个java图像界面窗口！");

        frame.setVisible(true);
        frame.setBackground(new Color(0x5B221B));
        frame.setSize(400,500);
        frame.setLocation(0,0);
        frame.setResizable(false);
    }
}
