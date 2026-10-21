package co.edu.puj.rpg.modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario<T> {
    private List<T> items = new ArrayList<>();

    public void agregar(T item) {
        items.add(item);
    }

    public T obtener(int index) {
        return items.get(index);
    }

    public T remover(int index) {
        return items.remove(index);
    }

    public int tamano() {
        return items.size();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public List<T> getItems() {
        return items;
    }
}
