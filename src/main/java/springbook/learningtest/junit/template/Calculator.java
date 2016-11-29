package springbook.learningtest.junit.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2016-11-29
 */
public class Calculator {
    public Integer calSum(String filePath) throws IOException {
        LineCallback sumCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public Integer calMultiply(String filePath) throws IOException {
        LineCallback multiplyCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filePath, multiplyCallback, 1);
    }

    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            int ret = callback.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer lineReadTemplate(String filePath, LineCallback lineCallback, int intVal) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            Integer res = intVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = lineCallback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
