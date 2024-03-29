package avaliadorexprecoes;

public class Pilha {

    public char[] vet = new char[30];
    public int topo = 0;

    public boolean push(char dado) {
        boolean res = false;

        if (!isFull()) {
            vet[topo] = dado;
            topo++;
            res = true;
        }
        return (res);
    }
    
    public char get(int i) {
        if (isEmpty() && i > 0 && i <= topo) throw new IllegalArgumentException("Uma das entradas está inválida");
        return vet[topo - i];
    }

    public char pop() {
        char res = '0';

        if (!isEmpty()) {
            topo--;
            res = vet[topo];
        }
        return (res);
    }

    public boolean isFull() {
        boolean res = (topo == vet.length) ? true : false;
        return (res);
    }

    public boolean isEmpty() {
        boolean res = (topo == 0) ? true : false;
        return (res);
    }
}
