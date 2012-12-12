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
public class RemoveTask extends TimeCostStatisticTask {

	private final Map<Object, Object> map;

	private static final int REMOVE_KEY_SIZE = 100000;

	private static List<Object> readKeyList;

	private final boolean synchronize;

	private final long times;

	public RemoveTask(Map<Object, Object> writemap, boolean synchronizeRead,
			long executionTimes) {
		map = writemap;
		synchronize = synchronizeRead;

		generateKeyList();

		times = executionTimes;
	}

	private synchronized void generateKeyList() {
		if (readKeyList == null) {
			Set<Object> keySet = map.keySet();
			List<Object> keyList = new ArrayList<Object>();
			for (Object key : keySet) {
				keyList.add(key);
			}
			if (keyList.size() < REMOVE_KEY_SIZE) {
				readKeyList = keyList;
			} else {
				readKeyList = new ArrayList<Object>();
				int i = 0;
				while (i < REMOVE_KEY_SIZE) {
					readKeyList
							.add(keyList.get((int) (keyList.size() / REMOVE_KEY_SIZE)*i));
					i++;
				}
			}
		}
	}

	@Override
	protected void execute() {
		int i = 0;
		while (i < REMOVE_KEY_SIZE) {
			Object key = readKeyList.get(i);
			if (synchronize) {
				synchronized (map) {
					map.remove(key);
				}
			} else {
				map.remove(key);
			}
			i++;
		}
	}

}
