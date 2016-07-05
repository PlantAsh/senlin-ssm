package ws.senlin.entity;

import java.util.Date;

public class PostsReply {
    private Integer replyId;

    private String userAccount;

    private String userName;

    private String replyText;

    private Date replyDate;

    private String replyPicture;

    private Integer postsSuper;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText == null ? null : replyText.trim();
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyPicture() {
        return replyPicture;
    }

    public void setReplyPicture(String replyPicture) {
        this.replyPicture = replyPicture == null ? null : replyPicture.trim();
    }

    public Integer getPostsSuper() {
        return postsSuper;
    }

    public void setPostsSuper(Integer postsSuper) {
        this.postsSuper = postsSuper;
    }
}