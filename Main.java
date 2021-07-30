public class Main {

   private static void testPQK() {
      System.out.println("-------------------");
      PQK<Integer, String> pq = new PQKImp<Integer, String>(3);
      pq.enqueue(5, "A");
      pq.enqueue(3, "B");
      pq.enqueue(2, "C");
      pq.enqueue(1, "D");
      pq.enqueue(2, "E");
      pq.enqueue(4, "F");
      pq.enqueue(6, "G");
      pq.enqueue(7, "H");
      pq.enqueue(8, "I");
      pq.enqueue(8, "J");
      pq.enqueue(7, "K");
      pq.enqueue(6, "L");
      pq.enqueue(5, "M");
      while (pq.length() > 0) {
         Pair<Integer, String> p = pq.serve();
         System.out.println(p.first + "\t" + p.second);
      }
      System.out.println("-------------------");
   }

   private static void testMap() {
      System.out.println("-------------------");
      Map<Integer, Integer> m = new BSTMap<Integer, Integer>();
      for (int i = 100; i > 0; i--) {
         m.insert(i % 13, i);
      }
      List<Integer> l = m.getKeys();
      l.findFirst();
      while (true) {
         int k = l.retrieve();
         System.out.println(k);
         if (l.last()) {
            break;
         } else {
            l.findNext();
         }
      }
      System.out.println("-------------------");
   }

   private static void testGraph() {
      System.out.println("-------------------");
      int n = 8;
      Graph<Integer> g = new MGraph<Integer>();
      for (int i = 0; i < n; i++) {
         g.addNode(i);
      }
      for (int i = 0; i < n; i++) {
         g.addEdge(i, (i + 1) % n);
      }
      List<Integer> l = g.getNodes();
      l.findFirst();
      while (!l.last()) {
         int i = l.retrieve();
         List<Integer> ni = g.neighb(i);
         if (!ni.empty()) {
            ni.findFirst();
            while (true) {
               int j = ni.retrieve();
               System.out.println(i + "\t" + j);
               if (ni.last()) {
                  break;
               } else {
                  ni.findNext();
               }
            }
         }
         if (l.last()) {
            break;
         } else {
            l.findNext();
         }
      }
      System.out.println("-------------------");
   }

   private static void testRecommender() {
      System.out.println("-------------------");
      Graph<Integer> g = Recommender.read("graph.txt");
      {
         PQK<Double, Integer> top = Recommender.recommendPop(g, 3, 4);
         while (top.length() > 0) {
            Pair<Double, Integer> e = top.serve();
            System.out.println(e.second + "\t" + e.first);
         }
      }
      System.out.println("----------");
      {
         PQK<Double, Integer> top = Recommender.recommendCN(g, 3, 4);
         while (top.length() > 0) {
            Pair<Double, Integer> e = top.serve();
            System.out.println(e.second + "\t" + e.first);
         }
      }
      System.out.println("-------------------");
   }
   
   private static void testRemove() {/////////////////////
      System.out.println("-------------------");
      System.out.println("");
      BSTMap<Integer, Integer> bst = new BSTMap<Integer, Integer>();
      bst.insert(20, 23);
      bst.insert(10, 26);
      bst.insert(50, 20);
      bst.insert(5, 22);
      bst.insert(15, 72);
      bst.insert(30, 24);
      bst.insert(55, 2);
      bst.insert(12, 6);
      bst.insert(16, 7);
      bst.insert(21, 24);
      bst.insert(35, 2);
      bst.insert(51, 6);
      bst.insert(60, 7);
      bst.insert(11, 24);
      bst.insert(40, 2);
   
      List<Integer> l = bst.getKeys();
      l.findFirst();
      int k;
      while (true) {
         k = l.retrieve();
         System.out.println(k);
         if (l.last()) {
            break;
         } else {
            l.findNext();
         }
      }
      System.out.println("----------Root: "+ bst.root.key+", size: "+bst.size()+", current: "+bst.current.key);
   
      bst.remove(20);
      bst.remove(50);
      bst.remove(21);
      bst.remove(30);
      bst.remove(35);
      bst.remove(40);
      bst.remove(51);
      bst.remove(55);
      bst.remove(60);
   
      l = bst.getKeys();
      l.findFirst();
      while (true) {
         k = l.retrieve();
         System.out.println(k);
         if (l.last()) {
            break;
         } else {
            l.findNext();
         }
      }
   
      System.out.println("-------------------Root: "+ bst.root.key+", size: "+bst.size()+", current: "+bst.current.key);
   }
   
   private static void testEdge() {///////////////////////
      System.out.println("-------------------");
      int n = 5;
      Graph<Integer> g = new MGraph<Integer>();
      for (int i = 0; i < n; i++) {
         g.addNode(i);
      }
      g.addEdge(1,2);
      g.addEdge(4,2);
      g.addEdge(1,3);
      g.addEdge(2,0);
      g.addEdge(2,3);
      g.addEdge(4,0);
      List<Integer> l = g.getNodes();
      int len = l.size();
      
      l.findFirst();
      for (int k = 0; k<len; k++)
      {
         int vertex = l.retrieve();
         List<Integer> edges = g.neighb(vertex);
         System.out.print(vertex + "\t ==> \t" );
         edges.findFirst();
         for (int w = 0; w< edges.size(); w++){ // loop on all edges
            System.out.print(edges.retrieve() + "\t");
            edges.findNext();
         }
         System.out.println();
         l.findNext();
      }
   
      System.out.println("-------------------");
      /*if(g.isEdge(0,2))
         System.out.println("edge 0,2");
      else
         System.out.println("NOT edge 0,2");
         
      if(g.isEdge(10,90))
         System.out.println("edge 10,90");
      else
         System.out.println("NOT edge 10,90");
         
         */
         
         check(g,0,2);
         check(g,10,3);
         check(g,2,3);
         
      
        
   }

public static void check (Graph<Integer> g, int n1, int n2)
{
System.out.println("Checking " + n1 + " and " + n2);
if(g.isNode(n1)) 
   System.out.print( n1 + " is a node ");
else
   System.out.print( n1 + " is NOT a node ");
   
if(g.isNode(n2)) 
   System.out.print( n2 + " is a node ");
else
   System.out.print( n2 + " is NOT a node ");
   
if(g.isEdge(n1,n2)) 
   System.out.print( n2 + " is a neighbor of " + n1);
else
   System.out.print( n1 + " is NOT a neighbor of " + n2);
   System.out.println();
  } 


   public static void main(String[] args) {
   	testPQK();
   	testMap();
   	testGraph();
   	testRecommender();
      //testRemove();
      //testEdge();
   }
}