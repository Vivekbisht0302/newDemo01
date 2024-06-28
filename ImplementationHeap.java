package CodeHelpLB_Playlist.Heaps;



public class ImplementationHeap {
   
    public static class Heap{
        int arr[]= new int[100];
        int size;

        Heap(){
            arr[0]=-1;
            size=0;
        }
    
        public void insert(int val){
            size+=1;
            int index=size;
            arr[index]=val;
    
            while(index>1){
                int parent=index/2;
    
                if(arr[parent]<arr[index]){
                    swap(arr,parent,index);
                    index=parent;
                }
                else{
                    return;
                }
            }
        }

        public void deleteFromHeap(){
            if(size==0){
                System.out.println("Nothing to delete");
                return;
            }
            // putting last index element in first
            arr[1]=arr[size];
            size--;

            //take root node to correct position
            int i=1;
            while(i<size){
                int leftIndx=2*i;
                int rightIndx=2*i+1;

                if(leftIndx<size && arr[leftIndx]>arr[i]){
                    swap(arr, leftIndx, i);
                    i=leftIndx;
                }
                else if(rightIndx<size && arr[rightIndx]>arr[i]){
                    swap(arr, rightIndx, i);
                    i=rightIndx;
                }
                else{
                    return;
                }
            }

        }

        public void heapify(int[]arr, int n,int i){
            int largest=i;
            int left=2*i;
            int right=2*i +1;

            if(left<n && arr[left]>arr[largest]){
                largest=left;
            }

            if(right<n && arr[right]>arr[largest]){
                largest=right;
            }

            if(largest!=i){
                swap(arr, i, largest);
                heapify(arr, n, largest);
            }
        }
    
        private void swap(int []arr,int i, int j) {
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
    
        private void printArr(){
            for(int i=0;i<=size;i++){
                System.out.print(arr[i]+" ");
            }
        }
        
    }
    public static void main(String[] args) {

        Heap h= new Heap();
        // h.insert(50);
        // h.insert(55);
        // h.insert(53);
        // h.insert(52);
        // h.insert(54);

        // h.printArr();

        // System.out.println();

        // h.deleteFromHeap();

        // h.printArr();

        int arr[]={-1,54,53,55,52,50};
        int n=5;
        for(int i=n/2 ; i>0 ;i--){
            h.heapify(arr,n,i);
        }
        for(int i=1;i<=n;i++){
            System.out.print(arr[i]+" ");
        }

    }

}
