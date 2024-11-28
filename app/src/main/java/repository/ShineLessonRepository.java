package repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import model.ShineLessonModel;

public class ShineLessonRepository extends SQLiteOpenHelper {
    private static  final String DB_NAME = "shinedown.db";
    private static String DB_TABLE = "shine_lesson";
    private static final int DB_VERSION = 1;
    private static String COL_ID = "id";
    private static String COL_TITLE = "title";
    private static String COL_VALUE = "value";

    private Context context;
    public ShineLessonRepository(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_VALUE + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long createShineLessonInDB(ShineLessonModel shinelesson) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, shinelesson.title);
        values.put(COL_VALUE, shinelesson.value);
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public ArrayList<ShineLessonModel> getShineLessonFromDB() {
        ArrayList<ShineLessonModel> shinelessonList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query
                (DB_TABLE, null,  null, null, null, null, null);
        return shinelessonList;
    }

}
