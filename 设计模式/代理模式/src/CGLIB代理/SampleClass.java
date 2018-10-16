package CGLIB代理;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Date:2018/10/16 16:40
 * @Author: 佳山
 */
public class SampleClass {
    public void test() {
        System.out.println( "hello world" );
    }


    public static void main(String[] args) {
        System.setProperty( DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\classes" );
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass( SampleClass.class );
        enhancer.setCallback( new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println( "before method run..." );
                Object result = proxy.invokeSuper( obj, args );
                // result = proxy.invoke( obj, args );//不能使用
                System.out.println( "after method run..." );
                return result;
            }
        } );
        SampleClass sample = (SampleClass) enhancer.create();
        sample.test();
    }
}
