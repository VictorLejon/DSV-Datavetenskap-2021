
import java.sql.*;
import java.util.Scanner;

public class DB {
    public static void main(String[] argv) throws SQLException {
		String url = "jdbc:ucanaccess://SQLDatabas.accdb";
		Connection con = DriverManager.getConnection(url);
        uppgiftA(con);
        uppgiftB(con);
        uppgiftC(con);
        con.close();
    }

    private static void uppgiftA(Connection con) throws SQLException {
        // Local variables
        String query;
        ResultSet rs;
        Statement stmt;

        System.out.println("Visar kurskod, kursnamn, längd och pris för alla kurser.");
        System.out.println();

        // Set the SQL statement into the variable query
        query = "SELECT kurskod, kursben, längd, pris FROM Kurs";

        // Create a statement associated to the connection con.
        // The new statement is placed in the variable stmt.
        stmt = con.createStatement();

        // Execute the SQL statement that is stored in the variable query
        // and store the result in the variable rs.
        rs = stmt.executeQuery(query);

        System.out.println("Uppgift A resultat:");

        // Loop through the ResultSet and print the results.
        // The method next() returns false when there are no more rows.
        while (rs.next()) {
            System.out.print("kurskod: " + rs.getString("kurskod"));
            System.out.print(", kursnamn: " + rs.getString("kursben"));
            System.out.print(", längd: " + rs.getString("längd"));
            System.out.println(", pris: " + rs.getString("pris"));
        }

        // Close the variable stmt and release all resources bound to it
        // Any ResultSet associated to the Statement will be automatically closed too.
        stmt.close();
    }

    private static void uppgiftB(Connection con) throws SQLException {
        // Local variables
        String query;
        ResultSet rs;
        Statement stmt;

        System.out.println();
        System.out.println("Visar namn på alla lokaler som har använts för minst ett kurstillfälle.");
        System.out.println();

        // Set the SQL statement into the variable query
        query = "SELECT DISTINCT lokal from Ktillf";

        // Create a statement associated to the connection con.
        // The new statement is placed in the variable stmt.
        stmt = con.createStatement();

        // Execute the SQL statement that is stored in the variable query
        // and store the result in the variable rs.
        rs = stmt.executeQuery(query);

        System.out.println("Uppgift B resultat:");

        // Loop through the ResultSet and print the results.
        // The method next() returns false when there are no more rows.
        while (rs.next()) {
            System.out.println("Lokal: " + rs.getString("lokal"));
        }

        // Close the variable stmt and release all resources bound to it
        // Any ResultSet associated to the Statement will be automatically closed too.
        stmt.close();
    }

    private static void uppgiftC(Connection con) throws SQLException {
        // Local variables
        String query;
        ResultSet rs;
        PreparedStatement pstmt;
        String lokalParam;

        System.out.println();
        System.out.println("Visar namn på lärare som har undervisat i en viss lokal. Användaren anger en lokal.");
        System.out.println();
		
        // Create a Scanner in order to allow the user to provide input.
        Scanner in = new Scanner(System.in);

        // Ask the user to specify a value for "Ort".
        System.out.print("Ange lokal: ");
        // Retrieve the value and place it in the variable ortparam.
        lokalParam = in.nextLine();
        in.close();

        // Set the SQL statement into the query variable
        query = "SELECT DISTINCT lnamn FROM Lärare, Ktillf WHERE lid = lärare AND lokal = ?";

        // Create a prepared statement associated to the connection and the query.
        // The new statement is placed in the variable pstmt.
        pstmt = con.prepareStatement(query);

        // Provide the value for the ? in the SQL statement.
        pstmt.setString(1, lokalParam);

        // Execute the SQL statement that is prepared in the variable pstmt
        // and store the result in the variable rs.
        rs = pstmt.executeQuery();

        System.out.println("Uppgift C resultat (lärare som har undervisat i lokalen " + lokalParam + "):");

        // Loop through the ResultSet and print the results.
        // The method next() returns false when there are no more rows.
        while (rs.next()) {
            System.out.println(rs.getString("lnamn"));
        }

        // Close the variable pstmt and release all resources bound to it
        // Any ResultSet associated to the Statement will be automatically closed too.
        pstmt.close();
    }
}
