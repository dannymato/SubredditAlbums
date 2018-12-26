package com.danny.subredditalbums;

public class Post {

private String title = "";
private String user = "";
private String votes = "";
private String imgUrl = "";
private boolean nsfw = false;
private boolean spoiler = false;
private long time_created = 0;

    public void setTitle(String t){title = t;}

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        checkURL();
    }

    public void setNsfw(String nsfw) {
        this.nsfw = Boolean.getBoolean(nsfw);
    }

    public void setSpoiler(String spoiler) {
        this.spoiler = Boolean.getBoolean(spoiler);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setVotes(String votes){
        this.votes = votes;
    }

    public void setTime_created(String time_created){
        this.time_created = Double.valueOf(time_created).longValue();
    }

    public String getTitle() {
        return title;
    }

    public String getVotes() {
        return votes + " points";
    }

    public String getUser() {
        return "/u/" + user;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    private void checkURL(){

        if(imgUrl.contains("imgur"))
            fixImgur();

    }

    private void fixImgur(){
        if(!(imgUrl.contains("jpg") || imgUrl.contains("png"))){
            imgUrl = imgUrl + ".jpg";
        }
    }
}
