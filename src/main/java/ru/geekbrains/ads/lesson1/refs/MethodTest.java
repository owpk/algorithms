package ru.geekbrains.ads.lesson1.refs;

public class MethodTest {

    public static void main(String[] args) {
//        testPrimitive();
//        testObject();
        testRecord();
    }

    private static void testRecord() {
        PersonRec pr1 = new PersonRec("Oleg", 15);
        PersonRec pr2 = new PersonRec("Oleg", 15);
        System.out.println(pr1.age());
        System.out.println("pr1 = pr2: " + (pr1 == pr2));
        System.out.println("pr1.equals(pr2): " + (pr1.equals(pr2)));
        System.out.println(pr1);
    }

    private static void testObject() {
        Person p  = new Person("Oleg", 15);//p->001[055]
        Person p2 = new Person("Oleg", 15);//p->003[066]
        System.out.println("Person = " + p);
        changeObject(p);//changeObject(&055)
        System.out.println("Person = " + p);//p->001[055]

        System.out.println("p = p2: " + (p == p2)); // 055 == 066
        System.out.println("p.equals(p2): " + (p.equals(p2)));
    }

    private static void changeObject(Person p) {//p->002[055]
        System.out.println("Person = " + p);
        p.setName("Egor");//p->002[055]
//        p = new Person("Alex", 45);//p->002[099]
        System.out.println("Person = " + p);
    }

    private static void testPrimitive() {
        int a = 5;//a->001[5]
        System.out.println("a = " + a);
        changeValue(a);//changeValue(5)
        System.out.println("a = " + a);
    }

    private static void changeValue(int a) {//a->002[5]
        System.out.println("a = " + a);
        a = 7;//a->002[7]
        System.out.println("a = " + a);
    }
}

