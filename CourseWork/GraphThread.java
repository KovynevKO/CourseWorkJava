import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class GraphThread extends Thread{

    public static int thrNumb; // кількість потоків
    private int s; // вершина, з якої починається обхід
    private Graph graph;  // граф для пошуку в ширину
    public BlockingQueue<Integer> localQueue; // локальна черга для кожного потоку

    public static  int max=0;
    public static  int sumTime=0;

    public GraphThread(Graph graph, int s)
    {
        this.graph=graph;
        this.s=s;
        this.graph.visited[s] = true;
        localQueue = new LinkedBlockingDeque<>() ;
    }


    @Override
    public void run()
    {
        long startTime = System.nanoTime(); // змінна для вимірювання часу работі кожного потоку
            graph.visited[s] = true;
            localQueue.add(s);
            while (localQueue.size() != 0)
            {
                s = localQueue.poll();
                Iterator<Integer> i = graph.adj[s].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!graph.visited[n]) {
                        graph.visited[n] = true;
                        graph.distance[n]=graph.distance[s]+1;
                        localQueue.add(n);
                    }
                }
            }
        long timeSpent = System.nanoTime() - startTime;
            if(max<timeSpent)
                max=(int)timeSpent;
        sumTime+=timeSpent;
//        System.out.println("Программа выполнялась " + timeSpent + " наносекунд");
    }
}
