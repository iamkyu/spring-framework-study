package springbook.learningtest.jdk;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-12-02
 */
public class ReflectionTest {
    @Test
    public void invokeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name = "Spring";
        assertThat(name.length(), is(6));

        Method lengthMethod = String.class.getMethod("length");
        assertThat(lengthMethod.invoke(name), is(6));

        assertThat(name.charAt(0), is('S'));

        Method chartAtMethod = String.class.getMethod("charAt", int.class);
        assertThat(chartAtMethod.invoke(name, 0), is('S'));
    }
}
