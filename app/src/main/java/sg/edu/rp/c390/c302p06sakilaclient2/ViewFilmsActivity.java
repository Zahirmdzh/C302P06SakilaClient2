package sg.edu.rp.c390.c302p06sakilaclient2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewFilmsActivity extends AppCompatActivity {

    ListView lvFilms;
    ArrayList<Film> alFilms = new ArrayList<Film>();
    ArrayAdapter aa;
    TextView tvCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_films);

        tvCat = findViewById(R.id.textViewCategory);
        Intent intent = getIntent();
        Category category = (Category) intent.getSerializableExtra("category");
        tvCat.setText(category.getName());

        lvFilms = findViewById(R.id.listViewFilms1);
        aa= new FilmAdapter(this,R.layout.film_row, alFilms);

        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(category.getId()));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getFilmsByCategoryId.php",params, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"

                try {
                    for (int i = 0; i <response.length();i++) {
                        JSONObject film = response.getJSONObject(i);
                        int filmId = film.getInt("film_id");
                        String title = film.getString("title");
                        String desc = film.getString("description");
                        String year = film.getString("release_year");
                        String rating = film.getString("rating");
                        Film films = new Film(filmId,title,desc,year,rating);
                        alFilms.add(films);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                aa.notifyDataSetChanged();
            }
        });
        lvFilms.setAdapter(aa);

    }
}
