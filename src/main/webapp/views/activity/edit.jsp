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

<form:form action="activity/edit.do" modelAttribute="activity">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="subject" />

	<acme:textbox code="activity.title" path="title" mandatory="true"/>
	<br />

	<acme:textarea code="activity.description" path="description" mandatory="true"/>
	<br />

	<acme:textbox code="activity.startDate" path="startDate" mandatory="true"/>
	<br />

	<acme:textbox code="activity.endDate" path="endDate" mandatory="true"/>
	<br />
	
	<acme:textbox code="activity.information" path="information" />
	<br />

	
	
	<jstl:choose>
		<jstl:when test="${activity.id == 0}">
			<acme:submit name="save" code="activity.submit" />
			<acme:cancel code="activity.cancel" url="activity/list.do?subjectId=${activity.subject.id}" />
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="activity.submit" />
			<acme:submit name="delete" code="activity.delete" />
			<acme:cancel code="activity.cancel" url="activity/list.do?subjectId=${activity.subject.id}" />
		</jstl:otherwise>
	</jstl:choose>

</form:form>