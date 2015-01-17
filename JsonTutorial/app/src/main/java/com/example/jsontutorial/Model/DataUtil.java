package com.example.jsontutorial.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RushitKapadia on 13-Jan-15.
 */
public class DataUtil {
    String category, uid, body , url, url_title, type, path;

    public static DataUtil setData(JSONObject object){
    DataUtil dataUtil = new DataUtil();
        try {
            dataUtil.setBody(object.getString("body").toString());
            dataUtil.setCategory(object.getString("category").toString());
            dataUtil.setPath(object.getString("path").toString());
            dataUtil.setType(object.getString("type").toString());
            dataUtil.setUid(object.getString("uid").toString());
            dataUtil.setUrl(object.getString("url").toString());
            dataUtil.setUrl_title(object.getString("url_title").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  dataUtil;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_title() {
        return url_title;
    }

    public void setUrl_title(String url_title) {
        this.url_title = url_title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
