package com.jerry.sell.util;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/20
 * Time: 14:06
 * Description:
 */
public class KeyUtil {

    /**
     * 生成随机唯一主键
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        // 生成时间毫秒数 + 6位随机数
        return System.currentTimeMillis() + String.valueOf(random.nextInt(900000) + 100000);
    }

}
