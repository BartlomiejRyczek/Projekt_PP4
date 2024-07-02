package pl.bryczek.creditcard;

import java.util.Collection;
import java.util.Collections;

public class App {
    public static void main(String[] args){
        var name = "Bartek";
        System.out.println("It works");
        System.out.println(String.format("Hello %s", name));

        int a =2;
        int b=5;
        int result = a+b;
        System.out.println(result);

        var myList = Collections.emptyList();
    }
}
