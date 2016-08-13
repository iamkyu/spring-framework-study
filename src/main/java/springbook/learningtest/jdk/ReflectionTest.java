package springbook.learningtest.jdk;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-07-31
 */
public class ReflectionTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";
        assertThat(name.length(), is(6));

        Method lengthMethod = String.class.getMethod("length");
        assertThat(lengthMethod.invoke(name), is(6));

        assertThat(name.charAt(0), is('S'));

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat(charAtMethod.invoke(name, 0), is('S'));

    }
}
