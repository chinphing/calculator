package org.xing.theme;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class Theme {
    private String id;
    private String name;
    private JSONObject style;
    private List<String> imagePaths;

    public Theme () {
        //默认全白色背景，黑色字体
        id = "0";
        name = "默认主题";
        style = null;
        imagePaths = new LinkedList<>();
    }

    public Theme (String id, String name, String style, String imagePathStr) {
        this.id = id;
        this.name = name;
        try {
            this.style = new JSONObject(style);
        }catch (Exception e) { }
        imagePaths = new LinkedList<>();

        if(imagePathStr != null) {
            String[] fields = imagePathStr.split(";");
            for(String path : fields) {
                if(path.length() > 0) {
                    imagePaths.add(path);
                }
            }
        }
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
    public String getStyle() {
        return style.toString();
    }
    public String getStyle(String name) {
        try {
            return style.getString(name);
        }catch (Exception e) { }
        return null;
    }

    public void setStyle(String style) {
        try {
            this.style = new JSONObject(style);
        }catch (Exception e) { }
        imagePaths = new LinkedList<>();
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public String getImagePathsStr() {
        StringBuilder str= new StringBuilder();
        for(String path : imagePaths) {
            if(str.length() > 0) {
                str.append(";");
            }
            str.append(path);
        }
        return str.toString();
    }

    public void setBackgroundImage(String imagePathStr) {
        imagePaths.clear();

        if(imagePathStr != null) {
            String[] fields = imagePathStr.split(";");
            for(String path : fields) {
                if(path.length() > 0) {
                    imagePaths.add(path);
                }
            }
        }
    }

}
