package com.abubusoft.kripton.examplea0.data.model;


import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;


/**
 * Created by xcesco on 10/10/2017.
 */
@BindDaoMany2Many(entity1 = Person.class, entity2 = PhoneNumber.class)
public interface Person2PhoneDao {
}
