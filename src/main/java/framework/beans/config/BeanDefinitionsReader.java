package framework.beans.config;

import java.io.IOException;

/**
 *  解析BeanDefinitions的接口
 *  通过加载指定位置的资源(配置文件，例如xml文件)，将资源中bean的信息给注入BeanDefinition中，以便后于创建bean对象
 */
public interface BeanDefinitionsReader {
    void loadBeanDefinitions(String location) throws IOException, Exception;
}
