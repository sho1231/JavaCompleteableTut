package Main.Completeable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteablePartTwo {
  public static Map<String,Object> test1()  {
    try {
      System.out.println("Thread:"+Thread.currentThread().getName());
//      Thread.sleep(60000);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return Map.of("Shourja","ganguly");
  }
  public static Map<String,Object> test2()  {
    try {
      System.out.println("Thread:"+Thread.currentThread().getName());
//      Thread.sleep(5000);
//      System.out.println("Thread:"+Thread.currentThread().getName());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return Map.of("Shourja","ganguly");
  }

  public static void main(String[] args) throws Exception{
    ExecutorService executorService1 = Executors.newFixedThreadPool(1);
    ExecutorService executorService2 = Executors.newFixedThreadPool(1);
    CompletableFuture<Map<String,Object>> r = CompletableFuture.supplyAsync(()  ->test1(),executorService1);
    CompletableFuture<Map<String,Object>> r1 = CompletableFuture.supplyAsync(()  ->test2(),executorService2);
    System.out.println("r1:"+r1.get());
    System.out.println("r:"+r.get());
    executorService1.shutdown();
    executorService2.shutdown();
  }
}
