package rs.dodatnaoprema.dodatnaoprema.models;

/**
 * Created by milan on 4/20/2016.
 */

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    String id, name, email;
    Uri photo;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = null;
    }

    public User(String id, String name, String email, Uri photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = null;
        if (photo != null)
            this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}