
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityRecordService;
import services.ActorService;
import services.AssignmentService;
import services.SubjectService;
import services.TeacherService;
import domain.ActivityRecord;
import domain.Actor;
import domain.Assignment;
import domain.Subject;
import domain.Teacher;

@Controller
@RequestMapping("/assignment")
public class AssignmentController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private TeacherService			teacherService;

	@Autowired
	private AssignmentService		assignmentService;

	@Autowired
	private SubjectService			subjectService;

	@Autowired
	private ActivityRecordService	activityRecordService;


	// Constructors
	// ============================================================================

	public AssignmentController() {
		super();
	}

	//List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int subjectId) {
		ModelAndView result;
		Collection<Assignment> assignments;
		boolean own = false;

		try {

			final Actor principal = this.actorService.findByPrincipal();

			final Subject subject = this.subjectService.findOne(subjectId);
			Assert.notNull(subject);

			if (subject.getTeacher() == null) {

			} else if (subject.getTeacher().getId() == principal.getId())
				own = true;

			assignments = this.assignmentService.findAllBySubject(subjectId);

			result = new ModelAndView("assignment/list");
			result.addObject("assignments", assignments);
			result.addObject("principal", principal);
			result.addObject("subject", subject);
			result.addObject("own", own);
			result.addObject("requestURI", "assignment/list.do");

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;

	}

	// Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int subjectId) {
		ModelAndView result;
		Assignment assignment;
		try {

			final Teacher principal = this.teacherService.findByPrincipal();

			final Subject subject = this.subjectService.findOne(subjectId);
			Assert.notNull(subject);
			Assert.isTrue(principal.getSubjects().contains(subject));

			assignment = this.assignmentService.create(subject);

			result = this.createEditModelAndView(assignment);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}
	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int assignmentId) {
		ModelAndView result;

		try {

			final Teacher principal = this.teacherService.findByPrincipal();

			final Assignment assignment = this.assignmentService.findOne(assignmentId);
			Assert.notNull(assignment);
			Assert.isTrue(principal.getSubjects().contains(assignment.getSubject()));

			result = this.createEditModelAndView(assignment);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Assignment assignment, final BindingResult binding) {
		ModelAndView result;

		try {

			this.assignmentService.reconstruct(assignment, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(assignment);
			else {
				result = new ModelAndView("redirect:/assignment/list.do?subjectId=" + assignment.getSubject().getId());
				this.assignmentService.save(assignment);

				ActivityRecord activityRecord = new ActivityRecord();
				if (assignment.getId() == 0) {
					activityRecord.setDescription(actorService.findByPrincipal().getName() + " has create/ha creado : " + assignment.getTitle());
				} else {
					activityRecord.setDescription(actorService.findByPrincipal().getName() + " has edit/ha editado : " + assignment.getTitle());
				}
				activityRecord.setActor(actorService.findByPrincipal());
				activityRecordService.save(activityRecord);

			}
		} catch (final Throwable oops) {
			if (binding.hasErrors())
				result = this.createEditModelAndView(assignment);
			else
				result = this.createEditModelAndView(assignment, "assignment.save.error");
		}

		return result;
	}

	// Deleting
	// ===========================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Assignment assignment, final BindingResult binding) {
		ModelAndView result;

		try {

			result = new ModelAndView("redirect:/assignment/list.do?subjectId=" + assignment.getSubject().getId());
			this.assignmentService.delete(assignment);

			ActivityRecord activityRecord = new ActivityRecord();
			activityRecord.setDescription(actorService.findByPrincipal().getName() + " has delete/ha eliminado : " + assignment.getTitle());
			activityRecord.setActor(actorService.findByPrincipal());
			activityRecordService.save(activityRecord);

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(assignment, "assignment.delete.error");
		}

		return result;

	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Assignment assignment) {

		return this.createEditModelAndView(assignment, null);
	}

	private ModelAndView createEditModelAndView(final Assignment assignment, final String message) {

		final ModelAndView resul = new ModelAndView("assignment/edit");

		resul.addObject("assignment", assignment);
		resul.addObject("message", message);

		return resul;
	}

}
