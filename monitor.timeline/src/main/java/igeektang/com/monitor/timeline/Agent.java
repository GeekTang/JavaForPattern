package igeektang.com.monitor.timeline;

import java.io.InputStream;

public interface Agent {
    
    void active();
    
    void stop();
    
    void loadConfiguration(InputStream in);
    
    void loadPlugin();
    
    String validat(InputStream in);

}
