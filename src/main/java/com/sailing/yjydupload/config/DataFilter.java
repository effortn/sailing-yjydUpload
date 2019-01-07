package com.sailing.yjydupload.config;

import com.sailing.yjydupload.entity.DeviceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据过滤
 * create by en
 * at 2018/12/26 13:57
 **/
@Slf4j
@Component
public class DataFilter {

    @Autowired
    private UploadInfoConfig uploadInfoConfig;

    // 用于过滤数据的集合，key：实体的Method，value：筛选条件
    private static Map<Method, String> FILTER_MAP = new ConcurrentHashMap<>();

    /**
     * 初始化 FILTER_MAP
     */
    @PostConstruct
    private void init() {
        log.info("【数据过滤】配置初始化，若有错误，不会终止，会跳过错误的过滤!");
        // 1. 获取相关筛选的配置信息
        Map<String, String> filter = uploadInfoConfig.getFilter();
        if (filter != null && filter.size() != 0) {
            // 2. 数据不为空，遍历查找配置的相关属性对应的实体类 DeviceInfo 中的 get方法，放入集合中
            Class deviceInfoClass = DeviceInfo.class;
            for (String key : filter.keySet()) {
                log.info("【数据过滤】初始化配置属性：{}", key);
                String methodName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
                try {
                    Method method = deviceInfoClass.getMethod(methodName, (Class<?>[]) null);
                    log.info("【数据过滤】获取方法：{}，筛选条件：{}", methodName, filter.get(key));
                    FILTER_MAP.put(method, filter.get(key));
                } catch (NoSuchMethodException e) {
                    log.error("【数据过滤】配置错误，没有属性：{}，请检查配置!", key, e);
                    continue;
                }
            }
        }
    }

    /**
     * 筛选数据
     * @param deviceInfo    需要筛选的设备数据
     * @return  Boolean  符合条件为true 否则为false
     */
    public synchronized boolean filter(DeviceInfo deviceInfo) {
        if (FILTER_MAP == null || FILTER_MAP.size() == 0) {
            return true;
        }
        boolean result = true;
        for (Method method : FILTER_MAP.keySet()) {
            try {
                Object value = method.invoke(deviceInfo);
                String s = String.valueOf(value);
                String filterString = FILTER_MAP.get(method);
                if (!filterString.contains(s)) {
                    result = false;
                    break;
                }
            } catch (Exception e) {
                log.error("【数据过滤】失败！", e);
                result = false;
                break;
            }
        }
        return result;
    }

}
