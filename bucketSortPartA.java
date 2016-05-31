
import java.util.ArrayList;
import java.util.Collections;

public class bucketSortPartA{

	
	
	
	 public static int[] bucketSort(int[] numbers, int bucketCount) {

		 if (numbers.length <= 1) return numbers;
		 int maxVal = numbers[0];
		 int minVal = numbers[0];

		 for (int i = 1; i < numbers.length; i++) {
		 if (numbers[i] > maxVal) maxVal = numbers[i];
		 if (numbers[i] < minVal) minVal = numbers[i];
		 }

		 double interval = ((double)(maxVal - minVal + 1)) / bucketCount; // range of bucket
		 ArrayList<Integer> buckets[] = new ArrayList[bucketCount];

		 for (int i = 0; i < bucketCount; i++) // initialize buckets (initially empty)
		 buckets[i] = new ArrayList<Integer>();

		 for (int i = 0; i < numbers.length; i++) // distribute numbers to buckets
		 buckets[(int)((numbers[i] - minVal)/interval)].add(numbers[i]);
		 
		 
		 
try{
		 for (int i = 0; i < buckets.length; i++) {
			 SortModifiedPartA b1=new SortModifiedPartA(buckets[i]);
			 Thread t1= new Thread(b1); // creates thread
			 t1.start();
			 t1.join();
		     System.out.println("Bucket"+i+buckets[i]);
		 }
}
catch(Exception e){
	e.printStackTrace();
}
		 int k = 0;
		 for (int i = 0; i < buckets.length; i++) {
		 for (int j = 0; j < buckets[i].size(); j++) { // update array with the bucket content
		 numbers[k] = buckets[i].get(j);
		 k++;
		 }
		 }

		 return numbers;
	 }
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
			int[] a= {2,5,3,10,23,12,14,8,9};
			int[] result=bucketSort(a,3);
			for (int i=0;i<result.length;i++)
			System.out.println(result[i]);
					
		}
	 }
