package Main.Completeable;

import java.util.concurrent.*;
import java.util.*;

public class WhyCompleteableFuture {
  public static void delay(int s) {
    try {
      TimeUnit.SECONDS.sleep(s);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static List<Integer> supplyAsyncMethod() throws ExecutionException, InterruptedException {
    CompletableFuture<List<Integer>> f = CompletableFuture.supplyAsync(
            ()->{
              delay(7);
              System.out.println(Thread.currentThread().getName());
              return Arrays.asList(1,2,3,4);
            }
    );
    return new ArrayList<>();
  }
  public static List<Integer> supplyAsyncWithExecutor(ExecutorService executorService) throws ExecutionException, InterruptedException {

    CompletableFuture<List<Integer>> f = CompletableFuture.supplyAsync(()->{
      delay(6);
      System.out.println(Thread.currentThread().getName());
      return Arrays.asList(1,2,3,4);
    },executorService);

    return new ArrayList<>();
  }
  public static void runasyncMethod() throws ExecutionException, InterruptedException {
    CompletableFuture<Void> f = CompletableFuture.runAsync(()->{
      delay(120);
      System.out.println(Thread.currentThread().getName());
      System.out.println(1);
    });

  }
  public static Void runasyncMethodWithCustomExecutor() throws ExecutionException, InterruptedException {
    CompletableFuture<Void> f = CompletableFuture.runAsync(()->{
      delay(6);
      System.out.println(1);
    }, Executors.newFixedThreadPool(4));
    return f.get();
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // complete method of the completeablefuture can stop the task forcefully
//    System.out.println(runasyncMethodWithCustomExecutor());
//    System.out.println(12323);
//      ExecutorService executorService = Executors.newFixedThreadPool(4);
    runasyncMethod();
//    executorService.shutdown();
    System.out.println("abc:"+Thread.currentThread().getName());
  }
}
