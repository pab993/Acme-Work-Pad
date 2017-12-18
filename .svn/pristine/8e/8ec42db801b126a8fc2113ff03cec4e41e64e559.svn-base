
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Group;
import domain.Student;
import domain.Subject;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GroupServiceTest extends AbstractTest {

	// Attributes -----------------------------------------------------------
	@Autowired
	private SubjectService	subjectService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private GroupService	groupService;


	// Colección de test para probar el requisito 13.2, crear un grupo.
	// Los tests comprueban que:
	// Un actor autenticado como Student puede crear un grupo en una asignatura en la que está inscrito.
	// Un actor autenticado como Student no puede crear un grupo si ya está en un grupo de esa asignatura.
	// Un actor autenticado como Student no puede crear un grupo en una asignatura en la que no está inscrito.
	// Un actor no autenticado no puede crear un grupo.
	// Un actor autenticado como Admin no puede crear un grupo.

	@Test
	public void driverCreateGroup() {
		final Object testingData[][] = {
			{
				"student2", 17, null
			}, {
				"student1", 16, IllegalArgumentException.class
			}, {
				"student2", 16, IllegalArgumentException.class
			}, {
				null, 17, IllegalArgumentException.class
			}, {
				"admin", 17, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateGroup((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateCreateGroup(final String username, final int subjectId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Subject subject = subjectService.findOne(subjectId);
			Group group = groupService.create(subject);
			group.setDescription("Descripcion");
			group.setName("Nombre");

			group.setStartDate(new Date(01 / 01 / 17));
			group.setEndDate(new Date(02 / 02 / 18));
			Group result = groupService.save(group);
			Assert.isTrue(groupService.findAll().contains(result));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	// Colección de test para probar el requisito 13.1, enrol a subejct.
	// Los tests comprueban que:
	// Un actor autenticado como Student puede unirse a un grupo correctamente.
	// Un actor autenticado como Student no puede unirse a un grupo en el que ya está inscrito.
	// Un actor autenticado como Student no puede unirse a un grupo si no está inscrito en la asignatura de ese grupo.
	// Un actor no autenticado no puede unirse a un grupo.
	// Un actor autenticado como Admin no puede unirse a un grupo.

	@Test
	public void driverJoinGroup() {
		final Object testingData[][] = {
			{
				"student2", 29, null
			}, {
				"student1", 28, IllegalArgumentException.class
			}, {
				"student1", 29, IllegalArgumentException.class
			}, {
				null, 30, IllegalArgumentException.class
			}, {
				"admin", 30, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateJoinGroup((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateJoinGroup(final String username, final int groupId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);

			final Group group = groupService.findOne(groupId);
			groupService.join(group);
			Student student = (Student) actorService.findByPrincipal();
			Assert.isTrue(group.getStudents().contains(student));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
