
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	@Query("select s from Subject s where s.title like %?1%")
	Collection<Subject> searchSubjectByWords(String keyWord);

	@Query("select t.subjects from Teacher t where t.id = ?1")
	Collection<Subject> findAllByTeacher(int teacherId);

	@Query("select s from Subject s where s.ticker = ?1")
	Subject findByTicker(String ticker);

	@Query("select s from Subject s where s.teacher = null")
	Collection<Subject> findAllByNotTeacherId();

	@Query("select s from Subject s where s.teacher != null")
	Collection<Subject> findAllByTeacherId();

	@Query("select s from Subject s where s.title like %?1% and s.seats > 0")
	Collection<Subject> getSubjectByKeyWordWithSeats(String keyWord);

	@Query("select s from Subject s where s.title like %?1% and s.seats = 0")
	Collection<Subject> getSubjectByKeyWordWithoutSeats(String keyWord);

	@Query("select min(s.seats), max(s.seats), avg(s.seats) from Subject s")
	Object[] minMaxAvgSeatsBySubject();

	@Query("select min(s.assignments.size), max(s.assignments.size), avg(s.assignments.size) from Subject s")
	Object[] minMaxAvgAssignmentsBySubject();

	@Query("select min(s.bibliographyRecords.size), max(s.bibliographyRecords.size), avg(s.bibliographyRecords.size) from Subject s")
	Object[] minMaxAvgBibliographyRecordsBySubject();

	@Query("select min(s.students.size), max(s.students.size), avg(s.students.size) from Subject s")
	Object[] minMaxAvgStudentsBySubject();

	@Query("select count(s)*1.0 / (select count(s1)*1.0 from Subject s1) from Subject s where s.bibliographyRecords.size = 1 or s.bibliographyRecords.size = 2")
	Object[] ratioSubjectsWithBibliographyRecords();

}
