package chap15_reactive_programming;

import static chap15_reactive_programming.Functions.f;
import static chap15_reactive_programming.Functions.g;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFComplete {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
      ExecutorService executorService = Executors.newFixedThreadPool(10);
      int x = 1337;

      CompletableFuture<Integer> a = new CompletableFuture<>();
      executorService.submit(() -> a.complete(f(x)));
      int b = g(x);
      System.out.println(a.get() + b);

      executorService.shutdown();
  }

}
