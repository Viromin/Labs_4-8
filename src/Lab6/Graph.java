package Lab6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Graph {
    private int numVertices;
    private Map<Integer, List<Integer>> adjacencyList;
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 1; i <= numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }


    public void addEdge(int vertex1, int vertex2) {
        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }



    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Integer vertex : entry.getValue()) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }
    public void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = new int[numVertices + 1][numVertices + 1];
        // Заповнення матриці суміжності
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int vertex1 = entry.getKey();
            for (Integer vertex2 : entry.getValue()) {
                adjacencyMatrix[vertex1][vertex2] = 1;
                adjacencyMatrix[vertex2][vertex1] = 1; // Граф невагований, тому встановлюємо 1 в обидва напрямки
            }
        }



        // Виведення матриці суміжності

        System.out.print("   ");
        for (int i = 1; i <= numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i <= numVertices; i++) {
            System.out.print(i + ": ");
            for (int j = 1; j <= numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printIncidenceMatrix() {
        int[][] incidenceMatrix = new int[numVertices + 1][adjacencyList.size()];
        // Заповнення матриці інцидентності
        for (int j = 0; j < adjacencyList.size(); j++) {
            for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
                int vertex = entry.getKey();
                List<Integer> neighbors = entry.getValue();
                if (neighbors.contains(j + 1)) {
                    incidenceMatrix[vertex][j] = 1;
                } else if (neighbors.contains(-(j + 1))) {
                    incidenceMatrix[vertex][j] = -1;
                }
            }
        }
        // Виведення матриці інцидентності

        System.out.print("   ");
        for (int i = 1; i <= numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();



        for (int j = 0; j < incidenceMatrix[0].length; j++) {
            System.out.print((j + 1) + ": ");
            for (int i = 1; i <= numVertices; i++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }



    public void printGraphEdges() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int vertex1 = entry.getKey();
            for (Integer vertex2 : entry.getValue()) {
                if (vertex1 < vertex2) {
                    System.out.println(vertex1 + " - " + vertex2);
                }
            }
        }
    }

    public void printIncidenceMatrixFromAdjacency() {
        int[][] incidenceMatrix = new int[numVertices + 1][adjacencyList.size()];
        // Заповнення матриці інцидентності
        int edgeIndex = 0;
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int vertex1 = entry.getKey();
            for (Integer vertex2 : entry.getValue()) {
                if (vertex1 < vertex2) {
                    incidenceMatrix[vertex1][edgeIndex] = 1;
                    incidenceMatrix[vertex2][edgeIndex] = 1;
                }
            }
            edgeIndex++;
        }



        // Виведення матриці інцидентності

        System.out.print("   ");
        for (int i = 1; i <= incidenceMatrix.length - 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int j = 0; j < incidenceMatrix[0].length; j++) {
            System.out.print((j + 1) + ": ");
            for (int i = 1; i <= incidenceMatrix.length - 1; i++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();

        }

    }

    public void printEdgeListFromAdjacency() {
        for (int i = 1; i <= numVertices; i++) {
            for (int j = i + 1; j <= numVertices; j++) {
                if (adjacencyList.get(i).contains(j)) {
                    System.out.println(i + " - " + j);
                }
            }
        }
    }



    public void printAdjacencyListFromAdjacency() {
        for (int i = 1; i <= numVertices; i++) {
            System.out.print(i + "-> ");
            for (int j = 1; j <= numVertices; j++) {
                if (adjacencyList.get(i).contains(j)) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numVertices = 7;
        Graph graph = new Graph(numVertices);
        // Додавання ребер A
        graph.addEdge(1, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        System.out.println("Матриця суміжності для неорієнтованого графа:");
        graph.printAdjacencyMatrix();
        System.out.println("Матриця інцидентності для неорієнтованого графа:");
        graph.printIncidenceMatrix();
        System.out.println("Список ребер для неорієнтованого графа:");
        graph.printGraphEdges();
        System.out.println("Список суміжностей для неорієнтованого графа:");
        graph.printGraph();
        System.out.println("Матриця інцидентності  для неорієнтованого графа побудована на основі матриці суміжності:");
        graph.printIncidenceMatrixFromAdjacency();
        System.out.println("Список ребер для неорієнтованого графа на основі матриці суміжності:");
        graph.printEdgeListFromAdjacency();
        System.out.println("Список суміжностей для неорієнтованого графа на основі матриці суміжності:");
        graph.printAdjacencyListFromAdjacency();
    }
}
