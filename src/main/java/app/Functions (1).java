package app;

class Nav {

    public static String NavigationBar() 
    {
        String html = "<div class = topnav'>";
        html = html + "<a href='/'>Homepage</a>";
        html = html + "<a href='mission.html'>Our Mission</a>" ;
        html = html + "<a href='SearchPage.html'>Search Country</a>";   
        html = html + "<a href='SearchPageState.html'>Search Region/State</a>";   
        html = html + "<a href='page3A.html'>Sub Task 3.A</a>";
        html = html + "<a href='page3B.html'>Sub Task 3.B</a>";
        html = html + "<div class='dropdown>'";
        html = html + "<button class='dropbtn'><i class=\"fa fa-bars\" aria-hidden=\"true\"></i></button>";
        html = html + "<div class='dropdown-content'>";
        html = html + "<a href='/'>Homepage</a>";
        html = html + "<a href='mission.html'> Our Mission</a>";
        html = html + "<a href='SearchPage.html'>Search Region</a>";
        html = html + "<a href='SearchPageState.html'>Search Region/State</a>";
        html = html + "<a href='page3A.html'>Sub Task 3.A</a>";
        html = html + "<a href='page3B.html'>Sub Task 3.B</a>";
        html = html + "</div></div></div>";

        // Your code here
        return html;
    }

}