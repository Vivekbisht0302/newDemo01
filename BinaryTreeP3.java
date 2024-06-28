package ApnaClg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ApnaClg.BinaryTreeP1.BinaryTree.Info;

public class BinaryTreeP3 {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    static class BinaryTree{
        static int idx=-1;
        public static Node buildTree(int [] nodes){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node newNode=new Node(nodes[idx]);
            newNode.left=buildTree(nodes);
            newNode.right=buildTree(nodes);

            return newNode;
        }

        public static void preorder(Node root){
            if(root==null){
                return;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }

        public static void inorder(Node root){
            if(root ==null){
                return;
            }
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }

         public static void postorder(Node root){
            if(root==null){
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+" ");
        }

        public static void levelorder(Node root){
            if(root == null){
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
            int leftHt=heightOfTree(root.left);
            int rightHt=heightOfTree(root.right);
            int height=Math.max(leftHt, rightHt);
            return height+1;
        }

        public static int countNodes(Node root){
            if(root==null){
                return 0;
            }
            int leftCount=countNodes(root.left);
            int rightCount=countNodes(root.right);
            int count=leftCount+rightCount +1;
            return count;
        }

        public static int sumOfTree(Node root){
            if(root==null){
                return 0;
            }
            int leftSum=sumOfTree(root.left);
            int rightSum=sumOfTree(root.right);
            return leftSum+rightSum+root.data;
        }

        public int diameterOfTree(Node root){
            if(root==null){
                return 0;
            }
            int ldiam=diameterOfTree(root.left);
            int rdiam=diameterOfTree(root.right);
            int lht=heightOfTree(root.left);
            int rht=heightOfTree(root.right);
            int selfdiam=lht+rht+1;
            return Math.max(selfdiam,(Math.max(rdiam, ldiam)));
        }

        static class Info{
            int diam;
            int ht;
            Info(int diam,int ht){
                this.diam=diam;
                this.ht=ht;
            }
        }
        public Info diameterOfTree2(Node root){
            if(root == null){
                return new Info(0, 0);
            }
            Info leftInfo=diameterOfTree2(root.left);
            Info rightInfo=diameterOfTree2(root.right);

            int finalDiam=Math.max((Math.max(leftInfo.diam,rightInfo.diam)),leftInfo.ht+rightInfo.ht+1);
            int finalHt=Math.max(leftInfo.ht,rightInfo.ht)+1;

            return new Info(finalDiam, finalHt);
        }

        public void kthLevelPrint(Node root, int level, int k) {
            if(root==null){
                return;
            }
            if(level==k){
                System.out.print(root.data+" ");
                return;
            }
            kthLevelPrint(root.left, level+1, k);
            kthLevelPrint(root.right, level+1, k);
        }

        public void printKthlevelIterative(Node root, int level, int k) {
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

        public ArrayList<Integer> nodeToRootPath(Node root, int n) {
            if(root==null){
                return new ArrayList<>();
            }
            if(root.data==n){
                ArrayList<Integer> blist=new ArrayList<>();
                blist.add(root.data);
                return blist;
            }
            ArrayList<Integer> left=nodeToRootPath(root.left, n);
            ArrayList<Integer> right=nodeToRootPath(root.right, n);
            ArrayList<Integer> ansList=new ArrayList<>();
            ansList.addAll(left);
            ansList.addAll(right);
            if(ansList.size()>0){
                ansList.add(root.data);
            }
            return ansList;
        }

        public int lowestCommonAncestor(Node root, int n1, int n2) {
            ArrayList<Integer>list1 = nodeToRootPath(root,n1);
            ArrayList<Integer>list2 = nodeToRootPath(root,n2);

            int i=0;
            int j=0;
            while(i<list1.size() && j<list2.size()){
                if(list1.get(i)==list2.get(j)){
                    return list1.get(i);
                }
                i++;
                j++;
            }
            return -1;
        }

        public Node lCA2(Node root, int n1, int n2) {
            if(root==null){
                return null;
            }
            if(root.data==n1 || root.data==n2){
                return root;
            }
            Node left=lCA2(root.left, n1, n2);
            Node right=lCA2(root.right, n1, n2);

            if(left==null){
                return right;
            }
            if(right==null){
                return left;
            }

            return root;
        }

        public int minDBtwNodes(Node root, int n1, int n2) {

            ArrayList<Integer>list1=nodeToRootPath(root, n1);
            ArrayList<Integer>list2=nodeToRootPath(root, n2);

            int i=0;
            int j=0;
            int ci=0;
            int cj=0;
            while(i<list1.size() && j<list2.size()){
                if(list1.get(i)==list2.get(j)){
                    return ci+cj;
                }
                i++; ci++;
                j++; cj++;
            }
            while(i<list1.size()){
                 i++; ci++;
            }
             while(j<list2.size()){
                 j++; cj++;
            }
            return ci+cj-2;
        }

        public int minDist(Node root, int n1, int n2) {

            Node lca=lCA2(root, n1, n2);
            int dist1=lcaDist(lca,n1);
            int dist2=lcaDist(lca,n2);

            return dist1+dist2;
        }

        private int lcaDist(Node root, int n) {
            if(root==null){
                return -1;
            }
            if(root.data==n){
                return 0;
            }
            int left=lcaDist(root.left, n);
            int right=lcaDist(root.right, n);

            if(left==-1 && right==-1){
                return -1;
            }
            else if(left==-1){
                return right+1;
            }
            else{
                return left+1;
            }
           
        }

        public int KthAncestor(Node root, int n, int k) {
            if(root==null){
                return -1;
            }
            if(root.data==n){
                return 0;
            }
            int left=KthAncestor(root.left, n, k);
            int right=KthAncestor(root.right,n,k);

            if(left==-1 && right==-1){
                return -1;
            }
            int max=Math.max(left, right);
            if(max+1==k){
                System.out.println("Kth Ancestor = "+root.data);
            }
            return max+1;
        }


        public int transformToSumTree(Node root) {
            if(root==null){
                return 0;
            }
            int leftSum=sumOfTree(root.left);
            int rightSum=sumOfTree(root.right);
            int data=root.data;

            root.data=leftSum+rightSum;

            int left= transformToSumTree(root.left);
            int right= transformToSumTree(root.right);

            return data;
        }

    }
    public static void main(String[] args) {
        int [] nodes={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree bt=new BinaryTree();
        Node root=bt.buildTree(nodes);
        // System.out.println("Root node is: "+root.data);
        // bt.preorder(root);
        // System.out.println();
        // bt.inorder(root);
        // System.out.println();
        // bt.postorder(root);

        // System.out.println();
        // bt.levelorder(root);

        // System.out.println("height of Binary tree: "+ bt.heightOfTree(root));
        // System.out.println("Count of nodes is : "+bt.countNodes(root));
        // System.out.println("Sum of nodes is: "+bt.sumOfTree(root));
        // System.out.println("diameter of tree is : ");
        // Info info =bt.diameterOfTree2(root);
        // System.out.println(info.diam);


        //part:- 3
        //kth level //recursively & iteratively
        // int k=3;
        // bt.kthLevelPrint(root,1,k);
        // bt.printKthlevelIterative(root,1,k);
        
        // ArrayList<Integer>list1 = bt.nodeToRootPath(root,4);
        // System.out.println(list1);
        // ArrayList<Integer>list2 = bt.nodeToRootPath(root,6);
        // System.out.println(list2);

        // //lowest common ancestor Approch 1:
        // int lca=bt.lowestCommonAncestor(root,4,5);
        // System.out.println("Lowest Common Ancestor = "+lca);

        // //lowest common ancestor Approch 2:
        // Node lca=bt.lCA2(root, 4, 5);
        // System.out.println("Lowest Common Ancestor = "+lca.data);

       // // minDisBtwNodes my way ---> incorrect
        // int minD=bt.minDBtwNodes(root,4,2);
        // System.out.println("Minimum distance between Nodes is "+minD);

       // //min distance alpha
        // int minD2=bt.minDist(root,4,6);
        // System.out.println("Minimum distance between Nodes "+minD2);

        // //kth Ancestor
        // int n=5;
        // int k=2;
        // int kAns=bt.KthAncestor(root,n,k);

        //transform to sum tree
        // int a=bt.transformToSumTree(root);
        // bt.levelorder(root);
        
    }
}
