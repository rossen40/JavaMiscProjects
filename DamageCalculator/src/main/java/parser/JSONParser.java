package main.java.parser;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.config.Configuration;

public class JSONParser {

    private final Logger logger = LoggerFactory.getLogger(JSONParser.class);

    private ObjectMapper mapper = new ObjectMapper();

    public Configuration parseConfiguration(String file) throws IOException {
        InputStream is = new ClassPathResource(file).getInputStream();
        Configuration config = mapper.readValue(is, Configuration.class);
        
        logger.info("Parsed configuration from JSON: " + config);
        //TODO DELETE ME !
        config.pritnBaseArmyFactors();
        
        return config;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

}
