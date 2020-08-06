public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
        Queue < int[] > queue = new LinkedList < > ();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
}

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
}

class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null) return null;
		rotate(matrix, 0, matrix.length - 1);
		return;
    }
	
	private void rotate(int[][] matrix, int s, int e){
		if (s >= e){
			return;
		}
		for (int i = 0; i < e - s; i++) {
			int tmp = matrix[s][s + i];
			matrix[s][s + i] = matrix[e - i][s];
			matrix[e - i][s] = matrix[e][e - i];
			matrix[e][e - i] = matrix[s + i][e];
			matrix[s + i][e] = tmp;
		}
		rotate(matrix, s--, e--);
	}
}

// 1. Two Sum

class Solution {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> M = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (M.containsKey(complement)) {
				return new int[]{M.get(complement), i};
			}
			M.put(nums[i], i);
		}
		return null;
	}
}

// 2. Add Two Numbers 

class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(0);
		ListNode p = res;
		int addOn = 0;
		while (l1 != null || l2 != null) {
			int cur = addOn;
			if (l1 != null) {
				cur += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				cur += l2.val;
				l2 = l2.next;
			}
			p.next = new ListNode(cur % 10, null);
			addOn = cur / 10;
			p = p.next;
		}
		if (addOn > 0) {
			p.next = new ListNode(addOn, null);
		}
		return res.next;
    }
}

// 3. Longest Substring Without Repeating Characters

class Solution {
    public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0 || s == null) {
			return 0;
		}
		
		int res = 0, start = 0;
		Map<Character, Integer> M = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char curChar = s.charAt(i);
			if (M.containsKey(curChar) && M.get(curChar) >= start) {
				start = M.get(curChar) + 1;
			}
			M.put(curChar, i);
			res = Math.max(i - start + 1, res);
		}
		return res;
    }
}

//4. Median of Two Sorted Arrays

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
	}
}


// 6. Zigzag conversion 

class Solution {
    private int getIndx(int curIndx, int numRows) {
        if (curIndx < numRows) {
            return curIndx;
        }
        return 2 * numRows - 2 - curIndx;
    }
    
    public String convert(String s, int numRows) {
        StringBuilder res = new StringBuilder();
        if (s.length() == 0) {
            return res.toString();
        }
        List<StringBuilder> M = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            M.add(new StringBuilder());
        }
        int stepSize = Math.max(1, 2 * numRows - 2);
        int repet = s.length() / stepSize;
        for (int i = 0; i <= repet; i++) {
            //String subs = s.substring(stepSize * i, stepSize * (i+1));
            int edgeRight = Math.min(s.length(), stepSize * (i+1));
            for (int j = stepSize * i; j < edgeRight; j++) {
                int rowIndx = getIndx(j - stepSize * i, numRows);
                M.get(rowIndx).append(s.charAt(j));
            }
        }
        for (int i = 0; i < numRows; i++) {
            res.append(M.get(i));
        }
        return res.toString();
    }
}

// 5. Longest Palindromic Substring
class Solution {
    public String longestPalindrome(String s) {
		int strLen = s.length();
		if (s == null) {
			return;
		}
		if (strLen == 0) {
			return "";
		}
        boolean[][] dp = new boolean[strLen][strLen];
		int maxLen = -1;
		String res;
		for (int j = 0; j < strLen; j++) { // 注意此处先j再i
			for (int i = 0; i <= j; i++) {
				if (j - i == 0) {
					dp[i][j] = true;
				} else if (s.charAt(i) != s.charAt(j)) {
					dp[i][j] = false;
				} else if (j == i + 1) {
					dp[i][j] = true;
				} else if (dp[i+1][j-1]) {
					dp[i][j] = true;
				} else {
					dp[i][j] = false;
				}
				if (dp[i][j] && (j - i) > maxLen) {
					res = s.substring(i, j + 1);
				}
			}
		}
		return res;
    }
}

// 7. Reverse Integer (July 2020)

class Solution {
    public int reverse(int x) {
		String res = String.valueOf(x);
		boolean symbol = res.charAt(0) == '-' ? false : true;
		if (symbol == false) {
			res = res.substring(1, res.length());
		}
		StringBuilder sb = new StringBuilder(res);
		res = sb.reverse().toString();
		if (!symbol) {
			res = "-" + res;
		}
		long reversed = Long.valueOf(res);
		if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
			return 0;
		}
		return (int) reversed;
	}
}

// 8. String to Integer (atoi) (July 2020) Strange test case such as : +-123, --132, + 3213

class Solution {
    public int myAtoi(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		boolean start = false, sign = true, signAdded = false;
		long res = 0;
		for (int i = 0; i < str.length(); i++) {
			char curLetter = str.charAt(i);
			if (!start) {
				if (curLetter == ' ' && !signAdded) {
					continue;
				} else if (curLetter == '-' && !signAdded) {
					sign = false;
					signAdded = true;
				} else if(curLetter == '+' && !signAdded) {
					signAdded = true;
				} else if(Character.isDigit(curLetter)) {
					start = true;
					res = curLetter - '0';
				} else {
					return 0;
				}
			} else {
				if (Character.isDigit(curLetter)) {
					res = res * 10 + (curLetter - '0');
					if (sign && res > Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					}
					if (!sign && -res < Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					}
				} else {
					return sign ? (int)res : -(int)res;
				}
			}
		}
		if (!start) {
			return 0;
		}
		return sign ? (int)res : -(int)res;
	}
}

// 12. Integer to Roman (July 2020)

class Solution {
	private StringBuilder intToRomanHelper(int num) {
		StringBuilder curRoman = new StringBuilder("");

		if (num <= 3) {
			curRoman.append("I");
			num -= 1;
		} else if (num == 4) {
			curRoman.append("IV");
			num -= 4;
		} else if (num > 4 && num < 9) {
			curRoman.append("V");
			num -= 5;
		} else if (num == 9) {
			curRoman.append("IX");
			num -= 9;
		} else if (num > 9 && num < 40) {
			curRoman.append("X");
			num -= 10;
		} else if (num >= 40 && num < 50) {
			curRoman.append("XL");
			num -= 40;
		} else if (num >= 50 && num < 90) {
			curRoman.append("L");
			num -= 50;
		} else if (num >= 90 && num < 100) {
			curRoman.append("XC");
			num -= 90;
		} else if (num >= 100 && num < 400) {
			curRoman.append("C");
			num -= 100;
		} else if (num >= 400 && num < 500) {
			curRoman.append("CD");
			num -= 400;
		} else if (num >= 500 && num < 900) {
			curRoman.append("D");
			num -= 500;
		} else if (num >= 900 && num < 1000) {
			curRoman.append("CM");
			num -= 900;
		} else {
			curRoman.append("M");
			num -= 1000;
		}
		if (num > 0) {
			return curRoman.append(intToRomanHelper(num));
		}
		return curRoman;
	}
	
    public String intToRoman(int num) {
		if (num <= 0) return null;
		return intToRomanHelper("", num).toString();
	}
}

// 12. Integer to Roman (Solution key)

int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

public String intToRoman(int num) {
    StringBuilder sb = new StringBuilder();
    // Loop through each symbol, stopping if num becomes 0.
    for (int i = 0; i < values.length && num >= 0; i++) {
        // Repeat while the current symbol still fits into num.
        while (values[i] <= num) {
            num -= values[i];
            sb.append(symbols[i]);
        }
    }
    return sb.toString();
}

// 13. Roman to Integer (July 2020)

class Solution {
	int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	Map<String, Integer> M = new HashMap<>();
	
	Solution(){
		for (int i = 0; i < symbols.length; i++) {
			M.put(symbols[i], values[i]);
		}
	}
	
    public int romanToInt(String s) {
		int sum = 0;
        for (int i = 0; i < s.length(); ) {
			String substr;
			if (i + 2 <= s.length() && M.containsKey(s.substring(i, i+2))) {
				substr = s.substring(i, i + 2);
				sum += M.get(substr);
				i += 2;
			} else {
				substr = s.substring(i, i + 1);
				sum += M.get(substr);
				i++;
			}
		}
		return sum;
    }
}

// 14. Longest Common Prefix

class Solution {
    public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (strs[j].length() <= i || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}
}

// 15. 3Sum (July 2020)

class Solution {
	private void twoSumHelper(int[] nums, int start, List<List<Integer>> res) {
		int i = start + 1, j = nums.length - 1;
		int target = -nums[start];
		while (i < j) {
			if (nums[i] + nums[j] < target) {
				i++;
			} else if (nums[i] + nums[j] > target) {
				j--;
			} else if (i  == start + 1 || nums[i] != nums[i-1]) {
				res.add(Arrays.asList(nums[start], nums[i++], nums[j--]));
			} else {
				i++; j--;
			}
		}
	}
	 
    public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
			if (i == 0 || nums[i] != nums[i-1]) {
				twoSumHelper(nums, i, res);
			}
		}
		return res;
	}
}

//16. 3Sum Closest

class Solution {
    public int threeSumClosest(int[] nums, int target) {
		int diff = Integer.MAX_VALUE, res = Integer.MIN_VALUE;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || nums[i] != nums[i-1]) {
				int sum = twoSumCloset(nums, i, target);
				int curDif = Math.abs(sum - target);
				if (curDif < diff){
					diff = curDif;
					res = sum;
				}
			}
		}
		return res;
	}
	
	private int twoSumCloset(int[] nums, int indx, int target) {
		int diff = Integer.MAX_VALUE, res = Integer.MIN_VALUE;
		int left = indx + 1, right = nums.length - 1;
		while (left < right) {
			int sum = nums[indx] + nums[left] + nums[right];
			if (Math.abs(sum - target) < diff){
				diff = Math.abs(sum - target);
				res = sum;
			}
			if (sum == target) {
				return target;
			}
			if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
}

// 17. letter Combination of a Phone Number (recursive)

class Solution {
	private char[] getLetters(char digit) {
		if (digit < '7') {
			char[] letters = new char[] {'a', 'b', 'c'};
			for (int i = 0; i < letters.length; i++) {
				letters[i] += (digit - '2') * 3;
			}
			return letters;
		} else if (digit == '7') {
			return new char[] {'p','q','r','s'};
		} else if (digit == '8') {
			return new char[] {'t','u','v'};
		} else {
			return new char[] {'w','x','y','z'};
		}
	}
	
    public List<String> letterCombinations(String digits) {
		if (digits.length() == 0) {
			return new ArrayList<String>();
		}
		List<String> resList = new ArrayList<>();
		letterCombinationsHelper("", resList, digits);
		return resList;
    }
	
	private void letterCombinationsHelper(String prefix, List<String> res, String digitsRem) {
		if (digitsRem.length() == 0) {
			res.add(prefix);
			return ;
		}
		char[] curLetters = getLetters(digitsRem.charAt(0));
		for (char c : curLetters) {
			letterCombinationsHelper(prefix + Character.toString(c), res, digitsRem.substring(1, digitsRem.length()));
		}
	}
}

// 18. 4Sum

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		return kSum(nums, target, 0, 4);
	}
	public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
			return res;
		if (k == 2)
			return twoSum(nums, target, start);
		for (int i = start; i < nums.length; ++i)
			if (i == start || nums[i - 1] != nums[i])
				for (var set : kSum(nums, target - nums[i], i + 1, k - 1)) {
					res.add(new ArrayList<>(Arrays.asList(nums[i])));
					res.get(res.size() - 1).addAll(set);
				}
		return res;
	}
	public List<List<Integer>> twoSum(int[] nums, int target, int start) {
		List<List<Integer>> res = new ArrayList<>();
		Set<Integer> s = new HashSet<>();
		for (int i = start; i < nums.length; ++i) {
			if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i])
				if (s.contains(target - nums[i]))
					res.add(Arrays.asList(target - nums[i], nums[i]));
			s.add(nums[i]);
		}
		return res;
	}

// 19. Remove Nth Node From End of List (slow, fast pointer)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode newHead = new ListNode(0);
		newHead.next = head;
		ListNode l = newHead, r = newHead;
		for (int i = 0; i < n; i++) {
			r = r.next;
		}
		while (r.next != null) {
			l = l.next;
			r = r.next;
		}
		l.next = l.next.next;
		return newHead.next;
	}
}


// 20. Valid Parentheses // 1st

class Solution {
    public boolean isValid(String s) {
		LinkedList<Character> stack = new LinkedList<>();
		Map<Character, Character> M = new HashMap<>();
		M.put(')', '('); M.put('}', '{'); M.put(']', '[');
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!M.containsKey(c)) {
				stack.push(c);
				continue;
			}
			if (stack.isEmpty()) {
				return false;
			}
			char match = stack.pop();
			if (M.get(c) != match) {
				return false;
			}
		}
		if (stack.isEmpty()) return true;
		return false;
	}
}

// 20. Valid Parentheses // (5/17/2020)

class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0) {
			return true;
		}
		LinkedList<Character> stack = new LinkedList<>();
		Map<Character, Character> M = new HashMap<>();
		M.put(')', '('); M.put('}', '{'); M.put(']', '[');
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!M.containsKey(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				if (M.get(c) != stack.pop()){
					return false;
				}
			}
		}
		if (stack.isEmpty()) return true;
		return false;
    }
}

// 21. Merge Two Sorted Lists (July 2020)

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode();
		ListNode p = newHead; p.next = null;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				p.next = l2;
				break;
			}
			if (l2 == null) {
				p.next = l1;
				break;
			}
			if (l1.val < l2.val) {
				p.next = l1;
				l1 = l1.next;
			} else {
				p.next = l2;
				l2 = l2.next;
			}
			p = p.next;
		}
		return newHead.next;
    }
}


// 21 Merge Two Sorted Lists

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode p = head;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				p.next = l2;
				return head.next;
			}
			if (l2 == null) {
				p.next = l1;
				return head.next;
			}
			if (l1.val < l2.val) {
				p.next = l1;
				l1 = l1.next;
			} else {
				p.next = l2;
				l2 = l2.next;
			}
			p = p.next;
		}
		return head.next;
	}
}

// 22. Generate Parentheses (july 2020)

class Solution {
	private void generateParenthesisHelper(StringBuilder prefix, List<String> res, int leftNum, int n) {
		if (prefix.length() == n * 2) {
			res.add(prefix.toString());
			return;
		}
		int diff = leftNum - (prefix.length() - leftNum);
		if (leftNum < n) {
			prefix.append('(');
			generateParenthesisHelper(prefix, res, leftNum + 1, n);
			prefix.setLength(prefix.length() - 1);
		}
		if (diff > 0) {
			prefix.append(')');
			generateParenthesisHelper(prefix, res, leftNum, n);
			prefix.setLength(prefix.length() - 1);
		}
	}
	
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
		generateParenthesisHelper(new StringBuilder(), res, 0, n);
		return res;
    }
}



// 22. Generate Parentheses

class Solution {
	private List<String> res;
	private int n;
	
    public List<String> generateParenthesis(int n) {
		this.n = n;
		res = new ArrayList<>();
		if (n <= 0) {
			return res;
		}
		gParentHelper(new StringBuilder(), 0, 0);
		return res;
	}
	
	private void gParentHelper(StringBuilder preList, int leftCount, int rightCount) {
		if (leftCount == n && rightCount == n) {
			res.add(preList.toString());
			return;
		}
		if (leftCount < n) {
			gParentHelper(preList.append('('), leftCount+1, rightCount);
			preList.setLength(preList.length() - 1);
		}
		if (leftCount - rightCount > 0) {
			gParentHelper(preList.append(')'), leftCount, rightCount + 1);
			preList.setLength(preList.length() - 1);
		}
	}
}

// 22. Generate Parentheses (May 19, 2020)

class Solution {
	private int n;
	private List<String> res;
	
    public List<String> generateParenthesis(int n) {
		this.n = n;
		res = new ArrayList<>();
		if (n <= 0) {
			return res;
		}
		gParentHelper(new StringBuilder(), 0, 0);
		return res;
    }
	
	private void gParentHelper(StringBuilder prevStr, int lcount, int rcount) {
		if (lcount == n && rcount == n) {
			res.add(prevStr.toString());
			return ;
		}
		if (lcount < n) {
			gParentHelper(prevStr.append('('), lcount + 1, rcount);
			prevStr.setLength(prevStr.length() - 1);
		}
		if (lcount > rcount) {
			gParentHelper(prevStr.append(')'), lcount, rcount + 1);
			prevStr.setLength(prevStr.length() - 1);
		}
	}
}

// 24. Swap Nodes in Pairs (May 2020)

class Solution {
    public ListNode swapPairs(ListNode head) {
		ListNode vHead = new ListNode(0);
		vHead.next = head;
		swapPairsHelper(vHead);
		return vHead.next;
    }
	
	private void swapPairsHelper(ListNode curPre){			
		ListNode p = curPre;
		if (p.next == null || p.next.next == null) {
			return ;
		}
		ListNode nextStart = p.next.next.next, nextNode = p.next;
		p.next = p.next.next;
		p.next.next = nextNode;
		nextNode.next = nextStart;
		swapPairsHelper(nextNode);
	}
}

// 26. Remove Duplicates from Sorted Array (May 2020)

class Solution {
    public int removeDuplicates(int[] nums) {
		int p1 = 1, p2 = 1;
		while (p2 < nums.length) {
			if (nums[p2] != nums[p2 - 1]) {
				nums[p1++] = nums[p2++];
			} else {
				p2++;
			}
		}
		return p1;
	}
}

// 27. (May 2020) Remove Element

class Solution {
    public int removeElement(int[] nums, int val) {
		int p1 = 0, p2 = 0;
		while (p2 < nums.length) {
			if (nums[p2] == val) {
				p2++;
			} else {
				nums[p1++] = nums[p2++];
			}
		}
		return p1;
	}
}


// 28. Implement strStr() (May 2020) 

class Solution {
    public int strStr(String haystack, String needle) {
		if (needle.length() == 0) {
			return 0;
		}
		for (int i = 0; i <= haystack.length() - needle.length(); i++) {
			if (haystack.substring(i, i + needle.length()).equals(needle)) {
				return i;
			}
		}
		return -1;
	}
}

// 31. Next Permutation (May 2020)
// 从末尾往前递进， 如果一直递增，则继续前进， 如果碰到第一个变小的数，把该数和右侧当中刚刚比它大的数swap. 注意swap之后还要对右侧进行sort

class Solution {
    public void nextPermutation(int[] nums) {
		if (nums.length <= 1) {
			return ;
		}
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] >= nums[i+1]) {
				continue;
			}
			int indx = findSwapIndx(nums, i);
			int tmp = nums[indx];
			nums[indx] = nums[i];
			nums[i] = tmp;
			Arrays.sort(nums, i + 1, nums.length);
			return ;
		}
		if (nums[0] > nums[nums.length - 1]) {
			Arrays.sort(nums);
		}
		return ;
	}
	
	private int findSwapIndx(int[] nums, int pos){
		for (int i = nums.length - 1; i > pos; i--) {
			if (nums[i] > nums[pos]) {
				return i;
			}
		}
		return -1;
	}
}

// 33. Search in Rotated Sorted Array (May 2020)
// 直接二分 每次取中点后，判定该点的位置 by comparing with nums[start];
class Solution {
    public int search(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[mid] >= nums[start]) {
				if (target >= nums[start] && nums[mid] > target) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else {
				if (target <= nums[end] && target > nums[mid]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return -1;
    }
}


// 35. Search Insert Position (May 2020)

class Solution {
    public int searchInsert(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		if (nums[start] == target) {
			return start;
		}
		if (nums[start] < target) {
			return start + 1;
		}
		return start;
	}
}

// 36. Valid Sudoku (May 2020)

class Solution {
    public boolean isValidSudoku(char[][] board) {
		boolean[][] rowRec = new boolean[9][9];
		boolean[][] colRec = new boolean[9][9];
		boolean[][] subRec = new boolean[9][9];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char c = board[i][j];
				if (c == '.') {
					continue;
				}
				int num = c - '1';
				if (rowRec[i][num]) {
					return false;
				}
				rowRec[i][num] = true;
				if (colRec[j][num]) {
					return false;
				}
				colRec[j][num] = true;
				int subIndx = (i / 3) * 3 + (j / 3);
				if (subRec[subIndx][num]) {
					return false;
				}
				subRec[subIndx][num] = true;
			}
		}
		return true;
	}
}

// 38. Count and say (May 2020)

class Solution {
    public String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		String lastString = countAndSay(n - 1);
		lastString += '.';
		StringBuilder res = new StringBuilder();
		char curCh = lastString.charAt(0); int curCount = 1;
		for (int i = 1; i < lastString.length(); i++) {
			if (lastString.charAt(i) == curCh) {
				curCount++;
			} else {
				res.append(curCount);
				res.append(curCh);
				curCh = lastString.charAt(i);
				curCount = 1;
			}
		}
		return res.toString();
	}
}

// 39. Combination Sum (July 2020)

class Solution {
	private List<List<Integer>> res;
	private int target;
	
	Solution(){
		res = new ArrayList<>();
	}
	
	private void combinationSumHelper(int[] nums, List<Integer> preList, int start, int curSum) {
		if (start >= nums.length) {
			return;
		}
		for (int i = start; i < nums.length; i++) {
			if (curSum + nums[i] == target) {
				preList.add(nums[i]);
				res.add(new ArrayList<Integer>(preList));
				preList.remove(preList.size() - 1);
				return;
			}
			if (curSum + nums[i] > target) {
				return;
			}
			preList.add(nums[i]);
			combinationSumHelper(nums, preList, i, curSum + nums[i]);
			preList.remove(preList.size() - 1);
		}
	}
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
			return res;
		}
		Arrays.sort(candidates);
		this.target = target;
		combinationSumHelper(candidates, new ArrayList<Integer>(), 0, 0);
		return res;
    }
}


// 39. Combination Sum (May 2020)

class Solution {
	private List<List<Integer>> res;
	private int target;
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
		res = new ArrayList<>();
		this.target = target;
		Arrays.sort(candidates);
		combinationSumHelper(new ArrayList<Integer>(), candidates, 0, 0);
		return res;
	}
	
	private void combinationSumHelper(List<Integer> preSet, int[] candidates, int pos, int curSum) {
		if (curSum == target) {
			res.add(new ArrayList<Integer>(preSet));
			return;
		}
		if (curSum > target) {
			return;
		}
		
		for (int i = pos; i < candidates.length; i++) {
			preSet.add(candidates[i]);
			combinationSumHelper(preSet, candidates, i, curSum + candidates[i]);
			preSet.remove(preSet.size() - 1);
		}
		return ;
	}
}


// 40. Combination Sum II

class Solution {
	private List<List<Integer>> res;
	private int target;
	private int[] candidates;
	
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		this.target = target;
		this.candidates = candidates;
		res = new ArrayList<>();
		Arrays.sort(this.candidates);
		combinationSum2Helper(new ArrayList<Integer>(), 0, 0);
		return res;
	}
	
	private void combinationSum2Helper(List<Integer> preList, int pos, int sum) {
		if (sum == target){
			res.add(new ArrayList<Integer>(preList));
			return ;
		}
		if (sum > target) {
			return ;
		}
		if (pos >= candidates.length) {
			return ;
		}
		for (int i = pos; i < candidates.length; i++) {
			if (i > pos && candidates[i] == candidates[i-1] ) {
				continue;
			}
			preList.add(candidates[i]);
			combinationSum2Helper(preList, i + 1, sum + candidates[i]);
			preList.remove(preList.size() - 1);
		}
		return ;
	}
}


// 41*. First missing positive

// nums.length + 1 是有可能的最大结果 i.e., 1, 2, 3, 4 ==> first missing为5 所以只要在array中寻找[1 - n]的每个数是否存在，并放在相应的indx上

class Solution {
    public int firstMissingPositive(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > nums.length || nums[i] <= 0) {
				continue;
			}
			int toIndx = nums[i] - 1;
			if (nums[toIndx] - 1 == toIndx) {
				continue;
			}
			int tmp = nums[toIndx];
			nums[toIndx] = nums[i];
			if (tmp > 0 && tmp <= nums.length) {
				nums[i--] = tmp;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return nums.length + 1;
    }
}


// 43. Multiply Strings
class Solution {
    public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")){
			return "0";
		}
		
		byte[] res = new byte[num1.length() + num2.length()];
		for (int i = num1.length() - 1; i >= 0; i--){
			for (int j = num2.length() - 1; j >= 0; j--){
				int mul = (num1.charAt(i) - '0') + (num2.charAt(j) - '0');
				int offset1 = num1.length() - i - 1, offset2 = num2.length() - j - 1;
				mul += res[offset1 + offset2];
				res[offset1 + offset2 + 1] += mul / 10;
				res[offset1 + offset2] = mul % 10;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < res.length; i++){
			if (i == res.length - 1 && res[i] == 0){
				break;
			}
			sb.append(res[i]);
		}
		return sb.reverse().toString();
    }
}

// 44. Wildcard Matching

class Solution {
    public boolean isMatch(String s, String p) {
        int sstarPos = -1, pstarPos = -1;
        int i, j;
        s = s + ".";
        p = p + ".";
        for (i = 0, j = 0; i < s.length();) {
        	if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
        		i++; j++;
        	} else if (p.charAt(j) == '*') {
        		sstarPos = i; pstarPos = j++;
        	} else if (sstarPos < 0) {
        		return false;
        	} else {
        		i = ++sstarPos;
        		j = pstarPos + 1;
        	}
        }
		// 该部分与之前版本有改进
		if (j < p.length()) {
			return false;
		}
        return true;
    }
}


// 46. Permutations (May 2020)
// 对于每一个位置， 分别遍历数字从1到n.

class Solution {
  public void backtrack(int n,
                        ArrayList<Integer> nums,
                        List<List<Integer>> output,
                        int first) {
    // if all integers are used up
    if (first == n)
      output.add(new ArrayList<Integer>(nums));
    for (int i = first; i < n; i++) {
      // place i-th integer first 
      // in the current permutation
      Collections.swap(nums, first, i);
      // use next integers to complete the permutations
      backtrack(n, nums, output, first + 1);
      // backtrack
      Collections.swap(nums, first, i);
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    // init output list
    List<List<Integer>> output = new LinkedList();

    // convert nums into list since the output is a list of lists
    ArrayList<Integer> nums_lst = new ArrayList<Integer>();
    for (int num : nums)
      nums_lst.add(num);

    int n = nums.length;
    backtrack(n, nums_lst, output, 0);
    return output;
  }
}

// 46. Again  该题first submission 用的hashset 低效率
class Solution {
    public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> resList = new ArrayList<>();
		for (int n : nums) {
			resList.add(n);
		}
		permuteHelper(resList, res, nums.length, 0);
		return res;
	}
	
	private void permuteHelper(List<Integer> resList, List<List<Integer>> res, int n, int curPos){
		if (curPos == n){
			res.add(new ArrayList<Integer>(resList));
			return;
		}
		
		for (int i = curPos; i < n; i++) {
			Collections.swap(resList, curPos, i);
			permuteHelper(resList, res, n, curPos + 1);
			Collections.swap(resList, curPos, i);
		}
		return;
	}
}

// 47. Permutations II 
// stupid prelist递归。 用HashMap来记录每个数字的count， 用掉之后count--

class Solution {
	private List<List<Integer>> res;
	
    public List<List<Integer>> permuteUnique(int[] nums) {
		res = new ArrayList<>();
		if (nums.length == 0) {
			return res;
		}
		Map<Integer, Integer> M = new HashMap<>();
		List<Integer> permuList = new ArrayList<>();
		for (int n : nums) {
			M.putIfAbsent(n, 0);
			M.put(n, M.get(n) + 1);
		}
		permuteUniqueHelper(M, permuList, 0, nums.length);
		return res;
	}
	
	private void permuteUniqueHelper(Map<Integer, Integer> M, List<Integer> preList, int start, int n) {
		if (start == n) {
			res.add(new ArrayList<>(preList));
			return;
		}
		Set<Integer> keySet = new HashSet<Integer>(M.keySet());
		for (int num : keySet) { // 当前位置的所有选择
			preList.add(num);
			M.put(num, M.get(num) - 1);
			if (M.get(num) == 0) {
				M.remove(num);
			}
			permuteUniqueHelper(M, preList, start + 1, n);
			preList.remove(preList.size() - 1);
			M.putIfAbsent(num, 0);
			M.put(num, M.get(num) + 1);
		}
		return ;
	}
}

// 47. Permutations II (July 2020)

class Solution {
	List<List<Integer>> res;
	
	private void permuteUniqueHelper(List<Integer> curList, int start) {
		if (start == curList.size() - 1) {
			res.add(new ArrayList<Integer>(curList));
			return;
		}
		Set<Integer> S = new HashSet<>(); // 注意这里需要一个HashSet来确保该数字之间没有被交换过， 因为list无法排序 (算法过程中排序会被打乱)
		for (int i = start; i < curList.size(); i++) {
			int curVal = curList.get(i);
			if (i == start || !S.contains(curVal)) {
				Collections.swap(curList, start, i);
				S.add(curVal);
				permuteUniqueHelper(curList, start + 1); // 注意这里是start+1 不是i+1
				Collections.swap(curList, start, i);
			}
		}
	}
	
    public List<List<Integer>> permuteUnique(int[] nums) {
		
		res = new ArrayList<>();
		if (nums.length == 0) return res;
		List<Integer> curList = new ArrayList<>();
		for (int n : nums) {
			curList.add(n);
		}
		permuteUniqueHelper(curList, 0);
		return res;
    }
}


// 48.* Rotate Image

class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null) return;
		rotate(matrix, 0, matrix.length - 1);
		return;
    }
	
	private void rotate(int[][] matrix, int s, int e) {
		if (s >= e){
			return;
		}
		for (int i = 0; i < e - s; i++) {
			int tmp = matrix[s][s + i];
			matrix[s][s + i] = matrix[e - i][s];
			matrix[e - i][s] = matrix[e][e - i];
			matrix[e][e - i] = matrix[s + i][e];
			matrix[s + i][e] = tmp;
		}
		rotate(matrix, s+1, e-1);
	}
}

//49. Group Anagrams

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, ArrayList<String>> hmap = new HashMap<>();
		List<List<String>> ans = new ArrayList<>();
		
		for (String str : strs){
			char[] arry = str.toCharArray();
			Arrays.sort(arry);
			String sortStr = new String(arry);
			if (!hmap.containsKey(sortStr)) {
				hmap.put(sortStr, new ArrayList<String>());
				ans.add(hmap.get(sortStr));
			}
			List list = hmap.get(sortStr);
			list.add(str);
		}
		return ans;
    }
}

//49. Group Anagrams (May 2020) 
// 对word化为char Array后进行排序

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> M = new HashMap<>();
		for (String str : strs) {
			char[] strArray = str.toCharArray();
			Arrays.sort(strArray);
			String sortedWord = new String(strArray);
			if (!M.containsKey(sortedWord)) {
				M.put(sortedWord, new ArrayList<String>());
				res.add(M.get(sortedWord));
			}
			M.get(sortedWord).add(str);
		}
		return res;
	}
}


// 51. N-Queens

class Solution {
    public List<List<String>> solveNQueens(int n) {
        
    }
}

// 53. Maximum Subarray

class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sum > max) {
				max = sum;
			}
			if (sum < 0){
				sum = 0;
			}
		}
		return max;
    }
}

// 53. repeat (May 2020)

class Solution {
    public int maxSubArray(int[] nums) {
		int s = 0, max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			s += nums[i];
			max = Math.max(max, s);
			if (s < 0) {
				s = 0;
			}
		}
		return max;
	}
}

// 54. Spiral Matrix

class Solution {
	private int[][] matrix;
	
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0){
			return new ArrayList<Integer>();
		}
        this.matrix = matrix;
		List<Integer> ans = new ArrayList<Integer>();
		spiralOrder(0, ans);
		return ans;
    }
	
	private void spiralOrder(int layer, List<Integer> ans) {
		int rowLeft = layer, rowRight = matrix[0].length - 1 - layer;
		int columTop = layer, columBottom = matrix.length - 1 - layer;
		if (rowLeft > rowRight || columTop > columBottom){
			return;
		}
		for (int i = rowLeft; i < rowRight; i++) {
			ans.add(matrix[columTop][i]);
		}
		if (columTop == columBottom){
			ans.add(matrix[columBottom][rowRight]);
			return ;
		}
		for (int i = columTop; i < columBottom; i++){
			ans.add(matrix[i][rowRight]);
		}
		if (rowLeft == rowRight) {
			ans.add(matrix[columBottom][rowLeft]);
			return;
		}
		for (int i = rowRight; i > rowLeft; i--){
			ans.add(matrix[columBottom][i]);
		}
		for (int i = columBottom; i > columTop; i--){
			ans.add(matrix[i][rowLeft]);
		}
		spiralOrder(layer + 1, ans);
	}
}

// 55 Jump Game (May 2020)

class Solution {
    public boolean canJump(int[] nums) {
		int curReach = 0;
        for (int i = 0; i < nums.length; i++) {
			if (i > curReach) {
				return false;
			}
			curReach = Math.max(curReach, i + nums[i]);
		}
		return true;
    }
}

//55. Jump Game

class Solution {
    public boolean canJump(int[] nums) {
		int curReach = 0;
		for (int i = 0; i < nums.length; i++){
			if (nums[i] + i > curReach){
				curReach = nums[i] + i;
			}
			if (curReach <= i){
				break;
			} else if (curReach >= nums.length - 1){
				return true;
			}
		}
		if (curReach >= nums.length - 1){
			return true;
		}
		return false;
    }
}

// 56. Merge Intervals

class Solution {
	public int[][] merge(int[][] intervals) {
        Map<Integer, Integer> hmap = new HashMap<>();
		for (int[] interv : intervals) {
			//if (interv[0] == interv[1]) continue;
			if (!hmap.containsKey(interv[0])) {
				hmap.put(interv[0], interv[1]);
			} else if (interv[1] > hmap.get(interv[0])){
				hmap.put(interv[0], interv[1]);
			}
		}
		int[] myArray = new int[hmap.size()];
		int i = 0;
		for (Integer k : hmap.keySet()) {
			myArray[i++] = k;
		}
		Arrays.sort(myArray);
		
		// algorithm starts
		List<int[]> ansList = new ArrayList<>();
		for (i = 0; i < myArray.length; ) {
			int curReach = hmap.get(myArray[i]);
			int j;
			for (j = i + 1; j < myArray.length; j++) {
				if (myArray[j] > curReach) {
					ansList.add(new int[]{myArray[i], curReach});
					i = j;
					break;
				}
				if (hmap.get(myArray[j]) > curReach) {
					curReach = hmap.get(myArray[j]);
				}
			}
			if (j == myArray.length) {
				ansList.add(new int[]{myArray[i], curReach});
				break;
			}
		}
		// algorithm ends
		
		int[][] ans = new int[ansList.size()][2];
		for (i = 0; i < ansList.size(); i++) {
			ans[i] = ansList.get(i);
		}
		return ans;
    }
}

// 56. Merge Intervals (May 2020)

class Solution {
    public int[][] merge(int[][] intervals) {
		Map<Integer, Integer> M = new HashMap<>();
		for (int[] interval : intervals) {
			M.putIfAbsent(interval[0], interval[1]);
			if (interval[1] > M.get(interval[0])) {
				M.put(interval[0], interval[1]);
			}
		}
		int[] starts = new int[M.size()];
		int i = 0;
		for (int start : M.keySet()) {
			starts[i++] = start;
		}
		Arrays.sort(starts);
		//....
	}
}

// 58. Length of Last Word
class Solution {
    public int lengthOfLastWord(String s) {
		int len = 0;
        for (int i = s.length() - 1; i >= 0; i--){
			if (s.charAt(i) != ' '){
				len++;
			} else {
				if (len == 0){
					continue;
				}
				break;
			}
		}
		return len;
    }
}

//59. Spiral Matrix II

class Solution {
	private int[][] matrix;
    public int[][] generateMatrix(int n) {
		if (n <= 0) return null;
		matrix = new int[n][n];
		generateMatrix(1, 0);
		return matrix;
	}
	
	private void generateMatrix(int start, int layer) {
		int rowLeft = layer, rowRight = matrix[0].length - 1 - layer;
		int columTop = layer, columBottom = matrix.length - 1 - layer;
		if (rowLeft > rowRight) {
			return;
		}
		if (rowLeft == rowRight) {
			matrix[rowLeft][rowLeft] = start;
			return;
		}
		for(int i = rowLeft, i < rowRight; i++){
			matrix[columTop][i] = start++;
		}
		for (int i = columTop; i < columBottom; i++) {
			matrix[i][rowRight] = start++;
		}
		for (int i = rowRight; i > rowLeft; i--){
			matrix[columBottom][i] = start++;
		}
		for (int i = columBottom; i > columTop;i--) {
			matrix[i][rowLeft] = start++;
		}
		generateMatrix(start, layer + 1);
	}
}

//60. Permutation Sequence

class Solution {
	private int getFirstNum(Set<Integer> hset, int order){
		Object[] arry = hset.toArray();
		Arrays.sort(arry);
		return (int) arry[order - 1];
	}
    public String getPermutation(int n, int k) {
        int amount = 1;
		Set<Integer> hset = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			amount *= i;
			hset.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int s = n; s > 0; s--) {
			int singleAmt = amount / s;
			for (int i = 1; i <= n; i++) {
				if (i * singleAmt >= k) {
					int num = getFirstNum(hset, i);
					sb.append((char)('0' + num));
					hset.remove(num);
					amount = singleAmt;
					k -= i * singleAmt;
					break;
				}
			}
		}
		for (Integer i : hset){
			sb.append(i + '0');
		}
		return sb.toString();
    }
}

//60. Permutation Sequence (Repeat May 2020)

class Solution {
    public String getPermutation(int n, int k) {
        
    }
}

// 61. Rotate List

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
		if (head == null) { 
			return null;
		}
		if (head.next == null || k == 0) {
			return head;
		}
		ListNode p = head, q;
		int listLen = 0;
		while (p != null) {
			listLen++;
			p = p.next;
		}
		k = k % listLen;
        if (k == 0) {
            return head;
        }
        //System.out.println(k + "," + listLen);
		p = head;
		for (int i = 0; i < (listLen - k - 1); i++){
			p = p.next;
		}
		q = p.next;
		p.next = null;
		p = q;
		while (q != null){
			if (q.next == null){
				q.next = head;
                break;
			}
            q = q.next;
		}
		return p;
    }
}



// 456. 132 Pattern

class Solution {
    public boolean find132pattern(int[] nums) {
        
		for (int i = 0; i < nums.length; i++) {
			int max = Integer.MIN_VALUE;
			for (j = i; j < nums.length; j++){
				if (nums[j] <= nums[i]) {
					continue;
				}
				if (nums[j] >= max) {
					max = nums[j];
				} else {
					return true;
				}
			}
		}
		return false;
    }
}

// 62. Unique Paths solution 1: time exceed
class Solution {
	private int nRow;
	private int nColum;
	private int count;
	private int[] target;
	
    public int uniquePaths(int m, int n) {
		if (n <= 1 || m <= 1) {
			return 1;
		}
        nRow = n;
		nColum = m;
		target = new int[2];
		target[0] = n - 1; target[1] = m - 1;
		uniquePathsHelper(new int[]{0, 0});
		return count;
    }
	private void uniquePathsHelper(int[] pos){
		if (pos[0] == nRow - 1 || pos[1] == nColum - 1){
			count++;
			return;
		}
		pos[0]++;
		uniquePathsHelper(pos);
		pos[0]--; pos[1]++;
		uniquePathsHelper(pos);
		pos[1]--;
	}
}
// 62. Unique Paths solution 2 DP: AC
class Solution {
	
    public int uniquePaths(int m, int n) {
		int[] mat = new int[m];
		Arrays.fill(mat, 1);
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				mat[j] = mat[j - 1] + mat[j];
			}
		}
		return mat[m - 1];
	}
}

// 62. Unique Paths (May 2020)

class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
		Arrays.fill(dp, 1);
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[j] = dp[j - 1] + dp[j];
			}
		}
		return dp[m - 1];
    }
}

// 63. Unique Paths II
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int[] mat = new int[obstacleGrid[0].length];
		boolean obstacled = false;
		for (int i = 0; i < mat.length; i++){
			if (obstacled) {
				mat[i] = 0;
			} else {
				if (obstacleGrid[0][i] == 1) {
					obstacled = true;
					mat[i] = 0;
				} else {
					mat[i] = 1;
				}
			}
		}
		
		for (int i = 1; i < obstacleGrid.length; i++) {
			if (obstacleGrid[i][0] == 1) {
				mat[0] = 0; 
			}
			boolean continueJudge = mat[0] > 0 ? true:false;
			for (int j = 1; j < obstacleGrid[0].length; j++){
				if (obstacleGrid[i][j] == 1) {
					mat[j] = 0;
				} else {
					mat[j] = mat[j - 1] + mat[j];
					if (mat[j] > 0) {
						continueJudge = true;
					}
				}	
			}
			if (continueJudge == false) {
				return 0;
			}
		}
		return mat[mat.length - 1];	
    }
}

// 63. Unique Paths II (May 2020)

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}
        int[] dp = new int[obstacleGrid[0].length]; 
		dp[0] = 1;
		for (int i = 1; i < obstacleGrid[0].length; i++) {
			if (obstacleGrid[0][i] == 1) {
				dp[i] = 0;
			} else {
				dp[i] = dp[i-1];
			}
		}
		
		for (int j = 1; j < obstacleGrid.length; j++) {
			if (obstacleGrid[j][0] == 1) {
				dp[0] = 0;
			}
			for (int i = 1; i < obstacleGrid[0].length; i++) {
				if (obstacleGrid[j][i] == 1) {
					dp[i] = 0;
				} else {
					dp[i] = dp[i] + dp[i-1];
				}
			}
		}
		return dp[obstacleGrid[0].length - 1];
    }
}

// 64. Minimum Path Sums (May 2020)

class Solution {
    public int minPathSum(int[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		for (int j = 1; j < grid[0].length; j++) {
			grid[0][j] += grid[0][j-1];
		}
		
		for (int i = 1; i < grid.length; i++) {
			grid[i][0] += grid[i-1][0];
			for (int j = 1; j < grid[0].length; j++) {
				grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
			}
		}
		return grid[grid.length - 1][grid[0].length - 1];
	}
}

// 64. Minimum Path Sum
class Solution {
    public int minPathSum(int[][] grid) {
		if (grid.length == 0) {
			return Integer.MIN_VALUE;
		}
		for (int i = 0; i < grid.length; i++) {
			if (i == 0){
				for (int j = 1; j < grid[0].length; j++) {
					grid[i][j] += grid[i][j-1];
				}
				continue;
			}
			grid[i][0] += grid[i - 1][0];
			for (int j = 1; j < grid[0].length; j++) {
				grid[i][j] += grid[i-1][j] < grid[i][j-1]? grid[i-1][j]:grid[i][j-1];
			}
		}
		
		return grid[grid.length - 1][grid[1].length - 1];
    }
}


// 66. Plus One
class Solution {
    public int[] plusOne(int[] digits) {
		int addOn = 1, i;
		for (i = digits.length - 1; i >= 0; i--) {
			int tmp = (digits[i] + addOn);
			digits[i] = tmp % 10;
			addOn = tmp / 10;
			if (addOn == 0) {
				break;
			}
		}
		if (i < 0 && addOn > 0) {
			int[] ans = new int[digits.length + 1];
			ans[0] = addOn;
			for (int j = 1; j < ans.length; j++) {
				ans[j] = digits[j - 1];
			}
			return ans;
		}
		return digits;
    }
}


// 70. Climbing Stairs (May 2020)

class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n];
		dp[0] = 1; dp[1] = 2;
		for (int i = 2; i < n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n - 1];
    }
}

// 70. Climbing Stairs
class Solution {
    public int climbStairs(int n) {
		int[] array = new int[n];
		array[0] = 1; array[1] = 2;
		for (int i = 2; i < n; i++) {
			array[i] = array[i - 1] + array[i - 2];
		}
		return array[n-1];
    }
}

// 71. Simplify Path (May 2020)

class Solution {
    public String simplifyPath(String path) {
		if (path.charAt(0) != '/') {
			return null;
		}
        Stack<String> S = new Stack<>();
		StringBuilder curDir = new StringBuilder();
		path += '/';
		for (int i = 1; i < path.length(); i++) {
			if (path.charAt(i) == '/') {
				if (curDir.length() == 0) {
					continue;
				}
				String dir = curDir.toString();
				if (dir.equals("..")) {
					if (S.size() > 0) {
						S.pop();
					}
				}
				else if (!dir.equals(".")) {
					S.push(dir);
				}
				curDir.setLength(0);
			} else {
				curDir.append(path.charAt(i));
			} 
		}
		if (S.isEmpty()) {
			return "/";
		}
		StringBuilder res = new StringBuilder();
		for (String dir : S) {
			res.append('/');
			res.append(dir);
		}
		return res.toString();
    }
}


// 71. Simplify Path

class Solution {
    public String simplifyPath(String path) {
        if (path.charAt(0) != '/') {
			return null;
		}
		path += "/";
		
		Stack<String> stack = new Stack<>();
		boolean withSlash = false;
		StringBuilder dir = new StringBuilder();
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '/') {
				if (withSlash) {
					if (dir.length() == 0) {
						continue;
					} else {
						String dirName = dir.toString();
                        dir.setLength(0);
						if (dirName.equals("..")) {
							if (stack.size() > 0)
                                stack.pop();
						} else if (!dirName.equals(".")) {
                            //System.out.println(dirName);
							stack.push(dirName);
						}
					}
				}
				withSlash = true;
			} else if (withSlash){
				dir.append(path.charAt(i));
			} else {
				return null;
			}
        }
		if (stack.isEmpty()) {
			return "/";
		}
		StringBuilder ans = new StringBuilder();
		for (String dir1 : stack) {
			ans.append("/");
			ans.append(dir1);
		}
		return ans.toString();
    }
}

// 73. Set Matrix Zeroes

class Solution {
	int[][] matrix;
	private setColunmZero(int col) {
		for (i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}
	}
	
    public void setZeroes(int[][] matrix) {
		this.matrix = matrix;
        boolean zeroTop, zeroLeft;
		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				zeroTop = true;
				break;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				zeroLeft = true;
			}
		}
		
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0){
				setColunmZero(i);
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				Arrays.fill(matrix[i], 0);
			}
		}
		if (zeroLeft) {
			setColunmZero(0);
		}
		if (zeroTop) {
			Arrays.fill(matrix[0], 0);
		}
    }
}

// 74. Search a 2D Matrix

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
		int start = 0, end = matrix.length - 1;
		int targetRow = -1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (target >= matrix[mid][0]) {
				if (mid == end) {
					targetRow = mid;
				} else if (target < matrix[mid + 1][0]){
					targetRow = mid;
				} else {
					start = mid + 1;
				}
			} else {
				end = mid - 1;
			}
		}
		if (targetRow < 0) {
			return false;
		}
		start = 0; end = matrix[0].length - 1;
		while (start <= end){
			int mid = start + (end - start) / 2;
			if (target == matrix[targetRow][mid]) {
				return true;
			}
			if (target > matrix[targetRow][mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return false;
    }
}

// 74. Search a 2D Matrix (May 2020)

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

		int s = 0,  e = matrix.length - 1;
		int tarRow = -1;
		while (s <= e) {
			int mid = s + (e - s) / 2;
			if (matrix[mid][0] == target) {
				return true;
			}
			if (matrix[mid][0] > target) {
				e = mid - 1;
			} else {
				if (mid == e) {
					tarRow = mid;
					break;
				} else {
					if (matrix[mid + 1][0] > target) {
						tarRow = mid;
						break;
					}
				}
				s = mid + 1;
			}
		}
		if (tarRow < 0) {
			return false;
		}
		s = 0; e = matrix[0].length - 1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (matrix[tarRow][mid] == target) {
				return true;
			}
			if (matrix[tarRow][mid] < target) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return false;
	}
}

// 75. Sort Colors

class Solution {
    public void sortColors(int[] nums) {
        int p1 = 0, p2 = nums.length - 1, pcur = 0;
		
		while (pcur <= p2) {
			if (nums[pcur] == 1){
				pcur++;
				continue;
			}
			if (nums[pcur] == 0) {
				if (pcur == p1) {
					p1++; pcur++;
				} else {
					nums[p1++] = 0;
					nums[pcur++] = 1;
				}
			} else {
				nums[pcur] = nums[p2];
				nums[p2] = 2;
				p2--;
			}
		}
    }
}

// 76. Minimum Window Substring (May 2020) 

// 本题要极度小心的点： hashmap counter 的integer数值不能直接 == 比大小， instead, use Integer.intValue()

class Solution {
    public String minWindow(String s, String t) {
		if (s.length() == 0 || t.length() == 0) {
			return "";
		}
		Map<Character, Integer> tMap = new HashMap<>();
		Map<Character, Integer> counterM = new HashMap<>();
		
		for (char c : t.toCharArray()) {
			tMap.putIfAbsent(c, 0);
			tMap.put(c, tMap.get(c) + 1);
		}
		
		int qualifiedCharNum = 0, minLen = Integer.MAX_VALUE;
		int[] minWin = new int[]{0, 0};
		for (int left = 0, right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			if (!tMap.containsKey(c)) {
				continue;
			}
			counterM.putIfAbsent(c, 0);
			counterM.put(c, counterM.get(c) + 1);
			if (counterM.get(c).intValue() == tMap.get(c).intValue()) {
				qualifiedCharNum++;
			}

			while (left <= right && qualifiedCharNum == tMap.size()) {
				int curWinLen = right - left + 1;
				if (curWinLen < minLen) {
					minLen = curWinLen;
					minWin[0] = left;
					minWin[1] = right + 1;
				}
				char curChar = s.charAt(left);
				if (!tMap.containsKey(curChar)) {
					left++;
					continue;
				}
				if (counterM.get(curChar).intValue() == tMap.get(curChar).intValue()) {
					qualifiedCharNum--;
				}
				counterM.put(curChar, counterM.get(curChar) - 1);
				left++;
			}
		}
		return s.substring(minWin[0], minWin[1]);
	}
}


// 76. Minimum Window Substring
class Solution {
    public String minWindow(String s, String t) {
		Map<Character, Integer> hmap = new HashMap<>();
		Map<Character, Integer> hmapCount = new HashMap<>();
		
		for (int i = 0; i < t.length(); i++) {
			if (!hmap.containsKey(t.charAt(i))) {
				hmap.put(t.charAt(i), 0);
				hmapCount.put(t.charAt(i), 0);
			}
			hmap.put(t.charAt(i), hmap.get(t.charAt(i)) + 1);
		}
		//Set<Character> hset = new HashSet<>(hmap.keySet());
		int curMinLen = Integer.MAX_VALUE, satisfied_c_count = 0;
		int[] curMinIndx = new int[2];

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char curChar = s.charAt(i);
			if (!hmap.containsKey(curChar)) {
				continue;
			}
			queue.add(i);
			hmapCount.put(curChar, hmapCount.get(curChar) + 1);
			if (hmapCount.get(curChar).intValue() == hmap.get(curChar).intValue()) {
				satisfied_c_count++;
			}
			while (satisfied_c_count == hmap.size()) {
				int left = queue.element();
				if (i - left + 1 < curMinLen) {
					curMinLen = i - left + 1;
					curMinIndx[0] = left;
					curMinIndx[1] = i + 1;
				}
				int indx = queue.remove();
				hmapCount.put(s.charAt(indx), hmapCount.get(s.charAt(indx)) - 1);
				if (hmapCount.get(s.charAt(indx)) == hmap.get(s.charAt(indx)) - 1) {
					satisfied_c_count--;
				}
			}
		}
		if (curMinLen == Integer.MAX_VALUE){
			return "";
		}
		return s.subString(curMinIndx[0], curMinIndx[1]);
    }
}


// 77. Combinations (May 2020)

class Solution {
	private int n;
	private int k;
	
    public List<List<Integer>> combine(int n, int k) {
		this.n = n;
		this.k = k;
		List<List<Integer>> res = new ArrayList<>();
		combineHelper(res, 1, new ArrayList<Integer>());
		return res;
	}
	
	private void combineHelper(List<List<Integer>> res, int start, List<Integer> preList) {
		
		if (preList.size() == k) {
			res.add(new ArrayList<Integer>(preList));
			return;
		}
		
		for (int i = start; i <= this.n; i++) {
			preList.add(i);
			combineHelper(res, i + 1, preList);
			preList.remove(preList.size() - 1);
		}
	}
}


// 77. Combinations

class Solution {
	private List<List<Integer>> ans;
	private int rlength ;
	
    public List<List<Integer>> combine(int n, int k) {
		rlength = k;
        ans = new ArrayList<>();
		combine(0, new ArrayList<Integer>());
    }
	
	private void combine(int start, List<Integer> curList) {
		if (curList.size() == rlength) {
			ans.add(new ArrayList<Integer>(curList));
			return;
		}
		if (start > rlength) {
			return;
		}
		for (int i = start; i <= rlength; i++) {
			curList.add(i);
			combine(i + 1, curList);
			curList.remove(i);
		}
	}
}

// 78. subsets (July 2020)

class Solution {
  List<List<Integer>> output = new ArrayList();
  int n, k;

  public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
    // if the combination is done
    if (curr.size() == k)
      output.add(new ArrayList(curr));

    for (int i = first; i < n; ++i) {
      // add i into the current combination
      curr.add(nums[i]);
      // use next integers to complete the combination
      backtrack(i + 1, curr, nums);
      // backtrack
      curr.remove(curr.size() - 1);
    }
  }

  public List<List<Integer>> subsets(int[] nums) {
    n = nums.length;
    for (k = 0; k < n + 1; ++k) {
      backtrack(0, new ArrayList<Integer>(), nums);
    }
    return output;
  }
}

// 78. Subsets
// 先找出长度为1的，在长度为1的lists基础上得到长度为2的lists.
class Solution {
	private int[] nums;
	
	private int getRightEdge(List<Integer> list) {
		if (list.size() == 0) {
			return -1;
		}
		return list.get(list.size() - 1);
	}
	
    public List<List<Integer>> subsets(int[] nums) {
		Arrays.sort(nums);
		this.nums = nums;
		List<List<Integer>> ans = new ArrayList<>();
		
		ans.add(new ArrayList<Integer>());
		int lastIndx = 0;
		for (int i = 1; i <= nums.length; i++) {
			int curSize = ans.size();
			subsetHelper(ans, lastIndx);
			lastIndx = curSize;
		}
		
		for (List<Integer> list : ans) {
			for (int i = 0; i < list.size(); i++) {
				list.set(i, nums[list.get(i)]);
			}
		}
		return ans;
    }
	
	private void subsetHelper(List<List<Integer>> anSet, int lastIndx) {
        int setSize = anSet.size();
		for (int i = lastIndx; i < setSize; i++) {
			List<Integer> list = anSet.get(i);
			int index = getRightEdge(list);
			for (int j = index + 1; j < nums.length; j++) {
				ArrayList<Integer> newList = new ArrayList<>(list);
				newList.add(j);
				anSet.add(newList);
			}
		}
	}
}

// 79. Word Search (May 2020)

class Solution {
	private char[][] board;
	boolean[][] visited;
	
    public boolean exist(char[][] board, String word) {
		this.board = board;
		visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (exist(word, 0, new int[]{i, j})) {
					return true;
				}
			}
		}
		return false;
	}
	
	private List<int[]> getNeibours(int[] target) {
		int i = target[0], j = target[1];
		int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		List<int[]> neibs = new ArrayList<>();
		for (int[] direct : directions) {
			int x = i + direct[0];
			int y = j + direct[1];
			if (x >= 0 && x < board.length && y >=0 && y < board[0].length) {
				neibs.add(new int[] {x, y});
			}
		}
		return neibs;
	}
	
	private boolean exist(String word, int curIndx, int[] startPos) {
		int row = startPos[0], col = startPos[1];
		if (word.charAt(curIndx) != board[row][col]) {
			return false;
		}
		if (curIndx == word.length() - 1) {
			return true;
		}
		visited[row][col] = true;
		for (int[] neib : getNeibours(startPos)) {
			if (visited[neib[0]][neib[1]] == true) {
				continue;
			}
			if (exist(word, curIndx + 1, neib)) {
				return true;
			}
		}
		visited[row][col] = false;
		return false;
	}	
}

// 79. Word Search

class Solution {
	private char[][] board;
	boolean[][] visited;
	
    public boolean exist(char[][] board, String word) {
		visited = new boolean[board.length][board[0].length];
		this.board = board;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0)) {
					if (existHelper(new int[]{i, j}, word, 0)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private List<int[]> getNeibours(int[] pos) {
		int i = pos[0], j = pos[1];
		List<int[]> ans = new ArrayList<>();
		int[][] directs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int[] dir : directs) {
			int x = pos[0] + dir[0];
			int y = pos[1] + dir[1];
			if (x < board.length && x >= 0 && y < board[0].length && y >= 0) {
				ans.add(new int[] {x, y});
			}
		}
		return ans;
	}
	
	private boolean existHelper(int[] startPos, String word, int curIndx) {
		int row = startPos[0], col = startPos[1];
		
		if (word.charAt(curIndx) != board[row][col]) {
			return false;
		}
		if (curIndx == word.length() - 1) {
			return true;
		}
		visited[row][col] = true;
		for (int[] neib : getNeibours(startPos)) {
			if (visited[neib[0]][neib[1]]) {
				continue;
			}
			boolean res = existHelper(neib, word, curIndx + 1);
			if (res) {
				return true;
			}
		}
		visited[row][col] = false;
		return false;
	}
}


// 80. Remove Duplicates from Sorted Array II

class Solution {
    public int removeDuplicates(int[] nums) {
		int indx = 0, curNum = Integer.MAX_VALUE, curLen = 0;
        for (int i = 0; i < nums.length; i++) {
			if (nums[i] != curNum || curLen == 0) {
				nums[indx++] = nums[i];
				curNum = nums[i];
				curLen = 1;
			} else {
				if (curLen == 1) {
					curLen++;
					nums[indx++] = nums[i];
				} else {
					curLen++;
				}
			}
		}
		return indx;
    }
}


// 80. Remove Duplicates from Sorted Array II (May 2020)

class Solution {
    public int removeDuplicates(int[] nums) {
		int count = 0, i = 0;
		for (int p = 0; p < nums.length; p++) {
			if (count == 0 || count == 1) {
				nums[i] = nums[p];
				if (i == 0 || nums[p] == nums[i-1]) {
					count++;
				}
				i++;
			} else {
				if (nums[p] != nums[i-1]) {
					nums[i++] = nums[p];
					count = 1;
				}
			}
		}
		return i;
	}
}

//81. Search in Rotated Sorted Array II

class Solution {
    public boolean search(int[] nums, int target) {
		
    }
}

//82. Remove Duplicates from Sorted List II

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
		Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        ListNode nhead = new ListNode(0);
		nhead.next = head;
		while (head != null) {
			if (!hmap.containsKey(head.val)) {
				hmap.put(head.val, 0);
			}
			hmap.put(head.val, hmap.get(head.val) + 1);
			head = head.next;
		}
		head = nhead;
		while (head != null) {
			if (head.next == null){
				break;
			}
			if (hmap.get(head.next.val) >= 2) {
				head.next = head.next.next;
			} else {
				head = head.next;
			}
		}
		return nhead.next;
    }
}

// 82. Remove Duplicates from Sorted List II (May 2020)

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
		ListNode vHead = new ListNode();
		vHead.next = head;
		ListNode p = vHead;
		while (p.next != null) {
			if (p.next.next == null) {
				break;
			}
			if (p.next.val != p.next.next.val) {
				p = p.next;
			} else {
				ListNode q = p.next;
				while(q != null && q.val == p.next.val) {
					q = q.next;
				}
				p.next = q;
			}
		}
		return vHead.next;
	}
}

// 83. Remove Duplicates from Sorted List

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
			return head;
		}
		ListNode p = head;
		while (p != null){
			if (p.next == null) {
				break;
			}
			if (p.next.val == p.val) {
				p.next = p.next.next;
			} else {
				p = p.next;
			}
		}
		return head;
    }
}

// 84. Largest Rectangle in Histogram: brute force
 public class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minheight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minheight = Math.min(minheight, heights[j]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
    }
}

//84. Largest Rectangle in Histogram: stack, copied from solution key
public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack < > ();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }
}

// 85. 
class Solution {
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0 ? 1 : dp[i][j-1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        } return maxarea;
    }
}

// 86. Partition List

class Solution {
    public ListNode partition(ListNode head, int x) {
		ListNode newHead = new ListNode(2019);
		newHead.next = head;
		ListNode p = newHead, q = newHead;
		while (p != null) {
			if (p.next == null) {
				break;
			}
			if (p.next.val < x) {
				if (p != q) {
					ListNode tmp = p.next;
					p.next = tmp.next;
					tmp.next = q.next;
					q.next = tmp;
					//q = q.next;
				} else {
					p = p.next;
				}
				q = q.next;
			} else {
				p = p.next;
			}
		}
		return newHead.next;
	}
}

// 86. Partition List (May 2020)

class Solution {
    public ListNode partition(ListNode head, int x) {
		ListNode vHead = new ListNode();
		vHead.next = head;
		ListNode pl = vHead, pc = vHead;
		while (pc.next != null) {
			if (pc.next.val < x) {
				if (pc == pl) {
					pc = pc.next;
					pl = pl.next;
					continue;
				}
				ListNode tar = pc.next;
				pc.next = tar.next;
				tar.next = pl.next;
				pl.next = tar;
				pl = pl.next;
			} else {
				pc = pc.next;
			}
		}
		return vHead.next;
	}
}

// 88. Merge Sorted Array

class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // two get pointers for nums1 and nums2
    int p1 = m - 1;
    int p2 = n - 1;
    // set pointer for nums1
    int p = m + n - 1;

    // while there are still elements to compare
    while ((p1 >= 0) && (p2 >= 0))
      // compare two elements from nums1 and nums2 
      // and add the largest one in nums1 
      nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

    // add missing elements from nums2
    System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
  }
}

// 88. Merge Sorted Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
		int r_edge = m + n - 1, p1 = m - 1, p2 = n - 1;
		int i;
		for (int i = r_edge; p1 >= 0 && p2 >= 0; i--) {
			if (nums1[p1] < nums2[p2]) {
				nums1[i] = nums[p2--];
			} else {
				nums1[i] = nums[p1--];
			}
		}
		while (p1 >= 0) {
			nums1[i--] = nums1[p1--];
		}
		while (p2 >= 0) {
			nums1[i--] = nums[p2--];
		}
    }
}


// 89. Gray Code

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
		
		if (n == 0) {
			ans.add(0);
			return ans;
		}
		for (int i = 1; i <= n; i++) {
			int pleft = ans.size() - 1;
			while (pleft >= 0) {
				ans.add(ans.get(pleft) + Math.pow(2, i));
				pleft--;
			}
		}
		return ans;
    }
}


// 90. Subsets II (May 2020) 

// 新法速度更快更clean

class Solution {
	private int[] nums;
	
    public List<List<Integer>> subsetsWithDup(int[] nums) {
		this.nums = nums;
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<Integer>());
		if (nums.length == 0) {
			return res;
		}
		Arrays.sort(nums);
		subsetsWithDupHelper(res, new ArrayList<Integer>(), -1);
		return res;
	}
	
	private void subsetsWithDupHelper(List<List<Integer>> res, 
									  List<Integer> preList, 
									  int lastIndx) { // the index of the last element of the preList
		for (int i = lastIndx + 1; i < nums.length; i++) {
			if (i == lastIndx + 1 || nums[i] != nums[i-1]) {
				preList.add(nums[i]);
				res.add(new ArrayList<Integer>(preList));
				subsetsWithDupHelper(res, preList, i);
				preList.remove(preList.size() - 1);
			}
		}
	}
}

// 90. Subsets II

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> ansList = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		ansList.add(list);
		Arrays.sort(nums);
		for (int i = 1; i <= nums.length; i++) {
			for (int j = ansList.size() - 1; j >= 0; j--) {
				list = ansList.get(j);
				if (list.size() != i - 1) {
					break;
				}
                int edgeIndx;
                if (list.size() == 0){
				    edgeIndx = -1;
                } else {
                    edgeIndx = list.get(list.size() - 1);
                }
				for (int k = edgeIndx + 1; k < nums.length; k++) {
					if (k > edgeIndx + 1) {
						if (nums[k] == nums[k-1]) {
							continue;
						}
					}
					List<Integer> newList = new ArrayList<>(list);
					newList.add(k);
					ansList.add(newList);
				}
			}
		}
		
		for (List<Integer> list1 : ansList){
			for (int i = 0; i < list1.size(); i++) {
                //System.out.println(list1.get(i));
				list1.set(i, nums[list1.get(i)]);
			}
		}
		return ansList;
    }
}

// 91. Decode Ways

class Solution {
	private boolean combineDecodable(String s, int i) {
		if (s.charAt(i - 1) == '0') {
			return false;
		}
		String dualDigit = s.substring(i - 1, i + 1);
		int ans = Integer.parseInt(dualDigit);
		if (ans < 1 || ans > 26) {
			return false;
		}
		return true;
	}
	
    public int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] dp = new int[s.length()];
		if (s.charAt(0) == '0') {
			dp[0] = 0;
		} else {
			dp[0] = 1;
		}
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != '0') {
				dp[i] = dp[i-1];
			}
			if (combineDecodable(s, i)) {
				if (i != 1) {
					dp[i] += dp[i - 2];
				} else {
					dp[i] += 1;
				}
			}
		}
		return dp[s.length() - 1];
    }
}

// 91. Decode Ways (May 2020)

class Solution {
	private Map<Integer, Integer> M;
    public int numDecodings(String s) {
		M = new HashMap<>();
		return numDecodingsHelper(s, 0);
	}
	
	private int numDecodingsHelper(String s, int curIndx) {
		if (curIndx == s.length()) {
			return 1;
		}
		if (M.containsKey(curIndx)) {
			return M.get(curIndx);
		}
		char c1 = s.charAt(curIndx);
		int s1 = 0, s2 = 0;
		if (c1 >= '1' && c1 <= '9') {
			s1 = numDecodingsHelper(s, curIndx + 1);
		}
		if (curIndx + 1 < s.length()) {
			int val = Integer.valueOf(s.substring(curIndx, curIndx + 2));
			if (val <= 26 && val >= 10) {
				s2 = numDecodingsHelper(s, curIndx + 2);
			}
		}
		M.put(curIndx, s1 + s2);
		return s1 + s2;
	}
}

// 92. Reverse Linked List II
 
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) {
			return head;
		}
		ListNode newHead = new ListNode(2019);
		newHead.next = head;
		ListNode p = newHead, q = newHead;
		for (int i = 0; i < m - 1; i++) {
			p = p.next;
		}
		ListNode pmark = p.next;
		q = p.next.next;
		for (int i = 0; i < (n - m); i++) {
			ListNode tmp = q;
			q = q.next;
			tmp.next = p.next;
			p.next = tmp;
		}
		pmark.next = q;
		return newHead.next;
	}
}


// 93. Restore IP Addresses

class Solution {
	private List<String> ans;
	private String s;
	
    public List<String> restoreIpAddresses(String s) {
        this.ans = new ArrayList<>();
		this.s = s;
		restoreHelper(new ArrayList<Integer>(), 0);
		return ans;
    }
	
	private boolean isLegalNumber(String substr) {
		int val = Integer.parseInt(substr);
		if (val < 10 && substr.length() > 1) {
			return false;
		}
		if (val < 100 && substr.length() > 2) {
			return false;
		}
		if (val >= 0 && val <= 255) {
			return true;
		}
		return false;
	}
	
	private void addToAnsList(List<Integer> prelist) {
		StringBuilder sb = new StringBuilder();
		for (Integer val : prelist){
			sb.append(val);
			sb.append('.');
		}
		sb.deleteCharAt(sb.length() - 1);
		ans.add(sb.toString());
	}
	
	private void restoreHelper(List<Integer> prelist, int str_cur_pos) {
		if (str_cur_pos == s.length()) {
			if (prelist.size() == 4) {
				addToAnsList(prelist);
			}
			return ;
		}
		if (prelist.size() == 4) {
			return ;
		}
		
		for (int i = 1; i < 4; i++) {
			if (i + str_cur_pos > s.length()) {
				break;
			}
			String substr = s.substring(str_cur_pos, str_cur_pos + i);
			if (!isLegalNumber(substr)) {
				break;
			}
			prelist.add(Integer.parseInt(substr));
			restoreHelper(prelist, str_cur_pos + i);
            prelist.remove(prelist.size() - 1);
		}
		return ;
	}
}

// 93. restore IP address (May 2020)

class Solution {
	private List<String> res;
	
	private boolean isValidSubIP(String subIP) {
		if (subIP.charAt(0) == '0' && subIP.length() > 1) {
			return false;
		}
		int ipValue = Integer.valueOf(subIP);
		if (ipValue >= 0 && ipValue <= 255) {
			return true;
		}
		return false;
	}
	
	private void restoreHelper(String s, int indx, StringBuilder prefix, int start) {
		if (indx == 4) {
			if (start == s.length()){
				res.add(prefix.toString());
			}
			return;
		}

		for (int i = start; i < s.length() && i < start + 3; i++) {
			String subIP = s.substring(start, i + 1);
			if (!isValidSubIP(subIP)) {
				continue;
			}
			if (indx > 0) {
				subIP = "." + subIP;
			}
			prefix.append(subIP);
			restoreHelper(s, indx + 1, prefix, i + 1);
			prefix.setLength(prefix.length() - subIP.length());
		}
	}
	
    public List<String> restoreIpAddresses(String s) {
		res = new ArrayList<>();
		restoreHelper(s, 0, new StringBuilder(), 0);
		return res;
	}
}

// 94. Binary Tree Inorder Traversal: recursive

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<Integer>();
		TreeNode p = root;
		inorderTraversal(p, ans);
		return ans;
    }
	
	private void inorderTraversal(TreeNode node, List<Integer> list){
		if (node == null) return;
		
		inorderTraversal(node.left, list);
		list.add(node.val);
		inorderTraversal(node.right, list);
	}
}

// 94. Binary Tree Inorder Traversal: iterative

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Stack<TreeNode> S = new Stack<>();
		Set<TreeNode> visited = new HashSet<>();
		S.push(root);
		while(!S.isEmpty()) {
			TreeNode node = S.pop();
			if (visited.contains(node)) {
				res.add(node.val);
				continue;
			}
			visited.add(node);
			if (node.right != null) {
				S.push(node.right);
			}
			S.push(node);
			if (node.left != null) {
				S.push(node.left);
			}
		}
		return res;
	}
}

// 95. Unique Binary Search Trees II

class Solution {
	
	private List<TreeNode> genTreeHelper(int start, int end) {
		List<TreeNode> allRoots = new LinkedList<>();
		
		if (start > end) {
			allRoots.add(null);
			return allRoots;
		}
		
		for (int i = start; i <= end; i++) {
			List<TreeNode> leftSubTrees = genTreeHelper(start, i - 1);
			List<TreeNode> rightSubTrees = genTreeHelper(i + 1, end);
			
			for (TreeNode left : leftSubTrees) {
				for (TreeNode right : rightSubTrees) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					allRoots.add(root);
				}
			}
		}		
		return allRoots;
	}
	
    public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new ArrayList<>();
		}
		return genTreeHelper(1, n);
    }
}

// 95. Unique Binary Search Trees II (May 2020)

class Solution {
    private List<TreeNode> generateTrees(int start, int end) {
		List<TreeNode> allRoots = new ArrayList<>();
		if (start > end) {
			allRoots.add(null);
			return allRoots;
		}
		
		for (int i = start; i <= end; i++) {
			List<TreeNode> leftTrees = generateTrees(start, i -1);
			List<TreeNode> rightTrees = generateTrees(i + 1, end);
			for (TreeNode l_root : leftTrees) {
				for (TreeNode r_root : rightTrees) {
					TreeNode root = new TreeNode(i);
					root.left = l_root;
					root.right = r_root;
					allRoots.add(root);
				}
			}
		}
		return allRoots;
	}
	
	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new ArrayList<TreeNode>();
		}
		return generateTrees(1, n);
	}
}

// 96. Unique Binary Search Trees

class Solution {
    public int numTrees(int n) {
        int[] arry = new int[n + 3];
		arry[0] = 1; arry[1] = 1; arry[2] = 2;
		for (int i = 3; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				arry[i] += arry[j] * arry[i - 1 - j];
			}
		}
		return arry[n];
    }
}

// 98. Validate Binary Search Tree (July 2020)

class Solution {
	private boolean isValidBSTHelper(TreeNode root, Integer left, Integer right) {
		if (root == null) return true;
		if (left != null && root.val <= Integer.intValue(left)) {
			return false;
		}
		if (right != null && root.val >= Integer.intValue(right)) {
			return false;
		}
		if (!isValidBSTHelper(root.left, left, root.val)) {
			return false;
		}
		if (!isValidBSTHelper(root.right, root.val, right)) {
			return false;
		}
		return true;
	}
	
    public boolean isValidBST(TreeNode root) {
		return isValidBSTHelper(root, null, null);
	}
}

// 98. Validate Binary Search Tree

class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
	
	public boolean isValidBST(TreeNode root, Integer left, Integer right) {
		if (root == null) {
			return true;
		}
		if (left != null && root.val <= left) {
			return false;
		}
		if (right != null && root.val >= right) {
			return false;
		}
		if (!isValidBST(root.left, left, root.val)) {
			return false;
		}
		if (!isValidBST(root.right, root.val, right)) {
			return false;
		}
		return true;
	}
}


// 100. Same Tree

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (q == null || p == null) {
			return false;
		}
			
		if (p.val != q.val) {
			return false;
		}
		if (!isSameTree(p.left, q.left)) {
			return false;
		}
		if (!isSameTree(p.right, q.right)) {
			return false;
		}
		return true;
    }
}
// 100. same tree (June 2020)

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		if (!isSameTree(p.left, q.left)) {
			return false;
		}
		if (!isSameTree(p.right, q.right)) {
			return false;
		}
		return true;
    }
}


// 101. Symmetric Tree

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
			return true;
		}
		return isSymmetric(root.left, root.right);
    }
	
	public boolean isSymmetric (TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}
		if (!isSymmetric(left.left, right.right)) {
			return false;
		}
		if (!isSymmetric(left.right, right.left)){
			return false;
		}
		return true;
	}
}

// 101. June 2020
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
			return true;
		}
		if (isSymmetric(root.left, root.right)) {
			return true;
		}
		return false;
    }
	
	private boolean isSymmetric(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}
		if (!isSymmetric(left.left, right.right)) {
			return false;
		}
		if (!isSymmetric(left.right, right.left)) {
			return false;
		}
		return true;
	}
}


// 102. June 2020 (use dfs recursion) much easier and efficient


class Solution {
	private List<List<Integer>> res;
	
    public List<List<Integer>> levelOrder(TreeNode root) {
		res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		levelOrderHelper(root, 0);
		return res;
	}
	
	private void levelOrderHelper(TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (res.size() <= level) {
			res.add(new ArrayList<Integer>());
		}
		res.get(level).add(root.val);
		levelOrderHelper(root.left, level + 1);
		levelOrderHelper(root.right, level + 1);
	}
}


// 102. Binary Tree Level Order Traversal

class Solution {
	
	private static class Qnode{
		private TreeNode tnode;
		private int level;
		public Qnode(TreeNode tnode, int level) {
			this.tnode = tnode;
			this.level = level;
		}
	}
	
    public List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null) return null;
		List<List<Integer>> ans = new ArrayList<>();
		Queue<Qnode> queue = new LinkedList<>();
		queue.add(new Qnode(root, 0));
		while (!queue.isEmpty()) {
			Qnode node = queue.remove();
			if (ans.size() - 1 != node.level) { // sublist already exists
				ans.add(new ArrayList<Integer>);
			}
			List<Integer> sublist = ans.get(node.level);
			sublist.add(node.tnode.val);
			if (node.tnode.left != null) {
				queue.add(new Qnode(node.tnode.left, node.level + 1));
			}
			if (node.tnode.right != null) {
				queue.add(new Qnode(node.tnode.right, node.level + 1));
			}
		}
		return ans;
	}
}

// 103. June 2020

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		Stack<TreeNode> S1 = new Stack<>();
		Stack<TreeNode> S2 = new Stack<>();
		S1.push(root);
		while (!S1.isEmpty() || !S2.isEmpty()) {
			List<Integer> row = new ArrayList<>();
			while(!S1.isEmpty()) {
				TreeNode node = S1.pop();
				row.add(node.val);
				if (node.left != null) {
					S2.push(node.left);
				}
				if (node.right != null) {
					S2.push(node.right);
				}
			}
			res.add(row);
			row = new ArrayList<>();
			while (!S2.isEmpty()) {
				TreeNode node = S2.pop();
				row.add(node.val);
				if (node.right != null) {
					S1.push(node.right);
				}
				if (node.left != null) {
					S1.push(node.left);
				}
			}
			if (row.size() > 0) {
				res.add(row);
			}
		}
		return res;
    }
}

// 103. Binary Tree Zigzag Level Order Traversal ()

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
		
		/* to store the even level nodes */
        Queue<TreeNode> queue0 = new LinkedList<>();  
		/* to store the odd level nodes */
        Queue<TreeNode> queue1 = new LinkedList<>();  
        queue0.add(root);
        while (!queue0.isEmpty() || !queue1.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            while (!queue0.isEmpty()) {
                TreeNode curr = queue0.poll();
                tmp.add(curr.val);
                /* add the children to odd level queue */
                if (curr.left != null) queue1.add(curr.left);
                if (curr.right != null) queue1.add(curr.right);
            }
            if(tmp.size() > 0) res.add(tmp);
            List<Integer> tmp1 = new ArrayList<>();
            while (!queue1.isEmpty()) {
                TreeNode curr = queue1.poll();
                tmp1.add(0, curr.val);
                /* add the children to even level queue */
                if (curr.left != null) queue0.add(curr.left);
                if (curr.right != null) queue0.add(curr.right);
            }
            if(tmp1.size() > 0) res.add(tmp1);
        }
        return res;
    }
}

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
		
        Stack<TreeNode> stack0 = new Stack<>();  
        Stack<TreeNode> stack1 = new Stack<>();
		
        stack0.add(root);
		List<Integer> tmp;
        while (!stack0.isEmpty() || !stack1.isEmpty()) {
			tmp = new ArrayList<>();
			while (!stack0.isEmpty()){
				TreeNode node = stack0.pop();
				tmp.add(node.val);
				if (node.left != null) {
					stack1.add(node.left);
				}
				if (node.right != null) {
					stack1.add(node.right);
				}
			}
			res.add(tmp);
			tmp = new ArrayList<>();
			while (!stack1.isEmpty()) {
				TreeNode node = stack1.pop();
				tmp.add(node.val);
				if (node.right != null) {
					stack0.add(node.right);
				}
				if (node.left != null) {
					stack0.add(node.left);
				}
			}
			if (tmp.size() > 0)
				res.add(tmp);
        }
        return res;
    }
}

// 104 June 2020

class Solution {
	private int res;
	
	private void maxDepthHelper(TreeNode root, int depth) {
		if (depth > res) {
			res = depth;
		}
		if (root.left != null) {
			maxDepthHelper(root.left, depth + 1);
		}
		if (root.right != null) {
			maxDepthHelper(root.right, depth + 1);
		}
	}
	
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
		res = -1;
		maxDepthHelper(root, 1);
		return res;
    }
}


// 104. Maximum Depth of Binary Tree
class Solution {
	private int maxLv;
	
    public int maxDepth(TreeNode root) {
        if (root == null) {
			return 0;
		}
		maxLv = 0;
		return maxDepth(root, 1);
    }
	
	private int maxDepth (TreeNode root, int curLevel) {
		if (curLevel > maxLv) {
			maxLv = curLevel;
		}
		if (root.left != null) {
			maxDepth(root.left, curLevel + 1);
		}
		if (root.right != null) {
			maxDepth(root.right, curLevel + 1);
		}
		return maxLv;
	}
}

// 105. Construct Binary Tree from Preorder and Inorder Traversal

class Solution {
	private int[] preorder;
	private int[] inorder;
	
	private TreeNode buildTreeHelper(int[] preRange, int[] inRange) {
		if (preRange[0] > preRange[1]) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preRange[0]]);
		int i = inRange[0];
		for (; i <= inRange[1]; i++) {
			if (inorder[i] == preorder[preRange[0]]) {
				break;
			}
		}
		int numsOfNodesLeftTree = i - inRange[0];
		root.left = buildTreeHelper(new int[]{preRange[0] + 1, preRange[0] + numsOfNodesLeftTree}, new int[]{inRange[0], i - 1});
		root.right = buildTreeHelper(new int[]{preRange[0] + numsOfNodesLeftTree + 1, preRange[1]}, new int[]{i + 1, inRange[1]});
		return root;
	}
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
		this.preorder = preorder;
		this.inorder = inorder;
		return buildTreeHelper(new int[]{0, preorder.length - 1}, new int[]{0, preorder.length - 1});
    }
}

// 105. Construct Binary Tree from Preorder and Inorder Traversal (June 2020)

class Solution {
	private int[] preorder;
	private int[] inorder;
	
	private int findIndxinInorder(int[] range, int val) {
		for (int i = range[0]; i <= range[1]; i++) {
			if (inorder[i] == val) {
				return i;
			}
		}
		return -1;
	}
	
	private TreeNode buildTreeHelper(int[] rangePre, int[] rangeIn) {
		if (rangePre[1] - rangePre[0] < 0) {
			return null;
		}
		int rootVal = preorder[rangePre[0]];
		TreeNode root = new TreeNode(rootVal);
		int indx = findIndxinInorder(rangeIn, rootVal);
		int leftLen = indx - rangeIn[0];
		root.left = buildTreeHelper(new int[]{rangePre[0] + 1, rangePre[0] + leftLen}, new int[]{rangeIn[0], indx - 1});
		root.right = buildTreeHelper(new int[] {rangePre[0] + leftLen + 1, rangePre[1]}, new int[]{indx + 1, rangeIn[1]});
		return root;
	}
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
		this.preorder = preorder;
		this.inorder = inorder;
		int len = preorder.length;
		return buildTreeHelper(new int[]{0, len - 1}, new int[] {0, len - 1});
	}
}

// 106. Construct Binary Tree from Inorder and Postorder Traversal

class Solution {
	private int[] inorder;
	private int[] postorder;
	
	private TreeNode buildTree(int inStart, int inEnd, int postStart, int postEnd){
		if (inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[postEnd]);
		int rootIndex = -1;
		
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				rootIndex = i;
				break;
			}
		}
		int nOfLeftTnodes = rootIndex - inStart;
		root.left = buildTree(inStart, rootIndex - 1, postStart, nOfLeftTnodes - 1 + postStart);
		root.right = buildTree(rootIndex + 1, inEnd, nOfLeftTnodes + postStart, postEnd - 1);
		return root;
	}
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
		this.inorder = inorder;
		this.postorder = postorder;
		return buildTree(0, inorder.length - 1, 0, inorder.length - 1);
    }
}

// 107. Binary Tree Level Order Traversal II

class Solution {
	private LinkedList<List<Integer>> res;
	
	private void levelOrderBottom(TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (level == res.size()) {
			res.addFirst(new ArrayList<Integer>());
		}
		res.get(res.size() - level - 1).add(root.val);
 		levelOrderBottom(root.left, level + 1);
		levelOrderBottom(root.right, level + 1);
		return ;
	}
	
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
		res = new LinkedList<>();
		levelOrderBottom(root, 0);
		return res;
    }
}

// 108. Convert Sorted Array to Binary Search Tree (June 2020)

class Solution {
	
	private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = left + (right - left) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sortedArrayToBST(nums, left, mid - 1);
		root.right = sortedArrayToBST(nums, mid + 1, right);
		return root;
	}
	
    public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) return null;
		return sortedArrayToBST(nums, 0, nums.length - 1);		
	}
}

// 108. Convert Sorted Array to Binary Search Tree

class Solution {
	private TreeNode sortedArrayToBST(int[] nums, int left, int right){
		if (left > right) {
			return null;
		}
		int mid = left + (right - left) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sortedArrayToBST(left, mid - 1);
		root.right = sortedArrayToBST(mid + 1, right);
		return root;
	}
	
    public TreeNode sortedArrayToBST(int[] nums) {
		return sortedArrayToBST(nums, 0, nums.length - 1);
	}
}


// 108. Convert Sorted Array to Binary Search Tree (June 2020)

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
		
	}
}

// 109. Convert Sorted List to Binary Search Tree

class Solution {
	
	private TreeNode sortedListToBST(ListNode head, int listLen) {
		if (head == null || listLen <= 0) {
			return null;
		}
		TreeNode root; ListNode p = head;
		int i;
		for (i = 0; i < listLen / 2; i++) {
			p = p.next;
		}
		root = new TreeNode(p.val);
		root.left = sortedListToBST(head, i);
		root.right = sortedListToBST(p.next, listLen - i - 1);
		return root;
	}
	
    public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}
		int listLen = 0; ListNode p = head;
		while (p != null) {
			p = p.next;
			listLen++;
		}
		return sortedListToBST(head, listLen);
	}
}

// 110. Balanced Binary Tree

class Solution { // top-down recursion // less efficient n! complexity
	private int treeHeight(TreeNode root, int base) {
		if (root == null) {
			return base;
		}
		return Math.max(treeHeight(root.left, base + 1), treeHeight(root.right, base + 1));
	}
	
    public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (Math.abs(treeHeight(root.left, 0) - treeHeight(root.right, 0)) <= 1) {
			if (isBalanced(root.left) && isBalanced(root.right)) {
				return true;
			}
		}
		return false;
	}
}

// 110. Balanced Binary Tree

class Solution { // Bottom-up recursion // Efficient O(N) 
	
	private Integer isBalancedHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Integer leftHeight = isBalancedHelper(root.left);
		if (leftHeight == null) { // Return null means unbalanced, otherwise refers to the height of the tree
			return null;
		}
		Integer rightHeight = isBalancedHelper(root.right);
		if (rightHeight == null) {
			return null;
		}
		if (Math.abs(leftHeight - rightHeight) <= 1) {
			return Math.max(leftHeight, rightHeight) + 1;
		}
		return null;
	}
	
    public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (isBalancedHelper(root) != null) {
			return true;
		}
		return false;
	}
}

// 111. (June 2020)
// 注意必须为root到leef的depth, 如果一边的子树为空， 则不能算作depth为0 + 1.
class Solution {
	
    public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = minDepth(root.left);
		int rightDepth = minDepth(root.right);
		
		if (leftDepth == 0 || rightDepth == 0) {
			return Math.max(leftDepth, rightDepth) + 1;
		}
		return Math.min(leftDepth, rightDepth) + 1;
    }
}
 
// 111. Minimum Depth of Binary Tree

class Solution {
	private int minDp;
	
    public int minDepth(TreeNode root) {
		if (root == null) {
            return 0;
        }
        minDp = Integer.MAX_VALUE;
		minDepth(root, 1);
		return minDp;
	}
	
	private void minDepth(TreeNode root, int curDepth) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			if (curDepth < minDp) {
				minDp = curDepth;
			}
			return;
		}
		minDepth(root.left, curDepth + 1);
		minDepth(root.right, curDepth + 1);
	}
}


// 112 (June 2020)

class Solution {
	private boolean hasPathSum(TreeNode root, int target, int curSum){
		if (root == null) return false;
		
		if (root.left == null && root.right == null) {
			if (curSum + root.val == target) {
				return true;
			}
			return false;
		}

		boolean leftHas = hasPathSum(root.left, target, curSum + root.val);
		boolean rightHas = hasPathSum(root.right, target, curSum + root.val);
		if (leftHas || rightHas) {
			return true;
		}
		return false;
	}
	
    public boolean hasPathSum(TreeNode root, int sum) {
		return hasPathSum(root, sum, 0);
	}
}

// 112. Path Sum

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (sum == root.val && root.left == null && root.right == null) {
			return true;
		}
		if (root.left != null) {
			if (hasPathSum(root.left, sum - root.val)) {
				return true;
			}
		}
		if (root.right != null) {
			if (hasPathSum(root.right, sum - root.val)) {
				return true;
			}
		}
		return false;
	}
}
// 113 July 20

class Solution {
	List<List<Integer>> res;
	int target;
	
	private void pathSumHelper(TreeNode root, int curSum, List<Integer> curList) {
		curList.add(root.val);
		if (curSum + root.val == target) {
			if (root.left == null && root.right == null) {
				res.add(new ArrayList<Integer>(curList));
				curList.remove(curList.size() - 1);
				return;
			}
		}
		if (root.left != null) {
			pathSumHelper(root.left, curSum + root.val, curList);
		}
		if (root.right != null) {
			pathSumHelper(root.right, curSum + root.val, curList);
		}
		curList.remove(curList.size() - 1);
	}
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
		res = new ArrayList<>();
		target = sum;
		if (root == null) {
			return res;
		}
		pathSumHelper(root, 0, new ArrayList<Integer>());
		return res;
	}
}


// 113. Path Sum II (june 2020)

class Solution {
	private List<List<Integer>> res;
	
	private void pathSum (TreeNode root, int sum, List<Integer> curList) {
		if (root == null) return;
		curList.add(root.val);
		if (root.left == null && root.right == null) {
			if (sum == root.val) {
				res.add(new ArrayList<>(curList));
			}
		}
		if (root.left != null) {
			pathSum(root.left, sum - root.val, curList);
		}
		if (root.right != null) {
			pathSum(root.right, sum - root.val, curList);
		}
		curList.remove(curList.size() - 1);
		return;
	}
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
		res = new ArrayList<>();
		pathSum(root, sum, new ArrayList<Integer>());
		return res;
	}
}


// 113. Path Sum II

class Solution {
	private List<List<Integer>> res;
	
	private void pathSumHelper(TreeNode root, int remain, List<Integer> preList) {
		preList.add(root.val);
		if (root.left == null && root.right == null) {
			if (root.val == remain) {
				res.add(new ArrayList<Integer>(preList));
				preList.remove(preList.size() - 1);
				return;
			}
		}
		if (root.left != null) {
			pathSumHelper(root.left, remain - root.val, preList);
		}
		if (root.right != null) {
			pathSumHelper(root.right, remain - root.val, preList);
		}
		preList.remove(preList.size() - 1);
		return;
	}
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
		res = new ArrayList<List<Integer>>();
		if (root == null) {
			return res;
		}
		pathSumHelper(root, sum, new ArrayList<Integer>());
		return res;
	}
}

// 114. Flatten Binary Tree to Linked List

class Solution {
    public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		flattenHelper(root);
		return ;
	}
	
	private TreeNode flattenHelper(TreeNode root) {
		TreeNode tmp = root;
		if (root.left != null) {
			tmp = flattenHelper(root.left);
			tmp.right = root.right;
			root.right = root.left;
			root.left = null;
		}
		if (tmp.right != null) {
			return flattenHelper(tmp.right);
		}
		return tmp;
	}
}

// 114*. Flatten Binary Tree to Linked List (June 20) Google's interview: flatten to a linked list (root in the middle)

class Solution {
	private static class Pair<T, S> {
		private T t1;
		private S t2;
		public Pair (T t1, S t2) {
			this.t1 = t1;
			this.t2 = t2;
		}
		public T getKey() {
			return this.t1;
		}
		public S getValue() {
			return this.t2;
		}
	}
	
	private Pair<TreeNode, TreeNode> flattenHelper(TreeNode root) {
		if (root == null) {
			return null;
		}
		Pair<TreeNode, TreeNode> leftTree = flattenHelper(root.left);
		if (leftTree != null) {
			leftTree.getValue().right = root;
		}
		Pair<TreeNode, TreeNode> rTree = flattenHelper(root.right);
		if (rTree == null) {
			root.right = null;
		} else {
			root.right = rTree.getKey();
		}
		TreeNode left = leftTree == null ? root : leftTree.getKey();
		TreeNode right = rTree == null ? root : rTree.getValue();
		return new Pair<TreeNode, TreeNode>(left, right);
	}
	
    public void flatten(TreeNode root) {
		if (root == null) return ;
		Pair<TreeNode, TreeNode> res = flattenHelper(root);
		// test code below
        TreeNode p = res.getKey();
        while (p != null) {
           System.out.println(p.val);
            p = p.right;
        }
		return;
	}
}

// 114. Flatten Binary Tree to Linked List (June 2020) (Root placed in the beginning)

class Solution {
	private TreeNode flattenHelper (TreeNode root) {
		if (root == null) return null;
		TreeNode leftSide = flattenHelper(root.left);
		TreeNode rightSide = flattenHelper(root.right);
		if (leftSide != null) {
			leftSide.right = root.right;
			leftSide.left = null;
			root.right = root.left;
			root.left = null;
		}
		if (rightSide != null) {
			return rightSide;
		}
		if (leftSide != null) {
			return leftSide;
		}
		return root;
	}
	
    public void flatten(TreeNode root) {
        TreeNode p = flattenHelper(root);
		// while (p != null) {
			// System.out.println(p.val);
			// p = p.right;
		// }
		return;
    }
}

// 116. Populating Next Right Pointers in Each Node (June 2020)

class Solution {
    public Node connect(Node root) {
        
    }
}

// 116. Populating Next Right Pointers in Each Node

class Solution { // Solution with extra spaces// Contradict to the requirement of the problem
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
		Queue<Node> queue0 = new LinkedList<>();
		Queue<Node> queue1 = new LinkedList<>();
		queue0.add(root);
		while (!queue0.isEmpty() || !queue1.isEmpty()) {
			while(!queue0.isEmpty()) {
				Node curNode = queue0.poll();
				if (curNode.left != null) {
					queue1.add(curNod e.left);
					queue1.add(cu  rNode.right);
				}
				if (!queue0.isEmpty()) {
					curNode.next = queue0.element();
				}
			}
			while (!queue1.isEmpty()) {
				Node curNode = queue1.poll();
				if (curNode.left != null) {
					queue0.add(curNode.left);
					queue0.add(curNode.right);
				}
				if (!queue1.isEmpty()) {
					curNode.next = queue1.element();
				}
			}
		}
		return root;
	}
}

// 116. Populating Next Right Pointers in Each Node

class Solution { // Solution with extra spaces// Contradict to the requirement of the problem
    public Node connect(Node root) {
		if (root == null) {
			return null;
		}
		connect(root, null);
		return root;
	}
	
	private void connect(Node root, Node nextSibling) {
		if (root.left != null) {
			root.left.next = root.right;
			if (nextSibling != null) {
				root.right.next = nextSibling.left;
			}
			connect(root.left, root.right);
			connect(root.right, nextSibling == null ? null:nextSibling.left);
		}
	}
}

// 116. (June 2020) 
// 用DFS遍历每个node， 一次recursion函数first完成root点的链接，其次connect root.left and root.right

class Solution {
	private void connect(Node root, Node nextSibling) {
		if (nextSibling != null) {
			root.next = nextSibling;
		}
		if (root.left != null) {
			connect(root.left, root.right);
			if (nextSibling != null) {
				connect(root.right, nextSibling.left);
			} else {
				connect(root.right, null);
			}
		}
	}
	
    public Node connect(Node root) {
		if (root == null) return null;
		connect(root, null);
		return root;
	}
}

//117. Populating Next Right Pointers in Each Node II (June 2020)

// 此题的关键在于在上一层的next指针已经连好的情况下，通过遍历上一层来连接下一层. 初始情况下首层无需连接

class Solution {
    public Node connect(Node root) {
        Node head = new Node();
		head.next = root;
		while (head.next != null) {
			Node ph = head.next; // higher level pointer
			Node pl = head; // lower level pointer
			head.next = null; // important
			while(ph != null) {
				if (ph.left != null) {
					pl.next = ph.left;
					pl = pl.next;
				}
				if (ph.right != null) {
					pl.next = ph.right;
					pl = pl.next;
				}
				ph = ph.next;
			}
		}
		return root;
    }
}


// 117. Populating Next Right Pointers in Each Node II

class Solution {
    public Node connect(Node root) {
		Node lvHead = new Node(2019, null, null, null);
		Node pn = lvHead;
		lvHead.next = root;
		while (lvHead.next != null) {
			Node curNode = lvHead.next;
			pn = lvHead;
			pn.next = null;
			while(curNode != null) {
				if (curNode.left != null) {
					pn.next = curNode.left;
					pn = pn.next;
				}
				if (curNode.right != null) {
					pn.next = curNode.right;
					pn = pn.next;
				}
				curNode = curNode.next;
			}
		}
		return root;
	}
}


// 118. Pascal's Triangle
class Solution {
    public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		
		for (int i = 1; i <= numRows; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(1);
			if (i == 1) {
				res.add(list);
			} else if (i == 2){
				list.add(1);
				res.add(list);
			} else {
				List<Integer> last_list = res.get(res.size() - 1);
				for (int j = 1; j < last_list.size(); j++) {
					list.add(last_list.get(j-1) + last_list.get(j));
				}
				list.add(1);
				res.add(list);
			}
		}
		return res;
	}
}


// 119. Pascal's Triangle II

class Solution {
    public List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<>();
		res.add(1);
		if (rowIndex == 0) {
			return res;
		}
		if (rowIndex == 1) {
			res.add(1);
			return res;
		}
		int[] arry = new int[rowIndex + 1];
		arry[0] = 1; arry[1] = 1;
		for (int i = 2; i <= rowIndex; i++) {
			int tmp = 0, lastTmp = arry[0];
			for (int j = 1; j < i; j++) {
				tmp = arry[j];
				arry[j] += lastTmp;
				lastTmp = tmp;
			}
			arry[i] = 1;
		}
		for (int i = 1; i <= rowIndex; i++) {
			res.add(arry[i]);
		}
		return res;
	}
}

// 120. (June 2020) more clean and less space used

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = triangle.get(dp.length - 1).get(i);
		}
		
		for (int row = triangle.size() - 2; row >=0 ; row--) {
			List<Integer> curList = triangle.get(row);
			for (int i = 0; i < curList.size(); i++) {
				dp[i] = Math.min(dp[i], dp[i+1]) + curList.get(i);
			}
		}
		return dp[0];
    }
}


// 120. Triangle

class Solution {
	private int getMinAdj(int[] dpArray, int indx) {
		return Math.min(dpArray[indx], dpArray[indx + 1]);
	}
	
    public int minimumTotal(List<List<Integer>> triangle) {
		List<Integer> list = triangle.get(triangle.size() - 1);
		int[] dp = new int[list.size()];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = list.get(i);
		}
		for (int i = triangle.size() - 2; i >= 0; i--) {
			list = triangle.get(i);
			int[] arry = new int[i + 1];
			for (int j = 0; j < arry.length; j++) {
				arry[j] = getMinAdj(dp, j) + list.get(j);
			}
			dp = arry;
		}
		return dp[0];
	}
}

// 121. Best Time to Buy and Sell Stock

class Solution {
    public int maxProfit(int[] prices) {
		int maxP = 0;
		int minBuyP = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; i++) {
			minBuyP = minBuyP < prices[i]? minBuyP:prices[i];
			if (prices[i] - minBuyP > maxP) {
				maxP = prices[i] - minBuyP;
			}
		}
		return maxP;
    }
}


// 121. Best time to Buy and Sell Stock (June 2020)

class Solution {
    public int maxProfit(int[] prices) {
		if (prices.length == 0) return 0;
		int cur_min = prices[0];
		int res = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] - cur_min > res) {
				res = prices[i] - cur_min;
			}
			cur_min = Math.min(prices[i], cur_min);
		}
		return res;
	}
}


// 122. Best Time to Buy and Sell Stock II

class Solution { // Time Limit Exceeded! Wrong answer!! 
	private int maxP;
	private int[] prices;
	private Map<Integer, Integer> M;
	
	private void maxProfitHelper(int start, int curP) {
		if (M.containsKey(start)) {
			if (M.get(start) >= curP) {
				return;
			}
		}
		M.putIfAbsent(start, curP);
		if (curP > maxP) {
			maxP = curP;
		}
		if (start >= prices.length - 1) {
			return;
		}
		for (int i = start; i < prices.length - 1; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				if (prices[j] <= prices[i]) {
					continue;
				}
				maxProfitHelper(j + 1, curP + prices[j] - prices[i]);
			}
		}
	}
	
    public int maxProfit(int[] prices) {
		if (prices.length <= 1) {
			return 0;
		}
		this.M = new HashMap<>();
		this.prices = prices;
		maxProfitHelper(0, 0);
		return maxP; 
	}
}

// 122. Best Time to Buy and Sell Stock II

class Solution { // One Pass O(n)
    public int maxProfit(int[] prices) {
		int maxP = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] - prices[i - 1] > 0) {
				maxp += prices[i] - prices[i-1];
			}
		}
		return maxP;
	}
}

// 123. Best Time to Buy and Sell Stock III

class Solution { // Time Limit Exceeded! Wrong answer!! 
	private int maxP;
	private int[] prices;
	
	private void maxProfitHelper(int start, int curP, boolean oneMore) {
		if (curP > maxP) {
			maxP = curP;
		}
		if (start >= prices.length - 1) {
			return;
		}
		for (int i = start; i < prices.length - 1; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				if (prices[j] <= prices[i]) {
					continue;
				}
				curP += prices[j] - prices[i];
				if (oneMore) {
					maxProfitHelper(j + 1, curP, false);
				} else {
					if (curP > maxP) {
						maxP = curP;
					}
				}
				curP -= prices[j] - prices[i];
			}
		}
	}
	
    public int maxProfit(int[] prices) {
		if (prices.length <= 1) {
			return 0;
		}
		this.prices = prices;
		maxProfitHelper(0, 0, true);
		return maxP; 
	}
}

// 124. Binary Tree Maximum Path Sum

class Solution { // 所有的Path都拥有一个最高level的顶点。 max_gain返回的是以参数node为顶点(的一条由上到下非转折路径)而获取到的最大收益。 

  int max_sum = Integer.MIN_VALUE;

  public int max_gain(TreeNode node) {
    if (node == null) return 0;

    // max sum on the left and right sub-trees of node
    int left_gain = Math.max(max_gain(node.left), 0);
    int right_gain = Math.max(max_gain(node.right), 0);

    // the price to start a new path where `node` is a highest node
    int price_newpath = node.val + left_gain + right_gain;

    // update max_sum if it's better to start a new path
    max_sum = Math.max(max_sum, price_newpath);

    // for recursion :
    // return the max gain if continue the same path
    return node.val + Math.max(left_gain, right_gain);
  }

  public int maxPathSum(TreeNode root) {
    max_gain(root);
    return max_sum;
  }
}


// 125. (June 2020)


class Solution {
	private boolean isValidCh(char c) {
		if (Character.isDigit(c)) {
			return true;
		}
		if (Character.isLetter(c)) {
			return true;
		}
		return false;
	}
	
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return ;
		for (int i = 0, j = s.length() - 1; i < j; ) {
			char a = s.charAt(i), b = s.charAt(j);
			if (!isValidCh(a)) {
				i++;
				continue;
			}
			if(!isValidCh(b)) {
				j--;
				continue;
			}
			if (Character.toLowerCase(a) != Character.toLowerCase(b)) {
				return false;
			}
			i++; j--;
		}
		return true;
    }
}


// 125. Valid Palindrome

class Solution {
	private boolean isValidCh(char c) {
		if (Character.isDigit(c)) {
			return true;
		}
		if (Character.isLetter(c)) {
			return true;
		}
		return false;
	}
	
    public boolean isPalindrome(String s) {
		if (s.length() == 0) {
			return true;
		}
		int start = 0, end = s.length() - 1;
		while (start < end) {
			while (!isValidCh(s.charAt(start))) {
				start++;
				if (start >= end) {
					return true;
				}
			}
			while(!isValidCh(s.charAt(end))) {
				end--;
				if (end <= start) {
					return true;
				}
			}
			if (Character.toLowerCase(s.charAt(start++)) != Character.toLowerCase(s.charAt(end--))) {
				return false;
			}
		}
		return true;
	}
}

// 127. Word Ladder
import javafx.util.Pair;

class Solution {
	
	private List<String> getAdjWords(Map<String, List<String>> dict, String word) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			String generic_word = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
			res.addAll(dict.getOrDefault(generic_word, new ArrayList<String>()));
		}
		return res;
	}
	
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int L = beginWord.length();
		Map<String, List<String>> dict_map = new HashMap<>();
		Map<String, Boolean> visited1 = new HashMap<>();
		Map<String, Boolean> visited2 = new HashMap<>();
		
        for (String word : wordList) {
			visited1.put(word, false);
			visited2.put(word, false);
			for (int i = 0; i < L; i++) {
				String generic_word = word.substring(0, i) + "*" + word.substring(i + 1, L);
				List<String> adjacentWords = dict_map.getOrDefault(generic_word, new ArrayList<String>());
				adjacentWords.add(word);
				dict_map.put(generic_word, adjacentWords);
			}
		}
		
		if (!visited1.containsKey(endWord)) {
			return 0;
		}
		
		Queue<Pair<String, Integer>> Q1 = new LinkedList<>();
		Queue<Pair<String, Integer>> Q2 = new LinkedList<>();
		visited1.put(beginWord, true);
		visited2.put(endWord, true); visited2.put(beginWord, false);
		Q1.add(new Pair(beginWord, 0)); Q2.add(new Pair(endWord, 0));
		
		for (int i = 0; !Q1.isEmpty() || !Q2.isEmpty(); i++) {
			while(!Q1.isEmpty()) {
				if (Q1.element().getValue() != i) {
					break;
				}
				Pair<String, Integer> p1 = Q1.poll();
				
				for (String adjacent : getAdjWords(dict_map, p1.getKey())) {
					if (visited1.get(adjacent) == true) {
						continue;
					}
					visited1.put(adjacent, true); //提前把visited设为true， 可以节省一轮循环
					if (visited2.get(adjacent) == true) {
						return i + i + 1 + 1;
					}
					Q1.add(new Pair(adjacent, i + 1));
				}
			}
			
			while(!Q2.isEmpty()) {
				if (Q2.element().getValue() != i) {
					break;
				}
				Pair<String, Integer> p2 = Q2.poll();
				for (String adjacent : getAdjWords(dict_map, p2.getKey())) {
					if (visited2.get(adjacent) == true) {
						continue;
					}
					visited2.put(adjacent, true);
					if (visited1.get(adjacent) == true) {
						return 2 * (i + 1) + 1;
					}
					Q2.add(new Pair(adjacent, i + 1));
				}
			}
		}
		return 0;
    }
}

// 127. (June 2020)

class Solution {
	private Map<String, List<String>> dict;
	
	private List<String> getNeibours(String word) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			String genericWord = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
			List<String> neibs = dict.get(genericWord);
			if (neibs != null) {
				res.addAll(neibs);
			}
		}
		return res;
	}
	
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		dict = new HashMap<>(); boolean flag = false;
		
		for (String word : wordList) {
			if (endWord.equals(word)) {
				flag = true;
			}
			for (int i = 0; i < word.length(); i++) {
				String keyWord = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
				dict.putIfAbsent(keyWord, new ArrayList<String>());
				dict.get(keyWord).add(word);
			}
		}
		
		if (!flag) return 0;
		Set<String> visited_s = new HashSet<>();
		Set<String> visited_e = new HashSet<>();
		visited_s.add(beginWord);
		visited_e.add(endWord);
		Queue<Pair<String, Integer>> Q1 = new LinkedList<>();
		Queue<Pair<String, Integer>> Q2 = new LinkedList<>();
		Q1.add(new Pair(beginWord, 0)); Q2.add(new Pair(endWord, 0));
		
		for (int i = 0; !Q1.isEmpty() || !Q2.isEmpty(); i++) {
			while(!Q1.isEmpty()) {
				if (Q1.element().getValue() != i) 
					break;
				Pair<String, Integer> p = Q1.poll();
				List<String> nexts = this.getNeibours(p.getKey());
				for (String next : nexts) {
					if (visited_s.contains(next))
						continue;
					visited_s.add(next);
					Q1.add(new Pair(next, i + 1));
					if (visited_e.contains(next)) {
						return 2 * (i + 1);
					}
				}
			}
			
			while (!Q2.isEmpty()) {
				if (Q2.element().getValue() != i) {
					break;
				}
				Pair<String, Integer> p = Q2.poll();
				List<String> nexts = this.getNeibours(p.getKey());
				for (String next : nexts) {
					if (visited_e.contains(next))
						continue;
					visited_e.add(next);
					Q2.add(new Pair(next, i + 1));
					if (visited_s.contains(next)) {
						return 1 + 2 * (i + 1);
					}
				}
			}
		}
		return 0;
	}
}

// 128. Longest Consecutive Sequence

class Solution { // method 1
    public int longestConsecutive(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int MaxLength = 0;
		Map<Integer, Integer> M = new HashMap<>();
		for (int num : nums) {
			M.put(num, 0);
		}
		
		for (int num : nums) {
			if (!M.containsKey(num)) {
				continue;
			}
			if (M.get(num) > 0) {
				continue;
			}
			int i;
			for (i = 0; M.containsKey(i + num); i++) {
				int curValue = i + num;
				if (M.get(curValue) > 0) {
					i += M.get(curValue);
					M.remove(curValue);
					break;
				} else {
					if (i > 0) {
						M.remove(num + i);
					}
				}
			}
			M.put(num, i);
			MaxLength = Math.max(MaxLength, i);
		}
		return MaxLength;
	}
}


// 128 (June)

class Solution {
    public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> M = new HashMap<>();
		if (nums.length == 0) return 0;
		for (int n : nums) {
			M.putIfAbsent(n, 0);
		}
		int max = 0;
		for (int num : nums) {
			if (!M.containsKey(num)) {
				continue;
			}
			if (M.get(num) > 0) {
				continue;
			}
			int count = 0;
			while (M.containsKey(num + count)) {
				int curVal = num + count;
				if (M.get(curVal) > 0) {
					count += M.get(curVal);
					M.remove(curVal);
					continue;
				} else if (curVal != num){
					M.remove(curVal);
				}
				count++;
			}
			M.put(num, count);
			max = Math.max(max, M.get(num));
		}
		return max;
	}
}

// 129. Sum root to leaf numbers (June)

class Solution {
	private int res;
	
	private void sumNumbersHelper (TreeNode root, int curValue) {
		curValue = root.val + curValue * 10;
		if (root.left == null && root.right == null) {
			res += curValue;
			return;
		}
		if (root.left != null) {
			sumNumbersHelper(root.left, curValue);
		}
		if (root.right != null) {
			sumNumbersHelper(root.right, curValue);
		}
	}
	
    public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		this.res = 0;
		sumNumbersHelper(root, 0);
		return res;
	}
}



// 129. Sum Root to Leaf Numbers
class Solution {
	private List<Integer> res;
	
	solution(){
		this.res = new ArrayList<>();
	}
	
	private void sumNumbersHelper(TreeNode root, int curSum) {
		curSum = curSum * 10 + root.val;
		if (root.left == null && root.right == null) {
			res.add(curSum);
			return;
		}
		if (root.left != null) {
			sumNumbersHelper(root.left, curSum);
		}
		if (root.right != null) {
			sumNumbersHelper(root.right, curSum);
		}
	}
    
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int sum = 0;
		sumNumbersHelper(root, 0);
		for (Integer val : this.res) {
			sum += val;
		}
		return sum;
	}
}

// 130. Surrounded Regions (June 2020)

class Solution {
	private boolean[][] visited;
	
	private void traversal(char[][] board, int row, int col) {
		visited[row][col] = true;
		int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		for (int[] direct : directions) {
			int x = row + direct[0];
			int y = col + direct[1];

			if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
				if (board[x][y] == 'X' || visited[x][y]) continue;
				traversal(board, x, y);
			}
		}
	}
	
    public void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0) {
			return ;
		}
		visited = new boolean[board.length][board[0].length];
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (r != 0 && c != 0 && r != board.length - 1 && c != board[0].length - 1) {
					continue;
				}
				if (board[r][c] == 'X' || visited[r][c]) {
					continue;
				}
				traversal(board, r, c);
			}
		}
		
		for (int r = 1; r < board.length - 1; r++) {
			for (int c = 1; c < board[0].length - 1; c++) {
				if (board[r][c] == 'O' && !visited[r][c]) {
					board[r][c] = 'X';
				}
			}
		}
	}
}

// 130. Surrounded Regions

class Solution {
	private char[][] board;
	private boolean[][] visited;
	
	private void traversal(int r, int c) {
		int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		visited[r][c] = true;
		for (int i = 0; i < direction.length; i++) {
			int x = r + direction[i][0];
			int y = c + direction[i][1];
			if (!(x >= 0 && x < board.length && y >= 0 && y < board[0].length)){
				continue;
			}
			if (!visited[x][y] && board[x][y] == 'O'){
				traversal(x, y);
			}
		}
	}
	
    public void solve(char[][] board) {
		if (board.length == 0) {
			return;
		}
		this.board = board;
		this.visited = new boolean[board.length][board[0].length];
		int[] rows = new int[]{0, board.length - 1};
		
		for (int row : rows) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[row][j] == 'O' && !visited[row][j]) {
					traversal(row, j);
				}
			}
		}
		int[] colums = new int[]{0, board[0].length - 1};
		for (int colum : colums) {
			for (int i = 0; i < board.length; i++) {
				if (!visited[i][colum] && board[i][colum] == 'O') {
					traversal(i, colum);
				}
			}
		}
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[0].length - 1; j++) {
				if (!visited[i][j] && board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}
}

// 131. Palindrome Partitioning (June 2020)

class Solution {
	private boolean[][] dp;
	private List<List<String>> res;
	
	private void partitionHelper(String s, List<String> preList, int start) {
		if (start >= s.length()) {
			res.add(new ArrayList<String>(preList));
			return;
		}
		for (int i = start; i < s.length(); i++) {
			if (start == 0 && i == s.length() - 1) {
				System.out.println(dp[start][i]);
			}
			if (dp[start][i] == true) {
				preList.add(s.substring(start, i + 1));
				partitionHelper(s, preList, i + 1);
				preList.remove(preList.size() - 1);
			}
		}
	}
	
    public List<List<String>> partition(String s) {
		res = new ArrayList<>();
		if (s.length() == 0) 
			return res;
        dp = new boolean[s.length()][s.length()];
		for (int j = 0; j < s.length(); j++) {
			for (int i = 0; i <= j; i++) {
				if (i == j) {
					dp[i][j] = true;
				}
				else if (s.charAt(i) != s.charAt(j)) {
					dp[i][j] = false;
				}
				else if (j == i + 1) {
					dp[i][j] = true;
				}
				else if (dp[i + 1][j - 1] == true) {
					dp[i][j] = true;
				}
				else {
					dp[i][j] = false;
				}
			}
		}
		partitionHelper(s, new ArrayList<String>(), 0);
		return res;
    }
}


// 131. Palindrome Partitioning: solution with recursion

class Solution {
	List<List<String>> res;
	
    public List<List<String>> partition(String s) {
		this.res = new ArrayList<>();
		if (s.length() == 0) {
			return res;
		}
		partition(s, new ArrayList<String>(), 0);
		return res;
	}
	
	private boolean isPalindrome(String s) {
		if (s.length() <= 1){
			return true;
		}
		int start = 0, end = s.length() - 1;
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++; end--;
		}
		return true;
	}
	
	private void partition(String s, List<String> preList, int start) {
		if (start >= s.length()) {
			res.add(new ArrayList<String>(preList));
			return;
		}
		for (int i = start + 1; i <= s.length(); i++) {
			String newStr = s.substring(start, i);
			if (!isPalindrome(newStr)) {
				continue;
			}
			preList.add(newStr);
			partition(s, preList, i);
			preList.remove(preList.size() - 1);
		}
	}
}

// 133. Clone Graph

class Solution {
	private Map<Node, Node> visited;
	
	private Node cloneGraphHelper(Node node) {
		Node cloned = new Node(node.val, new ArrayList<Node>());
		visited.put(node, cloned);
		for (Node nb : node.neighbors) {
			if (!visited.containsKey(nb)) {
				cloneGraphHelper(nb);
			}
			cloned.neighbors.add(visited.get(nb));
		}
		return cloned;
	}
	
    public Node cloneGraph(Node node) {
		visited = new HashMap<>();
		return cloneGraphHelper(node);
	}
}

// 133. Clone Graph (June 2020) LeetCode changed test cases since last submit

class Solution {
	private Map<Node, Node> M = new HashMap<>();
	
	private Node cloneGraphHelper (Node node) {
		Node cloned = new Node(node.val);
		M.put(node, cloned);
		for (Node n : node.neighbors) {
			if (!M.containsKey(n)) {
				cloned.neighbors.add(cloneGraphHelper(n));
			} else {
				cloned.neighbors.add(M.get(n));
			}
		}
		return cloned;
	}
	
    public Node cloneGraph(Node node) {
		if (node == null) return null;
        return cloneGraphHelper(node);
    }
}


// 134. Gas Station (June 2020)


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
		int[] difs = new int[gas.length];
		for (int i = 0; i < gas.length; i++) {
			difs[i] = gas[i] - cost[i];
		}
		int i;
		for (i = 0; i < difs.length;) {
			int j = 0, curGas = 0;
			for (; j < difs.length; j++) {
				int curPos = (i + j) % difs.length;
				if (curGas + difs[curPos] < 0) {
					if (curPos < i)
						return -1;
					i = curPos + 1;
					break;
				} 
				curGas += difs[curPos];
			}
			if (j == difs.length) return i;
		}
		return -1;
	}
}

// 134. Gas Station

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
		int start = 0;
		while (start < gas.length) {
			int sum = 0;
			for (int i = 0; i < gas.length; i++) {
				int curPos = (start + i) % gas.length;
				int offset = gas[curPos] - cost[curPos];
				sum += offset;
				if (sum < 0){
					if (curPos < start) {
						return -1;
					}
					start = curPos + 1;
					break;
				}
				if (i == gas.length - 1) {
					return start;
				}
			}
		}
		return -1;
	}
}


// 136. Single Number

class Solution {
    public int singleNumber(int[] nums) {
		Set<Integer> myset = new HashSet<>();
		for (int nu : nums) {
			if (myset.contains(nu)) {
				myset.remove(nu);
			} else {
				myset.add(nu);
			}
		}
		return myset.iterator().next();
    }
}

// 137. Single Number II

class Solution {
    public int singleNumber(int[] nums) {
		Set<Integer> S = new HashSet<>();
		long sumS = 0, sum = 0;
		for (int nu : nums) {
			sum += nu;
			if (!S.contains(nu)) {
				S.add(nu);
				sumS += nu;
			}
		}
		return (int) ((3 * sumS - sum) / 2);
	}
}


// 138. Copy List with Random Pointer (June 2020)

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
		Map<Node, Node> M = new HashMap<>();
		Node nHead = new Node(0);
		nHead.next = head;
		Node p = nHead, q = nHead;
		while(p.next != null) {
			Node copied = new Node(p.next.val);
			M.put(p.next, copied);
			p = p.next;
			q.next = copied;
			q = q.next;
		}
		q.next = null;
		p = head; q = nHead.next;
		while (p != null){
			q.random = p.random == null ? null : M.get(p.random);
			p = p.next;
			q = q.next;
		}
		return nHead.next;
    }
}


// 138. Copy List with Random Pointer

class Solution {
    public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		Map<Node, Node> M = new HashMap<>();
		copyRandomListHelper(M, head);
		M.put(null, null);
		Node p = head;
		while (p != null) {
			Node curNode = M.get(p);
			curNode.next = M.get(p.next);
			curNode.random = M.get(p.random);
			p = p.next;
		}
		return M.get(head);
	}
	
	private void copyRandomListHelper(Map<Node, Node> M, Node s_head) {
		while(s_head != null) {
			Node newNode = new Node(s_head.val, null, null);
			M.put(s_head, newNode);
			s_head = s_head.next;
		}
	}
}

// 139. Word Break (June 2020) Improved performance by using boolean as cache and other aspects

class Solution {
	private boolean[] visited; // the memory;
	
	private boolean wordBreakHelper(String s, int startPos, List<String> wordDict) {
		if (startPos >= s.length()) {
			return true;
		}
		if (visited[startPos]) {
			return false;
		}
		for (String word : wordDict) {
			if (!s.startsWith(word, startPos)) {
				continue;
			}
			if (wordBreakHelper(s, startPos + word.length(), wordDict)) {
				return true;
			}
		}
		visited[startPos] = true;
		return false;
	}
	
    public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) {
			return false;
		}
		if (wordDict.size() == 0) {
			return false;
		}
		visited = new boolean[s.length()];
		return wordBreakHelper(s, 0, wordDict);
	}
}


// 139. Word Break
class Solution {
	private Set<String> wordDict;
	private String s;
	private Map<Integer, Boolean> memory; // memory.get(start) == true means former tried but failed to match.
	
	Solution() {
		wordDict = new HashSet<>();
		memory = new HashMap<>();
	}
	
    public boolean wordBreak(String s, List<String> wordDict) {
		if (s.length() == 0 || wordDict.size() == 0) {
			return false;
		}
		for (String word : wordDict) {
			this.wordDict.add(word);
		}
		this.s = s;
		return wordBreakHelper(0);
	}
	
	private boolean wordBreakHelper(int start) {
		if (start >= s.length()) {
			return true;
		}
		for (int i = start + 1; i <= s.length(); i++) {
			if (wordDict.contains(s.substring(start, i))) {
				if (memory.containsKey(i) && memory.get(i)) {
					continue;
				}
				boolean res = wordBreakHelper(i);
				if (res) {
					return true;
				} else {
					memory.put(i, true);
				}
			}
		}
		return false;
	}
}

// 140 (June 2020) Word Break II

class Solution {
	private Map<Integer, List<List<Integer>>> mem;
	private Map<String, Integer> wordMap;
	
	Solution () {
		mem = new HashMap<>();
		wordMap = new HashMap<>();
	}
	
	private boolean wordBreakHelper(String s, int startPos) {
		if (startPos >= s.length()) {
			return true;
		}
		if (mem.containsKey(startPos)) {
			if (mem.get(startPos).size() == 0) {
				return false;
			}
			return true;
		}
		mem.put(startPos, new ArrayList<List<Integer>>());
		for (String word : wordMap.keySet()) {
			if (!s.startsWith(word, startPos)) {
				continue;
			}
			if (!wordBreakHelper(s, startPos + word.length())) {
				continue;
			}
			int wordIndx = wordMap.get(word);
			List<List<Integer>> nextRes = mem.get(startPos + word.length());
			if (nextRes == null) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(wordIndx);
				mem.get(startPos).add(list);
				continue;
			}
			//mem.get(startPos).addAll(nextRes);
			List<List<Integer>> res = mem.get(startPos);
			for (List<Integer> L : nextRes) {
				List<Integer> l = new ArrayList<>();
				l.add(wordIndx);
				l.addAll(L);
				res.add(l);
			}
		}
		return mem.get(startPos).size() == 0 ? false : true;
	}
	
    public List<String> wordBreak(String s, List<String> wordDict) {
		if (s.length() == 0) {
			return new ArrayList<String>();
		}
		int indx = 0;
		for (String word : wordDict) {
			wordMap.put(word, indx++);
		}
		wordBreakHelper(s, 0);
		
		List<String> res = new ArrayList<>();
		List<List<Integer>> resIndices = mem.get(0);
		for (List<Integer> L : resIndices) {
			StringBuilder sbd = new StringBuilder();
			for (int i = 0; i < L.size(); i++) {
				if (i > 0) {
					sbd.append(' ');
				}
				sbd.append(wordDict.get(L.get(i)));
			}
			res.add(sbd.toString());
		}
		return res;
	}
}

// 141. Linked List Cycle (June 2020)

public class Solution {
    public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode ps = head, pf = head;
		while (pf != null && pf.next != null) {
			pf = pf.next.next;
			ps = ps.next;
			if (pf == ps && ps != null) {
				return true;
			}
		}
		return false;
	}
}


// 141. Linked List Cycle

public class Solution {
    public boolean hasCycle(ListNode head) {
		ListNode p1 = head, p2 = head;
		
		while(p1 != null && p2 != null) {
			if (p1.next == null) {
				return false;
			}
			p1 = p1.next.next;
			p2 = p2.next;
			if (p1 == p2) {
				return true;
			}
		}
		return false;
	}
}


// 142. Linked List Cycle II (June 2020)

public class Solution {
    public ListNode detectCycle(ListNode head) {
		ListNode p1 = head, p2 = head;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2 && p1 != null) {
				break;
			}
		}
		if (p2 == null || p2.next == null) {
			return null;
		}
		p1 = head;
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}
}


// 142. Linked List Cycle II

public class Solution {
    public ListNode detectCycle(ListNode head) {
		ListNode p1 = head, p2 = head;
		while(p1 != null && p2 != null) {
			if (p1.next == null) {
				return null;
			}
			p1 = p1.next.next;
			p2 = p2.next;
			if (p1 == p2) {
				break;
			}
		}
		p1 = head;
		while(p1 != null && p2 != null){
			if (p1 == p2) {
				return p1;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		return null;
	}
}

// 143. Reorder List (June 2020) without using extra memory

class Solution {
    public void reorderList(ListNode head) {
		if (head == null) return ;
		// Step1: find the middle of the list
        ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		//Step2: reverse the second part of the list
		ListNode rhead = slow, p = slow;
		while (p != null && p.next != null) {
			ListNode q = p.next;
			p.next = p.next.next;
			q.next = rhead;
			rhead = q;
		}
		// rhead becomes the head, and slow becomes the tail of the second part list
		//step3: merge the two lists
		p = head;
		while (p != slow) {
			ListNode q = p.next;
			p.next = rhead;
			ListNode t = rhead.next;
			rhead.next = q;
			p = q;
			rhead = t;
		}
		p.next = null;
    }
}

// 143. Reorder List

class Solution {
    public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		Stack<ListNode> S = new Stack<>();
		ListNode p = head;
		while(p != null) {
			S.push(p);
			p = p.next;
		}
		p = head;
		int count = S.size();
		for (int i = 0; i < count / 2; i++) {
			ListNode other_node = S.pop();
			other_node.next = p.next;
			p.next = other_node;
			p = other_node.next;
		}
		p.next = null;
	}
}

// 144. Binary Tree Preorder Traversal (June 2020) The iterative way

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
		Stack<TreeNode> S = new Stack<>();
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		S.push(root);
		while (!S.isEmpty()) {
			TreeNode node = S.pop();
			res.add(node.val);
			if (node.right != null) {
				S.push(node.right);
			}
			if (node.left != null) {
				S.push(node.left);
			}
		}
		return res;
	}
}

// 144. Binary Tree Preorder Traversal

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		preorderTraversalHelper(res, root);
		return res;
	}
	
	private void preorderTraversalHelper(List<Integer> res, TreeNode root) {
		if (root == null) return;
		res.add(root.val);
		preorderTraversalHelper(res, root.left);
		preorderTraversalHelper(res, root.right);
	}
}

// 145. Binary Tree Postorder Traversal (June 2020)

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
		Stack<TreeNode> S = new Stack<>();
		Stack<TreeNode> S1 = new Stack<>();
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		S.push(root);
		while (!S.isEmpty()) {
			TreeNode node = S.pop();
			if (node.left != null) {
				S.push(node.left);
			}
			if (node.right != null) {
				S.push(node.right);
			}
			S1.push(node);
		}
		while (!S1.isEmpty()) {
			TreeNode node = S1.pop();
			res.add(node.val);
		}
		return res;
	}
}

// 146 LRU cache (June 2020) be careful when removing node from doubly linkedlist 

class LRUCache {
	int capacity;
	private DLinkedList dlist;
	private Map<Integer, DLinkedListNode> M;
	
	static class DLinkedListNode {
		int val;
		int key;
		DLinkedListNode next;
		DLinkedListNode prev;
		DLinkedListNode (int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
	
	static class DLinkedList {
		DLinkedListNode head;
		DLinkedListNode tail;
		DLinkedList (DLinkedListNode head, DLinkedListNode tail) {
			this.head = head;
			this.tail = tail;
		}
	}
	
	private void movetoTail(DLinkedListNode node) {
		if (dlist.tail == node) {
			return;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		dlist.tail.next = node;
		node.prev = dlist.tail;
		node.next = null;
		dlist.tail = node;
	}
	
	private void addtoTail(DLinkedListNode node) {
		dlist.tail.next = node;
		node.prev = dlist.tail;
		dlist.tail = node;
		if (M.size() > capacity) {
			M.remove(dlist.head.next.key);
			dlist.head.next = dlist.head.next.next;
			dlist.head.next.prev = dlist.head;
		}
	}
	
    public LRUCache(int capacity) {
		M = new HashMap<>();
		DLinkedListNode node = new DLinkedListNode(0, 0);
		dlist = new DLinkedList(node, node);
		this.capacity = capacity;
    }
    
    public int get(int key) {
		if (!M.containsKey(key)) {
			return -1;
		}
		DLinkedListNode tNode = M.get(key);
		movetoTail(tNode);
		return tNode.val;
	}
    
    public void put(int key, int value) {
        if (M.containsKey(key)) {
			DLinkedListNode node = M.get(key);
			node.val = value;
			movetoTail(node);
		} else {
			DLinkedListNode newNode = new DLinkedListNode(key, value);
			M.put(key, newNode);
			addtoTail(newNode);
		}
    }
}


// 146. LRU Cache

class LRUCache {
	private int capacity;
	private DlinkedListNode head, tail;
	private Map<Integer, DlinkedListNode> M;
	
	private class DlinkedListNode {
		int key;
		int val;
		DlinkedListNode next;
		DlinkedListNode prev;
	}
	
	private void addNode(int key, int val) {
		DlinkedListNode node = new DlinkedListNode();
		node.key = key; node.val = val;
		this.tail.next = node;
		node.prev = this.tail;
		tail = tail.next;
		tail.next = null;
		M.put(key, tail);
	}
	
	private void removeHeadNode() {
		M.remove(head.next.key);
		head.next = head.next.next;
		head.next.prev = head;
	}
	
	private void moveTotail(DlinkedListNode node, Integer val) {
		if (val != null) {
			node.val = val;
		}
		if (node == tail) {
			return;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		this.tail.next = node;
		node.prev = this.tail;
		tail = tail.next;
		tail.next = null;
	}
	
    public LRUCache(int capacity) {
		head = new DlinkedListNode();
		tail = head;
		this.capacity = capacity;
		this.M = new HashMap<>();
	}
    
    public int get(int key) {
		if (!M.containsKey(key)) {
			return -1;
		}
		DlinkedListNode targetNode = M.get(key);
		moveTotail(targetNode, null);
		return targetNode.val;
	}
    
    public void put(int key, int value) {
		if (!M.containsKey(key)) {
			addNode(key, value);
			if (M.size() > this.capacity) {
				removeHeadNode();
			}
		} else {
			moveTotail(M.get(key), value);
		}
	}
}


// 147. Insertion Sort List

class Solution {
    public ListNode insertionSortList(ListNode head) {
		if (head == null) return null;
		ListNode realHead = new ListNode(2019);
		realHead.next = head;
		
		ListNode p = head.next;
		head.next = null;
		while (p != null) {
			ListNode q = p.next;
			partial_insert(realHead, p);
			p = q;
		}
		return realHead.next;
	}
	
	private void partial_insert(ListNode head, ListNode targetNode) {
		ListNode p = head;
		while(p.next != null) {
			if (p.next.val < targetNode.val) {
				p = p.next;
				continue;
			}
			targetNode.next = p.next;
			p.next = targetNode;
			return;
		}
		p.next = targetNode;
		targetNode.next = null;
	}
}


// 148. Sort List

class Solution { // use merge sort
    public ListNode sortList(ListNode head) {
		
	}
}


// 150. Evaluate Reverse Polish Notation (June 2020)

class Solution {
	private Stack<Integer> S;
	private boolean isNumber(String s) {
		if (Character.isDigit(s.charAt(0))) {
			return true;
		}
		if (s.charAt(0) == '-' && s.length() > 1) {
			return true;
		}
		return false;
	}
	
	private int doOperation(String s) {
		int operand2 = S.pop();
		int operand1 = S.pop();
		char c = s.charAt(0);
		if (c == '+') {
			return operand1 + operand2;
		}
		if (c == '-') {
			return operand1 - operand2;
		}
		if (c == '/') {
			return operand1 / operand2;
		}
		return operand1 * operand2;
	}
	
    public int evalRPN(String[] tokens) {
		S = new Stack<>();
		for (String s : tokens) {
			if (isNumber(s)) {
				S.push(Integer.valueOf(s));
			} else {
				int res = doOperation(s);
				S.push(res);
			}
		}
		return S.pop();
	}
}

// 150. Evaluate Reverse Polish Notation
class Solution {
	private Set<Character> S;
	
	private boolean isOperator(String s) {
		if (S.contains(s.charAt(0)) && s.length() == 1) {
			return true;
		}
		return false;
	}
	
	private int doOperation(int a, int b, String operator) {
		char op = operator.charAt(0);
		if (op == '+') {
			return a + b;
		}
		if (op == '-') {
			return a - b;
		}
		if (op == '*') {
			return a * b;
		}
		if (op == '/') {
			return a / b;
		}
		return 0;
	}
	
    public int evalRPN(String[] tokens) {
		S = new HashSet<>();
		Stack<Integer> operandS = new Stack<>();
		
		char[] operators = new char[]{'+', '-', '*', '/'};
		for (char c : operators) {
			S.add(c);
		}
		for (String token : tokens) {
			if (!isOperator(token)) {
				operandS.push(Integer.parseInt(token));
			} else {
				int op2 = operandS.pop();
				int op1 = operandS.pop();
				operandS.push(doOperation(op1, op2, token));
			}
		}
		return operandS.pop();
	}
 }

// 151. Reverse Words in a String (June 2020)

class Solution {
    public String reverseWords(String s) {
		List<String> res = new ArrayList<>();
		int start = -1;
		s += " ";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				if (start == -1) {
					continue;
				}
				String word = s.substring(start, i);
				res.add(word);
				start = -1;
			} else {
				if (start == -1) {
					start = i;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = res.size() - 1; i >= 0; i--) {
			sb.append(res.get(i));
			if (i > 0) {
				sb.append(' ');
			}
		}
		return sb.toString();
	}
}



// 151. Reverse Words in a String

class Solution {
    public String reverseWords(String s) {
		if (s == null) {
			return s;
		}
		StringBuilder res = new StringBuilder();
		StringBuilder sbd = new StringBuilder();
		
		for (int i = s.length() - 1; i >= -1; i--) {
			char letter = ' ';
			if (i >= 0) {
				letter = s.charAt(i);
			}
			if (i == -1 || letter == ' ') {
				if (sbd.length() > 0) {
					for (int j = sbd.length() - 1; j >=0; j--) {
						res.append(sbd.charAt(j));
					}
					res.append(' ');
					sbd.setLength(0);
				}
			} else {
				sbd.append(letter);
			}
		}
		if (res.length() > 0) {
			res.setLength(res.length() - 1);
		}
		return res.toString();
	}
}

// 152. Maximum Product Subarray
class Solution {
    public int maxProduct(int[] nums) {
		int logic_min = 1, logic_max = 1, global_max = Integer.MIN_VALUE;
		for (int i : nums) {
			if (i > 0) {
				logic_max = logic_max * i;
				logic_min = logic_min * i;
				global_max = Math.max(global_max, logic_max);
			} else if (i < 0) {
				int tmp = logic_max;
				logic_max = logic_min * i;
				global_max = Math.max(global_max, logic_max); // notice the position of the sentence
				logic_max = Math.max(logic_max, 1);
				logic_min = tmp * i;
			} else {
				logic_max = 1; logic_min = 1;
				global_max = Math.max(global_max, 0);
			}
		}
		return global_max;
	}
}

// 152. Maximum Product Subarray (June 2020)

class Solution {
    public int maxProduct(int[] nums) {
		int logic_min = 1, logic_max = 1, global_max = Integer.MIN_VALUE;
		// logic_min and logic_max correspond to 以
		for (int i : nums) {
			if (i > 0) {
				logic_min = logic_min * i;
				logic_max = logic_max * i;
				global_max = Math.max(global_max, logic_max);
			} else if (i < 0) {
				int logic_min_prev = logic_min;
				logic_min = logic_max * i;
				logic_max = logic_min_prev * i;
				global_max = Math.max(global_max, logic_max);
				logic_max = Math.max(1, logic_max);
			} else {
				logic_max = 1; logic_min = 1;
				global_max = Math.max(0, global_max);
			}
		}
		return global_max;
	}
}

// 153. Find Minimum in Rotated Sorted Array (June 2020)

class Solution {
    public int findMin(int[] nums) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			if (nums[left] < nums[right]) {
				return nums[left];
			}
			int mid = left + (right - left) / 2;
			if (nums[mid] > nums[left]) {
				left = mid + 1;
			} else if (nums[mid] < nums[left]){
				right = mid;
			} else {
				return Math.min(nums[left], nums[right]);
			}
		}
		return nums[left];
	}
}


// 153. Find Minimum in Rotated Sorted Array

class Solution {
    public int findMin(int[] nums) {
		if (nums[nums.length - 1] >= nums[0]) {
			return nums[0];
		}
		int start = 0, end = nums.length - 1;
		while(start <= end) {
			if (start == end) {
				return nums[start];
			}
			int mid = (start + end) / 2;
			if (nums[mid] > nums[0]) {
				start = mid + 1;
			} else if (nums[mid] == nums[0]) {
				return nums[mid + 1];
			}
			else {
				end = mid;
			}
		}
		return -1;
	}
}

// 155. Min Stack (June 2020)

class MinStack {
	Stack<Integer> S1;
	Stack<Integer> S2;
	
    /** initialize your data structure here. */
    public MinStack() {
        S1 = new Stack<>();
		S2 = new Stack<>();
    }
    public void push(int x) {
        S1.push(x);
		if (S2.isEmpty() || S2.peek() >= x) {
			S2.push(x);
		}
    }
    public void pop() {
        int n = S1.pop();
		if (S2.peek() == n) {
			S2.pop();
		}
    }
    public int top() {
        return S1.peek();
    }
    public int getMin() {
        return S2.peek();
    }
}


// 155. Min Stack
class MinStack {
    /** initialize your data structure here. */
	private LinkedList<Integer> stack1;
	private LinkedList<Integer> stack2;
	
    public MinStack() {
        stack1 = new LinkedList<>();
		stack2 = new LinkedList<>();
    }
    
    public void push(int x) {
        stack1.addFirst(x);
		if (stack2.isEmpty()) {
			stack2.addFirst(x);
		} else if (x <= stack2.element()) {
			stack2.addFirst(x);
		}
    }
    
    public void pop() {
		if (!stack1.isEmpty()) {
			if (stack1.element().intValue() == stack2.element().intValue()) {
				stack2.remove();
			}
			stack1.remove();
		}
    }
    
    public int top() {
		if (!stack1.isEmpty()) {
			return stack1.element();
		}
		return 0;
	}
    
    public int getMin() {
        if (!stack2.isEmpty()) {
			return stack2.element();
		}
		return 0;
    }
}

// 157. Read N Characters Given Read4 (June 2020)

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
		int readBytes = 0;
		char[] tmp = new char[4];
		while (readBytes < n) {
			int cur_bytes = read4(tmp);
			if (cur_bytes == 0) {
				break;
			}
			int real_bytes = Math.min(cur_bytes, n - readBytes);
			for (int i = 0; i < real_bytes; i++) {
				buf[i + readBytes] = tmp[i];
			}
			readBytes += real_bytes;
		}
		return readBytes;
	}
}



// 158. Read N Characters Given Read4 II - Call multiple times 上一次调用的buffer_cache中可能残余数据

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
	private char[] buf_cache;
	private int w_pos;
	private int cache_start_pos;
	private int cache_end_pos;
	
	Solution() {
		buf_cache = new char[4];
		cache_start_pos = 0;
		cache_end_pos = 0;
	}
	
	private int myWrite(char[] buf, int remain_bytes) {
		int to_wt_bytes = Math.min(remain_bytes, cache_end_pos - cache_start_pos);
		
		for (int i = 0; i < to_wt_bytes; i++) {
			buf[w_pos++] = buf_cache[cache_start_pos++];
		}
		return to_wt_bytes;
	}
	
    public int read(char[] buf, int n) {
		int remain_bytes = n;
		boolean endOfFile = false;
		w_pos = 0;
		while(remain_bytes > 0) {
			if (cache_end_pos > cache_start_pos) {
				int wrted_bytes = myWrite(buf, remain_bytes);
				remain_bytes -= wrted_bytes;
			}
			if (remain_bytes <= 0) {
				break;
			}
			if (endOfFile) {
				break;
			}
			int rd_bytes = super.read4(buf_cache);
			if (rd_bytes < 4) {
				endOfFile = true;
			}
			cache_start_pos = 0;
			cache_end_pos = rd_bytes;
		}
		return w_pos;
	}
}

// 159. Longest Substring with At Most Two Distinct Characters (June 2020)

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> M = new HashMap<>();
		int left = 0, right = 0, maxLen = 0;
		while (right < s.length()) {
			char c = s.charAt(right);
			if (!M.containsKey(c)) {
				M.put(c, 1);
			} else {
				M.put(c, 1 + M.get(c));
			}
			if (M.size() <= 2) {
				maxLen = Math.max(right - left + 1, maxLen);
			} else {
				while (left < right) {
					char b = s.charAt(left++);
					if (M.get(b) <= 1) {
						M.remove(b);
						break;
					} else {
						M.put(b, M.get(b) - 1);
					}
				}
			}
			right++;
		}
		return maxLen;
    }
}


// 160. Intersection of Two Linked Lists (June 2020)

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode p1 = headA, p2 = headB;
		if (headA == null || headB == null) {
			return null;
		}
		while (p1 != null && p2 != null) {
			if (p1 == p2) {
				return p1;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		int difs = 0;
		ListNode pcur = p1 == null ? p2 : p1;
		while (pcur != null) {
			pcur = pcur.next;
			difs++;
		}
		if (p1 == null) {
			p1 = headA;
			p2 = headB;
		} else {
			p1 = headB;
			p2 = headA;
		}
		while (difs > 0) {
			p2 = p2.next;
			difs--;
		}
		while (p1 != null) {
			if (p1 == p2) {
				return p1;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		return null;
	}
}


// 160. Intersection of Two Linked Lists

public class Solution {
	
	private int getLinkedListLength(ListNode head) {
		if (head == null) {
			return 0;
		}
		int count = 0;
		while(head != null) {
			count++;
			head = head.next;
		}
		return count;
	}
	
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		int len1 = getLinkedListLength(headA);
		int len2 = getLinkedListLength(headB);
		while (len1 > len2) {
			headA = headA.next;
			len1--;
		}
		while (len2 > len1) {
			headB = headB.next;
			len2--;
		}
		
		while (len1-- > 0) {
			if (headA == headB) {
				return headA;
			}
			headA = headA.next;
			headB = headB.next;
		}
		return null;
	}
}

// 161. One Edit Distance (June 2020)

class Solution {
    public boolean isOneEditDistance(String s, String t) {
		if (s.equals(t)) {
			return false;
		}
		int len1 = s.length(), len2 = t.length();
		boolean used = false;
		if (Math.abs(len1 - len2) > 1) {
			return false;
		}
		// int i, j;
		for (int i = 0, j = 0; i < s.length() && j < t.length(); i++, j++) {
			if (s.charAt(i) != t.charAt(j)) {
				if (used) {
					return false;
				}
				used = true;
				if (len1 > len2) {
					j--;
				} else if (len1 < len2) {
					i--;
				}
			}
		}
		if (used) {
			return true;
		}
		if (len1 == len2) {
			return false;
		}
		return true;
	}
}


// 161. One Edit Distance

class Solution {
    public boolean isOneEditDistance(String s, String t) {
		int len_s = s.length(), len_t = t.length();
		if (Math.abs(len_s - len_t) > 1) {
			return false;
		}
		boolean oneEdit = false;
		for (int i = 0, j = 0; i < len_s && j < len_t; i++, j++) {
			if (s.charAt(i) == t.charAt(j)) {
				continue;
			}
			if (oneEdit) {
				return false;
			}
			if (len_s > len_t) {
				j--;
			} else if (len_s < len_t) {
				i--;
			}
			oneEdit = true;
		}
		if (!oneEdit && len_s == len_t) {
			return false;
		}
		return true;
	}
}

// 162. Find Peak Element (June 2020)

class Solution {
    public int findPeakElement(int[] nums) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < nums[mid + 1]) {
				left = mid + 1;
			} else {
				if (mid == 0) {
					return 0;
				}
				if (nums[mid] > nums[mid - 1]) {
					return mid;
				}
				right = mid - 1;
			}
		}
		return left;
	}
}

// 163. Missing Ranges (June 2020)
// 注意nums为空的情况, 以及比较的时候不要用加减，可能导致integer越界
class Solution { 
	private String getCurString(int low, int up) {
		String s = String.valueOf(low);
		if (up > low) {
			s = s.concat("->" + String.valueOf(up));
		}
		return s;
	}
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int low = lower;
		List<String> res = new ArrayList<>();
		if (nums.length == 0) {
			res.add(getCurString(lower, upper));
			return res;
		}
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i-1]) {
				continue;
			}
			if (nums[i] > low) {
				String s = getCurString(low, nums[i] - 1);
				res.add(s);
			}
			low = nums[i] + 1;
		}
		int last = nums[nums.length - 1];
		if (last < upper) {
			res.add(getCurString(last + 1, upper));
		}
		return res;
    }
}

// 163. Missing Ranges

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<>();
		if (nums.length == 0) {
			if (lower != upper){
				res.add(lower + "->" + upper);
			} else {
				res.add(String.valueOf(lower));
			}
			return res;
		}
		
		if (nums[0] > lower) {
			String s = String.valueOf(lower);
			int rightEdge = nums[0] - 1;
			if (rightEdge > lower) {
				s = s.concat("->" + rightEdge);
			}
			res.add(s);
		}
		boolean lastOne = false;
		for (int i = 0; i < nums.length; i++) {
			if (i == nums.length - 1) {
				lastOne = true;
			}
			int cur = nums[i];
			int next = lastOne? upper:nums[i + 1];
			if (cur == next) {
				continue;
			}
			int rightEdge = lastOne? upper:next - 1;
			if (rightEdge >= cur + 1) {
				String s = Integer.toString(cur + 1);
				if (rightEdge > cur + 1) {
					s = s.concat("->" + rightEdge);
				}
				res.add(s);
			}
			
		}
		return res;
	}
}

// 165. Compare Version Number (June 2020)

class Solution {
	private Pair<Integer, Integer> getNextNum(String version, int start) { // pair 1 indicate next pos, 2 indicate the return the version number
		if (start >= version.length()) {
			return new Pair<Integer, Integer>(start, 0);
		}
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = start; i < version.length(); i++) {
			char c = version.charAt(i);
			if (c == '.') {
				i++;
				break;
			}
			sb.append(c);
		}
		return new Pair<Integer, Integer>(i, Integer.valueOf(sb.toString()));
	}
	
    public int compareVersion(String version1, String version2) {
		int pos1 = 0, pos2 = 0;
		Pair<Integer, Integer> pair1, pair2;
		while (pos1 < version1.length() || pos2 < version2.length()) {
			pair1 = getNextNum(version1, pos1);
			pair2 = getNextNum(version2, pos2);
			if (pair1.getValue() < pair2.getValue()) {
				return -1;
			} else if (pair1.getValue() > pair2.getValue()) {
				return 1;
			}
			pos1 = pair1.getKey();
			pos2 = pair2.getKey();
		}
		return 0;
    }
}

// 165. Compare Version Numbers

class Solution {
	
	private List<Integer> splitVersion(String version) {
		List<Integer> res = new ArrayList<>();
		StringBuilder sbd = new StringBuilder();
		version += ".";
		for (int i = 0; i < version.length(); i++) {
			if (version.charAt(i) == '.') {
				if (sbd.length() == 0) {
					res.add(0);
					continue;
				}
				res.add(Integer.parseInt(sbd.toString()));
				sbd.setLength(0);
			} else {
				if (!(sbd.length() == 0 && version.charAt(i) == '0')) {
					sbd.append(version.charAt(i));
				}
			}
		}
        return res;
	}
	
    public int compareVersion(String version1, String version2) {
		List<Integer> v1_lvs = splitVersion(version1);
		List<Integer> v2_lvs = splitVersion(version2);
		int len1 = v1_lvs.size(), len2 = v2_lvs.size();
		int i;
		for (i = 0; i < Math.min(len1, len2); i++) {
			if (v1_lvs.get(i) > v2_lvs.get(i)) {
				return 1;
			} else if (v1_lvs.get(i) < v2_lvs.get(i)) {
				return -1;
			}
		}
		
		if (len1 >= len2) {
			for (; i < len1; i++) {
				if (v1_lvs.get(i) > 0) {
					return 1;
				}
			}
		} else {
			for (; i < len2; i++) {
				if (v2_lvs.get(i) > 0) {
					return -1;
				}
			}
		}
		return 0;
	}
}


// 166. Fraction to Recurring Decimal

class Solution { // notice that the inputs might be negative values.
	private int getNextDecimal(long remain, long denominator) {
		//assert remain < denominator * 10;
		int i;
		for (i = 0; i < 10; i++) {
			if ((i * denominator) <= remain && remain < ((i+1) * denominator)) {
				return i;
			}
		}
		return 0;
	}
	
    public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) return "0";
		StringBuilder res = new StringBuilder();
		if ((numerator ^ denominator) < 0) {
			res.append("-");
		} 
		long _numerator = Math.abs((long)numerator); long _denominator = Math.abs((long)denominator);
		long integral = _numerator / _denominator;
		long remain = _numerator % _denominator;
		res.append(String.valueOf(integral));
		if (remain == 0) {
			return res.toString();
		}
		res.append(".");
		int repeatStart = -1;
		List<Integer> decimals = new ArrayList<>();
		Map<Long, Integer> M = new HashMap<>();
		while (remain > 0) {
			remain *= 10;
			int digit = getNextDecimal(remain, _denominator);
			long curRemain = remain - (long) (digit * _denominator);
			decimals.add(digit);
			M.put(remain/10, decimals.size() - 1);
			if (M.containsKey(curRemain)) {
				repeatStart = M.get(curRemain);
				break;
			}
			remain = curRemain;
		}
		for (int i = 0; i < decimals.size(); i++) {
			if (repeatStart == i) {
				res.append('(');
			}
			res.append(String.valueOf(decimals.get(i)));
		}
		if (repeatStart >= 0) {
			res.append(')');
		}
		return res.toString();
	}
}



// 167. Two Sum II (July 2020)

class Solution {
    public int[] twoSum(int[] numbers, int target) {
		int low = 0, high = numbers.length - 1;
		while (low < high) {
			if (numbers[low] + numbers[high] == target) {
				return new int[]{low + 1, high + 1};
			}
			if (numbers[low] + numbers[high] < target) {
				low++;
			} else {
				high--;
			}
		}
		return null;
	}
}



// 167. Two Sum II - Input array is sorted

class Solution {
    public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		int start = 0, end = numbers.length - 1;
		while (start < end) {
			int sum = numbers[start] + numbers[end];
			if (sum == target) {
				res[0] = start + 1; res[1] = end + 1;
				break;
			}
			if (sum < target) {
				start++;
			} else {
				end--;
			}
		}
		return res;
	}
}

// 168. Excel Sheet Column Title

class Solution {
    public String convertToTitle(int n) {
		StringBuilder res = new StringBuilder();
		n -= 1;
		while (n >= 0) {
			int remainder = n % 26;
			char cur = (char) ('A' + remainder);
			res.append(cur);
			n = n / 26 - 1;
		}
		res.reverse();
		return res.toString();
	}
}


// 168. Excel Sheet Column Title (July 2020)


class Solution {
    public String convertToTitle(int n) {
        
    }
}

// 169. Majority Element (July 2020)

class Solution {
    public int majorityElement(int[] nums) {
        int candidate, count = 0;
		for (int i : nums) {
			if (count == 0) {
				candidate = i;
			}
			if (i == candidate) {
				count++;
			} else {
				count--;
			}
		}
		return candidate;
    }
}


// 169. Majority Element

class Solution { //sorting method
    public int majorityElement(int[] nums) {
		Arrays.sort(nums); int count = 1;
		if (nums.length == 1) return nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i-1]) {
				count++;
				if (count > nums.length / 2) {
					return nums[i];
				}
			} else {
				count = 1;
			}
		}
		return -1;
	}
}

// 170. Two Sum III - Data structure design

class TwoSum {
	private List<Integer> inList;
	private Map<Integer, Boolean> M;
    /** Initialize your data structure here. */
    public TwoSum() {
        inList = new ArrayList<>();
		M = new HashMap<>();
    }
	
	private int binarySearch(int target) {
		int listLen = inList.size();
		if (listLen == 0) {
			return 0;
		}
		int start = 0, end = listLen - 1;
		while(start <= end) {
			int mid = start + (end - start) / 2;
			int cur = inList.get(mid);
			if (cur == target) {
				return mid;
			}
			if (cur < target){
				if (mid == listLen - 1) {
					return mid + 1;
				} else if(inList.get(mid + 1) >= target){
					return mid + 1;
				}
				start = mid + 1;
			} else {
				if (mid == 0) {
					return 0;
				} else if (inList.get(mid - 1) <= target) {
					return mid;
				}
				end = mid - 1;
			}
		}
		return -1;
	}
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (M.containsKey(number) && M.get(number) == true) {
			return;
		}
		int insert_pos = binarySearch(number);
		inList.add(insert_pos, number);
		if (M.containsKey(number)) {
			M.put(number, true);
		} else {
			M.put(number, false);
		}
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (inList.size() == 0) {
			return false;
		}
		int start = 0, end = inList.size() - 1;
		while (start < end) {
			int sum = inList.get(start) + inList.get(end);
			if (sum == value) {
				return true;
			}
			if (sum < value) {
				start++;
			} else {
				end--;
			}
		}
		return false;
    }
}

// 170. Two Sum III - Data structure design

class TwoSum {
	private Map<Integer, Boolean> M;
    /** Initialize your data structure here. */
    public TwoSum() {
		M = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
		if (M.containsKey(number)) {
			if (M.get(number)) {
				return;
			}
			M.put(number, true);
		} else {
			M.put(number, false);
		}
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
		for (int i : M.keySet()) {
			int remain = value - i;
			if (!M.containsKey(remain)) {
				continue;
			}
			if (remain != i) {
				return true;
			}
			if (M.get(i)) {
				return true;
			}
		}
		return false;
    }
}

// 171. Excel Sheet Column Number

class Solution {
    public int titleToNumber(String s) {
		int sum = 0;
		for (int i = s.length() - 1; i >=0; i--) {
			sum += (s.charAt(i) - 'A' + 1) * Math.pow(26, s.length() - 1 - i);
		}
		return sum;
	}
}

// 172. Factorial Trailing Zeroes

public class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res += n / 5;
            n /= 5;
        }
        return res;
    }
}

// 173. Binary Search Tree Iterator (July 2020) (Flatten the BST tree Method)

class BSTIterator {
	List<Integer> list;
	int curPos;
    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
		curPos = 0;
		flatten(list, root);
    }
	
	private void flatten(List<Integer> list, TreeNode root) {
		if (root == null) return;
		if (root.left != null) {
			flatten(list, root.left);
		}
		list.add(root.val);
		if (root.right != null) {
			flatten(list, root.right);
		}
	}
    
    /** @return the next smallest number */
    public int next() {
        return list.get(curPos++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return curPos < list.size() ? true : false;
    }
}

// 173. Binary Search Tree Iterator (July 2020) (Iterative method)

class BSTIterator {
	Stack<TreeNode> S;
	
    public BSTIterator(TreeNode root) {
        S = new Stack<>();
		while (root != null) {
			S.push(root);
			root = root.left;
		}
    }
    
    /** @return the next smallest number */
	
    public int next() {
        TreeNode p = S.pop();
		int res = p.val;
		if (p.right != null) {
			p = p.right;
			while (p != null) {
				S.push(p);
				p = p.left;
			}
		}
		return res;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
		return !S.isEmpty();
    }
}


// 173. Binary Search Tree Iterator (Iterative method)

class BSTIterator { // perfect running time and space usage :)
	private Stack<TreeNode> S;
	
    public BSTIterator(TreeNode root) {
        S = new Stack<>();
		TreeNode curNode = root;
		while(curNode != null) {
			S.push(curNode);
			curNode = curNode.left;
		}
    }
    /** @return the next smallest number */
    public int next() {
        TreeNode curNode = S.pop();
		int ret = curNode.val;
		if (curNode.right != null) {
			curNode = curNode.right;
			while(curNode != null) {
				S.push(curNode);
				curNode = curNode.left;
			}
		}
		return ret;
    }
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (S.isEmpty()) {
			return false;
		}
		return true;
    }
}


// 179. Largest Number (July 2020)

class Solution {
    public String largestNumber(int[] nums) {
		Comparator<String> cmp = new Comparator<String>(){
			public int compare(String s1, String s2){
				return 0 - s1.concat(s2).compareTo(s2.concat(s1));
			}
		};
		String[] numStrs = new String[nums.length];
		for (int i = 0; i < numStrs.length; i++) {
			numStrs[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(numStrs, cmp);
		if (numStrs[0].equals("0")) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		for (String s : numStrs) {
			res.append(s);
		}
		return res.toString();
	}
}

// 179. Largest Number

class Solution {
    public String largestNumber(int[] nums) {
        Comparator<String> comp = new Comparator<String>(){
			public int compare(String s1, String s2) {
				return 0 - s1.concat(s2).compareTo(s2.concat(s1));
			}
		};
        
		String[] numStrings = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			numStrings[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(numStrings, comp);
		if (numStrings[0].equals("0")) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		for (String s : numStrings) {
			res.append(s);
		}
		return res.toString();
    }
}

// 189. Rotate Array

class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int i = 0; i < k; i++){
            int targetPos = (i + k) % nums.length;
            int target = nums[i];
            while (true) {
                int tmp = nums[targetPos];
                nums[targetPos] = target;
                count++;
                if (count == nums.length) {
                    return;
                }
                if (targetPos == i) break;
                target = tmp;
                targetPos = (targetPos + k) % nums.length;
            }
        }
    }
}

// 198. House Robber

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dpWith = new int[len];
        int[] dpWithout = new int[len];
        dpWith[0] = nums[0]; dpWithout[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            dpWith[i] = dpWithout[i-1] + nums[i];
            dpWithout[i] = Math.max(dpWith[i-1], dpWithout[i-1]);
        }
        return Math.max(dpWith[len-1], dpWithout[len-1]);
    }
}



// 198. House Robber (July 2020)

class Solution {
    public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int[] dpWith = new int[nums.length];
		int[] dpWithout = new int[nums.length];
		dpWith[0] = nums[0];
		dpWithout[0] = 0;
		for (int i = 1; i < nums.length; i++) {
			dpWith[i] = dpWithout[i-1] + nums[i];
			dpWithout[i] = Math.max(dpWith[i-1], dpWithout[i-1]);
		}
        return Math.max(dpWith[nums.length-1], dpWithout[nums.length-1]);
	}
}

// 199. Binary Tree Right Side View (July 2020)

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> Q1 = new LinkedList<>();
		Queue<TreeNode> Q2 = new LinkedList<>();
		Q1.add(root);
		while (!Q1.isEmpty()) {
			if (!Q1.isEmpty()) {
				res.add(Q1.element().val);
			}
			while (!Q1.isEmpty()) {
				TreeNode t = Q1.poll();
				if (t.right != null) {
					Q2.offer(t.right);
				}
				if (t.left != null) {
					Q2.offer(t.left);
				}
			}
			if (!Q2.isEmpty()) {
				res.add(Q2.peek().val);
			}
			while (!Q2.isEmpty()) {
				TreeNode t = Q2.poll();
				if (t.right != null) {
					Q1.offer(t.right);
				}
				if (t.left != null) {
					Q1.offer(t.left);
				}
			}
		}
		return res;
	}
}

// 199. Binary Tree Right Side View (July 2020)
class Solution {
	Set<Integer> visited;
	
	Solution() {
		visited = new HashSet<>();
	}
	
	private void rightSideViewHelper (TreeNode root, int level, List<Integer> res) {
		if (!visited.contains(level)) {
			visited.add(level);
			res.add(root.val);
		}
		if (root.right != null) {
			rightSideViewHelper(root.right, level + 1, res);
		}
		if (root.left != null) {
			rightSideViewHelper(root.left, level + 1, res);
		}
	}
	
    public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		rightSideViewHelper(root, 0, res);
		return res;
	}
}



// 199. Binary Tree Right Side View (solution1 BFS: 2ms)

import javafx.util.Pair;

class Solution { // BFS way
    public List<Integer> rightSideView(TreeNode root) {
		Queue<Pair<TreeNode, Integer>> Q = new LinkedList<>();
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		Q.add(new Pair(root, 1));
		while (!Q.isEmpty()) {
			Pair<TreeNode, Integer> pair = Q.remove();
			TreeNode tn = pair.getKey(); int level = pair.getValue();
			if (tn.left != null) {
				Q.add(new Pair(tn.left, level + 1));
			}
			if (tn.right != null) {
				Q.add(new Pair(tn.right, level + 1));
			}
			if (Q.isEmpty()) {
				res.add(tn.val);
			} else if (Q.element().getValue() > level) {
				res.add(tn.val);
			}
		}
		return res;
	}
}

// 199. Binary Tree Right Side View (solution2 DFS_recursion: 1ms)

class Solution {
	private Set<Integer> visited;
	private List<Integer> res;
	
	private void rightSideViewHelper(TreeNode curNode, int level) {
		if (!visited.contains(level)) {
			res.add(curNode.val);
			visited.add(level);
		}
		if (curNode.right != null) {
			rightSideViewHelper(curNode.right, level + 1);
		}
		if (curNode.left != null) {
			rightSideViewHelper(curNode.left, level + 1);
		}
	}
	
    public List<Integer> rightSideView(TreeNode root) {
		res = new ArrayList<>();
		visited = new HashSet<>();
		if (root == null) return res;
		rightSideViewHelper(root, 0);
		return res;
	}
}

// 200. Number of Islands (July 2020)

class Solution {
	private boolean[][] visited;
	
	private void traversal(int i, int j, char[][] grid){
		visited[i][j] = true;
		int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		for (int[] direct : directions) {
			int x = i + direct[0];
			int y = j + direct[1];
			if (x >= 0 && x < grid.length && y >=0 && y < grid[0].length) {
				if (grid[x][y] == '0' || visited[x][y]){
					continue;
				}
				traversal(x, y, grid);
			}
		}
	}
	
    public int numIslands(char[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == '0' || visited[row][col]) {
					continue;
				}
				traversal(row, col, grid);
				count++;
			}
		}
		return count;
	}
}

// 200. Number of Islands

class Solution {
	private char[][] grid;
	private int[][] directions;
	private void dfsVisit(int i, int j) {
		grid[i][j] = '0';
		for (int[] direct : directions) {
			int x = i + direct[0];
			int y = j + direct[1];
			if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
				continue;
			}
			if (grid[x][y] == '1') {
				dfsVisit(x, y);
			}
		}
	}
	
    public int numIslands(char[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		this.grid = grid;
		this.directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfsVisit(i, j);
					count++;
				}
			}
		}
		return count;
	}
}

// 207.  Course Schedule July 2020
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> M = new HashMap<>();
		int[] indgree = new int[numCourses];
		for (int[] pair : prerequisites) {
			indgree[pair[0]]++ ;
			M.putIfAbsent(pair[1], new ArrayList<Integer>());
			M.get(pair[1]).add(pair[0]);
		}
		Queue<Integer> Q = new LinkedList<Integer>();
		for (int i = 0; i < indgree.length; i++) {
			if (indgree[i] == 0) {
				Q.add(i);
			}
		}
		while (!Q.isEmpty()) {
			int course = Q.remove();
			List<Integer> nexts = M.get(course); // 注意这里nexts可能为null
			M.remove(course);
			if (nexts == null) {
				continue;
			}
			for (int c : nexts) {
				indgree[c]--;
				if (indgree[c] <= 0) {
					Q.add(c);
				}
			}
		}
		return M.size() > 0 ? false : true;
	}
}


// 208. Implement Trie (Prefix Tree) July 2020

class Trie {
	private TrieNode root;
	static class TrieNode {
		boolean isWord = false;
		Map<Character, TrieNode> children = new HashMap<>();
	}

    /** Initialize your data structure here. */
    public Trie() {
		root = new TrieNode();
	}
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {
			cur.children.putIfAbsent(c, new TrieNode());
			cur = cur.children.get(c);
		}
		cur.isWord = true;
	}
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
		TrieNode cur = root;
        for (char c : word.toCharArray()) {
			if (!cur.children.containsKey(c)) {
				return false;
			}
			cur = cur.children.get(c);
		}
		return cur.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
		TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
			if (!cur.children.containsKey(c)) {
				return false;
			}
			cur = cur.children.get(c);
		}
		return true;
    }
}
 
// 209. Minimum Size SubArray Sum (July 2020)

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int low = 0, high = 0;
		int res = Integer.MAX_VALUE, sum = 0;
		while (low <= high && high <= nums.length) {
			if (sum < s) {
				if (high == nums.length) {
					break;
				}
				sum += nums[high++];
			} else {
				sum -= nums[low++];
			}
			if (sum >= s) {
				res = Math.min(res, high - low);
			}
		}
		return res == Integer.MAX_VALUE ? 0 : res;
    }
}

// 207. Course Schedule II

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> M = new HashMap<>();
		int[] dgreeCount = new int[numCourses];
		for (int[] list : prerequisites) {
			dgreeCount[list[0]]++;
			M.putIfAbsent(list[1], new ArrayList<Integer>());
			M.get(list[1]).add(list[0]);
		}
		Queue<Integer> Q = new LinkedList<Integer>();
		for (int i = 0; i < dgreeCount.length; i++) {
			if (dgreeCount[i] == 0) {
				Q.add(i);
			}
		}
		int[] res = new int[numCourses];
		int p = 0;
		while (!Q.isEmpty()) {
			int cur = Q.remove();
			res[p++] = cur;
			List<Integer> nexts = M.get(cur);
			M.remove(cur);
			if (nexts == null) {
				continue;
			}
			for (int node : nexts) {
				dgreeCount[node]--;
				if (dgreeCount[node] <= 0) {
					Q.add(node);
				}
			}
		}
		if (M.size() > 0) {
			return new int[0];
		}
		return res;
	}
}

// 211. Add and Search Word data structure design (July 2020)

class WordDictionary {
	TrieNode root;
	
	static class TrieNode {
		boolean isWord = false;
		Map<Character, TrieNode> children = new HashMap<>();
	}
	
    /** Initialize your data structure here. */
    public WordDictionary() {
		root = new TrieNode();
	}
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
		for (char c : word.toCharArray()) {
			cur.children.putIfAbsent(c, new TrieNode());
			cur = cur.children.get(c);
		}
		cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean searchWithRoot(String word, TrieNode root) {
        TrieNode cur = root;
		int indx = -1;
		for (char c : word.toCharArray()) {
			indx++;
			if (!cur.children.containsKey(c)) {
				if (c != '.') {
					return false;
				}
				for (char x : cur.children.keySet()) {
					if (searchWithRoot(word.substring(indx + 1), cur.children.get(x))) {
						return true;
					}
				}
				return false;
			} else {
				cur = cur.children.get(c);
			}
		}
		return cur.isWord;
    }
	
	public boolean search (String word) {
		return searchWithRoot(word, this.root);
	}
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

// 213. House Robber II

class Solution {
    public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) {
			return nums[0];
		}
		int[] dpWith = new int[nums.length];
		int[] dpWithout = new int[nums.length];
		dpWith[0] = nums[0];
		dpWithout[0] = 0;
		
		// case 1: rob 0
		dpWithout[1] = dpWith[0];
		dpWith[1] = 0;
		for (int i = 2; i < nums.length; i++) {
			dpWithout[i] = Math.max(dpWith[i-1], dpWithout[i-1]);
			dpWith[i] = dpWithout[i-1] + nums[i];
		}
		int res1 = dpWithout[nums.length - 1];
		
		// case 2: dont rob 0
		dpWith[0] = 0;
		for (int i = 1; i < nums.length; i++) {
			dpWithout[i] = Math.max(dpWith[i-1], dpWithout[i-1]);
			dpWith[i] = dpWithout[i-1] + nums[i];
		}
		int res2 = Math.max(dpWithout[nums.length - 1], dpWith[nums.length - 1]);
		return Math.max(res1, res2);
	}
}


// 215. Kth Largest Element in an Array
class Solution {
    public int findKthLargest(int[] nums, int k) {
		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		};
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(comp);
		for (int n : nums) {
			minHeap.add(n);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}
		return minHeap.poll();
	}
}

// 217. Contains Duplicate

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> S = new HashSet<>();
		for (int i : nums) {
			if (S.contains(i)) {
				return true;
			}
			S.add(i);
		}
		return false;
    }
}

// 219. Contains Duplicate II

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> M = new HashMap<>();
		
		for (int i = 0; i < nums.length; i++) {
			if (!M.containsKey(nums[i])) {
				M.put(nums[i], i);
				continue;
			}
			if (M.get(nums[i]) >= i - k) {
				return true;
			}
			M.put(nums[i], i);
		}
		return false;
    }
}

// 223. Rectangle Area (AUG 2020)

class Solution {
	private int[] findCovRange(int a, int b, int c, int d) {
		int[] range = new int[2];
		if (b <= c || d <= a) {
			return null;
		}
		int x = Math.max(a, c);
		int y = Math.min(b, d);
		range[0] = x;
		range[1] = y;
		return range;
	}
	
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int comArea = 0;
		int[] horizon = findCovRange(A, C, E, G);
		if (horizon != null) {
			int[] vertical = findCovRange(B, D, F, H);
			if (vertical != null) {
				comArea += (horizon[1] - horizon[0]) * (vertical[1] - vertical[0]);
			}
		}
		return (C - A) * (D - B) + (G - E) * (H - F) - comArea;
    }
}


// 225. Implement Stack using Queues (Push O(1), pop O(N))

class MyStack {
	private Queue<Integer> Q1;
	private Queue<Integer> Q2;
	private int top = 0;
	
    /** Initialize your data structure here. */
    public MyStack() {
        Q1 = new LinkedList<>();
		Q2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
		Q1.add(x);
		top = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
		if (Q1.size() == 0) {
			return -1;
		}
        while (Q1.size() > 1) {
			if (Q1.size() == 2) {
				top = Q1.element();
			}
			Q2.add(Q1.poll());
		}
		int res = Q1.poll();
		Queue<Integer> q = Q1;
		Q1 = Q2;
		Q2 = q;
		return res;
    }
    
    /** Get the top element. */
    public int top() {
		return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return Q1.isEmpty();
    }
}

// 226. Invert Binary Tree (AUG 2020)

class Solution {
	private TreeNode invertTreeHelper(TreeNode root){
		if (root == null) return null;
		TreeNode node = new TreeNode(root.val);
		node.right = invertTreeHelper(root.left);
		node.left = invertTreeHelper(root.right);
		return node;
	}
	
    public TreeNode invertTree(TreeNode root) {
		return invertTreeHelper(root);
	}
}


// 227. Basic Calculator II (AUG 2020)

// 对于每个数字 如果之前的符号为+/-，压栈。 否则和栈顶的元素做乘除.

class Solution {
	private Stack<Integer> S = new Stack<>();
	private int pos = 0;
	
	private int getNextVal(String s) {
		boolean started = false;
		int p = pos;
		for (; p < s.length(); p++) {
			char c = s.charAt(p);
			if (started) {
				if (Character.isDigit(c)) {
					continue;
				}
				break;
			}
			if (Character.isDigit(c)) {
				started = true;
				pos = p;
			}
		}
		int res = Integer.valueOf(s.substring(pos, p));
		pos = p;
		return res;
	}
	
	private int getNextOp(String s) {
		while (pos < s.length()) {
			char c = s.charAt(pos);
			pos++;
			if (c == '+') {
				return 0;
			}
			if (c == '-') {
				return 1;
			}
			if (c == '*') {
				return 2;
			}
			if (c == '/') {
				return 3;
			}
		}
		return -1;
	}
	
    public int calculate(String s) {
		int sum = 0, operator = 0;
        while (true) {
			int val = getNextVal(s);
			if (operator == 1) {
				val = -val;
			}
			if (operator > 1) {
				int pre = S.pop();
				if (operator == 2) {
					val = val * pre;
				} else {
					val = pre / val;
				}
			}
			S.push(val);
			
			operator = getNextOp(s);
			if (operator == -1) {
				break;
			}
		}
		while(!S.isEmpty()) {
			sum += S.pop();
		}
		return sum;
    }
}

// 228. Summary Ranges

class Solution {
	private void addRes(int start, int end, List<String> res, int[] nums) {
		StringBuilder sb = new StringBuilder();
		sb.append(nums[start]);
		if (end > start) {
			sb.append("->");
			sb.append(nums[end]);
		}
		res.add(sb.toString());
	}
	
    public List<String> summaryRanges(int[] nums) {
		int startIndex = -1;
		List<String> res = new ArrayList<>();
		
		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				startIndex = i;
				if (nums.length == 1) {
					addRes(0, 0, res, nums);
					break;
				}
			} else {
				if (nums[i] > nums[i-1] + 1) {
					addRes(startIndex, i-1, res, nums);
					startIndex = i;
					if (i == nums.length - 1) {
						addRes(i, i, res, nums);
						break;
					}
				} else if (i == nums.length - 1){
					addRes(startIndex, i, res, nums);
				}
			}
		}
		return res;
	}
}

// 229. Majority Element II (AUG 2020)

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
		int a = 0, b = 0, countA = 0, countB = 0;
		for (int i : nums) {
			if (i == a) {
				countA++;
			} else if (i == b) {
				countB++;
			} else if(countA == 0) {
				a = i;
				countA++;
			} else if (countB == 0) {
				b = i;
				countB++;
			} else {
				countA--; countB--;
			}
		}
		countA = 0; countB = 0;
		for (int i : nums) {
			if (i == a) {
				countA++;
			} else if (i == b) {
				countB++;
			}
		}
		if (countA > nums.length / 3) {
			res.add(a);
		}
		if (countB > nums.length / 3) {
			res.add(b);
		}
		return res;
    }
}

// 234. Palindrome Linked List (AUG 2020)

class Solution {
    public boolean isPalindrome(ListNode head) {
        
    }
}


// 322. Coin Change

class Solution {// top down dp, 23ms
	private int[] M;
	private int[] coins;
	
    public int coinChange(int[] coins, int amount) {
        M = new int[amount];
		this.coins = coins;
		if (coins.length == 0) {
			return -1;
		}
		if (amount < 1) return 0;
		return coinChange(amount);
    }
	
	private int coinChange (int remain) {
		if (remain == 0) {
			return 0;
		}
		if (remain < 0) {
			return -1;
		}
		if (M[remain - 1] != 0) {
			return M[remain - 1];
		}
		int curMin = Integer.MAX_VALUE;
		for (int coin : coins) {
			int tmp = coinChange(remain - coin);
			if (tmp == -1) {
				continue;
			}
			if (tmp < curMin) {
				curMin = tmp;
			}
		}
		if (curMin == Integer.MAX_VALUE) {
			curMin = -2;
		}
		M[remain - 1] = curMin + 1;
		return curMin + 1;
	}
}

// 322. Coin Change, bottom up dp, 10ms

class Solution {
    public int coinChange(int[] coins, int amount) {
		if (amount <= 0) return 0;
		if (coins.length == 0) return -1;
		int[] dp = new int[amount + 1];
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int coin : coins) {
				if (i - coin < 0 || dp[i - coin] == -1) {
					continue;
				}
				dp[i] = Math.min(dp[i], dp[i - coin] + 1);
			}
			if (dp[i] == Integer.MAX_VALUE) {
				dp[i] = -1;
			}
		}
		return dp[amount];
	}
}

// 322. Coin Change followup, return the coins set that make the goal (min coins to the amount)(rather than the min coins)

class Solution {
    public int coinChange(int[] coins, int amount) {
		if (amount <= 0) return 0;
		if (coins.length == 0) return -1;
		int[] dp = new int[amount + 1];
		dp[0] = 0;
		int dpSelection[] = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int coin : coins) {
				if (i - coin < 0 || dp[i - coin] == -1) {
					continue;
				}
				if (dp[i - coin] + 1 < dp[i]) {
					dp[i] = dp[i - coin] + 1;
					dpSelection[i] = coin;
				}
			}
			if (dp[i] == Integer.MAX_VALUE) {
				dp[i] = -1;
				dpSelection[i] = -1;
			}
		}
		return dp[amount];
	}
}

// 383. Ransom Note

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> M = new HashMap<>();
		for (char c : magazine.toCharArray()) {
			M.putIfAbsent(c, 0);
			M.put(c, M.get(c) + 1);
		}
		for (char c : ransomNote.toCharArray()) {
			if (!M.containsKey(c) || M.get(c) == 0) {
				return false;
			}
			M.put(c, M.get(c) - 1);
		}
		return true;
    }
}

// 387. First Unique Character in a String

class Solution {
    public int firstUniqChar(String s) {
		Map<Character, Integer> M = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!M.containsKey(c)) {
				M.put(c, i);
			} else {
				M.put(c, -1);
			}
		}
		int res = Integer.MAX_VALUE;
		for (char c : M.keySet()) {
			if (!M.containsKey(c)) {
				continue;
			}
			if (M.get(c) >= 0) {
				res = Math.min(res, M.get(c));
			}
		}
		return res <= s.length() ? res : -1;
	}
}

// 490. The Maze (AUG 2020)
class Solution {
	private int[][] maze;
	private int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	private int[] getNextPos(int[] start, int[] direct) {
		int x = start[0], y = start[1];
		while (true) {
			int a = x + direct[0];
			int b = y + direct[1];
			if (a < 0 || a >= maze.length || b < 0 || b >= maze[0].length) {
				break;
			}
			if (maze[a][b] == 1) {
				break;
			}
			x = a; y = b;
		}
		if (start[0] == x && start[1] == y) {
			return null;
		}
		return new int[]{x, y};
	}
	
	private boolean sameDirect(int[] lastD, int[] d) {
		if (lastD == null) {
			return false;
		}
		if (lastD == d) {
			return true;
		}
		if (lastD[0] == -d[0] && d[1] == 0) {
			return true;
		}
		if (d[0] == 0 && d[1] == -lastD[1]) {
			return true;
		}
		return false;
	}
	
	public boolean hasPathHelper(int[] start, int[] dest, int[] direct, boolean[][] visited) {
		if (start[0] == dest[0] && start[1] == dest[1]) {
			return true;
		}
		visited[start[0]][start[1]] = true;
		for (int[] d : directions) {
			if (sameDirect(direct, d)) {
				continue;
			}
			int[] pos = getNextPos(start, d);
			if (pos == null || visited[pos[0]][pos[1]]) {
				continue;
			}
			if (hasPathHelper(pos, dest, d, visited)) {
				return true;
			}
		}
		return false;
	}
	
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		this.maze = maze;
		return hasPathHelper(start, destination, null, visited);
	}
}


// 203. Remove Linked List Elements

class Solution {
    public ListNode removeElements(ListNode head, int val) {
		ListNode newHead = new ListNode(0);
		newHead.next = head;
		ListNode p = newHead;
		
		while(p != null) {
			if (p.next == null) {
				break;
			}
			if (p.next.val == val) {
				p.next = p.next.next;
				continue;
			}
			p = p.next;
		}
		
		return newHead.next;
	}
}

// 204. Count Primes

class Solution {
    public int countPrimes(int n) {
        int res = 0;
		if (n < 2) {
			return 0;
		}
		boolean[] notPrime = new boolean[n];
		
		for (int i = 2; i < n; i++) {
			if (notPrime[i] == true) {
				continue;
			}
			res++;
			for (int j = 2; i * j < n; j++) {
				notPrime[i*j] = true;
			}
		}
		return res;
    }
}

// 205. Isomorphic Strings

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.equals(t)) {
			return true;
		}
		Map<Character, Character> M1 = new HashMap<>();
		Map<Character, Character> M2 = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			char c1 = s.charAt(i), c2 = t.charAt(i);
			if (M1.containsKey(c1) && M1.get(c1) != c2) {
				return false;
			}
			if (M2.containsKey(c2) && M2.get(c2) != c1) {
				return false;
			}	
			M1.putIfAbsent(c1, c2);
			M2.putIfAbsent(c2, c1);
		}
		return true;
    }
}

// 207. Course Schedule

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
		boolean[] visited = new boolean[numCourses];
		
    }
}



// 617. Merge Two Binary Tree (July 2020)

class Solution {
	
	private TreeNode mergeTreesHelper(TreeNode n1, TreeNode n2){
		TreeNode curNode = new TreeNode(0);
		if (n1 != null) {
			curNode.val += n1.val;
		}
		if (n2 != null) {
			curNode.val += n2.val;
		}
		if ((n1 != null && n1.left != null) || (n2 != null && n2.left != null)) {
			TreeNode leftNode1 = n1 == null ? null : n1.left;
			TreeNode leftNode2 = n2 == null ? null : n2.left;
			curNode.left = mergeTreesHelper(leftNode1, leftNode2);
		} else {
			curNode.left = null;
		}
		
		if ((n1 != null && n1.right != null) || (n2 != null && n2.right != null)) {
			TreeNode rNode1 = n1 == null ? null : n1.right;
			TreeNode rNode2 = n2 == null ? null : n2.right;
			curNode.right = mergeTreesHelper(rNode1, rNode2);
		} else {
			curNode.right = null;
		}
		return curNode;
	}
	
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return null;
		}
		return mergeTreesHelper(t1, t2);
	}
}

// 617. Merge Two Binary Tree (July 2020) method2
class Solution {
	
	private TreeNode mergeTreesHelper(TreeNode n1, TreeNode n2){
		if (n1 == null && n2 == null) return null;
		TreeNode curNode = new TreeNode(0);
		TreeNode leftNode1, leftNode2, rightNode1, rightNode2;
		if (n1 != null) {
			curNode.val += n1.val;
			leftNode1 = n1.left;
			rightNode1 = n1.right;
			if (n2 != null) {
				curNode.val += n2.val;
				leftNode2 = n2.left;
				rightNode2 = n2.right;
			}
		} else {
			curNode.val += n2.val;
			leftNode2 = n2.left;
			rightNode2 = n2.right;
		}
		curNode.left = mergeTreesHelper(leftNode1, leftNode2);
		curNode.right = mergeTreesHelper(rightNode1, rightNode2);
		return curNode;
	}
	
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		return mergeTreesHelper(t1, t2);
	}
}


// 662. Maximum width of Binary Tree (July 2020) 

class Solution {
	private List<int[]> widList;
	private int maxWidth;
	
	private void widthOfBinaryTreeHelper(TreeNode node, int level, int pos){
		if (widList.size() <= level) {
			widList.add(new int[]{pos, pos});
		} else {
			int[] wids = widList.get(level);
			wids[1] = pos;
			maxWidth = Math.max(maxWidth, pos - wids[0] + 1);
			widList.set(level, wids);
		}
		if(node.left != null) {
			widthOfBinaryTreeHelper(node.left, level + 1, (pos - 1) * 2 + 1);
		} 
		if (node.right != null) {
			widthOfBinaryTreeHelper(node.right, level + 1, (pos - 1) * 2 + 2);
		}
	}
	
    public int widthOfBinaryTree(TreeNode root) {
		if (root == null) return 0;
		widList = new ArrayList<>();
		maxWidth = 1;
		widthOfBinaryTreeHelper(root, 0, 1);
		return maxWidth;
	}
}





