package Dipesh;

import java.util.concurrent.*;

public class FutureTut {
    public static void delay(int delay) {
        try{
            TimeUnit.SECONDS.sleep(delay);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void asyncUsingFuture() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> f = executorService.submit(()->{
            delay(4);
            System.out.println("Test 123");
            return "123";
        });
//        System.out.println("f:"+f.get());
        executorService.shutdown();
    }
    public static CompletableFuture<Void> usingRunAsync() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> c = CompletableFuture.runAsync(()->{
            delay(3);
            System.out.println("Thread name:"+Thread.currentThread().getName());
        },executorService);
        executorService.shutdown();
        return c;
    }

    public static CompletableFuture<String> usingSupplyAsync() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<String> c = CompletableFuture.supplyAsync(()->{
            delay(3);
            System.out.println("Thread name:"+Thread.currentThread().getName());
            return "OK";
        },executorService);
        executorService.shutdown();
        return c;
    }

    public static String chainingMethods() {
        return CompletableFuture.supplyAsync(()->"acv").thenApply(s->"hello "+s).join();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Done:"+chainingMethods());
    }
}
