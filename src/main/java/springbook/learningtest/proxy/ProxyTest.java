package springbook.learningtest.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Proxy;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-12-02
 */
public class ProxyTest {
    String name = "Kyu";

    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();

        assertThat(hello.sayHello(name), is("Hello Kyu"));
        assertThat(hello.sayHi(name), is("Hi Kyu"));
        assertThat(hello.sayThankYou(name), is("Thank You Kyu"));

        Hello proxiedHello = new HelloUppercase(new HelloTarget());
        assertThat(proxiedHello.sayHello(name), is("HELLO KYU"));
        assertThat(proxiedHello.sayHi(name), is("HI KYU"));
        assertThat(proxiedHello.sayThankYou(name), is("THANK YOU KYU"));

        Hello dynamicProxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UppercaseHandler(new HelloTarget())
        );
        assertThat(dynamicProxiedHello.sayHello(name), is("HELLO KYU"));
        assertThat(dynamicProxiedHello.sayHi(name), is("HI KYU"));
        assertThat(dynamicProxiedHello.sayThankYou(name), is("THANK YOU KYU"));
    }

    @Test
    public void proxyFactoryBean() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());

        Hello proxiedHello = (Hello) pfBean.getObject();
        assertThat(proxiedHello.sayHello(name), is("HELLO KYU"));
        assertThat(proxiedHello.sayHi(name), is("HI KYU"));
        assertThat(proxiedHello.sayThankYou(name), is("THANK YOU KYU"));
    }

    @Test
    public void pointcutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) pfBean.getObject();
        assertThat(proxiedHello.sayHello(name), is("HELLO KYU"));
        assertThat(proxiedHello.sayHi(name), is("HI KYU"));
        assertThat(proxiedHello.sayThankYou(name), is("Thank You Kyu"));
    }

    static class UppercaseAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            String ret = (String) methodInvocation.proceed();
            return ret.toUpperCase();
        }
    }
}
