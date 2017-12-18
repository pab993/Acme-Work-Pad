
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;
import domain.Student;
import domain.Subject;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SubjectServiceTest extends AbstractTest {

	// Attributes -----------------------------------------------------------
	@Autowired
	private SubjectService			subjectService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	// Tests
	// ====================================================

	/*
	 * Browse the list of teachers and navigate to the subjects that they teach.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar los profesores que existen en nuestra aplicación y su correspondientes asignaturas.
	 */

	public void listOfSubjectsTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.subjectService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Manage a subject, which includes listing, displaying, editing and delete it. Note that only the manager who registers a subject can edit or delete it.
	 * 
	 * En este test, se comprueba la creación de una nueva lección, así como su edición y su eliminación. Estas acciones pueden realizarla el actor 'administrator'. Luego, si cualquier otro
	 * actor realiza dicha acción, el sistema impedirá la creación, edicion o borrado del mismo. Para ello
	 * introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Create a new subject.
	 * 
	 * En este caso de uso simularemos la creación de una asignatura.
	 */

	@SuppressWarnings("deprecation")
	public void subjectCreateTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Administrator administrator = this.administratorService.findByPrincipal();
			final Subject result = this.subjectService.create(administrator);

			result.setTitle("titleTest");
			result.setTicker("ss-12399");
			result.setSyllabus("syllabusTest");
			result.setSeats(3);

			this.subjectService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Edit a subject.
	 * 
	 * En este caso de uso un administrator puede editar una asignatura.
	 */

	public void editSubjectTest(final String username, final String name, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Subject result;

			result = this.subjectService.findOne(18);

			result.setTitle(name);

			this.subjectService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete a subject.
	 * 
	 * En este caso de uso un administrator puede borrar una asignatura existente.
	 */

	public void deleteSubjectTest(final String username, final int subjectId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Subject result;

			result = this.subjectService.findOne(subjectId);

			this.subjectService.delete(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Colección de test para probar el requisito 13.1, enrol a subejct.
	// Los tests comprueban que:
	// Un actor autenticado como Student puede inscribirse en una asignatura correctamente.
	// Un actor autenticado como Student no puede inscribirse en una asignatura que no tenga plazas disponibles.
	// Un actor autenticado como Student no puede inscribirse en una asignatura en la que ya esté inscrita.
	// Un actor no autenticado no puede inscribirse en una asignatura.
	// Un actor autenticado como Admin no puede inscribirse en una asignatura.

	protected void templateEnrolSubject(final String username, final int subjectId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			//			
			final Subject subject = this.subjectService.findOne(subjectId);
			this.subjectService.enrol(subject);
			final Student student = (Student) this.actorService.findByPrincipal();
			Assert.isTrue(subject.getStudents().contains(student));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
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
			// Un teacher -> true
			{
				"teacher1", null
			},
			// Un administrador -> true
			{
				"admin", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfSubjectsTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverSubjectCreateTest() {

		final Object testingData[][] = {
			// Crear una asignatura estando logueado como teacher -> false
			{
				"teacher1", IllegalArgumentException.class
			},
			// Crear una asignatura sin estar logueado --> false
			{
				null, IllegalArgumentException.class
			},
			// Crear una asignatura logueado como customer -> false
			{
				"customer1", IllegalArgumentException.class
			},
			// Crear una asignatura logueado como admin -> true
			{
				"admin", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.subjectCreateTest((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	@Test
	public void driverEditSubjectTest() {

		final Object testingData[][] = {
			// Editar una asignatura sin estar logueado -> false
			{
				null, "title2", IllegalArgumentException.class
			},
			// Editar una asignatura logueado como teacher -> false
			{
				"teacher1", "title3", IllegalArgumentException.class
			},
			// Editar una asignatura logueado como admin -> true
			{
				"admin", "title3", null
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editSubjectTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteSubjectTest() {

		final Object testingData[][] = {
			// Borrar una asignatura estando logueado como teacher -> false
			{
				"teacher1", 16, IllegalArgumentException.class
			},
			// Borrar una asignatura sin estar logueado -> false
			{
				null, 16, IllegalArgumentException.class
			},
			// Borrar una asignatura 
			{
				"admin", 16, null
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteSubjectTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverEnrolSubject() {
		final Object testingData[][] = {
			{
				"student2", 18, null
			}, {
				"student3", 16, null
			}, {
				"student1", 18, IllegalArgumentException.class
			}, {
				null, 17, IllegalArgumentException.class
			}, {
				"admin", 17, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEnrolSubject((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
