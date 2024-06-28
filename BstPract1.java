package ApnaClg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BstPract1 {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
        }
    }
    private static Node insertBst(Node root, int val) {
        if(root==null){
            return root=new Node(val);
        }
        if(root.data>val){
            root.left=insertBst(root.left, val);
        }
        else{
            root.right=insertBst(root.right, val);
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
    private static Node deleteNode(Node root, int delval) {
        if(root.data<delval){
            root.right=deleteNode(root.right, delval);
        }
        else if(root.data>delval){
            root.left=deleteNode(root.left, delval);
        }
        else{
            // 0 child
            if(root.left==null && root.right==null){
                return null;
            }
            // 1 child
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }

            //2 child
            Node IS=inOrderSuccesor(root.right);
            root.data=IS.data;
            root.right=deleteNode(root.right,IS.data);
        }
        return root;
    }   
    private static Node inOrderSuccesor(Node root) {
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }

    public static void main(String[] args) {
        // int values[]={5,1,3,4,2,7};
        // int values[]={8,5,3,1,4,6,10,11,14};
        Node root=null;
        // for(int i=0;i<values.length;i++){
        //     root=insertBst(root,values[i]);
        // }
        // inOrder(root);
        // System.out.println();
        // levelOrder(root);
        // int key =-4;
        // boolean srch=searchInBst(root,key);
        // System.out.println(srch);

        // int delval=3;
        // root=deleteNode(root,delval);

        // levelOrder(root);

        // //print in range
        // int k1=5;
        // int k2=12;
        // printInRange(root,k1,k2);

        // //root to leaf path
        // rootToLeafPath(root,new ArrayList<Integer>());

        // // mirror a tree
        // root=mirrorATree(root);
        // System.out.println();
        // levelOrder(root);

        //sorted array to balanced BST
        int arr[]={3,5,6,8,10,11,12};
        root=sortedArraytoBst(arr,0,arr.length-1);
        inOrder(root);
        System.out.println();
        levelOrder(root);

    }

    private static Node sortedArraytoBst(int[] arr, int s, int e) {
        if(s>e){
            return null;
        }
        int mid=s+(e-s)/2;
        Node root=new Node(arr[mid]);
        root.left=sortedArraytoBst(arr, s, mid-1);
        root.right=sortedArraytoBst(arr, mid+1, e);
        return root;
    }

    private static Node mirrorATree(Node root) {
        if(root==null){
            return null;
        }
        Node leftS=mirrorATree(root.left);
        Node rightS=mirrorATree(root.right);
        root.left=rightS;
        root.right=leftS;
        return root;
    }

    private static void rootToLeafPath(Node root, ArrayList<Integer> list) {
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
        if(root==null ){
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

   
}
