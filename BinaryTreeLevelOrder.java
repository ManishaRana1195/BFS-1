// Time Complexity : O(N), N nodes in the tree
// Space Complexity : ~O(N), in the worst case, all the nodes in the last level will be in queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach :

// We can keep a pair of value and level in the queue. That would use extra space in the queue. Rather we can maintain
// count of nodes in each level by counting the children at a particular level. We add all the children of a node in the queue,
// we remove them one by one, add them to a temporary list created for that level. Once that level is processed, this temp list
// is added to the result list.
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        // Initialize the queue with root
        queue.add(root);

        while (!queue.isEmpty()){
            int index = 0;
            // Store the queue size as this will be changing once new children are added
            int currentLevelSize = queue.size();
            List<Integer> currentLevelTreeNodes = new ArrayList<>();
            while (index < currentLevelSize){
                TreeNode currentNode = queue.remove();
                // Add all the children of the current node
                getChildren(currentNode, queue);
                currentLevelTreeNodes.add(currentNode.val);
                index++;
            }
            result.add(currentLevelTreeNodes);
        }

        return result;
    }

    private static void getChildren(TreeNode currentNode, Queue<TreeNode> queue) {
        if(currentNode.left != null){
            queue.add(currentNode.left);
        }
        if(currentNode.right != null){
            queue.add(currentNode.right);
        }
    }
}
