package com.studentapp.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.studentapp.model.Student;
import com.studentapp.service.StudentServiceDb;

@Controller
public class StudentWebController {

    
    private final StudentServiceDb service;
    public StudentWebController(StudentServiceDb service) { this.service = service; }

    
    @GetMapping({"/", "/menu"})
    public String menu() {
        return "menu";
    }

       @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", service.listAll());
        return "list";
    }

    @GetMapping("/students/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    
    @PostMapping("/students/save")
    public String save(@ModelAttribute Student student, RedirectAttributes ra) {
        if (student.getId() == 0) {
            service.addStudent(student);
            ra.addFlashAttribute("success", "Student added");
        } else {
            boolean ok = service.updateStudent(student.getId(), student);
            if (ok) ra.addFlashAttribute("success", "Student updated");
            else ra.addFlashAttribute("error", "Student not found");
        }
        return "redirect:/students";
    }

   
    @GetMapping("/students/view/{id}")
    public String view(@PathVariable int id, Model model, RedirectAttributes ra) {
        Optional<Student> s = service.getById(id);
        if (s.isEmpty()) {
            ra.addFlashAttribute("error", "Student not found");
            return "redirect:/students";
        }
        model.addAttribute("student", s.get());
        return "student_detail";
    }

   
    @GetMapping("/students/edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes ra) {
        Optional<Student> s = service.getById(id);
        if (s.isEmpty()) {
            ra.addFlashAttribute("error", "Student not found");
            return "redirect:/students";
        }
        model.addAttribute("student", s.get());
        return "form";
    }

   
    @GetMapping("/students/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        boolean ok = service.deleteStudent(id);
        if (ok) ra.addFlashAttribute("success", "Student deleted");
        else ra.addFlashAttribute("error", "Student not found");
        return "redirect:/students";
    }
@GetMapping("/students/search")
public String searchById(@RequestParam(value = "id", required = false) Integer id, Model model) {
    if (id != null) {
        Optional<Student> s = service.getById(id);
        model.addAttribute("result", s.orElse(null)); // <- must use "result"
    
    }
    return "search";
}


    @GetMapping("/exit")
    public String exit() {
        return "exit";
    }
}
