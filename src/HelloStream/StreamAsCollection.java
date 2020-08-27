package HelloStream;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAsCollection {
    public static void main(String[] args) {
        List<String> myNames =  Stream.of("abhijeet", "baranwal",
                "charanwal", "karanwal").collect(Collectors.toList());

        Optional<String> myAny = myNames.stream().findAny();

        if(myAny.isPresent()) {
            System.out.println(myAny.get());
        }

        Optional<String> myFirst = myNames.stream().findFirst();

        if(myFirst.isPresent()) {
            System.out.println(myFirst.get());
        }



        myNames.stream().filter(s->s.endsWith("wal")).forEach(System.out::println);

        Stream<String> myFilterStream = myNames.stream().filter(s->!s.startsWith("k"));
        List validWals = myFilterStream.filter(s->s.endsWith("wal")).collect(Collectors.toList());
        System.out.println(validWals);
        AtomicInteger a = new AtomicInteger();
        myNames.stream().filter(s->!s.startsWith("k")).forEach(s-> System.out.println(a.getAndIncrement() + " "
                                                                +s));



    }
}
