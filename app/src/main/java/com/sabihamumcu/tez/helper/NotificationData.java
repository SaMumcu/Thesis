package com.sabihamumcu.tez.helper;

import java.io.Serializable;

/**
 * Created by sabis on 2/3/2018.
 */

public class NotificationData implements Serializable{

    String title;
    String body;

    public NotificationData(String title,String data){
        this.title=title;
        this.body=body;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
