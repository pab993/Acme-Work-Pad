
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BibliographyRecordRepository;
import domain.Administrator;
import domain.BibliographyRecord;
import domain.Subject;
import domain.Teacher;

@Service
@Transactional
public class BibliographyRecordService {

	@Autowired
	private BibliographyRecordRepository	bibliographyRecordRepository;

	@Autowired
	private TeacherService					teacherService;

	@Autowired
	private AdministratorService			administratorService;


	public BibliographyRecordService() {
		super();
	}

	// CRUD methods --------------------------------------------------------------------------------
	public BibliographyRecord create(final Subject subject) {

		Assert.notNull(subject);

		final Teacher principal = this.teacherService.findByPrincipal();
		Assert.notNull(principal);

		BibliographyRecord result;

		result = new BibliographyRecord();
		result.setSubject(subject);

		return result;
	}

	public BibliographyRecord findOne(final int bibliographyRecordId) {
		return this.bibliographyRecordRepository.findOne(bibliographyRecordId);
	}

	public Collection<BibliographyRecord> findAll() {
		return this.bibliographyRecordRepository.findAll();
	}

	public BibliographyRecord save(final BibliographyRecord bibliographyRecord) {

		Assert.notNull(bibliographyRecord);
		Assert.isTrue(this.teacherService.isAuthenticated());
		Assert.isTrue(this.teacherService.findByPrincipal().getSubjects().contains(bibliographyRecord.getSubject()));

		BibliographyRecord res;

		res = this.bibliographyRecordRepository.save(bibliographyRecord);

		return res;
	}
	public void delete(final BibliographyRecord bibliographyRecord) {

		Assert.notNull(bibliographyRecord);
		Assert.isTrue(this.bibliographyRecordRepository.exists(bibliographyRecord.getId()));

		this.bibliographyRecordRepository.delete(bibliographyRecord);

		Assert.isTrue(!this.bibliographyRecordRepository.exists(bibliographyRecord.getId()));
	}

	public void delete2(final BibliographyRecord bibliographyRecord) {

		Assert.notNull(bibliographyRecord);

		final Administrator principal = this.administratorService.findByPrincipal();
		Assert.notNull(principal);

		this.bibliographyRecordRepository.delete(bibliographyRecord);

	}

	// Other business methods --------------------------------------------------------------------------------

	public Collection<BibliographyRecord> findBySubject(final int idSubject) {
		return this.bibliographyRecordRepository.findBySubject(idSubject);
	}

}
