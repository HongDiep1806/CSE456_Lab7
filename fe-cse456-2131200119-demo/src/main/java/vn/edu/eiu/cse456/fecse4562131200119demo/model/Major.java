package vn.edu.eiu.cse456.fecse4562131200119demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_Major")
@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;
    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
    // kết nối với bảng sinh viên
    // mappedBy = tên thuộc tính obj của Major bên bảng Student
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "major")
    public List<Student> studentList = new ArrayList<>();
    // 2 phương thức, thêm sv và xóa sv
    // thêm sv vào major
    public void addStudent(Student student) {
        studentList.add(student);
        student.setMajor(this);
    }
    // xóa sv ra khỏi major
    public void deleteStudent(Student student) {
        studentList.remove(student);
        student.setMajor(null);
    }
    // nếu id tự tăng phải thêm 1 ctor bỏ id
    public Major(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Major() {
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
