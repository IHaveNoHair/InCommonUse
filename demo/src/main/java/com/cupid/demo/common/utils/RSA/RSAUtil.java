package com.cupid.demo.common.utils.RSA;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.NullArgumentException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密
 * @author cupid
 * @date 2020/04/21
 */
public  class RSAUtil {
   public static Map<Integer,String> genKeyPair() throws NullArgumentException{
       Map<Integer,String> keyMap=new HashMap<>();
       try{
           //初始化秘钥生成器生成秘钥
           KeyPairGenerator KeyPairGen=KeyPairGenerator.getInstance("RSA");
           KeyPairGen.initialize(1024,new SecureRandom());
           KeyPair keyPair=KeyPairGen.generateKeyPair();
           //生成公私钥
           RSAPublicKey publicKey=(RSAPublicKey)keyPair.getPublic();
           RSAPrivateKey privateKey=(RSAPrivateKey)keyPair.getPrivate();
           String publicKeyString=encryptBASE64(publicKey.getEncoded());
           String privateKeyString=encryptBASE64(privateKey.getEncoded());
           keyMap.put(0,privateKeyString);
           keyMap.put(1,publicKeyString);
           return keyMap;
       }catch (Exception ex){
           return null;
       }
   }
    //解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
   /*
   * 私钥加密
   * data 待加密串
   * key 私钥
   * return 返回加密好的字符串
   * */
   public static String encryptByPrivate(String data,String key) throws Exception{
       if(data==null) {
           return null;
       }
       //取得私钥
       byte[] encrypted = new BASE64Decoder().decodeBuffer(key);
       PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(encrypted);
       KeyFactory keyFactory = KeyFactory.getInstance("RSA");
       //生成私钥
       PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
       //数据加密
       Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
       cipher.init(Cipher.ENCRYPT_MODE, privateKey);
       //对数据进行分段
       byte []  dataReturn=null;
       for (int i = 0; i < data.getBytes("utf-8").length; i += 64) {
           byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data.getBytes("utf-8"), i,
                   i + 64));
           dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
       }
       return encryptBASE64(dataReturn);
   }
    /**
     * 公钥加密
     * data 待加密串
     * key 公钥
     * return 返回加密好的字符串
     * */
    public static String encryptByPublic(String data,String key) throws Exception{
        //取得公钥
        byte[] encrypted = new BASE64Decoder().decodeBuffer(key);
        X509EncodedKeySpec X509KeySpec = new X509EncodedKeySpec(encrypted);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //生成私钥
        PublicKey publicKey = keyFactory.generatePublic(X509KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //对数据进行分段
        byte []  dataReturn=null;
        for (int i = 0; i < data.getBytes("utf-8").length; i += 64) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data.getBytes("utf-8"), i,
                    i + 64));
            dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
        }
        return encryptBASE64(dataReturn);
    }
    /**
     * 私钥解密
     * data 待解密串
     * key 私钥
     * return 返回解密好的字符串
     * */
    public static String decryptByPrivate(String data,String key) throws Exception{
        //取得私钥
        byte[] encrypted = decryptBASE64(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(encrypted);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //对数据进行分段
        String dataReturn=null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decryptBASE64(data).length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(decryptBASE64(data), i, i + 128));
            sb.append(new String(doFinal));
        }
        dataReturn = sb.toString();
        return dataReturn;
    }
    /**
     * 公钥加密
     * data 待加密串
     * key 公钥
     * return 返回加密好的字符串
     * */
    public static String decryptByPublic(String data,String key) throws Exception{
        byte[] encrypted=decryptBASE64(key);
        //取得公钥
        X509EncodedKeySpec X509KeySpec = new X509EncodedKeySpec(encrypted);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //生成私钥
        PublicKey publicKey = keyFactory.generatePublic(X509KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        //对数据进行分段
        String dataReturn=null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decryptBASE64(data).length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(decryptBASE64(data), i, i + 128));
            sb.append(new String(doFinal));
        }
        dataReturn = sb.toString();
        return dataReturn;
    }
}