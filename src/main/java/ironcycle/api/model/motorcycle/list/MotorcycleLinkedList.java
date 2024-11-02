package ironcycle.api.model.motorcycle.list;

import ironcycle.api.model.exceptions.EmptyListException;
import ironcycle.api.model.motorcycle.DataDetailsMotorcycle;
import ironcycle.api.model.motorcycle.Motorcycle;

public class MotorcycleLinkedList {
    
    private Node head;

    // Classe interna representando cada nó da lista encadeada
    private class Node {
        Motorcycle motorcycle;
        Node next;

        Node(Motorcycle motorcycle) {
            this.motorcycle = motorcycle;
        }
    }

    // Método para adicionar uma moto ao final da lista
    public void add(Motorcycle motorcycle) {
        Node newNode = new Node(motorcycle);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Método para converter a lista encadeada em um array de DataDetailsMotorcycle
    public DataDetailsMotorcycle[] toArray() {
        // Lança uma exceção se a lista estiver vazia
        if (head == null) {
            throw new EmptyListException("A lista está vazia.");
        }

        int size = getSize();
        DataDetailsMotorcycle[] motorcyclesArray = new DataDetailsMotorcycle[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            motorcyclesArray[index++] = new DataDetailsMotorcycle(current.motorcycle);
            current = current.next;
        }
        return motorcyclesArray;
    }

    // Método auxiliar para obter o tamanho da lista
    private int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}
