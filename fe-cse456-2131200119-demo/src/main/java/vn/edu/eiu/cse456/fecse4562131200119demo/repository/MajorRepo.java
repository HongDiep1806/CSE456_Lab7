package vn.edu.eiu.cse456.fecse4562131200119demo.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Major;

/**
 * jpaRepo là interface trong spring data, chứa all hàm abstract xử l CRUD
 * lúc kế thừa sẽ truyền vào tên class entity và kiểu dữ liệu của khóa chính
 * trong jpaRepository đã xây dựng sẵn các phương thức abstract tự sinh hầu như là phục vụ được các truy xuất cơ bản xuống db
 *
 */
@Repository// có quyền autowire
public interface MajorRepo extends JpaRepository<Major, Long> {
    // ko cần code các hàm đã có sẵn
}
