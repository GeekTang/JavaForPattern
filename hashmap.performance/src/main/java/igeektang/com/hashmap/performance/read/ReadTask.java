/**
 * 
 */
package igeektang.com.hashmap.performance.read;

import igeektang.com.hashmap.performance.common.TimeCostStatisticTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ares Tang
 * 
 */
public class ReadTask extends TimeCostStatisticTask {

    private final Map<Object, Object> map;

    private static final int READ_KEY_SIZE = 10;

    private List<Object> readKeyList;

    private final boolean synchronize;

    private final long times;

    public ReadTask(Map<Object, Object> readmap, boolean synchronizeRead, long executionTimes) {
        map = readmap;
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
            Object key = readKeyList.get(i % readKeyList.size());
            if (synchronize) {
                synchronized (map) {
                    map.get(key);
                }
            } else {
                map.get(key);
            }
        }
    }

}
