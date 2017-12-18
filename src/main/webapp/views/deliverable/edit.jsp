<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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

<form:form action="${requestURI}" modelAttribute="deliverable">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="group" />
	<form:hidden path="grade" />
	


	<div>
		<jstl:if test="${errorMessage != null}">
			<br />
			<span class="message" style="color: red"><spring:message
					code="${errorMessage}" /></span>
		</jstl:if>
	</div>

	<security:authorize access="hasRole('STUDENT')">
		<acme:select items="${assignments}" itemLabel="title"
			code="deliverable.assigment" path="assignment"/>
		<br />
		<acme:textbox code="deliverable.contents" path="contents"/>
		<br />
		<br />
		<acme:textbox code="deliverable.attachment" path="attachment" />
		<br />
		<br />
	</security:authorize>

	<jstl:choose>
		<jstl:when test="${deliverable.id == 0}">
			<acme:submit name="save" code="deliverable.save" />
			<acme:cancel code="deliverable.cancel" url="subject/display.do?subjectId=${deliverable.group.subject.id}" />
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="deliverable.save" />
			<acme:submit name="delete" code="deliverable.delete" />
			<acme:cancel code="deliverable.cancel" url="subject/display.do?subjectId=${deliverable.group.subject.id}" />
		</jstl:otherwise>
	</jstl:choose>


	<br />

</form:form>