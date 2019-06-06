package sg.edu.rp.c390.c302p06sakilaclient2;

import java.io.Serializable;

public class Film implements Serializable {
    private int id;
    private String title;
    private String description;
    private String year;
    private String rating;

    public Film(int id, String title,String description, String year, String rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
