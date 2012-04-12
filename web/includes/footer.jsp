<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    // initialize the current year that's used in the copyright notice
    Date currentDate = new Date();
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DateFormat shortDate = DateFormat.getDateInstance(DateFormat.LONG);
    String currentDateFormatted = shortDate.format(currentDate);
%>

</font>
<br><br><br>
<div id="footer">
    <small>
        <%= currentDateFormatted %> <br>
        Term Project for CMPT315 by Zhengyi Liang in <%= currentYear %> 
        All rights reserved
    </small>
</div>
</body>
</html>