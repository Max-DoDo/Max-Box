package Tools;

import java.io.*;
import java.util.Properties;

/**
 * <p>
 * 用于读写配置文件的类.
 *
 * @author Maxwell
 */
public class PropertiesReader {

    /**
     * 配置文件的路径
     */
    private final static String CONFIG_PATH = Constant.CONFIG_PATH;

    /**
     * <p>
     * 从config.properties配置文件中通过指定的键(key)读取值(value)
     *
     * @param key 要读取的值对应的键(key)
     * @return key在配置文件中对应的值
     */
    public static String get(String key) {

        Properties pps = new Properties();

        try {

            InputStream in = new BufferedInputStream(new FileInputStream(CONFIG_PATH));
            pps.load(in);
            return pps.getProperty(key);

        } catch (IOException e) {
            Tools.printErr("从配置文件中读取参数失败");
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 更新（或插入）一对properties信息(主键及其键值)
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     *
     * @param key   键名
     * @param value 键值
     */
    public static void set(String key, String value) {

        try {

            Properties props = new Properties();
            props.load(new FileInputStream(CONFIG_PATH));
            OutputStream fos = new FileOutputStream(CONFIG_PATH);

            props.setProperty(key, value);
            props.store(fos, "Config file");

        } catch (IOException e) {
            Tools.printErr("属性文件更新错误");
        }
    }


}
