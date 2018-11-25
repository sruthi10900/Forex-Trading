<%@page import="java.util.List"%>
<%@page import="com.forex.service.*"%>
<%@page import="java.util.Date"%>
<%@page import="com.forex.pojo.Trans_board"%>
<%@page import="com.forex.pojo.Order"%>
<%@page import="com.forex.pojo.Client"%>
<%@ page import="com.forex.service.*"%>
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
			  <!-- <link type="text/css" rel="stylesheet" href="materialize.css"  media="screen,projection"/> -->
		
		      <!--Let browser know website is optimized for mobile-->
		      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
			  <meta charset="ISO-8859-1">
			  <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
			  
			<!-- jQuery library -->
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			
			<!-- Latest compiled JavaScript -->
			<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<title>Order Blotter</title>
		
<script type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
		</script>
	</head>
	<body onload="noBack();" 
		onpageshow="if (event.persis ted) noBack();" onunload="">
		<!-- <script type="text/javascript"  src="jquery.min.js"></script>
		<script type="text/javascript" src="materialize.js"></script>
		 -->
		<style>
			body {font-family: Arial, Helvetica, sans-serif;}

			/* The Modal (background) */
			.modal {
				display: none; /* Hidden by default */
				position: fixed; /* Stay in place */
				z-index: 1; /* Sit on top */
				padding-top: 100px; /* Location of the box */
				left: 0;
				top: 0;
				width: 100%; /* Full width */
				height: 100%; /* Full height */
				overflow: auto; /* Enable scroll if needed */
				background-color: rgb(0,0,0); /* Fallback color */
				background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
			}

			/* Modal Content */
			.modal-content {
				background-color: #fefefe;
				margin: auto;
				padding: 20px;
				border: 1px solid #888;
				width: 80%;
			}

			/* The Close Button */
			.close {
				color: #aaaaaa;
				float: right;
				font-size: 28px;
				font-weight: bold;
			}

			.close:hover,
			.close:focus {
				color: #000;
				text-decoration: none;
				cursor: pointer;
			}

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
			 	<li><a  href="holidayClient.jsp">Holiday</a></li>
			   	<li><a  href="order.jsp" >Trade</a></li>
			   	<li  class="active"><a  href="orderBlotter.jsp">Order Blotter</a></li>
			   	<li><a  href="profile.jsp">Profile</a></li>
			   	<li><a href="logout.jsp">logout</a></li>
			 </ul>
		   </div>
		 </nav>	
	</head>
	<body>
	<%
		Client client=(Client) request.getSession().getAttribute("client");
		if(client == null){
			response.sendRedirect("login.jsp");
		}
	%>
		 <div class="row">
		 <div class="col s1"></div>
			 <div class="col s10">
			 <div class="scrollit">
			  <table border="1px">
					<thead>
						 <tr>
							 <th>ORDER ID</th>
							 <th>CUSTOMER ID</th>
							 <th>CURRENCY PAIR</th>
							 <th>ORDER DATE</th>
							 <th>VALUE DATE</th>
							 <th>NOTIONAL VALUE</th>
							 <th>RATE</th>
							 <th>STATUS</th>
							 <th>TYPE</th>								 
						 </tr>
					</thead>
					<tbody>
						 <%
						Client cli=(Client) request.getSession().getAttribute("client");
						 System.out.println("Client: "+cli.getClientId());
							if(cli == null){
								response.sendRedirect("login.jsp");
							}
							
						   OrderBlotterService orderService = new OrderBlotterService();
						   List<Order> list = orderService.getListOfOrders(cli.getClientId());
						   for (Order u : list) {
						 %>
						<tr style="font-size: 10px" align="center" >
							 <td ><%=u.getOrderid()%></td>
							 <td><%=u.getCid()%></td>
							 <td><%=u.getCurrency_pair()%></td>
							 <td><%=u.getOrder_date()%></td>
							 <td><%=u.getValue_date()%></td>
							 <td><%=u.getNotional_value()%></td>
							 <td><%=u.getRate()%></td>
							 <td><%=u.getStatus()%></td>
							 <td><%=u.getT_ype()%></td>
							 <td>
							<%
							String k=u.getStatus().toString();
							
						
							if(k.equals("pending")){
								 %>
								 
								<button class='btn btn-xs edit btn-edit' id="abc">Edit</button>
								
							 </td>
							 
								
						 </tr>
						 <% }}%>
					</tbody>
			   </table>  
			   </div>
			</div>	
		  </div>
		<div class="modal fade" id="dummyModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit your Order Details</h4>
					</div>
					<div class="modal-body">
						<form action="OrderModifyServlet" method="post">
							 Order ID: <input type="text" class="input-sm" id="orderId" name="orderId" readonly="readonly"/>
							Notional Value: <input type="text" class="input-sm" id="notionalValue" name="notionalValue" /> 
					</div>
					<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<input type="submit" id="btnsubmit" class="btn btn-primary" value="Update">
						</form>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
        <script type="text/javascript">
        $('document').ready(function() {
        	var options = {
                    "backdrop" : "static",
                    "show":true
                }
        	
        		//trail
				$('.btn-edit').click(function() {
					
					console.log("eneterd edit button");
					$("#orderId").val($(this).closest('tr').children()[0].textContent);
					$("#notionalValue").val($(this).closest('tr').children()[5].textContent);
					console.log($(this).closest('tr').children()[5].textContent);
					$('#dummyModal').modal(options);
				});
    	});
        </script> 
    </body>
    </html>