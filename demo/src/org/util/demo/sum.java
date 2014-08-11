package org.util.demo;

/**
 * 求从10到100中能被3或5整除的数的和
 * @author Administrator
 *
 */
public class sum {

	public static void main (String [] args){
		long a  = System.nanoTime();
		int sum = 0;
		for (int i = 10 ;i<100; i++){
			if (i%3==0||i%5==0){
				sum+=i;
			}
		}
		System.out.println(sum);
		System.out.println((System.nanoTime()-a));
	}
	
}