package springbook.learningtest.junit.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2016-11-30
 */
public interface BufferedReaderCallback {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
