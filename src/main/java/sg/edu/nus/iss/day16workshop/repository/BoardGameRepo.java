package sg.edu.nus.iss.day16workshop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.iss.day16workshop.model.Mastermind;

@Repository
public class BoardGameRepo {
       
    @Autowired RedisTemplate<String, String> template;

    // save into redis gameID : JsonObject.toString()
    public int saveGame(final Mastermind md) {
        template.opsForValue()
            .set(md.getId(), md.toJSON().toString());
        String result = (String) template
            .opsForValue().get(md.getId());
        // if Redis has game saved, return 1; else return 0
        if (result != null) {
            return 1;
        }
        return 0;
    }

    // find Json String using key mdId and convert to java object
    public Mastermind findById(final String msId) throws IOException {
        String jsonStrVal = (String) template.opsForValue().get(msId);
        Mastermind m = Mastermind.create(jsonStrVal);
        return m;
    }

    public int updateBoardGame(final Mastermind ms) {
        // get the String from key msId
        String result = (String) template.opsForValue().get(ms.getId());
        
        // if isUpSert is true && stored ms data found, replace old data
        // if isUpSert is true && no previous stored ms data, generate msId and store
        if (ms.isUpSert()) {
            if(result != null) {
                template.opsForValue().set(ms.getId(), ms.toJSON().toString());
            } else {
                ms.setId(ms.generateId(8));
                template.opsForValue().setIfAbsent(ms.getId(), ms.toJSON().toString());
            }
        // if isUpSert is false, replace data if ms data was found
        } else {
            if (result != null) 
            {
                template.opsForValue().set(ms.getId(), ms.toJSON().toString());
            }
        }

        // check if ms data is stored
        result = template.opsForValue().get(ms.getId());

        // if result is not null, return 1; else 0
        return result != null ? 1 : 0;
    }
}
