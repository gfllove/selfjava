package com.uss.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptoUtil {
	public static final String ALGORITHM_MD5 = "MD5";
	public static final String ALGORITHM_SHA1 = "SHA1";
	public static final String ALGORITHM_DES = "DES";

	public static final String ALGORITHM_DESEDE = "DESede";
	
	private static final Random random = new Random();

	private static final char constants[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' ,'/','+','='};
	
	public static final byte[] keyBytes = { 0x14, 0x21, 0x4F, 0x51, (byte) 0x81, 0x13,
			0x40, 0x32, 0x28, 0x25, 0x79, 0x41, (byte) 0xCB, (byte) 0xAD,
			0x57, 0x61, 0x37, 0x29, 0x74, (byte) 0x98, 0x32, 0x42, 0x46,
			(byte) 0xE2 }; // 24字节的密钥

	public static String hashMD5String(String dataToHash) {
		String tmp = null;
		try {
			// One-way hash
			MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
			md.update(dataToHash.getBytes());
			byte[] byteTmpe = md.digest();
			// base 64 encoding, using sun's internal lib
			BASE64Encoder b64encoder = new BASE64Encoder();
			tmp = b64encoder.encode(byteTmpe);
		} catch (NoSuchAlgorithmException e) {
		}
		return tmp;
	}

	public static String hashSHA1String(String dataToHash) {
		String tmp = null;
		try {
			// One-way hash
			MessageDigest md = MessageDigest.getInstance(ALGORITHM_SHA1);
			md.update(dataToHash.getBytes());
			byte[] byteTmpe = md.digest();
			// base 64 encoding, using sun's internal lib
			BASE64Encoder b64encoder = new BASE64Encoder();
			tmp = b64encoder.encode(byteTmpe);
		} catch (NoSuchAlgorithmException e) {
		}
		return tmp;
	}

	/**
	 * 对输入字符串进行加密
	 * @param keybyte 密钥
	 * @param src 被加密的字符
	 * @param algorithm 所使用的加密算法
	 * @return
	 */
	public static String encrypt(byte[] keybyte, String src, String algorithm) {
		String result = null;
		try {
			SecretKey deskey = new SecretKeySpec(keybyte, algorithm);
			Cipher cp = Cipher.getInstance(algorithm);
			cp.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] cipherByte = cp.doFinal(src.getBytes());
			BASE64Encoder b64encoder = new BASE64Encoder();
			result = b64encoder.encode(cipherByte);
		} catch (GeneralSecurityException e) {
			// slilenct and return null
			return null;
		}
		return result;
	}

	/**
	 * 对输入字符串进行解密
	 * @param keybyte
	 * @param obj
	 * @param algorithm
	 * @return
	 */
	public static String decrypt(byte[] keybyte, String obj, String algorithm) {
		String result = null;
		try {
			SecretKey deskey = new SecretKeySpec(keybyte, algorithm);
			Cipher cp = Cipher.getInstance(algorithm);
			cp.init(Cipher.DECRYPT_MODE, deskey);
			BASE64Decoder b64decoder = new BASE64Decoder();
			byte[] encodeByte = b64decoder.decodeBuffer(obj);
			result = new String(cp.doFinal(encodeByte));
		} catch (Exception e) {
			return null;
		}
		return result;
	}



	/**
	 * 采用Base64编码处理关键数据
	 * @param source
	 * @return
	 */
    public static String coreEncode(String source) {
		String result = null;
		if (source != null) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			int apendNum = (3 - source.length() % 3)%3;
			while (apendNum > 0) {
				source = source + " ";
				apendNum--;
			}
			result = base64Encoder.encode(source.getBytes());
		}
		return result;
	}

	/**
	 * 采用Base64解码处理关键数据
	 * @param object
	 * @return
	 */
    public static String coreDecode(String object) {
		String result = null;
		if (object != null) {
			try {
				BASE64Decoder base64Decoder = new BASE64Decoder();
				result = new String(base64Decoder.decodeBuffer(object));
				// trim result to discard space code
				result = result.trim();
			} catch (IOException e) {
				// to be silence
				return null;
			}
		}
		return result;
	}

	/**
	 * 采用DES算法加密关键数据
	 */
    public static String DESEncode(String src) {
		String result = null;
		if (src != null) {
			result = CryptoUtil.encrypt(CryptoUtil.keyBytes, src,
					CryptoUtil.ALGORITHM_DESEDE);
		}
		return result;
	}

	/**
	 * 采用DES算法解密关键数据
	 */
    public static String DESDecode(String object) {
		String result = null;
		if (object != null) {			
			result = CryptoUtil.decrypt(CryptoUtil.keyBytes, object,
					CryptoUtil.ALGORITHM_DESEDE);
		}
		return result;
	}

	/**
	 * 按照给定的长度，生成随机序列号，其中序列号由数字和字母组成
	 * @param length 给定的长度
	 * @return
	 */
    public static String compondNext(int length) {
		StringBuffer bs = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(constants.length);
			bs.append(constants[num]);
		}
		return bs.toString();
	}
    
    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++){
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
    
    public static String MD5(String str){
        try {
            if(null!=str){
                java.security.MessageDigest alg = java.security.MessageDigest.getInstance(ALGORITHM_MD5);
                alg.update(str.getBytes());  
                byte[] digesta=alg.digest();
                return byte2hex(digesta);
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }
    
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
        
	    String a= "net:train.17u.net";
        System.out.println(DESEncode(a));
        
        System.out.println(DESDecode(DESEncode(a)));
        
        System.out.println(MD5(a));
      
	}
}
