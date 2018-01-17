package sniper.appdemo.cn.myapplication.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sniper on 2018/1/16.
 */

public class HomePicBean implements Serializable {

    private int type;
    private String imgUrl;
    private String url;
    private String id;
    private String tag;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static List<HomePicBean> getBanners() {
        List<HomePicBean> bannerList = new ArrayList<>();
        bannerList.add(new HomePicBean(1004,"访谈","http://p4.music.126.net/UaHAO6JO37A0WAjju-O4iA==/3400789478107824.jpg","","5359370"));
        bannerList.add(new HomePicBean(10,"话题","http://p3.music.126.net/jnST6o7iR-XJma5x91Y5Hw==/18622428439868369.jpg","http://music.163.com/#/topic?id=14365052","14365052"));
        bannerList.add(new HomePicBean(1,"新歌推荐","http://p4.music.126.net/T75JUe9gYdK5cbokeUXJyQ==/18693896695682960.jpg","","429210383"));
        return bannerList;
    }

    public HomePicBean(int type, String tag , String imgUrl, String url, String id) {
        this.type = type;
        this.imgUrl = imgUrl;
        this.url = url;
        this.id = id;
        this.tag = tag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
