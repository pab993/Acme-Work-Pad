
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${formAction}" modelAttribute="group" method="POST">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="subject"/>
	<form:hidden path="students"/>
	<form:hidden path="deliverables"/>
	
	<div>
		<div>
			<acme:textbox code="group.name" path="name" readonly="${readonly}" mandatory="true"/><br />
			<acme:textbox code="group.description" path="description" readonly="${readonly}" mandatory="true"/><br />
			<acme:textbox code="group.startDate" path="startDate" readonly="${readonly}" mandatory="true"/><br />
			<acme:textbox code="group.endDate" path="endDate" readonly="${readonly}" mandatory="true"/><br />
			
		</div>
		
		
		
		<div>
			<jstl:if test="${!readonly}">
				<acme:submit name="save" code="save"/>
			</jstl:if>
			<acme:cancel url="/subject/display.do?subjectId=${group.subject.id }" code="cancel"/>
		</div>
		
	</div>
</form:form>
