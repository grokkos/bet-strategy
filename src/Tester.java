import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

public class Tester {


    public static void main(String[] args)throws SQLException {

        Connection conn = null;
        Document doc = null;
        Document doc1 = null;


        try {

            doc = Jsoup.connect("http://www.betonews.com/table.asp?lang=en&tp=3062").get();  //πραγματοποιούμε τη σύνδεση με το website που θελουμε
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
            String userName = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/bet?characterEncoding=utf8";    // σύνδεση με το localhost
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = (Connection) DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
        }


        Element table = doc.select("table[width=100%][border=0][cellpadding=3][cellspacing=1]").first() ; //Επιλέγουμε το σωστό table απο το website
        for (Element row : table.select("tr:gt(0)")) {  // η for εξασφαλιζει οτι με τις αντιστοιχες επαναλήψεις θα περαστούν ολα τα στοιχεία του πινακα στη βαση μας
            Elements td = row.select("a[href]");

            System.out.println(td.attr("href"));
            doc.setBaseUri("http://www.betonews.com/");
            try {

                doc1 = Jsoup.connect("http://www.betonews.com/" + td.attr("href")).get();
                System.out.println(td.attr("fuck"));
            } catch (IOException e) {
                e.printStackTrace();


            }

        }

    }

}



