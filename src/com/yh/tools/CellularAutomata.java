package com.yh.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by kevinyin on 2017/8/3.
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count,
                new Runnable() {
                    @Override
                    public void run() {
                        mainBoard.commitNewValues();     
                    }
                });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSuboard(count,i));
        }
    }

    class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        public void run(){
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX();x++){
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x,y,computeValue(x,y));
                        try {
                            barrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
        }
        
        public void start() {
            for (int i = 0; i < workers.length; i++) {
                new Thread(workers[i]).start();
                mainBoard.waitForConvergence();
            }
        }
    }

    private int computeValue(int x, int y) {
        return 0;
    }
}

class Board{

    private int MaxX;
    private int MaxY;

    public void commitNewValues() {
    }

    public Board getSuboard(int count, int i) {
        return null;
    }

    public boolean hasConverged() {
        return false;
    }

    public void waitForConvergence() {
    }

    public int getMaxX() {
        return MaxX;
    }

    public int getMaxY() {
        return MaxY;
    }

    public void setNewValue(int x, int y,int value) {

    }
}

