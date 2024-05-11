package PDA;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class problem5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String Symbol = scan.nextLine();
        HashMap<Character,Transation> trans = new HashMap<>();
        trans.put('a', new Transation('E','a'));
        trans.put('b', new Transation('a','E'));
        Stack<Character> PDAStack = new Stack<Character>();
        Integer currentState = 0;
        PDAStack.push('$');
        boolean dead = false;
        for(int i = 0;i < Symbol.length(); ++i){
            Character currentPop =  trans.get(Symbol.charAt(i)).Pop;
            Character currentPush = trans.get(Symbol.charAt(i)).Push;
             if(currentPop !='E' && PDAStack.peek() != currentPop){
                dead = true;
                break;
            }
           else if(currentPop != 'E'){ PDAStack.pop(); currentState = 2;}
            if(currentPush != 'E') {PDAStack.push(currentPush); currentState = 1;}
        }
        if(dead){
            System.out.println("not accepted");
            return;
        }
        if(PDAStack.peek() == '$'){
            currentState = 3;
        }
        System.out.println(currentState == 3? "accepted" : "not accepted");
    }
}
