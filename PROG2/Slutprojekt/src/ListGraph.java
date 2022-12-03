// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398

import java.io.Serializable;
import java.util.*;

public class ListGraph<T> implements Graph<T>, Serializable {


    private final Map<T, Set<Edge<T>>> nodes = new HashMap<>();

    public void add(T node){
        nodes.putIfAbsent(node, new HashSet<>());
    }

    public void connect(T node1, T node2, String name, int weight){
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2))
            throw new NoSuchElementException("Node(s) does not exist");
        if (weight < 0)
            throw new IllegalArgumentException("Cannot set negative weight");

        if (getEdgeBetween(node1, node2) != null)
            throw new IllegalStateException("Edge already exists");

        Set<Edge<T>> edges1 = nodes.get(node1);
        Set<Edge<T>> edges2 = nodes.get(node2);


        edges1.add(new Edge<T>(name, weight, node2));
        edges2.add(new Edge<T>(name, weight, node1));
    }

    @Override
    public void setConnectionWeight(T node1, T node2, int weight) {
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2))
            throw new NoSuchElementException();
        if (weight < 0)
            throw new IllegalArgumentException();

        Edge<T> edge1 = getEdgeBetween(node1, node2);
        Edge<T> edge2 = getEdgeBetween(node2, node1);

        edge1.setWeight(weight);
        edge2.setWeight(weight);

    }

    @Override
    public Set<T> getNodes() {
        return Collections.unmodifiableSet(nodes.keySet());
    }

    @Override
    public Collection<Edge<T>> getEdgesFrom(T node) {
        if (!getNodes().contains(node))
            throw new NoSuchElementException();
        return Collections.unmodifiableCollection(nodes.get(node));
    }

    @Override
    public Edge<T> getEdgeBetween(T node1, T node2) {
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2))
            throw new NoSuchElementException();

        Set<Edge<T>> edges = nodes.get(node1);

        for (Edge<T> edge : edges)
            if (edge.getDestination().equals(node2))
                return edge;
        return null;
    }

    public void disconnect(T a, T b) {
        if (!nodes.containsKey(a) || !nodes.containsKey(b))
            throw new NoSuchElementException();

        Edge<T> edgeA = getEdgeBetween(a, b);
        Edge<T> edgeB = getEdgeBetween(b, a);

        if (Objects.isNull(edgeA) || Objects.isNull(edgeB))
            throw new IllegalStateException();

        nodes.get(a).remove(edgeA);
        nodes.get(b).remove(edgeB);
    }

    @Override
    public void remove(T node) {
        if (!nodes.containsKey(node))
            throw new NoSuchElementException();
        for (T n : nodes.keySet()){
            if (getEdgeBetween(n, node) != null)
                disconnect(node, n);
        }
        nodes.remove(node);
    }

    @Override
    public boolean pathExists(T from, T to) {
        if (!nodes.containsKey(from) || !nodes.containsKey(to))
            return false;

        Set<T> visited = new HashSet<>();
        depthFirstVisitAll(from, visited);
        return visited.contains(to);
    }

    private void depthFirstVisitAll(T current, Set<T> visited){
        visited.add(current);
        for (Edge<T> edge : nodes.get(current))
            if (!visited.contains(edge.getDestination()))
                depthFirstVisitAll((T) edge.getDestination(), visited);
    }

    @Override
    public List<Edge<T>> getPath(T from, T to) {
        Map<T, T> connections = new HashMap<>();
        connections.put(from, null);

        LinkedList<T> queue = new LinkedList<>();
        queue.add(from);

        while (!queue.isEmpty()){
            T current = queue.pollFirst();
            for (Edge<T> node : nodes.get(current)){
                T destination = node.getDestination();
                if (!connections.containsKey(destination)) {
                    connections.put(destination, current);
                    queue.add(destination);
                }
            }
        }

        if (!connections.containsKey(to))
            return null;

        return gatherPath(from, to, connections);

    }

    private List<Edge<T>> gatherPath(T from, T to, Map<T, T> connections){
        LinkedList<Edge<T>> path = new LinkedList<>();
        T current = to;
        while (!current.equals(from)){
            T next = connections.get(current);
            Edge<T> edge = getEdgeBetween(next, current);
            path.addFirst(edge);
            current = next;
        }
        return Collections.unmodifiableList(path);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (T node : nodes.keySet()){
            sb.append(node).append(": ").append(getEdgesFrom(node)).append("\n");
        }

        return sb.toString();
    }


}
