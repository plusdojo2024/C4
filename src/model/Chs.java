package model;

public class Chs {
    private int channelId;
    private String chName;
    private String chComment;
    private String createdAt;

    public Chs(int channelId, String chName, String chComment, String createdAt) {
        this.channelId = channelId;
        this.chName = chName;
        this.chComment = chComment;


    }
    // Getters and setters
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChComment() {
        return chComment;
    }

    public void setChComment(String chComment) {
        this.chComment = chComment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


}



