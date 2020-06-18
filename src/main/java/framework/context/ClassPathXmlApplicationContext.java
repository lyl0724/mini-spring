package framework.context;

import framework.beans.config.XmlBeanDefinitionsReader;
import framework.beans.factory.AbstractBeanFactory;
import framework.beans.factory.AutowireCapableBeanFactory;
import framework.core.io.ResourceLoader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(new AutowireCapableBeanFactory(), configLocation);
    }

    public ClassPathXmlApplicationContext(AbstractBeanFactory beanFactory, String configLocation) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionsReader xmlBeanDefinitionsReader = new XmlBeanDefinitionsReader(new ResourceLoader());
        xmlBeanDefinitionsReader.loadBeanDefinitions(configLocation);
        for (String beanName : xmlBeanDefinitionsReader.getBeanDefinitionMap().keySet()) {
            beanFactory.registerBeanDefinition(beanName, xmlBeanDefinitionsReader.getBeanDefinitionMap().get(beanName));
        }
    }
}
