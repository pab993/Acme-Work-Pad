
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.SeminaryRepository;
import domain.Seminary;
import domain.Student;
import domain.Teacher;

@Service
@Transactional
public class SeminaryService {

	@Autowired
	private SeminaryRepository	seminaryRepository;

	@Autowired
	private TeacherService		teacherService;

	@Autowired
	private ActorService		actorService;


	public SeminaryService() {
		super();
	}

	// CRUD methods --------------------------------------------------------------------------------
	public Seminary create() {

		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);

		Seminary result;

		final Collection<Student> students = new HashSet<Student>();

		result = new Seminary();
		result.setTeacher(principal);
		result.setStudents(students);

		return result;
	}

	public Seminary findOne(final int seminaryId) {
		return this.seminaryRepository.findOne(seminaryId);
	}

	public Collection<Seminary> findAll() {
		return this.seminaryRepository.findAll();
	}

	public Seminary save(final Seminary seminary) {

		Assert.notNull(seminary);
		Assert.isTrue(this.teacherService.isAuthenticated());
		Assert.isTrue(seminary.getTeacher().equals(this.teacherService.findByPrincipal()));

		Seminary res;

		res = this.seminaryRepository.save(seminary);

		return res;
	}
	public void delete(final Seminary seminary) {

		Assert.notNull(seminary);
		Assert.isTrue(this.seminaryRepository.exists(seminary.getId()));
		//		Assert.isTrue(seminary.getStudents().isEmpty());

		this.seminaryRepository.delete(seminary);

		Assert.isTrue(!this.seminaryRepository.exists(seminary.getId()));
	}

	// Other business methods --------------------------------------------------------------------------------

	public Collection<Seminary> findByTeacher(final int idTeacher) {
		return this.seminaryRepository.findByTeacher(idTeacher);
	}

	public void reconstruct(final Seminary seminary, final BindingResult binding) {

		this.checkMoment(seminary.getMoment(), binding);

	}

	private boolean checkMoment(final Date moment, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		final long milliseconds = System.currentTimeMillis() - 100;
		final Date moment2 = new Date(milliseconds);

		if (moment.after(moment2))
			result = true;
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "seminary.moment.mismatch";
			error = new FieldError("seminary", "moment", moment, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

	public void register(Seminary seminary) {
		Assert.isTrue(actorService.isStudent());
		Assert.notNull(seminary);
		Assert.isTrue(seminary.getSeats() > 0);
		Student student = (Student) actorService.findByPrincipal();
		Assert.isTrue(!seminary.getStudents().contains(student));

		seminary.getStudents().add(student);
		seminary.setSeats(seminary.getSeats() - 1);

	}

	public void unregister(Seminary seminary) {
		Assert.isTrue(actorService.isStudent());
		Assert.notNull(seminary);
		Student student = (Student) actorService.findByPrincipal();
		Assert.isTrue(seminary.getStudents().contains(student));

		seminary.getStudents().remove(student);
		seminary.setSeats(seminary.getSeats() + 1);

	}

}
