package com.startandroid.client.Model.Responses;

/**
 * Created by Денис on 29.07.2016.
 */
public class User {
    /**
     * email : email@email.com
     * name : Denis
     * accessToken : acbkjsbk
     */

    private String email;
    private String name;
    private String accessToken;

    /**
     * avatar : http://minionomaniya.ru/wp-content/uploads/2016/01/Cartoons_Minions_little_girl_051610_.jpg
     */


    private String avatar;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
