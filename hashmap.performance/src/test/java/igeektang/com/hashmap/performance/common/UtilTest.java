/**
 * 
 */
package igeektang.com.hashmap.performance.common;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author eweitan
 *
 */
public class UtilTest extends TestCase {

    /**
     * Test method for {@link igeektang.com.hashmap.performance.common.Util#generateData(java.util.Map, int)}.
     */
    public void testGenerateData() {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Util.generateData(map, 100);
        Assert.assertEquals(100, map.size());
    }

}
