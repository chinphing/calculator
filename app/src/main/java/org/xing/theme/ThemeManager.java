package org.xing.theme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class ThemeManager {
    SQLiteDatabase database;
    LinkedList<ThemeChangeListener> listeners;

    public ThemeManager(Context ctx) {
        listeners = new LinkedList<>();
        try {
            String databasePath = ctx.getFilesDir().getAbsolutePath() + "/theme.db";
            database = SQLiteDatabase.openOrCreateDatabase(databasePath, null);
            database.execSQL("drop table themes");

            String createTableSql = "create table if not exists themes (" +
                    "id CHAR(32) PRIMARY KEY, " +
                    "name CHAR(32), " +
                    "style VARCHAR, " +
                    "img_paths VARCHAR" +
                    ")";
            database.execSQL(createTableSql);
            addTheme(new Theme("0", "测试风格1", "{'resultColor':'#000000', 'historyColor':'#444444', 'msgColor':'#555555', 'background':'#ffffff'}", null));
            addTheme(new Theme("1", "测试风格2", "{'resultColor':'#ffffff', 'historyColor':'#e0e0e0', 'msgColor':'#a0a0a0', 'background':'#222222'}", null));
            addTheme(new Theme("2", "测试风格4", "{'resultColor':'#FF3300', 'historyColor':'#ee3300', 'msgColor':'#aa3300'}", "theme/01.jpg"));
            addTheme(new Theme("3", "测试风格5", "{'resultColor':'#ffffff', 'historyColor':'#f0f0f0', 'msgColor':'#aaaaaa'}", "theme/02.jpg"));
            addTheme(new Theme("4", "测试风格6", "{'resultColor':'#ffa000', 'historyColor':'#ee9000', 'msgColor':'#cc7000'}", "theme/03.jpg"));
            addTheme(new Theme("5", "测试风格7", "{'resultColor':'#3eb3ed', 'historyColor':'#2ea3dd', 'msgColor':'#00ddff'}", "theme/04.jpg"));
        }catch (Exception e) {
            e.printStackTrace();
            database = null;
        }
    }

    public boolean addTheme(Theme theme) {
        if(database == null) return false;

        ContentValues values = new ContentValues();
        values.put("id", theme.getId());
        values.put("name", theme.getName());
        values.put("style", theme.getStyle());
        values.put("img_paths", theme.getImagePathsStr());

        try {
            long result = database.insert("themes", null, values);
            if (result > 0) return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteTheme(String id) {
        if(database == null || id == null) return false;

        String whereClause = "id=?";
        String[] whereArgs = {id};
        try {
            long result = database.delete("themes", whereClause, whereArgs);
            if (result > 0) return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteTheme(Theme theme) {
        return deleteTheme(theme.getId());
    }

    public Theme getTheme(String id) {
        try {
            Cursor cursor = database.rawQuery("select id, name, style, img_paths  from themes where id='"+id+"'", null);
            if(cursor.moveToFirst()) {
                Theme theme = new Theme(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                return theme;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Theme> getAllTheme() {
        List<Theme> themes = new LinkedList<>();
        try {
            Cursor cursor = database.rawQuery("select id, name, style, img_paths  from themes", null);
            if(cursor.moveToFirst()) {
                do {
                    Theme theme = new Theme(cursor.getString(0), cursor.getString(1),
                            cursor.getString(2), cursor.getString(3));
                    themes.add(theme);
                }while(cursor.moveToNext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return themes;
    }

    public void addThemeChangeListener(ThemeChangeListener listener) {
        listeners.add(listener);
    }

    public void applyTheme(String id) {
        if(id == null) return;
        applyTheme(getTheme(id));
    }

    public void applyTheme(Theme theme) {
        if(theme == null) return;
        for(ThemeChangeListener listener : listeners) {
            if(listener.isAlive()) {
                listener.onThemeChange(theme);
            }
        }
    }

    public void randomTheme() {
        List<Theme> themes = getAllTheme();
        if(themes.size() > 0) {
            Random random  = new Random();
            applyTheme(
                    themes.get(
                            random.nextInt(themes.size())
                    )
            );
        }
    }
}
