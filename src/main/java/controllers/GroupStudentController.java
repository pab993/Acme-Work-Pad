
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.GroupService;
import services.StudentService;
import services.SubjectService;
import domain.Group;
import domain.Student;
import domain.Subject;

@Controller
@RequestMapping("/group/student")
public class GroupStudentController extends AbstractController {

	@Autowired
	private SubjectService	subjectService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private GroupService	groupService;

	@Autowired
	private StudentService	studentService;


	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(final int groupId, @RequestParam(defaultValue = "0", required = false) int errorMessage) {
		ModelAndView res;

		try {

			final Group group = groupService.findOne(groupId);
			Assert.notNull(group);
			Assert.isTrue(!studentService.findByPrincipal().getGroups().contains(group));
			Assert.isTrue(studentService.findByPrincipal().getSubjects().contains(group.getSubject()));

			res = new ModelAndView();
			Integer subjectId = group.getSubject().getId();

			try {
				groupService.join(group);
				res.setViewName("redirect:/subject/display.do?subjectId=" + subjectId);
			} catch (Throwable oops) {
				res = new ModelAndView("redirect:/subject/display.do?subjectId=" + subjectId + "&errorMessage=3");
			}
			error(res, errorMessage);

		} catch (final Throwable oops) {

			res = new ModelAndView("redirect:/panic/misc.do");

		}
		return res;
	}

	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam Integer subjectId) {
		ModelAndView result;

		try {

			Subject subject = subjectService.findOne(subjectId);
			Assert.notNull(subject);
			Assert.isTrue(studentService.findByPrincipal().getSubjects().contains(subject));

			Collection<Student> students = new ArrayList<Student>();
			students.add((Student) actorService.findByPrincipal());
			Group group = groupService.create(subject);
			group.setStudents(students);
			result = createEditModelAndView(group);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("group") @Valid final Group group, final BindingResult binding) {
		ModelAndView res;

		if (group.getStartDate() != null) {
			groupService.reconstruct(group, binding);
		}

		if (group.getStartDate() != null && group.getEndDate() != null) {
			if (group.getEndDate().before(group.getStartDate())) {
				binding.rejectValue("endDate", "date.before");
			}
		}

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(group, "error.save");
			System.out.println(binding);

		}

		else
			try {
				groupService.save(group);
				Integer subjectId = group.getSubject().getId();
				res = new ModelAndView("redirect:/subject/display.do?subjectId=" + subjectId);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(group, "error.save");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Group group) {
		return this.createEditModelAndView(group, null);
	}

	protected ModelAndView createEditModelAndView(final Group group, final String text) {
		final ModelAndView res = new ModelAndView("group/edit");
		res.addObject("group", group);
		res.addObject("message", text);
		res.addObject("formAction", "group/student/save.do");
		res.addObject("readonly", false);
		return res;
	}

	private void error(ModelAndView result, int errorMessage) {
		if (errorMessage != 0) {
			switch (errorMessage) {
			case 3:
				result.addObject("errorMessage", "group.error.join");
				break;
			default:
				result.addObject("errorMessage", "group.errorDefault");
				break;
			}
		}
	}

}
