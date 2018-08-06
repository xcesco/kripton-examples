package com.abubusoft.kripton.examplea0.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.examplea0.model.Student;

import java.util.HashSet;
import java.util.List;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDao(Student.class)
public interface StudentDao {

    @BindSqlSelect(pageSize = 20)
    PaginatedResult<Student> getAllStudentsPaginated();

    @BindSqlSelect
    void getAllStudentWithBeanListener(OnReadBeanListener<Student> listener);

    @BindSqlSelect
    void getAllStudentWithCursorListener(OnReadCursorListener listener);

    @BindSqlSelect
    List<Student> getAllStudents();

    @BindSqlSelect
    HashSet<Student> getAllStudentsAsSet();

    @BindSqlSelect(where="id=${uid}")
    Student getStudent(int uid);

    @BindSqlUpdate
    void updateStudent(Student student);

    @BindSqlDelete
    void deleteStudent(Student student);

    @BindSqlInsert(jql="INSERT INTO student (name) VALUES (${student.name})")
    void insertStudent(Student student);
}
