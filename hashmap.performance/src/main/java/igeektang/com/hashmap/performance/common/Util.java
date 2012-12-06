/**
 * 
 */
package igeektang.com.hashmap.performance.common;

import java.util.Map;

/**
 * @author Ares Tang
 *
 */
public class Util {

    public static void generateData(Map<Object, Object> map, int contentSize) {
        while(contentSize-->0)
        {
            map.put(contentSize, contentSize);
        }
    }

    
}
