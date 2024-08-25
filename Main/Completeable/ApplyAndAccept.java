package Main.Completeable;


import java.util.concurrent.*;

public class ApplyAndAccept {


  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // This will run the chain of process in different thread according jvm thread scheduler
    // if we remove the async then it will run on single thread
    /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
      try {
        System.out.println("Current thread 1st task:"+Thread.currentThread().getName());
        Thread.sleep(6000);
        System.out.println("done");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).thenAcceptAsync((e)->{
      try {
        System.out.println("Current thread 2st task:"+Thread.currentThread().getName());
        Thread.sleep(4000);
        System.out.println("done");
      } catch (InterruptedException exception) {
        throw new RuntimeException(exception);
      }
    }).thenAcceptAsync((e)->{
      try {
        System.out.println("Current thread 3rd task:"+Thread.currentThread().getName());
        Thread.sleep(8000);
        System.out.println("done");
      } catch (InterruptedException exception) {
        throw new RuntimeException(exception);
      }
    }).thenAcceptAsync((e)->{
      try {
        System.out.println("Current thread 4th task:"+Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("done");
      } catch (InterruptedException exception) {
        throw new RuntimeException(exception);
      }
    });*/
    // to manually run these chain of task concurrently that's on different thread then
    Executor executorService = Executors.newFixedThreadPool(5);
    Executor executorService2 = Executors.newFixedThreadPool(3);
    // not recommended to use multiple executors
    CompletableFuture<Void> completableFutureAysnc = CompletableFuture.runAsync(()->{
      try {
        System.out.println("Current thread 1st task:"+Thread.currentThread().getName());
        Thread.sleep(6000);
        System.out.println("done");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    },executorService).thenAcceptAsync((e)->{
      try {
        System.out.println("Current thread 2st task:"+Thread.currentThread().getName());
        Thread.sleep(4000);
        System.out.println("done");
      } catch (InterruptedException exception) {
        throw new RuntimeException(exception);
      }
    },executorService2).thenAcceptAsync((e)->{
      try {
        System.out.println("Current thread 3rd task:"+Thread.currentThread().getName());
        Thread.sleep(8000);
        System.out.println("done");
      } catch (InterruptedException exception) {
        throw new RuntimeException(exception);
      }
    },executorService).thenAcceptAsync((e)->{
      try {
        System.out.println("Current thread 4th task:"+Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("done");
      } catch (InterruptedException exception) {
        throw new RuntimeException(exception);
      }
    },executorService2);
    System.out.println(completableFutureAysnc.get());
//    executorService
//    executorService.execute();
  }
}
