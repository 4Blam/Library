package ablam;

import java.sql.Connection;

public interface Connector {
    /**
     * Allows us to connect to our database
     * @return returns connection to our database
     */
    Connection connect();
}
