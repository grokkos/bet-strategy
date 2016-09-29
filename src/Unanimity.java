import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by George on 29/9/2016.
 */
public class Unanimity {
    public static void main(String[] args)throws SQLException {

        Connection conn = null;
        Document doc = null;



        try {

            doc = Jsoup.connect("http://www.betcosmos.com/index.php?page=omofonies").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
            String userName = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/bet?characterEncoding=utf8";
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = (Connection) DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
        }


        Element table = doc.select("table.prognostika_table").first() ; //Επιλέγουμε το σωστό table απο το website
        for (Element row : table.select("tr")) {  // η for εξασφαλιζει οτι με τις αντιστοιχες επαναλήψεις θα περαστούν ολα τα στοιχεία του πινακα στη βαση μας
            Elements td = row.select("td.prognostika_header_date");

            if (td.eq(0).hasText()) {


                String string1 = td.eq(0).text();
                String[] parts1 = string1.split(" ");

                String part1 = parts1[0];
                System.out.println(part1);


            }
            Statement s = conn.createStatement ();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();



            s.close ();


        }

    }

}
