import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes method. If i does not exist, return null. 
   //In case of a tie, users with the lowest id are selected.
   public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
      List<K> users = g.getNodes();//increasing order
      if(!users.exists(i))
         return null;
      PQK<Double, K> pqk= new PQKImp<Double, K>(k);
      List<K> ni = g.neighb(i);//user's neighbors
      users.findFirst();
      ni.findFirst();
      K key;
      double deg;
      for(int j = 0; j < users.size() ; j++){
         key = users.retrieve();
         if(i.equals(key)){//not including user i
            users.findNext();
            continue;
         }
         deg = g.deg(key);
         if(!ni.exists(key))
            pqk.enqueue(deg, key);
         users.findNext();
      }
      return pqk;
   }

	// Return the top k recommended friends for user i using common neighbors method. If i does not exist, return null. 
   //In case of a tie, users with the lowest id are selected.
   public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
      List<K> users = g.getNodes();//increasing order
      if(!users.exists(i))
         return null;
      PQK<Double, K> pqk= new PQKImp<Double, K>(k);
      List<K> ni = g.neighb(i);//user's neighbors
      users.findFirst();
      ni.findFirst();
      K key;
      double common = 0;
      List<K> nkey;//neighbors of key
      for(int j = 0; j < users.size(); j++){
         key = users.retrieve();
         if(i.equals(key)){//not including user i
            users.findNext();
            continue;
         }
         common = 0;
         nkey = g.neighb(key);
         nkey.findFirst();
         for(int w = 0; w < nkey.size(); w++){
            if(ni.exists(nkey.retrieve()))
               common++;
            nkey.findNext();
         }
         if(!ni.exists(key))
            pqk.enqueue(common, key);
         users.findNext();
      }
      return pqk;
   }

	// Read graph from file. The file is a text file where each line contains an edge. The end and start of the edge are separated by space(s) or tabs.
   public static Graph<Integer> read(String fileName) {
      try {
         Graph<Integer> g = new MGraph<Integer>();
         Scanner scanner = new Scanner(new File(fileName));
         while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            g.addNode(i);
            int j = scanner.nextInt();
            g.addNode(j);
            g.addEdge(i, j);
         }
         scanner.close();
         return g;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }
}
