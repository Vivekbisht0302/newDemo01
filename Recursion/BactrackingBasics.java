package ApnaClg.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BactrackingBasics {
    public static void main(String[] args) {
        // int arr[] = new int[5];
        // recFun(arr,0);
        // System.out.println(Arrays.toString(arr));

        // String str="abc";
        // printSubset("",str);
        // System.out.println(ans);
        // printPermutation(str,"");
        // System.out.println(anst);
        // System.out.println(str.substring(0,0));

        int arr[]={1,2};
        ArrayList<ArrayList<Integer>> list= subsets(arr);
        for(ArrayList l:list){
            System.out.println(l);
        }
    }

    // public static List<List<Integer>> anss=new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> anss=new ArrayList<>();
        ArrayList<Integer> temp=new ArrayList<>();
        helperss(nums,0,temp,anss);
        return anss;
    }
    public static void helperss(int arr[],int idx,ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> anss){
        if(idx==arr.length){
            anss.add(temp);
            return;
        }
        // int ele=arr[idx];
        temp.add(arr[idx]);
        helperss(arr,idx+1,temp,anss);
        temp.remove(temp.size()-1);
        helperss(arr,idx+1,temp,anss);
    }

    public static ArrayList<String> anst=new ArrayList<>();
    private static void printPermutation(String str, String ans) {
        if(str.length()==0){
            anst.add(ans);
        }
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            String s1=str.substring(0,i);
            String s2=str.substring(i+1);
            String roq=s1+s2;
            printPermutation(roq, ans+ch);
        }
    }
    public static ArrayList<String> ans=new ArrayList<>();
    private static void printSubset(String p,String str) {
        if(str.length()==0){
            // System.out.println(p);
            ans.add(p);
            return;
        }
        char ch=str.charAt(0);
        printSubset(p+ch, str.substring(1));
        printSubset(p, str.substring(1));
    }

    private static void recFun(int[] arr,int ind) {
       if(ind==arr.length){
          return ;
        }
        arr[ind]=ind+1;
        recFun(arr, ind+1);
        arr[ind]=arr[ind]-2;
    }
}
