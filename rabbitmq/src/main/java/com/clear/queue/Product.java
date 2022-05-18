package com.clear.queue;

import java.util.concurrent.BlockingDeque;

public class Product implements Runnable {
    private BlockingDeque<KouZhao> queue;

    private Integer id;

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
                KouZhao kouZhao = new KouZhao("N95", id++);
                System.out.println("正在生产");
                queue.push(kouZhao);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
