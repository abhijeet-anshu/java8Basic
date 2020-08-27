package helloLambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class fileReaderUtil {

    //behaviour implemented the old way
    public static String readFirstLine() throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader("C:\\Users\\abbaranw\\java8Basic\\out\\production\\java8Basic\\data.txt")))
        {
            return br.readLine();
        }

    }

    //create behavior on files
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException; // this represents the behavior that needs bufferedReader as input
    }
    //accept behaviour parameter
    //and execute

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader("C:\\Users\\abbaranw\\java8Basic\\out\\production\\java8Basic\\data.txt"))) {
            return p.process(br);
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void  main(String args[]) throws  IOException{
        print(readFirstLine());

        print(processFile((BufferedReader r) -> r.readLine() + r.readLine()
        ));

        //another way
        Function<BufferedReader, String> f =
                (BufferedReader b) -> {
                    try {
                        return b.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                };
        //below won't work as anonymous function
        //processFile((BufferedReaderProcessor) f);




    }

}
