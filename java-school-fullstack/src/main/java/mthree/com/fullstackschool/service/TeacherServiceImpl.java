package mthree.com.fullstackschool.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Teacher;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE
    private final TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao){ 
        this.teacherDao = teacherDao;
    }

    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return teacherDao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE
        Teacher teacher = new Teacher();
        try {
            teacher = teacherDao.findTeacherById(id);
        } catch (DataAccessException e) {
            teacher.setTeacherFName("Teacher Not Found.");
            teacher.setTeacherLName("Teacher Not Found.");
        }
        return teacher;
        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
        if (teacher.getTeacherFName().isBlank() || teacher.getTeacherLName().isBlank()){ 
            teacher.setTeacherFName("First Name blank, teacher NOT added");
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
            return teacher;
        }
        return teacherDao.createNewTeacher(teacher);
        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE
        if (teacher.getTeacherId() != id){
            teacher.setTeacherFName("IDs do not match, teacher not updated");
            teacher.setTeacherLName("IDs do not match, teacher not updated");
            return teacher;
        }
        teacherDao.updateTeacher(teacher);
        return teacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE
        teacherDao.deleteTeacher(id);
        //YOUR CODE ENDS HERE
    }
}
