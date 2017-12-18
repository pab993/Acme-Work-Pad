
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationSystemService;
import domain.ConfigurationSystem;

@Controller
@RequestMapping("/configurationSystem")
public class ConfigurationSystemController extends AbstractController {

	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	public ConfigurationSystemController() {
		super();
	}

	@RequestMapping("/edit")
	public ModelAndView edit() {
		ConfigurationSystem configurationSystem;
		configurationSystem = this.configurationSystemService.getCS();

		ModelAndView result;

		result = new ModelAndView("configurationSystem/edit");
		result.addObject("configurationSystem", configurationSystem);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ConfigurationSystem configurationSystem, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(configurationSystem);
		else
			try {

				this.configurationSystemService.save(configurationSystem);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(configurationSystem, "configurationSystem.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final ConfigurationSystem configurationSystem) {
		ModelAndView result;

		result = this.createEditModelAndView(configurationSystem, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ConfigurationSystem configurationSystem, final String message) {
		ModelAndView result;

		result = new ModelAndView("configurationSystem/administrator/edit");

		result.addObject("configurationSystem", configurationSystem);
		result.addObject("message", message);

		return result;
	}
}
