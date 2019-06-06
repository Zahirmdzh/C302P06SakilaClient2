package sg.edu.rp.c390.c302p06sakilaclient2;

public class Summary {
    private String category;
    private String numberfilms;

    public Summary(String category, String numberfilms) {
        this.category = category;
        this.numberfilms = numberfilms;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumberfilms() {
        return numberfilms;
    }

    public void setNumberfilms(String numberfilms) {
        this.numberfilms = numberfilms;
    }
}
