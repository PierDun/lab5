package com.company;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class MySig {
    public static void shhh(int s){ //s -> seconds :)
        s = s*1000;
        try{
            Thread.sleep(s);
        }catch(InterruptedException e){
            System.out.println("Uh-oh :(");
        }
    }

}
