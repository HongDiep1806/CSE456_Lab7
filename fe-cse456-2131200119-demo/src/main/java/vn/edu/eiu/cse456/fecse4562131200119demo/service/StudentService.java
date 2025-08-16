package vn.edu.eiu.cse456.fecse4562131200119demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Major;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Student;
import vn.edu.eiu.cse456.fecse4562131200119demo.repository.MajorRepo;
import vn.edu.eiu.cse456.fecse4562131200119demo.repository.StudentRepo;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private MajorRepo majorRepo;
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }
    public Student getStudentById(String id) {
        return studentRepo.findById(id).orElseThrow();
    }
    public void deleteStudentById(String id) {
        Student s = studentRepo.findById(id).orElseThrow();
        if (s.getMajor() != null) {
            Major m = s.getMajor();
            m.getStudentList().remove(s);
            s.setMajor(null);
        }
        studentRepo.delete(s);

    }
    // hàm phục vụ tìm kiếm sv bằng tên
    public List<Student> searchStudentsByName(String name) {
        List<Student> students = studentRepo.searchAllByNameContainingIgnoreCase(name);
        return students;
    }

    // hàm phục vụ kiểu tra trung pk
    public boolean checkExistsById(String id) {
        return studentRepo.existsById(id);
    }

}
