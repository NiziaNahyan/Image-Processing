package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Bundle;

public class getResults extends AppCompatActivity {
    ListView listView;
    private static final String newurl = "http://192.168.0.105/AndroidUploadImage/getandroidosnames.php";
    private String json;
    private JSONArray urls;
    public  Getjson getjsonobj;
    Customadapter customadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_results);
        listView = (ListView) findViewById(R.id.lst);
        getURLs();
    }
    //Get FoodTYpe
    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getResults.this, "Loading Menu", "Please wait...", false, false);
            }
            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                customadapter = new Customadapter(getResults.this, getjsonobj.Android_Name ,getjsonobj.bitmaps );
                listView.setAdapter(customadapter);
            }
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getjsonobj.getAllImages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
}
    private void getURLs() {
        class GetURLs extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getResults.this, "Loading...", "Please Wait...", true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getjsonobj = new Getjson(s);
                getImages();
            }
            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(newurl);
    }
}