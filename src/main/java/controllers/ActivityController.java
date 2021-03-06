
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
import services.ActivityService;
import services.ActorService;
import services.SubjectService;
import services.TeacherService;
import domain.Activity;
import domain.ActivityRecord;
import domain.Actor;
import domain.Subject;
import domain.Teacher;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private TeacherService			teacherService;

	@Autowired
	private ActivityService			activityService;

	@Autowired
	private SubjectService			subjectService;

	@Autowired
	private ActivityRecordService	activityRecordService;


	// Constructors
	// ============================================================================

	public ActivityController() {
		super();
	}

	//List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int subjectId) {
		ModelAndView result;
		Collection<Activity> activities;
		boolean own = false;

		try {

			final Actor principal = this.actorService.findByPrincipal();

			final Subject subject = this.subjectService.findOne(subjectId);
			Assert.notNull(subject);

			if (subject.getTeacher() == null) {

			} else if (subject.getTeacher().getId() == principal.getId())
				own = true;

			activities = this.activityService.findAllBySubject(subjectId);

			result = new ModelAndView("activity/list");
			result.addObject("activities", activities);
			result.addObject("principal", principal);
			result.addObject("subject", subject);
			result.addObject("own", own);
			result.addObject("requestURI", "activity/list.do");

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
		Activity activity;
		try {

			final Teacher principal = this.teacherService.findByPrincipal();

			final Subject subject = this.subjectService.findOne(subjectId);
			Assert.notNull(subject);
			Assert.isTrue(principal.getSubjects().contains(subject));

			activity = this.activityService.create(subject);

			result = this.createEditModelAndView(activity);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}
	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int activityId) {
		ModelAndView result;

		try {

			final Teacher principal = this.teacherService.findByPrincipal();

			final Activity activity = this.activityService.findOne(activityId);
			Assert.notNull(activity);
			Assert.isTrue(principal.getSubjects().contains(activity.getSubject()));

			result = this.createEditModelAndView(activity);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Activity activity, final BindingResult binding) {
		ModelAndView result;

		try {

			this.activityService.reconstruct(activity, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(activity);
			else {
				result = new ModelAndView("redirect:/activity/list.do?subjectId=" + activity.getSubject().getId());
				this.activityService.save(activity);

				ActivityRecord activityRecord = new ActivityRecord();
				if (activity.getId() == 0) {
					activityRecord.setDescription(actorService.findByPrincipal().getName() + " has create/ha creado : " + activity.getTitle());
				} else {
					activityRecord.setDescription(actorService.findByPrincipal().getName() + " has edit/ha editado : " + activity.getTitle());
				}
				//activityRecord.setDescription("crear/edit activity");
				activityRecord.setActor(actorService.findByPrincipal());
				activityRecordService.save(activityRecord);

			}
		} catch (final Throwable oops) {
			if (binding.hasErrors())
				result = this.createEditModelAndView(activity);
			else
				result = this.createEditModelAndView(activity, "activity.save.error");
		}

		return result;
	}

	// Deleting
	// ===========================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Activity activity, final BindingResult binding) {
		ModelAndView result;

		try {

			result = new ModelAndView("redirect:/activity/list.do?subjectId=" + activity.getSubject().getId());
			this.activityService.delete(activity);

			ActivityRecord activityRecord = new ActivityRecord();
			activityRecord.setDescription(actorService.findByPrincipal().getName() + " has delete/ha eliminado : " + activity.getTitle());
			activityRecord.setActor(actorService.findByPrincipal());
			activityRecordService.save(activityRecord);

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(activity, "activity.delete.error");
		}

		return result;

	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Activity activity) {

		return this.createEditModelAndView(activity, null);
	}

	private ModelAndView createEditModelAndView(final Activity activity, final String message) {

		final ModelAndView resul = new ModelAndView("activity/edit");

		resul.addObject("activity", activity);
		resul.addObject("message", message);

		return resul;
	}

}
