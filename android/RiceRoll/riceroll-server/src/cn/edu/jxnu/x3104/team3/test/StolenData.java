/*package cn.edu.jxnu.x3104.team3.test;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.jxnu.x3104.team3.bean.Count;
import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;
import cn.edu.jxnu.x3104.team3.dao.CountDao;
import cn.edu.jxnu.x3104.team3.dao.CountDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDao;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDaoImpl;


*//**
 * 从指定站点获取并解析数据，并将其存入数据库<br>
 * 解决数据库资源匮乏
 *//*
public class StolenData {
	FoodMaterialDao foodMaterialDao = new FoodMaterialDaoImpl();
	CountDao countDao = new CountDaoImpl();
	String link ;	
	FoodMaterial foodMaterial = new FoodMaterial();
	Count count ;
	public StolenData(String address){		
		link = address;				
		count = countDao.findByType(0);		
		foodMaterial.setFood_material_id(String.format("%08d", (count.getCount_food_material()+1)));
		count.setCount_food_material(count.getCount_food_material()+1);
		foodMaterial.setFood_material_photo_link("0");
		new Thread(new Runnable(){
			public void run() {
				try {
					//将调料营养成分页面存进doc
					Document doc=Jsoup.connect(link).get();
					//取出调料名
					Elements nameSmall = doc.getElementsByTag("h2");
					String[] temp1 = nameSmall.first().toString().trim().split(">");
					String[] temp2 = temp1[1].split("<");
					String name = temp2[0].replace("营养成分", "");
					name = StringUtils.deleteWhitespace(name);	
					foodMaterial.setFood_material_name(name);
					//分取成分名
					Elements ths = doc.getElementsByTag("th");
					//分取含量及单位
					Elements tds = doc.getElementsByTag("td");
					int i = 0;
					for(Element th:ths){
						if(th.toString().contains("热量")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);
							if(tds.get(i).toString().contains("千卡")){
								foodMaterial.setFood_material_calories((int)number);
							}else{
								foodMaterial.setFood_material_calories((int)(number/1000));
							}							
						}
						
						if(th.toString().contains("蛋白质")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_protein((int)number);
							}else{
								foodMaterial.setFood_material_protein((int)(number*1000));
							}							
						}
						
						if(th.toString().contains("碳水化合物")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_sugar((int)number);
							}else{
								foodMaterial.setFood_material_sugar((int)(number*1000));
							}							
						}
						
						if(th.toString().contains("脂肪")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_fat((int)number);
							}else{
								foodMaterial.setFood_material_fat((int)(number*1000));				
							}							
						}
						
						if(th.toString().contains("膳食纤维")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);							
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_dietary_fiber((int)number);
							}else{
								foodMaterial.setFood_material_dietary_fiber((int)(number*1000));
							}
						}
						
						if(th.toString().contains("维生素C")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);							
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_vc((int)number);
							}else{
								foodMaterial.setFood_material_vc((int)(number*1000));							
							}
						}
						
						if(th.toString().contains("钙")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);							
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_calcium((int)number);
							}else{
								foodMaterial.setFood_material_calcium((int)(number*1000));								
							}
						}
						
						if(th.toString().contains("钾")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);							
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_muriate((int)number);
							}else{
								foodMaterial.setFood_material_muriate((int)(number*1000));								
							}
						}
						
						if(th.toString().contains("钠")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);							
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_natrium((int)number);
							}else{
								foodMaterial.setFood_material_natrium((int)(number*1000));								
							}
						}
						
						if(th.toString().contains("磷")){
							String[] temp = tds.get(i).toString().trim().split(">");
							String[] num = temp[1].split("<");
							String stringNumber = StringUtils.deleteWhitespace(num[0]);							
							double number = Double.parseDouble(stringNumber);			
							if(tds.get(i).toString().contains("毫克")){
								foodMaterial.setFood_material_phosphorus((int)number);
							}else{
								foodMaterial.setFood_material_phosphorus((int)(number*1000));								
							}
						}
						
						i++;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				foodMaterialDao.save(foodMaterial);
				countDao.update(count);
			}
		}).start();
		
	}
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		String link = "http://yingyang.911cha.com/Mjg=.html";
		StolenData test = new StolenData(link);
	}
	
	

}
*/