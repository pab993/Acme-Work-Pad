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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="actor.name" />
:
<jstl:out value="${actor.name}"></jstl:out>
<br>
<spring:message code="actor.surname" />
:
<jstl:out value="${actor.surname}"></jstl:out>
<br>
<spring:message code="actor.email" />
:
<jstl:out value="${actor.email}"></jstl:out>
<br>
<spring:message code="actor.phone" />
:
<jstl:out value="${actor.phone}"></jstl:out>
<br>
<spring:message code="actor.postalAddress" />
:
<jstl:out value="${actor.postalAddress}"></jstl:out>
<br>
<br>

<security:authorize access="isAuthenticated()">
	<h3>
		<a href="comment/postComment.do?comentableId=${actor.id }"> <spring:message
				code="postComment" />
		</a>
	</h3>
</security:authorize>

<br>

<display:table name="comments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="comment.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" />

	<spring:message code="comment.userName" var="userNameHeader" />
	<display:column>
		<a href="actor/seeProfile.do?actorId=${row.actor.id}"> <spring:message
				code="display.actor" />
		</a>
	</display:column>
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="comment/ban.do?commentId=${row.id}"><jstl:choose>
					<jstl:when test="${row.ban}">

						<spring:message code="unBan" />
					</jstl:when>
					<jstl:otherwise>
						<spring:message code="ban" />
					</jstl:otherwise>
				</jstl:choose> </a>
		</display:column>
	</security:authorize>
</display:table>

</br>

<display:table name="activityRecords" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="activityRecord.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" />

	<spring:message code="activityRecord.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="activityRecord.attachments" var="attachmentsHeader" />
	<display:column property="attachments" title="${attachmentsHeader}" />

	<security:authorize access="isAuthenticated()">
		<jstl:if test="${principal.id eq row.actor.id}">
			<display:column>
				<a href="activityRecord/edit.do?activityRecordId=${row.id}"> <spring:message
						code="activityRecord.edit"></spring:message></a>
			</display:column>
		</jstl:if>
	</security:authorize>


</display:table>


<br>
<br>
<jstl:if test="${actor.id == principal.id}">
	<a href="actor/editProfile.do"><spring:message
			code="actor.edit.profile" /></a>
</jstl:if>