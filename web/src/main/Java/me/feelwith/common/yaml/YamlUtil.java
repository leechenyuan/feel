package me.feelwith.common.yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Created by L on 2017/2/11.
 */
public class YamlUtil {
    public String toString(Object ob){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.writeValueAsString(ob);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
