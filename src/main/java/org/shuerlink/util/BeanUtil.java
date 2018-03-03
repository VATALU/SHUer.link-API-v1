package org.shuerlink.util;

import java.lang.reflect.Field;

public class BeanUtil {
    public static boolean isEveryFieldNotNull(Object obj) throws Exception{
        Class stuCla = (Class) obj.getClass();
        Field[] fs = stuCla.getDeclaredFields();
        boolean flag = true;
        for (Field f : fs) {
            f.setAccessible(true);
            Object val = f.get(obj);
            if(val==null) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
