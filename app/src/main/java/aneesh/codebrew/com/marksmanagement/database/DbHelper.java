package aneesh.codebrew.com.marksmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aneesh.codebrew.com.marksmanagement.R;
import aneesh.codebrew.com.marksmanagement.model.DataModel;

public class DbHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "MarkSheet.db";
    private static final Integer DB_VERSION = 1;
    private static final String TABLE_NAME = "Student_Details";
    private static final String NAME = "Name";
    private static final String ROLLNO = "Rollno";
    private static final String STREAM = "Stream";
    private static final String SUBJECT1 = "English";
    private static final String SUBJECT2 = "Maths";
    private static final String SUBJECT3 = "Hindi";
    private static final String SUBJECT4 = "Physics";
    private static final String SUBJECT5 = "Chemistry";
    private static final String TAG = "TAG";

    Context context;

    public DbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                ROLLNO + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                STREAM + " TEXT, " +
                SUBJECT1 + " INTEGER, " +
                SUBJECT2 + " INTEGER, " +
                SUBJECT3 + " INTEGER, " +
                SUBJECT4 + " INTEGER, " +
                SUBJECT5 + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion != newVersion)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void insertData(DataModel dataModel)
    {
        SQLiteDatabase db = getWritableDatabase();
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, dataModel.mName);
            contentValues.put(ROLLNO, (dataModel.mRollno));
            contentValues.put(STREAM, (dataModel.mStream));
            contentValues.put(SUBJECT1, (dataModel.mMarks.get(SUBJECT1)));
            contentValues.put(SUBJECT2, (dataModel.mMarks.get(SUBJECT2)));
            contentValues.put(SUBJECT3, (dataModel.mMarks.get(SUBJECT3)));
            contentValues.put(SUBJECT4, (dataModel.mMarks.get(SUBJECT4)));
            contentValues.put(SUBJECT5, (dataModel.mMarks.get(SUBJECT5)));
            db.insert(TABLE_NAME, null, contentValues);

        } catch (SQLiteConstraintException e)
        {
            e.printStackTrace();
        }
    }

    public List<DataModel> getDbData()
    {
        List<DataModel> dataModels = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM " + TABLE_NAME), null);
        try
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    HashMap<String, Integer> map = new HashMap<>();

                    DataModel dataModel = new DataModel();
                    dataModel.mName = cursor.getString(cursor.getColumnIndex(NAME));
                    dataModel.mRollno = cursor.getInt(cursor.getColumnIndex((ROLLNO)));
                    dataModel.mStream = cursor.getString(cursor.getColumnIndex(STREAM));

                    int data1 = cursor.getInt(cursor.getColumnIndex(SUBJECT1));
                    int data2 = cursor.getInt(cursor.getColumnIndex(SUBJECT2));
                    int data3 = cursor.getInt(cursor.getColumnIndex(SUBJECT3));
                    int data4 = cursor.getInt(cursor.getColumnIndex(SUBJECT4));
                    int data5 = cursor.getInt(cursor.getColumnIndex(SUBJECT5));

                    map.put(SUBJECT1, data1);
                    map.put(SUBJECT2, data2);
                    map.put(SUBJECT3, data3);
                    map.put(SUBJECT4, data4);
                    map.put(SUBJECT5, data5);
                    dataModel.mMarks = map;

                    dataModels.add(dataModel);

                }
                while (cursor.moveToNext());
            } else
                Toast.makeText(context, R.string.database_empty, Toast.LENGTH_SHORT).show();

        } catch (SQLiteConstraintException e)
        {
            e.printStackTrace();
        } finally
        {
            if (cursor != null && cursor.isClosed())
                cursor.close();

            db.close();
        }
        return dataModels;
    }

    public void deleteRollNo(String rollno)
    {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NAME, ROLLNO + " =?", new String[]{rollno});
        db.close();
    }

    public List<DataModel> getRollnoData(int rollno)
    {
        List<DataModel> dataModels = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM " + TABLE_NAME + " WHERE " + ROLLNO + " ='" + rollno + "'"), null);
        try
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    HashMap<String, Integer> map = new HashMap<>();

                    DataModel dataModel = new DataModel();
                    dataModel.mName = cursor.getString(cursor.getColumnIndex(NAME));
                    dataModel.mRollno = cursor.getInt(cursor.getColumnIndex((ROLLNO)));
                    dataModel.mStream = cursor.getString(cursor.getColumnIndex(STREAM));

                    int data1 = cursor.getInt(cursor.getColumnIndex(SUBJECT1));
                    int data2 = cursor.getInt(cursor.getColumnIndex(SUBJECT2));
                    int data3 = cursor.getInt(cursor.getColumnIndex(SUBJECT3));
                    int data4 = cursor.getInt(cursor.getColumnIndex(SUBJECT4));
                    int data5 = cursor.getInt(cursor.getColumnIndex(SUBJECT5));

                    map.put(SUBJECT1, data1);
                    map.put(SUBJECT2, data2);
                    map.put(SUBJECT3, data3);
                    map.put(SUBJECT4, data4);
                    map.put(SUBJECT5, data5);
                    dataModel.mMarks = map;

                    dataModels.add(dataModel);

                }
                while (cursor.moveToNext());
            } else
                Toast.makeText(context, R.string.database_empty, Toast.LENGTH_SHORT).show();

        } catch (SQLiteConstraintException e)
        {
            e.printStackTrace();
        } finally
        {
            if (cursor != null && cursor.isClosed())
                cursor.close();

            db.close();
        }
        return dataModels;
    }

    public boolean checkRollno(int rollno)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(("SELECT * FROM " + TABLE_NAME + " WHERE " + ROLLNO + " ='" + rollno + "'"), null);

        if (cursor.moveToFirst())
        {
            return true;
        } else
            return false;

    }
}
