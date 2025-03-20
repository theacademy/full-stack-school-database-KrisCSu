package mthree.com.fullstackschool.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Student;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    private final StudentDao studentDao;
    private CourseDao courseDao;

    public StudentServiceImpl(StudentDao studentDao){ 
        this.studentDao = studentDao;
    }
    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
        return studentDao.getAllStudents();
        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE
        Student student = new Student();
        try {
            student = studentDao.findStudentById(id);
        } catch (DataAccessException e) {
            student.setStudentFirstName("Student Not Found");
            student.setStudentLastName("Student Not Found");
        }
        return student;
        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        if (student.getStudentFirstName().isBlank() || student.getStudentLastName().isBlank()){ 
            student.setStudentFirstName("First Name blank, student NOT added");
            student.setStudentLastName("Last Name blank, student NOT added");
            return student;
        }
        return studentDao.createNewStudent(student);
        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE
        if (student.getStudentId() != id){
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
            return student;
        } else { 
            studentDao.updateStudent(student);
        }
        return student;
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE
        studentDao.deleteStudent(id);
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        if(studentDao.findStudentById(studentId).getStudentFirstName().equals("Student Not Found")) { 
            System.out.println("Student not found");
        } else if(courseDao.findCourseById(courseId).getCourseName().equals("Course Not Found")) {
            System.out.println("Course not found");
        } else{ 
            studentDao.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Student: " + studentId + " deleted from course: " + courseId);
        }
        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try {
            if(studentDao.findStudentById(studentId).getStudentFirstName().equals("Student Not Found")) { 
                System.out.println("Student Not Found");
            } else if(courseDao.findCourseById(courseId).getCourseName().equals("Course Not Found")) {
                System.out.println("Course not found");
            } else {
                studentDao.addStudentToCourse(studentId, courseId);
                System.out.println("Student: " + studentId + " added to course: " + courseId);
            }
        } catch (DataAccessException e) {
            System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
        }
        //YOUR CODE ENDS HERE
    }
}
