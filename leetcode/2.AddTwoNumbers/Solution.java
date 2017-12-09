class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resHead = new ListNode(0);     //返回结果的链表头
        ListNode p = l1, q = l2, curr = resHead;
        int carry = 0;      //加法进位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null){
                p = p.next;
            }
            if (q != null){
                q = q.next;
            }
        }
         if (carry > 0){
                curr.next = new ListNode(carry);
        }
        return resHead.next;
    }
}

class Client {
    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);

        ListNode l2 = new ListNode(0);

        Solution solution = new Solution();
        ListNode res = solution.addTwoNumbers(l1, l2);

        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}