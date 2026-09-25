import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);

            if (c == '{') {
                // Insert implicit concatenation if preceding token was a word or closing brace
                if (i > 0 && (Character.isLetter(expression.charAt(i - 1)) || expression.charAt(i - 1) == '}')) {
                    while (!operatorStack.isEmpty() && operatorStack.peek() == '*') {
                        popAndEvaluate(operandStack, operatorStack);
                    }
                    operatorStack.push('*');
                }
                operatorStack.push('{');
            } else if (c == ',') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '{') {
                    popAndEvaluate(operandStack, operatorStack);
                }
                operatorStack.push(',');
            } else if (c == '}') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '{') {
                    popAndEvaluate(operandStack, operatorStack);
                }
                operatorStack.pop(); // Pop '{'
            } else if (Character.isLetter(c)) {
                // Insert implicit concatenation if preceding token was a word or closing brace
                if (i > 0 && (Character.isLetter(expression.charAt(i - 1)) || expression.charAt(i - 1) == '}')) {
                    while (!operatorStack.isEmpty() && operatorStack.peek() == '*') {
                        popAndEvaluate(operandStack, operatorStack);
                    }
                    operatorStack.push('*');
                }

                // Read full lowercase word token
                StringBuilder sb = new StringBuilder();
                while (i < n && Character.isLetter(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                i--; // Adjust loop counter

                Set<String> set = new HashSet<>();
                set.add(sb.toString());
                operandStack.push(set);
            }
        }

        while (!operatorStack.isEmpty()) {
            popAndEvaluate(operandStack, operatorStack);
        }

        // Return sorted list of distinct strings
        List<String> result = new ArrayList<>(operandStack.pop());
        Collections.sort(result);
        return result;
    }

    private void popAndEvaluate(Stack<Set<String>> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        Set<String> set2 = operandStack.pop();
        Set<String> set1 = operandStack.pop();

        Set<String> resultSet = new HashSet<>();

        if (op == '*') { // Concatenation / Cartesian Product
            for (String s1 : set1) {
                for (String s2 : set2) {
                    resultSet.add(s1 + s2);
                }
            }
        } else if (op == ',') { // Set Union
            resultSet.addAll(set1);
            resultSet.addAll(set2);
        }

        operandStack.push(resultSet);
    }
}