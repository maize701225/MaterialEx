package idv.hangermo.materialex.lastest;

import java.io.Serializable;

/**
 * Created by user on 2015/6/28.
 */
public class LastestVO implements Serializable {
    private String title;
    private String url;
    private String picUrl;

    public LastestVO() {
        super();
    }

    public LastestVO(String title, String url, String picUrl) {
        this.title = title;
        this.url=url;
        this.picUrl=picUrl;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
