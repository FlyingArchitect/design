package 动态代理JDK;

import 被代理对象.BuyHouse;
import 被代理对象.BuyHouseImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Date:2018/10/16 15:15
 * @Author: 佳山
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object object;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println( "买房前准备" );
        Object result = method.invoke( object, args );
        System.out.println( "买房后装修" );
        return result;
    }

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        BuyHouse buyHouse1Proxy = (BuyHouse) Proxy.newProxyInstance( BuyHouse.class.getClassLoader(), new Class[]{BuyHouse.class}, new DynamicProxyHandler( buyHouse ) );
        buyHouse1Proxy.buyHouse();
    }
}
