package abobakr.ahmed.greek303g.com.assignment2greek303g;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import abobakr.ahmed.greek303g.com.assignment2greek303g.async.GetPostInfo;
import abobakr.ahmed.greek303g.com.assignment2greek303g.models.PostInfo;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    ImageView imgProfile, imgPost;
    TextView txtName, txtTime, txtPost, txtComment, txtLikes, txtShares;
    Button btnLike, btnComment, btnShare;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgProfile = (ImageView) findViewById(R.id.img_profile);
        imgPost = (ImageView) findViewById(R.id.img_post);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtTime = (TextView) findViewById(R.id.txt_time);
        txtPost = (TextView) findViewById(R.id.txt_post);
        txtComment = (TextView) findViewById(R.id.txt_comments);
        txtLikes = (TextView) findViewById(R.id.txt_likes);
        txtShares = (TextView) findViewById(R.id.txt_shares);
        btnLike = (Button) findViewById(R.id.btn_like);
        btnComment = (Button) findViewById(R.id.btn_comment);
        btnShare = (Button) findViewById(R.id.btn_share);

        new GetPostInfo(this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();


        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                new GetPostInfo(this).execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(PostInfo postInfo){
        Picasso.with(this).load(postInfo.userPic).into(imgProfile);
        txtName.setText(postInfo.userName);
        txtPost.setText(postInfo.postText);
        txtTime.setText(postInfo.postTime);
        txtLikes.setText(postInfo.likes);
        txtShares.setText(postInfo.shares);
        txtComment.setText(postInfo.comments);

        Picasso.with(this).load(postInfo.postImage).into(imgPost);
    }


}
