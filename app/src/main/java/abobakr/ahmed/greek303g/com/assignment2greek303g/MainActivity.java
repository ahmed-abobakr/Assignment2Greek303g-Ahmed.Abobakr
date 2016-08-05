package abobakr.ahmed.greek303g.com.assignment2greek303g;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import abobakr.ahmed.greek303g.com.assignment2greek303g.adpater.PostsAdapter;
import abobakr.ahmed.greek303g.com.assignment2greek303g.async.GetPostInfo;
import abobakr.ahmed.greek303g.com.assignment2greek303g.models.PostInfo;
import abobakr.ahmed.greek303g.com.assignment2greek303g.parser.PostsParser;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {


    private RecyclerView postsRecyclerView;
    private PostsAdapter postsAdapter;
    private List<PostInfo> postsList;
    private final String API_URL = "https://dl.dropboxusercontent.com/s/7rvknz9e6tfprun/facebookFeed.json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        postsRecyclerView = (RecyclerView) findViewById(R.id.posts_list);


        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        postsRecyclerView.setLayoutManager(mLayout);
        postsRecyclerView.setItemAnimator(new DefaultItemAnimator());





        new GetPostInfo(this, new PostsParser(), API_URL);
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
                new GetPostInfo(this, new PostsParser(), API_URL);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(List<PostInfo> postInfto){
        postsList = postInfto;
        postsAdapter = new PostsAdapter(postsList, this);
        postsRecyclerView.setAdapter(postsAdapter);
    }



}
