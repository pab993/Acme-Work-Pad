
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.BibliographyRecord;

@Repository
public interface BibliographyRecordRepository extends JpaRepository<BibliographyRecord, Integer> {

	@Query("select br from BibliographyRecord br where br.subject.id=?1")
	Collection<BibliographyRecord> findBySubject(int idSubject);

}
