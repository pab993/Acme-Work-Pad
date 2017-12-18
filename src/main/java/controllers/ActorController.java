
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
import services.ActorRegisterService;
import services.ActorService;
import services.BulletinService;
import domain.ActivityRecord;
import domain.Actor;
import domain.Bulletin;
import domain.Student;
import domain.Teacher;
import forms.ActorForm;
import forms.ActorRegisterForm;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	//Services
	// =============================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ActorRegisterService	actorRegisterService;

	@Autowired
	private BulletinService			commentService;

	@Autowired
	private ActivityRecordService	activityRecordService;


	// Constructors
	// ==============================================================================

	public ActorController() {
		super();
	}

	//Profile
	// ==============================================================================

	@RequestMapping(value = "/seeProfile", method = RequestMethod.GET)
	public ModelAndView verProfile(@RequestParam(required = false) final Integer actorId) {

		ModelAndView result;
		Actor principal;

		try {
			if (actorId != null) {

				final Actor actor = this.actorService.findOne(actorId);
				Assert.notNull(actor);

				principal = this.actorService.findByPrincipal();
				final Collection<Bulletin> comments = this.commentService.filterComments(actor);
				Collection<ActivityRecord> activityRecords = activityRecordService.findAllByActor(actorId);

				result = new ModelAndView("actor/seeProfile");
				result.addObject("principal", principal);
				result.addObject("actor", actor);
				result.addObject("comments", comments);
				result.addObject("activityRecords", activityRecords);
				result.addObject("requestURI", "actor/seeProfile.do");

			} else {

				principal = this.actorService.findByPrincipal();
				result = new ModelAndView("redirect:/actor/seeProfile.do?actorId=" + principal.getId());

			}
		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;

	}

	@RequestMapping(value = "/seeProfileUnregistered", method = RequestMethod.GET)
	public ModelAndView verProfileUnregistred(@RequestParam(required = false) final Integer actorId) {

		ModelAndView result;
		Actor principal;

		try {
			if (actorId != null) {

				final Actor actor = this.actorService.findOne(actorId);
				Assert.notNull(actor);

				result = new ModelAndView("actor/seeProfile");
				result.addObject("actor", actor);

			} else {

				principal = this.actorService.findByPrincipal();
				result = new ModelAndView("redirect:/actor/seeProfile.do?actorId=" + principal.getId());

			}
		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;

	}

	//Edition
	// ================================================================================

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile() {

		ModelAndView result;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		final ActorForm actorForm = this.actorService.reconstructToForm(principal);

		result = new ModelAndView("actor/editProfile");
		result.addObject("actorForm", actorForm);

		return result;

	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		Actor actor;

		try {

			actor = this.actorService.reconstruct1(actorForm, binding);

			if (binding.hasErrors())

				result = this.createEditModelAndView(actorForm, "actor.save.error");

			else {

				actor = this.actorService.reconstruct2(actorForm, binding);

				result = new ModelAndView("redirect:/actor/seeProfile.do");

				actor = this.actorService.save(actor);

			}

		} catch (final Throwable oops) {

			result = this.createEditModelAndView(actorForm, "actor.save.error");

		}

		return result;

	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/registerStudent", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("actorRegister/edit");

		result.addObject("actorRegisterForm", new ActorRegisterForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/registerStudent", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorRegisterForm actorRegisterForm, final BindingResult binding) {
		ModelAndView result;
		Student actorRegister;

		try {
			actorRegister = this.actorRegisterService.reconstructStudent(actorRegisterForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndViewRegister(actorRegisterForm, "actorRegister.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				actorRegister = (Student) this.actorRegisterService.save(actorRegister);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewRegister(actorRegisterForm, "actorRegister.save.error");
		}

		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/registerTeacher", method = RequestMethod.GET)
	public ModelAndView editTeacher() {

		ModelAndView result;
		result = new ModelAndView("actorRegister/editTeacher");

		result.addObject("actorRegisterForm", new ActorRegisterForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/registerTeacher", method = RequestMethod.POST, params = "save")
	public ModelAndView saveTeacher(@Valid final ActorRegisterForm actorRegisterForm, final BindingResult binding) {
		ModelAndView result;
		Teacher actorRegister;

		try {
			actorRegister = this.actorRegisterService.reconstructTeacher(actorRegisterForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndViewRegisterTeacher(actorRegisterForm, "actorRegister.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				actorRegister = (Teacher) this.actorRegisterService.save(actorRegister);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewRegisterTeacher(actorRegisterForm, "actorRegister.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	//	private ModelAndView createEditModelAndViewRegister(final ActorRegisterForm actorRegisterForm) {
	//
	//		return this.createEditModelAndViewRegister(actorRegisterForm, null);
	//	}

	private ModelAndView createEditModelAndViewRegister(final ActorRegisterForm actorRegisterForm, final String message) {

		final ModelAndView resul = new ModelAndView("actorRegister/edit");

		resul.addObject("actorRegisterForm", actorRegisterForm);
		resul.addObject("message", message);
		return resul;
	}

	private ModelAndView createEditModelAndViewRegisterTeacher(final ActorRegisterForm actorRegisterForm, final String message) {

		final ModelAndView resul = new ModelAndView("actorRegister/editTeacher");

		resul.addObject("actorRegisterForm", actorRegisterForm);
		resul.addObject("message", message);
		return resul;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/editProfile");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);

		return result;

	}

}
