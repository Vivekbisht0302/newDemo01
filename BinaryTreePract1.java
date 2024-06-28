package ApnaClg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreePract1 {
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static class BinaryTree {
        static int idx=-1;
        public static Node buildTree(int[] nodes){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node newNode=new Node(nodes[idx]);
            newNode.left=buildTree(nodes);
            newNode.right=buildTree(nodes);

            return newNode;
        }

        public static void levelorder(Node root){
            if(root==null){
                return;
            }
            Queue<Node> q=new LinkedList<>();
            q.add(root);
            q.add(null);

            while(!q.isEmpty()){
                Node currNode=q.remove();
                if(currNode==null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    }
                    else{
                        q.add(null);
                    }
                }
                else{
                     System.out.print(currNode.data+" ");
                     if(currNode.left!=null){
                         q.add(currNode.left);
                      }
                     if(currNode.right!=null){
                    q.add(currNode.right);
                      }
                }
            }            
        }

        public static int heightOfTree(Node root){
            if(root==null){
                return 0;
            }
            int lht=heightOfTree(root.left);
            int rht=heightOfTree(root.right);
            return Math.max(lht, rht)+1;
        }

        
    }
    public static void main(String[] args) {
        int [] nodes={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree bt=new BinaryTree();
        Node root = bt.buildTree(nodes);
        bt.levelorder(root);
        // System.out.println(bt.heightOfTree(root));


        // int k=3;
        // KthlevelPrint(root,1,k);

        // KthlevelPrintIterative(root,1,k);

        // lowest common Ancestor :-

        // int n1=4;
        // int n2=5;
        // ArrayList<Integer> list1=nTRP(root,n1);
        // System.out.println(list1);
        // ArrayList<Integer> list2=nTRP(root,n2);
        // System.out.println(list2);

        // Node lca=lowestCommonAncestor(root,n1,n2);
        // System.out.println("Lowest common ancestor : "+lca.data);


        // //minimum distance btw nodes
        // int n1=4;
        // int n2=2;
        // int minimum_distance=minDisBtwNodes(root,n1,n2);
        // System.out.println("minimum_distance : "+minimum_distance);

        //transform a tree
        int data=transformTree(root);
        System.out.println("After transforming : ");
        bt.levelorder(root);

    }
    private static int transformTree(Node root) {
        if(root==null){
            return 0;
        }
        int lefts=transformTree(root.left);
        int rights=transformTree(root.right);
        int val=root.data;
        
        root.data=root.left.data+root.right.data+lefts+rights;

        return val;
    }
    private static int minDisBtwNodes(Node root, int n1, int n2) {
        Node lca=lowestCommonAncestor(root,n1,n2);

        int dist1=lcadis(lca,n1);
        int dist2=lcadis(lca,n2);

        return dist1+dist2;
    }
  
    private static int lcadis(Node root, int n) {
        if(root==null){
            return -1;
        }
        if(root.data==n){
            return 0;
        }
        int lefts=lcadis(root.left, n);
        int rights=lcadis(root.right, n);
        if(lefts==-1&& rights==-1){
            return -1;
        }
        if(rights==-1){
            return lefts+1;
        }
        if(lefts==-1){
            return rights+1;
        }
       return-1;
    }
    private static Node lowestCommonAncestor(Node root, int n1, int n2) {
        if(root==null){
            return null;
        }
        if(root.data==n1||root.data==n2){
            return root;
        }
        Node leftSide=lowestCommonAncestor(root.left, n1, n2);
        Node rightSide=lowestCommonAncestor(root.right, n1, n2);
        if(leftSide==null && rightSide==null){
            return null;
        }
        if(rightSide==null){
            return leftSide;
        }
        if(leftSide==null){
            return rightSide;
        }
        return root;
    }
    private static ArrayList<Integer> nTRP(Node root, int n1) {
        if(root==null){
            return new ArrayList<>();
        }
        if(root.data==n1){
            ArrayList<Integer> blist=new ArrayList<>();
            blist.add(root.data);
            return blist;
        }
        ArrayList<Integer> list1=nTRP(root.left, n1);
        ArrayList<Integer> list2=nTRP(root.right, n1);
        ArrayList<Integer> ans=new ArrayList<>();
        ans.addAll(list1);
        ans.addAll(list2);
        if(ans.size()>0){
            ans.add(root.data);
        }
        return ans;
    }
    // recursively
    // private static void KthlevelPrint(Node root, int l, int k) {
    //     if(root==null){
    //         return;
    //     }
    //     if(l==k){
    //         System.out.print(root.data+" ");
    //     }
    //     KthlevelPrint(root.leftNode,l+1,k);
    //     KthlevelPrint(root.rightNode,l+1,k);
    // }
    private static void KthlevelPrintIterative(Node root,int level, int k) {
        if(root==null){
            return ;
        }

        int l=level;
        Queue<Node> q=new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){

            int eleCount=q.size();
            for(int i=0;i<eleCount;i++){
                Node currNode=q.remove();
                if(currNode.left!=null){
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }

                if(l==k){
                    System.out.print(currNode.data+" ");
                }
            }
            l++;
            if(l>k){
                break;
            }
        }
    }
}
