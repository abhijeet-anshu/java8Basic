package helloLambda;

import java.util.function.Predicate;

public class basicFilter
{
    /*
    Problem:
    abc enjed ejedjk swnkwk xoieded edned orfrfn

    given list of string, ignoring strings that start with x, print others

    1. abc
    2. enjed
    3. ejedjk
    4. swnkwk
    5. edned
    6. orfrfn

     */

    //@boilerPlateCode
    void oldFilter(String s)
    {
        int count = 0;
        for(String words: s.split(" ")) {
            if(words.startsWith("x")) continue;
            ++count;
            System.out.println(count + ". " + words);
        }
    }

    void filter(String s, Predicate p) {
        int count = 0;
        for(String words: s.split(" "))
        {
            if(p.test(words)) {
                ++count;
                System.out.println(count + ". " + words);
            }
        }
    }

    void newFilter(String s) {
        Predicate<String> notStartsWithX = (String j) -> !j.startsWith("x");
        filter(s, notStartsWithX);
    }

    public static void  main(String args[]) {
        new basicFilter().oldFilter("abc enjed ejedjk swnkwk xoieded edned orfrfn");
        new basicFilter().newFilter("abc enjed ejedjk swnkwk xoieded edned orfrfn");
    }



}
