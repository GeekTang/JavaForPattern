/**
 * 
 */
package igeektang.com.hashmap.performance.read;

import igeektang.com.hashmap.performance.common.TaskFactory;

import java.util.Map;

/**
 * @author Ares Tang
 * 
 */
public class ReadTaskFactory implements TaskFactory{

    private Map<Object, Object> mapInstance;

    private boolean synchroniz;

    private int times;

    public ReadTaskFactory(Map<Object, Object> map, boolean synchronizeRead, int readTimes) {
        mapInstance = map;
        synchroniz = synchronizeRead;
        times = readTimes;
    }

    public ReadTask newInstance() {
        return new ReadTask(mapInstance, synchroniz, times);
    }

}
