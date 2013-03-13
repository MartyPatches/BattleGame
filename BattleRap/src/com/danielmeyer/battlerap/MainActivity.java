package com.danielmeyer.battlerap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TableLayout tl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		

		DBAdapter db = new DBAdapter(this);
		db.open();
		if (!db.ContainsUsers()) {
			Intent myIntent = new Intent(this, CreateUserActivity.class);
			startActivity(myIntent);
		}else {
			Cursor u = db.GetUsers();
//			if (u.moveToFirst()) {
//				do {
//					DisplayUsers(u);
//				} while (u.moveToNext());
//			}
			if (u.moveToFirst()){
				tl = (TableLayout)findViewById(R.id.tblListUsers);
			do{
				//Create a new row to be added.
				TableRow tr = new TableRow(this);
				//Create text views to be added to the row.
				//TextView tv1 = new TextView(this);
				TextView tv2 = new TextView(this);
				//Put the data into the text view by passing it to a user defined function createView()
				//createView(tr, tv1, Integer.toString(i+1));
				CreateView(tr, tv2, u.getString(0));
				//Add the new row to our tableLayout tl
				tl.addView(tr);
				
			} while(u.moveToNext());
			
			TableRow trAddUser = new TableRow(this);
			TextView txtAddUser = new TextView(this);
			txtAddUser.setText("Ny användare");
			txtAddUser.setPadding(20, 0, 0, 0);
			
			trAddUser.setPadding(0, 1, 0, 1);
			txtAddUser.setTextSize(30);
			trAddUser.addView(txtAddUser);
			txtAddUser.setGravity(0x01);
//			TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
//					TableLayout.LayoutParams.MATCH_PARENT,
//					TableLayout.LayoutParams.WRAP_CONTENT);
//			txtAddUser.setLayoutParams(lp);
			txtAddUser.setOnClickListener(addUserHandler);
			tl.addView(trAddUser);
			}
		}
		
		//db.insertBattleRapper("Per", "Orten", 6, 5, 1, 10, 10, 2);
		//db.getSkills("Danne");
		//Cursor c = db.getAllRecords();
		Cursor c = db.getSkills("Henry Bowers");
		//Cursor c = db.getBrId("R-man");
		
		if (c.moveToFirst()) {
			do {
				//DisplaySkills(c);
			} while (c.moveToNext());
		}
		
		db.close();

	}
	
	
		
View.OnClickListener addUserHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			StartCreateUser();
			
		}
	};

	
	public void StartCreateUser(){
		Intent myIntent = new Intent(this, CreateUserActivity.class);
		startActivity(myIntent);
	}
	
	public void CreateView(TableRow tr, TextView t, String viewdata){
		t.setText(viewdata);
		//adjust the porperties of the textView
		//t.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		//t.setTextColor(Color.DKGRAY);
		//t.setBackgroundColor(Color.CYAN);
		t.setPadding(20, 0, 0, 0);
		tr.setPadding(0, 1, 0, 1);
		//tr.setBackgroundColor(Color.BLACK);
		t.setTextSize(40);
		tr.addView(t); // add TextView to row.
	}

	public void DisplayRecord(Cursor c) {
		Toast.makeText(
				this,
				"id: " + c.getString(0) + "\n" + "Namn: " + c.getString(1)
						+ "\n" + "Hemstad:  " + c.getString(2),
				Toast.LENGTH_SHORT).show();
	}
	
	public void DisplaySkills(Cursor c) {
		Toast.makeText(
				this,
				"id: " + c.getString(0) + "\n" + "Flow: " + c.getString(1)
						+ "\n" + "Delivery:  " + c.getString(2) + "\n" + "Confidence:  " + c.getString(3)
						+ "\n" + "Punchlines:  " + c.getString(4) + "\n" + "Humor:  " + c.getString(5) + "\n" + "Shape:  " + c.getString(6),
				Toast.LENGTH_LONG).show();
	}
	
	public void DisplayUsers(Cursor c) {
		Toast.makeText(
				this,
				"Namn: " + c.getString(0),
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
