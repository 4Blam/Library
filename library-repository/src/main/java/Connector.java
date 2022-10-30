import java.sql.Connection;

/**
 * This interface provides method for connecting to database
 */
public interface Connector {
    /**
     * Allows us to connect to our database
     * @return returns connection to our database
     */
    Connection connect() throws IllegalAccessException;
}
