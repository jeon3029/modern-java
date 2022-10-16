package performance;

public class PerformanceTester {
  static void calcPerformance(Runnable r,final int runTimes, final int iterTimes){
    long startTime = System.currentTimeMillis();
    for(int i=0;i<runTimes;i++){
      for(int j=0;j<iterTimes;j++){
        r.run();
      }
    }
    long finishTime = System.currentTimeMillis();
    System.out.println("Total : " + String.valueOf((double)(finishTime - startTime) / 1000));
  }
}
