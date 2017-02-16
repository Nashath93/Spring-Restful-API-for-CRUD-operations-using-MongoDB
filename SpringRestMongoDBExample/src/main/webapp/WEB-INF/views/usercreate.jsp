<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${message-title}</title>
</head>
<body>
	<h2>${messageheading}</h2>
	<%-- <f:form method="POST" action="addTraveller">
		<table>
			<tbody>
				<tr>
					<td>Id: </td>
					<td><f:input path="id"/></td>
				</tr>
				<tr>
					<td>Traveller Name: </td>
					<td><f:input path="travelerName"/></td>
				</tr>
				<tr>
					<td>Traveller Country: </td>
					<td><f:input path="travellerCountry"/></td>
				</tr>
				<tr>
					<td>Traveller Passport Number: </td>
					<td><f:input path="travellerPassportNumber"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Add Traveller"></td>
				</tr>
			</tbody>
		</table>
	</f:form> --%>
	

</body>
</html>