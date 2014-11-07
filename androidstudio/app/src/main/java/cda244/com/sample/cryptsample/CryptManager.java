package cda244.com.sample.cryptsample;

import android.os.Bundle;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;


public class CryptManager
{
	private static final String TRANSFORMATION = "AES/CBC/PKCS7Padding";
	private static final String SECRET_KEY="1234567890qwaszx";
	private static final byte[] SECRET_KEY_BYTE = SECRET_KEY.getBytes();
	private static final String KEY_ALGORITHM = "AES";

	private static final String BUNDLE_KEY_DATA="data";
	private static final String BUNDLE_KEY_IV="iv";


	public static Bundle encrypt(String orgStr) throws Exception
	{
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY_BYTE, KEY_ALGORITHM));
		byte[] encryptedByte = cipher.doFinal( orgStr.getBytes() );

		String encodedStr = Base64.encodeToString(encryptedByte, Base64.DEFAULT);
		String iv = Base64.encodeToString(cipher.getIV(), Base64.DEFAULT);

		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_KEY_DATA, encodedStr );
		bundle.putString(BUNDLE_KEY_IV, iv );

		return bundle;
	}


	public static String decrypt(Bundle encryptedData) throws Exception
	{
		String ivStr = encryptedData.getString(BUNDLE_KEY_IV);
		IvParameterSpec iv =
				new IvParameterSpec( Base64.decode(ivStr, Base64.DEFAULT) );

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY_BYTE, KEY_ALGORITHM), iv);

		String encodedStr = encryptedData.getString(BUNDLE_KEY_DATA);
		byte[] decodedByte = Base64.decode(encodedStr.getBytes(), Base64.DEFAULT);
		byte[] decryptedByte = cipher.doFinal(decodedByte);

		return new String(decryptedByte);
	}


}


