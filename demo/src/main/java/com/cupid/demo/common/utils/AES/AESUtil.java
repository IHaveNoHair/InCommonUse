package com.cupid.demo.common.utils.AES;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    //实例化日志对象，引用jar包org.slf4j.Logger
    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);
    //算法
    private static final String ALGORITHMSTRs = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHMSTR = "AES/ECB/NoPadding";
    /**
     * AES加密
     * @param  content 待加密的内容
     * @param  encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception 抛出异常
     */
    public static String aesEncryptToBytes(String content, String encryptKey) throws Exception {
        byte[] raw = encryptKey.getBytes();  //获得密码的字节数组
        SecretKeySpec skey = new SecretKeySpec(raw, "AES"); //根据密码生成AES密钥
        Cipher cipher = Cipher.getInstance(ALGORITHMSTRs);  //根据指定算法ALGORITHM自成密码器
        cipher.init(Cipher.ENCRYPT_MODE, skey); //初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
        byte [] byte_content = content.getBytes("utf-8"); //获取加密内容的字节数组(设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
        byte [] encode_content = cipher.doFinal(byte_content); //密码器加密数据
        return Base64.encodeBase64String(encode_content); //将加密后的数据转换为字符串返回

    }
    /**
     * AES解密
     * @param data 待解密数据
     * @param key 解密秘钥
     * @return 解密后的string
     * @throws Exception 抛出异常
     */
    public static String decryption(String data,String key) throws Exception{
        try{
            byte[] encrypted=new BASE64Decoder().decodeBuffer(data);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            return new String(cipher.doFinal(encrypted),"UTF-8");
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
}
