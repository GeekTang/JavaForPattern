package igeektang.com.monitor.timeline;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketAgent implements Agent {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketAgent.class);
    
    private int port=16060;

    public void active() {
        // TODO Auto-generated method stub

    }

    public void stop() {
        // TODO Auto-generated method stub

    }

    public void loadConfiguration(InputStream in) {
        Properties config = new Properties();
        try {
            config.load(in);
            if(config.get("PORT")!=null)
            {
                port = (Integer) config.get("PORT");
            }
        } catch (IOException e) {
            LOGGER.warn("Configuration is invalide, using default value.");
        }
    }

    public void loadPlugin() {
        // TODO Auto-generated method stub

    }

    public String validat(InputStream in) {
        // TODO Auto-generated method stub
        return null;
    }

}
