package javafoodapp.model;

import javax.swing.*;

public class MenuData {
    private String icon;
    private String name;
    private MenuType type;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuData(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public MenuData() {}

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/javafoodapp/icon/" + icon + ".png"));
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }

}
