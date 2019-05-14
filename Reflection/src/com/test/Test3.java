package com.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Test3 {

	public static void main(String[] args) throws Exception {

		U_1 u1=new U_1("deepak!",25,"AAA");
		U_2 u2=new U_2("deepak!",205,"AAA","aher");
		
		System.out.println("------- BEFORE ---------");
		
		System.out.println("U1: "+u1);
		System.out.println("U2: "+u2);
		
		Map<String,Object> finalValMap = new Test3().convertMasterToTemp(u2,u1);
		
		System.out.println("------- AFTER ---------");
		
		for (String key : finalValMap.keySet()) {
			System.out.println(key+"  ---> "+finalValMap.get(key));
		}
	
	}
	
	
	public Map<String,Object>  convertMasterToTemp(Object master,Object temp) throws Exception{
		Map<String,Object> tempValMap =new HashMap<>();
		Map<String,Object> finalValMap =new HashMap<>();
		try {
			
			//----------------------------------------
			Class tclass = temp.getClass();
			Field [] tFields = tclass.getDeclaredFields();
			
			for (Field tField : tFields) {
				tField.setAccessible(true);
				if(tField.get(temp)!= null)
					tempValMap.put(tField.getName(), tField.get(temp)); // fieldName = FiledValue
			}
			
			//----------------------------------------
			
			Class mclass = master.getClass();
			Field [] mFields = mclass.getDeclaredFields();
			
			for (Field mField : mFields) {
				mField.setAccessible(true);
				
				if(tempValMap.get(mField.getName())!= null && mField.get(master) != null) {

					// IF VALUE IS NOT NULL
					
					String tempFieldValue = String.valueOf(tempValMap.get(mField.getName()));
					String masterFieldValue = String.valueOf(mField.get(master));
					
					if(!masterFieldValue.equals(tempFieldValue)) {
						// HIGHLITE IN BEAN
						finalValMap.put(mField.getName(), "Old value is : "+masterFieldValue);
					}else {
						finalValMap.put(mField.getName(), "");  // same
					}
					
				}else {
					// IF VALUE IS NULL
					if(tempValMap.get(mField.getName()) == null && mField.get(master) != null) {
						finalValMap.put(mField.getName(), "Old value is : "+String.valueOf(mField.get(master)));
					}else if(tempValMap.get(mField.getName()) != null && mField.get(master) == null) {
						finalValMap.put(mField.getName(), "Old value is :"+null);
					}else {
						finalValMap.put(mField.getName(),  ""); // same 
					}
				}
					
			}
			
			//----------------------------------------
		} catch (Exception e) {
			throw e;
		}
		return finalValMap;
	}
	
	
}
