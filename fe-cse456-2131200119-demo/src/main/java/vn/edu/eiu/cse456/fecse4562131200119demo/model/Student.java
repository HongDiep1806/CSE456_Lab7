package vn.edu.eiu.cse456.fecse4562131200119demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="tbl_Student")
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class Student {
    @Id
    @Column(name = "Id", columnDefinition = "CHAR(5)")
    @Size(min = 5, max = 5, message = "Length out of bound, ID must be exactly 5 characters")
    @NotBlank(message = "ID must be not empty")
    public String id;
    @Column(name = "Name", columnDefinition = "NVARCHAR(100)", nullable = false)
    @NotNull(message = "Name must be not empty")
    @Size(min = 5, max = 100, message = "Length of name must be between 5 and 100")
    @Pattern(
            regexp = "^(\\p{Lu}\\p{Ll}+)(\\s\\p{Lu}\\p{Ll}+)*$",
            message = "Each word must start with an uppercase letter and the rest lowercase"
    )
    private String name;
    @Column(name = "Year of Birth", nullable = false)
    @Min(value = 2000, message = "Yob must be from 2000")
    @Max(value = 2007, message = "Yob must be to 2007")
    @NotNull(message = "Yob can not be null")
    private int yob;
    @Column(name = "Gpa", columnDefinition = "DECIMAL(3,2)")
    private double gpa;
    @ManyToOne
    @JoinColumn(name = "majorID") // tên cột khóa ngoại
    private Major major; // gắn mappedBy bên class Major
    public Student(String id, String name, int yob, double gpa) {
        this.id = id;
        this.name = name;
        this.yob = yob;
        this.gpa = gpa;
    }

    public Student(String id, String name, int yob, double gpa, Major major) {
        this.id = id;
        this.name = name;
        this.yob = yob;
        this.gpa = gpa;
        this.major = major;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
