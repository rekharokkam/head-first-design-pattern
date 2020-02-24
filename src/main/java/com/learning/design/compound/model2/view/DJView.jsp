<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="beatModel" class="com.book.designpattern.compoundpattern.mvc.model.BeatModel" scope="request"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Model 2 Testing</title>
	</head>
	
	<body>
		<%-- Delegates the User actions to Servlet Controller along with FormData for processing. --%>
		<form action="/model2/controller/BeatController" method="get">
			<table border="2">
				<tr>
					<%--Extracts the Model's sthate information from the Javabean to build the User Interface --%>
					<td colspan="3">Beats per minute : <jsp:getProperty name="beatModel" property="BPM"/></td>
				</tr>
				
				<hr>
				<br><br>
				
				<tr>
					<td>BPM:</td>
					<td><input type="text" name="bpm" value='<jsp:getProperty name="beatModel" property="BPM"/>'></td>
					<td><input type="submit" name="operation" value="set"></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="submit" name="operation" value="decrease"> &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="operation" value="increase">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="submit" name="operation" value="on"> &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="operation" value="off">
					</td>
				</tr>
				
			</table>
		</form>
	</body>
</html>