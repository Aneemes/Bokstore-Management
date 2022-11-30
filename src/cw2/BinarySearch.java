package cw2;
import java.util.ArrayList;
class BinarySearch { 
      
    // Returns index of x if it is present 
    // in arr[], else return -1 
    int binarySearch(ArrayList<Integer> quan, int x) 
    { 
  
        int l = 0, r = quan.size() - 1; 
  
        // Iterate until l <= r 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            // Check if x is at mid 
            if (quan.get(m) == x) 
                return m; 
  
            // If x greater than arr[m] 
            // then ignore left half 
            if (quan.get(m) < x) 
                l = m + 1; 
  
            // If x is smaller than arr[m] 
            // ignore right half 
            else
                r = m - 1; 
        } 
  
        // If we reach here, then element 
        // was not present 
        return -1; 
    } 
  
 
    
}
