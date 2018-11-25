<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.forex.pojo.*" %>
    <%@page import="com.forex.service.*" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		 <!-- Compiled and minified CSS -->
		    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css">		
		    <!-- Compiled and minified JavaScript -->
		    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>
		 <!--Import Google Icon Font-->
		      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		      <!--Import materialize.css-->
		      <link type="text/css" rel="stylesheet" href="materialize.css"  media="screen,projection"/>		
		      <!--Let browser know website is optimized for mobile-->
		      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>		
		<meta charset="ISO-8859-1">				
		<title>Order Page</title>
		
	<script type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
		</script>
	</head>
	<body onload="noBack();" 
		onpageshow="if (event.persis ted) noBack();" onunload="">
		
		<script type="text/javascript">
			$( document ).ready(function() {
				$('select').not('.disabled').material_select();
			  });		
			document.addEventListener('DOMContentLoaded', function() {
				var elems = document.querySelectorAll('.modal');
				var instances = M.Modal.init(elems, opacity);
			  });		
		</script>
		<script type="text/javascript"  src="jquery.min.js"></script>
		<script type="text/javascript" src="materialize.js"></script>
		<script>
		</script>
	
	<%
		Client client=(Client) request.getSession().getAttribute("client");
		if(client == null){
			response.sendRedirect("login.jsp");
		}
	%>
		<style>
		.card {
		  overflow: visible;
		}
				.scrollit {
	    overflow:scroll;
	    height:550px;
	    }
	    nav {
		    color: #fff;
		    background-color:  #F44336 !important;
		    width: 100%;
		    height: 56px;
		    line-height: 56px;
		}
		</style>
		<nav>
		   <div class="nav-wrapper">
		       <a href="#" class="brand-logo">&nbsp;<img src="images/logo.png" alt="Home" width="60" height="50"></a>
		     <ul id="nav-mobile" class="right hide-on-med-and-down">
		     	<li  class="active"><a  href="holidayClient.jsp">Holiday List</a></li>
		       	<li><a  href="order.jsp" >Trade</a></li>
		       	<li><a  href="orderBlotter.jsp">Order Blotter</a></li>
		       	<li><a  href="profile.jsp">Profile</a></li>
		       	<li><a href="logout.jsp">logout</a></li>
		     </ul>
		   </div>
		 </nav>
		 
		 
		 
<div class="row">	
	<form id="order-form" action="HolidayClientServlet" method="post">
	<div class="col s6">				
		<div class="card" style="margin-top: 50px;">
			<div class="card-action"></div>
				<div class="card-content">
					<!-- First row of form -->
						<div class="row">
							 <div class="input-field col s4">
							    <select class="dropdown-button btn" name="currency1" id="mySelect">
							    <option value="" disabled selected>Currency</option>
							      <option value="EUR">EUR</option>
							      <option value="GBP">GBP</option>
							       <option value="CHF">CHF</option>
							      <option value="USD">USD</option>
							       <option value="AUD">AUD</option>
							      <option value="NZP">NZP</option>
							       <option value="CAD">CAD</option>
							      <option value="JPY">JPY</option>  
							    </select> 
							 </div>
							 <div class="input-field col s4">
							    <select class="dropdown-button btn" name="currency2" id="mySelect">
							    <option value="" disabled selected>Currency</option>
							      <option value="EUR">EUR</option>
							      <option value="GBP">GBP</option>
							       <option value="CHF">CHF</option>
							      <option value="USD">USD</option>
							       <option value="AUD">AUD</option>
							      <option value="NZP">NZP</option>
							       <option value="CAD">CAD</option>
							      <option value="JPY">JPY</option>  
							    </select> 
							 </div>
							 
							 </div>						 
							 <div class="row">
							 <button type="submit" class="btn-small red">SUBMIT</button>
							 </div>
							 </div>
				</div>
			</div>
			</form>
			
				<div class="col s6"></div>
				
				<div class="col s6">
				<div class="scrollit">
				<table>					
						<%
						if((request.getAttribute("cur1")!=null)){
						//String cur=request.getParameter("currency_pair");
						//String cur=request.getAttribute("cur").toString();
						%>
						<tr>
							<th>CURRENCY_PAIR</th>
							<th>DATE</th>
						</tr>
						<%
						HolidayService orderService = new HolidayService();
						   List<Holidays> list = orderService.getListOfHolidays(request.getAttribute("cur1").toString(),request.getAttribute("cur2").toString());
						   for (Holidays u : list) {
							   System.out.println("hello "+u.getHoliday_name() );
						%>
						<tr>
						<td><%=u.getCurrency() %></td>
						<td><%=u.getHoliday_date()%></td>
						<td><%=u.getHoliday_name()%></td>
						
						</tr>
						<%}
						   } 
						%>
					</table>
					</div>
					</div> 
					</div>
	</body>
</html>