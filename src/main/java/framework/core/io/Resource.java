package framework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *  框架内部用于表示和定位资源的接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
