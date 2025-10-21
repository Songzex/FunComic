package com.scy.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
public class PasswordUtil {

    /**
     * 使用指定的哈希算法对密码进行加密
     *
     * @param password 原始密码
     * @param algorithm 哈希算法名称，例如 "MD5"、"SHA-256"等
     * @return 加密后的密码（十六进制字符串形式）
     */
    public static String encryptPassword(String password, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证输入的密码与已加密密码是否匹配（基于相同的算法再次加密后对比）
     *
     * @param inputPassword 输入的密码
     * @param storedPassword 存储的已加密密码
     * @param algorithm 哈希算法名称
     * @return true表示匹配成功，false表示匹配失败
     */
    public static boolean verifyPassword(String inputPassword, String storedPassword, String algorithm) {
        String encryptedInput = encryptPassword(inputPassword, algorithm);
        return encryptedInput!= null && encryptedInput.equals(storedPassword);
    }

    public static void main(String[] args) {
        // 示例用法，使用MD5算法（实际中不建议用MD5了，这里仅示例）
        String password = "123456";
        String encrypted = encryptPassword(password, "MD5");
        System.out.println("加密后的密码: " + encrypted);

        boolean result = verifyPassword(password, encrypted, "MD5");
        System.out.println("密码验证结果: " + result);
    }
}