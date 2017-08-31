package com.abubusoft.kripton.examplea0;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.examplea0.model.City;
import com.abubusoft.kripton.examplea0.model.Contact;
import com.abubusoft.kripton.examplea0.model.Country;
import com.abubusoft.kripton.examplea0.model.Language;
import com.abubusoft.kripton.examplea0.model.Student;
import com.abubusoft.kripton.examplea0.persistence.BindStudentsDataSource;
import com.abubusoft.kripton.examplea0.persistence.StudentDaoImpl;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    String convert(BinderType type, Object bean) {
        BinderContext binder = KriptonBinder.bind(type);

        if (!type.onlyBinary) {
            // write on a string
            String output = binder.serialize(bean);

            return output;
        } else {
            KriptonByteArrayOutputStream output = new KriptonByteArrayOutputStream();
            binder.serialize(bean, output);

            return toString(output.getByteBuffer());
        }
    }

    <E> String convert(BinderType type, List<E> beanList, Class<E> clazz) throws FileNotFoundException {
        BinderContext binder = KriptonBinder.bind(type);

        if (!type.onlyBinary) {
            // write on a string
            String output = binder.serializeCollection(beanList, clazz);

            return output;
        } else {
            KriptonByteArrayOutputStream output = new KriptonByteArrayOutputStream();
            binder.serializeCollection(beanList, clazz, new FileOutputStream("file.json"));


            List<Contact> list=binder.parseCollection(new FileInputStream("file.json"), new ArrayList<Contact>(), Contact.class);
            return toString(output.getByteBuffer());
        }
    }

    String toString(byte[] input) {
        StringBuilder buffer = new StringBuilder();
        for (int j = 0; j < input.length; j++) {
            buffer.append(String.format("%02X", input[j]));
        }
        return buffer.toString();
    }

    @Test
    public void first_example() throws Exception {
        Contact bean = new Contact();
        bean.setName("Tonj");
        bean.setSurname("Manero");
        bean.setBirthDay(new Date());

        System.out.println(convert(BinderType.JSON, bean));
        System.out.println(convert(BinderType.XML, bean));
        System.out.println(convert(BinderType.SMILE, bean));
        System.out.println(convert(BinderType.YAML, bean));
        System.out.println(convert(BinderType.CBOR, bean));
        System.out.println(convert(BinderType.PROPERTIES, bean));

    }

    @Test
    public void second_example() {
        Country country = new Country();
        country.cities = new HashMap<>();
        country.spokenLanguages = new LinkedList<>();

        country.spokenLanguages.add(Language.ITALIAN);
        country.cities.put("C01", new City("Rome"));

        System.out.println(convert(BinderType.JSON, country));
        System.out.println(convert(BinderType.XML, country));
        System.out.println(convert(BinderType.SMILE, country));
        System.out.println(convert(BinderType.YAML, country));
        System.out.println(convert(BinderType.CBOR, country));
        System.out.println(convert(BinderType.PROPERTIES, country));
    }

    @Test
    public void persist_list() {
        ArrayList<Country> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Country country = new Country();
            country.cities = new HashMap<>();
            country.spokenLanguages = new LinkedList<>();

            country.spokenLanguages.add(Language.ITALIAN);
            country.cities.put("C01", new City("Rome" + 1));

            list.add(country);
        }

//        System.out.println(convert(BinderType.JSON, list, Country.class));
//        System.out.println(convert(BinderType.SMILE, list, Country.class));
//        System.out.println(convert(BinderType.YAML, list, Country.class));
//        System.out.println(convert(BinderType.CBOR, list, Country.class));
//        System.out.println(convert(BinderType.PROPERTIES, list, Country.class));

    }

    @Test
    public void testSQLite() {

    }

}