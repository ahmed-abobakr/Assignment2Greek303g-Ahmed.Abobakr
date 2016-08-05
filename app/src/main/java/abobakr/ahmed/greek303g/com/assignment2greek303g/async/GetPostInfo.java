package abobakr.ahmed.greek303g.com.assignment2greek303g.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import abobakr.ahmed.greek303g.com.assignment2greek303g.R;
import abobakr.ahmed.greek303g.com.assignment2greek303g.models.PostInfo;
import abobakr.ahmed.greek303g.com.assignment2greek303g.parser.JsonParser;
import de.greenrobot.event.EventBus;

/**
 * Created by ahmed-abobakr on 28/07/16.
 */
public class GetPostInfo {


    private Context context;
    private JsonParser parser;
    private List<PostInfo> postInfoList;


    public GetPostInfo(Context context, JsonParser parser, String url){
        this.context = context;
        this.parser = parser;
        new GetFromServer().execute(url);
    }

    private class GetFromServer extends AsyncTask<String, Void, Void>{


        @Override
        protected Void doInBackground(String... strings) {

            String postStr;
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try{
                URL url = new URL(strings[0]);
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
                postInfoList = parser.parseJson(postStr);

            }catch (IOException ex){
                ex.printStackTrace();
                postInfoList = null;
            }finally {
                if(connection != null){
                    connection.disconnect();
                }


            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(postInfoList == null || postInfoList.size() == 0){
                Toast.makeText(context, context.getResources().getString(R.string.error_getData), Toast.LENGTH_SHORT).show();
            }else {

                EventBus.getDefault().post(postInfoList);
            }
        }
    }
}
