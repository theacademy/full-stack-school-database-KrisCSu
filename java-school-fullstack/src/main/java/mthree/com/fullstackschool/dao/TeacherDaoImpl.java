package mthree.com.fullstackschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import mthree.com.fullstackschool.dao.mappers.TeacherMapper;
import mthree.com.fullstackschool.model.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
        final String sql = "INSERT INTO teacher(tFName, tLName, dept) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, teacher.getTeacherFName());
            stmt.setString(2, teacher.getTeacherLName());
            stmt.setString(3, teacher.getDept());

            return stmt;
        }, keyHolder);
        teacher.setTeacherId(keyHolder.getKey().intValue());
        return teacher;
        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE
        final String sql = "SELECT tid, tFName, tLName, dept FROM teacher;";

        return jdbcTemplate.query(sql, new TeacherMapper());
        //YOUR CODE ENDS HERE
    }

    @Override
    public Teacher findTeacherById(int id) {
        //YOUR CODE STARTS HERE
        final String sql = "SELECT tid, tFName, tLName, dept FROM teacher WHERE tid = ?;";
        return jdbcTemplate.queryForObject(sql, new TeacherMapper(), id);
        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateTeacher(Teacher t) {
        //YOUR CODE STARTS HERE
        final String sql = "UPDATE teacher SET "
                        + "tFName = ?, "
                        + "tLName = ?, "
                        + "dept = ? "
                        + "WHERE tid = ?;";
        jdbcTemplate.update(sql, t.getTeacherFName(),
                            t.getTeacherLName(),
                            t.getDept(),
                            t.getTeacherId());
        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteTeacher(int id) {
        //YOUR CODE STARTS HERE
        final String sql = "DELETE FROM teacher WHERE tid = ?;";
        jdbcTemplate.update(sql, id);

        //YOUR CODE ENDS HERE
    }
}
