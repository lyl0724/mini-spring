package framework.beans.config;

import framework.beans.BeanDefinition;
import framework.core.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 *  由于资源中可能包含多个Bean的配置信息，因此我们解析配置生成的BeanDefinition可能有多个
 *  这里使用一个Map<String, BeanDefinition>来存储这些解析后生成的BeanDefinition
 *  这里不对具体的解析过程进行实现，只是定义了BeanDefinition要如何存储
 */
public abstract class AbstractBeanDefinitionsReader implements BeanDefinitionsReader {
    private Map<String, BeanDefinition> beanDefinitionRegistry;

    private final ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionsReader(ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    /**
     * @return  返回资源加载器，方便其子类读取配置的时候使用
     */
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public Map<String, BeanDefinition> getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }
}
