package springbook.learningtest.junit.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-11-29
 */
public class CalSumTest {
    Calculator calculator;
    String numFilePath;

    @Before
    public void setUp() throws IOException {
        this.calculator = new Calculator();
        this.numFilePath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calSum(this.numFilePath), is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calMultiply(this.numFilePath), is(24));
    }
}
