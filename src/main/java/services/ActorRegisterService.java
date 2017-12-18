
package services;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.ActorRegisterRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Actor;
import domain.Folder;
import domain.Student;
import domain.Teacher;
import forms.ActorRegisterForm;

@Service
@Transactional
public class ActorRegisterService {

	//Managed Repository =============================================================================

	@Autowired
	private ActorRegisterRepository	actorRegisterRepository;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private FolderService			folderService;


	//Simple CRUD methods ============================================================================

	public Student createStudent() {
		Student result;
		UserAccount userAccount;
		Authority authority;

		authority = new Authority();
		userAccount = new UserAccount();

		authority.setAuthority("STUDENT");
		userAccount.addAuthority(authority);

		result = new Student();

		result.setUserAccount(userAccount);

		return result;
	}

	public Teacher createTeacher() {
		Teacher result;
		UserAccount userAccount;
		Authority authority;

		authority = new Authority();
		userAccount = new UserAccount();

		authority.setAuthority("TEACHER");
		userAccount.addAuthority(authority);

		result = new Teacher();

		result.setUserAccount(userAccount);

		return result;
	}

	public Actor save(final Actor actorRegister) {
		Assert.notNull(actorRegister);
		Assert.notNull(actorRegister.getUserAccount());
		Actor result;

		result = this.actorRegisterRepository.save(actorRegister);

		final Folder inbox = this.folderService.createInFolder(result);
		this.folderService.saveCreate(inbox);
		final Folder outbox = this.folderService.createOutFolder(result);
		this.folderService.saveCreate(outbox);
		final Folder spambox = this.folderService.createSpamFolder(result);
		this.folderService.saveCreate(spambox);
		final Folder trashbox = this.folderService.createTrashFolder(result);
		this.folderService.saveCreate(trashbox);

		return result;
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

		result = this.actorRegisterRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Student reconstructStudent(final ActorRegisterForm actorRegisterForm, final BindingResult binding) {

		Student result;

		result = this.createStudent();
		result.getUserAccount().setUsername(actorRegisterForm.getUsername());
		result.setName(actorRegisterForm.getName());
		result.setSurname(actorRegisterForm.getSurname());
		result.setPhone(actorRegisterForm.getPhone());
		result.setEmail(actorRegisterForm.getEmail());
		result.setPostalAddress(actorRegisterForm.getPostalAddress());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(actorRegisterForm.getPassword(), null));

		//		comprobarPhone(actorRegisterForm.getPhone(), binding);
		this.comprobarContrasena(actorRegisterForm.getPassword(), actorRegisterForm.getRepeatPassword(), binding);
		this.comprobarPostalAddress(actorRegisterForm.getPostalAddress(), binding);
		this.comprobarUsuarioUnico(actorRegisterForm.getUsername(), binding);

		return result;
	}

	public Teacher reconstructTeacher(final ActorRegisterForm actorRegisterForm, final BindingResult binding) {

		Teacher result;

		result = this.createTeacher();
		result.getUserAccount().setUsername(actorRegisterForm.getUsername());
		result.setName(actorRegisterForm.getName());
		result.setSurname(actorRegisterForm.getSurname());
		result.setPhone(actorRegisterForm.getPhone());
		result.setEmail(actorRegisterForm.getEmail());
		result.setPostalAddress(actorRegisterForm.getPostalAddress());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(actorRegisterForm.getPassword(), null));

		//		comprobarPhone(actorRegisterForm.getPhone(), binding);
		this.comprobarContrasena(actorRegisterForm.getPassword(), actorRegisterForm.getRepeatPassword(), binding);
		this.comprobarPostalAddress(actorRegisterForm.getPostalAddress(), binding);
		this.comprobarUsuarioUnico(actorRegisterForm.getUsername(), binding);

		return result;
	}

	private boolean comprobarUsuarioUnico(final String username, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result = false;

		final UserAccount userAccount = this.userAccountRepository.findByUsername(username);

		if (userAccount == null)
			result = true;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "actorRegister.username.unique";
			//			error = new FieldError("cadidateForm", "password", "actorRegister.paswword.mismatch");
			error = new FieldError("actorRegisterForm", "username", username, false, codigos, null, "");
			binding.addError(error);
		}
		return result;
	}

	private boolean comprobarContrasena(final String password, final String passwordRepeat, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordRepeat))
			result = password.equals(passwordRepeat);
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "actorRegister.password.mismatch";
			//			error = new FieldError("cadidateForm", "password", "actorRegister.paswword.mismatch");
			error = new FieldError("actorRegisterForm", "password", password, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

	private boolean comprobarPostalAddress(final String postalAddress, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (postalAddress == null || postalAddress.isEmpty())
			result = true;
		else
			result = false;

		if (!result)
			if (postalAddress.matches("^[0-9]{5}$"))
				result = true;
			else {
				codigos = new String[1];
				codigos[0] = "actorRegister.postalAddress.error";
				error = new FieldError("actorRegisterForm", "postalAddress", postalAddress, false, codigos, null, "");
				binding.addError(error);
			}

		return result;
	}

	//	private boolean comprobarPhone(final String phone, final BindingResult binding) {
	//		FieldError error;
	//		String[] codigos;
	//		boolean result;
	//
	//		if (phone == null || phone.isEmpty())
	//			result = true;
	//		else
	//			result = false;
	//
	//		if (!result)
	//			if (phone.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"))
	//				result = true;
	//			else {
	//				codigos = new String[1];
	//				codigos[0] = "actorRegister.phone.error";
	//				error = new FieldError("actorRegisterForm", "phone", phone, false, codigos, null, "");
	//				binding.addError(error);
	//			}
	//
	//		return result;
	//	}

}
