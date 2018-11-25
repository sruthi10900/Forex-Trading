<%@page import="java.util.List"%>
<%@page import="com.forex.service.TradeBlotterService"%>
<%@page import="java.util.Date"%>
<%@page import="com.forex.pojo.Trans_board"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
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
		
		
		<title>Dashboard</title>
		
		
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

	
	<style>
		.card {
		  overflow: visible;
		}
			.scrollit {
	    overflow:scroll;
	    height:450px;
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
		    <ul class="right hide-on-med-and-down">
		    	<li><a  href="holidayAdmin.jsp">Holiday List</a></li>
		      	<li><a href="netting.jsp">Netting</a></li>
		      	<li class="active"><a href="tradeBlotter.jsp">Trade Blotter</a></li>
			  	<li><a href="#">User: Admin</a></li>
			  	<li><a href="logout.jsp">Logout</a></li>
			</ul>
		  </div>
		</nav>
		
</head>
<script type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
		</script>
		<script type="text/javascript">
			    function doRefresh(){
			        $("#show").load("http://localhost:9600/forex/currency.jsp");
			    }
			    $(function() {
			        setInterval(doRefresh, 5000);
			    });
			</script> 
	</head>
	<body onload="noBack();" 
		onpageshow="if (event.persis ted) noBack();" onunload="">
	<%
		String admin=(String)request.getSession().getAttribute("admin");
		if(admin == null){
			response.sendRedirect("login.jsp");
		}
	%>
<div class="row">
<div class="col s6">
<div class="row">
<form action="TradeBlotterServlet" method="post">
<div class="input-field col s6">
					        	<input id="value-date" type="date" class="validate" name="value-date" required> 	
                            </div> 
                            </div>
                            <div class="row">
                            <div class="form-field col s6">
								<input type="submit" class="btn-small red" name="submit"  value="Submit"></input>
								</div>
								<div class="row"></div>
								
								  <div class="row">
								  
         <% 
System.out.println("before");
if((request.getAttribute("date")!=null)){
	System.out.println("after : "+request.getAttribute("date"));

%>
         <div class="col s12">
         <div class="scrollit">
          <table>
           
                 <tr>
                     <th>TRANS ID</th>
                     <th>COMMISSION</th>
                     <th>CURRENCY</th>
                     <th>TRANSACTION DATE</th>
                     <th>VOLUME</th>
                                     
                 </tr>
         
                 <%
                          	TradeBlotterService transService = new TradeBlotterService();
                                               List<Trans_board> list = transService.getTransByDate(request.getAttribute("date").toString());
                                               for (Trans_board u : list) {
                                            	   System.out.println("this is for checking "+  u.getCurrency_pair());
                          %>
                 <tr>
                     <td><%=u.getTid()%></td>
                     <td><%=u.getCommission()%></td>
                     <td><%=u.getCurrency_pair()%></td>
                     <td><%=u.getTrans_date()%></td>
                     <td><%=u.getVolume()%></td>
                 </tr>
                 <%}%>
           
         </table>    
         </div>
         </div>
         <%} %>
         <br/>
     
    
     
		</div>
  </div>
				
</form>
</div>
<div class="col s6"></div>
     <div class="col s1"></div>
			
				<div class="col s5">
					<!-- <table>
						<tr>
							<th>CURRENCY</th>
							<th>BID</th>
							<th>ASK</th>
							
						</tr>
						<tr>
							<td>INR/USD</td>
							<td>1.234</td>
							<td>1.235</td>
						</tr>
						<tr>
							<td>INR/USD</td>
							<td>1.234</td>
							<td>1.235</td>
						</tr>
						<tr>
							<td>INR/USD</td>
							<td>1.234</td>
							<td>1.235</td>
						</tr>
						<tr>
							<td>INR/USD</td>
							<td>1.234</td>
							<td>1.235</td>
						</tr>
						<tr>
							<td>INR/USD</td>
							<td>1.234</td>
							<td>1.235</td>
						</tr>
						<tr>
							<td>INR/USD</td>
							<td>1.234</td>
							<td>1.235</td>
						</tr>
						<tr>
							<td>INR/USD</td>
							<td>1.234</td>
							<td>1.235</td>
						</tr>
					
					</table> -->
					<%@include file="currency.jsp" %>
				</div>
			</div>
			
</div>
			


   
     
     </body>
     </html>

