package org.util.demo;
import java.io.BufferedInputStream;
import java.util.Scanner;
 
 
public class PerfectSquare {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * ����˼·:
         * ͨ����С�����ֵ�������С�����ƽ����,Ȼ�����ƽ������
         */
        Scanner cin = new Scanner(new BufferedInputStream(System.in)); 
        while(cin.hasNext()){
            int a = cin.nextInt();
            int b = cin.nextInt();
            int am = (int) Math.ceil(Math.sqrt(a));
            int bm = (int) Math.floor(Math.sqrt(b));
            if(bm < am){
                System.out.println(0);
            }else{
                System.out.println(bm - am + 1);
            }
             
        }
        cin.close();
 
    }
}