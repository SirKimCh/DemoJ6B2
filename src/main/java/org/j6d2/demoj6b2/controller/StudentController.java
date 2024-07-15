package org.j6d2.demoj6b2.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.j6d2.demoj6b2.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @GetMapping("/student")
    public String index(Model model, @RequestParam("index") Optional<Integer> index) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String studentPatch = "src/main/resources/static/student.json";
            List<Student> students = mapper.readValue(new File(studentPatch), new TypeReference<List<Student>>() {});
            int studentIndex = index.orElse(0);
            if (studentIndex >= 0 && studentIndex < students.size()) {
                model.addAttribute("student", students.get(studentIndex));
                model.addAttribute("currentIndex", studentIndex);
                model.addAttribute("hasPrev", studentIndex > 0);
                model.addAttribute("hasNext", studentIndex < students.size() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "scope/student";
    }
}