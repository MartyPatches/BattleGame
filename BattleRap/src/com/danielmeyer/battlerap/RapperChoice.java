package com.danielmeyer.battlerap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RapperChoice extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rapper_choice);
		
		Button btnNext = (Button)findViewById(R.id.btnNext);
		Button btnPrev = (Button)findViewById(R.id.btnPrev);
		final TextView txtBattleRapper = (TextView)findViewById(R.id.battleName);
		
		btnNext.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtBattleRapper.setText("Bagarn");
				Intent myIntent = new Intent();
				myIntent.setAction(Intent.ACTION_VIEW);
				myIntent.setData(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
				startActivity(myIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rapper_choice, menu);
		return true;
	}

}
