package avaliadorexprecoes;

class No {

    public int item;
    public No ant;
}

class PilhaSE {

    private No topo;

    public PilhaSE() {
        topo = null;
    }

    public boolean empty() {
        return (topo == null);
    }

    public int top() {
        return topo.item;
    }

    public void pop() {
        if (!empty()) {
            topo = topo.ant;
        }
    }

    public void push(int valor) {
        No novo = new No();
        novo.item = valor;
        novo.ant = topo;
        topo = novo;
    }
}
