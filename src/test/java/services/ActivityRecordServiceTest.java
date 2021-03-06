
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ActivityRecord;
import domain.Actor;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActivityRecordServiceTest extends AbstractTest {

	// Attributes -----------------------------------------------------------
	@Autowired
	private ActivityRecordService	activityRecordService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	// Tests
	// ====================================================

	/*
	 * Browse the list of teachers and navigate to the activityRecords that they teach.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar los profesores que existen en nuestra aplicaci�n y su correspondientes asignaturas.
	 */

	public void listOfActivityRecordsTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.activityRecordService.findAllByActor(9);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Manage his or her activity records, which includes listing, creating, editing, and de-leting them.
	 * 
	 * En este test, se comprueba la creaci�n de un nuevo registro de actividad, as� como su edici�n y su eliminaci�n. Estas acciones pueden realizarla cualquier 'actor'. Para ello
	 * introducimos valores correctos e incorrectos para observa el comportamiento de la aplicaci�n.
	 */

	/*
	 * Create a new activityRecord.
	 * 
	 * En este caso de uso simularemos la creaci�n de un registro de actividad.
	 */

	@SuppressWarnings("deprecation")
	public void activityRecordCreateTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Actor actor = this.actorService.findOne(9);
			final ActivityRecord result = this.activityRecordService.create(actor);

			result.setDescription("description1");
			this.activityRecordService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	/*
	 * Edit a activityRecord.
	 * 
	 * En este caso de uso un actor puede editar un registro de actividad.
	 */

	public void editActivityRecordTest(final String username, final String description, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			ActivityRecord result;

			Assert.notNull(actorService.findByPrincipal());

			result = this.activityRecordService.findOne(74);

			result.setDescription(description);

			this.activityRecordService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete a activityRecord.
	 * 
	 * En este caso de uso cualquier actor puede borrar su registro de actividad.
	 */

	public void deleteActivityRecordTest(final String username, final int activityRecordId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			ActivityRecord result;

			result = this.activityRecordService.findOne(activityRecordId);

			this.activityRecordService.delete(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverListActivityRecordTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, IllegalArgumentException.class
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
			this.listOfActivityRecordsTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverActivityRecordCreateTest() {

		final Object testingData[][] = {
			// Crear un registro de actividad estando logueado como teacher -> false
			{
				"teacher1", IllegalArgumentException.class
			},
			// Crear un registro de actividad sin estar logueado --> false
			{
				null, IllegalArgumentException.class
			},
			// Crear un registro de actividad logueado como customer -> false
			{
				"customer1", IllegalArgumentException.class
			},
			// Crear un registro de actividad logueado como admin -> true
			{
				"admin", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.activityRecordCreateTest((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	@Test
	public void driverEditActivityRecordTest() {

		final Object testingData[][] = {
			// Editar un registro de actividad sin estar logueado pero no pertenece a �ste  -> false
			{
				null, "description2", IllegalArgumentException.class
			},
			// Editar un registro de actividad logueado como teacher -> true
			{
				"teacher1", "description3", null
			},
			// Editar un registro de actividad logueado como admin y pertenece a �ste -> true
			{
				"admin", "description3", null
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editActivityRecordTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteActivityRecordTest() {

		final Object testingData[][] = {
			// Borrar un registro de actividad estando logueado como teacher pero no pertenece a �ste -> false
			{
				"teacher1", 74, IllegalArgumentException.class
			},
			// Borrar un registro de actividad sin estar logueado y no pertenece a �ste -> false
			{
				null, 74, IllegalArgumentException.class
			},
			// Borrar un registro de actividad estando logueado como admin y pertenece a �ste  -> true
			{
				"admin", 74, null
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteActivityRecordTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
