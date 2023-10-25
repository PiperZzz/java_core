package base.core.leetcode.string;

public class RotatedString {
    public boolean isRotation(String s1, String s2) {
        // Boundary Condition Check
        if (s1 == null || s2 == null) {
            return false;
        }
        
        if (s1.length() != s2.length()) {
            return false;
        }
        
        String temp = s1 + s1;
        return temp.contains(s2);
    }
}
