package base.core.leetcode.string;

import java.util.Stack;

public class ClosedBrackets {
    public boolean isValid(String str) {
    // 创建一个栈来存储左括号
    Stack<Character> stack = new Stack<>();
    
    // 遍历字符串的每个字符
    for (char c : str.toCharArray()) {
        // 如果是左括号，将其入栈
        if (c == '(' || c == '[' || c == '{') {
            // stac的push方法用于将元素入栈
            stack.push(c);
        } else {
            // 如果是右括号，检查栈是否为空
            // 如果为空，说明必然没有与之匹配的左括号，直接返回false
            if (stack.isEmpty()) {
                return false;
            }
            
            // 弹出栈顶的左括号
            // stack的pop方法用于将栈顶元素弹出,并返回栈顶元素,也就是最后一个入栈的元素
            char top = stack.pop();
            
            // 检查右括号是否与栈顶的左括号匹配
            if (c == ')' && top != '(') {
                return false;
            } else if (c == ']' && top != '[') {
                return false;
            } else if (c == '}' && top != '{') {
                return false;
            }
        }
        // 思路就是遇到左括号就入栈，遇到右括号就立即弹出栈顶的左括号并进行匹配
        // 核心方法是栈的push和pop
        // 最后空栈说明每一个右括号必然有一个位于栈顶的左括号与之匹配
    }
    
    // 如果栈不为空，说明还有未闭合的左括号
    return stack.isEmpty();
}
}
