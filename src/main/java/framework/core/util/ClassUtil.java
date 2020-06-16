package framework.core.util;

/**
 *  用于处理类的一些方法
 */
public class ClassUtil {

    /**
     * 根据类名，获得Class对象
     * @param className 类的全限定名
     */
    public static Class<?> loadClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
