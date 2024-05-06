package app;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    //public static final String DATABASE = "jdbc:sqlite:database/ctg.db";
    public static final String DATABASE = "jdbc:sqlite:database/temperature.db";
    public final ArrayList<Double> gettemperature_data_global = null;

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     * @param end
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<Double> gettemperature_data_global(int start, int end, String selectedRegion, String country) {
        // Create the ArrayList of LGA objects to return
        ArrayList<Double> temperatures = new ArrayList<>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
           
            String query = "";
            // The Query
            if (selectedRegion.equals("countries")) {
            query = "SELECT * FROM temperature_data_country WHERE year BETWEEN '" + start + "' AND '" + end + "'";
        } else {
            query = "SELECT * FROM temperature_data_global WHERE year BETWEEN '" + start + "' AND '" + end + "'";
        }

        ResultSet resultSet = statement.executeQuery(query);
            // Process all of the results
             while (resultSet.next()) {
            double averagetemperature = resultSet.getDouble("Averagetemperature");
            double minimumtemperature = resultSet.getDouble("Minimumtemperature");
            double maximumTemperature = resultSet.getDouble("maximumTemperature");

            // Add the temperature values to the list
            temperatures.add(averagetemperature);
            temperatures.add(minimumtemperature);
            temperatures.add(maximumTemperature);
        }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return temperatures;
    }

    public ArrayList<TemperatureData> getTemperatureDataState(int start, int end, String country, String selectedRegion) {
        ArrayList<TemperatureData> temperatureData = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            statement = connection.createStatement();

            String query = "SELECT * FROM temperature_data_state WHERE year BETWEEN " + start + " AND " + end;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                float averageTemperature = resultSet.getFloat("AverageTemperature");
                float minimumTemperature = resultSet.getFloat("MinimumTemperature");
                float maximumTemperature = resultSet.getFloat("MaximumTemperature");
                country = resultSet.getString("Country");
                String state = resultSet.getString("State");
                int year = resultSet.getInt("Year");

                TemperatureData data = new TemperatureData(year, averageTemperature, minimumTemperature, maximumTemperature, country, state);
                temperatureData.add(data);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return temperatureData;
    }
    
public ArrayList<tempdatacountry> getTemperatureDataCountry(int start, int end, String country) {
    ArrayList<tempdatacountry> temperatureDatacountry = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;

    try {
        connection = DriverManager.getConnection(DATABASE);
        statement = connection.createStatement();
        String query = "SELECT * FROM temperature_data_country WHERE year BETWEEN " + start + " AND " + end + " AND Country = '" + country + "'";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            float AverageTemperature = resultSet.getFloat("AverageTemperature");
            float MinimumTemperature = resultSet.getFloat("MinimumTemperature");
            float MaximumTemperature = resultSet.getFloat("MaximumTemperature");
            String Country = resultSet.getString("country");
            int year = resultSet.getInt("year");

            tempdatacountry data = new tempdatacountry(year, AverageTemperature, MinimumTemperature, MaximumTemperature, Country);
            temperatureDatacountry.add(data);
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return temperatureDatacountry;
}


    public ArrayList<String[]> getPopulation(int year, int population) {
    ArrayList<String[]> populationList = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;

    try {
        connection = DriverManager.getConnection(DATABASE);
        statement = connection.createStatement();
        String query = "SELECT Year, Population FROM Population";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String[] row = new String[2];
            row[0] = resultSet.getString("Year");
            row[1] = resultSet.getString("Population");
            populationList.add(row);
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return populationList;
}
 
public ArrayList<TemperatureData> getTemperatureDataCity(int start, int end, String country, String selectedRegion) {
        ArrayList<TemperatureData> temperatureData = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            statement = connection.createStatement();

            String query = "SELECT * FROM temperature_data_city WHERE year BETWEEN " + start + " AND " + end;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                float averageTemperature = resultSet.getFloat("AverageTemperature");
                float minimumTemperature = resultSet.getFloat("MinimumTemperature");
                float maximumTemperature = resultSet.getFloat("MaximumTemperature");
                country = resultSet.getString("Country");
                String city = resultSet.getString("City");
                int year = resultSet.getInt("Year");

                TemperatureData data = new TemperatureData(year, averageTemperature, minimumTemperature, maximumTemperature, country, city);
                temperatureData.add(data);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return temperatureData;
    }

    public List<String> getUniqueCountry() {
        List<String> lstCountry = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            statement = connection.createStatement();

            String query = "SELECT DISTINCT Country FROM temperature_data_country  ";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String country = resultSet.getString("Country");
                lstCountry.add(country);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return lstCountry;
    }
}

