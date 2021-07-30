public class LinkedList<T> implements List<T>{
   private Node<T> head;
   private Node<T> current;
   private int size;
	
   public LinkedList() {
      head = current = null;
      size = 0;
   }
	
   public boolean empty() {
      return head == null;
   }
	
   public boolean full() {
      return false;
   }
	
   public void findFirst() {
      current= head;
   }
	
   public void findNext() {
      current= current.next;
   }
	
   public boolean last() {
      return current.next == null;		
   }
	
   public T retrieve() {
      return current.data;
   }
	
   public void update(T e) {
      current.data= e;
   }
	
   public void insert(T e) {
      Node<T> tmp; 
      if (empty()) { 
         current = head = new Node<T> (e);
      } 
      else { 
         tmp = current.next;
         current.next = new Node<T> (e); 
         current = current.next; 
         current.next = tmp; 
      }
      size++;
   }
	
   public void remove() {
      if(empty())
         return;
      if(current == head)
         head= head.next;
      else {
         Node<T> temp= head;
         while(temp.next != current) 
            temp= temp.next;
         temp.next= current.next;
      }
      if(current.next == null)
         current= head;
      else
         current= current.next;
      size--;
   }
      
   public int size(){
      return size;
   }
   
   public boolean exists(T e){
      if(empty())
         return false;
      Node<T> temp= head;
      while(temp != null) {
         if(temp.data.equals(e)) 
            return true;
         temp= temp.next;
      }
      return false;
   }
   
}