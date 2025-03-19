package mthree.com.fullstackschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mthree.com.fullstackschool.model.Teacher;
import mthree.com.fullstackschool.service.TeacherServiceImpl;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE
        return teacherServiceImpl.getAllTeachers();
        //YOUR CODE ENDS HERE
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        return teacherServiceImpl.getTeacherById(id);
        //YOUR CODE ENDS HERE
    }

    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        //YOUR CODE STARTS HERE
        return teacherServiceImpl.addNewTeacher(teacher);
        //YOUR CODE ENDS HERE
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        //YOUR CODE STARTS HERE
        return teacherServiceImpl.updateTeacherData(id, teacher);
        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        teacherServiceImpl.deleteTeacherById(id);
        //YOUR CODE ENDS HERE
    }
}
