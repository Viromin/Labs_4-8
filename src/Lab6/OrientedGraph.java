package Lab6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrientedGraph {
    private Map<Integer, List<Integer>> adjacencyList;


    public OrientedGraph(int vertices) {
        adjacencyList = new HashMap<>();
        for (int i = 1; i <= vertices; i++) {
            addVertex(i);
        }
    }


    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) {
            adjacencyList.get(source).add(destination);
        } else {
            System.out.println("Невірне ребро: Вершина не знайдена.");
        }
    }


    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }


    public void printAdjacencyMatrix() {
        int vertices = adjacencyList.size();
        int[][] matrix = new int[vertices + 1][vertices + 1];
        // Заповнення індексів вершин у перший рядок та перший стовпець
        for (int i = 1; i <= vertices; i++) {
            matrix[0][i] = i;
            matrix[i][0] = i;
        }


        // Заповнення матриці
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int source = entry.getKey();
            for (Integer destination : entry.getValue()) {
                matrix[source][destination] = 1;
            }
        }
        // Виведення матриці
        for (int i = 0; i <= vertices; i++) {
            for (int j = 0; j <= vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printIncidenceMatrix() {
        int vertices = adjacencyList.size();
        int edges = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            edges += neighbors.size();
        }


        int[][] incidenceMatrix = new int[vertices + 1][edges + 1];
        // Заповнення номерів вершин у перший рядок
        for (int i = 1; i <= vertices; i++) {
            incidenceMatrix[i][0] = i;
        }



        // Заповнення номерів ребер у перший стовпець
        for (int i = 1; i <= edges; i++) {
            incidenceMatrix[0][i] = i;
        }


        // Заповнення матриці інцидентності

        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int source = entry.getKey();
            for (Integer destination : entry.getValue()) {
                int edgeIndex = findEdgeIndex(source, destination) + 1; // Додаємо 1 для врахування першого стовпця
                incidenceMatrix[source][edgeIndex] = -1;
                incidenceMatrix[destination][edgeIndex] = 1;
            }
        }



        // Виведення матриці інцидентності

        for (int i = 0; i <= vertices; i++) {
            for (int j = 0; j <= edges; j++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }



    private int findEdgeIndex(int source, int destination) {
        int edgeIndex = 0;
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int currentSource = entry.getKey();
            for (Integer currentDestination : entry.getValue()) {
                if (currentSource == source && currentDestination == destination) {
                    return edgeIndex;
                }
                edgeIndex++;
            }
        }
        return -1; // Ребро не знайдено
    }



    public void printEdgeList() {
        int edgeNumber = 1;
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int source = entry.getKey();
            for (Integer destination : entry.getValue()) {
                System.out.println("Ребро " + edgeNumber + ": " + source + " -> " + destination);
                edgeNumber++;
            }
        }
    }
    public void printEdgeListFromAdjacencyMatrix() {
        int vertices = adjacencyList.size();
        int edgeNumber = 1;

        // Проходимося по всій матриці суміжності
        for (int i = 1; i <= vertices; i++) {
            for (int j = 1; j <= vertices; j++) {
                if (adjacencyList.containsKey(i) && adjacencyList.get(i).contains(j)) {
                    System.out.println("Ребро " + edgeNumber + ": " + i + " -> " + j);
                    edgeNumber++;
                }
            }
        }

    }

    public void printAdjacencyMatrixFromIncidenceMatrix() {
        int vertices = adjacencyList.size();
        int[][] matrix = new int[vertices + 1][vertices + 1];
        // Заповнення індексів вершин у перший рядок та перший стовпець
        for (int i = 1; i <= vertices; i++) {
            matrix[0][i] = i;
            matrix[i][0] = i;
        }



        // Заповнення матриці

        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int source = entry.getKey();
            for (Integer destination : entry.getValue()) {
                matrix[source][destination] = 1;
            }
        }


        // Виведення матриці
        for (int i = 0; i <= vertices; i++) {
            for (int j = 0; j <= vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void printGraphFromIncidenceMatrix() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        OrientedGraph graph = new OrientedGraph(6);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 1);
        graph.addEdge(2, 4);
        graph.addEdge(3, 1);
        graph.addEdge(3, 5);
        graph.addEdge(3, 6);
        graph.addEdge(4, 2);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        System.out.println("Матриця суміжності для орієнтованого графа:");
        graph.printAdjacencyMatrix();
        System.out.println("Матриця інцидентності для орієнтованого графа:");
        graph.printIncidenceMatrix();
        System.out.println("Список ребер для орієнтованого графа:");
        graph.printEdgeList();
        System.out.println("Список суміжності для орієнтованого графа:");
        graph.printGraph();
        System.out.println("Ребра з матриці суміжності:");
        graph.printEdgeListFromAdjacencyMatrix();
        System.out.println("Матриця суміжності з матриці інцидентності:");
        graph.printAdjacencyMatrixFromIncidenceMatrix();
        System.out.println("Список суміжностей з матриці інцидентності:");
        graph.printGraphFromIncidenceMatrix();
    }
}
