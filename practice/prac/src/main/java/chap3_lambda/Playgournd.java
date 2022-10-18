package chap3_lambda;

import java.util.function.Predicate;
import java.util.function.IntPredicate;

public class Playgournd {
  public static void main(String[] args){
    
    IntPredicate evenNumbers = (int i) -> i%2==0;
    //not authboxing
    boolean output = evenNumbers.test(1000);
    //true
    System.out.println(output);

    
    Predicate<Integer> oddNumbers = (Integer i )->i%2!=0;
    
    //autoboxing to Integer
    boolean output2 = oddNumbers.test(1000); 
    //false
    System.out.println(output2);

  }
  
  // @FunctionalInterface
  // public interface IntPredicate{
  //   boolean test(int t);
  // }
}
