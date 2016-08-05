package abobakr.ahmed.greek303g.com.assignment2greek303g.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import abobakr.ahmed.greek303g.com.assignment2greek303g.R;
import abobakr.ahmed.greek303g.com.assignment2greek303g.models.PostInfo;

/**
 * Created by ahmedabobakr on 8/5/16.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {


    private List<PostInfo> postsList;
    private Context mContext;

    public PostsAdapter(List<PostInfo> postsList, Context context){
        this.postsList = postsList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_adapter, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostInfo postInfo = postsList.get(position);
        Picasso.with(mContext).load(postInfo.userPic).into(holder.imgProfile);
        holder.txtName.setText(postInfo.userName);
        holder.txtPost.setText(postInfo.postText);
        holder.txtTime.setText(postInfo.postTime);
        holder.txtLikes.setText(postInfo.likes);
        holder.txtShares.setText(postInfo.shares);
        holder.txtComment.setText(postInfo.comments);

        Picasso.with(mContext).load(postInfo.postImage).into(holder.imgPost);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgProfile, imgPost;
        public TextView txtName, txtTime, txtPost, txtComment, txtLikes, txtShares;
        public Button btnLike, btnComment, btnShare;

        public ViewHolder(View v){
            super(v);

            imgProfile = (ImageView) v.findViewById(R.id.img_profile);
            imgPost = (ImageView) v.findViewById(R.id.img_post);
            txtName = (TextView) v.findViewById(R.id.txt_name);
            txtTime = (TextView) v.findViewById(R.id.txt_time);
            txtPost = (TextView) v.findViewById(R.id.txt_post);
            txtComment = (TextView) v.findViewById(R.id.txt_comments);
            txtLikes = (TextView) v.findViewById(R.id.txt_likes);
            txtShares = (TextView) v.findViewById(R.id.txt_shares);
            btnLike = (Button) v.findViewById(R.id.btn_like);
            btnComment = (Button) v.findViewById(R.id.btn_comment);
            btnShare = (Button) v.findViewById(R.id.btn_share);
        }
    }

}
