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
/**
 * Created by George on 25/9/2016.
 */
public class Tester {


    public static void main(String[] args)throws SQLException {


        Document doc = null;


        try {

            doc = Jsoup.connect("http://www.betonews.com/table.asp?lang=en&tp=3062").get();  //πραγματοποιούμε τη σύνδεση με το website που θελουμε
        } catch (IOException e) {
            e.printStackTrace();
        }





        Element table = doc.select("table[width=100%][border=0][cellpadding=3][cellspacing=1]").first() ; //Επιλέγουμε το σωστό table απο το website
        for (Element row : table.select("tr:gt(0)")){  // η for εξασφαλιζει οτι με τις αντιστοιχες επαναλήψεις θα περαστούν ολα τα στοιχεία του πινακα στη βαση μας
            Elements td = row.select("td");

            System.out.println(td.eq(17).text());
            String string = td.eq(17).text();
            String[] parts = string.split("-");
            String part1 = parts[0];
            String part2 = parts[1];

            System.out.println (part1);
            System.out.println (part2);
            int a = Integer.parseInt(part1);
            int b = Integer.parseInt(part2);
            int c = a - b ;
            System.out.println(c);






        }

    }

}



