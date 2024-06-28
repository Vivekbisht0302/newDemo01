package ApnaClg;

import java.util.Scanner;
import java.util.Stack;

public class Stack01 {

    //question 1 push at bottom
    // public static void main(String[] args) {
    //     Stack<Integer> s=new Stack<>();
    //     s.push(1);
    //     s.push(2);
    //     s.push(3);

    //     pushAtBottom(s,4);

    //     System.out.println(s.pop());
    //     System.out.println(s.pop());
    //     System.out.println(s.pop());
    //     System.out.println(s.pop());
    // }

    // question 2 reverse a string using stack
    // public static void main(String[] args) {
    //     Scanner in = new Scanner(System.in);
    //     System.out.println("Enter a String");
    //     String str = in.nextLine();
    //     String rev=reverseString(str);
    //     System.out.println("Reversed String is "+rev);
    // }

    //question 3 reverse a stack
    // public static void main(String[] args) {
    //     Stack<Integer> s=new Stack<>();
    //     s.push(1);
    //     s.push(2);
    //     s.push(3);

    //     reverseStack(s);
    //     while(!s.isEmpty()){
    //         System.out.println(s.pop());
    //     }
    // }

    //question 4 stock span
    // public static void main(String[] args) {
    //     int [] stock={100,80,60,70,60,85,100};
    //     int [] span=new int[stock.length];

    //     stockSpan(stock,span);

    //     for(int i=0;i<span.length;i++){
    //         System.out.print(span[i]+" ");
    //     }
    // }

    //question 5 next greater element to right, left , smaller to right
     public static void main(String[] args) {
        int [] arr={6,8,0,1,3};
        int [] ngr=new int[arr.length];
        int [] ngl=new int[arr.length];

        nextGreaterElementR(arr,ngr);
        nextGreaterElementL(arr, ngl);

        int[] nsr=new int[arr.length];
        int[] nsl=new int[arr.length];
        nextSmallestR(arr,nsr);
        nextSmallestL(arr, nsl);



        for(int i=0;i<ngr.length;i++){
            System.out.print(ngr[i]+" ");
        }
        System.out.println();
        for(int i=0;i<ngl.length;i++){
            System.out.print(ngl[i]+" ");
        }
        System.out.println();
        for(int i=0;i<nsr.length;i++){
            System.out.print(nsr[i]+" ");
        }
         System.out.println();
        for(int i=0;i<nsl.length;i++){
            System.out.print(nsl[i]+" ");
        }

    }

    //quetion max area in histogram
    // public static void main(String[] args) {
    //     int[] arr={2,1,5,6,2,3};
    //     int[] nsl=
    // }

    


    private static void nextSmallestL(int[] arr, int[] nsl) {
        Stack<Integer> s=new Stack<>();
        for(int i=0;i<arr.length;i++){
            while(!s.isEmpty() && arr[i]<=arr[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                nsl[i]=-1;
            }
            else{
                nsl[i]=arr[s.peek()];
            }
            s.push(i);
        }
    }

    private static void nextSmallestR(int[] arr, int[] nsr) {
        Stack<Integer> s=new Stack<>();

        for(int i=arr.length-1;i>=0;i--){
            while(!s.isEmpty() && arr[i]<=arr[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                nsr[i]=-1;
            }
            else{
                nsr[i]=arr[s.peek()];
            }
            s.push(i);
        }
    }


    private static void nextGreaterElementL(int[] arr, int[] ngl) {
        Stack<Integer> s=new Stack<>();
        for(int i=0;i<arr.length;i++){
            while(!s.isEmpty() && arr[i]>=arr[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                ngl[i]=-1;
            }
            else{
                ngl[i]=arr[s.peek()];
            }
            s.push(i);
        }
    }


    private static void nextGreaterElementR(int[] arr, int[] ngr) {
        Stack<Integer> s=new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            while(!s.isEmpty() && arr[i]>=arr[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                ngr[i]=-1;
            }
            else{
                ngr[i]=arr[s.peek()];
            }
            s.push(i);
        }
    }


    private static void stockSpan(int[] stock, int[] span) {
        Stack<Integer> s=new Stack<>();
        s.push(0);
        span[0]=1;
        for(int i=1;i<stock.length;i++){
            int currele=stock[i];
            while(!s.isEmpty() && currele > stock[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                span[i]=i+1;
            }
            else{
                int prevHigh=s.peek();
                span[i]=i-prevHigh;
            }
            s.push(i);
        }

    }

    private static void reverseStack(Stack<Integer> s) {
        if(s.size()==0){
            return;
        }
        int temp=s.pop();
        reverseStack(s);
        pushAtBottom(s, temp);
    }

    //reverse string using stack
    private static String reverseString(String str) {
        Stack<Character> s=new Stack<>();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            s.push(ch);
        }
        String ans="";
        while(!s.isEmpty()){
            ans+=s.pop();
        }
        return ans;
    }

    private static void pushAtBottom(Stack<Integer> s, int ele) {
        if(s.size()==0){
            s.push(ele);
            return;
        }
        int temp=s.pop();
        pushAtBottom(s, ele);
        s.push(temp);
    }
}
