package sg.edu.rp.c390.c302p06sakilaclient2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film> {


    private ArrayList<Film> alFilms;
    private Context context;
    private TextView tvTitle, tvYear, tvRating;


    public FilmAdapter(Context context, int resource, ArrayList<Film> objects) {
        super(context, resource, objects);
        alFilms = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.film_row,parent,false);

        tvTitle = rowView.findViewById(R.id.textViewTitle);
        tvYear = rowView.findViewById(R.id.textViewYear);
        tvRating = rowView.findViewById(R.id.textViewRating);

        Film currposition = alFilms.get(position);

        tvTitle.setText(currposition.getTitle());
        tvYear.setText(currposition.getYear());
        tvRating.setText(currposition.getRating());


        return rowView;
    }
}
