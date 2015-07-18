package idv.hangermo.materialex.hotnews;

/**
 * Created by user on 2015/7/14.
 */
public class HotNewsVO {
    private String title;
    private String url;
    private String time;

    public HotNewsVO(String title, String url, String time) {
        this.title = title;
        this.url = url;
        this.time=time;
    }
    public HotNewsVO() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return url;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
