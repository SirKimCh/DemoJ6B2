package org.j6d2.demoj6b2.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.j6d2.demoj6b2.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @GetMapping("/student")
    public String index(Model model, @RequestParam("index") Optional<Integer> index) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String studentPatch = "src/main/resources/static/student.json";
            List<Student> students = mapper.readValue(new File(studentPatch), new TypeReference<List<Student>>() {
            });
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

    @GetMapping("/student/create")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "scope/createStudent";
    }

    @PostMapping("/student/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "scope/createStudent";
        }
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/static/student.json");
        try {
            List<Student> students = new ArrayList<>();
            if (file.exists()) {
                students = mapper.readValue(file, new TypeReference<List<Student>>() {});
            }
            students.add(student);
            mapper.writeValue(file, students);
            redirectAttributes.addFlashAttribute("message", "Student added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to add student.");
        }
        return "redirect:/student/create";
    }
}