package performance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ListPerformance {
  public static void main(String[] args){
    // LinkedList 일 때, ArrayList 일 때 각각 테스트
    List<Integer> list = new LinkedList<>();
    for (int i = 0; i < 100; i++) {
        list.add(i);
    }
  
    final int TestCaseNum = 100000;
    final int IteratorNumber = 100;
  
    PerformanceTester.calcPerformance(() -> forEachLoops(list), TestCaseNum, IteratorNumber);
  
    PerformanceTester.calcPerformance(() -> traditionalForLoops(list), TestCaseNum, IteratorNumber);
  }

  private static void forEachLoops(List<Integer> list) {
    for (Integer integer : list) {
      integer++;
    }
  }
  private static void traditionalForLoops(List<Integer> list) {
    for (int i = 0, size = list.size(); i < size; i++) {
      Integer integer = list.get(i);
      integer++;
    }
  }
}
