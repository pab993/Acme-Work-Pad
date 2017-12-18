
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
import domain.Teacher;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private TeacherService			teacherService;

	@Autowired
	private ActorRegisterService	actorRegisterService;


	// Tests
	// ====================================================

	/*
	 * Browse the list of teachers and navigate to the subjects that they teach.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar los profesores que existen en nuestra aplicación y su correspondientes asignaturas.
	 */

	public void listOfTeachersTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.teacherService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * To check the validity of a new teacher in our system, the system must check the username,
	 * the passwords, the name, the surname, the phone, the email and the postal address.
	 * 
	 * En este test, se comprueba el registro de una nueva academia.
	 * Para ello introducimos valores correctos e incorrectos para observar el comportamiento de la aplicación.
	 */

	/*
	 * Register a new teacher.
	 * 
	 * En este caso de uso simularemos el registro de un candidato.
	 */

	public void teacherRegisterTest(final String username, final String password, final String passwordRepeat, final String name, final String surname, final String phone, final String email, final String postalAddress, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Teacher result = this.actorRegisterService.createTeacher();

			Assert.notNull("TeacherTest");
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

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverListTeacherTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				null, null
			},
			// Un student -> true
			{
				"student1", null
			},
			// Una teacher -> true
			{
				"teacher1", null
			},
			// Un administrador -> true
			{
				"admin", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfTeachersTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverTeacherRegisterTest() {

		final Object testingData[][] = {
			// Creacion correcta-> true
			{
				"admin", "teacherTest", "teacherTest", "teacherTestName", "teacherTestSurname", "+ES1234456", "teacherTest@teacherTest.com", "12345", null
			},
			// Todo vacio --> false
			{
				null, null, null, null, null, null, null, null, IllegalArgumentException.class
			},
			// Creacion correcta pero sin estar logueado como admin -> false
			{
				null, "teacherTest", "teacherTest", "teacherTestName", "teacherTestSurname", "ES1234456", "teacherTest@teacherTest.com", "12345", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.teacherRegisterTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (Class<?>) testingData[i][8]);
	}
}
