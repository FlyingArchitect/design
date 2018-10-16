package 静态代理;

import 被代理对象.BuyHouse;
import 被代理对象.BuyHouseImpl;

/**
 * @Description:
 * @Date:2018/10/16 15:06
 * @Author: 佳山
 */
public class BuyHouseProxy implements BuyHouse{
    private BuyHouse buyHouse;

    public BuyHouseProxy(BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHouse() {
        System.out.println("买房前");
        buyHouse.buyHouse();
        System.out.println("买房后");
    }

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseProxy(new BuyHouseImpl());
        buyHouse.buyHouse();

    }
}
