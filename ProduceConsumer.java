import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProduceConsumer {
    public static void main(String[] args) throws Exception  {


        CubbyHole cubby1 = new CubbyHole();
        Consumer c1 = new Consumer(cubby1, "banana", "carrots");

        Producer p1 = new Producer(cubby1, "Apple");

        p1.start();
        c1.start();
    }
}

   class CubbyHole {
    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        contents = value;
        available = true;
        notifyAll();
    }
}


  class Consumer extends Thread {

       private CubbyHole cubbyhole;
       private String Consumer1;
       private String Consumer2;

    public Consumer(CubbyHole c, String Consumer1, String Consumer2) {
        cubbyhole = c;
        this.Consumer1 = Consumer1;
        this.Consumer2 = Consumer2;
    }

    public void run() {

            System.out.println("Consumer is: " + this.Consumer1);
            System.out.println("Consumer is: " + this.Consumer2);
        }
    }




   class Producer extends Thread {
    private CubbyHole cubbyhole;
    private String producer;

    public Producer(CubbyHole c, String producer) {
        cubbyhole = c;
        this.producer = producer;
    }

    public void run() {

        System.out.println("Producer is: " + this.producer);
        File file = new File("C:/Users/musta/desktop/song.txt");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(input.hasNext()) {

            char ch = input.next().charAt(0);

            if(ch == 'a'|| ch == 'e'|| ch == 'i' ||ch == 'o' ||ch == 'u'||ch == ' '){
                System.out.println("The word has a starting letter vowel");

            }else{
                System.out.println("The word has a starting letter consonant");
            }

        }

            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }

