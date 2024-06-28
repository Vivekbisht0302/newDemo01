package ApnaClg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import ApnaClg.BinaryTreeP1.BinaryTree.Info;

public class BinaryTreeP2 {
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

    }
    public static void main(String[] args) {
        // int [] nodes={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        // BinaryTree bt=new BinaryTree();
        // Node root=bt.buildTree(nodes);

        // Node root=new Node(1);
        // root.left=new Node(2);
        // root.right=new Node(3);
        // root.left.left=new Node(4);
        // root.left.right=new Node(5);
        // root.right.left=new Node(6);
        // root.right.right=new Node(7);
       
        // Node subRoot=new Node(2);
        // subRoot.left=new Node(4);
        // subRoot.right=new Node(5);

        // BinaryTree bt =new BinaryTree();
        // bt.levelorder(root);
        // bt.levelorder(subRoot);
        // System.out.println(isSubtree(root, subRoot));


        //top view
        Node root=new Node(1);
        root.left=new Node(2);
        root.right=new Node(3);
        root.left.left=new Node(4);
        root.left.right=new Node(5);
        root.right.left=new Node(6);
        root.right.right=new Node(7);

        topView(root);
        System.out.println();
        bottomView(root);
        System.out.println();
        leftView(root);


    }
   
    private static void topView(Node root) {
        HashMap<Integer,Node> map=new HashMap<>();
        int max=0;
        int min=0;
        Queue<Info2> q=new LinkedList<>();
        q.add(new Info2(root, 0));
        while(!q.isEmpty()){
            Info2 curr=q.remove();
            if(!map.containsKey(curr.hd)){
                map.put(curr.hd,curr.node);
            }
            if(curr.node.left!=null){
                q.add(new Info2(curr.node.left,curr.hd-1));
                min=Math.min(min,curr.hd-1);
            }
            if(curr.node.right!=null){
                q.add(new Info2(curr.node.right,curr.hd+1));
                max=Math.max(max,curr.hd+1);
            }
        }
         //printing hashmap
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        }
    }

    public static class Info2{
        Node node;
        int hd;
        Info2(Node node,int hd){
            this.node=node;
            this.hd=hd;
        }
    }
    // public static void topView(Node root){
    //     HashMap<Integer,Node> map=new HashMap<>();

    //     Queue<Info2> q=new LinkedList<>();
    //     q.add(new Info2(root,0));
    //     q.add(null);

    //     int min=0;
    //     int max=0;

    //     while(!q.isEmpty()){
    //         Info2 curr=q.remove();
    //         if(curr==null){
    //             if(q.isEmpty()){
    //                 break;
    //             }
    //             else{
    //                 q.add(null);
    //             }
    //         }
    //         else{
    //              if(!map.containsKey(curr.hd)){
    //                  map.put(curr.hd, curr.node);
    //                  }
    //              if(curr.node.left!=null){
    //                  q.add(new Info2(curr.node.left, curr.hd-1));
    //                  min=Math.min(min, curr.hd-1);
    //                  }
    //              if(curr.node.right!=null){
    //                  q.add(new Info2(curr.node.right, curr.hd+1));
    //                  max=Math.max(max, curr.hd+1);
    //                 }
    //         }
            
    //     }

    //     //printing hashmap
    //     for(int i=min;i<=max;i++){
    //         System.out.print(map.get(i).data+" ");
    //     }
    // }


    private static void bottomView(Node root) {
        HashMap<Integer,Node> map=new HashMap<>();
        int min=0;
        int max=0;
        Queue<Info2> q=new LinkedList<>();
        q.add(new Info2(root, 0));
        while(!q.isEmpty()){
            Info2 curr=q.remove();
            map.put(curr.hd, curr.node);
            if(curr.node.left!=null){
                q.add(new Info2(curr.node.left,curr.hd-1));
                min=Math.min(min,curr.hd-1);
            }
            if(curr.node.right!=null){
                q.add(new Info2(curr.node.right,curr.hd+1));
                max=Math.max(max,curr.hd+1);
            }
        }

        //printing hashmap
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        }
    }

    private static void leftView(Node root) {
        HashMap<Integer,Node> map=new HashMap<>();
        int min=0;
        int max=0;
        Queue<Info2> q=new LinkedList<>();
        q.add(new Info2(root, 0));
        while(!q.isEmpty()){
            Info2 curr=q.remove();
            if(!map.containsKey(curr.hd)){
                map.put(curr.hd,curr.node);
            }
            if(curr.node.left!=null){
                q.add(new Info2(curr.node.left,curr.hd+1));
                max=Math.max(max,curr.hd+1);
            }
            if(curr.node.right!=null){
                q.add(new Info2(curr.node.right,curr.hd+1));
                max=Math.max(max,curr.hd+1);
            }
        }
         //printing hashmap
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        } 
    }

    public static boolean isSubtree(Node root,Node subRoot){
        if(root==null){
            return false;
        }
        if(root.data==subRoot.data){
            if(isIdentical(root,subRoot)){
                return true;
            }
        }
        Boolean leftAns=isSubtree(root.left, subRoot);
        Boolean rightAns=isSubtree(root.right, subRoot);

        return leftAns||rightAns;
    }
    private static boolean isIdentical(Node node, Node subRoot) {
        if(node==null && subRoot==null){
            return true;
        }
        else if(node==null || subRoot==null || node.data!=subRoot.data){
            return false;
        }
        // if(!isIdentical(node.left, subRoot.left)){
        //     return false;
        // }
        // if(!isIdentical(node.right, subRoot.right)){
        //     return false;
        // }
        boolean leftSide=isIdentical(node.left, subRoot.right);
        boolean rightSide=isIdentical(node.right, subRoot.right);
        return leftSide|| rightSide;
    }
  
}
