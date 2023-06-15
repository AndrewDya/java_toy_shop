import java.util.PriorityQueue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Создание приоритетной очереди и заполнение игрушками
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>();
        toyQueue.add(new Toy("1", "конструктор", 2));
        toyQueue.add(new Toy("2", "робот", 2));
        toyQueue.add(new Toy("3", "кукла", 6));

        // Вызов метода Get и запись результатов в файл
        // TODO: добавить код
    }

    private static Toy getToy(PriorityQueue<Toy> toyQueue) {
        Random random = new Random();
        int totalFrequency = toyQueue.stream().mapToInt(Toy::getFrequency).sum();
        int randomNum = random.nextInt(totalFrequency);
        int cumulativeFrequency = 0;

        for (Toy toy : toyQueue) {
            cumulativeFrequency += toy.getFrequency();
            if (randomNum < cumulativeFrequency) {
                return toy;
            }
        }

        // В случае, если произошла ошибка при расчете веса, возвращаем первую игрушку из очереди
        return toyQueue.peek();
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
