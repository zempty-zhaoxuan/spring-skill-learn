package com.zempty.spring_skill_learn.reponse;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.zempty.spring_skill_learn.annotation.UserName;

import java.lang.reflect.Field;

public class UserModifyResponse implements ContextValueFilter {

    @Override
    public Object process(BeanContext context, Object object, String name, Object value) {
        System.out.println(context);
        System.out.println(object);
        System.out.println(name);
        System.out.println(value);
        Field field = context != null ? context.getField() : null;
        if (field != null && field.isAnnotationPresent(UserName.class)) {
            value = "zempty_moddiy_name";
        }
        return value;
    }
}
