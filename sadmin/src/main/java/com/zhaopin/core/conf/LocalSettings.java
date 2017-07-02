package com.zhaopin.core.conf;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhou.hao on 2017/7/2.
 */
public class LocalSettings {
    public static int lack;
    public static int enough;
    static {
        InputStream is = LocalSettings.class.getClassLoader().getResourceAsStream("config.properties");
        Properties props = new Properties();
        try {
            props.load(is);
            lack = Integer.parseInt(props.getProperty("lack"));
            enough = Integer.parseInt(props.getProperty("enough"));
        }catch (Exception e){
            lack = 5;
            enough = 10;
            e.printStackTrace();
        }
    }
}
