/**
 * 
 */
package igeektang.com.hashmap.performance.write;

import igeektang.com.hashmap.performance.common.TimeCostStatisticTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author eweitan
 *
 */
public class UpdateTask extends TimeCostStatisticTask {

    private final Map<Object, Object> map;

    private static final int READ_KEY_SIZE = 10;

    private List<Object> readKeyList;

    private final boolean synchronize;

    private final long times;

    public UpdateTask(Map<Object, Object> writemap, boolean synchronizeRead, long executionTimes) {
        map = writemap;
        synchronize = synchronizeRead;

        generateKeyList();

        times = executionTimes;
    }

    private void generateKeyList() {
        Set<Object> keySet = map.keySet();
        List<Object> keyList = new ArrayList<Object>();
        for (Object key : keySet) {
            keyList.add(key);
        }
        if (keyList.size() < READ_KEY_SIZE) {
            readKeyList = keyList;
        } else {
            readKeyList = new ArrayList<Object>();
            int i = 0;
            while (i < READ_KEY_SIZE) {
                readKeyList.add(keyList.get((int) (keyList.size() * i / READ_KEY_SIZE)));
                i++;
            }
        }
    }

    @Override
    protected void execute() {
        int i = 0;
        while (i++ < times) {
            Object key = readKeyList.get((int)(Math.random() * readKeyList.size()));
            if (synchronize) {
                synchronized (map) {
                    map.put(key, i);
                }
            } else {
                map.put(key, i);
            }
        }
    }

}
