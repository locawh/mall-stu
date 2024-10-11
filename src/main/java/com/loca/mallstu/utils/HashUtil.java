package com.loca.mallstu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Description 生成唯一hash值
 * @ClassName HashUtilT
 * @Author locawong
 * @Date 2024/10/11 10:36
 */
public class HashUtil {
    private static final Logger logger = LoggerFactory.getLogger(HashUtil.class);

    private HashUtil() {
        // empty constructor
    }

    public static String generateUniqueHash() {
        try {
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
            return bytesToHex(salt.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("HashUtil.generateUniqueHash() has thrown an error: ", e);
        }
        return null;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            buf[index++] = hex[b >>> 4 & 0xf];
            buf[index++] = hex[b & 0xf];
        }
        return new String(buf);
    }
}
