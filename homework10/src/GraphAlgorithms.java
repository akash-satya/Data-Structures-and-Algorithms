import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Akash Satya
 * @userid asatya8
 * @GTID 903896933
 * @version 1.0
 * 
 * By typing 'I agree' below, you are agreeing that this is your
 * own work and that you are responsible for all the contents of 
 * this file. If this is left blank, this homework will receive a zero.
 * 
 * Agree Here: I agree
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("The start vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("The graph cannot be null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The start vertex is not in the graph");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        List<Vertex<T>> traversal = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();
            traversal.add(v);

            for (VertexDistance<T> vertex: graph.getAdjList().get(v)) {
                if (!visited.contains(vertex.getVertex())) {
                    visited.add(vertex.getVertex());
                    queue.add(vertex.getVertex());
                }
            }
        }
        return traversal;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("the start vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("the graph cannot be null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("the start vertex is not in the graph");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> traversal = new ArrayList<>();
        dfsHelper(start, visited, traversal, graph);

        return traversal;
    }

    /**
     * Helper method for the depth-first search algorithm and processing. 
     * @param start the start vertex
     * @param visited set with the elements that are visited
     * @param traversal the list of elements in the order they are traversed
     * @param graph the graph of vertices
     * @param <T> the type of elements
     */

    private static <T> void dfsHelper(Vertex<T> start, Set<Vertex<T>> visited, List<Vertex<T>> traversal,
                                     Graph<T> graph) {
        visited.add(start);
        traversal.add(start);

        for (VertexDistance<T> vertex: graph.getAdjList().get(start)) {
            if (!visited.contains(vertex.getVertex())) {
                dfsHelper(vertex.getVertex(), visited, traversal, graph);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("the start vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("the graph cannot be null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("the start vertex is not in the graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        Map<Vertex<T>, Integer> map = new HashMap<>();
        Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>();

        for (Vertex<T> vertex : graph.getVertices()) {
            map.put(vertex, Integer.MAX_VALUE);
        }
        priorityQueue.add(new VertexDistance<>(start, 0));
        map.put(start, 0);

        while (!priorityQueue.isEmpty() && graph.getVertices().size() > visited.size()) {
            VertexDistance<T> vertexDistance = priorityQueue.remove();

            if (!visited.contains(vertexDistance.getVertex())) {
                visited.add(vertexDistance.getVertex());
                map.put(vertexDistance.getVertex(), vertexDistance.getDistance());
                for (VertexDistance<T> adjacentVertex : graph.getAdjList().get(vertexDistance.getVertex())) {

                    if (!visited.contains(adjacentVertex.getVertex())) {
                        priorityQueue.add(new VertexDistance<>(
                                adjacentVertex.getVertex(),
                                vertexDistance.getDistance() + adjacentVertex.getDistance()));
                    }
                }
            }
        }

        return map;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use PriorityQueue, java.util.Set, and any class that 
     * implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("the start vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("the graph cannot be null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("the start vertex is not in the graph");
        }

        if (graph.getEdges().size() == 0) {
            throw new IllegalArgumentException("The graph is not connected");
        }

        Set<Vertex<T>> visited = new HashSet<>();

        Set<Edge<T>> edges = new HashSet<>();

        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>();

        visited.add(start);

        for (Edge<T> edge : graph.getEdges()) {
            if (edge.getU().equals(start)) {
                priorityQueue.add(edge);
            }
        }

        while (!(priorityQueue.size() == 0) && graph.getVertices().size() > visited.size()) {
            Edge<T> Edge = priorityQueue.remove();

            if (!visited.contains(Edge.getV())) {
                edges.add(Edge);

                visited.add(Edge.getV());

                edges.add(new Edge<T>(Edge.getV(), Edge.getU(), Edge.getWeight()));
                for (Edge<T> edge : graph.getEdges()) {

                    if (Edge.getV().equals(edge.getU())) {

                        if (!visited.contains(edge.getV())) {
                            priorityQueue.add(edge);
                        }
                    }
                }
            }
        }

        if (visited.size() != graph.getVertices().size()) {
            return null;
        }

        return edges;
    }

    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the DisjointSet and DisjointSetNode classes that
     * have been provided to you for more information.
     *
     * An MST should NOT have self-loops or parallel edges.
     *
     * By using the Disjoint Set provided, you can avoid adding self-loops and
     * parallel edges into the MST.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Set, and any class that implements the aforementioned
     * interfaces.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        
        return null;
    }
}