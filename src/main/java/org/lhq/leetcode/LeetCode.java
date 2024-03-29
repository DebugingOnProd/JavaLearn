
package org.lhq.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.lhq.leetcode.struc.ListNode;
import org.lhq.leetcode.struc.Node;
import org.lhq.leetcode.struc.TreeNode;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: org.lhq.leetcode
 * @description:
 * @author: LittleCuteWangDF
 * @create: 2021-07-30 14:01
 */
@Slf4j
public class LeetCode {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        while (l1 != null || l2 != null) {
            pre.next = new ListNode(l1.val + l2.val);
            l1 = l1.next;
            l2 = l2.next;
            pre = pre.next;
        }
        return pre.next;
    }


    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            } else {
                head = head.next;
            }
        }
        return null;
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = 0;
        int n = matrix[0].length - 1;
        while (m < matrix.length && n >= 0) {
            if (matrix[m][n] == target) {
                return true;
            } else if (matrix[m][n] > target) {
                n--;
            } else {
                m++;
            }
        }
        return false;
    }


    public void rotate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < (row) / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[row - 1 - i];
            matrix[row - 1 - i] = temp;
        }
        for (int i = 0; i < row; i++) {
            for (int j = i; j < col; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < rowIndex; i++) {
            list.add(0);
            for (int j = list.size() - 1; j > 0; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }
        return list;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int p1 = 0;
        int p2 = 0;
        int cur = 2;
        res[0][0] = 1;
        while (cur <= n * n) {
            while (p2 < n - 1 && res[p1][p2 + 1] == 0) {
                res[p1][++p2] = cur;
                cur++;
            }
            while (p1 < n - 1 && res[p1 + 1][p2] == 0) {
                res[++p1][p2] = cur;
                cur++;
            }
            while (p2 > 0 && res[p1][p2 - 1] == 0) {
                res[p1][--p2] = cur;
                cur++;
            }
            while (p1 > 0 && res[p1 - 1][p2] == 0) {
                res[--p1][p2] = cur;
                cur++;
            }

        }
        return res;
    }


    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length - 1; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (result.isEmpty() || result.get(result.size() - 1)[1] < left) {
                result.add(new int[]{left, right});
            } else {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], right);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                p++;
            }
        }
        for (int i = p; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                p++;
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int right = nums.length - 1;
        int left = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (right > left) {
                int sum = nums[right] + nums[left] + nums[i];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                }

            }
        }
        return result;
    }


    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }


    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        Map.Entry<Integer, Integer> max = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (max == null) {
                max = entry;
            } else if (entry.getValue() > max.getValue()) {
                max = entry;
            }
        }
        return max.getKey();
    }

    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if (leftSum == sum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        ter(root, list);
        return list;
    }

    private void ter(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (Node child : root.children) {
            ter(child, list);
        }
    }


    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX = coordinates[0][0];
        int deltaY = coordinates[0][1];
        int length = coordinates.length;
        for (int i = 0; i < length; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int a = coordinates[1][1];
        int b = coordinates[1][0];
        for (int i = 2; i < length; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            if (a * x != b * y) {
                return false;
            }
        }
        return true;

    }

    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode.left != null) {
                if (isLeaf(treeNode.left)) {
                    sum += treeNode.left.val;
                } else {
                    queue.offer(treeNode.left);
                }
            }
            if (treeNode.right != null) {
                if (!isLeaf(treeNode.right)) {
                    queue.offer(treeNode.right);
                }
            }

        }
        return sum;
    }

    public boolean isLeaf(TreeNode treeNode) {
        return treeNode.right == null && treeNode.left == null;
    }

    public int getDecimalValue(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int result = 0;
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            int item = list.get(i);
            double v = item * Math.pow(2, i + 1);
            result += v;
        }
        return result;
    }


    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        list.sort(((o1, o2) -> {
            int bitCountO1 = Integer.bitCount(o1);
            int bitCountO2 = Integer.bitCount(o2);
            if (bitCountO2 == bitCountO1) {
                return o1 - o2;
            } else {
                return bitCountO1 - bitCountO2;
            }
        }));
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;

    }


    public char findTheDifference2(String s, String t) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : chars) {
            Integer count = charMap.getOrDefault(c, 0);
            charMap.put(c, ++count);
        }
        for (char c : t.toCharArray()) {
            Integer count = charMap.get(c);
            if (count <= 0) {
                charMap.remove(c);
            }
            charMap.put(c, --count);
            if (!charMap.containsKey(c)) {
                return c;
            }
        }
        return ' ';
    }


    public String interpret(String command) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("G", "G");
        hashMap.put("()", "o");
        hashMap.put("(al)", "al");
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            command = command.replace(entry.getKey(), entry.getValue());
        }
        return command;
    }

    public String mergeAlternately(String word1, String word2) {
        int length = word1.length();
        int length1 = word2.length();
        int index = 0;
        char[] chars = new char[length1 + length];
        for (int i = 0; i < length1 || i < length; ++i) {
            if (i < length) {
                chars[index++] = word1.charAt(i);
            }
            if (i < length1) {
                chars[index++] = word2.charAt(i);
            }
        }
        return new String(chars);

    }


    public int diagonalSum(int[][] mat) {
        int x = mat.length;
        int sum = 0;
        int mid = x / 2;
        for (int i = 0; i < x; ++i) {
            sum += mat[i][i] + mat[i][x - i - 1];
        }
        return sum - mat[mid][mid] * (mid & 2);

    }

    public int maximumWealth(int[][] accounts) {
        int sum = 0;
        for (int[] account : accounts) {
            sum = Math.max(sum, Arrays.stream(account).sum());
        }
        return sum;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int start = 0; start < n; start++) {
            for (int length = 1; start + length <= n; length += 2) {
                int end = start + length - 1;
                for (int i = start; i <= end; i++) {
                    sum += arr[i];
                }
            }
        }
        return sum;

    }


    public boolean areAlmostEqual(String s1, String s2) {

        if (s1.length() < 3) {
            if (!s1.equals(s2)) {
                return false;
            }
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] s1chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();
        int count = 0;
        int s1char = -1, s2char = -1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s1chars.length; i++) {
            set.add(s1chars[i]);
            if (s1chars[i] != s2chars[i]) {
                s1char = s1chars[i];
                s2char = s2chars[i];
                if (s2char == -1) {
                    s2char = i;
                }
                if (s1char == -1 && s2char != i) {
                    s1char = i;
                }
                count++;
            }
        }
        for (char c : s2chars) {
            if (!set.contains(c)) {
                return false;
            }
        }
        if (count == 2 && s1.charAt(s1char) == s2.charAt(s2char) && s1.charAt(s2char) == s2.charAt(s1char)) {
            return true;
        }
        return count == 0;
    }

    public int nearestValidPoint(int x, int y, int[][] points) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int absX = Math.abs(points[i][0] - x);
                int absY = Math.abs(points[i][1] - y);
                int des = absX + absY;
                if (des < min) {
                    min = des;
                    index = i;
                }
            }
        }
        return index;
    }

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; --i) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }


    public double average(int[] salary) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : salary) {
            sum += i;
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        return (sum - (min + max)) / (double) (salary.length - 2);
    }

    public int countOdds(int low, int high) {
        int cur = low;
        int count = 0;
        while (cur <= high) {
            if (cur % 2 != 0) {
                cur += 2;
                count++;
            } else {
                cur++;
            }
        }
        return count;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        List<TreeNode> pathP = getPath(root, p);
        List<TreeNode> pathQ = getPath(root, q);
        TreeNode result = null;
        for (int i = 0; i < pathP.size() && i < pathQ.size(); ++i) {
            if (pathP.get(i) == pathQ.get(i)) {
                result = pathP.get(i);
            } else {
                break;
            }
        }
        return result;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode p) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode node = root;
        while (node != p) {
            list.add(node);
            if (node.val < p.val) {
                node = node.left;
            }
            if (node.val > p.val) {
                node = node.right;
            }
        }
        list.add(node);
        return list;
    }


    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (set.contains(k - treeNode.val)) {
                return true;
            }
            set.add(treeNode.val);
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }
        return false;
    }


    public boolean isValidBST(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        Integer curValue = null;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (curValue == null) {
                    curValue = pop.val;
                } else {
                    if (curValue > pop.val) {
                        return false;
                    }
                }
                cur = pop.right;
            }
        }
        return true;

    }


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        while (cur != null) {
            // 如果值小于当前节点的值,就说明值应该放在左子书中
            if (val < cur.val) {
                // 如果当前节点发左子节点为空就说明当前节点为叶子节点,改节点
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                } else {
                    cur = cur.left;
                }
                // 否则就存在与右子树中
            } else {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }
        return root;

    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            if (treeNode.val == val) {
                return treeNode;
            }
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
        }
        return root;
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        queue.offer(root);
        valQueue.offer(root.val);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            int treeValue = valQueue.poll();
            if (treeNode.left == null && treeNode.right == null) {
                if (treeValue == targetSum) {
                    return true;
                }
                continue;
            }

            if (treeNode.left != null) {
                queue.offer(treeNode.left);
                valQueue.offer(treeValue + treeNode.left.val);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
                valQueue.offer(treeValue + treeNode.right.val);
            }
        }
        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            TreeNode left = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = left;
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
        }
        return root;
    }


    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode root, TreeNode root1) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root1);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            TreeNode t2 = queue.poll();
            if (t == null && t2 == null) {
                continue;
            }
            if ((t == null || t2 == null) || t.val != t2.val) {
                return false;
            }
            queue.offer(t.right);
            queue.offer(t2.left);

            queue.offer(t.left);
            queue.offer(t2.right);
        }
        return true;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> teamList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                teamList.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            list.add(teamList);

        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        // 如果 当前节点为空 或者 栈不为空
        while (root != null || !stack.isEmpty()) {
            // 当前节点不为空
            while (root != null) {
                // 栈推入当前节点
                stack.push(root);
                // 当前节点等于 左子节点
                root = root.left;
            }
            // 栈一直推入到左子节点为空
            // 栈推出
            root = stack.pop();
            // 如果 当前节点 的右子节点为空  或者右子节点等于前驱节点
            // 如果右子节点为空，或者右子节点等于前驱节点 说明没值或者刚刚遍历过
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                // 前驱节点等于 当前节点
                prev = root;
                // 当前节点置空
                root = null;
            } else {
                // 如果当前节点右子节点不为空或者 当前节点的右子节点 等于前驱节点
                // 将刚刚推出的节点又放回去
                // 然后将当前节点指向右子节点
                stack.push(root);
                // 当前节点 等于 右子节点
                root = root.right;
            }
        }
        return list;
    }


    public List<Integer> inorderTraversal2(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> inOrder = new ArrayList<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                inOrder.add(pop.val);
                cur = pop.right;
            }
        }
        return inOrder;
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> preList = new ArrayList<>();
        stack.addFirst(root);
        if (!stack.isEmpty()) {
            TreeNode treeNode = stack.removeFirst();
            preList.add(treeNode.val);
            if (treeNode.right != null) {
                stack.addFirst(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.addFirst(treeNode.left);
            }

        }
        return preList;
    }

    public boolean isValid2(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (stack.isEmpty() || !stack.peek().equals(map.get(c))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            // 当前节点的下一节点
            ListNode next = cur.next;
            // 吧当前节点的下一节点指向前一个节点
            cur.next = pre;
            // 前一个节点为当前节点
            pre = cur;
            // 指针往下一个节点移动
            cur = next;
        }
        return head;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode perHead = new ListNode(-1);
        perHead.next = head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }

        }
        return perHead.next;
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode temp = new ListNode(-1);
        ListNode cur = temp;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        cur.next = p1 == null ? p2 : p1;
        return temp.next;
    }

    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer count = charMap.getOrDefault(c, 0);
            charMap.put(c, count + 1);
        }
        for (char c : t.toCharArray()) {
            charMap.computeIfPresent(c, (key, value) -> value - 1);
            if (!charMap.containsKey(c)) {
                return false;
            }
            if (charMap.containsKey(c) && charMap.get(c).equals(0)) {
                charMap.remove(c);
            }
        }
        return charMap.isEmpty();
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        char[] chars = magazine.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : chars) {
            Integer count = charMap.getOrDefault(c, 0);
            charMap.put(c, count + 1);
        }
        char[] array = ransomNote.toCharArray();
        for (char c : array) {
            charMap.computeIfPresent(c, (key, value) -> value - 1);
            if (!charMap.containsKey(c)) {
                return false;
            }
            if (charMap.containsKey(c) && charMap.get(c).equals(0)) {
                charMap.remove(c);
            }
        }
        return true;
    }


    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : chars) {
            Integer count = charMap.getOrDefault(c, 0);
            charMap.put(c, count + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (charMap.get(chars[i]).equals(1)) {
                return i;
            }
        }
        return -1;
    }

    public void setZeroes(int[][] matrix) {
        int width = matrix.length;
        int high = matrix[0].length;
        Set<Integer> colSet = new HashSet<>();
        Set<Integer> rowSet = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < high; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }

            }
        }
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                if (colSet.contains(i)) {
                    row[i] = 0;
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < high; j++) {
                if (rowSet.contains(i)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    public boolean isValidSudoku(char[][] board) {
        int width = board.length;
        int high = board[0].length;
        Set<Character> charSet = new HashSet<>();
        int pHigh = 0;
        int pWidth = 0;
        while (pWidth < width) {
            if (board[pWidth][pHigh] != '.' && !charSet.add(board[pWidth][pHigh])) {
                return false;
            }
            if ((pHigh + 1) % 3 == 0 && (pWidth + 1) % 3 == 0) {
                charSet = new HashSet<>();
            }
            if ((pWidth + 1) % 3 == 0) {
                pHigh++;
                pWidth -= 3;
            }
            if (pHigh == high) {
                pWidth += 3;
                pHigh = 0;
            }
            pWidth++;
        }
        for (char[] chars : board) {
            Set<Character> hashSet = new HashSet<>();
            for (char c : chars) {
                if (c != '.' && !hashSet.add(c)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < high; i++) {
            Set<Character> hashSet = new HashSet<>();
            for (char[] chars : board) {
                if (chars[i] != '.' && !hashSet.add(chars[i])) {
                    return false;
                }
            }
        }
        return true;

    }


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            // 每一行的开头都为1
            list.add(1);

            for (int j = 1; j < i; j++) {
                //获取上一行 的数组
                List<Integer> sub = result.get(i - 1);
                Integer cur = sub.get(j);
                Integer pre = sub.get(j - 1);
                list.add(cur + pre);
            }
            if (i != 0) {
                // 给结尾 加上 1
                list.add(1);
            }
            result.add(list);
        }
        return result;
    }


    public int[][] matrixReshape(int[][] mat, int r, int c) {
        //行数
        int m = mat.length;
        // 列数
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        } else {
            int[][] resultMartix = new int[r][c];
            int newWidth = 0;
            int oldWidth = 0;
            int oldHigh = 0;
            int newHigh = 0;
            while (newHigh < r) {
                resultMartix[newHigh][newWidth++] = mat[oldHigh][oldWidth++];
                if (newWidth == c) {
                    newHigh++;
                    newWidth = 0;
                }
                if (oldWidth > n - 1) {
                    oldHigh++;
                    oldWidth = 0;
                }
            }
            return resultMartix;
        }

    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int price : prices) {
            //记录当前最大的差价,
            max = Math.max(max, price - min);
            // 记录当前股票最小价格
            min = Math.min(min, price);
        }
        return max;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return new int[0];

    }


    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }
        return false;
    }


    public int maxSubArray(int[] nums) {
        int maxSub = nums[0];
        int pre = 0;
        for (int num : nums) {
            //当前和
            pre = Math.max(pre + maxSub, num);
            maxSub = Math.max(maxSub, pre);
        }
        return maxSub;
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    public boolean isPalindrome(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        while (head != null) {
            if (stack.pop() != head.val) {
                return false;
            } else {
                head = head.next;
            }
        }
        return true;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }


    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }


    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums2) {
            map.putIfAbsent(i, 1);
            map.computeIfPresent(i, (key, value) -> value++);
        }

        int[] intersect = new int[nums2.length];
        int index = 0;
        for (int i : nums1) {
            Integer integer = map.getOrDefault(i, 0);
            if (integer > 0) {
                intersect[index++] = integer;
                integer--;
                if (integer > 0) {
                    map.put(i, integer);
                } else {
                    map.remove(i);
                }
            }
        }
        return Arrays.copyOfRange(intersect, 0, index);

    }


    public boolean checkIfExist(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(i * 2) || (i % 2 != 0 && set.contains(i / 2))) {
                return true;
            }
            set.add(i);
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = searchRow(matrix, target);
        if (row < 0) {
            return false;
        }
        return searchCol(matrix[row], target);
    }

    public boolean searchCol(int[] matrix, int target) {
        int start = 0;
        int end = matrix.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid] == target) {
                return true;
            } else if (matrix[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    public int searchRow(int[][] matrix, int target) {
        int start = -1;
        int end = matrix.length - 1;
        while (start < end) {
            int mid = start + 1 + (end - start) / 2;
            if (matrix[mid][0] <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    public int countNegatives(int[][] grid) {
        AtomicLong sum = new AtomicLong();
        Arrays.asList(grid).forEach(ints -> {
            long count = Arrays.stream(ints).filter(item -> item < 0).count();
            sum.addAndGet(count);
        });
        long l = sum.get();
        return Math.toIntExact(l);
    }


    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = findStart(nums, target);
        int end = findEnd(nums, target);
        return new int[]{start, end};

    }

    public int findStart(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] == target) {
                end = mid;
            } else {
                end = mid - 1;
            }
        }
        if (nums[start] == target) return start;
        return -1;
    }

    public int findEnd(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] == target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] == target) return start;
        return -1;
    }


    public char nextGreatestLetter(char[] letters, char target) {
        int end = letters.length - 1;
        if (target >= letters[end]) {
            return letters[0];
        }
        int start = 0;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return letters[start];
    }


    public int mySqrt(int x) {
        int start = 0, end = x;
        int result = 0;

        while (start < end) {
            int mid = start + (end - start) / 2;
            int squareRoot = x / mid;
            long square = (long) mid * mid;
            if (square <= x) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }


    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return 0;
    }


    public int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (arr[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


    public boolean isPerfectSquare(int num) {
        int start = 0, end = num;
        while (start < end) {
            int mid = start + (end - start) / 2;
            long square = mid * mid;
            if (square == num) {
                return true;
            } else if (square < num) {
                start = mid + 1;
            } else if (square > num) {
                end = mid - 1;
            } else {
                return false;
            }
        }
        return false;
    }


    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] ans = new int[nums.length];
        int pos = nums.length - 1;
        while (left <= right) {
            nums[left] = nums[left] * nums[left];
            nums[right] = nums[right] * nums[right];
            if (nums[left] >= nums[right]) {
                ans[pos] = nums[left];
                left++;
            } else {
                ans[pos] = nums[right];
                right--;
            }
            pos--;

        }
        return ans;
    }


    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                set2.add(i);
            }
        }
        return set2.stream().mapToInt(i -> i).toArray();

    }


    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> char2Str = new HashMap<>();
        Map<String, Character> str2Char = new HashMap<>();
        int m = s.length();
        int p = 0;
        for (int i = 0; i < pattern.length(); ++i) {
            char ch = pattern.charAt(i);
            if (p >= m) {
                return false;
            }
            int j = p;
            while (j < m && s.charAt(j) != ' ') {
                j++;
            }
            String subString = s.substring(p, j);
            if (str2Char.containsKey(subString) && str2Char.get(subString) != ch) {
                return false;
            }
            if (char2Str.containsKey(ch) && !char2Str.get(ch).equals(subString)) {
                return false;
            }
            str2Char.put(subString, ch);
            char2Str.put(ch, subString);
            p = j + 1;
        }
        return p >= m;

    }


    public int missingNumber(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int miss = 0;
        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) {
                miss = i;
                break;
            }
        }
        return miss;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            boolean key = map.containsKey(value);
            if (key && Math.abs(i - map.get(value)) <= k) {
                return true;
            }
            map.put(value, i);
        }
        return false;
    }


    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> integerMap = new HashMap<>();
        Arrays.stream(arr).forEach(item -> integerMap.put(item, integerMap.getOrDefault(item, 0) + 1));
        HashSet<Integer> hashSet = new HashSet<>();
        integerMap.forEach((key, value) -> hashSet.add(value));
        return hashSet.size() == integerMap.size();
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        ListNode superSlow = null;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            superSlow = slow;
            fast = fast.next;
            slow = slow.next;
        }
        superSlow = superSlow.next.next;
        return head;

    }

    public int[] twoSum(int[] numbers, int target) {


        int lenght = numbers.length;
        int left = 0;
        int right = lenght - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }


    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }


    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        while (left < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }

    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(strings).forEach(item -> {
            char[] chars = item.toCharArray();
            int length = chars.length;
            int left = 0;
            int right = length - 1;
            while (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
            stringBuffer.append(chars.toString()).append(" ");
        });
        stringBuffer.deleteCharAt(stringBuffer.length());
        return stringBuffer.toString();
    }


    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return right;
    }


    public char findTheDifference(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        Set<Character> set = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (char c : chars) {
            set.add(c);
        }
        for (char c : chars1) {
            set.remove(c);
        }
        return '2';

    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        HashSet<Integer> integers = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            integers.add(i);
        }
        for (int num : nums) {
            integers.remove(num);
        }
        return new ArrayList<>(integers);
    }

    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            count += map.getOrDefault(num, 0);
            map.put(num + k, map.get(num + k) + 1);
            map.put(num - k, map.get(num - k) + 1);
        }
        return count;
    }

    public int numJewelsInStones(String jewels, String stones) {
        char[] jeweksChar = jewels.toCharArray();
        char[] stonesChar = stones.toCharArray();
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (char c : jeweksChar) {
            set.add(c);
        }
        for (char c : stonesChar) {
            if (set.contains(c)) {
                count++;
            }
        }
        return count;
    }


    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Character character = map.putIfAbsent(chars[i], chars1[i]);
            log.info("{}...{}:{}:{}", chars[i], chars1[i], map.get(chars[i]), chars1[i] != map.get(chars[i]));
            log.info("{}", character);
            if (character != null && character != chars1[i]) {
                return false;
            }
        }


        return true;

    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;

    }


    public boolean hasCycle(ListNode head) {
        Set<Integer> set = new HashSet<>();
        while (head.next != null) {
            if (set.contains(head.hashCode())) {
                return true;
            }
            set.add(head.hashCode());
            head = head.next;
        }
        return false;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n > 1) {
            boolean contains = set.contains(n);
            if (contains) {
                return false;
            }
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int tmp = n % 10;
            n = n / 10;
            sum += tmp * tmp;
        }
        return sum;
    }


    public int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new LinkedHashMap<>();
        for (int num : nums) {
            count.computeIfPresent(num, (key, value) -> value + 1);
            count.putIfAbsent(num, 1);
        }
        Map.Entry<Integer, Integer> entity = null;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entity == null || entry.getValue() > entity.getValue()) {
                entity = entry;
            }
        }
        return entity.getKey();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int total = m + n;
        int p = m - 1;
        int p2 = n - 1;
        int tail = total - 1;
        int cur = 0;
        while (p >= 0 || p2 >= 0) {
            if (nums1[p] > nums2[p2]) {
                cur = nums1[p--];
            }
            if (nums2[p2] > nums1[p]) {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return Collections.emptyList();
        }
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;

        }
        return result;
    }


    public ListNode deleteDuplicates(ListNode head) {
        ListNode current;
        current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }


    /**
     * 66. 加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < length; j++) {
                    digits[j] = 0;
                }
                return digits;
            }
        }
        int[] ans = new int[length + 1];
        ans[0] = 1;
        return ans;
    }


    public int lengthOfLongestSubstring(String s) {

        // 用来存放字符与他的
        HashMap<Character, Integer> windows = new HashMap<>();
        int max = 0, left = 0;
        for (int right = 0; right < s.length(); ) {
            // 获取右边指针的字符
            char c = s.charAt(right);
            // 指针右移
            right++;
            Integer integer = windows.get(c);
            integer = integer == null ? 0 : integer;
            windows.put(c, ++integer);
            // 如果该字符的值大于1则说明这个字符出超过一次
            while (windows.get(c) > 1) {
                //获取右边左边边界的字符
                char c1 = s.charAt(left);
                //左边边界++
                left++;
                Integer integer1 = windows.get(c1);
                integer1 = integer1 == null ? 0 : integer1;
                //使得该字符
                windows.put(c1, --integer1);
            }
            max = Math.max(max, right - left);
        }
        return max;
    }


    public boolean carPooling(int[][] trips, int capacity) {
        //创建一个以下车顺序的小根堆
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        //对上车顺序排序
        Arrays.sort(trips, Comparator.comparingInt(o -> o[1]));
        //便利行程表
        for (int[] trip : trips) {
            //先上车 减少车的空位
            capacity -= trip[0];
            //当座位为负值的时候就要踢人下车
            if (capacity < 0) {
                // 如果下车堆不为空, 并且下车的站小于等于当前要上车的
                while (!heap.isEmpty() && heap.peek()[2] <= trip[1]) {
                    // 车的容量就加上当前要下车的人数,然后在堆里移除这个行程
                    capacity += heap.poll()[0];
                }
                //能下的都下完,如果这个时候位置还是小于0,就说明行程表不满足
                if (capacity < 0) {
                    return false;
                    //能下完的 下完还不行就返回
                }
            }
            //吧这个行程表加到堆里,并按照下车顺序排序
            heap.offer(trip);
        }
        return true;
    }

    /**
     * 爱吃香蕉
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        // 从小到大排序
        //List<Integer> pile = Arrays.stream(piles).sorted().boxed().collect(Collectors.toList());
        Arrays.sort(piles);
        // 获取最大值
        Integer right = piles[piles.length - 1];
        int left = 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!canEat(piles, h, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private boolean canEat(int[] pile, int h, int k) {
        int sumTime = 0;
        for (Integer p : pile) {
            double i2 = p;
            double i3 = k;
            double i1 = Math.ceil(i2 / i3);
            sumTime += i1;
        }
        return sumTime <= h;
    }

    /**
     * 二分查找
     *
     * @return
     */
    public int search(int[] nums, int target) {
        int right = nums.length - 1;
        int left = 0;
        while (left <= right) {
            // 获取偏移量
            int offset = (right - left) / 2;
            // 中间下标等于左边位置加上偏移量;
            int mid = left + offset;
            if (nums[mid] == target) {
                return mid;
                // 如果中间的下标的值大于目标的值,
                // 就说明该值在左边,就把右边的边界移动到现在mid值-1的位置
            } else if (nums[mid] > target) {
                right = mid - 1;
                //如果中间下标的值小于目标的值,
                // 就说明目标值在,中间值的右边,就把左边边界移动到现在中间值+1的位置
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 前缀和
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int sum = 0, count = 0;
        // 一次循环
        for (int i = 0; i < nums.length; i++) {
            // 二次循环  如果j下标等于 [3]
            for (int j = i; j >= 0; j--) {
                // 那这里就是 [3]+[2]+[1]
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 反转字符串中的元音字母
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;

        char[] ss = s.toCharArray();
        while (left < right) {
            // 如果左边小于右边并且左边是元音
            while (left < right && isVowel(ss[left])) {
                left++;
            }
            // 如果左边小于右边并且右边边是元音
            while (left < right && isVowel(ss[right])) {
                right--;
            }
            //左右互换
            if (left < right) {
                char temp = ss[left];
                ss[left] = ss[right];
                ss[right] = temp;
                //游标移动
                left++;
                right--;
            }


        }
        return new String(ss);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) < 0;
    }


    /**
     * 去除重复元素
     *
     * @param nums
     * @param val
     * @return
     */


    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int m = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {
                nums[m] = nums[i];
                m++;
            }
        }
        return m;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int m = 0;
        //从1开始遍历
        for (int i = 1; i < nums.length; i++) {
            /**
             * 如果该数前一个数小于该数则表明,不重复，
             * 如果m>=i则 m下标的元素是等于i下标的元素的,则开启下一个循环,此时i+1,m没有改变
             * 直到找到一个m下标的值小于i下标的值(也就是下一个不重复的值),就把m+1下标的元素赋予这个元素
             */
            if (nums[m] < nums[i]) {
                nums[++m] = nums[i];
            }
        }
        return ++m;
    }

    /**
     * 网咯延时
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        return 0;
    }

    /**
     * 有效括号
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int n = s.length();
        // 如果为单数直接返回
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        // 新建一个栈
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 如果map里面有这个key就说明是右括号，
            if (pairs.containsKey(ch)) {
                // 如果栈为空，或者栈里面取出来的map value里面的左括号不能栈顶的左括号
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 矩阵中战斗力最弱的k行
     *
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows2(int[][] mat, int k) {
        // 定义数组记录士兵的个数和行数
        int[][] sort = new int[mat.length][2];
        // 双层循环遍历数组的值
        for (int i = 0; i < mat.length; i++) {
            // count 记录每一行1的个数
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    count++;
                } else {
                    break;
                }
            }
            // 把记录的士兵的个数和行数放入新的数组
            sort[i] = new int[]{count, i};
        }
        //重写排序接口对sort进行排序
        Arrays.sort(sort, (e1, e2) -> {
            // 如果士兵个数不相等
            if (e1[0] != e2[0]) {
                // 按照士兵个数进行升序排序
                return e1[0] - e2[0];
            } else {
                // 按照行数进行升序排序
                return e1[1] - e2[1];
            }
        });
        int[] ans = new int[k];
        // 把前面k个放入数组
        for (int i = 0; i < k; i++) {
            ans[i] = sort[i][1];
        }
        return ans;

    }

    /**
     * 每日一题之
     * 二叉树的垂序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //nodes得到深度优先遍历之后的结果
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        // 对node进行排序
        nodes.sort(Comparator
                // col 为第一关键字升序
                .comparingInt((int[] x) -> x[0])
                //row 为第二关键字升序
                .thenComparingInt(x -> x[1])
                //value 为第三关键字升序
                .thenComparingInt(x -> x[2]));

        List<List<Integer>> ans = new ArrayList<>();
        int size = 0;
        int lastcol = Integer.MIN_VALUE;
        // 遍历排序之后的node节点
        for (int[] tuple : nodes) {
            int col = tuple[0], value = tuple[2];
            if (col != lastcol) {
                lastcol = col;
                ans.add(new ArrayList<>());
                size++;
            }
            ans.get(size - 1).add(value);
        }
        return ans;
    }

    // 二叉树深度优先遍历
    private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(new int[]{col, row, node.val});
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }

    /**
     * 最长公共前缀--纵向查找法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        // 如果数组为空,或者长度为0 直接返回空字符串
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 获取第一字符串的长度
        int length = strs[0].length();
        // 获取数组的长度
        int count = strs.length;
        // 纵向比较
        for (int i = 0; i < length; i++) {
            // 获取第一个字符串的第一个下标的值
            char a = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                // 如果i等于当前第j个字符串的长度
                // 又或者第j个字符串的第i个字母与首个字符串的第i个不相等
                if (i == strs[j].length() || strs[j].charAt(i) != a) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 罗马数字转整数
     *
     * @param s
     * @return
     */
    public Integer romanToInt(String s) {
        int sum = 0;
        // 获取第一个阿拉伯数字
        int pre = _getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            //获取该下标对应的罗马数字的阿拉伯数字
            int n = _getValue(s.charAt(i));
            // 如果前一个数字小于数字n,则n的前面一个数为负数需要减去
            // 例如 IV  = -1 + 5 = 4
            if (pre < n) {
                sum -= pre;
                // 若前一个数大于n 着这两个数正常相加
            } else {
                sum += pre;
            }
            //循环下一个数,当前数变成前一个数
            pre = n;
        }
        sum += pre;

        return sum;
    }

    /**
     * 将罗马数字转换成对应的阿拉伯数字
     *
     * @param ch
     * @return
     */
    private int _getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /**
     * 回文数
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        int reverseNumber = 0;
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        while (x > reverseNumber) {
            reverseNumber = reverseNumber * 10 + x % 10;
            x /= 10;
        }
        return x == reverseNumber || x == reverseNumber / 10;
    }

    /**
     * 整数反转
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int rev = 0;
        while (x != 0) {
            if (rev > ((1 << 31) - 1) / 10 || rev < (-1 << 31) / 10) {
                return 0;
            } else {
                int last = x % 10;
                x = x / 10;
                rev = rev * 10 + last;
            }
        }
        return rev;
    }
}
