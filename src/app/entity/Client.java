package app.entity;

public class Client {
    private String firstName;
    private String secondName;
    private String email;
    private String phone;

    private String id;

    private String passwordHash;



    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }



    public Client() {
    }

    public Client(String firstName, String secondName, String email, String phone, String id, String passwordHash) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.passwordHash = passwordHash;
    }

}
