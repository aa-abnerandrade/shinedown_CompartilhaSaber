package repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import model.ShineLessonModel;
import util.MediaConverter;
import util.ShineLessonMock;

public class ShineLessonRepository extends SQLiteOpenHelper {
  private static final String DB_NAME = "shinedown.sc.db";
  private static String DB_TABLE = "shine_lesson";
  private static final int DB_VERSION = 1;
  private static String COL_ID = "id";
  private static String COL_TITLE = "title";
  private static String COL_VALUE = "value";
  private static String COL_IMAGE = "image";
  private static String COL_USERID = "userid";

  public static ShineLessonMock shineLessonMock;
  public static MediaConverter mediaConverter;

  private Context context;

  public ShineLessonRepository(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    shineLessonMock = new ShineLessonMock();
    mediaConverter = new MediaConverter();
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (" +
        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COL_TITLE + " TEXT, " +
        COL_VALUE + " TEXT, " +
        COL_IMAGE + " TEXT," +
        COL_USERID + " INTEGER " +
        ");";
    sqLiteDatabase.execSQL(query);
    createAllShineOnDB(sqLiteDatabase);
  }

  private void createAllShineOnDB(SQLiteDatabase sqLiteDatabase) {

    ArrayList<ShineLessonModel> allShine = shineLessonMock.getMockShineLessons();

    for (ShineLessonModel shine : allShine) {
      ContentValues contentValues = new ContentValues();
      contentValues.put(COL_TITLE, shine.title);
      contentValues.put(COL_VALUE, shine.value);
      contentValues.put(COL_IMAGE, shine.image);
      contentValues.put(COL_USERID, shine.userId);

      long result = sqLiteDatabase.insert(DB_TABLE, null, contentValues);
      if (result == -1) {
        Log.i("ShineLessonRepository ----> ", "FALHA em createAllShineOnDB");
        System.out.println("ShineLessonRepository: FALHA em createAllShineOnDB");
      } else {
        Log.i("ShineLessonRepository -----> ", "Shine criada em com ID = " + result);
        System.out.println("ShineLessonRepository: Shine criada com ID = " + result);
      }
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
    onCreate(sqLiteDatabase);
  }

  public long createShineLessonOnDB(ShineLessonModel shinelesson, int userId) {
    SQLiteDatabase database = getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COL_TITLE, shinelesson.title);
    values.put(COL_VALUE, shinelesson.value);
    values.put(COL_IMAGE, shinelesson.image);
    values.put(COL_USERID, userId);
    long id = database.insert(DB_TABLE, null, values);
    database.close();
    return id;
  }

  public ArrayList<ShineLessonModel> getAllShineLessonFromDB() {
    ArrayList<ShineLessonModel> shineLessonList = new ArrayList<>();
    SQLiteDatabase database = getReadableDatabase();
    Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      do {
        @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
        @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
        @SuppressLint("Range") String value = cursor.getString(cursor.getColumnIndex(COL_VALUE));
        @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(COL_IMAGE));
        @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex(COL_USERID));

        ShineLessonModel shineLesson = new ShineLessonModel(id, title, value, image, userId);
        shineLessonList.add(shineLesson);
      } while (cursor.moveToNext());
    }
    cursor.close();
    database.close();

    return shineLessonList;
  }

  public ArrayList<ShineLessonModel> getAllShineLessonByUserIdFromDB(int paramUserId) {
    ArrayList<ShineLessonModel> shineLessonList = new ArrayList<>();
    SQLiteDatabase database = getReadableDatabase();
    String selection = COL_USERID + " = ? ";
    String[] selectionArgs = { String.valueOf(paramUserId) };
    Cursor cursor = database.query(
        DB_TABLE,     // tbl
        null,         // Cols
        selection,    // WHERE
        selectionArgs,// Args do WHERE
        null,         // groupby
        null,         // having
        null          // order by
    );

    if (cursor.moveToFirst()) {
      do {
        @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
        @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
        @SuppressLint("Range") String value = cursor.getString(cursor.getColumnIndex(COL_VALUE));
        @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(COL_IMAGE));
        @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex(COL_USERID));

        // cria um SLModel com os valores recuperados
        ShineLessonModel shineLesson = new ShineLessonModel(id, title, value, image, userId);
        shineLessonList.add(shineLesson);
      } while (cursor.moveToNext());
    }


    cursor.close();
    database.close();

    return shineLessonList;
  }

  public boolean deleteShineLessonFromDB(long shineId) {

    SQLiteDatabase database = getWritableDatabase();

    // excluir por shineid
    String whereClause = COL_ID + " = ?";
    String[] whereArgs = { String.valueOf(shineId) };

    int rowsDeleted = database.delete(DB_TABLE, whereClause, whereArgs);
    database.close();
    return rowsDeleted > 0;
  }

}


