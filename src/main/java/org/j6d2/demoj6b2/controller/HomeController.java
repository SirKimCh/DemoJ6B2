package org.j6d2.demoj6b2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.j6d2.demoj6b2.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/home/index")
    public String index(Model model) {
        model.addAttribute("msg", "Welcome Home!");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String studentPatch = "src/main/resources/static/student.json";
            List<Student> students = mapper.readValue(new File(studentPatch), new TypeReference<List<Student>>(){});
            if (!students.isEmpty()) {
                model.addAttribute("student", students.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home/index";
    }
}