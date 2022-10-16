package performance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ListPerformance {
  public static void main(String[] args){
    
    List<Integer> list = new LinkedList<>();
    for (int i = 0; i < 100; i++) {
        list.add(i);
    }
  
    final int TestCaseNum = 100000;
    final int IteratorNumber = 100;
  
    PerformanceTester.calcPerformance(() -> extracted1(list), TestCaseNum, IteratorNumber);
  
    PerformanceTester.calcPerformance(() -> extracted2(list), TestCaseNum, IteratorNumber);
  }

  private static void extracted1(List<Integer> list) {
    for (Integer integer : list) {
      integer++;
    }
  }
  private static void extracted2(List<Integer> list) {
    for (int i = 0, size = list.size(); i < size; i++) {
      Integer integer = list.get(i);
      integer++;
    }
  }
}
