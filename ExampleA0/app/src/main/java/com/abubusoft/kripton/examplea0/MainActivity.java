package com.abubusoft.kripton.examplea0;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.examplea0.model.Contact;
import com.abubusoft.kripton.examplea0.model.Student;
import com.abubusoft.kripton.examplea0.persistence.BindStudentsDaoFactory;
import com.abubusoft.kripton.examplea0.persistence.BindStudentsDataSource;
import com.abubusoft.kripton.examplea0.persistence.StudentDaoImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contact bean=new Contact();
        bean.setName("Tonj");
        bean.setSurname("Manero");
        bean.setBirthDay(new Date());

        BinderContext binder = KriptonBinder.bind(BinderType.YAML);

        // write on a string
        String output=binder.serialize(bean);
        Logger.info(output);

        BindStudentsDataSource dataSource=BindStudentsDataSource.instance();

        dataSource.openWritableDatabase();
        StudentDaoImpl dao = dataSource.getStudentDao();
        dataSource.close();

        dataSource.execute(new BindStudentsDataSource.Transaction() {

            @Override
            public void onError(Throwable e) {
                // manage error
            }

            @Override
            public boolean onExecute(BindStudentsDaoFactory daoFactory) throws Throwable {
                daoFactory.getStudentDao().insertStudent(new Student());

                daoFactory.getStudentDao().getAllStudentWithBeanListener(new OnReadBeanListener<Student>() {
                    @Override
                    public void onRead(Student bean, int row, int rowCount) {
                        // for each
                    }
                });

                daoFactory.getStudentDao().getAllStudentWithCursorListener(new OnReadCursorListener() {
                    @Override
                    public void onRead(Cursor cursor) {

                    }
                });

                PaginatedResult<Student> res = daoFactory.getStudentDao().getAllStudentsPaginated();

                while(res.hasNext()) {
                    List<Student> pageElements = res.list();
                }
                return true;
            }
        });
    }
}
