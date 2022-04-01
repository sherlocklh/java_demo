package snake;
import javax.swing.*;
import java.net.URL;
public class Data {
    public static URL headerUrl = Data.class.getResource("/snake/statics/header.png");  // 获取图片的路径。
    public static ImageIcon header = new ImageIcon(headerUrl); // new了一个ImageIcon对象，图片的头像放到游戏里边。
    //头部：上下左右
    public static URL upUrl = Data.class.getResource("/snake/statics/up.png");
    public static URL downUrl = Data.class.getResource("/snake/statics/down.png");
    public static URL leftUrl = Data.class.getResource("/snake/statics/left.png");
    public static URL rightUrl = Data.class.getResource("/snake/statics/right.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);
    //身体的图片。
    public static URL bodyUrl = Data.class.getResource("/snake/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //食物的图片。
    public static URL foodUrl = Data.class.getResource("/snake/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}