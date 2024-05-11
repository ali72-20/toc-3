package CFG;

import java.util.Scanner;
import java.util.Stack;

public class problem1 {

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
        // aSaSb
        public static boolean Parse(String st, Stack<Character> stk){
//        System.out.print("Stack: " + stk + " ");
//        System.out.println(st);
            if(stk.size() == 1 && stk.peek()== 's' && st.isEmpty()){
                return true;
            }
            if(st.isEmpty()  && stk.isEmpty()){
                return true;
            }
            if(st.isEmpty() || stk.empty()){
                return false;
            }
            Boolean ans = false;
            if(st.charAt(0) == 'a'){
                if(stk.peek() == 's'){
                    stk.pop();
                    ans = Parse(st,deepCopyStack(stk));
                    stk.push('s');
                    stk.push('b');
                    stk.push('s');
                    stk.push('a');
                    ans |= Parse(st,deepCopyStack(stk));
                }else if(stk.peek() == 'a'){
                    stk.pop();
                    ans = Parse(st.substring(1),deepCopyStack(stk));
                }
                //bSaSa
            }else if(st.charAt(0) == 'b'){
                if(stk.peek() == 's'){
                    stk.pop();
                    ans =  Parse(st,deepCopyStack(stk));
                    stk.push('s');
                    stk.push('b');
                    stk.push('s');
                    stk.push('b');
                    ans |= Parse(st,deepCopyStack(stk));
                }else if(stk.peek() == 'b'){
                    stk.pop();
                    ans |= Parse(st.substring(1),deepCopyStack(stk));
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

