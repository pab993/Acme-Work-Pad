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

<form:form action="assignment/edit.do" modelAttribute="assignment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="subject" />
	<form:hidden path="deliverables" />

	<acme:textbox code="assignment.title" path="title" mandatory="true"/>
	<br />

	<acme:textarea code="assignment.description" path="description" mandatory="true"/>
	<br />

	<acme:textbox code="assignment.startDate" path="startDate" mandatory="true"/>
	<br />

	<acme:textbox code="assignment.endDate" path="endDate" mandatory="true"/>
	<br />
	
	<acme:textbox code="assignment.information" path="information" />
	<br />

	
	
	<jstl:choose>
		<jstl:when test="${assignment.id == 0}">
			<acme:submit name="save" code="assignment.submit" />
			<acme:cancel code="assignment.cancel" url="assignment/list.do?subjectId=${assignment.subject.id}" />
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="assignment.submit" />
			<acme:submit name="delete" code="assignment.delete" />
			<acme:cancel code="assignment.cancel" url="assignment/list.do?subjectId=${assignment.subject.id}" />
		</jstl:otherwise>
	</jstl:choose>

</form:form>