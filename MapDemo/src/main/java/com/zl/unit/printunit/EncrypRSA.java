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
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public class EncrypRSA {

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDiMPz4qbXd9we8q4gnaGg3NaeH1Vbj9JA7odkBDDKCX5RoJotSPLX5n/6dWh3imUVmc6HNMo8oBOhki/W7+1KMyOUqI+h/uQDa35EvgrUSPwyc0QZszCyakk0uXtGqLdQz/IgrsywXfIzNL5sQ2679xinch4efgaikApmXYhSDywIDAQAB";
    private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOIw/Piptd33B7yriCdoaDc1p4fVVuP0kDuh2QEMMoJflGgmi1I8tfmf/p1aHeKZRWZzoc0yjygE6GSL9bv7UozI5Soj6H+5ANrfkS+CtRI/DJzRBmzMLJqSTS5e0aot1DP8iCuzLBd8jM0vmxDbrv3GKdyHh5+BqKQCmZdiFIPLAgMBAAECgYEAqTkrWcJmbRzu7emLIKiNJ5j9sLMcockLy4Fnv8/nTgDCIDWOEEWZg5t+uyx7pVc0Q9UI3WMRFUiusOLBQxVhCMhbwzOe+aD+xxXkA5gcQz3lURHkXcCcrSVfOyN9GK2pQg1clmYBwYjNacp4X8UOwQWGhcK1XSAsokGrhHGcwrkCQQD/zImXC1ONUiljf59xjCAbO/G42nbMdaSJIPcGDI9CY4XD135IsNsmPPhWxu4HiJ5/fuh8Z6kapBRJvDldbKeNAkEA4l5+gDpOiAsGO0CVDXgk6kT9yADbTuxOpPM4FEWfsMUf0h8vYO0B+3VPrQ7/mrV5WrrfBufN5hukXCHlDZU2twJAIBVrfIJzLFqNzmkHepp0vHW8T88271YiGQEFesDAhzcsY+/3au6jzhv/mgLBgDhmiN9GEbR+xVSnJshw+YLTUQJBAJQ4i1wq0YECtvHVN8O6B3Hd+s4awX7L/DLFjtK3Q/jbGhrbkIpGpiWgiqsmRvdmHC/sbFx5K7igIN6y0ugx68ECQE7sFCZ+9va95ZMnYdhDvHypZmOfZVZIALT5CfFx1H8y5Qyc0jR6vRKHSv96kVOHlg3XihbIZryD7zqm37jnb6I=";
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
    protected byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        if (publicKey != null) {
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始
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
    protected byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (privateKey != null) {
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
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
        String msg = "郭XX-精品相声";
       /* //KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，密钥大小为1024位
        keyPairGen.initialize(1024);
        //生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();*/
        //用公钥加密

        RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey();
        //得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) getPublicKey();
        byte[] srcBytes = msg.getBytes();
        byte[] resultBytes = rsa.encrypt((RSAPublicKey) getPublicKey(), srcBytes);
        //用私钥解密
        byte[] decBytes = rsa.decrypt((RSAPrivateKey) getPrivateKey(), resultBytes);
        System.out.println("明文是:" + msg);
        System.out.println("加密后是:" + new String(resultBytes));
        saveFile("f://key/ss.txt",new String(resultBytes));
        System.out.println("解密后是:" + new String(decBytes));

    }

    public static void saveFile(String path,String key)throws Exception{
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(key.getBytes());
        fos.flush();
        fos.close();
    }


    /**
     * @Description 根据字符串生产公钥
     * @return java.security.PublicKey
     * @throws 
     * @Author zhanglei
     * @Date 14:25 2019/4/29
     * @Param [key]
     **/
    public static PublicKey getPublicKey() throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(publicKey);
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
    public static PrivateKey getPrivateKey() throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

}

