<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="seminary/edit.do" modelAttribute="seminary">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="teacher" />
	<form:hidden path="students" />

	<acme:textbox code="seminary.title" path="title" />
	<br />
	
	<acme:textbox code="seminary.abstractSeminary" path="abstractSeminary" />
	<br />
	
	<acme:textbox code="seminary.moment" path="moment" />
	<br />
	
	<acme:textbox code="seminary.duration" path="duration" />
	<br />
	
	<acme:textbox code="seminary.hall" path="hall" />
	<br />
	
	<acme:textbox code="seminary.seats" path="seats" />
	<br />

	
	<jstl:choose>
		<jstl:when test="${seminary.id == 0}">
			<acme:submit name="save" code="seminary.submit" />
			<acme:cancel code="seminary.cancel" url="seminary/myList.do" />
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="seminary.submit" />
			<acme:submit name="delete" code="seminary.delete" />
			<acme:cancel code="seminary.cancel" url="seminary/myList.do" />
		</jstl:otherwise>
	</jstl:choose>

</form:form>