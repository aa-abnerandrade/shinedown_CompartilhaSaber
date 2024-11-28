package model;

public class ProfileDataModel {
    public long id;
    public String name;
    public String cpf;
    public String email;
    public String phone;
    public String password;
    public String payment;
    public boolean isNotificationEmail;
    public boolean isPrivacyData;

    public ProfileDataModel(String name, String cpf, String email, String phone, String password, String payment, boolean isNotificationEmail, boolean isPrivacyData) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.payment = payment;
        this.isNotificationEmail = isNotificationEmail;
        this.isPrivacyData = isPrivacyData;
    }

    public ProfileDataModel(long id, String name, String cpf, String email, String phone, String password, String payment, boolean isNotificationEmail, boolean isPrivacyData) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.payment = payment;
        this.isNotificationEmail = isNotificationEmail;
        this.isPrivacyData = isPrivacyData;
    }
}
