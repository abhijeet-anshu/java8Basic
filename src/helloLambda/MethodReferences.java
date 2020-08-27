package helloLambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MethodReferences {

    static class Bicycle {
        String brandName;
        Bicycle(String brandName) {
            System.out.println("Constructing... cycle for brand - " + brandName);
            this.brandName = brandName;
        }
        String getBrandName() {
            return brandName;
        }
        public String toString() {
            return "brand::"+brandName;
        }
    }

    private static <T> Boolean[] doNothing(Object... o) {
        System.out.println("My Object : " + o);
        System.out.println("At index 0 : " + o[0]);
        //System.out.println("At index 1 : " + o[1]);

        return Arrays.stream(o).map(s-> (s instanceof Bicycle)).toArray(Boolean[]::new);
        //return true;
    }

    /*
    just for practice
     */
    private static <T> String[] getNames(Object... o) {
        System.out.println("My Named Object : " + o[0]);
        //Bicycle abc = new Bicycle("ww");
        //boolean p = abc instanceof Bicycle;

        //if(1+1==2)
        return Arrays.stream(o).filter(s-> (s instanceof Bicycle)).map(b->((Bicycle) b).getBrandName()).toArray(String[]::new);

        //if(1==2) return null;
            //does not work
          //  return Arrays.stream(o).filter(s-> (s instanceof Bicycle)).map(Bicycle::getBrandName).toArray(String[]::new);


        //return true;
    }


    public static void main(String[] args) {
        List<String> cycleBrands = Arrays.asList("Hercules", "MTB", "ATLAS", "NI", "DEC");

        Bicycle a = new Bicycle("wsn");

        //get list of cycles for each brand
        List<Bicycle> b = cycleBrands.stream().map(s->new Bicycle(s)).collect(toList());

        //better written as
        List<Bicycle> c = cycleBrands.stream().map(Bicycle::new).collect(toList());

        //to Array
        Bicycle[] abc = cycleBrands.stream().map(Bicycle::new).toArray(Bicycle[]::new);

        //below also works
        cycleBrands.stream().map(Bicycle::new).map(MethodReferences::doNothing).flatMap(Arrays::stream).forEach(System.out::println);

        cycleBrands.stream().map(Bicycle::new).map(MethodReferences::getNames).flatMap(Arrays::stream).forEach(System.out::println);
    }
}
