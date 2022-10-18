package chap3_lambda;


public class Execute {
  public static void main(String[] args){
    execute(() -> {System.out.println("test");});
    
    execute((Action) ()->{
      System.out.println("hello");
    });
  }
  public static void execute(Runnable runnable){
    runnable.run();
  }
  public static <T> void execute(Action<T> action){
    action.act();
  }

  @FunctionalInterface
  interface Action<T>{
    void act();
  }
}
