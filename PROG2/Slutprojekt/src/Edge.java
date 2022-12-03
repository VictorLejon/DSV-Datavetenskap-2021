// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398

import java.util.Objects;

public class Edge<T> {

    private final String name;
    private int weight;
    private T destination;

    public Edge(String name, int weight, T destination){
        this.name = Objects.requireNonNull(name);
        this.destination = Objects.requireNonNull(destination);

        setWeight(weight);
    }

    public T getDestination(){
        return destination;
    }

    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int newWeight){
        if (newWeight < 0)
            throw new IllegalArgumentException();
        else
            weight = newWeight;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, destination);
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Edge edge)
            return Objects.equals(name, edge.name) && Objects.equals(destination, edge.destination);
        return false;
    }

    @Override
    public String toString(){
        return "to " + destination + " by " + name + " takes " + weight;
    }
}
