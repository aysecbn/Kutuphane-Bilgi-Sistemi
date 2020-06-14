
public class Kitap 
{
    private int id;
    private String ad;
    private String yayinevi;
    private String yazar;

    public Kitap(int id, String ad, String yayinevi, String yazar) {
        this.id = id;
        this.ad = ad;
        this.yayinevi = yayinevi;
        this.yazar = yazar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getYayinevi() {
        return yayinevi;
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }
    
    
}
