/**
 * 
 */
package igeektang.com.hashmap.performance;

import igeektang.com.hashmap.performance.common.InvalideParameters;
import igeektang.com.hashmap.performance.test.HashMapTest;
import igeektang.com.hashmap.performance.write.WriteModel;

/**
 * @author Ares Tang
 *
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        HashMapTest test = new HashMapTest();
        try {
            System.out.println("Test the Hash Map Size's impact for read performance.");
            test.readLoadTest(16, 1, 1, 10000000);
            test.readLoadTest(16, 10, 1, 10000000);
            test.readLoadTest(16, 100, 1, 10000000);
            test.readLoadTest(16, 1000, 1, 10000000);
            test.readLoadTest(16, 10000, 1, 10000000);
            test.readLoadTest(16, 100000, 1, 10000000);
            test.readLoadTest(16, 1000000, 1, 10000000);
            
            System.out.println("Test the access thread number's impact for read performance.");
            test.readLoadTest(16, 100000, 1, 10000000);
            test.readLoadTest(16, 100000, 2, 10000000);
            test.readLoadTest(16, 100000, 4, 10000000);
            test.readLoadTest(16, 100000, 8, 10000000);
            test.readLoadTest(16, 100000, 16, 10000000);
            test.readLoadTest(16, 100000, 32, 10000000);
            
            System.out.println("Test the access thread number's impact for wite (remove) performance.");
            test.writeLoadTest(1000000, 1, 100000, WriteModel.REMOVE);
            test.writeLoadTest(1000000, 2, 100000, WriteModel.REMOVE);
            test.writeLoadTest(1000000, 4, 100000, WriteModel.REMOVE);
            test.writeLoadTest(1000000, 8, 100000, WriteModel.REMOVE);
            test.writeLoadTest(1000000, 16, 100000, WriteModel.REMOVE);
            test.writeLoadTest(1000000, 32, 100000, WriteModel.REMOVE);
            
            System.out.println("Test the Hash Map Size's impact for wite (update) performance.");
            test.writeLoadTest(1, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(10, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(100, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(1000, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(10000, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(100000, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(1000000, 1, 100000, WriteModel.UPDATE);
            
            System.out.println("Test the access thread number's impact for wite (update) performance. 1000 size");
            test.writeLoadTest(1000, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(1000, 2, 100000, WriteModel.UPDATE);
            test.writeLoadTest(1000, 4, 100000, WriteModel.UPDATE);
            test.writeLoadTest(1000, 8, 100000, WriteModel.UPDATE);
            test.writeLoadTest(1000, 16, 100000, WriteModel.UPDATE);
            test.writeLoadTest(1000, 32, 100000, WriteModel.UPDATE);
            
            System.out.println("Test the access thread number's impact for wite (update) performance. 100000");
            test.writeLoadTest(100000, 1, 100000, WriteModel.UPDATE);
            test.writeLoadTest(100000, 2, 100000, WriteModel.UPDATE);
            test.writeLoadTest(100000, 4, 100000, WriteModel.UPDATE);
            test.writeLoadTest(100000, 8, 100000, WriteModel.UPDATE);
            test.writeLoadTest(100000, 16, 100000, WriteModel.UPDATE);
            test.writeLoadTest(100000, 32, 100000, WriteModel.UPDATE);
            
        } catch (InvalideParameters e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        test.stop();
    }

}
