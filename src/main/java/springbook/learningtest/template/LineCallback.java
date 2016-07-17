package springbook.learningtest.template;

/**
 * @author Kj Nam
 * @since 2016-07-17
 */
public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
