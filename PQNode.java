public class PQNode<T> { 
   public T data; 
   public double priority; 
   public PQNode<T> next;
   
   public PQNode() { 
      next = null; 
   }
   public PQNode(T e, double p) { 
      data = e; 
      priority = p; 
   }
}