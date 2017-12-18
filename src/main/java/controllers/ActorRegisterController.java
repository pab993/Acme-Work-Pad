
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ActorRegisterService;

@Controller
@RequestMapping("/actorRegister")
public class ActorRegisterController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private ActorRegisterService	actorRegisterService;


	//Constructors
	// ============================================================================

	public ActorRegisterController() {
		super();
	}

	//Edition
	//=============================================================================

	//	@RequestMapping(value = "/register", method = RequestMethod.GET)
	//	public ModelAndView edit() {
	//
	//		ModelAndView result;
	//		result = new ModelAndView("actorRegister/edit");
	//
	//		result.addObject("actorRegisterForm", new ActorRegisterForm());
	//
	//		return result;
	//	}
	//
	//	// Save
	//	// ====================================================================
	//
	//	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final ActorRegisterForm actorRegisterForm, final BindingResult binding) {
	//		ModelAndView result;
	//		Student actorRegister;
	//
	//		try {
	//			actorRegister = this.actorRegisterService.reconstruct(actorRegisterForm, binding);
	//
	//			if (binding.hasErrors())
	//				result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error");
	//			else {
	//				result = new ModelAndView("redirect:/welcome/index.do");
	//				actorRegister = this.actorRegisterService.save(actorRegister);
	//
	//			}
	//		} catch (final Throwable oops) {
	//			result = this.createEditModelAndView(actorRegisterForm, "actorRegister.save.error");
	//		}
	//
	//		return result;
	//	}
	//
	//	// Ancilliary methods
	//	// =================================================================================================
	//
	//	private ModelAndView createEditModelAndView(final ActorRegisterForm actorRegisterForm) {
	//
	//		return this.createEditModelAndView(actorRegisterForm, null);
	//	}
	//
	//	private ModelAndView createEditModelAndView(final ActorRegisterForm actorRegisterForm, final String message) {
	//
	//		final ModelAndView resul = new ModelAndView("actorRegister/edit");
	//
	//		resul.addObject("actorRegisterForm", actorRegisterForm);
	//		resul.addObject("message", message);
	//		return resul;
	//	}

}
