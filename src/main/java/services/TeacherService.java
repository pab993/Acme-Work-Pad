
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TeacherRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Subject;
import domain.Teacher;

@Service
@Transactional
public class TeacherService {

	//Managed Repository =============================================================================

	@Autowired
	private TeacherRepository		teacherRepository;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SubjectService			subjectService;


	//Services
	// ===============================================================================================

	//SCRUDs Methods
	//===============================================================================================

	public Teacher save(final Teacher teacher) {
		Assert.notNull(teacher);
		Assert.notNull(teacher.getUserAccount());

		Teacher result;

		result = this.teacherRepository.save(teacher);

		return result;
	}

	//Other Business Methods =========================================================================

	public Teacher findByPrincipal() {
		Teacher result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Teacher findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Teacher result;

		result = this.teacherRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Teacher findOne(final int teacherId) {
		Teacher result;

		result = this.teacherRepository.findOne(teacherId);

		return result;
	}

	public Collection<Teacher> findAll() {

		Collection<Teacher> result = new HashSet<Teacher>();

		result = this.teacherRepository.findAll();

		return result;
	}

	public Teacher findOneBySubject(final int subjectId) {

		Teacher result;

		result = this.teacherRepository.findOneBySubject(subjectId);

		return result;
	}

	public void addSubject(final Teacher teacher, final Subject subject) {
		final Administrator principal = this.administratorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		Assert.notNull(principal);

		teacher.getSubjects().add(subject);
		subject.setTeacher(teacher);
		this.teacherRepository.save(teacher);
	}

	public boolean isAuthenticated() {
		try {
			Assert.notNull(LoginService.getPrincipal());
		} catch (final Exception e) {
			return false;
		}

		return true;
	}

	public void checkTeacherIsAuthenticated() {
		Assert.notNull(LoginService.getPrincipal());
	}

	//Dashboard -----------------------------------------------

	public Collection<Teacher> findTeacherMoreSubject() {

		Collection<Teacher> result;

		result = this.teacherRepository.findTeacherMoreSubject();

		return result;
	}

	public Collection<Teacher> findTeacherLessSubject() {

		Collection<Teacher> result;

		result = this.teacherRepository.findTeacherLessSubject();

		return result;
	}

	public Collection<Teacher> findTeachersPlusMinus10AvgSubjects() {

		Collection<Teacher> result;

		result = this.teacherRepository.findTeachersPlusMinus10AvgSubjects();

		return result;

	}

	public Object[] minMaxAvgSubjectsByTeacher() {

		Object[] result;

		result = this.teacherRepository.minMaxAvgSubjectsByTeacher();

		return result;

	}

	public Object[] minMaxAvgSeminariesByTeacher() {

		Object[] result;

		result = this.teacherRepository.minMaxAvgSeminariesByTeacher();

		return result;

	}

	public Collection<Teacher> findTeachersPlusMinus10AvgSeminaries() {

		Collection<Teacher> result;

		result = this.teacherRepository.findTeachersPlusMinus10AvgSeminaries();

		return result;

	}

	//	====================
	//	NUEVO
	//	====================
	public Collection<Teacher> findAllWithoutSubject() {
		Collection<Teacher> result;

		result = this.teacherRepository.findAllWithoutSubject();

		return result;
	}

}
