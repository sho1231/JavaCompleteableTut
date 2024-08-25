package Main.Completeable;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CheckQueue {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    for(int i=0;i<4;i++) {
      TimeUnit.SECONDS.sleep(4);
      executorService.submit(new Make());
    }
    executorService.shutdown();
  }
  static class Make implements Runnable{
    @Override
    public void run() {
      System.out.println("Thread:"+Thread.currentThread().getName());
      System.out.println("Thread state:"+Thread.currentThread().getState());;
    }
  }
}
