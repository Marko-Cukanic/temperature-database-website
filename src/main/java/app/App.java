package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.charset.StandardCharsets;


import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;


/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the 
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class App {

    public static final int         JAVALIN_PORT    = 7001;
    public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7000
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            
            // Uncomment this if you have files in the CSS Directory
            config.addStaticFiles(CSS_DIR);

            // Uncomment this if you have files in the Images Directory
            config.addStaticFiles(IMAGES_DIR);
        }).start(JAVALIN_PORT);

        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        // All webpages are listed here as GET pages
        app.get(HomePage.URL, new HomePage());
        app.get(PageMission.URL, new PageMission());
        app.get(SearchPage.URL, new SearchPage());
        app.get(SearchPageState.URL, new SearchPageState());
        app.get(PageST3A.URL, new PageST3A());
        app.get(PageST3B.URL, new PageST3B());

        // Add / uncomment POST commands for any pages that need web form POSTS
         app.post(HomePage.URL, new HomePage());
         app.post(PageMission.URL, new PageMission());
         app.post(SearchPage.URL, new SearchPage());
         app.post(SearchPageState.URL, new SearchPageState());
         app.post(PageST3A.URL, new PageST3A());
         app.post(PageST3B.URL, new PageST3B());
    }

    public class ClimateProcessCSV {

        // MODIFY these to load/store to/from the correct locations
        private static final String DATABASE = "jdbc:sqlite:database/climate.db";
        private static final String CSV_FILE = "database/GlobalYearlyLandTempByCountry.csv";
     
     
        public static void main (String[] args) {
           // Load up the Date table
           // This only needs to be done once - uncomment this to reload the Date table
           loadYears();
     
           // Load the Country Temperature Observations
           loadCountryTemperatures();
     
           return;
        }
     
        public static void loadYears() {
           // JDBC Database Object
           Connection connection = null;
     
           // Like JDBCConnection, we need some error handling.
           try {
              connection = DriverManager.getConnection(DATABASE);
     
              for (int i = 1750; i != 2024; ++i) {
                 // Prepare a new SQL Query & Set a timeout
                 Statement statement = connection.createStatement();
     
                 // Create Insert Statement
                 String query = "INSERT into Date VALUES ("
                                + i
                                + ")";
     
                 // Execute the INSERT
                 System.out.println("Executing: " + query);
                 statement.execute(query);
              }
     
           } catch (Exception e) {
              e.printStackTrace();
           }
        }
     
        public static void loadCountryTemperatures() {
           // JDBC Database Object
           Connection connection = null;
     
           // We need some error handling.
           try {
              // Open A CSV File to process, one line at a time
              // CHANGE THIS to process a different file
              Scanner lineScanner = new Scanner(new File(CSV_FILE));
     
              // Read the first line of "headings"
              String header = lineScanner.nextLine();
              System.out.println("Heading row" + header + "\n");
     
              // Setup JDBC
              // Connect to JDBC database
              connection = DriverManager.getConnection(DATABASE);
     
              // Read each line of the CSV
              int row = 1;
              while (lineScanner.hasNext()) {
                 // Always get scan by line
                 String line = lineScanner.nextLine();
                 
                 // Create a new scanner for this line to delimit by commas (,)
                 Scanner rowScanner = new Scanner(line);
                 rowScanner.useDelimiter(",");
     
                 // Get all of the columns in order
                 String year = rowScanner.next();
                 String avgTemp = rowScanner.next();
                 String minTemp = rowScanner.next();
                 String maxTemp = rowScanner.next();
                 String rawCountryName = rowScanner.next();
     
                 // In this example, we don't have the population, so we'll leave that as zero for now
                 int population = 0;
                 
                 // Set a default country code
                 String countryCode = "ZZZZ";
     
                 // Some error handling
                 if (avgTemp.equals("")) {
                    avgTemp = "0";
                 }
                 if (minTemp.equals("")) {
                    minTemp = "0";
                 }
                 if (maxTemp.equals("")) {
                    maxTemp = "0";
                 }
     
                 // Convert any Latin1 encoded country names to UTF-8
                 String countryName = new String(rawCountryName.getBytes("ISO-8859-1"), "UTF-8");
                 // We now need to look-up the country code from the name
                 Statement statement = connection.createStatement();
                 String query = "SELECT * from Country WHERE CountryName = \"" + countryName + "\"";
                 System.out.println("Looking up: " + query);
                 ResultSet results = statement.executeQuery(query);
                 if(results.next()) {
                    countryCode = results.getString("CountryCode");
                 }
     
                 // Now we can insert the entry into the CountryTempObservation tabe
                 // Prepare a new SQL Query & Set a timeout
                 statement = connection.createStatement();
     
                 // Create Insert Statement
                 query = "INSERT into CountryTempObservation VALUES ("
                          + "'" + countryCode + "',"
                          + year + ","
                          + avgTemp + ","
                          + minTemp + ","
                          + maxTemp + ","
                          + population
                          + ")";
     
                 // Execute the INSERT
                 System.out.println("Executing: " + query);
                 statement.execute(query);
               }
     
            } 
           catch (Exception e) {
              e.printStackTrace();
            }
        }

    }
}
     
     
     


