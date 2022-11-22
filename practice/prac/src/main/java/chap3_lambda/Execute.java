package chap3_lambda;


public class Execute {
  public static void main(String[] args){
    execute1(() -> {System.out.println("test");});
    
    execute2((Action) ()->{
      System.out.println("hello");
    });
  }
  public static void execute1(Runnable runnable){
    runnable.run();
  }
  public static <T> void execute2(Action<T> action){
    action.act();
  }

  @FunctionalInterface
  interface Action<T>{
    void act();
  }
}
