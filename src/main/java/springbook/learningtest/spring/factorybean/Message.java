package springbook.learningtest.spring.factorybean;

/**
 * @author Kj Nam
 * @since 2016-08-09
 */
public class Message {
    String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}
