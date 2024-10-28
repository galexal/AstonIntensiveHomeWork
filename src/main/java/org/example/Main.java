package org.example;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(13);
        list.add(45);
        list.add(8);
        list.add(60);
        list.add(999);

        System.out.println(list);

        list.add(42);
        System.out.println(list);
        list.add(0, 100);
        System.out.println(list);
        list.remove(2);
        list.remove(Integer.valueOf(60));
        System.out.println(list);
        System.out.println(list.get(3));
        list.addAll(Set.of(5, 6, 7));
        System.out.println(list);
        list.sort(Integer::compareTo);
        System.out.println(list);
        list.clear();
        System.out.println("Пустой список? " + list.isEmpty());
    }
}