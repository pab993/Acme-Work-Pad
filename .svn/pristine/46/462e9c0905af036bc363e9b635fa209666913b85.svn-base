
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
import domain.ActivityRecord;
import domain.Actor;

@Controller
@RequestMapping("/activityRecord")
public class ActivityRecordController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private ActivityRecordService	activityRecordService;

	@Autowired
	private ActorService			actorService;


	// Constructors
	// ============================================================================

	public ActivityRecordController() {
		super();
	}

	//Listing
	// ============================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ActivityRecord> activityRecords;
		Actor principal;

		principal = this.actorService.findByPrincipal();

		activityRecords = this.activityRecordService.findAllByActor(principal.getId());

		result = new ModelAndView("activityRecord/list");
		result.addObject("activityRecords", activityRecords);
		result.addObject("principal", principal);
		result.addObject("requestURI", "activityRecord/myList.do");

		return result;
	}

	// Create
	// =================================================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		final ActivityRecord activityRecord = this.activityRecordService.create(principal);

		result = this.createEditModelAndView(activityRecord);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int activityRecordId) {
		ModelAndView result;
		try {

			final ActivityRecord activityRecord = this.activityRecordService.findOne(activityRecordId);
			Assert.notNull(activityRecord);
			Assert.isTrue(actorService.findByPrincipal().getId() == activityRecord.getActor().getId());

			result = this.createEditModelAndView(activityRecord);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActivityRecord activityRecord, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(activityRecord, "activityRecord.save.error");
			else {
				result = new ModelAndView("redirect:/activityRecord/myList.do");
				this.activityRecordService.save(activityRecord);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(activityRecord, "activityRecord.save.error");
		}

		return result;
	}

	// Deleting
	// -------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final ActivityRecord activityRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.activityRecordService.delete(activityRecord);
			result = new ModelAndView("redirect:/activityRecord/myList.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(activityRecord, "activityRecord.delete.error");
		}
		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final ActivityRecord activityRecord) {

		return this.createEditModelAndView(activityRecord, null);
	}

	private ModelAndView createEditModelAndView(final ActivityRecord activityRecord, final String message) {

		final ModelAndView resul = new ModelAndView("activityRecord/edit");

		resul.addObject("activityRecord", activityRecord);
		resul.addObject("message", message);

		return resul;
	}

}
