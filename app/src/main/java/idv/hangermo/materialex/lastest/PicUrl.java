package idv.hangermo.materialex.lastest;

import java.io.Serializable;

/**
 * Created by user on 2015/7/17.
 */
public class PicUrl implements Serializable {
    private String picURL;

    public PicUrl(String picURL) {
        this.picURL = picURL;
    }

    public PicUrl() {
        super();
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }
}
