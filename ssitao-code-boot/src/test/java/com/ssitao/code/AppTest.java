package com.ssitao.code;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        Person person = new Person("小明");
        changeReference(person); // 传递的是 person 持有的地址值的拷贝
        System.out.println(person.getName()); // 输出“小红”，对象内部状态被修改了

        tryToReassign(person); // 同样是传递地址值的拷贝
        System.out.println(person.getName()); // 依然输出“小红”，原引用 person 指向的对象没变
    }

    static void changeReference(Person p) {
        p.setName("小红"); // ✅ 操作的是拷贝的地址所指向的同一个对象，所以对象状态变了
    }

    static void tryToReassign(Person p) {
        p = new Person("小刚"); // ❌ 这只是让拷贝的引用 p 指向了新对象，不影响 main 中的 person
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private String name;

}
