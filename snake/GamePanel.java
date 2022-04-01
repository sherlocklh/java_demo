package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int lenth; //蛇的长度；
    int[] snakeX = new int[600];// 蛇的坐标
    int[] snakeY = new int[500]; // 蛇的坐标Y
    String fx = "R"; //蛇的方向 ： R
    boolean isStart = false; // 游戏是否开始
    Timer timer = new Timer(100, this); //定时器：第一个参数，就是定时执行时间。
    // 食物
    int foodx; // 食物的坐标
    int foody;// 食物的坐标
    Random random = new Random();
    boolean isFail = false; // 游戏是否结束;
    int score;  // 游戏的分数！
    public GamePanel() {
        init();
        this.setFocusable(true);  // 获取焦点事件。
        this.addKeyListener(this); // 键盘监听事件。
        timer.start(); //定时执行时间,调用开始的方法。
    }
    public void init() {
        lenth = 3; //初始小蛇有三节,包括小脑袋
        //初始化开始的蛇,给蛇定位,
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        foodx = 25 + 25 * random.nextInt(34); //初始化食物横坐标为一个随机数，
        foody = 75 + 25 * random.nextInt(24);//初始化食物纵坐标为一个随机数，
        score = 0;//        初始化分数为零。
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);//设置面板的背景色----为白色。
        Data.header.paintIcon(this, g, 25, 11);//绘制头部信息区域
        g.fillRect(25, 75, 850, 600);//绘制游戏区域
        if (fx.equals("R")) {//蛇的头通过方向变量来判断
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);  // 从Data资源类中找到向右边小蛇图标，当前对象，用画壁画，身体的位置跟着动。
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);// 左边
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]); // 上面移动
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]); // 下面移动
        }
        for (int i = 1; i < lenth; i++) { // 对小蛇的身体进行遍历。
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]); //蛇的身体长度根据lenth来控制
        }
        //画食物
        Data.food.paintIcon(this, g, foodx, foody);
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度" + lenth, 750, 35);
        g.drawString("分数" + score, 750, 50);
        //游戏提示
        if (isStart == false) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏!", 300, 300);
        }
        //失败判断
        if (isFail) {
            g.setColor(Color.RED); // 失败的话字体变成红色。
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("失败, 按下空格重新开始", 200, 300); // 提示的字体，位置。
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且没有结束，则小蛇可以移动
        if (isStart && isFail == false) {
            //右移:即让后一个移到前一个的位置即可 !
            for (int i = lenth - 1; i > 0; i--) { //除了脑袋都往前移：身体移动
                snakeX[i] = snakeX[i - 1]; //即第i节(后一节)的位置变为(i-1：前一节)节的位置！
                snakeY[i] = snakeY[i - 1];
            }
            //通过方向控制，头部移动
            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 850) snakeX[0] = 25;  // 如果小蛇向右移动超出的了边界，那么久让小蛇回到左边的初始点。
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0] < 25) snakeX[0] = 850;
            } else if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 75) snakeY[0] = 650;
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 650) snakeY[0] = 75;
            }
            //吃食物:当蛇的头和食物一样时,算吃到食物!
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                //1.长度加一
                lenth++;
                //每吃一个食物，增加积分
                score = score + 10;
                //2.重新生成食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }
            //结束判断，头和身体撞到了
            for (int i = 1; i < lenth; i++) {
                //如果头和身体碰撞，那就说明游戏失败
                if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
                    isFail = true;
                }
            }
            repaint(); //需要不断的更新页面实现动画。
        }
        timer.start();//让时间动起来!
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); //获取按下的键盘
        if (keyCode == KeyEvent.VK_SPACE) { //如果是空格
            if (isFail) { //如果游戏失败,从头再来！
                isFail = false;
                init(); //重新初始化
            } else { //否则，暂停游戏
                isStart = !isStart;
            }
            repaint();
        }
        //键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT) {  // 按下了键盘中的左方向
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {// 右边按键
            fx = "R";
        } else if (keyCode == KeyEvent.VK_UP) {// 上键
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) { // 下键
            fx = "D";
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}