public class BalanceTree {
    
    public boolean isBalanced (TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = TreeHeight(root.left);
        int rightHeight = TreeHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) <= 1) {
           return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int TreeHeight(TreeNode treeNode) {
        int leftHeight = 0;
        int rightHeight = 0;

        if (treeNode == null) {
            return 0;
        }

        if (treeNode.left != null) {
            leftHeight = 1 + TreeHeight(treeNode.left);
        } else if (treeNode.right != null) {
            rightHeight = 1 + TreeHeight(treeNode.right);
        }

        return Math.max(leftHeight, rightHeight);
    }
}
