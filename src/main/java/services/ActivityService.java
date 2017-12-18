
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.ActivityRepository;
import domain.Activity;
import domain.Subject;
import domain.Teacher;

@Service
@Transactional
public class ActivityService {

	//Managed Repository =============================================================================

	@Autowired
	private ActivityRepository	activityRepository;

	//Supporting Services =============================================================================

	@Autowired
	private TeacherService		teacherService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Activity create(final Subject subject) {
		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(subject));

		final Activity result = new Activity();

		result.setSubject(subject);

		return result;
	}

	public Activity save(final Activity activity) {
		Assert.notNull(activity);

		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(activity.getSubject()));

		Activity result;

		result = this.activityRepository.save(activity);

		return result;
	}

	public void delete(final Activity activity) {

		Assert.notNull(activity);

		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getSubjects().contains(activity.getSubject()));

		this.activityRepository.delete(activity);

	}

	public void delete2(final Activity activity) {

		Assert.notNull(activity);

		this.activityRepository.delete(activity);

	}

	public Collection<Activity> findAll() {
		Collection<Activity> result;

		result = this.activityRepository.findAll();

		return result;
	}

	public Activity findOne(final int activityId) {
		Activity result;

		result = this.activityRepository.findOne(activityId);

		return result;
	}

	//Other Business Methods =========================================================================

	public Collection<Activity> findAllBySubject(final int subjectId) {
		Collection<Activity> result;

		result = this.activityRepository.findAllBySubject(subjectId);

		return result;

	}

	public void reconstruct(final Activity activity, final BindingResult binding) {
		this.checkStartDate(activity.getStartDate(), binding);
		this.checkStartAndEndDate(activity.getStartDate(), activity.getEndDate(), binding);

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
			codigos[0] = "activity.date.mismatch";
			error = new FieldError("activity", "endDate", endDate, false, codigos, null, "");
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
			codigos[0] = "activity.startDate.mismatch";
			error = new FieldError("activity", "startDate", startDate, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

}
