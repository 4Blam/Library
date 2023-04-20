package service;

import java.util.HashMap;

public interface PubHousesService {
    /**
     * Getting Ph names with resttemplate call into another service
     * @return Hashmap with Publishing houses pairs <phid; phname>
     */
    public HashMap<Long, String> getPHNames();
}
