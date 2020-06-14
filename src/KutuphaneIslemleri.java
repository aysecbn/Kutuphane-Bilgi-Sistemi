
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KutuphaneIslemleri 
{
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    public ArrayList<Kitap> kitaplariGetir()
    {
        ArrayList<Kitap> cikti = new ArrayList<Kitap>();
        try 
        {
            statement = con.createStatement();
            String sorgu = "Select * from kitaplar";
            ResultSet rs =  statement.executeQuery(sorgu);
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String yayinevi = rs.getString("yayınevi");
                String yazar = rs.getString("yazar");
                
                cikti.add(new Kitap(id,ad,yayinevi,yazar));
            }
            return cikti;
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(KutuphaneIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public void kitapGuncelle(int id , String yeni_ad , String yeni_yayinevi , String yeni_yazar)
    {
        String sorgu = "Update kitaplar set ad = ? , yayınevi = ? , yazar = ? where id = ?"; 
        
        try 
        {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, yeni_ad);
            preparedStatement.setString(2, yeni_yayinevi);
            preparedStatement.setString(3, yeni_yazar);
            preparedStatement.setInt(4, id);
            
            preparedStatement.executeUpdate();
            
            
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(KutuphaneIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void kitapSil(int id)
    {
      String sorgu = "Delete from kitaplar where id = ?"; 
      
        try
        {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(KutuphaneIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void kitapEkle(String ad , String yayinevi , String yazar)
    {
        String sorgu = "Insert into kitaplar(ad,yayınevi,yazar) VALUES(?,?,?)";
        
        try {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, yayinevi);
            preparedStatement.setString(3, yazar);
            
            preparedStatement.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(KutuphaneIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean girisYap (String kullanici_adi ,String parola) 
    {
        String sorgu = "Select * From kullanici Where kullaniciadi = ? and sifre = ?";
        
        try 
        {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, kullanici_adi);
            
            preparedStatement.setString(2, parola);
            
            ResultSet rs =  preparedStatement.executeQuery();
            
            return rs.next();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(KutuphaneIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return false; 
        }
       
    }
    
    
    public KutuphaneIslemleri()
{
String url = "jdbc:mysql://" + DataBase.host + ":" + DataBase.port + "/" + DataBase.db_ismi + "?useUnicode=true&characterEncoding=utf8"; 
              try
              {
                  
                  Class.forName("com.mysql.jdbc.Driver");
                  
              } 
              
              catch (ClassNotFoundException ex) 
              {
                  
            System.out.println("Driver Bulunamadı...");
            
              }
               
            

        try 
        { 
            con = java.sql.DriverManager.getConnection(url,DataBase.kullanici_adi,DataBase.sifre);
            System.out.println("Veri Tabanına Bağlantı sağlandı");
            
        } 
        catch (SQLException ex)
        {
           //System.out.println("Bağlantı başarısız...");
           ex.printStackTrace();
        }
}
   /* public static void main(String[] args)
    {
      KutuphaneIslemleri islemler = new KutuphaneIslemleri();
      
        
        
    }*/
}
