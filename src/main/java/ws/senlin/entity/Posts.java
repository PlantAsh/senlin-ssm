package ws.senlin.entity;

import java.util.Date;

public class Posts {
    private Integer postsId;

    private String userAccount;

    private String userName;

    private String postsTitle;

    private String postsText;

    private Date postsDate;

    private String postsFloor;

    private String postsPicture;

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPostsTitle() {
        return postsTitle;
    }

    public void setPostsTitle(String postsTitle) {
        this.postsTitle = postsTitle == null ? null : postsTitle.trim();
    }

    public String getPostsText() {
        return postsText;
    }

    public void setPostsText(String postsText) {
        this.postsText = postsText == null ? null : postsText.trim();
    }

    public Date getPostsDate() {
        return postsDate;
    }

    public void setPostsDate(Date postsDate) {
        this.postsDate = postsDate;
    }

    public String getPostsFloor() {
        return postsFloor;
    }

    public void setPostsFloor(String postsFloor) {
        this.postsFloor = postsFloor == null ? null : postsFloor.trim();
    }

    public String getPostsPicture() {
        return postsPicture;
    }

    public void setPostsPicture(String postsPicture) {
        this.postsPicture = postsPicture == null ? null : postsPicture.trim();
    }
}