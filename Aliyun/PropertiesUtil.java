package cn.guandoo.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.guandoo.ongea.web.PropertyContext;

public class PropertiesUtil {

	private static PropertiesUtil util = null;
	private static Map<String, Properties> props = null;

	private PropertiesUtil() {
	}

	public static PropertiesUtil getInstance() {
		if (util == null) {
			props = new HashMap<String, Properties>();
			util = new PropertiesUtil();
		}
		return util;
	}

	public Properties load(String name) {
		if (props.get(name) != null) {
			return props.get(name);
		} else {
			Properties prop = new Properties();
			try {
				prop.load(PropertiesUtil.class.getResourceAsStream("/" + name + ".properties"));
				props.put(name, prop);
				return prop;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 加载resources目录下面的settings.properties文件
	public static void setPropertyContext() {
		Properties prop = PropertiesUtil.getInstance().load("settings");
		PropertyContext.getInstance().setAppId(prop.getProperty("app_id"));
		PropertyContext.getInstance().setAppSecret(prop.getProperty("appsecret"));
		PropertyContext.getInstance().setOriginUrl(prop.getProperty("origin_url"));
		PropertyContext.getInstance().setToken(prop.getProperty("token"));
		PropertyContext.getInstance().setImgUrl(prop.getProperty("img_url"));
		PropertyContext.getInstance().setPageSize(Integer.parseInt(prop.getProperty("pageSize")));
	}
}