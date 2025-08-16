package vn.edu.eiu.cse456.fecse4562131200119demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Major;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Student;
import vn.edu.eiu.cse456.fecse4562131200119demo.service.MajorService;
import vn.edu.eiu.cse456.fecse4562131200119demo.service.StudentService;

import java.util.ArrayList;
import java.util.List;

/**
 * UI --- CONTROLLER --- SERVICE --- REPOSITORY --- JDBC --- DB
 * mỗi url cần có 1 hàm xử lí
 * đối với form thì dùng postMapping
 */
@Controller
public class StudentController {
    // inject service
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;

    /**
     *link này có 2 cách request
     * 1. gõ trực tiếp
     * - Khi gõ trực tiếp link/students nếu haàm xử l @RequestParam thì sẽ bị lỗi NULL, nên cần xử lí bằng 1 trong 2 cách:
     * + gán default value bằng ""
     * + dùng require = false như code dưới
     * 2. thông qua nút bấm search
     *
     */
    @GetMapping("/students")
    public String students(@RequestParam(value = "keyword", defaultValue = "") String keyword,Model model) {
        List<Student> students = new ArrayList<>();
        if(keyword.isBlank()){
            students = studentService.getStudents();
        }else{
            students = studentService.searchStudentsByName(keyword);
        }
        // gửi qua view cho thymeleaf mix với html tag
        model.addAttribute("studentList", students);
        return "student-list";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable("id") String id, Model model) {
        Student s = studentService.getStudentById(id);
        List<Major> majors = majorService.getAllMajors();
        model.addAttribute("student", s);
        model.addAttribute("majorList", majors);
        model.addAttribute("formMode", "edit");
        return "student-form";
    }

    @GetMapping("/student/new")
    public String newStudent(Model model) {
        Student s = new Student();
        List<Major> majors = majorService.getAllMajors();
        model.addAttribute("student", s);
        model.addAttribute("majorList", majors);
        model.addAttribute("formMode", "create");
        return "student-form";
    }

    // hàm xử lí cho url /student/form khi ng dùng bấm save trên form
    @PostMapping("/student/form")
    public String saveStudent(@Valid @ModelAttribute("student") Student s,  BindingResult result, @RequestParam("formMode") String formMode,Model model) {
        // lấy thông tin từ trên form gửi xuống , nếu có lỗi quay lại trang form kèm theo mesage
        if(result.hasErrors()) {
            model.addAttribute("formMode", formMode);
            model.addAttribute("majorList", majorService.getAllMajors());
            return "student-form";
        }
        if(formMode.equals("create")){
            if(studentService.checkExistsById(s.getId())) {
                model.addAttribute("formMode", formMode);
                model.addAttribute("majorList", majorService.getAllMajors());
                model.addAttribute("duplicate", "ID is existed" );
                return "student-form";
            }
        }
        // lấy thông tin gửi từ form xuống
        // validate thông tin
        // lưu xuống db
        // trả về url students để hiển thị ds sv đã cập nhật bằng redirect
        studentService.saveStudent(s);
        return "redirect:/students";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id, Model model) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }



}
