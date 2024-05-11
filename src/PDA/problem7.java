package PDA;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class problem7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String Symbol = scan.nextLine();
        int curState = 0;
        Stack<Character> PDAStack = new Stack<Character>();
        boolean dead = false;
        HashMap<Character, Transation> trans = new HashMap<>();
        trans.put('{',new Transation('E','{'));
        trans.put('}', new Transation('{','E'));
        PDAStack.push('$');
        curState = 1;
        for(int i = 0;i < Symbol.length(); ++i){
            if(Symbol.charAt(i) == ' ') continue;
            Character currentPop = trans.get(Symbol.charAt(i)).Pop;
            Character currentPush = trans.get(Symbol.charAt(i)).Push;
            if(currentPush != 'E'){
                curState =1;
                PDAStack.push(currentPush);
            }else if(currentPop != 'E' && PDAStack.peek() == currentPop){
                 PDAStack.pop();
                 curState = 2;
            }else {
                dead = true;
                break;
            }
        }
        if(dead){
            System.out.println("not accepted");
            return;
        }
        if(PDAStack.peek() == '$') curState = 3;
        System.out.println(curState == 3 || curState == 1 ? "accepted" : "not accepted");
    }
}
