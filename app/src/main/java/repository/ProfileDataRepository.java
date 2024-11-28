package repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProfileDataRepository extends SQLiteOpenHelper {
  private static final String DB_NAME = "shinedown.db";
  public static String DB_TABLE = "profile_data";
  private static final int DB_VERSION = 1;
  public static String COL_ID = "id";
  public static String COL_NAME = "name";
  public static String COL_CPF = "cpf";
  public static String COL_EMAIL = "email";
  public static String COL_PHONE = "phone";
  public static String COL_PASSWORD = "password";
  public static String COL_PAYMENT = "payment";
  public static String COL_NOTIFICATIONEMAIL = "notificationEmail";
  public static String COL_PRIVACYDATA = "privacyData";

  private Context context;
  public ProfileDataRepository(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (" +
        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COL_NAME + " TEXT, " +
        COL_CPF + " TEXT UNIQUE, " +
        COL_EMAIL + " TEXT, " +
        COL_PHONE + " TEXT, " +
        COL_PASSWORD + " TEXT, " +
        COL_PAYMENT + " TEXT, " +
        COL_NOTIFICATIONEMAIL + " INTEGER, " +
        COL_PRIVACYDATA + " INTEGER" +
        ");";
    sqLiteDatabase.execSQL(query);
    createTestUser(sqLiteDatabase);
  }

  private void createTestUser(SQLiteDatabase sqLiteDatabase) {
    ContentValues contentValues = new ContentValues();
    contentValues.put(COL_NAME, "João Silva");
    contentValues.put(COL_CPF, "12345678900");
    contentValues.put(COL_EMAIL, "joaosilva@email.com");
    contentValues.put(COL_PHONE, "41 987654321");
    contentValues.put(COL_PASSWORD, "123456");
    contentValues.put(COL_PAYMENT, "Cartão de Crédito");
    contentValues.put(COL_NOTIFICATIONEMAIL, 0); // 0 para false
    contentValues.put(COL_PRIVACYDATA, 1); // 1 para true
    long result = sqLiteDatabase.insert(DB_TABLE, null, contentValues);
      if (result == -1) {
        Log.i("ProfileDataRepository ----> ", "FALHA em createTestUser");
        System.out.println("ProfileDataRepository: FALHA em createTestUser");
      } else {
        Log.i("ProfileDataRepository -----> ", "Usuário de teste criado com ID = " + result);
        System.out.println("ProfileDataRepository: Usuário de teste criado com ID = " + result);
      }
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }

  public boolean editProfileDataOnDB(long id, String name, String cpf, String email, String phone, String password, String payment, boolean notificationEmail, boolean privacyData) {
    SQLiteDatabase database = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COL_NAME, name);
    contentValues.put(COL_CPF, cpf);
    contentValues.put(COL_EMAIL, email);
    contentValues.put(COL_PHONE, phone);
    contentValues.put(COL_PASSWORD, password);
    contentValues.put(COL_PAYMENT, payment);
    contentValues.put(COL_NOTIFICATIONEMAIL, notificationEmail ? 1 : 0);
    contentValues.put(COL_PRIVACYDATA, privacyData ? 1 : 0);

    int rowsAffected = database.update(
        DB_TABLE,
        contentValues,
        COL_ID + " = ?",
        new String[]{String.valueOf(id)}
    );

      if (rowsAffected == 0) {
          long result = database.insert(DB_TABLE, null, contentValues);
          database.close();
          return result != -1;
      } else {
          database.close();
          return true;
      }
  }

}
