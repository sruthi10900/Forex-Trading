<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.forex.service.*"%>
<%@page import="com.forex.pojo.*" %>
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
		<title>Netting Page</title>
		<SCRIPT type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
		</SCRIPT>
	</head>
	<body onload="noBack();" 
		onpageshow="if (event.persis ted) noBack();" onunload="">
		<%
			String admin=(String)request.getSession().getAttribute("admin");
			if(admin == null){
				response.sendRedirect("login.jsp");
			}
		%>
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
		    <ul class="right hide-on-med-and-down">
		    	  <li><a  href="holidayAdmin.jsp">Holiday List</a></li>
			      <li class="active"><a href="netting.jsp">Netting</a></li>
			      <li><a href="tradeBlotter.jsp">Trade Blotter</a></li>
				  <li><a href="#">User: Admin</a></li>
				  <li><a href="logout.jsp">Logout</a></li>
			</ul>
		  </div>
		</nav>
		
		<div class="row">
			<form id="netting-form" action="NettingServlet" method="post">
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
                              <div class="input-field col s4">
					        	<input id="value-date" type="date" class="validate" name="value-date"> 	
                            </div>  
                            <div class="input-field col s1"></div> 
                             
						</div>
			         <!-- Second Row of Form -->
						<div class="row">
								<div class="form-field col s3">
								<input type="submit" class="btn-small red" name="submit"  value="Submit"></input>
								</div>
						</div>
																											
					</div>
				</div>	
				<%
				if((request.getAttribute("bool")!=null)){
					if((request.getAttribute("first")!=null)){
				%>
				<div class="card" style="margin-top: 50px;">
					<div class="card-action"></div>
					<div class="card-content">
				<div class="row">
					<div class="form-field col s9">
              			<p style="color:#cc9eaa"><b style="color: #a78aa6">CurrencyPair</b> <%=request.getAttribute("pair")%>&nbsp; <b style="color: #a78aa6">Date</b> <%=request.getAttribute("dat") %>  </p></br>      
              			<%
              			System.out.println("pujitha "+request.getAttribute("pair"));
              			String base=request.getAttribute("pair").toString().substring(0, 3);
              			String quote=request.getAttribute("pair").toString().substring(4, 7);
              			double first = Double.parseDouble((String)request.getAttribute("first"));
              			double second =Double.parseDouble((String)request.getAttribute("second"));
              			%>
              			<p style="color:#cc9eaa"><%=base%>&nbsp;
              			<%
              			if(first > 0)
              			{
              			%>            	
              			<p style="color: #67E567"><b style="color: #a78aa6">Receivable Amount</b>  <%=first %></p>
              			<%}else{%>
              			<p style="color: #ff0000"><b style="color: #a78aa6">Payable Amount</b>   <%=first %></p>
              			<%}
              			%>
              			<p style="color:#cc9eaa"><%=quote%>&nbsp;
              			<%
							if(second > 0)
			      			{
                       	%>
                       	<p style="color: #67E567"><b style="color: #a78aa6">Receivable Amount</b>  <%=second %></p>
              			<%}else{%>
              			<p style="color: #ff0000"><b style="color: #a78aa6">Payable Amount</b>   <%=second %></p>
              			<%}
							 request.setAttribute("bool", "");
		             		}
							}
		              			
              			%>
                       	
					</div>
					</div> 
				</div>
			</div>
													
			</div>
			</form>
			<!-- End of Order Form -->		
			<div id="result" class="col s1"></div>
				<div class="col s5">
					<% 
									if((request.getAttribute("bool")!=null)){
										
										if((request.getAttribute("first")!=null))
                                		{
                                			System.out.println("3");
                                			NettingService nettingService = new NettingService();
                                			String currencyPair=request.getAttribute("pair").toString();
                                			String dat=request.getAttribute("dat").toString();
           			%>   
                                			<div  class="scrollit">    
                                			<table>
						<tr style="font-size: 12px">
							<th>CID</th>
							<th>TYPE</th>
							<th>NOTIONAL</th>	
							<th>BUY CURRENCY</th>
							<th>RATE</th>
							<th>RETRUN CURRENCY</th>
							<th>RETURN VALUE</th>						
						</tr>												
						<%
						
						
						List<Order> orderList = nettingService.getListOfUsers(currencyPair,dat);
						   for (Order u : orderList) {
							   System.out.println("hello");
						%>
						<tr style="font-size: 10px">
						<td><%=u.getCid() %></td>
						<td><%=u.getT_ype()%></td>
						<td><%=u.getNotional_value()%></td>
						<td><%=u.getCurrency_pair().substring(0,3) %></td>
						<td><%=u.getRate() %></td>
						<td><%=u.getCurrency_pair().substring(4,7)%></td>
						<td><%=u.getNotional_value()*u.getRate()%></td>
						</tr>
						<%} %>
					</table>  
					</div>
                                			
                                		<%
                                		request.setAttribute("bool", "");}
                                		
									}
														
                                		%>
				
				</div>			
		</div>	 
	</body>
</html>