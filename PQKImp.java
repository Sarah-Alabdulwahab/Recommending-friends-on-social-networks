public class PQKImp <P extends Comparable <P>, T> implements PQK<P, T>{
   private int maxsize;
   private int size; 
   private int head, tail; 
   private T[] data; //holds data
   private P[] prio; //holds priorities
   
   @SuppressWarnings("unchecked")
   public PQKImp(int k){
      maxsize = k; 
      size = 0; 
      head = tail = 0; 
      data = (T[])new Object[k]; 
      prio = (P[])new Comparable[k + 1]; 
   }
	   
   public int length () { 
      return size; 
   }
   // Enqueue a new element. The queue keeps the k elements with the highest priority. In case of a tie apply FIFO.
   public void enqueue(P pr, T e){
      if(size == 0){
         prio[0] = pr;
         data[0] = e;
         size++;
         return;
      }
      if(size == maxsize)
         if(prio[size-1].compareTo(pr) >= 0)
            return;
         else
            size--;
      int i = size - 1;
      while(prio[i].compareTo(pr) < 0){
         prio[i+1] = prio[i];
         data[i+1] = data[i];
         i--;
         if(i == -1) 
            break;
      }
      data[i + 1] = e;
      prio[i + 1] = pr;
      size++;
   }
   // Serve the element with the highest priority. In case of a tie apply FIFO.
   public Pair<P, T> serve(){
      T e = data[0]; 
      P p = prio[0];
      for(int i = 0; i < size - 1; i++){
         prio[i] = prio[i+1];
         data[i] = data[i+1];
      }
      size--; 
      return new Pair<P, T>(p,e);
   }
   
}
