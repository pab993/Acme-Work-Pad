
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
import services.SubjectService;
import domain.ActivityRecord;
import domain.Subject;

@Controller
@RequestMapping("/subject/student")
public class SubjectStudentController extends AbstractController {

	@Autowired
	private SubjectService			subjectService;

	@Autowired
	private ActivityRecordService	activityRecordService;

	@Autowired
	private ActorService			actorService;


	@RequestMapping(value = "/enrol", method = RequestMethod.GET)
	public ModelAndView enrol(final int subjectId, @RequestParam(defaultValue = "0", required = false) int errorMessage) {
		ModelAndView res;

		try {

			final Subject subject = subjectService.findOne(subjectId);
			Assert.notNull(subject);

			res = new ModelAndView();

			try {
				subjectService.enrol(subject);

				ActivityRecord activityRecord = new ActivityRecord();
				activityRecord.setDescription(actorService.findByPrincipal().getName() + "has enroll to/ se ha unido a : " + subject.getTitle());
				activityRecord.setActor(actorService.findByPrincipal());
				activityRecordService.save(activityRecord);

				res.setViewName("redirect:/subject/list.do");
			} catch (Throwable oops) {
				res = new ModelAndView("redirect:/subject/list.do?errorMessage=2");
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
				result.addObject("errorMessage", "subject.error.enrol");
				break;
			default:
				result.addObject("errorMessage", "subject.errorDefault");
				break;
			}
		}
	}

}
