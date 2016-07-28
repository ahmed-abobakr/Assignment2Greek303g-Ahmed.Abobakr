package abobakr.ahmed.greek303g.com.assignment2greek303g.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import abobakr.ahmed.greek303g.com.assignment2greek303g.R;
import abobakr.ahmed.greek303g.com.assignment2greek303g.models.PostInfo;
import de.greenrobot.event.EventBus;

/**
 * Created by ahmed-abobakr on 28/07/16.
 */
public class GetPostInfo extends AsyncTask<Void, Void, String> {

    private final String API_URL = "https://dl.dropboxusercontent.com/s/7rvknz9e6tfprun/facebookFeed.json";
    private Context context;

    public GetPostInfo(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String postStr = null;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL(API_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();


            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }


            postStr = buffer.toString();

        }catch (IOException ex){
            ex.printStackTrace();
            postStr = null;
        }finally {
            if(connection != null){
                connection.disconnect();
            }


        }

        return postStr;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result == null){
            Toast.makeText(context, context.getResources().getString(R.string.error_getData), Toast.LENGTH_SHORT).show();
        }else {
            Gson gson = new Gson();
            PostInfo postInfo = gson.fromJson(result, PostInfo.class);
            EventBus.getDefault().post(postInfo);
        }
    }
}
