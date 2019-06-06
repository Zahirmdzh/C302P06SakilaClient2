package sg.edu.rp.c390.c302p06sakilaclient2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewCategorySummary extends AppCompatActivity {


    TextView tvCategory,tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category_summary);

        tvCategory = findViewById(R.id.textViewCategory);
        tvNumber = findViewById(R.id.textViewNumber);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategorySummary.php", new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"


                try {
                    String txt = "";
                    String txtnum = "";
                    for (int i = 0; i <response.length();i++) {
                        JSONObject summary = response.getJSONObject(i);
                        String catname = summary.getString("category");
                        String numberfilms = summary.getString("number_films");
                        txt += "Category:     " + catname + "\n";
                        txtnum += " Number of films:     " + numberfilms + "\n";
                    }
                    tvCategory.setText(txt);
                    tvNumber.setText(txtnum);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
