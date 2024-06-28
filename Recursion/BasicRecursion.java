package ApnaClg.Recursion;

import java.util.ArrayList;

public class BasicRecursion { 
    public static void main(String[] args) {
        // int[]arr={1,2,3,5,4};
        // System.out.println(isSorted(arr,0));
        // int[]arr={8,3,6,9,5,10,2,5,3}; 
        // int k=5;
        // int fo=firstOccurance(arr,0,k);
        // System.out.println("First Occurance of an element : "+fo);
        // int lo=lastOccurance(arr,0,k);
        // System.out.println("Last Occurance of an element : "+lo);

        // //pow(x,n)
        // int x=3;
        // int n=3;
        // int ans=pow(x,n);
        // System.out.println(ans);

        // //say digits
        // int n=412;
        // String rec;
        // rec=sayDigits(n);
        // System.out.println(ans);

        //binarySearch using recursion
        int arr[]={1,2,3,4,5,6};
        int ans=bsr(arr,2,0,arr.length-1);
        System.out.println(ans);
    }

    
    private static int bsr(int[] arr,int key,int st,int ed) {
    //    int s=st;
    //    int e=ed;
    //    while(s<=e){
    //     int mid=s+(e-s)/2;
    //     if(arr[mid]==key){
    //         return mid;
    //     }
    //     else if(arr[mid]>key){
    //         return bsr(arr, key,st,mid);
    //     }
    //     else{
    //         return bsr(arr, key, mid+1, ed);
    //     }
    //    }
    //    return -1;

        if(st>ed){
            return -1;
        }
       int mid=st+(ed-st)/2;
       if(arr[mid]==key){
            return mid;
        }
        else if(arr[mid]>key){
            return bsr(arr, key,st,mid);
        }
        else{
            return bsr(arr, key, mid+1, ed);
        }
    }


    static ArrayList<String> ans=new ArrayList<>();
    static String arr[]={"Zero","One","Two","Three","Four"};
    private static String sayDigits(int n) {
        if(n==0){
            return "";
        }
        int rem=n%10;
        sayDigits(n/10);
        ans.add(arr[rem]); 
        return "";
    }



    private static int pow(int x, int n) {
        if(n==0){
            return 1;
        }
        int getTemp=pow(x, n/2)*pow(x, n/2);
        if(n%2==0){
            return getTemp;
        }
        else{
            return getTemp*x;
        }
    }
    //one way is to set as last indexed element ex: i=arr.length-1;
    //look forward then compare with self
    private static int lastOccurance(int[] arr, int i, int k) {
        if(i==arr.length){
            return -1;
        }
        int ans=lastOccurance(arr, i+1, k);
        if(arr[i]==k && ans==-1){
            return i;
        }
        return ans;
    }

    private static int firstOccurance(int[] arr, int i, int k) {
       if(i==arr.length){
        return -1;
       }
       if(arr[i]==k){
        return i;
       }
       return firstOccurance(arr, i+1, k);
    }

    private static boolean isSorted(int[] arr, int i) {
       if(i==arr.length-1){
        return true;
       }
       if(arr[i]>arr[i+1]){
        return false;
       }
       return isSorted(arr, i+1);
    }
}
