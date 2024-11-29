package model;

import android.media.Image;

public class ShineLessonModel {
    public long id;
    public String title;
    public String value;
    public String image;

    public ShineLessonModel(long id, String title, String value, String image) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.image = image;
    }

    public ShineLessonModel(String title, String value, String image) {
        this.title = title;
        this.value = value;
        this.image = image;
    }
}
