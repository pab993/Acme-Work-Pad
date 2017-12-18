
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityRecordService;
import services.ActorService;
import services.SeminaryService;
import domain.ActivityRecord;
import domain.Seminary;

@Controller
@RequestMapping("/seminary/student")
public class SeminaryStudentController extends AbstractController {

	@Autowired
	private SeminaryService			seminaryService;

	@Autowired
	private ActivityRecordService	activityRecordService;

	@Autowired
	private ActorService			actorService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(final int seminaryId, @RequestParam(defaultValue = "0", required = false) int errorMessage) {
		ModelAndView res;

		try {

			final Seminary seminary = seminaryService.findOne(seminaryId);
			Assert.notNull(seminary);

			res = new ModelAndView();

			try {
				seminaryService.register(seminary);

				ActivityRecord activityRecord = new ActivityRecord();
				activityRecord.setDescription(actorService.findByPrincipal().getName() + " registered to : " + seminary.getTitle());
				activityRecord.setActor(actorService.findByPrincipal());
				activityRecordService.save(activityRecord);

				res.setViewName("redirect:/seminary/list.do");
			} catch (Throwable oops) {
				res = new ModelAndView("redirect:/seminary/list.do?errorMessage=2");
			}
			error(res, errorMessage);

		} catch (final Throwable oops) {

			res = new ModelAndView("redirect:/panic/misc.do");

		}
		return res;
	}

	@RequestMapping(value = "/unregister", method = RequestMethod.GET)
	public ModelAndView unregister(final int seminaryId, @RequestParam(defaultValue = "0", required = false) int errorMessage) {
		ModelAndView res;

		try {

			final Seminary seminary = seminaryService.findOne(seminaryId);
			Assert.notNull(seminary);

			res = new ModelAndView();

			try {
				seminaryService.unregister(seminary);
				res.setViewName("redirect:/seminary/list.do");
			} catch (Throwable oops) {
				res = new ModelAndView("redirect:/seminary/list.do?errorMessage=2");
			}
			error(res, errorMessage);

		} catch (final Throwable oops) {

			res = new ModelAndView("redirect:/panic/misc.do");

		}

		return res;
	}

	private void error(ModelAndView result, int errorMessage) {
		if (errorMessage != 0) {
			switch (errorMessage) {
			case 2:
				result.addObject("errorMessage", "subject.error.register");
				break;
			default:
				result.addObject("errorMessage", "subject.errorDefault");
				break;
			}
		}
	}

}
