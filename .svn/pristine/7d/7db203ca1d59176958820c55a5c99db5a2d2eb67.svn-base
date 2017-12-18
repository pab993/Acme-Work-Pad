
package controllers;

import java.util.ArrayList;
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
import services.SeminaryService;
import services.TeacherService;
import domain.ActivityRecord;
import domain.Seminary;
import domain.Student;
import domain.Teacher;

@Controller
@RequestMapping("/seminary")
public class SeminaryController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private TeacherService			teacherService;

	@Autowired
	private SeminaryService			seminaryService;

	@Autowired
	private ActivityRecordService	activityRecordService;

	@Autowired
	private ActorService			actorService;


	// Constructors
	// ============================================================================

	public SeminaryController() {
		super();
	}

	// My List
	// =============================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		Collection<Seminary> seminaries;
		final Teacher teacher = this.teacherService.findByPrincipal();

		seminaries = this.seminaryService.findByTeacher(teacher.getId());

		result = new ModelAndView("seminary/myList");
		result.addObject("seminaries", seminaries);
		result.addObject("requestURI", "seminary/myList.do");

		return result;
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int errorMessage) {
		ModelAndView result;
		Collection<Seminary> seminaries;

		seminaries = this.seminaryService.findAll();
		result = new ModelAndView("seminary/list");
		if (actorService.isAuthenticated()) {
			if (actorService.isStudent()) {
				Student student = (Student) actorService.findByPrincipal();
				Collection<Seminary> studentSeminary = new ArrayList<Seminary>();
				for (Seminary sm : seminaries) {
					if (sm.getStudents().contains(student)) {
						studentSeminary.add(sm);
					}
				}
				result.addObject("studentSeminary", studentSeminary);
			}
		}

		result.addObject("seminaries", seminaries);
		result.addObject("requestURI", "seminary/list.do");

		error(result, errorMessage);
		return result;
	}

	// Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Seminary seminary;
		try {

			seminary = this.seminaryService.create();

			result = this.createEditModelAndView(seminary);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}

		return result;
	}
	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int seminaryId) {
		ModelAndView result;

		try {

			final Teacher principal = this.teacherService.findByPrincipal();

			final Seminary seminary = this.seminaryService.findOne(seminaryId);
			Assert.notNull(seminary);
			Assert.isTrue(principal.getSeminaries().contains(seminary));

			result = this.createEditModelAndView(seminary);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Seminary seminary, final BindingResult binding) {
		ModelAndView result;

		try {

			this.seminaryService.reconstruct(seminary, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(seminary);
			else {
				result = new ModelAndView("redirect:/seminary/myList.do?seminaryId=" + seminary.getId());
				this.seminaryService.save(seminary);

				ActivityRecord activityRecord = new ActivityRecord();
				if (seminary.getId() == 0) {
					activityRecord.setDescription(actorService.findByPrincipal().getName() + " has create/ ha creado : " + seminary.getTitle());
				} else {
					activityRecord.setDescription(actorService.findByPrincipal().getName() + " has edite/ ha eliminado : " + seminary.getTitle());
				}
				activityRecord.setActor(actorService.findByPrincipal());
				activityRecordService.save(activityRecord);

			}
		} catch (final Throwable oops) {
			if (binding.hasErrors())
				result = this.createEditModelAndView(seminary);
			else
				result = this.createEditModelAndView(seminary, "seminary.save.error");
		}

		return result;
	}

	// Deleting
	// ===========================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Seminary seminary, final BindingResult binding) {
		ModelAndView result;

		try {

			result = new ModelAndView("redirect:/seminary/myList.do?seminaryId=" + seminary.getId());
			this.seminaryService.delete(seminary);

			ActivityRecord activityRecord = new ActivityRecord();
			activityRecord.setDescription(actorService.findByPrincipal().getName() + " has delete/ ha eliminado : " + seminary.getTitle());
			activityRecord.setActor(actorService.findByPrincipal());
			activityRecordService.save(activityRecord);

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(seminary, "seminary.delete.error");
		}

		return result;

	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Seminary seminary) {

		return this.createEditModelAndView(seminary, null);
	}

	private ModelAndView createEditModelAndView(final Seminary seminary, final String message) {

		final ModelAndView resul = new ModelAndView("seminary/edit");

		resul.addObject("seminary", seminary);
		resul.addObject("message", message);

		return resul;
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
