package com.ultrapower.lnkhzs.quality.utils;

import java.util.ArrayList;
import java.util.List;

public class CompareUtil {

	public static List<String[]> compareList(List<String> list1, List<String> list2){
		List<String[]> returnList = new ArrayList<String[]>();
		
		for(String str1 : list1) {
			if(!list2.contains(str1)){
				returnList.add(new String[] {str1,""});
				 System.out.println(str1);
			}
		}
		
		for(String str1 : list2) {
			if(!list1.contains(str1)){
				returnList.add(new String[] {"",str1});
			}
		}
		
		return returnList;
	}
	
}