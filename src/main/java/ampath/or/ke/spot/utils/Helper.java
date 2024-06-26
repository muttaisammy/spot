package ampath.or.ke.spot.utils;

public class Helper {

    private String icon;
    private String title;

    public Helper(String success, String s) {
        icon = success;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}