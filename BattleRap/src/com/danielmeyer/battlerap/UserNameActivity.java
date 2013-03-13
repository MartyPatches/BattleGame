package com.danielmeyer.battlerap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UserNameActivity extends Activity {

	private TextView txtUsername, txtPassword, txtChosenRapper;
	private Button btnSaveUser;
	private int brId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_user_name);
		
		txtUsername = (TextView)findViewById(R.id.txtUsername);
		txtPassword = (TextView)findViewById(R.id.txtPassword);
		
		btnSaveUser = (Button)findViewById(R.id.btnSave);
		btnSaveUser.setOnClickListener(myhandler);
		
		//Receive putExtra from intent
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
		    return;
		    }
		
		// Get data via the key
		brId = extras.getInt("BR_ID");
		String brName = extras.getString("BR_NAME");
		
		txtChosenRapper = (TextView)findViewById(R.id.txtChosenRapper);
		txtChosenRapper.setText( "Vald rappare: " + brName);
		
		
		
	}
	
	View.OnClickListener myhandler = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			IntentMethod();
			
		}
	};
	
	public void IntentMethod(){
		
		DBAdapter db = new DBAdapter(this);
		db.open();
		db.CreateUser(txtUsername.getText().toString(), txtPassword.getText().toString(), brId);
		db.close();
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_name, menu);
		return true;
	}

}
