package net.tusdasa.evaluation.utils;

import java.util.UUID;

/**
 * UUID 工具类
 *
 * @author tusdasa
 * @version 1.0
 */

public class UUIDUtils {

    /**
     * 生成UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
