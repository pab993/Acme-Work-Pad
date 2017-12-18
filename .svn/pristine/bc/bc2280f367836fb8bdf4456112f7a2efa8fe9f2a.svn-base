
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Query("select t from Teacher t where t.userAccount.id = ?1")
	Teacher findByUserAccountId(int userAccountId);

	@Query("select t from Teacher t join t.subjects s where s.id = ?1")
	Teacher findOneBySubject(int subjectId);

	@Query("select t from Teacher t where t.subjects.size >= all(select t1.subjects.size from Teacher t1)")
	Collection<Teacher> findTeacherMoreSubject();

	@Query("select t from Teacher t where t.subjects.size <= all(select t1.subjects.size from Teacher t1)")
	Collection<Teacher> findTeacherLessSubject();

	@Query("select t from Teacher t where t.subjects.size <= (select avg(t1.subjects.size)*1.1 from Teacher t1) and t.subjects.size >= (select avg(t2.subjects.size)*0.9 from Teacher t2)")
	Collection<Teacher> findTeachersPlusMinus10AvgSubjects();

	@Query("select min(t.subjects.size), max(t.subjects.size), avg(t.subjects.size) from Teacher t")
	Object[] minMaxAvgSubjectsByTeacher();

	@Query("select min(t.seminaries.size), max(t.seminaries.size), avg(t.seminaries.size) from Teacher t")
	Object[] minMaxAvgSeminariesByTeacher();

	@Query("select t from Teacher t where t.seminaries.size <= (select avg(t1.seminaries.size)*1.1 from Teacher t1) and t.seminaries.size >= (select avg(t2.seminaries.size)*0.9 from Teacher t2)")
	Collection<Teacher> findTeachersPlusMinus10AvgSeminaries();

	@Query("select t from Teacher t join t.subjects s where s.teacher.id = null")
	Collection<Teacher> findAllWithoutSubject();

}
