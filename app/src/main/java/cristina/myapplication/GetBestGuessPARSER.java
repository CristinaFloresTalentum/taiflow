package cristina.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.*;
/**
 * Created by Cristina on 28/8/17.
 */

public class GetBestGuessPARSER {

    private String url = "https://personalshopper.000webhostapp.com/RS/example.php";

    //JSON Node Names
    private static final String TAG_BEGIN = "best_guess";
    private static final String TAG_ID = "best_guess_name";
    private static final String TAG_NAME = "url0";
    private static final String TAG_SIZE = "name0";
    private static final String TAG_ORIGINAL = "original0";

    JSONArray begin = null;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.best_guess, container, false);

        new JSONParse().execute();

        return rootView;
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                begin = json.getJSONArray(TAG_BEGIN);
                JSONObject c = begin.getJSONObject(0);

                // Storing  JSON item in a Variable
                String best_guess = c.getString(TAG_ID);
                String best_guess_name = c.getString(TAG_NAME);
                String url0 = c.getString(TAG_NAME);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
