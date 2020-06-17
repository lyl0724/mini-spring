package framework.beans.config;

import framework.beans.BeanDefinition;
import framework.beans.PropertyValue;
import framework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 *  解析XML资源并将配置信息存入BeanDefinitions
 */
public class XmlBeanDefinitionsReader extends AbstractBeanDefinitionsReader {

    public XmlBeanDefinitionsReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        //读取指定路径的资源
        InputStream inputStream = this.getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        //根据xml的输入流，得到DOM文档
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        //解析DOM文档，获取BeanDefinition
        registerBeanDefinitions(document);
        inputStream.close();
    }

    public void registerBeanDefinitions(Document document) throws ClassNotFoundException {
        Element root = document.getDocumentElement();
        //从根节点开始解析，对应beans标签
        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) throws ClassNotFoundException {
        NodeList nodeList = root.getChildNodes();
        //扫描一个个bean标签，并解析
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                processBeanDefinition(element);
            }
        }
    }

    protected void processBeanDefinition(Element element) throws ClassNotFoundException {
        //bean标签中包含的属性
        String name = element.getAttribute("name");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        //将这个bean标签中properties给注入beanDefinition中
        processProperty(element, beanDefinition);
        this.getBeanDefinitionMap().put(name, beanDefinition);
    }

    private void processProperty(Element element, BeanDefinition beanDefinition) {
        NodeList nodeList = element.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element propertyElement = (Element) node;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                beanDefinition.getPropertyValues().addProperty(new PropertyValue(name, value));
            }
        }
    }
}
