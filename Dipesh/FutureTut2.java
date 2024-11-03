package Dipesh;

import java.util.List;
import java.util.concurrent.*;

public class FutureTut2 {
    private static void delay(int time)  {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static CompletableFuture<String> getUser(String user,ExecutorService executorService) {
        return CompletableFuture.supplyAsync(()->{
            delay(4);
            System.out.println("started executing getUser:"+Thread.currentThread().getName());
            return "user is "+user;
        },executorService );
    }
    public static CompletableFuture<String> getWishList(String user,ExecutorService executorService) {
        return CompletableFuture.supplyAsync(()->{
            System.out.println("started executing getWishList:"+Thread.currentThread().getName());
            delay(2);
            return user + " and wishlist:car";
        },executorService);
    }
    public static CompletableFuture<List<String>> getUserList(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(()->{
            delay(4);
            System.out.println("started executing getUserList:"+Thread.currentThread().getName());
            return List.of("user1","user2","user3");
        },executorService );
    }
    public static CompletableFuture<String> getWeatherReport(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(()->{
            delay(4);
            System.out.println("started executing getWeatherReport:"+Thread.currentThread().getName());
            return "Sunny";
        },executorService );
    }
    public static void main(String[] args)  {
        long start  = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        CompletableFuture<String> getWishListOfUser = getUser("Shourja",executorService).thenCompose((user)->getWishList(user,executorService));
        // here thenCombine will take argument first argument as the completeable future and second argument as a method which will do something
        // after both method is being completely executed
        CompletableFuture<String> sendWeatherReport = getUserList(executorService).thenCombine(getWeatherReport(executorService),(userList,report)->{
            // The below line will not necessary be executed on the same thread as getWeatherReport
            return "Weather Report is sunny and sending to:"+userList+" "+Thread.currentThread().getName();
        });
        System.out.println("Random task 1");
        delay(2);
        System.out.println("Random task 2");
        delay(3);
//        System.out.println(getWishListOfUser.join());
        System.out.println(sendWeatherReport.join());
        executorService.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("time taken:"+(end-start)/1000);
    }
}
