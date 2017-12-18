<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>



	<acme:textbox code="subject.title" path="subject.title" readonly="true"/>	
	<acme:textbox code="subject.ticker" path="subject.ticker" readonly="true"/>
	<acme:textbox code="subject.syllabus" path="subject.syllabus" readonly="true"/>
	<acme:textbox code="subject.seats" path="subject.seats" readonly="true"/>
	
	
	
	
	<br/>
	<br/>
	
	<security:authorize access="hasRole('STUDENT')">
	<jstl:if test="${join eq 'TRUE' && join2 eq 'TRUE'}">
			<input type="button" name="group" value="<spring:message code="new.group"/>" 
		onclick="document.location.href='group/student/create.do?subjectId=${subject.id}';">
							
			</jstl:if>
	</security:authorize>
	
	<div>
	<jstl:if test="${errorMessage != null}">
		<br />
		<span class="message" style="color:red"><spring:message code="${errorMessage}" /></span>
	</jstl:if>
	</div>
	
	<%-- <security:authorize access="hasRole('ACADEMY') && principal.id == ${course.academy.userAccount.id}"> --%>
	 	<h3><spring:message code="subject.groups"/></h3>
		<display:table name="groups" class="displaytag" requestURI="${formAction}" id="row">
	
			<spring:message code="group.name" var="headerTag" />
			<display:column property="name" title="${headerTag}"/>
			
			<spring:message code="group.description" var="headerTag" />
			<display:column property="description" title="${headerTag}"/>
				
			<spring:message code="group.startDate" var="headerTag" />
			<display:column property="startDate" title="${headerTag}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code="group.endDate" var="headerTag" />
			<display:column property="endDate" title="${headerTag}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code="join.group" var="headerTag" />
			<display:column title="${headerTag}">
			<security:authorize access="hasRole('STUDENT')">
				<jstl:if test="${join eq 'TRUE' && join2 eq 'TRUE'}">
					<a href="group/student/join.do?groupId=${row.id}"><spring:message
				code="join.group"></spring:message></a>
				</jstl:if>
			</security:authorize>
			</display:column>
			
			
			<%-- <spring:message code="create.deliverable" var="headerTag" />
			<display:column title="${headerTag}">
			<security:authorize access="hasRole('STUDENT')">
				<jstl:if test="${join2 eq 'FALSE'}">
					<a href="deliverable/student/create.do?groupId=${row.id}"><spring:message
				code="create.deliverable"></spring:message></a>
				</jstl:if>
			</security:authorize>
			</display:column>
			
			
			<spring:message code="my.deliverable" var="headerTag" />
			<display:column title="${headerTag}">
			<security:authorize access="hasRole('STUDENT')">
				<jstl:if test="${join2 eq 'FALSE'}">
					<a href="deliverable/student/list.do?groupId=${row.id}"><spring:message
				code="my.deliverable"></spring:message></a>
				</jstl:if>
			</security:authorize>
			</display:column> --%>
			
			<spring:message code="create.deliverable" var="headerTag" />
			<display:column title="${headerTag}">
			<security:authorize access="hasRole('STUDENT')">
				<jstl:if test="${studentGroup.contains(row)}">
					<a href="deliverable/student/create.do?groupId=${row.id}"><spring:message
				code="create.deliverable"></spring:message></a>
				</jstl:if>
			</security:authorize>
			</display:column>
			
			
			<spring:message code="my.deliverable" var="headerTag" />
			<display:column title="${headerTag}">
			<security:authorize access="hasRole('STUDENT')">
				<jstl:if test="${studentGroup.contains(row)}">
					<a href="deliverable/student/list.do?groupId=${row.id}"><spring:message
				code="my.deliverable"></spring:message></a>
				</jstl:if>
			</security:authorize>
			</display:column>
			
			
		</display:table>
		

<h3><spring:message code="subject.bulletins"/></h3>
<display:table name="bulletins" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="bulletin.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="bulletin.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="bulletin.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="bulletin.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" />

	<%-- <spring:message code="actor.name" var="actorNameHeader" />
	<display:column property="actor.name" title="${actorNameHeader}" /> --%>

</display:table>
 

<security:authorize access="isAuthenticated()">
	<h3>
		<a href="comment/postComment.do?comentableId=${subject.getId() }">
			<spring:message code="postComment" />
		</a>
	</h3>
</security:authorize>

	<%-- </security:authorize> --%>
