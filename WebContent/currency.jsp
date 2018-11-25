<%@page import="com.forex.pojo.*" %>
<%@page import="com.forex.service.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
	<body>
			<table>
				<tr>
					<th>CURRENCY</th>
					<th>BID</th>
					<th>ASK</th>
					
				</tr> 
				<%
				
				
				Map <String,Double> bids = (Map<String,Double>)session.getAttribute("bids");
				Map <String,Double> asks = (Map<String,Double>)session.getAttribute("asks");
				
					CurrencyService orderService = new CurrencyService();
				   	List<CurrencyPair> list = orderService.getListOfCurrencies();
				   	
				   	for (CurrencyPair u : list) {
					    System.out.println(u.getCurrency_pair());
					
						Double bid_diff = bids.get(u.getCurrency_pair())-u.getBid();
						Double ask_diff = asks.get(u.getCurrency_pair())-u.getAsk_price();
						
						bids.put(u.getCurrency_pair(),u.getBid());
						asks.put(u.getCurrency_pair(),u.getAsk_price());
						String bidPrice = new DecimalFormat("##.##").format(u.getBid());
						String askPrice = new DecimalFormat("##.##").format(u.getAsk_price());
						int bidPip = (int)((u.getBid()-(Double.parseDouble(bidPrice)))*10000);
						int askPip = (int)((u.getAsk_price()-(Double.parseDouble(askPrice)))*10000);
				%>
				<tr>
				
					<td><%=u.getCurrency_pair() %></td>
					
					<%	if(bid_diff < 0){%>
						<td style="color:green;">
							<%= bidPrice%><font size="5"> <%=bidPip %> &uarr;</font>
					<% }else{%>
						<td style="color:red;">
							<%= bidPrice%><font size="5"> <%=bidPip %> &darr;</font>
					<%}%>
						</td>
											
					<%	if(ask_diff < 0){%>
						<td style="color:green;">
							<%= askPrice%><font size="5"> <%=askPip %> &uarr;</font>
					<% }else{%>
						<td style="color:red;">
							<%= askPrice%><font size="5"> <%=askPip %> &darr;</font>
					<%}%>
						</td>
				</tr>
				<%} %>
			</table> 
			<%
				session.setAttribute("bids",bids);
				session.setAttribute("asks",asks);
			%>
	</body>
	</body>
</html>