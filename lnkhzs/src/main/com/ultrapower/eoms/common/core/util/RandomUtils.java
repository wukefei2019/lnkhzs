package com.ultrapower.eoms.common.core.util;

import java.util.Random;


public class RandomUtils {
	
	public static String getRandomN(int n){
		StringBuffer ret = new StringBuffer();
		Random rand = new Random();
		rand.nextInt();
		for(int i=0;i<n;i++){
			ret.append(rand.nextInt(10));
		}
		return ret.toString();
	}
	
	public static void main(String[] args){
		for(int i=1;i<200;i++){
			System.out.println(getRandomN(6));
		}
	}
}
