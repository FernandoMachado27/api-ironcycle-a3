package ironcycle.api.model.users.list;

import java.util.EmptyStackException;

import ironcycle.api.model.users.DataDetailsUser;
import ironcycle.api.model.users.User;

public class UserStack {
    private Node top; // Topo da pilha
    private int size;

    private static class Node {
        User data;
        Node next;

        Node(User data) {
            this.data = data;
            this.next = null;
        }
    }

    public UserStack() {
        this.top = null;
        this.size = 0;
    }

    // Adiciona um usuário no topo da pilha
    public void push(User user) {
        Node newNode = new Node(user);
        newNode.next = top; // O novo nó aponta para o atual topo
        top = newNode;      // Atualiza o topo
        size++;
    }

    // Remove e retorna o elemento do topo
    public User pop() {
        if (isEmpty()) throw new EmptyStackException();
        User data = top.data;
        top = top.next; // Move o topo para o próximo nó
        size--;
        return data;
    }

    // Verifica o topo sem remover
    public User peek() {
        if (isEmpty()) throw new EmptyStackException();
        return top.data;
    }

    // Converte a pilha em um array
    public DataDetailsUser[] toArray() {
        DataDetailsUser[] array = new DataDetailsUser[size];
        Node current = top;
        int index = 0;
        while (current != null) {
            array[index++] = new DataDetailsUser(current.data);
            current = current.next;
        }
        return array;
    }

    // Verifica se a pilha está vazia
    public boolean isEmpty() {
        return top == null;
    }
}
