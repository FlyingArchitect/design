package CGLIB代理;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import 被代理对象.BuyHouseImpl;
import 被代理对象.CglibWork;

import java.lang.reflect.Method;

/**
 * @Description: CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。但因为采用的是继承，所以不能对final修饰的类进行代理。
 * @Date:2018/10/16 15:27
 * @Author: 佳山
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass( this.target.getClass() );
        enhancer.setCallback( this );
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println( "======" );
        ////////////// invokeSuper  ///////////////
        Object result = methodProxy.invokeSuper( o, objects );
        System.out.println( "======" );
        return result;
    }

    public static void main(String[] args) {
        CglibWork cglibWork = new CglibWork();
        CglibProxy cglibProxy = new CglibProxy();
        CglibWork cglibProxyInstance = (CglibWork) cglibProxy.getInstance( cglibWork );
        cglibProxyInstance.method1();
        System.out.println("--------------------");
        BuyHouseImpl buyHouse = (BuyHouseImpl) cglibProxy.getInstance(new  BuyHouseImpl() );
        buyHouse.buyHouse();
    }

    // CGLIB创建的动态代理对象比JDK创建的动态代理对象的性能更高，但是CGLIB创建代理对象时所花费的时间却比JDK多得多。
    // 所以对于单例的对象，因为无需频繁创建对象，用CGLIB合适，反之使用JDK方式要更为合适一些。同时由于CGLib由于是采用动态创建子类的方法，
    // 对于final修饰的方法无法进行代理。
}
