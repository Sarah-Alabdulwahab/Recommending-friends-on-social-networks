public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
   public BSTNode<K, T> root; // Do not change this
   public BSTNode<K, T> current;
   public int size;

   public BSTMap() {
      root = current = null;
      size = 0;
   }
   
   //not in interface
   private boolean empty(){
      return root == null;
   }
   
   // Return the size of the map.
   public int size(){
      return size;
   }

	// Return true if the map is full.
   public boolean full(){
      return false; //never full
   }

	// Remove all elements from the map.
   public void clear(){
      root = current = null;
      size = 0;
   }

	// Update the data of the key k if it exists and return true. If k does not exist, the method returns false.
   public boolean update(K k, T e){
      if(!findkey(k))
         return false;
      current.data = e;      
      return true;
   }

	// Search for element with key k and returns a pair containing true and its data if it exists. 
   //If k does not exist, the method returns false and null.
   public Pair <Boolean , T> retrieve(K k){
      if(!findkey(k))
         return new Pair <Boolean , T> (false, null);
      return new Pair <Boolean , T> (true, current.data);
   }

	// Insert a new element if it does not exist and return true. If k already exists, return false.
   public boolean insert(K k, T e){
      if(empty()){
         root = current = new BSTNode<K,T>(k, e);
         size++; 
         return true;
      }
      
      BSTNode<K,T> temp = current;
      if(findkey(k)){//moves current
         current = temp; //returning current to its original place
         return false; //bc it already exists
      }
      if(current.key.compareTo(k) > 0)
         current = current.left = new BSTNode<K,T>(k, e); 
      else
         current = current.right = new BSTNode<K,T>(k, e); 
      size++;
      return true;
   }
   
   //not in interface
   private boolean findkey(K tkey){
      BSTNode<K,T> temp1 = root;
      BSTNode<K,T> temp2 = root;
      if(empty())
         return false;
      while(temp1 != null){
         temp2 = temp1;
         if(temp1.key == tkey){
            current = temp1;
            return true;
         }
         else if(temp1.key.compareTo(tkey) > 0)
            temp1 = temp1.left;
         else
            temp1 = temp1.right;
      }//end while
      current= temp2;
      return false;
   }


	// Remove the element with key k if it exists and return true. If the element does not exist return false.
   public boolean remove(K k){
      if(!findkey(k))
         return false;//not found
      //boolean removed = false;
      BSTNode<K, T> p = remove(k, root);//, removed); 
      //find the key and if removed (true & will return the modified tree) else (false). 
      current = root = p;
      size--;
      return true;
   }
   
   private BSTNode<K, T> remove(K key, BSTNode<K, T> p){//, boolean flag){
      BSTNode<K, T> q, child = null;
      if(p == null)
         return null;
      if(p.key.compareTo(key) > 0)
         p.left = remove(key, p.left); //move left
      else if(p.key.compareTo(key) < 0)
         p.right = remove(key, p.right); //move right
      else{//else 1
         if(p.left != null && p.right != null){ //2 children
            q = findMin(p.right);
            p.key = q.key;
            p.data = q.data;
            p.right = remove(q.key, p.right);
         }//end if
         else{//else 2
            if(p.right == null)
               child = p.left;
            else if(p.left == null)
               child = p.right;
            return child;
         }//end else 2
      }//end else 1
      return p;
   }
   
   private BSTNode<K, T> findMin(BSTNode<K, T> p){
      if(p == null)
         return null;
      while(p.left != null)
         p = p.left;
      return p;
   }

	// Return the list of keys in increasing order.
   public List<K> getKeys(){
      List<K> list = new LinkedList<K>();
      if(!empty()){
         BSTNode<K, T> temp = root;
         list = getKeys(temp, list);
      }
      return list;
   }
   
   private List<K> getKeys(BSTNode<K, T> temp, List<K> list){
      if(temp == null)
         return list;
      //insert here if preorder
      list = getKeys(temp.left, list);
      list.insert(temp.key);//inorder
      list = getKeys(temp.right, list);
      //insert here if postorder
      return list;
   }
}
