/**
 * 
 */
package igeektang.com.hashmap.performance.write;

import igeektang.com.hashmap.performance.common.TaskFactory;
import igeektang.com.hashmap.performance.common.TimeCostStatisticTask;

import java.util.Map;

/**
 * @author Ares Tang
 *
 */
public class WriteTaskFactory implements TaskFactory {
    
    private Map<Object, Object> mapInstance;

    private boolean synchroniz;

    private int times;
    
    private WriteModel model;

    public WriteTaskFactory(Map<Object, Object> map, boolean synchronizeRead, int readTimes, WriteModel writeModel) {
        mapInstance = map;
        synchroniz = synchronizeRead;
        times = readTimes;
        model = writeModel;
    }


    public TimeCostStatisticTask newInstance() {
        switch(model)
        {
        case ADD:
            return new WriteNewTask(mapInstance, synchroniz, times);
        case UPDATE:
            return new UpdateTask(mapInstance, synchroniz, times);
        default:
            return null;
        }
    }

}
