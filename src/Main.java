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

public class Main {


    public static void main(String[] args)throws SQLException, ParseException {


        Connection conn = null;
        Document doc = null;


        try {

            doc = Jsoup.connect("http://www.betonews.com/table.asp?lang=en&tp=3062").get();
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


        Element table = doc.select("table[width=100%][border=0][cellpadding=3][cellspacing=1]").first() ;
        for (Element row : table.select("tr:gt(0)")){

            Elements td = row.select("td");

            Statement s = conn.createStatement ();

            PreparedStatement preparedStmt = conn.prepareStatement("insert into bet.BetOddsResults (Competition, date, KickOffTime, Code, Team1, Team2, HomeOdd, DrawOdd," +
                    " AwayOdd, U, O, G, NG, 0to1, 2to3, 4to6, HomeGoals_1H, AwayGoals_1H, TotalGoals1H, HomeGoals_2H, AwayGoals_2H, TotalGoals2H) " + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                    " " + " ON DUPLICATE KEY UPDATE " + "Competition = VALUES(Competition)," + "date = VALUES(date),"
                    + "KickOffTime = VALUES(KickOffTime),"  + "Code = VALUES(Code)," + "Team1 = VALUES(Team1),"  + "Team2 = VALUES(Team2)," +"HomeOdd = VALUES(HomeOdd)," + "DrawOdd = VALUES(DrawOdd),"
                    + "AwayOdd = VALUES(AwayOdd), " + "U = VALUES(U)," + "O = VALUES(O)," + "G = VALUES(G)," + "NG = VALUES(NG),"
                    + "0to1 = VALUES(0to1)," + "2to3 = VALUES(2to3)," + "4to6 = VALUES(4to6)," + "HomeGoals_1H = VALUES(HomeGoals_1H)," + "AwayGoals_1H = VALUES(AwayGoals_1H)," + "TotalGoals1H = VALUES(TotalGoals1H)," + "HomeGoals_2H = VALUES(HomeGoals_2H),"
                    + "AwayGoals_2H = VALUES(AwayGoals_2H)," + "TotalGoals2H = VALUES(TotalGoals2H)," +"id = LAST_INSERT_ID(id)");


            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();

            preparedStmt.setString(1, td.eq(0).text());
            preparedStmt.setString(2, dateFormat.format(date));
            preparedStmt.setString(3, td.eq(1).text());
            preparedStmt.setString(4, td.eq(2).text());
            preparedStmt.setString(5, td.eq(6).text());
            preparedStmt.setString(6, td.eq(10).text());
            preparedStmt.setString(7, td.eq(4).text());
            preparedStmt.setString(8, td.eq(8).text());
            preparedStmt.setString(9, td.eq(12).text());
            preparedStmt.setString(10, "");
            preparedStmt.setString(11, "");
            preparedStmt.setString(12, "");
            preparedStmt.setString(13, "");
            preparedStmt.setString(14, "");
            preparedStmt.setString(15, "");
            preparedStmt.setString(16, "");
            preparedStmt.setString(17, "");
            preparedStmt.setString(18, "");
            preparedStmt.setString(19, "");
            preparedStmt.setString(20, "");
            preparedStmt.setString(21, "");
            preparedStmt.setString(22, "");

            int euReturnValue = preparedStmt.executeUpdate();

            System.out.println(String.format("executeUpdate returned %d", euReturnValue));
            ResultSet rs = (ResultSet) s.executeQuery("SELECT LAST_INSERT_ID() AS n");
            rs.next();
            rs.getInt(1);

            s.close ();

        }



    }


}
