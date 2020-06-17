package framework.core.io;

import java.net.URL;

/**
 *  加载指定路径的资源，是获取Resource的途径
 */
public class ResourceLoader {

    /**
     * @param location  资源的类路径
     * @return  Resource资源，后续我们可以直接通过resource.getInputStream()方法来获取它的输入流
     */
    public Resource getResource(String location) {
        URL urlResource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(urlResource);
    }
}
