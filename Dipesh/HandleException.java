package Dipesh;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HandleException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        boolean isError = false;
        // .handle() will take 2 argument one when of success and one of error
        CompletableFuture<String> handleMethod = CompletableFuture.supplyAsync(()->{
            if(isError) throw new NullPointerException("Error");
            else return "success";
        }).handle((success,error)->{
            if(error == null) return success;
            return error.getMessage();
        });
        // .handle() will take 2 argument one when of success and one of error
        CompletableFuture<String> exceptionallyMethod = CompletableFuture.supplyAsync(()->{
            if(isError) throw new NullPointerException("Error");
            else return "success";
        }).exceptionally((error)->{
            return "asd";
        });
        System.out.println("Executing");
        System.out.println("Result:"+handleMethod.get());
        System.out.println("Result:"+exceptionallyMethod.get());

    }
}
