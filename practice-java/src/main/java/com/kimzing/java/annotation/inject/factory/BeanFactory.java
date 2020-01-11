package com.kimzing.java.annotation.inject.factory;

import com.kimzing.java.annotation.inject.annotation.Autowire;
import com.kimzing.java.annotation.inject.annotation.Service;
import com.kimzing.java.annotation.inject.consumer.Person;
import com.kimzing.java.annotation.inject.provider.Car;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * .
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/9/28 19:49
 */
public class BeanFactory {

    Map<String, Object> map = new ConcurrentHashMap();

    public void init() throws Exception {
        Class<Car> carClazz = Car.class;
        List<Annotation> collect = Stream.of(carClazz.getAnnotations()).filter(t -> t.annotationType() == Service.class).collect(Collectors.toList());
        if (collect != null && collect.size() > 0) {
            Service service = (Service) collect.get(0);
            Car car = carClazz.newInstance();
            String name = service.name();
            if ("".equals(name)) {
                name = carClazz.getSimpleName();
            }
            map.put(name, car);
        }

        Class<Person> personClazz = Person.class;
        List<Annotation> personList = Stream.of(personClazz.getAnnotations()).filter(t -> t.annotationType() == Service.class).collect(Collectors.toList());
        if (personList != null && personList.size() > 0) {
            Service service = (Service) personList.get(0);
            Person person = personClazz.newInstance();
            String name = service.name();
            if ("".equals(name)) {
                name = personClazz.getSimpleName();
            }
            map.put(name, person);

            Field[] fields = personClazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotations().length <= 0) {
                    continue;
                }
                if (fields[i].getAnnotations()[0].annotationType() == Autowire.class) {
                    Autowire wire = (Autowire) fields[i].getAnnotations()[0];
                    Object o = map.get(wire.name());
                    if (o == null) {
                        throw new RuntimeException(" no instance found in my context");
                    }
                    fields[i].setAccessible(true);
                    fields[i].set(person, (Car)o);
                }
            }
            person.drive();
        }
    }

}
