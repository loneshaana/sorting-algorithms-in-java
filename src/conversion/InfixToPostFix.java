package conversion;

import java.util.Stack;

class InfixToPostFix{
    private static int Prec(char ch){
        switch (ch){
            case '+':
            case  '-':
                return 1;
            case '/':
            case '*':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    private static boolean isOperand(char c){
        return (c >= 'a' && c<= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static String postFixToInfix(String exp){
        Stack<String> stack = new Stack<>();

        for(int i=0;i<exp.length();i++){
            char c = exp.charAt(i);
            if(isOperand(c)){
                stack.push(c+" ");
            }
            else{
                // operator encountered
                if(!stack.isEmpty()){
                    String c1 = stack.peek();
                    stack.pop();
                    String c2 = stack.peek();

                    stack.pop();
                    stack.push("("+c2+c+c1+")");
                }
            }
        }
        return stack.peek();
    }

    private static String infixToPostFix(String exp){
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<exp.length();i++){
            char c = exp.charAt(i);

            if(Character.isLetterOrDigit(c)){
//                result.append(c);
                stack.push(c);
            }

            else if(c == '('){
                stack.push(c);
            }

            else if(c == ')'){
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                if(!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression";
                else
                    stack.pop();
            }else{
                // an operand is encountered
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()){
            result.append(stack.pop());
        }

        return result.toString();
    }
    public static void main(String[] args) {
        String exp = "a+b*(c^d-e)^(f+g*h)-i"; // its postfix -> abcd^e-fgh*+^*+i-
        String exp1 = "((a +(b *(((c ^d )-e )^(f +(g * h )))))-i )";
        System.out.println(infixToPostFix(exp1));
//        System.out.println(postFixToInfix("abcd^e-fgh*+^*+i-"));
    }
}