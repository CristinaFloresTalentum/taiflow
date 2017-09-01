package cristina.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;



public class GetBestGuess extends AppCompatActivity {

    ProgressDialog progressDialog ;
    // Clase JSONParser
    JSONParser jsonParser = new JSONParser();
    private static final String URL = "https://personalshopper.000webhostapp.com/RS/example.php";
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_guess);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        // Call to process when activity is being created
        AsyncTaskNextClass AsyncTaskNextClassOBJ = new AsyncTaskNextClass();

        AsyncTaskNextClassOBJ.execute();
    }


    private class AsyncTaskNextClass extends AsyncTask<Void,Void,String> {


        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = ProgressDialog.show(GetBestGuess.this, "Image is Showing", "Please Wait", false, false);
        }

        @Override
        protected void onPostExecute(String string1) {

            super.onPostExecute(string1);

            // Dismiss the progress dialog after done uploading.
            progressDialog.dismiss();

        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                final JSONObject json = jsonParser.getJSONFromUrl(URL);

                // Getting most matchable result name and url
                final String best_guess_url = json.getString("best_guess_url");
                final String best_guess_name = json.getString("best_guess_name");

                final JSONArray results = json.getJSONArray("results");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        populateTextWithLink(best_guess_name, best_guess_url, R.id.best_guess_name);

                        for(int i = 0; i < results.length(); i++) {
                            try {
                                View baseLayout = LayoutInflater.from(GetBestGuess.this).inflate(R.layout.base_results, linearLayout, false);
                                String name = results.getJSONObject(i).get("name"+i).toString();
                                String urlPath = results.getJSONObject(i).get("url"+i).toString();
                                String urlImg = results.getJSONObject(i).get("original" + i).toString();

                                ImageView imageView = publishImgFromURL(baseLayout, urlImg, R.id.imageBase);

                                populateTextWithLink(baseLayout, name, urlPath, R.id.textBase);
                                urlToImage(imageView, urlPath);

                                linearLayout.addView(baseLayout);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            return null;


        }

        /*
            This method remove weird characters after and extension
            and allow to click and access to the photo on web
           @params url Url to remove characters after .jpg extension
         */
        public String cleaningURL(String url) {

            if(url.contains(".jpg"))
                return url.substring(0, url.indexOf(".jpg")+4);
            else if(url.contains(".png"))
                return url.substring(0, url.indexOf(".png")+4);

            return url;
        }

        private void populateTextWithLink(String name, String url, int id) {
            TextView textView = (TextView) findViewById(id);
            textView.setText(Html.fromHtml("<a href=" + url + ">"+ name + "</a>"));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        private void populateTextWithLink(View baseLayout, String name, String url, int id) {
            TextView textView = baseLayout.findViewById(id);
            textView.setText(Html.fromHtml("<a href=" + url + ">"+ name + "</a>"));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        private ImageView publishImgFromURL(View baseLayout, String src, int id){
            ImageView imageView = baseLayout.findViewById(id);
            try{
                String s = cleaningURL(src);
                URL url = new URL(s);
                InputStream inputStream = (InputStream) url.getContent();
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                imageView.setImageDrawable(drawable);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return imageView;
        }

        private void urlToImage(ImageView imageView, final String url) {
            imageView.setClickable(true);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
        }
    }
}
