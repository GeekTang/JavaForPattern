/**
 * 
 */
package igeektang.com.hashmap.performance.common;


/**
 * @author Ares Tang
 * 
 */
public abstract class TimeCostStatisticTask implements StatisticableTask {

    private volatile  long startTime;

    private volatile  long stopTime;

    abstract protected void execute();

    public void run() {
        taskStart();
        execute();
        taskStop();
    }

    public void taskStart() {
        startTime = System.currentTimeMillis();
    }


    public void taskStop() {
        stopTime = System.currentTimeMillis();
    }

    public long getTimeCost() {
        return stopTime - startTime;
    }

}
