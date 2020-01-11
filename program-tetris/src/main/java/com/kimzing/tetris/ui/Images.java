package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Images {
    private Images() {

    }

    public static final Image TITLE = new ImageIcon(getPath("graphics/other/title.jpg"))
            .getImage();
    public static final Image KING = new ImageIcon(getPath("graphics/other/king.png"))
            .getImage();
    public static final Image DB = new ImageIcon(getPath("graphics/String/db.png"))
            .getImage();
    public static final Image Rect = new ImageIcon(getPath("graphics/game/rect.jpg"))
            .getImage();
    public static final Image LEVEL = new ImageIcon(getPath("graphics/string/level.png"))
            .getImage();
    public static final Image LOCAL = new ImageIcon(getPath("graphics/string/local.png"))
            .getImage();
    public static final Image[] NEXT_RECT;
    public static final List<Image> BACKGROUND;

    static {
        NEXT_RECT = new Image[GameConfig.getSYSTEM_CONFIG().getTypeNumber()];
        for (int i = 0; i < NEXT_RECT.length; i++) {
            NEXT_RECT[i] = new ImageIcon(getPath("graphics/game/" + i + ".png"))
                    .getImage();
        }
        File file = new File(getPath("graphics/background"));
        File[] files = file.listFiles();
        BACKGROUND = new ArrayList<Image>();
        for (File f : files) {
            if (f.isFile()) {

                BACKGROUND.add(new ImageIcon(f.getPath()).getImage());
            }
        }
    }

    public static final ImageIcon START = new ImageIcon(getPath("graphics/string/start.png"));
    public static final ImageIcon TEMP = new ImageIcon(getPath("graphics/string/temp.png"));
    public static final Image SCORE = new ImageIcon(getPath("graphics/String/score.png"))
            .getImage();
    public static final Image DISAPPEAR = new ImageIcon(
            getPath("graphics/String/disappear.png")).getImage();
    public static final Image COLOR = new ImageIcon(getPath("graphics/window/color.png"))
            .getImage();
    public static final Image WINDOW = new ImageIcon(
            getPath("graphics/window/window.png")).getImage();
    public static final Image NUMBER = new ImageIcon(
            getPath("graphics/string/number.png")).getImage();
    public static final Image SHADOW = new ImageIcon(getPath("graphics/game/shadow.png"))
            .getImage();
    public static final Image PAUSE = new ImageIcon(getPath("graphics/string/pause.png")).getImage();


    public static String getPath(String fileName) {
        if (fileName == null) return "";
        return Images.class.getResource("/" + fileName).getPath();
    }
}
