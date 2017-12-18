
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Folder;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FolderServiceTest extends AbstractTest {

	@Autowired
	MessageService	messageService;

	@Autowired
	ActorService	actorService;

	@Autowired
	FolderService	folderService;


	//En este test vamos a probar el "Eliminar una carpeta" : 
	//un actor puede eliminar una carpeta cuando sea propia y no sea una por defecto
	//un actor no puede eliminar una carpeta que sea por defecto
	//un actor no puede eliminar la carpeta de otro actor
	//alguien que no esté autenticado no puede eliminar una carpeta
	@Test
	public void driverDeleteFolder() {
		final Object testingData[][] = {
			{
				"admin", 41, null
			}, {
				"teacher1", 41, IllegalArgumentException.class
			}, {
				"student1", 41, IllegalArgumentException.class
			}, {
				null, 41, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteFolder((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeleteFolder(final String username, final int folderId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Folder folder = this.folderService.findOne(folderId);
			this.folderService.delete(folder);

			Assert.isTrue(!this.folderService.findAll().contains(folder));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	//En este test vamos a probar el "Crear una carpeta" : 
	//una academy puede enviar un mensaje a otro actor
	//una academy no puede enviarse un mensaje a si mismo
	//	@Test
	//	public void driverCreateFolder() {
	//		final Object testingData[][] = {
	//			{
	//				"academy1", null
	//			}, {
	//				"academy3", null
	//			}
	//		};
	//
	//		for (int i = 0; i < testingData.length; i++)
	//			this.templateCreateFolder((String) testingData[i][0], (Class<?>) testingData[i][1]);
	//	}
	//
	//	protected void templateCreateFolder(final String username, final Class<?> expected) {
	//		Class<?> caught;
	//
	//		caught = null;
	//		try {
	//			super.authenticate(username);
	//			final Folder folder = this.folderService.create(actorService.findByPrincipal());
	//			Collection<Message> messages = new ArrayList<Message>();
	//			folder.setMessages(messages);
	//			folder.setIsSystem(false);
	//			folder.setActor(actorService.findByPrincipal());
	//			folder.setName("test");
	//
	//			folderService.save(folder);
	//
	//			Assert.isTrue(!folderService.findAll().contains(folder));
	//
	//			super.unauthenticate();
	//		} catch (final Throwable oops) {
	//			caught = oops.getClass();
	//		}
	//
	//		super.checkExceptions(expected, caught);
	//	}

	//En este test estamos probando "Editar una carpeta" y comprobamos que:
	//un actor puede editar una carpeta propia y que no sea por defecto,
	//un actor no puede editar una carpeta que sea por defecto,
	//alguien que no esté logueado, no se puede editar un estilo,
	@Test
	public void driverEditFolder() {
		final Object testingData[][] = {
			{
				"admin", 37, "cambio", null
			}, {
				"teacher1", 37, "cambio", IllegalArgumentException.class
			}, {
				"student1", 37, "cambio", IllegalArgumentException.class
			}, {
				null, 37, "cambio", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditFolder((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}
	protected void templateEditFolder(final String username, final int folderId, final String title, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Folder folder = this.folderService.findOne(folderId);
			folder.setName(title);
			this.folderService.save(folder);
			Assert.isTrue(folder.getName().equals("cambio"));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
