import com.mysql.jdbc.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tester3 {

    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Document doc = null;


        try {

            doc = Jsoup.connect("http://www.forebet.com/en/football-tips-and-predictions-for-italy/serie-b").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String userName = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/bet?characterEncoding=utf8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
        }


        Element table = doc.select("table.schema").first();
        for (Element row : table.select("tr:gt(1)")) {
            Elements td = row.select("td.lResTd");




            System.out.println(td.eq(0).text());
            Elements td2 = row.select("td.lResTd > span.ht_scr");
            System.out.println(td2.eq(0).text());


        }

    }
}
