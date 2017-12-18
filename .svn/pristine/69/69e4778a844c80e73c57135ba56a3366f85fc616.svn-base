
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

import services.DeliverableService;
import services.TeacherService;
import domain.Deliverable;
import domain.Teacher;

@Controller
@RequestMapping("/deliverable/teacher")
public class DeliverableTeacherController extends AbstractController {

	@Autowired
	private DeliverableService	deliverableService;

	@Autowired
	private TeacherService		teacherService;


	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Teacher principal = this.teacherService.findByPrincipal();
		Collection<Deliverable> deliverables;

		deliverables = this.deliverableService.findAllByTeacher(principal);

		result = new ModelAndView("deliverable/list");
		result.addObject("requestURI", "deliverable/teacher/list.do");
		result.addObject("principal", principal);
		result.addObject("deliverables", deliverables);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer deliverableId, @RequestParam(defaultValue = "0", required = false) final int errorMessage) {

		ModelAndView result;
		Deliverable deliverable;

		try {
			deliverable = this.deliverableService.findOne(deliverableId);
			Assert.notNull(deliverable);
			Assert.isTrue(deliverable.getAssignment().getSubject().getTeacher().equals(this.teacherService.findByPrincipal()));

			result = this.createEditModelAndView(deliverable);

			this.error(result, errorMessage);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	// Save ---------------------------------------------------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid final Deliverable deliverable, final BindingResult binding, @RequestParam(defaultValue = "0", required = false) final int errorMessage) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(deliverable, "deliverable.commit.error");
		else

			try {

				this.deliverableService.saveGrade(deliverable);
				result = new ModelAndView("redirect:/deliverable/teacher/list.do");

			} catch (final Throwable oops) {
				if (binding.hasErrors())
					result = this.createEditModelAndView(deliverable);
				else
					result = this.createEditModelAndView(deliverable, "deliverable.commit.error");
			}

		this.error(result, errorMessage);

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

		result = new ModelAndView("deliverable/editGrade");
		result.addObject("deliverable", deliverable);
		result.addObject("message", text);
		result.addObject("requestURI", "deliverable/teacher/save.do");

		return result;
	}

	private void error(final ModelAndView result, final int errorMessage) {
		if (errorMessage != 0)
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
