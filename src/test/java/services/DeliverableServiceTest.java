
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Assignment;
import domain.Deliverable;
import domain.Group;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DeliverableServiceTest extends AbstractTest {

	// Attributes -----------------------------------------------------------
	@Autowired
	private DeliverableService	deliverableService;

	@Autowired
	private GroupService		groupService;

	@Autowired
	private AssignmentService	assignmentService;


	// Colección de test para probar el requisito 13.1, enrol a subejct.
	// Los tests comprueban que:
	// Un actor autenticado como Student puede subir un entregable correctamente.
	// Un actor autenticado como Student no puede subir un entregable si la tarea está fuera de fecha.
	// Un actor autenticado como Student no puede subir un entregable si no está inscrito en ese grupo.
	// Un actor no autenticado no puede unirse a un grupo.
	// Un actor autenticado como Admin no puede unirse a un grupo.

	@Test
	public void driverSubmitDeliverable() {
		final Object testingData[][] = {
			{
				"student1", 28, 19, null
			}, {
				"student3", 29, 20, IllegalArgumentException.class
			}, {
				"student1", 29, 20, IllegalArgumentException.class
			}, {
				null, 30, 21, IllegalArgumentException.class
			}, {
				"admin", 30, 21, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSubmitDeliverable((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateSubmitDeliverable(final String username, final int groupId, final int assignmentId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);

			final Group group = groupService.findOne(groupId);
			final Assignment assignment = assignmentService.findOne(assignmentId);
			Deliverable deliverable = deliverableService.create(group);
			deliverable.setAssignment(assignment);
			deliverable.setContents("Content");
			deliverable.setAttachment("https://ev.us.es/bbcswebdav/pid-2436249-dt-content-rid-8094163_1/courses/201617-2050018-205-EC/Deliverable%281%29.pdf");
			Deliverable result = deliverableService.save(deliverable);
			Assert.notNull(result);
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
