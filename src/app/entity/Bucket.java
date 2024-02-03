package app.entity;

import java.util.ArrayList;
public class Bucket {

    private String ownerId;
    private ArrayList<Product> content;


    public ArrayList<Product> getContent() {
        return content;
    }

    public void setContent(ArrayList<Product> secondName) {
        this.content = secondName;
    }

    public Bucket() {
    }

    public Bucket(String ownerId, ArrayList<Product> content) {
        this.ownerId = ownerId;
        this.content = content;
    }
}
