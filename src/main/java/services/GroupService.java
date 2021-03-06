
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.GroupRepository;
import domain.Administrator;
import domain.Deliverable;
import domain.Group;
import domain.Student;
import domain.Subject;

@Service
@Transactional
public class GroupService {

	@Autowired
	private GroupRepository			groupRepository;

	@Autowired
	private StudentService			studentService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	public GroupService() {
		super();
	}

	// CRUD methods --------------------------------------------------------------------------------
	public Group create(final Subject subject) {
		Assert.isTrue(this.actorService.isStudent());
		Assert.notNull(subject);
		final Student principal = this.studentService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(subject));
		Boolean condition = true;
		for (final Group gr : principal.getGroups())
			if (gr.getSubject().equals(subject))
				condition = false;
		Assert.isTrue(condition);

		Group result;
		result = new Group();
		final Collection<Student> students = new ArrayList<Student>();
		final Collection<Deliverable> deliverables = new ArrayList<Deliverable>();
		result.setDeliverables(deliverables);
		result.setStudents(students);
		result.getStudents().add(principal);
		result.setSubject(subject);

		return result;
	}

	public Group findOne(final int groupId) {
		return this.groupRepository.findOne(groupId);
	}

	public Collection<Group> findAll() {
		return this.groupRepository.findAll();
	}

	public Group save(final Group group) {

		Assert.notNull(group);

		final Student principal = this.studentService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(group.getSubject()));
		//		Assert.isTrue(group.getStudents().contains(principal));

		Group res;

		res = this.groupRepository.save(group);

		return res;
	}

	public void delete(final Group group) {

		Assert.notNull(group);
		Assert.isTrue(this.groupRepository.exists(group.getId()));
		Assert.isTrue(group.getStudents().size() <= 1);

		final Student principal = this.studentService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(group.getStudents().contains(principal));

		this.groupRepository.delete(group);

		Assert.isTrue(!this.groupRepository.exists(group.getId()));
	}

	public void delete2(final Group group) {

		Assert.notNull(group);

		final Administrator principal = this.administratorService.findByPrincipal();
		Assert.notNull(principal);

		this.groupRepository.delete(group);

	}

	// Other business methods --------------------------------------------------------------------------------

	public Group findOneByStudentAndGroup(final int idStudent, final int idSubject) {

		Group group;

		group = this.groupRepository.findOneByStudentAndSubject(idStudent, idSubject);

		return group;
	}

	public void join(final Group group) {
		Assert.isTrue(this.actorService.isStudent());
		final Student student = (Student) this.actorService.findByPrincipal();
		Assert.isTrue(!student.getGroups().contains(group));
		Assert.isTrue(student.getSubjects().contains(group.getSubject()));
		Assert.isTrue(group.getStartDate().before(Calendar.getInstance().getTime()) && group.getEndDate().after(Calendar.getInstance().getTime()));

		student.getGroups().add(group);
		group.getStudents().add(student);

	}

	public void reconstruct(Group group, BindingResult binding) {
		this.checkStartDate(group.getStartDate(), binding);

	}

	private boolean checkStartDate(final Date startDate, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		final long milliseconds = System.currentTimeMillis() - 100;
		final Date moment = new Date(milliseconds);

		if (startDate.after(moment))
			result = true;
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "group.startDate.mismatch";
			error = new FieldError("group", "startDate", startDate, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

}
