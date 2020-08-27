package helloLambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class functionalInterface_Function {
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    //method that uses functional interface
    public static <T, R> Map<T, R> createMap(List<T> inpList, Function<T, R> f) {
        Map<T, R> myMap = new HashMap<T, R>();

        for(T listItem : inpList) {
            myMap.put(listItem, f.apply(listItem));
        }
        return  myMap;
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void main(String args[]) {
        Integer a = 5;
        List<String> countryNames =
                Arrays.asList("India", "Maldives", "Bangladesh", "Nepal",
                        "Sri Lanka", "Bhutan", "Afganistan");
        Map<String, Integer> map = createMap(countryNames, s->s.length()
                );
        print(map.toString());

    }

}
