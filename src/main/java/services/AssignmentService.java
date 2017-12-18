
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.AssignmentRepository;
import domain.Administrator;
import domain.Assignment;
import domain.Deliverable;
import domain.Subject;
import domain.Teacher;

@Service
@Transactional
public class AssignmentService {

	//Managed Repository =============================================================================

	@Autowired
	private AssignmentRepository	assignmentRepository;

	//Supporting Services =============================================================================

	@Autowired
	private TeacherService			teacherService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private DeliverableService		deliverableService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Assignment create(final Subject subject) {
		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(subject));

		final Assignment result = new Assignment();

		result.setSubject(subject);

		return result;
	}

	public Assignment save(final Assignment assignment) {
		Assert.notNull(assignment);

		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(assignment.getSubject()));

		Assignment result;

		result = this.assignmentRepository.save(assignment);

		return result;
	}

	public void delete(final Assignment assignment) {

		Assert.notNull(assignment);

		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(assignment.getSubject()));
		//		Assert.isTrue(assignment.getDeliverables().isEmpty());
		if (!assignment.getDeliverables().isEmpty()) {
			for (Deliverable deliverable : assignment.getDeliverables()) {
				deliverableService.delete(deliverable);
			}
		}

		this.assignmentRepository.delete(assignment);

	}

	public void delete2(final Assignment assignment) {

		Assert.notNull(assignment);

		final Administrator principal = this.administratorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(assignment.getSubject()));
		//		Assert.isTrue(assignment.getDeliverables().isEmpty());
		for (Deliverable deliverable : assignment.getDeliverables()) {
			deliverableService.delete(deliverable);
		}

		this.assignmentRepository.delete(assignment);

	}

	public Collection<Assignment> findAll() {
		Collection<Assignment> result;

		result = this.assignmentRepository.findAll();

		return result;
	}

	public Assignment findOne(final int assignmentId) {
		Assignment result;

		result = this.assignmentRepository.findOne(assignmentId);

		return result;
	}

	//Other Business Methods =========================================================================

	public Collection<Assignment> findAllBySubject(final int subjectId) {
		Collection<Assignment> result;

		result = this.assignmentRepository.findAllBySubject(subjectId);

		return result;

	}

	public void reconstruct(final Assignment assignment, final BindingResult binding) {

		this.checkStartDate(assignment.getStartDate(), binding);
		this.checkStartAndEndDate(assignment.getStartDate(), assignment.getEndDate(), binding);

	}

	private boolean checkStartAndEndDate(final Date startDate, final Date endDate, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (startDate.before(endDate))
			result = true;
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "assignment.date.mismatch";
			error = new FieldError("assignment", "endDate", endDate, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
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
			codigos[0] = "assignment.startDate.mismatch";
			error = new FieldError("assignment", "startDate", startDate, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

}
