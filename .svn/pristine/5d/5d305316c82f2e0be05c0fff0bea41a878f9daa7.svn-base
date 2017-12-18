
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.BibliographyRecord;
import domain.Subject;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class BibliographyRecordServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private BibliographyRecordService	bibliographyRecordService;

	@Autowired
	private SubjectService				subjectService;


	// Tests
	// ====================================================

	/*
	 * Create a new bibliographyRecord.
	 * 
	 * En este caso de uso un teacher puede crear un bibliographyRecord.
	 */

	public void bibliographyRecordCreateTest(final int subjectId, final String username, final String title, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Subject subject = this.subjectService.findOne(subjectId);

			final BibliographyRecord result = this.bibliographyRecordService.create(subject);

			result.setTitle(title);
			result.setLocator("locatorTest11");
			result.setSubject(subject);

			this.bibliographyRecordService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	/*
	 * Edit a new bibliographyRecord.
	 * 
	 * En este caso de uso un teacher puede editar un bibliographyRecord existente.
	 */

	public void editBibliographyRecordTest(final String username, final String title, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			BibliographyRecord result;

			result = this.bibliographyRecordService.findOne(27);

			result.setTitle(title);

			this.bibliographyRecordService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete a bibliographyRecord.
	 * 
	 * En este caso de uso un teacher puede borrar un bibliographyRecord existente.
	 */

	public void deleteBibliographyRecordTest(final String username, final int bibliographyRecordId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			final BibliographyRecord bibliographyRecord = this.bibliographyRecordService.findOne(bibliographyRecordId);

			this.authenticate(username);

			this.bibliographyRecordService.delete(bibliographyRecord);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverBibliographyRecordCreateTest() {

		final Object testingData[][] = {
			// Crear un bibliographyRecord estando logueado como teacher -> true
			{
				17, "teacher1", "bibliographyRecord de prueba", null
			},
			// Crear un bibliographyRecord sin estar logueado --> false
			{
				17, null, "bibliographyRecord de prueba", IllegalArgumentException.class
			},
			// Crear un bibliographyRecord logueado como student-> false
			{
				17, "student1", "bibliographyRecord de prueba", IllegalArgumentException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.bibliographyRecordCreateTest((int) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);

	}

	@Test
	public void driverEditBibliographyRecordTest() {

		final Object testingData[][] = {
			// Editar una bibliographyRecord estando logueado como el teacher que imparte su asignatura correspondiente -> true
			{
				"teacher1", "bibliographyRecord de prueba", null
			},
			// Editar un bibliographyRecord sin estar logueado -> false
			{
				null, "bibliographyRecord de prueba", IllegalArgumentException.class
			},
			// Editar un bibliographyRecord logueado como student -> false
			{
				"student1", " bibliographyRecord de prueba", IllegalArgumentException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.editBibliographyRecordTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteBibliographyRecordTest() {

		final Object testingData[][] = {
			// Borrar un bibliographyRecord estando logueado como teacher -> true
			{
				"teacher1", 27, null
			},
			// Borrar un bibliographyRecord sin estar logueado -> false
			{
				null, 27, IllegalArgumentException.class
			},
			// Borrar un bibliographyRecord estando logueado como student -> false
			{
				"student1", 27, IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteBibliographyRecordTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
