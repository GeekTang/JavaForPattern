/**
 * 
 */
package igeektang.com.hashmap.performance.test;

import igeektang.com.hashmap.performance.common.InvalideParameters;
import igeektang.com.hashmap.performance.common.TaskFactory;
import igeektang.com.hashmap.performance.common.TimeCostStatisticTask;
import igeektang.com.hashmap.performance.common.Util;
import igeektang.com.hashmap.performance.read.ReadTaskFactory;
import igeektang.com.hashmap.performance.write.WriteModel;
import igeektang.com.hashmap.performance.write.WriteTaskFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ares Tang
 * 
 */
public class HashMapTest {

    private Map<Object, Object> hashMap;

    private Map<Object, Object> concurrentHashMap;

    private ExecutorService threadPoolService;

    private static final long UNIT = 10000;

    private static final int MAX_THREAD_NUMBER = 32;

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    public HashMapTest() {
        threadPoolService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);
    }

    private void init(int mapSize, int contentSize) {
        int initSize = mapSize > 0 ? mapSize : DEFAULT_INITIAL_CAPACITY;
        hashMap = new HashMap<Object, Object>(initSize);
        concurrentHashMap = new ConcurrentHashMap<Object, Object>(mapSize);
        Util.generateData(hashMap, contentSize);
        Util.generateData(concurrentHashMap, contentSize);
    }

    /**
     * @param mapSize
     *            The initial size the Hash Map
     * @param contentSize
     *            The total data existed in Hash Map
     * @param threadNumber
     *            Number of thread access the Hash Map at the same time
     * @param times
     *            How many read operation performed by each thread
     * @throws InvalideParameters
     */
    public void readLoadTest(int mapSize, int contentSize, int threadNumber, int times) throws InvalideParameters {
        if (threadNumber > MAX_THREAD_NUMBER) {
            throw new InvalideParameters("Thread number should not larger then " + MAX_THREAD_NUMBER);
        }
        init(mapSize, contentSize);
        ReadTaskFactory hashReadTaskFactory = new ReadTaskFactory(hashMap, true, times);
        ReadTaskFactory concurrentHashReadTaskFactory = new ReadTaskFactory(concurrentHashMap, false, times);

        long concurrentHashMapReadTime = runLoadTest(concurrentHashReadTaskFactory, threadNumber);
        long hashMapReadTime = runLoadTest(hashReadTaskFactory, threadNumber);

        System.out.println(generateResult("HashMap", hashMapReadTime, mapSize, contentSize, threadNumber, times));
        System.out.println(generateResult("ConcurrentHashMap", concurrentHashMapReadTime, mapSize, contentSize, threadNumber, times));
    }

    /**
     * @param contentSize
     *            The total data existed in Hash Map
     * @param threadNumber
     *            Number of thread access the Hash Map at the same time
     * @param times
     *            How many read operation performed by each thread
     * @throws InvalideParameters
     */
    public void writeLoadTest(int contentSize, int threadNumber, int times, WriteModel writeModel) throws InvalideParameters {
        if (threadNumber > MAX_THREAD_NUMBER) {
            throw new InvalideParameters("Thread number should not larger then " + MAX_THREAD_NUMBER);
        }
        if (WriteModel.UPDATE.equals(writeModel)) {
            init(DEFAULT_INITIAL_CAPACITY, contentSize);
        } else {
            init(DEFAULT_INITIAL_CAPACITY, 0);
        }

        WriteTaskFactory hashReadTaskFactory = new WriteTaskFactory(hashMap, true, times, writeModel);
        WriteTaskFactory concurrentHashReadTaskFactory = new WriteTaskFactory(concurrentHashMap, false, times, writeModel);

        long hashMapReadTime = runLoadTest(hashReadTaskFactory, threadNumber);
        long concurrentHashMapReadTime = runLoadTest(concurrentHashReadTaskFactory, threadNumber);

        System.out.println(generateResult("HashMap " + writeModel, hashMapReadTime, DEFAULT_INITIAL_CAPACITY, contentSize, threadNumber, times));
        System.out.println(generateResult("ConcurrentHashMap" + writeModel, concurrentHashMapReadTime, DEFAULT_INITIAL_CAPACITY, contentSize, threadNumber,
                times));
    }

    private String generateResult(String tag, long totalTimeCost, int mapSize, int contentSize, int threadNumber, int times) {
        String result = "[" + tag + "] with {map size : " + mapSize + "} {conte size : " + contentSize + "} {Thread : " + threadNumber + "}";
        double timeCost = (double) (totalTimeCost * UNIT) / (double) (times * threadNumber);
        result += "\n\r    Time cost : " + timeCost * 1000 + " ms.";
        return result;
    }

    private long runLoadTest(TaskFactory taskFactory, int threadNumber) {
        Collection<Future<?>> futures = new LinkedList<Future<?>>();

        List<TimeCostStatisticTask> tasks = new ArrayList<TimeCostStatisticTask>();
        while (threadNumber-- > 0) {
            TimeCostStatisticTask task = taskFactory.newInstance();
            tasks.add(task);
            futures.add(threadPoolService.submit(task));
        }
        waitTasksTerminate(futures);

        long timeCost = 0;
        for (TimeCostStatisticTask task : tasks) {
            timeCost += task.getTimeCost();
        }
        return timeCost;
    }

    private void waitTasksTerminate(Collection<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        threadPoolService.shutdown();
    }

}
