import java.util.PriorityQueue;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>();
        toyQueue.add(new Toy("1", "конструктор", 2));
        toyQueue.add(new Toy("2", "робот", 2));
        toyQueue.add(new Toy("3", "кукла", 6));

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"));
            for (int i = 0; i < 10; i++) {
                Toy toy = getToy(toyQueue);
                writer.write(toy.getId());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static Toy getToy(PriorityQueue<Toy> toyQueue) {
        Random random = new Random();
        int randomNum = random.nextInt(10);

        if (randomNum < 2) {
            return getToyById(toyQueue, "1");
        } else if (randomNum < 4) {
            return getToyById(toyQueue, "2");
        } else {
            return getToyById(toyQueue, "3");
        }
    }

    private static Toy getToyById(PriorityQueue<Toy> toyQueue, String id) {
        for (Toy toy : toyQueue) {
            if (toy.getId().equals(id)) {
                return toy;
            }
        }
        return null; // id не найден
    }

    static class Toy implements Comparable<Toy> {
        private String id;
        private String name;
        private int frequency;

        public Toy(String id, String name, int frequency) {
            this.id = id;
            this.name = name;
            this.frequency = frequency;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getFrequency() {
            return frequency;
        }

        @Override
        public int compareTo(Toy other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }
}
