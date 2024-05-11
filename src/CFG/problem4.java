package CFG;

import java.util.Scanner;
import java.util.Stack;

public class problem4 {
    public static Stack<Character> deepCopyStack(Stack<Character> original) {
        Stack<Character> copy = new Stack<>();
        for (Character item : original) {
            copy.push((item));
        }
        return copy;
    }


    public static boolean Parse(String st){
        Stack<Character> stk = new Stack<Character>();
        stk.push('s');
        return Parse(st,stk);
    }
    public static boolean Parse(String st, Stack<Character> stk){
//        System.out.print("Stack: " + stk + " ");
//        System.out.println(st);
        //S -> aaaT
        //T -> aaTb | ε
        if(stk.size() == 1 && stk.peek()== 't' && st.isEmpty()){
            return true;
        }
        if(st.isEmpty()  && stk.isEmpty()){
            return true;
        }
        if(st.isEmpty() || stk.empty()){
            return false;
        }
        boolean ans = false;
        if(st.charAt(0) == 'a'){
            if(stk.peek() == 's'){
                stk.pop();
                stk.push('t');
                stk.push('a');
                stk.push('a');
                stk.push('a');
                ans |= Parse(st,deepCopyStack(stk));
            }else if(stk.peek() == 'a'){
                stk.pop();
                ans = Parse(st.substring(1),deepCopyStack(stk));
            }else if(stk.peek() == 't'){
                stk.pop();
                ans = Parse(st,deepCopyStack(stk));
                stk.push('b');
                stk.push('t');
                stk.push('a');
                stk.push('a');
                ans|= Parse(st, deepCopyStack(stk));
            }
        }else if(st.charAt(0) == 'b'){
            if(stk.peek() == 'b'){
                stk.pop();
                ans |= Parse(st.substring(1),deepCopyStack(stk));
            }else if(stk.peek() == 't'){
                stk.pop();
                ans|= Parse(st,stk);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str  = scan.nextLine();
        System.out.println(Parse(str)? "true" : "false");
    }
}
