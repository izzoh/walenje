package com.izzoh.walenje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		((Button) findViewById(R.id.btnGo))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent i = new Intent(HomeActivity.this,
						LandingActivity.class);
						startActivity(i);
					}
				});
	}

}
