package service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Component
public class PubHousesServiceImpl implements PubHousesService {
    public HashMap<Long, String> getPHNames(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PubHouse[]> res = restTemplate.getForEntity("http://localhost:8042/phs", PubHouse[].class);
        HashMap<Long, String> names = new HashMap<>();
        PubHouse[] pubHouses = res.getBody();
        Arrays.stream(pubHouses).map(PubHouse::getPhID);
        for(PubHouse pubHouse : pubHouses){
            names.put(pubHouse.getPhID(), pubHouse.getName());
        }
        return names;
    }
}
