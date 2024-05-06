package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
//import scala.Int;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.List;


/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class SearchPage implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/SearchPage.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Countries + World</title>";

        // Add some CSS (external file)
       html = html + "<link rel='stylesheet' type='text/css' href='PageMission.css' />";
        html = html + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' />"; 

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
            html = html + """
                <div class='topnav'>
                    <a href='/'>Homepage</a>
                    <a href='mission.html'>Our Mission</a>
                    <a href='SearchPage.html'>Search Country</a>
                    <a href='SearchPageState.html'>Search Region/State</a>
                    <div class='dropdown'>
                    <button class='dropbtn'><i class="fa fa-bars" aria-hidden="true"></i></button>
                    <div class='dropdown-content'>
                        <a href='/'>Homepage</a>
                    <a href='mission.html'>Our Mission</a>
                    <a href='SearchPage.html'>Search Country</a>
                    <a href='SearchPageState.html'>World Data</a>
                    <a href='page3A.html'>Search Region/State</a>
                    </div>
                    </div>
                </div>
                
            """;



        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Countries + World</h1>
            </div>
        """;
        
        // Add Div for page Content
        html = html + "<div class='content'>";
         


        // Add HTML for the page content
        html = html + """
            
                    
            """;
             html = html + "<form method='GET'>";
        html = html + "<label for='region'>Select a region:</label>";
        html = html + "<select name='region' id='region'>";
        JDBCConnection jdbcConnection = new JDBCConnection();
        List<String> lstCountry = jdbcConnection.getUniqueCountry();
        for (int i=1;i<lstCountry.size();i++){
                html = html + "<option value="+ lstCountry.get(i) +">"+ lstCountry.get(i) +"</option>";
    }
        //html = html + "<option value='countries'>Countries</option>";
        html = html + "<option value='world'>World</option>";
        html = html + "</select>";
            html = html + "<label for='startYear'>Select a start year:</label>";
            html = html + "<select name='startYear' id='startYear'>";
            for (int year = 1750; year <= 2013; year++){
                html = html + "<option value= '" + year + "''>" + year + "</option>";
            }
            html = html + "</select>";

            html = html + "<label for='endYear'>Select an end year:</label>";
            html = html + "<select name='endYear' id='endYear'>";
            for (int year = 1750; year <= 2013; year++){
                html = html + "<option value= '" + year + "''>" + year + "</option>";
            }
            html = html + "</select>";

            html = html + "<input type='submit' value='Show Data'>";
            html = html + "</form>";
            
        // Get the selected region and year range from the query parameters
        String selectedRegion = context.queryParam("region");
        String startYear = context.queryParam("startYear");
        String endYear = context.queryParam("endYear");
         String country = context.queryParam("country");

        if (selectedRegion != null && startYear != null && endYear != null) {
            int globalStartYear = Integer.parseInt(startYear);
            int globalEndYear = Integer.parseInt(endYear);

            // Call the JDBCConnection to get the temperature data
            ArrayList<Double> temperatures;
            if (selectedRegion.equals("countries")) {
    temperatures = jdbcConnection.gettemperature_data_global(globalStartYear, globalEndYear, selectedRegion, country);
} else {
    temperatures = jdbcConnection.gettemperature_data_global(globalStartYear, globalEndYear, selectedRegion, null);
}

            // Display the temperature data
            html += "<p1>" + "Temperature for " + globalStartYear + " to " + globalEndYear + "</p1>";

            if (temperatures.isEmpty()) {
                html += "<p>No data available.</p>";
            } else {
                html += "<table>";
                html += "<tr><th>Year</th><th>Average Temperature</th><th>Minimum Temperature</th><th>Maximum Temperature</th></tr>";

                int year = Integer.parseInt(startYear);
                int index = 0;

                while (year <= Integer.parseInt(endYear)) {
                    html += "<tr>";
                    html += "<td>" + year + "</td>";
                    html += "<td>" + temperatures.get(index++) + "</td>";
                    html += "<td>" + temperatures.get(index++) + "</td>";
                    html += "<td>" + temperatures.get(index++) + "</td>";
                    html += "</tr>";
                    year++;
                }

                html += "</table>";
            }
        }
        
         
        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p></p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
    
    
    
}
