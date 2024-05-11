package PDA;

import java.util.Scanner;
import java.util.Stack;

public class problem6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String Symbol = scan.nextLine();
        int currentState = 0;
        Stack<Character> trans = new Stack<>();
        int a_counter = 0;
        trans.push('$');
        currentState = 1;
        boolean dead = false;
        for(int i= 0; i < Symbol.length(); ++i){
            if(Symbol.charAt(i)=='a'){
                if(currentState == 4){
                 dead = true;
                 break;
                }
                a_counter++;
                if(a_counter == 2){
                    trans.push('a');
                    trans.push('a');
                    trans.push('a');
                    currentState = 3;
                    a_counter = 0;
                }
            }else if(Symbol.charAt(i) == 'b'){
                if(currentState == 1){
                    dead = true;
                    break;
                }
                currentState = 4;
                trans.pop();
            }
        }
        if(dead){
            System.out.println("not accepted");
            return;
        }
        if(trans.peek() == '$'){
            currentState = 5;
        }
        System.out.println(currentState == 5 ? "accepted" : "not accepted");
    }
}
