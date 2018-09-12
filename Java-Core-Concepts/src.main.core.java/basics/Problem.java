package basics;

import java.util.Random;

/**
 * Creating randomized data - testing Random() method
 * */
public class Problem {
	
	 private final int[] list = new int[2000000];

	    public Problem() {
	        Random generator = new Random(19580427);
	        
	        for (int i = 0; i < list.length; i++) {
	        	
	            list[i] = generator.nextInt(500000);
	        }
	    }

	    public int[] getList() {
	        return list;
	    }

}
