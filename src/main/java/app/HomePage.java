package app;

import java.util.ArrayList;

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
public class HomePage implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>TempClimate Homepage</title>";
               

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='HomePage.css' />";
       html = html + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' />"; 
        html = html + "</head>";    
        


        // Add the body
       /*  html = html + "<Test body>"; */ 

        // Add the topnav
        // <html>
// <head>
//   <link rel="stylesheet" type="text/css" href="common.css">
// </head>
// <body>

// <div class="big_wrapper">
//   <a href="#" class="nav">
//     <div class="one"></div>
//     <div class="two"></div>
//     <div class="one"></div> 
//   </a>
// </div>

// </body>
// </html>

        // This uses a Java v15+ Text Block
        /*  html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='SearchPage.html'>Search Region</a>
                <a href='page2B.html'>Sub Task 2.B</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                <div class='dropdown'>
                <button class='dropbtn'><i class="fa fa-bars" aria-hidden="true"></i></button>
                <div class='dropdown-content'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Mission</a>
                <a href='SearchPage.html'>Search Region</a>
                <a href='page2B.html'>Sub Task 2.B</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                </div>
                </div>
                

            </div>
            
        """; */
               
    html = html + NavigationBar();

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>
                    TempClimate
                </h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
            <p>Homepage content</p>
            """;
//var = user input
        // Get the ArrayList of Strings of all LGAs
       

        // Add HTML for the LGA list
        html = html + "<h1>Welcome To TempClimate </h1>" + "<ul>"; 
        html += "<img src='tree.avif' alt='Image' style='display: block; margin: 0 auto'>";
        html += "</ul>";
        html += """
                <div style= 'txt=align: right;'>
                <h2>About Climate Change</h2>
                <p>Climate change is the long-term changes in Earth's temperature patterns and weather. Human activities such as the release of glasshouse gases into the atmosphere are the main cause. </p>
                <p></p>
                <p>The glass house effect is caused by gases like carbon dioxide (CO2) and methane (CH4) that trap solar heat inside the Earth's atmosphere.</p>
                <h2></h2>
                </div>
                """;
        
        

        // Finally we can print out all of the LGAs
        

        // Finish the List HTML
        html = html + "</ul>";

        // Close Content div
        html = html + "</div>";
        
        // Footer
        html = html + """
            <div class='footer'>
                <p style=\"font-size:40px; font-family:Helvetica\">Contact Information</p>
                <ul class=\"socials\">
                <li><a href=\"https://www.facebook.com/profile.php?id=100093128210837\"><img src=\"facebook.svg\" alt=\"Facebook\" width=\"30px\" height=\"30px\"></a></li>
                <li><a href=\"https://www.instagram.com/temperatureclimate101/\"><img src=\"instagram.svg\" alt=\"Instagram\" width=\"30px\" height=\"30px\"></a></li>
                <li><a href=\"https://twitter.com/temperatur9997\"><img src=\"twitter.svg\" alt=\"Twitter\" width=\"30px\" height=\"30px\"></a></li>
                </ul>
                <p>Thank you for visiting TempClimate!</p>
                <p>Copyright . 2023 TempClimate. Designed by Harshitha</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }


    /**
     * Get the names of the LGAs in the database.
     */
    
    public static String NavigationBar() 
    {
        String html = """
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
                <a href='SearchPageState.html'>Search Region/State</a>
                </div>
                </div>
                

            </div>
            
        """;
      

        // Your code here
        return html;
    }
}
