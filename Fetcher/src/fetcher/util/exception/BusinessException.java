package fetcher.util.exception;

/**
 *
 * @author João Victor
 */
public class BusinessException extends RuntimeException {

    /**
     * Constructs an instance of <code>BusinessException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BusinessException(String msg) {
        super(msg);
    }
}
