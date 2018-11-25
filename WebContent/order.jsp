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
		<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
		      <!--Let browser know website is optimized for mobile-->
		      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		
		<meta charset="ISO-8859-1">
		
		
		<title>Order Page</title>
		
		
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
	</head>
	<script type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
		</script>
	</head>
	<body onload="noBack();" 
		onpageshow="if (event.persis ted) noBack();" onunload="">
	
		
		<style>
		.card {
		  overflow: visible;
		}
		
		nav {
		    color: #fff;
		    background-color:  #F44336 !important;
		    width: 100%;
		    height: 56px;
		    line-height: 56px;
		}
		</style>
	
	<%
	Client client=(Client) request.getSession().getAttribute("client");
	if(client == null){
		response.sendRedirect("login.jsp");
	}
	
	%>
		<nav>
		   <div class="nav-wrapper">
		     <a href="#" class="brand-logo">&nbsp;<img src="images/logo.png" alt="Home" width="60" height="50"></a>
		     <ul id="nav-mobile" class="right hide-on-med-and-down">
		     	<li><a  href="holidayClient.jsp">Holiday List</a></li>
		       	<li class="active"><a  href="order.jsp" >Trade</a></li>
		       	<li><a  href="orderBlotter.jsp">Order Blotter</a></li>
		       	<li><a  href="profile.jsp">Profile</a></li>
		       	<li><a href="logout.jsp">logout</a></li>
		     </ul>
		   </div>
		 </nav>
	        
	        
	
	   
		<div class="row">
	
	
			<form id="order-form" action="OrderServlet" method="post">
			<div class="col s6">
				
				<div class="card" style="margin-top: 50px;">
					<div class="card-action"></div>
					<div class="card-content">
					<!-- First row of form -->
						<div class="row">
							 <div class="input-field col s3">
							    <select class="dropdown-button btn" name="currency-pair">
							    <option value="" disabled selected>Currency</option>
							      <option value="EUR/USD">EUR/USD</option>
							      <option value="EUR/GBP">EUR/GBP</option>
							       <option value="EUR/CHF">EUR/CHF</option>
							      <option value="GBP/USD">GBP/USD</option>
							       <option value="EUR/AUD">EUR/AUD</option>
							      <option value="GBP/NZD">GBP/NZD</option>
							       <option value="NZD/CAD">NZD/CAD</option>
							      <option value="TRY/JPY">TRY/JPY</option>  
							    </select> 
							 </div>
						 	 <div class="input-field col s1"></div> 
						   	 <div class="input-field col s6">
					          <input id="volume" type="text" class="validate" name="volume" required>
					          <label for="volume">volume</label>
					         </div>     
						</div>
			         <!-- Second Row of Form -->
						 <div class="row">
					        <div class="input-field col s4">
					          <input disabled value="SPOT" id="disabled" type="text" class="validate">
					          <label for="disabled">Order Type</label>
					        </div>  
					        <div class="input-field col s4">
					        	
					        	<input id="value-date" class="validate" name="value-date" disabled> 
					        	
					        </div>
					      </div>
						
						
						<!--  Third Row-->
						
						<div class="row">
								<div class="form-field col s3">
									<select class="dropdown-button btn" name="type">
								    <option value="buy">Buy</option>
								     <option value="sell">Sell</option>
								    </select>
								</div>
								<div class="form-field col s3"></div>
								<div class="form-field col s6">
								<input type="submit" class="btn-large red" name="submit"  value="Order"></input>
								</div>
						</div>		
					</div>
				</div>
			
			</div>
			</form>
			<!-- End of Order Form -->
	
		
			<div class="col s1"></div>
				<div class="col s5"  id="show">
					<%@ include file = "currency.jsp" %>		
				</div>
			</div>
			
			<script>
				var d = new Date();
				document.getElementById("value-date").value = "Order Date: "+d.toDateString();
				System.out.println(d);
			</script>
			
			<script type="text/javascript">
			    function doRefresh(){
			        $("#show").load("http://localhost:9600/forex/currency.jsp");
			    }
			    $(function() {
			        setInterval(doRefresh, 5000);
			    });
			</script> 
	
	</body>
</html>

