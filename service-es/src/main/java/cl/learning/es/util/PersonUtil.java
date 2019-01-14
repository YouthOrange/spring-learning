package cl.learning.es.util;

/**
 * @Author : 常亮
 * @Date : 15:00 2019-01-09
 * @Description :
 */
public class PersonUtil {
    public static Long idBuilder() {
        Long time = System.currentTimeMillis();
        Integer id = time.hashCode();
        return id.longValue();
    }
}
