package sg.edu.rp.c390.c302p06sakilaclient2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private ListView lvCategories;
    ArrayList<Category> alCategories = new ArrayList<Category>();
    ArrayAdapter<Category> aaCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCategories = findViewById(R.id.listViewCategories1);
        aaCategories = new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, alCategories);
        lvCategories.setAdapter(aaCategories);

		//TODO: Task 1: Consume getCategories.php using AsyncHttpClient

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategories.php", new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"

                try {
                    for (int i = 0; i <response.length();i++) {
                        JSONObject category = response.getJSONObject(i);
                        int catId = category.getInt("category_id");
                        String name = category.getString("name");
                        Category c = new Category(catId, name);
                        alCategories.add(c);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                aaCategories.notifyDataSetChanged();
            }
        });
		

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Category catSelected = alCategories.get(pos);  // Get the selected Category
                Intent intent = new Intent(MainActivity.this,ViewFilmsActivity.class);
                intent.putExtra("category",catSelected);
                startActivity(intent);
                
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.categorySummary) {
            startActivity(new Intent(MainActivity.this,ViewCategorySummary.class));
        }
        if (id == R.id.searchFilms) {
            startActivity(new Intent(MainActivity.this,SearchFilmActivity.class));
        }
        if (id == R.id.searchRental) {

        }

        return super.onOptionsItemSelected(item);
    }
}
