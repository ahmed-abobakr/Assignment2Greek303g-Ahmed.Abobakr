package abobakr.ahmed.greek303g.com.assignment2greek303g.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import abobakr.ahmed.greek303g.com.assignment2greek303g.models.PostInfo;

/**
 * Created by ahmedabobakr on 8/5/16.
 */
public class PostsParser extends JsonParser {

    @Override
    public List<PostInfo> parseJson(String json){
        try {
            List<PostInfo> list = new ArrayList<>();
            JSONArray postsArray = new JSONArray(json);
            for(int i = 0; i < postsArray.length(); i++){
                JSONObject postJson = postsArray.getJSONObject(i);
                PostInfo post = new PostInfo();
                post.comments = postJson.getString("comments");
                post.likes = postJson.getString("likes");
                post.postImage = postJson.getString("postImage");
                post.postText = postJson.getString("postText");
                post.postTime = postJson.getString("postTime");
                post.shares = postJson.getString("shares");
                post.userName = postJson.getString("userName");
                post.userPic = postJson.getString("userPic");
                list.add(post);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
