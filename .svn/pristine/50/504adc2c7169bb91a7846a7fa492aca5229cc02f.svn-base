
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Student;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private StudentService			studentService;

	@Autowired
	private ActorRegisterService	actorRegisterService;


	// Tests
	// ====================================================

	/*
	 * Browse the catalogue of academies and navigate to the courses that they offer.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar las academias que existen en nuestra aplicación.
	 */

	public void listOfStudentsTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.studentService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * To check the validity of a new student in our system, the system must check the username,
	 * the passwords, the name, the surname, the phone, the email and the postal address.
	 * 
	 * En este test, se comprueba el registro de una nueva academia.
	 * Para ello introducimos valores correctos e incorrectos para observar el comportamiento de la aplicación.
	 */

	/*
	 * Register a new student.
	 * 
	 * En este caso de uso simularemos el registro de un candidato.
	 */

	public void studentRegisterTest(final String username, final String password, final String passwordRepeat, final String name, final String surname, final String phone, final String email, final String postalAddress, final Class<?> expected) {
		Class<?> caught = null;

		try {

			final Student result = this.actorRegisterService.createStudent();

			Assert.notNull(username);
			Assert.notNull(password);
			Assert.notNull(passwordRepeat);
			Assert.isTrue(password.equals(passwordRepeat));
			Assert.notNull(phone);
			Assert.isTrue(phone.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"));
			Assert.notNull(email);
			Assert.notNull(name);
			Assert.notNull(surname);

			result.getUserAccount().setUsername(username);
			result.setName(name);
			result.setSurname(surname);
			result.setPhone(phone);
			result.setEmail(email);
			result.setPostalAddress(postalAddress);
			result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(password, null));

			this.actorRegisterService.save(result);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverListStudentTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				null, null
			},
			// Un dancer -> true
			{
				"student1", null
			},
			// Una student -> true
			{
				"student1", null
			},
			// Un administrador -> true
			{
				"admin", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfStudentsTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverStudentRegisterTest() {

		final Object testingData[][] = {
			// Creacion correcta-> true
			{
				"studentTest", "studentTest", "studentTest", "studentTestName", "studentTestSurname", "+ES1234456", "studentTest@studentTest.com", "12345", null
			},
			// Todo vacio --> false
			{
				null, null, null, null, null, null, null, null, IllegalArgumentException.class
			},
			// Password null -> false
			{
				"studentTest", null, "studentTest", "studentTestName", "studentTestSurname", "ES1234456", "studentTest@studentTest.com", "12345", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.studentRegisterTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (Class<?>) testingData[i][8]);
	}
}
