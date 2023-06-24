package avaliadorexprecoes;

import java.util.Scanner;

public class AvaliadorExprecoes {
    
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        String exp;
        
        while (true) {
            System.out.println("\nDigite 0 para encerrar o programa\nEntre com a expreção: ");
            exp = sc.next();
            sc.nextLine();
            
            if(exp.equals("0")) break;
            
            calculadora(avaliador(exp));
        }
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
