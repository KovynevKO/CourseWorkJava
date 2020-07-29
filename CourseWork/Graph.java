import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

class Graph {
    public int V; // кількість вершин в графі
    public LinkedList<Integer> adj[]; // список масивів пов'язаних вершин
    public BlockingQueue<Integer> queue;
    public boolean visited[]; // масив відвіданих вершин
    public int distance[]; // масив дистанцій від початкової точки


    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
        visited= new boolean[this.V];
        queue = new LinkedBlockingDeque<>();
        distance = new int[V];
    }

    void BFS(int s)
    {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s]=true;
        queue.add(s); // включення вершини s у чергу

        while (queue.size() != 0)
        {
            s = queue.poll(); // розглядання вершини, яка перебуває на початку черги
            Iterator<Integer> i = adj[s].listIterator(); // усі вершини, які досяжні з вершини s
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true; // вершини, які досяжні з вершини s відмічаються, як пройдені
                    queue.add(n);  // вершини, які досяжні з вершини s додаються до черги
                }
            }
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    } // функція додавання зв'язку між вершинами

}