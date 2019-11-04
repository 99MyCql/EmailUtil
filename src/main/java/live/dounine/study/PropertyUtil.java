package live.dounine.study;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 * @project EmailUtil
 * @class PropertyUtil
 * @author douNine
 * @date 2019/11/3 10:51
 * @description 配置文件管理工具类
 */
public class PropertyUtil {
    private static PropertyUtil singleton = null; // 全局静态单例
    private Properties properties; // 配置文件对象

    /**
     * 获取单例对象
     * @return PropertyManager对象
     * @throws IOException
     */
    public static PropertyUtil getSingleton() throws IOException {
        if (singleton == null) {
            return singleton = new PropertyUtil();
        }
        else {
            return singleton;
        }
    }

    /**
     * 单例模式下的私有构造函数
     * @throws IOException
     */
    private PropertyUtil() throws IOException {
        InputStream inputStream = EmailUtil.class.getClassLoader().getResourceAsStream("config.properties");
        properties = new Properties();
        properties.load(inputStream);
    }

    /**
     * 获取配置文件中，key对应的value值
     * @param key String
     * @return value字符串
     */
    public String getValue(String key) {
        return properties.getProperty(key, "");
    }
}
