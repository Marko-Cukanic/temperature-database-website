package app;

import java.util.ArrayList;

import app.App.ClimateProcessCSV;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Our Mission</title>";

        // Add some CSS (external file)
       html = html + "<link rel='stylesheet' type='text/css' href='PageMission.css' />";
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
                <h1>Our Mission</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
            <p></p>
            """;

        // This example uses JDBC to lookup the LGAs
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the LGAs
      //  ArrayList<ClimateProcessCSV> climates = jdbc.getClimate();

        // Add HTML for the list
        html = html + "<h1>Creators of the Website: Marko Cukanic and Harshitha Chandra Kumar</h1>" + "<ul>";

        // Finally we can print out all of the LGAs
     //   for (ClimateProcessCSV climate : climates) {
           // html = html + "<li>" + climates.getCode()
         //               + " - " + climates.getName() + "</li>";
        

        // Finish the List HTML
        html = html + "</ul>";


        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p></p>
            </div>
        """;

        html = html + """
                <div class='personas'>
                <img src = "0b7f4e9b-f59c-4024-9f06-b3dc12850ab7-1920-1080.jpg">
                <img src = "womanPersona.jpg">
                <img src = "Womanpersona2.jpg">
                </div>  
                """;

        html = html + """
                <div class= 'container'>
                <p> Kim Callisto: is 40 years and is married, he currently works as a doctor at Mercy Hospital in Melbourne and lives in the city. He does not suffer from any major health issues and invests into smaller businesses. His needs consist of receiving a break from work to focus on fitness more, to sleep at least eight hours per day and to learn about how climate change is affecting him and the world. He hears about how climate change might affect his daily activities from conversations while he is on the tram. His family had decided to move away from the city into a suburb far from the city, so currently he lives on his own. He also sees ads in the city advocating for climate change and is curious about Climate change. He owns multiple monitors and smart devices and has never thought about the electrical consumption of those devices upon the environment. Currently, he has no experience on climate change and wishes to become knowledgeable enough, so he can also help to save the environment. He wants to find out data relating to climate change and just have to look through to get a feel of what he was looking at. He does not have experience navigating through a website with graphs.
                
                </div>
                """;
        html = html + """
            <div class= 'container2'>
                </p>
                <p> Elizabeth Rustichel: is 30 years old and single. She works as a social worker and is currently looking for land to build a house. She has a dog named Bell and writes books in her free time, she is hoping to write a newspaper about climate change to inform the public. She goes to the gym four times a week and like rock music, especially Baby Metal. She likes to shop at Myers and does not like the winter season. One of goals is to build a fruit and vegetable farm to save energy, as she heard climate change is getting worse each year. She needs to save money as much as possible to save u to purchase a house. She wants to live independently and confidently. She wants to get the right amount of sleep every night, so she does not wake up tired and grumpy for work. Moreover, she wants to purchase good quality solar panels when she builds the house to save energy costs. Elizabeth is competent with web browsing and is able to manage her work lifestyle and leisure lifestyle. She reads articles about climate change and ways to reduce it, hence making a document about the ways to reduce it and wishes to implement it when she gets a new house. Elizabeth wants to find a website where she can research about climate change data and add it to her word document as evidence. 
                </p>

                </div>
                """;
        html = html + """
            <div class= 'container3'>
                </p>
                <p> Sakura Eckhart: is 25 years old and single. She works as a geoscientist and has a pet dog. She currently lives in a share house with people who want to save the planet from global warming, and some of them are geoscientists as well. On every Friday, they get together to discuss climate change and the issues it causes globally, specific countries, cities and states. She goes to K-pop concerts and drinks coffee every day to keep herself awake. She tries to manage her savings well. She wants to join more protests about climate change, where she can make more friends who are passionate about climate change and letting them join her group to get more ideas about ways to prevent climate change from getting worse. She needs to save money to buy more solar panels. She wants to donate to climate change awareness charities. She wants to make videos dedicating to climate change so she can attract more people to support the cause. Sakura knows how to use the internet well. She reads about climate change every day while drinking her morning coffee. She knows how to recycle all sorts of items. She has volunteered in a climate change charity. She knows how to interpret a graph. Sakura wishes to find a website that shows her the data of the world, countries, cities and states. 
                </p>

                </div>
                """;



        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
