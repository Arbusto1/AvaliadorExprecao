package avaliadorexprecoes;

public class Lista<T> {

    class Celula {

        T item;
        Celula prox;
        Celula ant;

        public Celula(T item) {
            this.item = item;
        }
    }

    private Celula head;
    private Celula tail;
    private long size;

    public Lista() {
        head = new Celula(null);
        tail = new Celula(null);
        head.prox = tail;
        tail.ant = head;
        size = 0;
    }
    
    //add inicio
    void addi(T item) {
        Celula novo = new Celula(item);
        novo.ant = head;
        novo.prox = head.prox;
        head.prox = novo;
        novo.prox.ant = novo;
        size++;

    }

    //add fim
    void add(T item) {
        Celula novo = new Celula(item);
        novo.ant = tail.ant;
        novo.prox = tail;
        tail.ant = novo;
        novo.ant.prox = novo;
        size++;

    }

    public T excluir(T item) {
        if (head.prox == tail) {
            throw new IllegalArgumentException("Lista Vazia");
        } else {
            Celula aux = head.prox;
            while (aux != tail && !aux.item.equals(item)) {
                aux = aux.prox;
            }
            if (aux == tail) {
                throw new IllegalArgumentException("item buscado nao existe");
            } else {
                aux.ant.prox = aux.prox;
                aux.prox.ant = aux.ant;
                size--;
                return aux.item;
            }
        }

    }
    
    //exclui no final
    public T excluir() {
        if (head.prox == tail) throw new IllegalArgumentException("Lista vazia");
        
        Celula aux = tail.ant;
        tail.ant.ant.prox = tail;
        tail.ant = tail.ant.ant;
        size--;
        return aux.item;
    }

    public T get(long i) {
        if (head == tail) {
            throw new IllegalArgumentException("A lista está vazia");
        } else {
            Celula aux = head.prox;
            long j = 0;

            while (aux != null && j < i) {
                aux = aux.prox;
                j++;
            }

            if (aux == tail) {
                throw new IllegalArgumentException("Indice inválido, favor "
                        + "inserir índice dentro do tamanho da lista(" + size
                        + ")");
            } else {
                return aux.item;
            }
        }
    }

    public long getSize() {
        return this.size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Celula aux = head.prox;

        while (aux != tail) {
            sb.append(aux.item);
            aux = aux.prox;
        }
        return sb.toString();

    }
}
