package Main;

import java.util.concurrent.*;
import java.util.*;
public class WhyNotFuture {
  public static void delay(int min) {
    try {
      TimeUnit.MINUTES.sleep(min);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  // Cannot forcefully stop the task even though it will block the main thread for 1 min
  public static void cannotbeforcefullystopped(ExecutorService executorService) throws ExecutionException, InterruptedException {
    Future<List<Integer>> future = executorService.submit(()->{
        delay(1);
        return Arrays.asList(1,2,3,4,5);
    });
    System.out.println(future.get());
  }
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    cannotbeforcefullystopped(executorService);
//    executorService.shutdown();
  }
}
