# LeetCode Style Coding Problems

**Level 4: Interview Preparation - Practice Problems**

Complete these coding problems to master FAANG interview patterns and algorithms.

---

## Problem 1: Two Sum (LeetCode 1 - EASY)

**Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.**

**Constraints:**
- Each input has exactly one solution
- Same element cannot be used twice
- Answer can be returned in any order

```java
public class TwoSumSolutions {

    // Solution 1: Brute Force - O(n²) time, O(1) space
    public static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // No solution found
    }

    // Solution 2: Hash Table - OPTIMIZED - O(n) time, O(n) space
    public static int[] twoSumHash(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{}; // No solution found
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Brute Force: " + Arrays.toString(twoSumBrute(nums, target)));
        System.out.println("Hash Table: " + Arrays.toString(twoSumHash(nums, target)));
    }
}
```

---

## Problem 2: Valid Parentheses (LeetCode 20 - EASY)

**Given a string s containing just the characters `()`, `{}`, `[]`, determine if the input string is valid.**

**Rules:**
- Open brackets must be closed by the same type
- Open brackets must be closed in the correct order
- Every close bracket has a corresponding open bracket

```java
public class ValidParentheses {

    // SOLUTION: Stack-based approach - O(n) time, O(n) space
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty(); // Stack should be empty if all brackets matched
    }

    public static void main(String[] args) {
        String[] testCases = {
            "()",       // true
            "()[]{}",   // true
            "(]",       // false
            "([)]",     // false
            "{[]}",     // true
            "",         // true
            "(",        // false
            ")",        // false
            "((()))",   // true
        };

        for (String test : testCases) {
            System.out.println("\"" + test + "\" → " + isValid(test));
        }
    }
}
```

---

## Problem 3: Merge Two Sorted Lists (LeetCode 21 - EASY)

**Merge two sorted linked lists and return it as a sorted list.**

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class MergeTwoLists {

    // SOLUTION: Iterative approach - O(n+m) time, O(1) space
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Dummy head node
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Attach remaining nodes
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        return dummy.next;
    }

    // Helper to create linked list from array
    public static ListNode createList(int[] nums) {
        if (nums.length == 0) return null;
        ListNode head = new ListNode(nums[0]);
        ListNode current = head;
        for (int i = 1; i < nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        return head;
    }

    // Helper to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" → ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 7};
        int[] nums2 = {2, 4, 6, 8};

        ListNode l1 = createList(nums1);
        ListNode l2 = createList(nums2);

        System.out.print("List 1: ");
        printList(l1);
        System.out.print("List 2: ");
        printList(l2);

        ListNode merged = mergeTwoLists(l1, l2);
        System.out.print("Merged: ");
        printList(merged);
    }
}
```

---

## Problem 4: Maximum Depth of Binary Tree (LeetCode 104 - EASY)

**Given the root of a binary tree, return its maximum depth.**

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class MaxDepthBinaryTree {

    // SOLUTION 1: Recursive DFS - O(n) time, O(h) space
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // SOLUTION 2: Iterative BFS - O(n) time, O(w) space (w = max width)
    public static int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return depth;
    }

    public static void main(String[] args) {
        // Create a binary tree
        //      3
        //     / \
        //    9   20
        //   /     \
        //  15      7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.left.left = new TreeNode(25);

        System.out.println("Max Depth (DFS): " + maxDepth(root));
        System.out.println("Max Depth (BFS): " + maxDepthBFS(root));
    }
}
```

---

## Problem 5: Same Tree (LeetCode 100 - EASY)

**Given the roots of two binary trees p and q, check if they are the same or not.**

```java
public class SameTreeCheck {

    // SOLUTION: Recursive comparison - O(n) time, O(h) space
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // Both null - same
        if (p == null && q == null) return true;

        // One null - different
        if (p == null || q == null) return false;

        // Values different - different
        if (p.val != q.val) return false;

        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        // Tree 1: [1, 2, 3]
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);

        // Tree 2: [1, 2, 3] - same as tree1
        TreeNode tree2 = new TreeNode(1);
        tree2.left = new TreeNode(2);
        tree2.right = new TreeNode(3);

        // Tree 3: [1, 2, null] - different
        TreeNode tree3 = new TreeNode(1);
        tree3.left = new TreeNode(2);

        System.out.println("Tree1 vs Tree2 (same): " + isSameTree(tree1, tree2));
        System.out.println("Tree1 vs Tree3 (different): " + isSameTree(tree1, tree3));
        System.out.println("Tree1 vs null: " + isSameTree(tree1, null));
    }
}
```

---

## Problem 6: Climbing Stairs (LeetCode 70 - EASY)

**You are climbing a staircase. It takes n steps to reach the top. Each time you can climb 1 or 2 steps. In how many distinct ways can you climb to the top?**

```java
public class ClimbingStairs {

    // SOLUTION 1: Recursive with memoization - O(n) time, O(n) space
    public static int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climbStairsHelper(n, memo);
    }

    private static int climbStairsHelper(int n, int[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (memo[n] != 0) return memo[n];

        memo[n] = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        return memo[n];
    }

    // SOLUTION 2: Iterative DP - O(n) time, O(n) space
    public static int climbStairsDP(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // SOLUTION 3: Optimized iterative - O(n) time, O(1) space
    public static int climbStairsOptimized(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int prev2 = 1; // n-2
        int prev1 = 2; // n-1
        int current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] testCases = {1, 2, 3, 4, 5, 6, 10, 20};

        for (int n : testCases) {
            System.out.println("n = " + n + ":");
            System.out.println("  Recursive: " + climbStairs(n));
            System.out.println("  DP: " + climbStairsDP(n));
            System.out.println("  Optimized: " + climbStairsOptimized(n));
            System.out.println();
        }
    }
}
```

---

## Problem 7: Best Time to Buy and Sell Stock (LeetCode 121 - EASY)

**Given an array prices where prices[i] is the price of a given stock on the ith day, return the maximum profit you can achieve.**

```java
public class BestTimeBuySellStock {

    // SOLUTION: Single pass - O(n) time, O(1) space
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            // Update minimum price if current is lower
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            // Calculate profit if sell today
            int currentProfit = prices[i] - minPrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {7,1,5,3,6,4},    // 5 (6-1)
            {7,6,4,3,1},      // 0 (no profit possible)
            {2,4,1},          // 2 (4-2)
            {3,2,6,5,0,3},    // 4 (6-2)
            {},               // 0 (empty array)
            {1}               // 0 (single price)
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] prices = testCases[i];
            int profit = maxProfit(prices);
            System.out.println("Test " + (i+1) + ": " + Arrays.toString(prices) + " → $" + profit);
        }
    }
}
```

---

## Interview Preparation Tips

### **Common Interview Patterns:**

1. **Hash Table Usage:** Two Sum, Contains Duplicate, Group Anagrams
2. **Two Pointers:** Valid Palindrome, Container With Most Water, Remove Duplicates
3. **Sliding Window:** Longest Substring Without Repeating, Minimum Size Subarray Sum
4. **Stack:** Valid Parentheses, Evaluate Reverse Polish Notation
5. **Linked Lists:** Merge Two Sorted Lists, Remove Nth Node From End
6. **Trees:** Maximum Depth, Same Tree, Invert Binary Tree
7. **Dynamic Programming:** Climbing Stairs, House Robber, Coin Change
8. **Greedy:** Best Time to Buy and Sell Stock, Jump Game

### **Problem-Solving Steps:**

1. **Understand the problem** (5-10 minutes)
   - Read carefully, identify constraints
   - Ask clarifying questions
   - Example walkthrough

2. **Brainstorm approaches** (5-10 minutes)
   - Brute force first (O(n²), O(n³))
   - Look for patterns and optimizations
   - Consider space-time trade-offs

3. **Implement solution** (15-20 minutes)
   - Write clean, readable code
   - Handle edge cases
   - Test mentally as you write

4. **Analyze complexity** (2-3 minutes)
   - Time: O(?)
   - Space: O(?)
   - Explain optimizations

### **Success Metrics:**
- Easy problems: 85-90% solve rate under time pressure
- Recognize patterns and apply appropriate algorithms
- Clean, bug-free implementations
- Good communication of your thought process

**Practice makes perfect! Keep solving problems daily to master these patterns.**
