package vn.edu.eiu.cse456.fecse4562131200119demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Major;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Student;
import vn.edu.eiu.cse456.fecse4562131200119demo.service.MajorService;

/**
 * khi thêm data chú ý là phải thêm bảng 1 trước
 * thêm bảng nhiều sau
 *
 */
@Component
public class DataInitializer implements CommandLineRunner {
    // tiêm service để thao tác dữ liệu
    @Autowired // tiêm bằng field
    MajorService majorService;
    @Override
    public void run(String... args) throws Exception {
        // inject service để thao tác dữ liệu
        Major m1 = new Major("CSE - Kỹ thuật phần mềm", "Ngành Kỹ thuật phần mềm");
        Major m2 = new Major("CSW - Mạng và Truyền thông dữ liệu", "Ngành mạng và truyền thông");
        Student s1 = new Student("st1m1", "Trịnh Trần Phương Tuấn", 2000, 2.0);
        Student s2 = new Student("st2m1", "Võ Đoàn Duy Best", 2001, 2.0);
        Student s3 = new Student("st2m2", "Nghi Lé", 2003, 2.0);
        Student s4 = new Student("st2m3", "Út Mon", 2002, 2.0);
        // thêm sv vào major
        m1.addStudent(s1);
        m1.addStudent(s2);
        m2.addStudent(s3);
        m2.addStudent(s4);

        // gọi service lưu db
        majorService.saveMajor(m1);
        majorService.saveMajor(m2);





    }
}
