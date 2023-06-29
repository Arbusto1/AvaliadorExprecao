package avaliadorexprecoes;

import java.util.Scanner;

public class AvaliadorExprecoes {
    
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        String exp, pos;
        Lista<Character> lista = new Lista<>();
        
        while (true) {
            System.out.println("\nDigite 0 para encerrar o programa\nEntre com a expreção: ");
            exp = sc.next();
            sc.nextLine();
            
            if(exp.equals("0")) break;
            
            //preenche a lista, cada posição da mesma será um char(simbolo ou numero ou parenteses)
            for (int i = 0; i < exp.length(); i++) {
                lista.add(exp.charAt(i));
            }
            
            if (verifica(lista)) {
                System.out.println("A expreção é inválida.");
                break;
            }
            
            pos = avaliador(exp);
            System.out.println("A expreção infixa: " + exp + "\nResulta na expreção pós fixa:"
                    + pos + "\nTendo como resultado da expreção: "
                    + calculadora(pos));
            
            while (lista.getSize() > 0) {
                lista.excluir();
            }
        }
    }
    
    public static boolean verifica(Lista lista) {
        
        char c, ch;
        
        for (int i = 0; i < lista.getSize() -1;) {
            
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
        if((c == '/') && (ch == '0')) return true;
        return false;
    }
    
    public static String avaliador(String exp) {
        //essa função retornará a expreção na forma infixa
        return null;
    }
    
    public static int calculadora(String exp) {
        //essa função retornará o resultado da expressão pósfixa;
        int resu = 0;
        return resu;
    }
}
