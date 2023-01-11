public class Main {
    public static void main(String[] args) {
        Person pavel = new Person("Pavel", "Bal");
        Person ivan = new Person("Ivan", "Ivanov");
        Person alex = new Person("Alex", "Drozd");
        Person JoJo = new Person("Jonathan", "Joestar");

        System.out.println("1");
        MyArrayList<Person> list1 = new MyArrayList<>();
        System.out.println("List1 " + list1);
        MyArrayList<Person> list2 = new MyArrayList<>();
        System.out.println("List2 " + list2);


        System.out.println("2");
        list1.add(pavel);
        System.out.println("List1 " + list1);

        list1.add(ivan);
        System.out.println("List1 " + list1);

        list1.add(alex);
        System.out.println("List1 " + list1);

        list2.addAll(list1);
        System.out.println("List2 " + list2);

        MyArrayList<Person> list3 = new MyArrayList<>(list2);
        System.out.println("List3 " + list3);

        list3.add(1, JoJo);
        System.out.println("List3 " + list3);
        list3.add(0, JoJo);
        System.out.println("List3 " + list3);


        System.out.println("3");
        list1.remove(1);
        System.out.println("List1 " + list1);

        list1.remove(alex);
        System.out.println("List1 " + list1);

        list2.clear();
        System.out.println("List2 " + list2);

        System.out.println("4");
        System.out.println(list1.get(0));


        System.out.println("4");
        list3.bubbleSort(new SortFirstName());
        System.out.println("List3 " + list3);

        MyArrayList<Integer> intList = new MyArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.bubbleSort(Integer::compareTo);


    }
}