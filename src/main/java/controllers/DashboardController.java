
package controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.StudentService;
import services.SubjectService;
import services.TeacherService;
import domain.Actor;
import domain.Teacher;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractController {

	//Services--------------------------------------------------------

	@Autowired
	ActorService	actorService;

	@Autowired
	StudentService	studentService;

	@Autowired
	TeacherService	teacherService;

	@Autowired
	SubjectService	subjectService;


	//Constructor------------------------------------------------------
	public DashboardController() {
		super();
	}

	//Listing----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;

		Collection<Teacher> findTeacherMoreSubject;
		Collection<Teacher> findTeacherLessSubject;
		Collection<Teacher> findTeachersPlusMinus10AvgSubjects;
		Collection<Teacher> findTeachersPlusMinus10AvgSeminaries;
		Collection<Actor> findActorsPlusMinus10AvgActivityRecords;

		Object[] minMaxAvgSubjectsByTeacher;
		Object[] minMaxAvgSeatsBySubject;
		Object[] minMaxAvgStudentsBySubject;
		Object[] minMaxAvgAssignmentsBySubject;
		Object[] minMaxAvgSeminariesByTeacher;
		Object[] minMaxAvgActivityRecordsByActor;
		Object[] minMaxAvgBibliographyRecordsBySubject;
		Object[] minMaxAvgSocialIdentitiesByActor;

		Object[] ratioSubjectsWithBibliographyRecords;
		Object[] ratioActorsWithSocialIdentity;

		findTeacherMoreSubject = this.teacherService.findTeacherMoreSubject();

		if (findTeacherMoreSubject.isEmpty())
			findTeacherMoreSubject = new HashSet<Teacher>();

		findTeacherLessSubject = this.teacherService.findTeacherLessSubject();

		if (findTeacherLessSubject.isEmpty())
			findTeacherLessSubject = new HashSet<Teacher>();

		findTeachersPlusMinus10AvgSubjects = this.teacherService.findTeachersPlusMinus10AvgSubjects();

		if (findTeachersPlusMinus10AvgSubjects.isEmpty())
			findTeachersPlusMinus10AvgSubjects = new HashSet<Teacher>();

		findTeachersPlusMinus10AvgSeminaries = this.teacherService.findTeachersPlusMinus10AvgSeminaries();

		if (findTeachersPlusMinus10AvgSeminaries.isEmpty())
			findTeachersPlusMinus10AvgSeminaries = new HashSet<Teacher>();

		findActorsPlusMinus10AvgActivityRecords = this.actorService.findActorsPlusMinus10AvgActivityRecords();

		if (findActorsPlusMinus10AvgActivityRecords.isEmpty())
			findActorsPlusMinus10AvgActivityRecords = new HashSet<Actor>();

		minMaxAvgSubjectsByTeacher = this.teacherService.minMaxAvgSubjectsByTeacher();

		minMaxAvgSeatsBySubject = this.subjectService.minMaxAvgSeatsBySubject();

		minMaxAvgStudentsBySubject = this.subjectService.minMaxAvgStudentsBySubject();

		minMaxAvgAssignmentsBySubject = this.subjectService.minMaxAvgAssignmentsBySubject();

		minMaxAvgSeminariesByTeacher = this.teacherService.minMaxAvgSeminariesByTeacher();

		minMaxAvgActivityRecordsByActor = this.actorService.minMaxAvgActivityRecordsByActor();

		minMaxAvgBibliographyRecordsBySubject = this.subjectService.minMaxAvgBibliographyRecordsBySubject();

		minMaxAvgSocialIdentitiesByActor = this.actorService.minMaxAvgSocialIdentitiesByActor();

		ratioSubjectsWithBibliographyRecords = this.subjectService.ratioSubjectsWithBibliographyRecords();

		ratioActorsWithSocialIdentity = this.actorService.ratioActorsWithSocialIdentity();

		result = new ModelAndView("dashboard/list");

		result.addObject("findTeacherMoreSubject", findTeacherMoreSubject);
		result.addObject("findTeacherLessSubject", findTeacherLessSubject);
		result.addObject("findTeachersPlusMinus10AvgSubjects", findTeachersPlusMinus10AvgSubjects);
		result.addObject("findTeachersPlusMinus10AvgSeminaries", findTeachersPlusMinus10AvgSeminaries);
		result.addObject("findActorsPlusMinus10AvgActivityRecords", findActorsPlusMinus10AvgActivityRecords);

		result.addObject("minMaxAvgSubjectsByTeacher", minMaxAvgSubjectsByTeacher);
		result.addObject("minMaxAvgSeatsBySubject", minMaxAvgSeatsBySubject);
		result.addObject("minMaxAvgStudentsBySubject", minMaxAvgStudentsBySubject);
		result.addObject("minMaxAvgAssignmentsBySubject", minMaxAvgAssignmentsBySubject);
		result.addObject("minMaxAvgSeminariesByTeacher", minMaxAvgSeminariesByTeacher);
		result.addObject("minMaxAvgActivityRecordsByActor", minMaxAvgActivityRecordsByActor);
		result.addObject("minMaxAvgBibliographyRecordsBySubject", minMaxAvgBibliographyRecordsBySubject);
		result.addObject("minMaxAvgSocialIdentitiesByActor", minMaxAvgSocialIdentitiesByActor);

		result.addObject("ratioSubjectsWithBibliographyRecords", ratioSubjectsWithBibliographyRecords);
		result.addObject("ratioActorsWithSocialIdentity", ratioActorsWithSocialIdentity);

		return result;

	}

}
