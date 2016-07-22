package springbook.user.dao;

/**
 * @author Kj Nam
 * @since 2016-07-20
 */
public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException(Throwable cause) {
        super(cause);
    }
}
