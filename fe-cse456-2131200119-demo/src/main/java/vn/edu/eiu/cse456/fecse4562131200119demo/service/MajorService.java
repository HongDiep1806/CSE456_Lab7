package vn.edu.eiu.cse456.fecse4562131200119demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.eiu.cse456.fecse4562131200119demo.model.Major;
import vn.edu.eiu.cse456.fecse4562131200119demo.repository.MajorRepo;

import java.util.List;

@Service
public class MajorService {
    // DI: dùng dependency injection,có 3 cách: field, constructor, setter
    @Autowired
    private MajorRepo majorRepo;
    public void saveMajor(Major major) {
        majorRepo.save(major);
    }
    public List<Major> getAllMajors() {
        return majorRepo.findAll();
    }

}
