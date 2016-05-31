import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;



class SortModifiedPartA implements Runnable{
	ArrayList<Integer> num;
	
	//constructor
	SortModifiedPartA(ArrayList<Integer> numbrs){
	
		num=numbrs;
	}
	 public void run() {
	
		 Collections.sort(num); 
	 }
	 

}