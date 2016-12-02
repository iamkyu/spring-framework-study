package springbook.learningtest.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-12-02
 */
public class ProxyTest {
    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        String name = "Kyu";
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
}
