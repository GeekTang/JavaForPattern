/**
 * 
 */
package igeektang.com.hashmap.performance.write;
import igeektang.com.hashmap.performance.common.TimeCostStatisticTask;

import java.util.Map;

/**
 * @author Ares Tang
 *
 */
public class WriteNewTask extends TimeCostStatisticTask {

    private final Map<Object, Object> map;

    private final boolean synchronize;

    private final long times;

    public WriteNewTask(Map<Object, Object> readmap, boolean synchronizeRead, long executionTimes) {
        map = readmap;
        synchronize = synchronizeRead;
        times = executionTimes;
    }


    @Override
    protected void execute() {
        int i = 0;
        while (i++ < times) {
            if (synchronize) {
                synchronized (map) {
                    map.put(i, i);
                }
            } else {
                map.put(i, i);
            }
        }
    }

}
