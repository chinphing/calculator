package org.xing.theme;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public interface ThemeChangeListener {
    boolean isAlive();
    void onThemeChange(Theme theme);
}
