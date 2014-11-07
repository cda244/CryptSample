package cda244.com.sample.cryptsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MyActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);

		String orgTxt = "元のテキスト";
		Bundle bundle = null;
		try {
			bundle = CryptManager.encrypt(orgTxt);
		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			Log.d("CryptSample", "Decrypt後 "+CryptManager.decrypt(bundle));
			Log.d("CryptSample", "Decrypt前 "+orgTxt);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
