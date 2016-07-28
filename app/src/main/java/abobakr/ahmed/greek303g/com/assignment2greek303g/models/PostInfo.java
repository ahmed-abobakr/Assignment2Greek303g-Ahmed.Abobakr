package abobakr.ahmed.greek303g.com.assignment2greek303g.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed-abobakr on 28/07/16.
 */
public class PostInfo {

    @SerializedName("comments")
    public String comments;
    @SerializedName("likes")
    public String likes;
    @SerializedName("postImage")
    public String postImage;
    @SerializedName("postText")
    public String postText;
    @SerializedName("postTime")
    public String postTime;
    @SerializedName("shares")
    public String shares;
    @SerializedName("userName")
    public  String userName;
    @SerializedName("userPic")
    public String userPic;
    @SerializedName("postType")
    private int postType;

}
