package springbook.learningtest.spring.factorybean;

/**
 * @author Kj Nam
 * @since 2016-12-02
 */
public class Message {
    String text;

    private Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}
