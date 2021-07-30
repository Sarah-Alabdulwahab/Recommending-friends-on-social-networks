public class MGraph<K extends Comparable<K>> implements Graph<K> {
   public Map<K, List<K>> adj; // Do not change this
   public MGraph() {
      adj = new BSTMap<K, List<K>>();
   }
	// Add a node to the graph if it does not exist and return true. If the node already exists, return false.
   public boolean addNode(K i){
      List<K> list = new LinkedList<K>();
      return adj.insert(i, list);
   }

	// Check if i is a node
   public boolean isNode(K i){
      Pair <Boolean , List<K>> p = adj.retrieve(i);
      return p.first;
   }

	// Add an edge to the graph if it does not exist and return true. If i or j do not exist or the edge (i, j) already exists, return false.
   public boolean addEdge(K i, K j){
      Pair <Boolean , List<K>> pi = adj.retrieve(i);
      Pair <Boolean , List<K>> pj = adj.retrieve(j);
      boolean check= false;
      if(pi.first && pj.first){
         if(!pi.second.exists(j)){
            pi.second.insert(j);
            pj.second.insert(i);
            adj.update(i, pi.second);
            adj.update(j, pj.second);
            check = true;
         }
      }
      return check;
   }

	// Check if (i, j) is an edge.
   public boolean isEdge(K i, K j){
      Pair <Boolean , List<K>> p = adj.retrieve(i);
      if(p.first && p.second.exists(j)){//should only check if one is in the others adj list
            return true;
      }
      return false;
   }

	// Return the set of neighbors of node i. If i does not exist, the method returns null.
   public List<K> neighb(K i){
      Pair <Boolean , List<K>> p = adj.retrieve(i);
      if(p.first)
         return p.second;
      return null;
   }

	// Return the degree (the number of neighbors) of node i. If i does not exist, the method returns -1.
   public int deg(K i){
      Pair <Boolean , List<K>> p = adj.retrieve(i);
      if(p.first)
         return p.second.size();
      return -1;
   }   

	// Return a list containing the nodes in increasing order.
   public List<K> getNodes(){
      return adj.getKeys();
   }
}

