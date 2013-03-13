package com.danielmeyer.battlerap;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
	//openOrCreateDatabase("BattlerapDB", MODE_PRIVATE, null);
	// First table
    public static final String BR_ID = "id";
    public static final String BR_NAME = "name";
    public static final String BR_HOMETOWN = "hometown";
    private static final String DATABASE_TABLE = "battlerappers";
    
    
    //Skills table
    private static final String SKILL_BRID = "br_id";
    private static final String SKILL_FLOW = "flow";
    private static final String SKILL_DELIVERY = "delivery";
    private static final String SKILL_CONFIDENCE = "confidence";
    private static final String SKILL_PUNCHLINES = "punchlines";
    private static final String SKILL_HUMOR = "humor";
    private static final String SKILL_SHAPE = "shape";
    private static final String DATABASE_TABLE2 = "skills";
    
  //Skills table
    private static final String PLAYER_ID = "id";
    private static final String PLAYER_USERNAME = "username";
    private static final String PLAYER_PASSWORD = "password";
    private static final String PLAYER_BR_ID = "br_id";
    private static final String DATABASE_TABLE3 = "players";
    
    
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "BattlerapDB";
    
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CREATE =
        "create table if not exists battlerappers (id integer primary key autoincrement, "
        + "name VARCHAR not null, hometown VARCHAR);";
    private static final String TABLE2_CREATE =
            "create table if not exists skills (br_id integer," +
            " flow integer not null, delivery integer not null, confidence integer not null," +
            " punchlines integer not null, humor integer not null, shape integer not null," +
            " foreign key(br_id) references battlerapper(id));";
    private static final String TABLE3_CREATE =
            "create table if not exists "+DATABASE_TABLE3+" ("+PLAYER_ID+" integer primary key autoincrement," +
            " "+PLAYER_USERNAME+" VARCHAR not null UNIQUE, "+PLAYER_PASSWORD+" VARCHAR, "+PLAYER_BR_ID+" integer not null,"+
            		" foreign key("+PLAYER_BR_ID+") references battlerapper("+BR_ID+"));";
        
    private final Context context;    

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	try {
        		db.execSQL(TABLE_CREATE);
        		db.execSQL(TABLE2_CREATE);
        		db.execSQL(TABLE3_CREATE);
        		
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        	
        	// Initiate predefined battlerappers
            	BattleRapper.InitBattleRappers();
            	for(BattleRapper b : BattleRapper.getBrList()){
            		ContentValues initialValues = new ContentValues();
                    initialValues.put(BR_NAME, b.getName());
                    initialValues.put(BR_HOMETOWN, b.getHometown());
                     db.insert(DATABASE_TABLE, null, initialValues);
                     ContentValues initialValues2 = new ContentValues();
                     initialValues2.put(SKILL_BRID, b.getId());
                     initialValues2.put(SKILL_FLOW, b.getSkills().getFlow());
                     initialValues2.put(SKILL_DELIVERY, b.getSkills().getDelivery());
                     initialValues2.put(SKILL_CONFIDENCE, b.getSkills().getConfidence());
                     initialValues2.put(SKILL_PUNCHLINES, b.getSkills().getPunchlines());
                     initialValues2.put(SKILL_HUMOR, b.getSkills().getHumor());
                     initialValues2.put(SKILL_SHAPE, b.getSkills().getShape());
                     db.insert(DATABASE_TABLE2, null, initialValues2);
            	}
            
        	
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }    

    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a record into the database---
    public void InsertNewBattleRapper(String name, String hometown, int flow, int delivery, int confidence, int punchlines, int humor, int shape) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(BR_NAME, name);
        initialValues.put(BR_HOMETOWN, hometown);
         db.insert(DATABASE_TABLE, null, initialValues);
         ContentValues initialValues2 = new ContentValues();
         initialValues2.put(SKILL_BRID, getBrId(name).getString(0));
         initialValues2.put(SKILL_FLOW, flow);
         initialValues2.put(SKILL_DELIVERY, delivery);
         initialValues2.put(SKILL_CONFIDENCE, confidence);
         initialValues2.put(SKILL_PUNCHLINES, punchlines);
         initialValues2.put(SKILL_HUMOR, humor);
         initialValues2.put(SKILL_SHAPE, shape);
         db.insert(DATABASE_TABLE2, null, initialValues2);
    }
    
    public void CreateUser(String username, String password, int battleRapper){
    	ContentValues values = new ContentValues();
    	values.put(PLAYER_USERNAME, username);
    	values.put(PLAYER_PASSWORD, password);
    	values.put(PLAYER_BR_ID, battleRapper);
    	db.insert(DATABASE_TABLE3, null, values);
    }
    
    public void InsertBattleRappers(){
    	BattleRapper.InitBattleRappers();
    	for(BattleRapper b : BattleRapper.getBrList()){
    		ContentValues initialValues = new ContentValues();
            initialValues.put(BR_NAME, b.getName());
            initialValues.put(BR_HOMETOWN, b.getHometown());
             db.insert(DATABASE_TABLE, null, initialValues);
             ContentValues initialValues2 = new ContentValues();
             initialValues2.put(SKILL_BRID, b.getId());
             initialValues2.put(SKILL_FLOW, b.getSkills().getFlow());
             initialValues2.put(SKILL_DELIVERY, b.getSkills().getDelivery());
             initialValues2.put(SKILL_CONFIDENCE, b.getSkills().getConfidence());
             initialValues2.put(SKILL_PUNCHLINES, b.getSkills().getPunchlines());
             initialValues2.put(SKILL_HUMOR, b.getSkills().getHumor());
             initialValues2.put(SKILL_SHAPE, b.getSkills().getShape());
             db.insert(DATABASE_TABLE2, null, initialValues2);
    	}
    }
    

    //---deletes a particular record---
    public boolean deleteContact(long rowId) 
    {
        return db.delete(DATABASE_TABLE, BR_ID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords() 
    {
        return db.query(DATABASE_TABLE, new String[] {BR_ID, BR_NAME,
                BR_HOMETOWN}, null, null, null, null, null);
    }
    
    public Cursor GetAllRappers() 
    {
        return db.query(DATABASE_TABLE, new String[] {BR_ID, BR_NAME,
                BR_HOMETOWN}, null, null, null, null, null);
    }
    
    public Cursor GetAllSkills()
    {
        return db.query(DATABASE_TABLE2, new String[] {SKILL_BRID, SKILL_FLOW, SKILL_DELIVERY, SKILL_CONFIDENCE,
        		SKILL_PUNCHLINES, SKILL_HUMOR, SKILL_SHAPE}, null, null, null, null, null);
    }

    //---retrieves a particular record---
    public Cursor getRecord(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {BR_ID,
                BR_NAME, BR_HOMETOWN}, 
                BR_ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    public Cursor getBrId(String brName) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {BR_ID,
                BR_NAME, BR_HOMETOWN}, 
                BR_NAME + "=" + "'"+brName+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(long rowId, String title, String duedate, String course, String notes) 
    {
        ContentValues args = new ContentValues();
        args.put(BR_NAME, title);
        args.put(BR_HOMETOWN, duedate);
        return db.update(DATABASE_TABLE, args, BR_ID + "=" + rowId, null) > 0;
    }
    
    public Cursor getSkills(String brName) throws SQLException 
    {
    	//db.execSQL(TABLE2_CREATE);
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {BR_ID, BR_NAME, BR_HOMETOWN}, 
                BR_NAME + "=" + "'"+brName+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
      
        Cursor mCursor2 =
                db.query(true, DATABASE_TABLE2, new String[] {SKILL_BRID, SKILL_FLOW,
                		SKILL_DELIVERY, SKILL_CONFIDENCE, SKILL_PUNCHLINES, SKILL_HUMOR, SKILL_SHAPE}, 
                		SKILL_BRID + "=" + mCursor.getString(0), null, null, null, null, null);
        return mCursor2;
    }
    
    public boolean ContainsUsers(){
    	
    	Cursor cursor = db.query(DATABASE_TABLE3, new String[] { PLAYER_USERNAME }, null, null, null, null, null);
    	if (cursor != null && cursor.getCount() > 0)
    	{
    	   return true;
    	}
    	
    	return false;
    }
    
public Cursor GetUsers(){
    	
    	Cursor cursor = db.query(DATABASE_TABLE3, new String[] { PLAYER_USERNAME }, null, null, null, null, null);
    	
    	
    	return cursor;
    }
}
