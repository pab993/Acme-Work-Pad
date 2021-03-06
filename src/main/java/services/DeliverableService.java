
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.DeliverableRepository;
import domain.Actor;
import domain.Assignment;
import domain.Deliverable;
import domain.Group;
import domain.Student;
import domain.Subject;
import domain.Teacher;

@Service
@Transactional
public class DeliverableService {

	//Managed Repository =============================================================================

	@Autowired
	private DeliverableRepository	deliverableRepository;

	@Autowired
	private GroupService			groupService;

	@Autowired
	private StudentService			studentService;

	@Autowired
	private TeacherService			teacherService;

	@Autowired
	private ActorService			actorService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Deliverable findOne(final int deliverableId) {
		Assert.notNull(deliverableId);

		Deliverable result;
		result = this.deliverableRepository.findOne(deliverableId);

		return result;
	}

	//	public Deliverable create(final Assignment assignment) {
	//
	//		Assert.notNull(assignment);
	//
	//		final Student principal = this.studentService.findByPrincipal();
	//		Assert.notNull(principal);
	//		Assert.isTrue(principal.getSubjects().contains(assignment.getSubject()));
	//
	//		final Group group = this.groupService.findOneByStudentAndGroup(principal.getId(), assignment.getSubject().getId());
	//		Assert.notNull(group);
	//
	//		Deliverable result;
	//
	//		result = new Deliverable();
	//		result.setAssignment(assignment);
	//		result.setGroup(group);
	//
	//		return result;
	//	}

	public Deliverable create(final Group group) {

		Assert.isTrue(actorService.isStudent());
		Assert.notNull(group);

		final Student principal = this.studentService.findByPrincipal();
		Assert.isTrue(group.getStudents().contains(principal));
		Assert.notNull(principal);

		Deliverable result;

		result = new Deliverable();
		result.setGroup(group);

		return result;
	}
	public Deliverable save(final Deliverable deliverable) {
		Deliverable result;
		try {
			Assert.notNull(deliverable);

			final Student principal = this.studentService.findByPrincipal();
			Assert.notNull(principal);
			Assert.isTrue(deliverable.getGroup().getStudents().contains(principal));
			Assert.isTrue(deliverable.getAssignment().getStartDate().before(Calendar.getInstance().getTime()) && deliverable.getAssignment().getEndDate().after(Calendar.getInstance().getTime()));

			result = this.deliverableRepository.save(deliverable);
		} catch (Throwable oops) {
			result = null;
		}
		return result;
	}

	public Deliverable saveGrade(final Deliverable deliverable) {
		Assert.notNull(deliverable);

		Deliverable result;

		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(deliverable.getAssignment().getSubject()));
		Assert.isTrue(!deliverable.getGrade().equals(null));

		result = this.deliverableRepository.save(deliverable);

		return result;
	}

	public void delete(final Deliverable deliverable) {

		Assert.notNull(deliverable);

		Actor principal;

		principal = actorService.findByPrincipal();
		Assert.notNull(principal);
		//		Assert.isTrue(deliverable.getGroup().getStudents().contains(principal));
		//		Assert.isTrue(deliverable.getGrade().equals(null));

		this.deliverableRepository.delete(deliverable);
	}

	//Other Business Methods =========================================================================

	public Collection<Deliverable> findAllByGroup(final Group group) {
		Assert.notNull(group);
		Collection<Deliverable> result;

		result = this.deliverableRepository.findAllByGroup(group.getId());

		return result;
	}

	public Collection<Deliverable> findAllByGroupAndStudent(final Group group, final Student student) {
		Assert.notNull(group);
		Collection<Deliverable> result;
		final Collection<Deliverable> result2 = new ArrayList<Deliverable>();

		result = this.deliverableRepository.findAllByGroup(group.getId());
		for (final Deliverable dl : result)
			if (dl.getGroup().getStudents().contains(student))
				result2.add(dl);

		return result2;
	}

	public Collection<Deliverable> findAllByTeacher(final Teacher teacher) {
		Assert.notNull(teacher);

		final Collection<Deliverable> result = new HashSet<Deliverable>();

		for (final Subject s : teacher.getSubjects())
			for (final Assignment a : s.getAssignments())
				result.addAll(a.getDeliverables());

		return result;

	}

	public void reconstruct(Deliverable deliverable, BindingResult binding) {

		this.checkAssignment(deliverable.getAssignment(), binding);

	}

	private boolean checkAssignment(Assignment assignment, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (assignment == null)
			result = true;
		else
			result = false;

		if (result) {
			codigos = new String[1];
			codigos[0] = "assignment.null";
			error = new FieldError("deliverable", "assignment", assignment, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

}
