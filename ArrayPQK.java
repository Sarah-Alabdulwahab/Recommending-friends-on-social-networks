public class ArrayPQK <P extends Comparable<P>, T> implements PQK<P, T> {
   private int size; 
   private PQNode<T> head;
   
   /*tail is of no use here*/ 
   public ArrayPQK() { 
      head = null; 
      size = 0; 
   }
   
	// Return the length of the queue
   public int length(){
      return size;
   }

	// Enqueue a new element. The queue keeps the k elements with the highest priority. In case of a tie apply FIFO.
   public void enqueue(P pr, T e){
      PQNode<T> tmp = new PQNode<T>(e, pr); 
      if((size == 0) || (pr > head.priority)) { 
         tmp.next = head; head = tmp; 
      } 
      else { 
         PQNode<T> p = head; 
         PQNode<T> q = null; 
         while((p != null) && (pr <= p.priority)) {
            q = p; 
            p = p.next; 
         } 
         tmp.next = p; 
         q.next = tmp; 
      }
      size++;
   
   }

	// Serve the element with the highest priority. In case of a tie apply FIFO.
   public Pair<P, T> serve(){
      PQNode<T> node = head; 
      Pair<P, T> pqe = new Pair<T>(node.data,node.p); 
      head = head.next; 
      size--; 
      return pqe;

   }
   
  
}
