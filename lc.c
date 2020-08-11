
// 3. Longest Substring Without Repeating Characters

int lengthOfLongestSubstring(char * s) {
	int hmap[256];
	for (int i = 0; i < 256; i++) {
		hmap[i] = -1;
	}
	int i = 0, j = 0, maxLen = 0;
	while (i <= j && j < strlen(s)) {
		if (hmap[s[j]] == -1 || hmap[s[j]] < i) {
			maxLen = j - i + 1 > maxLen ? j - i + 1 : maxLen;
		} else {
			i = hmap[s[j]] + 1;
		}
		hmap[s[j]] = j;
		j++;
	}
	return maxLen;
}

// 14. longest common prefix
char * longestCommonPrefix(char ** strs, int strsSize){
	int i, j, breakFlag = 0;
	if (strsSize == 0) {
		return "";
	}
	for (i = 0; ; i++) {
		char c = strs[0][i];
		if (c == 0) {
			break;
		}
		for (j = 1; j < strsSize; j++) {
			if (strs[j][i] != c) {
				breakFlag = 1;
				break;
			}
		}
		if (breakFlag == 1) {
			break;
		}
	}
	char * res = (char *)malloc(i + 1);
	strncpy(res, strs[0], i);
	res[i] = 0;
	return res;
}


// 28. Implement strStr()

int checkMatch(char * s1, char * s2) {
	for (int i = 0; i < strlen(s2); i++) {
		if (s1[i] != s2[i]) {
			return 0;
		}
	}
	return 1;
}

int strStr(char * haystack, char * needle){
	if (strlen(needle) == 0) {
		return 0;
	}
	int len1 = strlen(haystack), len2 = strlen(needle);
	for (int i = 0; i <= len1 - len2; i++) {
		if (checkMatch(haystack + i, needle)) {
			return i;
		}
	}
	return -1;
}

// 38. Count and Say

char * countAndSay(int n){
	if (n == 1) {
		return "1";
	}
	if (n == 2) {
		return "11";
	}
	char * lastLine = countAndSay(n - 1);
	char * res = malloc(10000); int start = 0;
	int pos = 0;
	for (int i = 1; i <= strlen(lastLine); i++) {
		if (lastLine[i] == lastLine[start]) {
			continue;
		}
		int count = i - start;
		sprintf(res + pos, "%d%c", count, lastLine[start]);
		pos = strlen(res);
		start = i;
	}
	res[pos] = 0;
	if (n >= 4)
		free(lastLine);
	return res;
}


// 58. Length of Last Word

int lengthOfLastWord(char * s){
	if(strlen(s) == 0) {
		return 0;
	}
	int started = -1;
	for (int i = strlen(s) - 1; i >= 0; i--) {
		if (started >= 0) {
			if (s[i] == ' ') {
				return started - i;
			}
		} else {
			if (s[i] == ' ') {
				continue;
			}
			started = i;
		}
	}
	if (started == -1) {
		return 0;
	}
	return started + 1;
}

// 67. Add Binary 

char * addBinary(char * a, char * b){
	int len1 = strlen(a), len2 = strlen(b);
	int len = len1 > len2 ? len1 : len2;
	char * res = (char *)malloc(len + 2);
	int sum = 0, rem = 0, cur = 0;
	for (int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0; i--, j--) {
		sum = 0;
		if (i >= 0) {
			sum += a[i] - '0';
		}
		if (j >= 0) {
			sum += b[j] - '0';
		}
		sum += rem;
		if (sum <= 1) {
			rem = 0;
			res[cur++] = sum == 1 ? '1' : '0';
		} else {
			rem = 1;
			res[cur++] = sum == 2 ? '0' : '1';
		}
	}
	if (rem > 0) {
		res[cur++] = '1';
	}
	res[cur] = 0;
	for (int i = 0, j = cur - 1; i < j; i++, j--) {
		char tmp = res[i];
		res[i] = res[j];
		res[j] = tmp;
	}
	return res;
}

// 83. Remove Duplicates from sorted list

struct ListNode* deleteDuplicates(struct ListNode* head){
	struct ListNode* p = head, *q = NULL;
	if (head == NULL) return NULL;
	while(p->next != NULL) {
		if (p->next->val == p->val) {
			q = p->next;
			p->next = q->next;
			free(q);
		} else {
			p = p->next;
		}
	}
	return head;
}


// 125. Valid Palindrome

char toLowerCase(char c) {
	if (c >= 'a') {
		return c;
	}
	return 'a' + (c - 'A');
}

bool isLetter(char c) {
	if (c >= '0' && c <= '9') {
		return 1;
	}
	if (c >= 'a' && c <= 'z') {
		return 1;
	}
	if (c >= 'A' && c <= 'Z') {
		return 1;
	}
	return 0;
}

bool isPalindrome(char * s){
	if (strlen(s) == 0) {
		return TRUE;
	}
	for (int i = 0, j = strlen(s) - 1; i < j;) {
		if (s[i] == ' ' || !isLetter(s[i])) {
			i++;
			continue;
		}
		if (s[j] == ' ' || !isLetter(s[j])) {
			j--;
			continue;
		}
		if (toLowerCase(s[i]) != toLowerCase(s[j])) {
			return FALSE;
		}
		i++; j--;
	}
	return TRUE;
}

// 151. Reverse Words in a String 

int * getNextIndx (char * s, int start) {
	int * res = (int *) malloc(2 * sizeof(int));
	
	for (int i = start; i >=0; i--) {
		if (s[i] == ' ') {
			if (s[start] != ' ') {
				res[0] = i + 1;
				res[1] = start - i;
				return res;
			}
		} else {
			if (s[start] == ' ') {
				start = i;
			}
			if (i == 0) {
				res[0] = 0;
				res[1] = start - i + 1;
				return res;
			}
		}
	}
	return NULL;
}

char * reverseWords(char * s){
	char * res = malloc(strlen(s) + 1);
	int pos = 0; int start = strlen(s);
	int * indx;
	while (indx = getNextIndx(s, start - 1)) {
		int begin = indx[0];
		int len = indx[1];
		if (pos != 0) {
			res[pos++] = ' ';
		}
		strncpy(res + pos, &s[begin], len);
		pos += len;
		start = begin - 1;
		if (start < 0) {
			break;
		}
	}
	res[pos] = 0;
	return res;
}

// 157. Read N Characters Given Read4

int _read(char* buf, int n) {
	int rem = n, pos = 0;
	char *buf4 = malloc(4);
	while(rem > 0) {
		int read_bytes = read4(buf4);
		int real_bytes = rem > read_bytes ? read_bytes : rem;
		if (read_bytes > 0) {
			memcpy(buf + pos, buf4, real_bytes);
			pos += real_bytes;
		}
		rem -= real_bytes;
		if (read_bytes < 4) {
			break;
		}
	}
	return pos;
}

// 159. Longest Substring with at Most Two Distinct Characters

int lengthOfLongestSubstringTwoDistinct(char * s){
	int hashMap[256];
	for (int i = 0; i < 256; i++) {
		hashMap[i] = 0;
	}
	int maxLen = 0, count = 0;
	for (int i = 0, j = 0; j < strlen(s); j++) {
		if (hashMap[s[j]] == 0) {
			count++;
		}
		hashMap[s[j]]++;
		if (count <= 2) {
			maxLen = maxLen > j - i + 1 ? maxLen : j - i + 1;
		} else {
			while (count > 2) {
				hashMap[s[i]]--;
				if (hashMap[s[i]] == 0) {
					count--;
				}
				i++;
			}
		}	
	}
	return maxLen;
}

// 161. One Edit Distance

bool isOneEditDistance(char * s, char * t){
	bool used = 0;
	int len1 = strlen(s), len2 = strlen(t);
	int i, j;
	for (i = 0, j = 0; i < len1 && j < len2; ) {
		if (s[i] != t[j]) {
			if (used) {
				return 0;
			}
			used = 1;
			if (len1 < len2) {
				j++;
			} else if (len1 == len2) {
				i++; j++;
			} else {
				i++;
			}
		} else {
			i++; j++;
		}
	}
	if (used) {
		if (i == len1 && j == len2) {
			return 1;
		}
		return 0;
	}
	if (i == len1 - 1 || j == len2 -1) {
		return 1;
	}
	return 0;
}

// 186. Reverse Words in a String II

void reverseWords(char* s, int sSize){
	int sleft = sSize - 1;
	int sright = sSize;
	char * res = malloc(sSize + 1);
	int cur = 0;
	for (; sleft >= 0; sleft--) {
		if (s[sleft] == ' ' && sright - sleft> 1) {
			strncpy(res + cur, s + sleft + 1, sright - sleft - 1);
			cur += sright - sleft - 1;
			if (cur < sSize - 1) {
				res[cur++] = ' ';
			} else {
				res[cur] = 0;
				break;
			}
			sright = sleft;
		} else if (sleft == 0) {
			strncpy(res + cur, s, sright - sleft);
			cur += sright - sleft;
			res[cur] = 0;
		}
	}
	return res;
}


// 190. Reverse Bits
uint32_t reverseBits(uint32_t n) {
	int rem = 31; 
	uint32_t res = 0;
	while (n != 0) {
		res += (n & 1) << rem;
		rem--;
		n = n >> 1;
	}
	return res;
}

// 191. Number of 1 Bits

int hammingWeight(uint32_t n) {
	int count = 0;
	while (n != 0) {
		count += n & 0x01;
		n = n >> 1;
	}
	return count;
}

// 228. Summary Ranges 

void addRes(int start, int end, int *nums, char **res, int* curSize){
	char * curRg = malloc(100);
	sprintf(curRg, "%d", nums[start]);
	if (end > start) {
		strcat(curRg, "->");
		sprintf(curRg + strlen(curRg), "%d", nums[end]);
	}
	res[*curSize] = curRg;
	(*curSize)++;
}

char ** summaryRanges(int* nums, int numsSize, int* returnSize){
	char ** res = (char **)malloc(sizeof(char *) * 100);
	int startIndx = -1; *returnSize = 0;
	for (int i = 0; i < numsSize; i++) {
		if (i == 0) {
			startIndx = i;
			if (numsSize == 1) {
				addRes(0, 0, nums, res, returnSize);
			}
		} else {
			if (nums[i] > nums[i-1] + 1) {
				addRes(startIndx, i-1, nums, res, returnSize);
				startIndx = i;
			}
			if (i == numsSize - 1) {
				addRes(startIndx, i, nums, res, returnSize);
			}
		}
	}
	return res;
}

// 234. Palindrome Linked List

struct ListNode* reverseList(struct ListNode* head) {
	struct ListNode* H = head, *p = head;
	while (p->next != NULL) {
		struct ListNode* q = p->next;
		p->next = q->next;
		q->next = H;
		H = q;
	}
	return H;
}

bool isPalindrome(struct ListNode* head){
	if (head == NULL) {
		return true;
	}
	struct ListNode *fast = head, *slow = head;
	while (fast != NULL && fast->next != NULL) {
		slow = slow->next;
		fast = fast->next->next;
	}
	if (fast != NULL) {
		slow = slow->next;
	}
	if (slow == NULL) {
		return true;
	}
	struct ListNode* phalf = reverseList(slow);
	while (phalf != NULL) {
		if (phalf->val != head->val) {
			return 0;
		}
		phalf = phalf->next;
		head = head->next;
	}
	return 1;
}
// 383. Ransom Note 

bool canConstruct(char * ransomNote, char * magazine){
	int hashMap[256];
	for (int i = 0; i < 256; i++) {
		hashMap[i] = 0;
	}
	for (int i = 0; i < strlen(magazine); i++) {
		hashMap[magazine[i]]++;
	}
	for (int i = 0; i < strlen(ransomNote); i++) {
		if (hashMap[ransomNote[i]] == 0) {
			return 0;
		}
		hashMap[ransomNote[i]]--;
	}
	return 1;
}

// 387. First Unique Character in a String

int firstUniqChar(char * s) {
	int hashMap[26];
	for (int i = 0 ; i < 26; i++) {
		hashMap[i] = -2;
	}
	for (int i = 0; i < strlen(s); i++) {
		int hashIndex = s[i] - 'a';
		if (hashMap[hashIndex] == -2) {
			hashMap[hashIndex] = i;
		} else if (hashMap[hashIndex] >= 0) {
			hashMap[hashIndex] = -1;
		}
	}
	int start = strlen(s);
	for (char c = 'a'; c <= 'z'; c++ ){
		int indx = c - 'a';
		if (hashMap[indx] >= 0) {
			start = start < hashMap[indx] ? start : hashMap[indx];
		}
	}
	return start == strlen(s) ? -1 : start;
}


// 394. Decode String

char * decodeString(char * s){
	
}

// 509. Fibonacci Number

int fib(int N){
	if (N == 0) {
		return 0;
	}
	if (N == 1) {
		return 1;
	}
	return fib(N - 1) + fib(N - 2);
}

int fib(int N) {
	if (N == 0) return 0;
	int* dp = (int *)malloc((N + 1) * sizeof(int));
	dp[0] = 0; dp[1] = 1;
	for (int i = 2; i <= N; i++) {
		dp[i] = dp[i-1] + dp[i-2];
	}
	return dp[N];
}



