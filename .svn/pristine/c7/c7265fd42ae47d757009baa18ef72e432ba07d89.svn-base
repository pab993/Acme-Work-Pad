
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
import services.DeliverableService;
import services.GroupService;
import domain.ActivityRecord;
import domain.Assignment;
import domain.Deliverable;
import domain.Group;
import domain.Student;
import domain.Subject;

@Controller
@RequestMapping("/deliverable/student")
public class DeliverableStudentController extends AbstractController {

	@Autowired
	private DeliverableService		deliverableService;

	@Autowired
	private ActivityRecordService	activityRecordService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private GroupService			groupService;


	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Integer groupId, @RequestParam(defaultValue = "0", required = false) final String message) {
		ModelAndView result;

		try {

			Collection<Deliverable> deliverables;

			Student student = (Student) actorService.findByPrincipal();
			Group group = groupService.findOne(groupId);
			Assert.notNull(group);

			deliverables = deliverableService.findAllByGroupAndStudent(group, student);

			result = new ModelAndView("deliverable/list");
			result.addObject("requestURI", "deliverable/student/list.do");
			result.addObject("deliverables", deliverables);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer groupId, @RequestParam(defaultValue = "0", required = false) int errorMessage) {
		ModelAndView result;

		try {

			Deliverable deliverable;
			Group group = groupService.findOne(groupId);
			Assert.notNull(group);
			Assert.isTrue(group.getStudents().contains(actorService.findByPrincipal()));

			deliverable = this.deliverableService.create(group);
			Subject subject = group.getSubject();
			Collection<Assignment> assignments = subject.getAssignments();

			result = this.createEditModelAndView(deliverable);
			result.addObject("deliverable", deliverable);
			result.addObject("assignments", assignments);
			result.addObject("subject", subject);

			error(result, errorMessage);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}
		return result;
	}

	// Save ---------------------------------------------------------------

	//	@RequestMapping(value = "/save", method = RequestMethod.POST)
	//	public ModelAndView save(@Valid final Deliverable deliverable, final BindingResult binding, @RequestParam(defaultValue = "0", required = false) int errorMessage) {
	//		ModelAndView result;
	//
	//		deliverableService.reconstruct(deliverable, binding);
	//
	//		if (binding.hasErrors()) {
	//			result = this.createEditModelAndView2(deliverable, "deliverable.commit.error");
	//			System.out.println(binding);
	//		} else
	//			try {
	//				Deliverable saved = deliverableService.save(deliverable);
	//
	//				ActivityRecord activityRecord = new ActivityRecord();
	//				activityRecord.setDescription(actorService.findByPrincipal().getName() + "has submit a/ ha realizado el : " + saved.getAssignment());
	//				activityRecord.setActor(actorService.findByPrincipal());
	//				activityRecordService.save(activityRecord);
	//
	//				result = new ModelAndView("redirect:/subject/display.do?subjectId=" + saved.getGroup().getSubject().getId());
	//
	//			} catch (Throwable oops) {
	//				//				result = new ModelAndView("redirect:/deliverable/student/create.do?groupId=" + deliverable.getGroup().getId() + "&errorMessage=2");
	//				result = createEditModelAndView2(deliverable, "deliverable.errorDate");
	//			}
	//
	//		error(result, errorMessage);
	//		return result;
	//	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid Deliverable deliverable, BindingResult binding) {
		ModelAndView result;

		deliverableService.reconstruct(deliverable, binding);
		if (binding.hasErrors()) {
			result = this.createEditModelAndView2(deliverable, "deliverable.commit.error");
		} else {
			try {
				Deliverable saved = deliverableService.save(deliverable);
				if (saved != null) {
					ActivityRecord activityRecord = new ActivityRecord();
					activityRecord.setDescription(actorService.findByPrincipal().getName() + "has submit a/ ha realizado el : " + saved.getAssignment().getTitle());
					activityRecord.setActor(actorService.findByPrincipal());
					activityRecordService.save(activityRecord);
					result = new ModelAndView("redirect:/subject/display.do?subjectId=" + saved.getGroup().getSubject().getId());
				} else {
					result = createEditModelAndView2(deliverable, "deliverable.errorDate");
				}

			} catch (Throwable oops) {
				//				result = new ModelAndView("redirect:/deliverable/student/create.do?groupId=" + deliverable.getGroup().getId() + "&errorMessage=2");
				result = createEditModelAndView2(deliverable, "deliverable.errorDate");
			}
		}

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Deliverable deliverable) {
		ModelAndView result;

		result = this.createEditModelAndView(deliverable, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Deliverable deliverable, final String text) {
		ModelAndView result;

		result = new ModelAndView("deliverable/edit");
		result.addObject("deliverable", deliverable);
		result.addObject("message", text);
		result.addObject("requestURI", "deliverable/student/save.do");

		return result;
	}

	protected ModelAndView createEditModelAndView2(Deliverable deliverable) {
		ModelAndView result;

		result = this.createEditModelAndView2(deliverable, null);

		return result;
	}

	protected ModelAndView createEditModelAndView2(Deliverable deliverable, String text) {
		ModelAndView result;

		result = new ModelAndView("deliverable/edit");
		result.addObject("deliverable", deliverable);
		result.addObject("assignments", deliverable.getGroup().getSubject().getAssignments());
		result.addObject("message", text);
		result.addObject("requestURI", "deliverable/student/save.do");

		return result;
	}

	private void error(ModelAndView result, int errorMessage) {
		if (errorMessage != 0) {
			switch (errorMessage) {
			case 2:
				result.addObject("errorMessage", "deliverable.errorDate");
				break;
			default:
				result.addObject("errorMessage", "errorDefault");
				break;
			}
		}
	}

}
