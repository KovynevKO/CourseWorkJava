import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть назву файлу:");
        String name = scanner.nextLine(); // назва файлу з матрицею суміжності
        System.out.println("Введіть розмір графа:");
        int v = scanner.nextInt();  // розмір графа

        System.out.println("Введіть кількість потоків:");
        GraphThread.thrNumb = scanner.nextInt(); // кількість потоків
        System.out.println("Введіть вершину, з якої починається обхід:");
        int s = scanner.nextInt(); // вершина, з якої починається обхід


        Graph g=null;
        try {
            g=ReadFileClass.ReadFile(name, v); // зчитання матриці в граф g
        } catch (IOException e) {

        }
        ParallelBFSClass.ParallelBFS(g,s);
//        long startTime = System.nanoTime();
//        g.BFS(1);
//        long timeSpent = System.nanoTime() - startTime;
//        System.out.println(timeSpent);

        System.out.println("Середній час виконання потоків: ");
        System.out.println(GraphThread.sumTime/GraphThread.thrNumb);
        System.out.println("Максимальний час виконання потоку : ");
        System.out.println(GraphThread.max);
    }
}
