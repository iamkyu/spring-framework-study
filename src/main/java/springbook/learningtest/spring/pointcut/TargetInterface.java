package springbook.learningtest.spring.pointcut;

/**
 * @author Kj Nam
 * @since 2016-08-15
 */
public interface TargetInterface {
    public void hello();
    public void hello(String a);
    public int minus(int a, int b) throws RuntimeException;
    public int plus(int a, int b);
}
