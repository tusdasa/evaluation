package net.tusdasa.evaluation.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
