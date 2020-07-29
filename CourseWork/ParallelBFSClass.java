import java.util.Iterator;

public class ParallelBFSClass {
    public static void ParallelBFS(Graph g, int s)
    {
        GraphThread[] parThreads = new GraphThread[GraphThread.thrNumb]; // масив потоків
        int [] arrS = new int[GraphThread.thrNumb]; // масив вершин, які будуть видані потокам
        // робота послідовного алгоритму до моменту розпаралелювання
        g.visited[s] = true;
        g.distance[s]=0;
        g.queue.add(s);
        while (g.queue.size() < GraphThread.thrNumb) // поки кількість вершин в черзі послідовного алгоритму лише дорівнюватиме кількості потоків
        {
            s = g.queue.poll();

            Iterator<Integer> i = g.adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!g.visited[n]) {
                    g.visited[n] = true;
                    g.distance[n]=g.distance[s]+1;
                    g.queue.add(n);
                }
            }
        }
        for(int i=0;i<GraphThread.thrNumb;i++)
        {
            arrS[i]=g.queue.poll();
        }
        for(int i=0;i<GraphThread.thrNumb;i++) // розпаралелювання пошуку в ширину
        {
            parThreads[i]=new GraphThread(g,arrS[i]);
            parThreads[i].start();
        }

        for(GraphThread thr : parThreads)
        {
            try {
                thr.join();
            } catch (InterruptedException e) {

            }
        }
    }
}
