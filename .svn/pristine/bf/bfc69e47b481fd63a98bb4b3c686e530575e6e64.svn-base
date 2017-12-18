
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;
import domain.Message;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageServiceTest extends AbstractTest {

	@Autowired
	MessageService	messageService;

	@Autowired
	ActorService	actorService;

	@Autowired
	FolderService	folderService;


	//En este test vamos a probar el "Eliminar un mensaje" : 
	//un adminpuede eliminar un mensaje que sea propio
	//un actor(student) no puede eliminar un mensaje que no sea propio
	//un actor(teacher) no puede eliminar un mensaje que no sea propio
	//un actor(teacher) no puede eliminar un mensaje que no sea propio
	//un no autenticado no puede eliminar un mensaje
	@Test
	public void driverDeleteMessage() {
		final Object testingData[][] = {
			{
				"admin", 66, null
			}, {
				"student2", 66, IllegalArgumentException.class
			}, {
				"teacher1", 66, IllegalArgumentException.class
			}, {
				"teacher2", 66, IllegalArgumentException.class
			}, {
				null, 66, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteMessage((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeleteMessage(final String username, final int messageId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Message message = this.messageService.findOne(messageId);
			this.messageService.delete(message);

			Assert.isTrue(message.getFolder().getName().equals("trashbox"));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	//En este test vamos a probar el "Mover un mensaje a otra carpeta" : 
	//un admin puede mover un mensaje que sea propio
	//un admin no puede mover un mensaje propio a la carpeta en la que ya se encuentra
	//un actor(teacher1) no puede mover un mensaje que no sea propio
	//un actor(student) no puede mover un mensaje que no sea propio
	//un no autenticado no puede mover un mensaje
	@Test
	public void driverMoveMessageFolder() {
		final Object testingData[][] = {
			//			{
			//				"admin", 66, 39, null
			//			}, 
			{
				"admin", 66, 38, IllegalArgumentException.class
			}, {
				"teacher1", 66, 41, IllegalArgumentException.class
			}, {
				"student1", 66, 41, IllegalArgumentException.class
			}, {
				null, 66, 41, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateMoveMessageFolder((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateMoveMessageFolder(final String username, final int messageId, final int folderId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Message message = this.messageService.findOne(messageId);
			final Folder folder = this.folderService.findOne(folderId);
			this.messageService.moveMessage(folder, message);

			Assert.isTrue(message.getFolder().getName().equals("spambox1"));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	//En este test vamos a probar el "Enviar un mensaje a otro actor" : 
	//un admin puede enviar un mensaje a otro actor
	//un admin no puede enviarse un mensaje a si mismo
	@Test
	public void driverSendMessage() {
		final Object testingData[][] = {
			{
				"admin", 11, null
			}, {
				"admin", 9, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSendMessage((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateSendMessage(final String username, final int receiveId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Message message = this.messageService.create();
			final Actor actor = this.actorService.findOne(receiveId);
			final Collection<Actor> receives = new ArrayList<Actor>();
			receives.add(actor);
			message.setReceives(receives);
			message.setBody("cuerpo mensaje");
			message.setSubject("asunto mensaje");
			this.messageService.sendMessage(message);

			Assert.isTrue(message.getReceives().contains(actor));

			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
