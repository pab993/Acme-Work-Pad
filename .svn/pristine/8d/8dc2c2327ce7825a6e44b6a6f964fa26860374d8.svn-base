
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StudentRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Student;

@Service
@Transactional
public class StudentService {

	//Managed Repository =============================================================================

	@Autowired
	private StudentRepository		studentRepository;

	@Autowired
	private AdministratorService	administratorService;


	//Services
	// ===============================================================================================

	//SCRUDs Methods
	//===============================================================================================

	public Student save(final Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getUserAccount());

		Student result;

		result = this.studentRepository.save(student);

		return result;
	}

	public void delete(final Student student) {
		Assert.notNull(student);
		Administrator principal;

		principal = this.administratorService.findByPrincipal();
		Assert.notNull(principal);

		this.studentRepository.delete(student);
	}

	public void delete2(final Student student) {

		Assert.notNull(student);

		final Administrator principal = this.administratorService.findByPrincipal();
		Assert.notNull(principal);

		this.studentRepository.delete(student);

	}

	//Other Business Methods =========================================================================

	public Student findByPrincipal() {
		Student result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Student findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Student result;

		result = this.studentRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Student findOne(final int studentId) {
		Student result;

		result = this.studentRepository.findOne(studentId);

		return result;
	}

	public Collection<Student> findAll() {

		Collection<Student> result = new HashSet<Student>();

		result = this.studentRepository.findAll();

		return result;
	}

	public boolean isAuthenticated() {
		try {
			Assert.notNull(LoginService.getPrincipal());
		} catch (final Exception e) {
			return false;
		}

		return true;
	}

	public void checkStudentIsAuthenticated() {
		Assert.notNull(LoginService.getPrincipal());
	}

}
