/**
 * 
 */
package igeektang.com.hashmap.performance.common;

/**
 * @author Ares Tang
 *
 */
public interface StatisticableTask extends Runnable {
    void taskStart();
    void taskStop();

}
