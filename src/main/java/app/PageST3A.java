package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    public static final String DATABASE_URL = "jdbc:sqlite:/climate.db";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='HomePage.css' />";
        html = html + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' />"; 
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
               <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='SearchPage.html'>Search Country</a>
                <a href='SearchPageState.html'>World Data</a>
                <a href='page3A.html'>View Data</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                <div class='dropdown'>
                <button class='dropbtn'><i class="fa fa-bars" aria-hidden="true"></i></button>
                <div class='dropdown-content'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='SearchPage.html'>Search Country</a>
                <a href='SearchPageState.html'>World Data</a>
                <a href='page3A.html'>View Data</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                </div>
                </div>
            </div>
            
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Subtask 3.A</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        

        html = html + "<h1>All Countries</h1>";

        // Add HTML for the page content
        html = html + """
            <p>Subtask 3.A page content</p>
            """;

        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
            </div>
        """;

        

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        

        
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public ArrayList<String> getClimate() {
        // Create the ArrayList of country names to return
        ArrayList<String> countryNames = new ArrayList<>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE_URL);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Country FROM Countries WHERE Year = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.println(query);



            // Set the query parameter
            //preparedStatement.setString(1, countryNames);

            // Get Result
            ResultSet results = preparedStatement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the country name
                String countryName = results.getString("country");

                // Add country name to the ArrayList
                countryNames.add(countryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return countryNames;
    }
        

}
