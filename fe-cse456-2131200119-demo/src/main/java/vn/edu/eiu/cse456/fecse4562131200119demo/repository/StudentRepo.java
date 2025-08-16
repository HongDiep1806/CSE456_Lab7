package vn.edu.eiu.cse456.fecse4562131200119demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Student;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
    public void deleteStudentById(String id);

    List<Student> searchAllByNameContainingIgnoreCase(String name);
}
