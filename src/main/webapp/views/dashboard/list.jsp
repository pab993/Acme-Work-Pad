<%--
 * list.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.findTeacherMoreSubject"/></b>
		<br/>
		<jstl:forEach items="${findTeacherMoreSubject}" var="item">
			<h4><jstl:out value="${item.userAccount.username}"/></h4>
		</jstl:forEach>
	</div>
</fieldset>
<br/>

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.findTeacherLessSubject"/></b>
		<br/>
		<jstl:forEach items="${findTeacherLessSubject}" var="item">
			<h4><jstl:out value="${item.userAccount.username}"/></h4>
		</jstl:forEach>
	</div>
</fieldset>
<br/>

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.findTeachersPlusMinus10AvgSubjects"/></b>
		<br/>
		<jstl:forEach items="${findTeachersPlusMinus10AvgSubjects}" var="item">
			<h4><jstl:out value="${item.userAccount.username}"/></h4>
		</jstl:forEach>
	</div>
</fieldset>
<br/>

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.findTeachersPlusMinus10AvgSeminaries"/></b>
		<br/>
		<jstl:forEach items="${findTeachersPlusMinus10AvgSeminaries}" var="item">
			<h4><jstl:out value="${item.userAccount.username}"/></h4>
		</jstl:forEach>
	</div>
</fieldset>
<br/>

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.findActorsPlusMinus10AvgActivityRecords"/></b>
		<br/>
		<jstl:forEach items="${findActorsPlusMinus10AvgActivityRecords}" var="item">
			<h4><jstl:out value="${item.userAccount.username}"/></h4>
		</jstl:forEach>
	</div>
</fieldset>
<br/>

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgSubjectsByTeacher" /></b>
		<br /> 
		MIN: ${minMaxAvgSubjectsByTeacher[0]}<br />
		MAX: ${minMaxAvgSubjectsByTeacher[1]}<br /> 
		AVG: ${minMaxAvgSubjectsByTeacher[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgSeatsBySubject" /></b>
		<br /> 
		MIN: ${minMaxAvgSeatsBySubject[0]}<br />
		MAX: ${minMaxAvgSeatsBySubject[1]}<br /> 
		AVG: ${minMaxAvgSeatsBySubject[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgStudentsBySubject" /></b>
		<br /> 
		MIN: ${minMaxAvgStudentsBySubject[0]}<br />
		MAX: ${minMaxAvgStudentsBySubject[1]}<br /> 
		AVG: ${minMaxAvgStudentsBySubject[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgAssignmentsBySubject" /></b>
		<br /> 
		MIN: ${minMaxAvgAssignmentsBySubject[0]}<br />
		MAX: ${minMaxAvgAssignmentsBySubject[1]}<br /> 
		AVG: ${minMaxAvgAssignmentsBySubject[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgSeminariesByTeacher" /></b>
		<br /> 
		MIN: ${minMaxAvgSeminariesByTeacher[0]}<br />
		MAX: ${minMaxAvgSeminariesByTeacher[1]}<br /> 
		AVG: ${minMaxAvgSeminariesByTeacher[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgActivityRecordsByActor" /></b>
		<br /> 
		MIN: ${minMaxAvgActivityRecordsByActor[0]}<br />
		MAX: ${minMaxAvgActivityRecordsByActor[1]}<br /> 
		AVG: ${minMaxAvgActivityRecordsByActor[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgBibliographyRecordsBySubject" /></b>
		<br /> 
		MIN: ${minMaxAvgBibliographyRecordsBySubject[0]}<br />
		MAX: ${minMaxAvgBibliographyRecordsBySubject[1]}<br /> 
		AVG: ${minMaxAvgBibliographyRecordsBySubject[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.minMaxAvgSocialIdentitiesByActor" /></b>
		<br /> 
		MIN: ${minMaxAvgSocialIdentitiesByActor[0]}<br />
		MAX: ${minMaxAvgSocialIdentitiesByActor[1]}<br /> 
		AVG: ${minMaxAvgSocialIdentitiesByActor[2]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.ratioSubjectsWithBibliographyRecords" /></b>
		<br /> 
		RATIO: ${ratioSubjectsWithBibliographyRecords[0]}<br />
	</div>
</fieldset>
<br />

<fieldset class="panel panel-default">
	<div class="panel-body">
		<b><spring:message code="dashboard.ratioActorsWithSocialIdentity" /></b>
		<br /> 
		RATIO: ${ratioActorsWithSocialIdentity[0]}<br />
	</div>
</fieldset>
<br />
