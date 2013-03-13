package com.danielmeyer.battlerap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.widget.TextView;
import android.graphics.Color;



public class CreateUserActivity extends Activity implements OnGestureListener{
	
	private LinearLayout main;    
    private TextView viewA;
    private Cursor rapCursor, skillCursor;
    private GestureDetector gestureScanner;
    private TextView brName ,ontopText, skill1, 
    skill2, skill3, skill4, skill5, skill6,
    skill1val, skill2val, skill3val, skill4val,
    skill5val, skill6val;
    private ImageView image;
    private Button btnChoice;
    private int currentRapperId;
    private String currentRapper;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gestureScanner = new GestureDetector(this, this);
        
        main = new LinearLayout(this);
        main.setBackgroundColor(Color.GRAY);
        main.setLayoutParams(new LinearLayout.LayoutParams(320,480));
          
        viewA = new TextView(this);
        viewA.setBackgroundColor(Color.YELLOW);
        viewA.setTextColor(Color.BLACK);
        viewA.setTextSize(16);
        viewA.setLayoutParams(new LinearLayout.LayoutParams(320,80));
        main.addView(viewA);
        
        DBAdapter db = new DBAdapter(this);
        db.open();
        rapCursor = db.GetAllRappers();
        //skillCursor = db.GetAllSkills();
        rapCursor.moveToFirst();
        //skillCursor.moveToFirst();
        db.close();
        DBAdapter db2 = new DBAdapter(this);
        db2.open();
        skillCursor = db2.GetAllSkills();
        //rapCursor.moveToFirst();
        skillCursor.moveToFirst();
        db2.close();
        
          
        setContentView(main);
		setContentView(R.layout.activity_create_user);
		brName = (TextView)findViewById(R.id.txtBrName);
		brName.setText(rapCursor.getString(1));
		
		btnChoice = (Button)findViewById(R.id.btnBrChoice);
		btnChoice.setOnClickListener(myhandler);
		
		currentRapper = rapCursor.getString(1);
		currentRapperId = rapCursor.getInt(0);
		
		
		image = (ImageView) findViewById(R.id.imageView1);
		image.setImageResource(R.drawable.br1);
		
		//Skilltextboxes
		//Headlines
		skill1 = (TextView)findViewById(R.id.txtSkill1);
		skill1.setText("Flow: ");
		skill2 = (TextView)findViewById(R.id.txtSkill2);
		skill2.setText("Delivery: ");
		skill3 = (TextView)findViewById(R.id.txtSkill3);
		skill3.setText("Självförtroende: ");
		skill4 = (TextView)findViewById(R.id.txtSkill4);
		skill4.setText("Punchlines: ");
		skill5 = (TextView)findViewById(R.id.txtSkill5);
		skill5.setText("Humor: ");
		skill6 = (TextView)findViewById(R.id.txtSkill6);
		skill6.setText("Form: ");
		
		//skill1val.setText("Hej " + skillCursor.getCount());
		
		skill1val = (TextView)findViewById(R.id.txtSkill1Value);
		skill1val.setText(skillCursor.getString(1));
		skill2val = (TextView)findViewById(R.id.txtSkill2Value);
		skill2val.setText(skillCursor.getString(2));
		skill3val = (TextView)findViewById(R.id.txtSkill3Value);
		skill3val.setText(skillCursor.getString(3));
		skill4val = (TextView)findViewById(R.id.txtSkill4Value);
		skill4val.setText(skillCursor.getString(4));
		skill5val = (TextView)findViewById(R.id.txtSkill5Value);
		skill5val.setText(skillCursor.getString(5));
		skill6val = (TextView)findViewById(R.id.txtSkill6Value);
		skill6val.setText(skillCursor.getString(6));
		
		
		
		
	}
	
	View.OnClickListener myhandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			IntentOnclick();
			
		}
	};

	public void IntentOnclick(){
		Intent intent = new Intent(this, UserNameActivity.class);
		intent.putExtra("BR_ID", currentRapperId);
		intent.putExtra("BR_NAME", currentRapper);
		startActivity(intent); 
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent me) {
        return gestureScanner.onTouchEvent(me);
    }
   
    @Override
    public boolean onDown(MotionEvent e) {
        viewA.setText("-" + "DOWN" + "-");
        return true;
    }
   
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    	 TextView brName = (TextView)findViewById(R.id.txtBrName);
    	if(velocityX < 0){
    		if(!rapCursor.isLast()){
    		rapCursor.moveToNext();
    		}
    	} else{
    		if(!rapCursor.isFirst()){
    		rapCursor.moveToPrevious();
    		}
    		
    	}
    	skillCursor.moveToPosition(rapCursor.getPosition());
    	brName.setText(rapCursor.getString(1));
		int imgId = getResources().getIdentifier("com.danielmeyer.battlerap:drawable/" + "br" + rapCursor.getString(0), null, null);
		image.setImageResource(imgId);
		
		//Set skills
		skill1val.setText(skillCursor.getString(1));
		skill2val.setText(skillCursor.getString(2));
		skill3val.setText(skillCursor.getString(3));
		skill4val.setText(skillCursor.getString(4));
		skill5val.setText(skillCursor.getString(5));
		skill6val.setText(skillCursor.getString(6));
		
		currentRapper = rapCursor.getString(1);
		currentRapperId = rapCursor.getInt(0);
		
        return true;
    }
   
    @Override
    public void onLongPress(MotionEvent e) {
        viewA.setText("-" + "LONG PRESS" + "-");
    }
   
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        viewA.setText("-" + "SCROLL" + "-");
        return true;
    }
   
    @Override
    public void onShowPress(MotionEvent e) {
        viewA.setText("-" + "SHOW PRESS" + "-");
    }    
   
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        viewA.setText("-" + "SINGLE TAP UP" + "-");
        return true;
    }
	

}


    


   