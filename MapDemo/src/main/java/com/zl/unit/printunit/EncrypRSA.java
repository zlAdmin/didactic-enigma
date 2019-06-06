package com.zl.unit.printunit;

import org.apache.tomcat.util.codec.binary.Base64;
import java.io.FileOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 加密解密算法
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-04-29 10:49
 * @Version: 1.0
 *           <p>
 *           Copyright: Copyright (zl) 2019
 *           </p>
 **/
public class EncrypRSA {

    private static final String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpAFY0T/4bLYM/8Ai82zEvmM5xO3+J9gouUy8JLv+6hqeZBB7J5ZXzhhAvvhxnorxTgnptCZS5+hABfMxrq9Kw3y4fVGYGw21LlrUovcS3g9wJ1Ywmu7KQsvXfRWyOXJTqJPjN+XFYy71dT606P6v7WVmvFKzGOONcEIUP4C/xJwIDAQAB";
    private static final String PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKkAVjRP/hstgz/wCLzbMS+YznE7f4n2Ci5TLwku/7qGp5kEHsnllfOGEC++HGeivFOCem0JlLn6EAF8zGur0rDfLh9UZgbDbUuWtSi9xLeD3AnVjCa7spCy9d9FbI5clOok+M35cVjLvV1PrTo/q/tZWa8UrMY441wQhQ/gL/EnAgMBAAECgYEAkPHI/XTd61FNkDi+Rbt4o+napSLyb9ClSrXtUWMN0VxLweDWxzIxOXtxxoC2u+vGuZjeh0YAWMEvmRb//BiRydeHng3uNLH0KKCfZDpUQKHnWubJqH54tpmXdqdNheAZ78i8KKH4wqzBfvEbO+oCDPKe0HwrH4+ln63d5gsqGoECQQDy69in7ORuHmDkjxK29AUEAkC0QFZXotmqyuMjkYKFr/itA0aYf4HV2VuUaqNAbxHeAoCqb0P981Gx6i5n4ZcFAkEAshmpK/GUc0qRsnoDQRMIrd6cGn+OejWDNZN7hLlkY9R5gkCnYMrnn4Si/FyLwDZ3tQBzArV5zQXVFYZ1bp0HOwJAQ2LxYwPwCiwbLMwToPToP0hwso/2Y7ElOJ+3irJexr8d+MCj4MHePnhhUzaRutoU1sVS8/SRo+zPiM8xuFd4EQJAFWMbe5lxGD82K9aXGXNtsWNwH4Z915MhhIeHZ7LTqWFUjh2xe+Ah3HgTEncSmSxxR50cMpEUZVhz9DfHVlcpDQJBAM/YgTG6Yos7ImZGFfjnkgB/VVAAhL8ip+E5JKz4+QRu0H4TCHLCFVwzjl6Es7JHTrj3ZOcEVWsN+SCcAR8kwu8=";

    /**
     * 加密
     *
     * @param publicKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        if (publicKey != null) {
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 解密
     *
     * @param privateKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (privateKey != null) {
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws Exception {
        EncrypRSA rsa = new EncrypRSA();
        String msg = "985f67e2-130f-48b6-a99e-744ad56d15d9";
        generateKey();
        // 用公钥加密

        RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(PRIVATEKEY);
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) getPublicKey(PUBLICKEY);
        byte[] srcBytes = msg.getBytes();
        byte[] resultBytes = rsa.encrypt((RSAPublicKey) getPublicKey(PUBLICKEY), srcBytes);
        // 用私钥解密
        byte[] decBytes = rsa.decrypt((RSAPrivateKey) getPrivateKey(PRIVATEKEY), resultBytes);
        System.out.println("明文是:" + msg);
        System.out.println("加密后是:" + new String(resultBytes));
        saveFile("f://key/sst.txt", new String(resultBytes));
        System.out.println("解密后是:" + new String(decBytes));

    }

    /**
     * 保存文件
     * @param path
     * @param key
     * @throws Exception
     */
    public static void saveFile(String path, String key) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(key.getBytes());
        fos.flush();
        fos.close();
    }

    /**
     * @Description 根据字符串生产公钥
     * @return java.security.PublicKey
     * @throws @Author zhanglei
     * @Date 14:25 2019/4/29
     * @Param [key]
     **/
    public static PublicKey getPublicKey(String publicKeys) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(publicKeys);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * String转私钥PrivateKey
     * @param
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKeys) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(privateKeys);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * Sring转byte数组
     */
    public static byte[] stringToByte(String msg) {
        byte[] msgByte = Base64.decodeBase64(msg);
        return msgByte;
    }

    /**
     * byte数组转字符串
     */
    public static String byteToString(byte[] bytes) {
        String msg = Base64.encodeBase64String(bytes);
        return msg;
    }

    /**
     * 生成公钥私钥
     */
    public static void generateKey() {
        try {
            // 基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            //初始化密钥对生成器，密钥大小为1024位
            keyPairGen.initialize(512);
            //生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            //得到私钥 RSAPrivateKey
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            //得到公钥 RSAPublicKey
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            System.out.println("私钥：" + Base64.encodeBase64String(privateKey.getEncoded()));
            System.out.println("公钥：" + Base64.encodeBase64String(publicKey.getEncoded()));
            /* saveFile("c://privateKey.txt", Base64.encodeBase64String(privateKey.getEncoded()));
            saveFile("c://publicKey.txt", Base64.encodeBase64String(publicKey.getEncoded())); */
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

