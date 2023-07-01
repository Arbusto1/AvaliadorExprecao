package avaliadorexprecoes;

import java.util.Scanner;

public class AvaliadorExprecoes {

    public static Scanner sc = new Scanner(System.in);
    
    public static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) {
        char pos;
        String exp;
        Lista<Character> lista = new Lista<>();
        PilhaSE p = new PilhaSE();

        while (true) {
            System.out.println("\nDigite 0 para encerrar o programa\nEntre com a expreção: ");
            exp = sc.next();
            sc.nextLine();

            if (exp.equals("0")) {
                break;
            }

            //preenche a lista, cada posição da mesma será um char(simbolo ou numero ou parenteses)
            for (int i = 0; i < exp.length(); i++) {
                lista.add(exp.charAt(i));
            }

            if (verifica(lista)) {
                System.out.println("A expreção é inválida.");
                break;
            }
            

            System.out.println("A expreção infixa: \n" + exp + "\nResulta na expreção pós fixa:");
            conversao(exp);
            System.out.println("\nQue tem como resultado: " + calculadora(p, sb.toString()));

            while (lista.getSize() > 0) {
                lista.excluir();
            }
        }
    }

    public static boolean verifica(Lista lista) {

        char c, ch;

        for (int i = 0; i < lista.getSize() - 1;) {

            //char c sempre será a posição atual da lista e ch sempre será a seguinte
            c = (char) lista.get(i++);
            ch = (char) lista.get(i);

            /*
                CASOS EM QUE O IF RETORNA TRUE(FUNÇÃO INVÁLIDA)
                (+ - Abre parentese seguido por um símbolo (+-*^/)
                +) - Símbolo seguido por fecha parênteses
                12 - Dois números seguidos
                1( - Número seguido por abre parentese(não foi especificado a operação)
                /0 - Divisão por zero
             */
            if ((c == '(') && getSimbol(ch)
                    || (getNum(c) && getNum(ch))
                    || (getSimbol(c) && (ch == ')'))
                    || (getNum(c) && (ch == '('))
                    || divZero(c, ch)) {
                return true;
            }
        }
        return false;
    }

    //Caso o parametro seja um operador a função retorna true
    public static boolean getSimbol(char c) {

        switch (c) {
            case '+':
            case '-':
            case '/':
            case '*':
            case '^':
                return true;

            default:
                return false;
        }
    }

    //Caso o parametro seja um número decimal (0 a 9) retorna true
    public static boolean getNum(char c) {

        switch (c) {

            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;

            default:
                return false;
        }
    }

    public static boolean divZero(char c, char ch) {
        if ((c == '/') && (ch == '0')) {
            return true;
        }
        return false;
    }

    public static Pilha conversao(String expr) {
        Pilha pilhaT = new Pilha();
        Pilha pilhaP = new Pilha();
        Pilha pilhaPrint = new Pilha();
        char auxPrint = 'a';
        char tmp, aux, auxLimp;
        int tamexpr = 0, i = 0, opc = 0;

        while (!pilhaP.isEmpty()) {
            auxLimp = pilhaP.pop();
        }
        while (!pilhaT.isEmpty()) {
            auxLimp = pilhaT.pop();
        }
        while (!pilhaPrint.isEmpty()) {
            auxLimp = pilhaPrint.pop();
        }
        i = 0;
        tamexpr = 0;

        tamexpr = expr.length();
        while (i < tamexpr) {
            aux = expr.charAt(i);
            i = i + 1;
            switch (aux) {
                case '(': {
                    pilhaT.push(aux);
                }
                break;
                case ')': {
                    //	Enquanto naum encontrar "(" transfere os operadores da pilhaT para pilhaP
                    //	Quando encontrar ele para
                    tmp = pilhaT.pop();
                    while (tmp != '(') {
                        aux = pilhaT.pop();
                        if (aux == '(') {
                            pilhaP.push(tmp);
                            break;
                        } else {
                            if (checaPrio(tmp) >= checaPrio(aux)) {
                                pilhaP.push(tmp);
                                tmp = aux;
                            } else {
                                pilhaP.push(aux);
                                tmp = pilhaT.pop();
                            }
                        }
                    }
                }
                break;
                case '+': {
                    if (!(pilhaT.isEmpty())) {
                        tmp = pilhaT.pop();
                        if (checaPrio(tmp) >= checaPrio(aux)) {
                            pilhaP.push(tmp);
                            while (!(pilhaT.isEmpty())) {
                                tmp = pilhaT.pop();
                                if (checaPrio(aux) == checaPrio(tmp)) {
                                    pilhaP.push(tmp);
                                    break;
                                } else {
                                    pilhaT.push(tmp);
                                    break;
                                }
                            }
                            pilhaT.push(aux);
                        } else {
                            pilhaT.push(tmp);
                            pilhaT.push(aux);
                        }
                    } else {
                        pilhaT.push(aux);
                    }
                }
                break;
                case '-': {
                    if (!(pilhaT.isEmpty())) {
                        tmp = pilhaT.pop();
                        if (checaPrio(tmp) >= checaPrio(aux)) {
                            pilhaP.push(tmp);
                            while (!(pilhaT.isEmpty())) {
                                tmp = pilhaT.pop();
                                if (checaPrio(aux) == checaPrio(tmp)) {
                                    pilhaP.push(tmp);
                                    break;
                                } else {
                                    pilhaT.push(tmp);
                                    break;
                                }
                            }
                            pilhaT.push(aux);
                        } else {
                            pilhaT.push(tmp);
                            pilhaT.push(aux);
                        }
                    } else {
                        pilhaT.push(aux);
                    }
                }
                break;
                case '*': {
                    if (!(pilhaT.isEmpty())) {
                        tmp = pilhaT.pop();
                        if (checaPrio(tmp) >= checaPrio(aux)) {
                            pilhaP.push(tmp);
                            while (!(pilhaT.isEmpty())) {
                                tmp = pilhaT.pop();
                                if (checaPrio(aux) == checaPrio(tmp)) {
                                    pilhaP.push(tmp);
                                    break;
                                } else {
                                    pilhaT.push(tmp);
                                    break;
                                }
                            }
                            pilhaT.push(aux);
                        } else {
                            pilhaT.push(tmp);
                            pilhaT.push(aux);
                        }
                    } else {
                        pilhaT.push(aux);
                    }
                }
                break;
                case '/': {
                    if (!(pilhaT.isEmpty())) {
                        tmp = pilhaT.pop();
                        if (checaPrio(tmp) >= checaPrio(aux)) {
                            pilhaP.push(tmp);
                            while (!(pilhaT.isEmpty())) {
                                tmp = pilhaT.pop();
                                if (checaPrio(aux) == checaPrio(tmp)) {
                                    pilhaP.push(tmp);
                                    break;
                                } else {
                                    pilhaT.push(tmp);
                                    break;
                                }
                            }
                            pilhaT.push(aux);
                        } else {
                            pilhaT.push(tmp);
                            pilhaT.push(aux);
                        }
                    } else {
                        pilhaT.push(aux);
                    }
                }

                break;
                case '^': {
                    if (!(pilhaT.isEmpty())) {
                        tmp = pilhaT.pop();
                        if (checaPrio(tmp) >= checaPrio(aux)) {
                            pilhaP.push(tmp);
                            while (!(pilhaT.isEmpty())) {
                                tmp = pilhaT.pop();
                                if (checaPrio(aux) == checaPrio(tmp)) {
                                    pilhaP.push(tmp);
                                    break;
                                } else {
                                    pilhaT.push(tmp);
                                    break;
                                }
                            }
                            pilhaT.push(aux);
                        } else {
                            pilhaT.push(tmp);
                            pilhaT.push(aux);
                        }
                    } else {
                        pilhaT.push(aux);
                    }
                }

                break;
                default: {
                    //	Empilha na pilhaP a letra encontrada na pilhaI
                    pilhaP.push(aux);
                }
                break;
            }
        }
        while (!(pilhaT.isEmpty())) {
            tmp = pilhaT.pop();
            if ((tmp == '(') || (tmp == '0')) {

            } else {
                pilhaP.push(tmp);
            }
        }
        //	Inverte os valores da pilhaP para impressao
        while (!(pilhaP.isEmpty())) {
            pilhaPrint.push(pilhaP.pop());
        }

        while (!(pilhaPrint.isEmpty())) {
            auxPrint = pilhaPrint.pop();
            if (auxPrint == '0') {

            } else {
                System.out.print(auxPrint);
                sb.append(auxPrint);
            }
        }
        return pilhaPrint;

    }

    public static int checaPrio(char caracter) {
        int prioridade = 0;
        if (caracter == '(') {
            prioridade = 1;
        } else {
            if ((caracter == '-') || (caracter == '+')) {
                prioridade = 2;
            } else {
                if ((caracter == '*') || (caracter == '/')) {
                    prioridade = 3;
                } else {
                    if ((caracter == '^')) {
                        prioridade = 4;
                    }
                }
            }
        }
        return (prioridade);
    }

    public static String avaliador(String exp) {

        return null;
    }

    public static int calculadora(PilhaSE p, String s) {
        
        int arg1, arg2;
        char c;
        
        for (int i=0; i<s.length(); i++) {
            c = s.charAt(i);
            if (Character.isDigit(c))
                p.push(Character.digit(c,10));
            else if(c=='+') {
                arg1 = p.top(); p.pop();
                arg2 = p.top(); p.pop();
                p.push(arg1+arg2);
            }
            else if(c=='*') {
                arg1 = p.top(); p.pop();
                arg2 = p.top(); p.pop();
                p.push(arg1*arg2);
            }
            else if(c=='-') {
                arg1 = p.top(); p.pop();
                arg2 = p.top(); p.pop();
                p.push(arg1-arg2);
            }
            else if(c=='/') {
                arg1 = p.top(); p.pop();
                arg2 = p.top(); p.pop();
                p.push(arg1/arg2);
            }
            else if(c=='^') {
                arg1 = p.top(); p.pop();
                arg2 = p.top(); p.pop();
                p.push((int) Math.pow(arg1,arg2));
            }
        }
        
        return p.top();
    }   
}