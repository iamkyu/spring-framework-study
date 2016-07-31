package springbook.learningtest.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-07-31
 */
public class HelloTargetTest {
    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("Kyu"), is("Hello Kyu"));
        assertThat(hello.sayHi("Kyu"), is("Hi Kyu"));
        assertThat(hello.sayThankYou("Kyu"), is("Thank You Kyu"));

        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] {Hello.class},
                new UpperCaseHandler(new HelloTarget()));
        assertThat(proxiedHello.sayHello("Kyu"), is("HELLO KYU"));
        assertThat(proxiedHello.sayHi("Kyu"), is("HI KYU"));
        assertThat(proxiedHello.sayThankYou("Kyu"), is("THANK YOU KYU"));

    }
}
