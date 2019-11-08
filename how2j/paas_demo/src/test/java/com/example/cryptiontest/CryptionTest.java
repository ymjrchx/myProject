package com.example.cryptiontest;

import net.dgg.crypto.constant.DggHmacEnum;
import net.dgg.crypto.constant.DggShaEnum;
import net.dgg.crypto.digest.*;
import net.dgg.crypto.dto.DggRsaKeys;
import org.junit.Test;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/23 0023
 * @Description:
 */
public class CryptionTest {

    @Test
    public void base64Test(){
        String base64EncodeResult = DggBase64Digest.encoder("hello");
        System.out.println(base64EncodeResult);
        System.out.println(DggBase64Digest.decoder(base64EncodeResult));
    }

    @Test
    public void MD5Test(){
        System.out.println(DggMD5Digest.digest("hello", true));
        System.out.println(DggMD5Digest.digest("hello", false));
    }

    @Test
    public void hmacTest(){
        String hmacKey = DggHamcDigest.getHmacKey(DggHmacEnum.HMAC_SHA_1, false);
        System.out.println(hmacKey);
        System.out.println(DggHamcDigest.digest("hello", hmacKey, DggHmacEnum.HMAC_MD5, false));
    }

    @Test
    public void aesTest(){
        String encryptionResult = DggAESDigest.encrypt("hello", "123");
        System.out.println(encryptionResult);
        System.out.println(DggAESDigest.decrypt(encryptionResult, "123"));
    }


    @Test
    public void shaTest(){
        System.out.println(DggSHADigest.digest("hello", DggShaEnum.SHA512, false));
    }

    @Test
    public void rsaTest(){
        DggRsaKeys rsaKeys = DggRSADigest.initKey(512);
        System.out.println("公钥为: " + rsaKeys.getPublicKey());
        System.out.println("私钥为: " + rsaKeys.getPrivateKey());
        System.out.println("*****************************************************");

        String publicEncryptionResult = DggRSADigest.encryptByPublicKey("hello", rsaKeys.getPublicKey());
        System.out.println("公钥加密结果: " + publicEncryptionResult);
        String privateDecryptionResult = DggRSADigest.decryptByPrivateKey(publicEncryptionResult, rsaKeys.getPrivateKey());
        System.out.println("私钥解密结果: " + privateDecryptionResult);
        System.out.println("*****************************************************");

        String privateEncryptionResult = DggRSADigest.encryptByPrivateKey("hello", rsaKeys.getPrivateKey());
        System.out.println("私钥加密结果: " + privateEncryptionResult);
        String publicDecryptionResult = DggRSADigest.decryptByPublicKey(privateEncryptionResult, rsaKeys.getPublicKey());
        System.out.println("公钥解密结果: " + publicDecryptionResult);

    }

}
