package com.sailing.yjydupload.util;

import org.springframework.util.StringUtils;

/**
 * 项目公共工具类
 * create by en
 * at 2018/12/12 16:44
 **/
public class CommonUtil {

    /**
     * 选择非空的字符（优先选择第一个，都为空抛出异常）
     * @param v1    字符串1
     * @param v2    字符串2
     * @return  非空字符串
     */
    public static String choiceNotNull(String v1, String v2) {
        if (!StringUtils.isEmpty(v1))
            return v1;
        else if (!StringUtils.isEmpty(v2))
            return v2;
        else
            throw new RuntimeException("【配置加载】配置出错，数据为空，请检查是否有未配置的选项！");
    }

}
