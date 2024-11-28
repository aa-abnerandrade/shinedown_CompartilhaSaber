package model;

public class ShineLessonModel {
    public long id;
    public String title;
    public double value;

    public ShineLessonModel(long id, String title, double value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    public ShineLessonModel(String title, double value) {
        this.title = title;
        this.value = value;
    }
}
