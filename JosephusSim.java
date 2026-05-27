import java.util.*;
import java.io.*;

public class JosephusSim {
   private PersonNode circle;     // a PersonNode pointer that tracks first node
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       
   private PersonNode track;      // a PersonNode pointer to help with elimination

   public JosephusSim(String fileName) {
      try {
         // load names from the file in order, generating a singly linked list of PersonNodes
         Scanner file = new Scanner(new File("people.txt"));
         while (file.hasNextLine()){
            String name = file.nextLine().trim();
            if(!name.isEmpty()){
               add(name);
               size++;
            }
         }
         
         // make the ring circular by attaching last node's next to front
         PersonNode last = circle;
         while(last.next != null) {
            last = last.next;
         }
         last.next = circle;
         // remember the last node as the one in front of the next to get eliminated
         track = last;
         
         // generate, print, and save the random elimination count
         Random rand = new Random();
         int maxRange = Math.max(1, size/2);
         eliminationCount = rand.nextInt(maxRange) + 1;
         
         System.out.println("=== Elimination count is " + eliminationCount + " ===");
         
      } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
   }
   
   public void eliminate() {
      // count to the elimination count
      for (int i = 0; i < eliminationCount; i++) {
         track = track.next;
      }
      
      // print who will be eliminated
      PersonNode victim = track.next;
      System.out.println(track.next + "eliminated!");
      
      // eliminate the person and update "front" of the circle and size
      track.next = victim.next;
      size--;
      
      circle = track.next;
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      return size == 1;
   }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      
      // print the remaining survivors (watch out for infinite loop since list is circular)

      return "";
   }

}