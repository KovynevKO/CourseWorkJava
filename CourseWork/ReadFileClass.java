import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFileClass {
    private static int n;
    public static int [][] myArr;
    public static Graph g = new Graph(n);

    public static Graph ReadFile(String name, int numb) throws IOException {
        n=numb;
        myArr = new int[n][n];
        g = new Graph(n);

        BufferedReader br = new BufferedReader(new FileReader(name)); // читання вмісту файлу в список рядків
            List<String> lines = new ArrayList<>();
            while (br.ready()) {
                lines.add(br.readLine());
            }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String[] line = lines.get(i).split(" "); // поділ рядків по пропуску
                myArr[i][j] = Integer.parseInt(line[j]); // запис значень в матрицю суміжності
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(myArr[i][j]==1)
                {
                    g.addEdge(i,j); // додавання вершин в граф
                }

            }
        }
        return g;
    }
}
