package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2016-07-17
 */
public interface BufferedReaderCallback {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
