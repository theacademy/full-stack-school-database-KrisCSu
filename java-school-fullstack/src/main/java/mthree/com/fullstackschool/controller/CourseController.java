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

import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.service.CourseServiceImpl;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        return courseService.getAllCourses();
        //YOUR CODE ENDS HERE
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        return courseService.getCourseById(id);

        //YOUR CODE ENDS HERE
    }

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        //YOUR CODE STARTS HERE

        return courseService.addNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        //YOUR CODE STARTS HERE

        return courseService.updateCourseData(id, course);

        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        courseService.deleteCourseById(id);

        //YOUR CODE ENDS HERE
    }
}
