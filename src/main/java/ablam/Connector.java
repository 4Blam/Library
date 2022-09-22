package ablam;

import java.sql.Connection;

public interface Connector {
    /**
     * This method allows us to connect to our database
     * @return returns connection to our database
     */
    Connection connect();
}
