<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
   <%@page import="com.forex.pojo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name = "viewport" content = "width = device-width, initial-scale = 1">      
    <link rel = "stylesheet"
       href = "https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel = "stylesheet"
       href = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
    <script type = "text/javascript"
       src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>           
    <script src = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js">
    </script>
    
    <style>
#footer {
   position: fixed;
   bottom: 0;
   width: 100%;
}

nav {
		    color: #fff;
		    background-color:  #F44336 !important;
		    width: 100%;
		    height: 56px;
		    line-height: 56px;
		}
    
    </style>
<script type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
		</script>
	</head>
	<body onload="noBack();" 
		onpageshow="if (event.persis ted) noBack();" onunload="">
        
<!-- navbar -->

		<nav>
		   <div class="nav-wrapper">
		       <a href="#" class="brand-logo">&nbsp;<img src="images/logo.png" alt="Home" width="60" height="50"></a>
		     <ul id="nav-mobile" class="right hide-on-med-and-down">
		     	<li><a  href="holidayClient.jsp">Holiday List</a></li>
		       	<li><a  href="order.jsp">Trade</a></li>
		       	<li><a  href="orderBlotter.jsp">Order Blotter</a></li>
		       	<li><a class="active" href="#">Profile</a></li>
		       	<li><a href="logout.jsp">logout</a></li>
		     </ul>
		   </div>
		 </nav>
 <%
 Client client=(Client) request.getSession().getAttribute("client");
	if(client == null){
		response.sendRedirect("login.jsp");
	}
	
 
 %>
 
    <div style="border-top-width: 20">
        <div class="container col s6" style="border-top-width: 20%">
            <ul class = "collapsible popout" data-collapsible = "accordion">
                <li>
                   <div class = "collapsible-header">
                    <i class="material-icons prefix">account_circle</i>Personal Info</div>
                   <div class = "collapsible-body">
                       <div class="container">
                        <table class="table">
                            <thead>
                            <tr class="info">
                                <th>Name</th>
                                <td><%=client.getUsername() %>
                            </tr>
                            
                            <tr class="success">
                                <th>Email</td>
                                <td><%=client.getEmailId() %></td>
                            </tr>      
                            <tr class="danger">
                                <td>Phone Number</td>
                                <td><%=client.getPhoneNo()%></td>
                            </tr>
                            </thead>    
                        </table>
                    </div>
                   </div>
                </li>
            
                <li>
                   <div class = "collapsible-header">
                      <i class = "material-icons prefix">account_balance</i>Bank Info</div>
                   <div class = "collapsible-body"><div class="container">
                        <table class="table">
                            <thead>
                            <tr class="info">
                                <th>Bank Number</th>
                                <td><%=client.getBankNo() %>
                            </tr>
                            </thead>    
                        </table>
                    </div>
                  </div>
                </li>
             </ul>
         </div>
      </div>
    </body>
</html>