package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;


public class MediaConverter {
  public static String init64To64(String origin64) {
    int flags = Base64.NO_WRAP | Base64.URL_SAFE;
    String base64String = origin64;
    byte[] decodedBytes = Base64.decode(base64String, flags);
    String data = new String(decodedBytes, StandardCharsets.UTF_8);
    return data;
  }

  public static String bitmapToBase64(Bitmap bitmap) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
    byte[] byteArray = byteArrayOutputStream.toByteArray();
    return Base64.encodeToString(byteArray, Base64.DEFAULT);
  }

  public static Bitmap base64ToBitmap(String base64String) {
    byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
  }
}
