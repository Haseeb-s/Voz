package Voz.technique.A4;
import java.util.Collection;

public interface BSTInterface <T> {
    void add(T element);
    String search(T element) throws BSTUnderflowException;
    boolean remove(T element) throws BSTUnderflowException;
    boolean contains(T element) throws BSTUnderflowException;
    int size();
    boolean isEmpty();
    Collection<T> traversal(Order order);
}