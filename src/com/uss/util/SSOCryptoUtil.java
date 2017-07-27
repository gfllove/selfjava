package com.uss.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;




import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @version 1.000, 2008-10-31
 * 
 */
public class SSOCryptoUtil {
	private final static String START_IDENTIFER = "Jvq2Dyzi8ElKfx7m";

	private final static String END_IDENTIFER = "Aq0bWnx1HvgTsPRc";

	private static final Random random = new Random();

	private static final char constants[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };

	/**
	 * 对字符窜数据编码
	 * 
	 * @param painStr
	 * @return
	 */
	public static String encode(String plainStr) {
		if (plainStr != null) {
			StringBuffer result = new StringBuffer(100);
			int beginRandomNum = random.nextInt(32);
			int endRandomNum = 32 - beginRandomNum;
			String startRandomStr = compondNext(beginRandomNum);
			String endRandomoStr = compondNext(endRandomNum);			
			String coreStr = DESEncode(plainStr);
			result.append(startRandomStr);
			result.append(START_IDENTIFER);
			result.append(coreStr);
			result.append(END_IDENTIFER);
			result.append(endRandomoStr);
			return result.toString();
		} else {
			return null;
		}
	}

	/**
	 * 对字符窜数据进行解码
	 * 
	 * @param code
	 * @return
	 */
	public static String decode(String code) {
		String result = null;
		if (code != null) {
			int start = code.indexOf(START_IDENTIFER);
			int end = code.indexOf(END_IDENTIFER);
			if (start != -1 && end != -1) {
				code = code.substring(start + START_IDENTIFER.length(), end);
				result = DESDecode(code);
				return result;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 采用Base64编码处理关键数据
	 * 
	 * @param source
	 * @return
	 */
	private static String coreEncode(String source) {
		String result = null;
		if (source != null) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			int apendNum = (3 - source.length() % 3)%3;
			// add space code to replace pad code '='
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
	 * 
	 * @param object
	 * @return
	 */
	private static String coreDecode(String object) {
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
	private static String DESEncode(String src) {
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
	private static String DESDecode(String object) {
		String result = null;
		if (object != null) {			
			result = CryptoUtil.decrypt(CryptoUtil.keyBytes, object,
					CryptoUtil.ALGORITHM_DESEDE);
		}
		return result;
	}

	/**
	 * 按照给定的长度，生成随机序列号，其中序列号由数字和字母组成
	 * 
	 * @param length
	 *            给定的长度
	 * @return
	 */
	private static String compondNext(int length) {
		StringBuffer bs = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(62);
			bs.append(constants[num]);
		}
		return bs.toString();
	}

	public static void main(String[] args) {
		String sample_pid = "0709050001";
		String encode_result = SSOCryptoUtil.encode(sample_pid);
		String decode_result = SSOCryptoUtil.decode(encode_result);
		//System.out.println("the sample plain string: " + sample_pid);
		//System.out.println("the sample encode string:" + encode_result);
		//System.out.println("the sample decode string:" + decode_result);
		try {
			String enURL = "eyJkaXN0aW5jdF9pZCI6IjE1Y2UzNjExNjcwNGY4LTBlOWM1ZDJmMDUyYjE3LTZhMTExNTdhLTEzMjcxMDQtMTVjZTM2MTE2NzE3OGQiLCJsaWIiOnsiJGxpYiI6ImpzIiwiJGxpYl9tZXRob2QiOiJjb2RlIiwiJGxpYl92ZXJzaW9uIjoiMS43LjgifSwicHJvcGVydGllcyI6eyIkc2NyZWVuX2hlaWdodCI6ODY0LCIkc2NyZWVuX3dpZHRoIjoxNTM2LCIkbGliIjoianMiLCIkbGliX3ZlcnNpb24iOiIxLjcuOCIsIiRsYXRlc3RfcmVmZXJyZXIiOiIiLCIkbGF0ZXN0X3JlZmVycmVyX2hvc3QiOiIiLCJHZkNsaWNrZWdnc19vcmlnaW4iOiJodHRwczovL2tleS5nZi5jb20uY24vIiwiR2ZDbGlja2VnZ3NfaGFzaCI6Ii9tYWluL29yZGVyIiwiR2ZDbGlja2VnZ3NfdGVybWluYWwiOiJtYW5hZ2VyX3dlYiIsIkdmQ2xpY2tlZ2dzX3NvY2tldEFkZHJlc3MiOiIiLCJHZkNsaWNrZWdnc19zdGF0dXNDb2RlIjoiMjAwIiwiR2ZDbGlja2VnZ3Nfc3RhdHVzVGV4dCI6Ik9LIiwiR2ZDbGlja2VnZ3NfdGltZURpZmYiOiIzNSIsIkdmQ2xpY2tlZ2dzX21ldGhvZCI6InB1c2hBY2siLCIkaXNfZmlyc3RfZGF5IjpmYWxzZX0sInR5cGUiOiJ0cmFjayIsImV2ZW50IjoiR2ZDbGlja2VnZ3NfU29ja2V0IiwiX25vY2FjaGUiOiIwODYyMzcxOTE4NzMyODAifQ==";
			String deURL =  java.net.URLEncoder.encode(enURL,"UTF-8");
			//System.out.println(deURL);
			
			String decode_data = SSOCryptoUtil.decode(deURL);
			
			//System.out.println("the sample decode_data string:" + decode_data);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
		
		
		//毫秒转时间
		long millisecond = 1500545416641l;
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss SSS a");
        System.out.println("毫秒对应日期时间字符串：" + format.format(date));
        
        System.out.println("毫秒对应日期时间字符串：" + format.format(new Date(1500545416641l)));
        System.out.println("毫秒对应日期时间字符串：" + format.format(new Date(1500545416564l)));
        
        
	}
}
