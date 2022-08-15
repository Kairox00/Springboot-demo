package com.khaled.assignment.repositories;

import com.khaled.assignment.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE STUDENT s SET s.NAME = :name, s.AGE = :age, s.DEPARTMENT = :department WHERE s.STUDENT_ID = :id")
//    void update(@Param("name") String name, @Param("age") int age, @Param("department") String department, @Param("id") Long id);
}
