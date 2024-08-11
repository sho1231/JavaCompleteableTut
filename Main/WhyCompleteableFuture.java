package Main;

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
  public static List<Integer> supplyAsync() throws ExecutionException, InterruptedException {
    CompletableFuture<List<Integer>> f = CompletableFuture.supplyAsync(()-> Arrays.asList(1,2,3,4));
    return f.get();
  }
  public static List<Integer> supplyAsyncWithExecutor(ExecutorService executorService) throws ExecutionException, InterruptedException {
    delay(6);
    CompletableFuture<List<Integer>> f = CompletableFuture.supplyAsync(()-> Arrays.asList(1,2,3,4),executorService);
    return f.get();
  }
  public static Void runasyncMethod() throws ExecutionException, InterruptedException {
    CompletableFuture<Void> f = CompletableFuture.runAsync(()->{
      delay(6);
      System.out.println(1);
    });
   return f.get();
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
      ExecutorService executorService = Executors.newFixedThreadPool(4);
    System.out.println(supplyAsyncWithExecutor(executorService));;
    executorService.shutdown();
  }
}
