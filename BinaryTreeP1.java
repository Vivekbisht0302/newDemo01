package ApnaClg;

import java.util.LinkedList;
import java.util.Queue;

import ApnaClg.BinaryTreeP1.BinaryTree.Info;

public class BinaryTreeP1 {
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
        
       
    }
}
