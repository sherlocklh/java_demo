package sys;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.concurrent.SynchronousQueue;

public class DrawingMoney {
    public static void main(String[] args) {
        Account account=new Account(100,"资产");
        Drawing drawing=new Drawing(50,account,"我");
        Drawing drawing2=new Drawing(100,account,"老婆");

        drawing.start();
        drawing2.start();
    }
}
//账户
class Account {
    int money;//余额
    String name;//账户名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread{
    Account account;
    int drawingMoney;//取了多少钱
    int nowMoney;//现在手里有多少钱

    public Drawing(int drawingMoney, Account account,String name){
        super(name);
        this.account = account;
        this.drawingMoney=drawingMoney;
    }

    public void run(){
        //判断有没有钱
        synchronized (account){
            if(account.money-drawingMoney<0){
                System.out.println(Thread.currentThread().getName()+"钱不够，取不了");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money=account.money-drawingMoney;
            nowMoney=nowMoney+drawingMoney;
            System.out.println(account.name+"账户的余额"+account.money);
            System.out.println(this.getName()+"手里的钱"+nowMoney);
        }
    }
}
