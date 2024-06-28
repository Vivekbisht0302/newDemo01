package ApnaClg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeL1 {
 
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
        }
    }
    public static Node insert(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }

        if(root.data>val){
            root.left=insert(root.left, val);
        }
        else{
            root.right=insert(root.right, val);
        }
        return root;
    }

    private static void inOrder(Node root) {
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    private static boolean searchInBst(Node root, int key) {
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }
        if(root.data<key){
            return searchInBst(root.right, key);
        }
        else{
            return searchInBst(root.left, key);
        }
    }

    public static void main(String[] args) {
        // int values[]={5,1,3,4,2,7};
        int values[]={8,5,3,1,4,6,10,11,14};
        Node root=null;

        for(int i=0;i<values.length;i++){
            root=insert(root, values[i]);
        }
        // inOrder(root);
        // System.out.println();

        // int key=4;
        // boolean search=searchInBst(root,key);
        // System.out.println(search);

        // //delete node
        // int val=10;
        // Node delitem=delete(root,val);
        // inOrder(root);
        // System.out.println(delitem.data);

        // //print in range
        // int k1=5;
        // int k2=12;
        // printInRange(root,k1,k2);

        //root to leaf path
        // rootToLeafPath(root,new ArrayList<Integer>());


        Node min=null;
        Node max=null;
        boolean validate=validateBst(root,min,max);
        System.out.println(validate);

        // //mirror tree
        // root=mirrorTree(root);
        // inOrder(root);

        //sorted array to binary tree
        // int arr[]={3,5,6,8,10,11,12};
        // root=sortedArraytoBst(arr,0,arr.length-1);
      
        // levelOrder(root);
        
        

    }

    private static boolean validateBst(Node root, Node min, Node max) {
        if(root==null){
            return true;
        }
        if(min!=null && root.data<=min.data){
            return false;
        }
        if(max!=null && root.data>=max.data){
            return false;
        }
        
        return validateBst(root.left, min, root) && validateBst(root.right, root, max);
    }

    private static void levelOrder(Node root) {
        Queue<Node>q=new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node curr=q.remove();
            if(curr==null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                System.out.print(curr.data+" ");
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
            }
        }
        
    }

    private static Node sortedArraytoBst(int[] arr, int s, int e) {
        if(s>e){
            return null;
        }
        // if(s==e){
        //     return new Node(arr[s]);
        // }
        int m=s+(e-s)/2;
        Node root=new Node(arr[m]);
        root.left=sortedArraytoBst(arr, s, m-1);
        root.right=sortedArraytoBst(arr, m+1, e);
        return root;
    }

    private static Node mirrorTree(Node root) {
        if(root==null){
            return null;
        }
        Node leftS=mirrorTree(root.left);
        Node rightS=mirrorTree(root.right);
        root.left=rightS;
        root.right=leftS;
        return root;
    }

    private static void rootToLeafPath(Node root, ArrayList list) {
        if(root==null){
            return;
        }
        list.add(root.data);
        if(root.left==null && root.right==null){
            System.out.println(list);
        }
        rootToLeafPath(root.left, list);
        rootToLeafPath(root.right, list);
        list.remove(list.size()-1);
    }

    private static void printInRange(Node root, int k1, int k2) {
        if(root==null){
            return;
        }
        if(root.data>=k1 && root.data<=k2){
            printInRange(root.left, k1, k2);
            System.out.print(root.data+" ");
            printInRange(root.right, k1, k2);
        }
        if(root.data<k1){
            // printInRange(root.left, k1, k2);
            return;
        }
        if(root.data>k2){
            // printInRange(root.right, k1, k2);
            return;
        }
    }

    private static Node delete(Node root, int val) {
        if(root.data<val){
            root.right=delete(root.right, val);
        }
        else if(root.data>val){
            root.left=delete(root.left, val);
        }
        else{
            //case 1 no child
            if(root.left==null && root.right==null){
                return null;
            }

            //case 2 1 child
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }

            //case 3: both child
            Node IS=findInorderSuccesor(root.right);
            root.data=IS.data;
            root.right=delete(root.right, IS.data);

        }
        return root;
    }

    public static Node findInorderSuccesor(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }

   
   
}
