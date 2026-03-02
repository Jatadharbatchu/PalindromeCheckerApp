import java.util.*;

public class PalindromeChecker {

    // ================= UC1 =================
    private static final String APP_NAME = "Palindrome Checker Application";
    private static final String VERSION = "1.0.0";

    public static void main(String[] args) {

        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word or sentence to check: ");
        String input = scanner.nextLine();
        System.out.println();

        // UC2
        checkHardcodedPalindrome(input);

        // UC3
        palindromeUsingReverse(input);

        // UC4
        palindromeUsingCharArray(input);

        // UC5
        palindromeUsingStack(input);

        // UC6
        palindromeUsingQueueAndStack(input);

        // UC7
        palindromeUsingDeque(input);

        // UC8
        palindromeUsingLinkedList(input);

        // UC9
        palindromeUsingRecursion(input);

        // UC10
        palindromeIgnoreCaseAndSpaces(input);

        // UC11
        palindromeUsingService(input);

        // UC12
        palindromeUsingStrategy(input);

        // UC13
        performanceComparison(input);

        System.out.println("Program execution completed.");
        scanner.close();
    }

    // ================= UC1 =================
    private static void displayWelcomeMessage() {
        System.out.println("===========================================");
        System.out.println("        " + APP_NAME);
        System.out.println("              Version: " + VERSION);
        System.out.println("===========================================\n");
    }

    // ================= UC2 =================
    private static void checkHardcodedPalindrome(String word) {
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed += word.charAt(i);
        }
        System.out.println("UC2 Result: " + word.equals(reversed));
        System.out.println();
    }

    // ================= UC3 =================
    private static void palindromeUsingReverse(String original) {
        String reversed = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }
        System.out.println("UC3 Result: " + original.equals(reversed));
        System.out.println();
    }

    // ================= UC4 =================
    private static void palindromeUsingCharArray(String text) {
        char[] arr = text.toCharArray();
        int start = 0, end = arr.length - 1;
        boolean isPalindrome = true;
        while (start < end) {
            if (arr[start] != arr[end]) {
                isPalindrome = false;
                break;
            }
            start++; end--;
        }
        System.out.println("UC4 Result: " + isPalindrome);
        System.out.println();
    }

    // ================= UC5 =================
    private static void palindromeUsingStack(String text) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++)
            stack.push(text.charAt(i));

        boolean isPalindrome = true;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != stack.pop()) {
                isPalindrome = false;
                break;
            }
        }
        System.out.println("UC5 Result: " + isPalindrome);
        System.out.println();
    }

    // ================= UC6 =================
    private static void palindromeUsingQueueAndStack(String text) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            stack.push(ch);
            queue.add(ch);
        }

        boolean isPalindrome = true;
        while (!queue.isEmpty()) {
            if (!queue.remove().equals(stack.pop())) {
                isPalindrome = false;
                break;
            }
        }
        System.out.println("UC6 Result: " + isPalindrome);
        System.out.println();
    }

    // ================= UC7 =================
    private static void palindromeUsingDeque(String text) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++)
            deque.addLast(text.charAt(i));

        boolean isPalindrome = true;
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                isPalindrome = false;
                break;
            }
        }
        System.out.println("UC7 Result: " + isPalindrome);
        System.out.println();
    }

    // ================= UC8 =================
    static class Node {
        char data;
        Node next;
        Node(char data) { this.data = data; }
    }

    private static void palindromeUsingLinkedList(String text) {
        Node head = createLinkedList(text);
        System.out.println("UC8 Result: " + isPalindromeLinkedList(head));
        System.out.println();
    }

    private static Node createLinkedList(String text) {
        Node head = null, tail = null;
        for (int i = 0; i < text.length(); i++) {
            Node node = new Node(text.charAt(i));
            if (head == null) head = tail = node;
            else { tail.next = node; tail = node; }
        }
        return head;
    }

    private static boolean isPalindromeLinkedList(Node head) {
        if (head == null || head.next == null) return true;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = reverseList(slow);
        Node firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // ================= UC9 =================
    private static void palindromeUsingRecursion(String text) {
        System.out.println("UC9 Result: " +
                isPalindromeRecursive(text, 0, text.length() - 1));
        System.out.println();
    }

    private static boolean isPalindromeRecursive(String text, int start, int end) {
        if (start >= end) return true;
        if (text.charAt(start) != text.charAt(end)) return false;
        return isPalindromeRecursive(text, start + 1, end - 1);
    }

    // ================= UC10 =================
    private static void palindromeIgnoreCaseAndSpaces(String text) {
        String normalized = text.toLowerCase().replaceAll("\\s+", "");
        int start = 0, end = normalized.length() - 1;
        boolean isPalindrome = true;

        while (start < end) {
            if (normalized.charAt(start) != normalized.charAt(end)) {
                isPalindrome = false;
                break;
            }
            start++; end--;
        }
        System.out.println("UC10 Result: " + isPalindrome);
        System.out.println();
    }

    // ================= UC11 =================
    private static void palindromeUsingService(String text) {
        PalindromeService service = new PalindromeService();
        System.out.println("UC11 Result: " + service.checkPalindrome(text));
        System.out.println();
    }

    static class PalindromeService {
        public boolean checkPalindrome(String text) {
            int start = 0, end = text.length() - 1;
            while (start < end) {
                if (text.charAt(start) != text.charAt(end)) return false;
                start++; end--;
            }
            return true;
        }
    }

    // ================= UC12 =================
    private static void palindromeUsingStrategy(String text) {
        PalindromeStrategy strategy = new StackStrategy();
        System.out.println("UC12 Stack Strategy: " + strategy.check(text));

        strategy = new DequeStrategy();
        System.out.println("UC12 Deque Strategy: " + strategy.check(text));
        System.out.println();
    }

    interface PalindromeStrategy {
        boolean check(String text);
    }

    static class StackStrategy implements PalindromeStrategy {
        public boolean check(String text) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < text.length(); i++)
                stack.push(text.charAt(i));
            for (int i = 0; i < text.length(); i++)
                if (text.charAt(i) != stack.pop()) return false;
            return true;
        }
    }

    static class DequeStrategy implements PalindromeStrategy {
        public boolean check(String text) {
            Deque<Character> deque = new ArrayDeque<>();
            for (int i = 0; i < text.length(); i++)
                deque.addLast(text.charAt(i));
            while (deque.size() > 1)
                if (deque.removeFirst() != deque.removeLast()) return false;
            return true;
        }
    }

    // ================= UC13 =================
    private static void performanceComparison(String text) {

        int iterations = 10000;

        long start, end;

        System.out.println("===== UC13 Performance Comparison =====");

        start = System.nanoTime();
        for (int i = 0; i < iterations; i++)
            palindromeUsingCharArray(text);
        end = System.nanoTime();
        System.out.println("CharArray Time: " + (end - start) + " ns");

        start = System.nanoTime();
        for (int i = 0; i < iterations; i++)
            palindromeUsingStack(text);
        end = System.nanoTime();
        System.out.println("Stack Time: " + (end - start) + " ns");

        start = System.nanoTime();
        for (int i = 0; i < iterations; i++)
            palindromeUsingDeque(text);
        end = System.nanoTime();
        System.out.println("Deque Time: " + (end - start) + " ns");

        start = System.nanoTime();
        for (int i = 0; i < iterations; i++)
            palindromeUsingRecursion(text);
        end = System.nanoTime();
        System.out.println("Recursion Time: " + (end - start) + " ns");

        System.out.println("========================================\n");
    }
}