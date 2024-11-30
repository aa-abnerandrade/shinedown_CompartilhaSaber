package model;

import android.media.Image;

public class ShineLessonModel {
    public long id;
    public String title;
    public String value;
    public String image;
    public int userId;

    public ShineLessonModel(long id, String title, String value, String image, int userId) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.image = image;
        this.userId = userId;
    }

    public ShineLessonModel(String title, String value, String image, int userId) {
        this.title = title;
        this.value = value;
        this.image = image;
        this.userId = userId;
    }
}
