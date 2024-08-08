package Main;

import java.util.concurrent.*;
import java.util.*;
public class Asynchron {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Future<List<Integer>> f = executorService.submit(()-> {
      try {
//        System.out.println(10/0);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return Arrays.asList(1, 2, 3);
    });
    System.out.println(123);
    executorService.shutdownNow();
  }
}
