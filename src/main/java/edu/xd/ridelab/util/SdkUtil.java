package edu.xd.ridelab.util;

import java.util.UUID;

/**
 * @Author zjh
 * @Date 2019/05/07,09:38
 */
public class SdkUtil {

    public static String getSDK(Long productId) {
        String uuid = UUID.randomUUID().toString() + productId;

        return uuid;
    }
}
