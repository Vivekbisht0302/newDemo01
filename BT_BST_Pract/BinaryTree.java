package ApnaClg.BT_BST_Pract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data=data;
        this.left=null;
        this.right=null;
    }
}

public class BinaryTree {
    static int idx=-1;
    public static Node buildTree(int[] arr){
        ++idx;
        if(arr[idx]==-1){
            return null;
        }
        Node nn=new Node(arr[idx]);
        nn.left=buildTree(arr);
        nn.right=buildTree(arr);
        return nn;
    }

    //preOrder --> root,left,right
    public static void preOrder(Node root){
        if(root!=null){
            System.out.print(root.data+" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    //preOrder --> left,right,root
    public static void postOrder(Node root){
        if(root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data+" ");
        }
    }
    //preOrder --> left,root,right
    public static void inOrder(Node root){
        if(root!=null){
            inOrder(root.left);
            System.out.print(root.data+" ");
            inOrder(root.right);
        }
    }
    public static void levelOrder(Node root){
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
                q.add(null);
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
        return Math.max(leftHt, rightHt)+1;
    }

    public static int countOfTree(Node root){
        if(root==null){
            return 0;
        }
        int lc=countOfTree(root.left);
        int rc=countOfTree(root.right);
        return lc+rc+1;
    }

    public static int sumOfTree(Node root){
        if(root==null){
            return 0;
        }
        int ls=sumOfTree(root.left);
        int rs=sumOfTree(root.right);
        return ls+rs+root.data;
    }

    public static int diamOfTree(Node root,int [] daim){
        if(root==null){
            return 0;
        }
        int ld=diamOfTree(root.left, daim);
        int rd=diamOfTree(root.right, daim);
        daim[0]=Math.max(daim[0],ld+rd);
        return Math.max(ld, rd)+1;
    }

    public static class Info{
        Node node;
        int hd;
        Info(Node node,int hd){
            this.node=node;
            this.hd=hd;
        }
    }
    public static void topView(Node root){
        HashMap<Integer,Node> map=new HashMap<>();
        int min=0;
        int max=0;
        Queue<Info> q= new LinkedList<>();
        q.add(new Info(root,0));
        q.add(null);
        while(!q.isEmpty()){
            Info curr=q.remove();
            if(curr==null){
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                if(!map.containsKey(curr.hd)){
                    map.put(curr.hd,curr.node);
                }
                if(curr.node.left!=null){
                    q.add(new Info(curr.node.left, curr.hd-1));
                    min=Math.min(min,curr.hd-1);
                }
                if(curr.node.right!=null){
                    q.add(new Info(curr.node.right, curr.hd+1));
                    max=Math.max(max,curr.hd+1);
                }
            }
        }
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        }
    }
    public static ArrayList<Integer> ntrp(Node root,int n1){
        if(root==null){
            return new ArrayList<>();
        }
        if(root.data==n1){
            ArrayList<Integer> blist=new ArrayList<>();
            blist.add(n1);
            return blist;
        }
        ArrayList<Integer> list1=ntrp(root.left, n1);
        ArrayList<Integer> list2=ntrp(root.right, n1);
        ArrayList<Integer> ans=new ArrayList<>();
        ans.addAll(list1);
        ans.addAll(list2);
        if(ans.size()>0){
            ans.add(root.data);
        }
        return ans;
    }

    public static Node lca(Node root,int n1,int n2){
        if(root==null){
            return null;
        }
        if(root.data==n1 || root.data==n2){
            return root;
        }
        Node lf=lca(root.left, n1, n2);
        Node rf=lca(root.right, n1, n2);
        if(lf==null && rf==null){
            return null;
        }
        if(lf==null){
            return rf; 
        }
        if(rf==null){
            return lf;
        }
        return root;
    }
    public static ArrayList<Integer> preOrderItr(Node root){
        if(root==null){
            return new ArrayList<>();
        }
        ArrayList<Integer> ans=new ArrayList<>();
        Stack<Node> st=new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            Node curr=st.pop();
            ans.add(curr.data);
            if(curr.right!=null){
                st.push(curr.right);
            }
            if(curr.left!=null){
                st.push(curr.left);
            }
        }
        return ans;
    }

    public static ArrayList<Integer> inOrderItr(Node root){
        ArrayList<Integer> ans=new ArrayList<>();
        Stack<Node> st=new Stack<>();
        st.push(root);
        Node curr=root;
        while(curr!=null){
            curr=curr.left;
            if(curr!=null){
                st.push(curr.left);
            }
        }
        
    }
    public static void main(String[] args) {
        int[]arr={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        Node root=buildTree(arr);
        // System.out.println("Root is : "+root.data);
        // preOrder(root);
        // System.out.println();
        // postOrder(root);
        // System.out.println();
        // inOrder(root);
        // System.out.println("level order:");
        // levelOrder(root);

        // int ht=heightOfTree(root);
        // System.out.println("height of tree : "+ht);

        // int countNodes=countOfTree(root);
        // System.out.println("Count of Nodes : "+countNodes);

        // int sumOfNodes=sumOfTree(root);
        // System.out.println("sum of tree : "+sumOfNodes);

        // //diameter of a tree:
        // int[]daim=new int[1];
        // int diameterOfTree=diamOfTree(root,daim);
        // System.out.println("diameter of tree : "+daim[0]);

        //top view of tree
        // topView(root);

        // //node to root path
        // int n1=4;
        // List<Integer> list=ntrp(root,n1);
        // System.out.println(list);

        // int n1=4;
        // int n2=5;
        // Node lcans=lca(root,n1,n2);

        // System.out.println(lcans.data);

        // //preorder Itratively
        // ArrayList<Integer> ans=preOrderItr(root);
        // System.out.println(ans);

        //inorder Itratively
        ArrayList<Integer> ans=inOrderItr(root);
        System.out.println(ans);

    }
}
