package PDA;

import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class problem8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String Symbol = scan.nextLine();
        HashMap<Character,Transation> trans = new HashMap<>();
        trans.put('a', new Transation('E','a'));
        trans.put('b',new Transation('a','E'));
        trans.put('c', new Transation('a','E'));
        Stack<Character> PDAStack = new Stack<>();
        int currentState = 0;
        boolean dead = false;
        PDAStack.push('$');
        currentState = 1;
        for(int i = 0;i < Symbol.length(); ++i){
            Character currentPop = (trans.get(Symbol.charAt(i))).Pop;
            Character currentPush = (trans.get(Symbol.charAt(i))).Push;
            if(currentState > 1 && Symbol.charAt(i) == 'a'){
                dead = true;
                break;
            }
            if(currentPush != 'E'){
                PDAStack.push(currentPush);
                currentState = 1;
            }
            if(currentPop != 'E' && currentPop == PDAStack.peek()){
                PDAStack.pop();
                if(Symbol.charAt(i) == 'b') currentState = 2;
                else if(Symbol.charAt(i) == 'c') currentState = 3;
            }else if(currentPop != 'E' && currentPop != PDAStack.peek()){
                dead = true;
                break;
            }
        }
        if(dead){
            System.out.println("not accepted");
            return;
        }
        if(PDAStack.peek() == '$'){
            currentState = 4;
            PDAStack.pop();
        }
        System.out.println(currentState == 4 ? "accepted" : "not accepted");
    }
}
