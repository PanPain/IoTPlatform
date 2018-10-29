package edu.xd.ridelab.common.security;

/**
 * 在客户端请求网页的时候需要有一个实体类来向客户端传递消息
 *
 * @Author ChenXiang
 * @Date 2018/08/12,19:23
 */
public class Msg {
    private String title;
    private String content;
    private String extraInfo;

    public Msg(){

    }

    public Msg(String title, String content, String extraInfo) {
        super();
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
