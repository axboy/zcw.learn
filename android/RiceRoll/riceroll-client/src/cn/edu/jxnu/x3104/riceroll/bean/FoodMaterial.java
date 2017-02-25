package cn.edu.jxnu.x3104.riceroll.bean;

/**食材类*/
public class FoodMaterial {
	/**食材编号**/
	private String foodMaterial_id;	
	
	/**食材名字**/
	private String foodMaterial_name;
	
	/**食材图片链接地址**/
	private String foodMaterial_photo_link;
	
	/**蛋白质**/
	private int foodMaterial_protein;
	
	/**糖**/
	private int foodMaterial_sugar;
	
	/**脂肪**/
	private int foodMaterial_fat;
	
	/**膳食纤维**/
	private int foodMaterial_dietary_fiber;
	
	/**VC**/
	private int foodMaterial_vc;
	
	/**钙**/
	private int foodMaterial_calcium;
	
	/**氯**/
	private int foodMaterial_muriate;
	
	/**钠**/
	private int foodMaterial_natrium;
	
	/**磷**/
	private int foodMaterial_phosphorus;
	
	/**卡路里**/
	private int foodMaterial_calories;

	/**
	 * FoodMaterial构造函数，不包含加入冰箱时间
	 * 
	 * @param foodMaterial_id   调料编号
	 * @param foodMaterial_name   调料名字
	 * @param foodMaterial_photo_link   调料图片链接地址
	 * @param foodMaterial_protein   蛋白质
	 * @param foodMaterial_sugar   糖
	 * @param foodMaterial_fat   脂肪
	 * @param foodMaterial_dietary_fiber   膳食纤维
	 * @param foodMaterial_vc   VC
	 * @param foodMaterial_calcium   钙
	 * @param foodMaterial_muriate   氯
	 * @param foodMaterial_natrium   钠
	 * @param foodMaterial_phosphorus   磷
	 * @param foodMaterial_calories   卡路里
	 */
	public FoodMaterial(String foodMaterial_id, String foodMaterial_name,
			String foodMaterial_photo_link, int foodMaterial_protein,
			int foodMaterial_sugar, int foodMaterial_fat,
			int foodMaterial_dietary_fiber, int foodMaterial_vc,
			int foodMaterial_calcium, int foodMaterial_muriate,
			int foodMaterial_natrium, int foodMaterial_phosphorus,
			int foodMaterial_calories) {
		super();
		this.foodMaterial_id = foodMaterial_id;
		this.foodMaterial_name = foodMaterial_name;
		this.foodMaterial_photo_link = foodMaterial_photo_link;
		this.foodMaterial_protein = foodMaterial_protein;
		this.foodMaterial_sugar = foodMaterial_sugar;
		this.foodMaterial_fat = foodMaterial_fat;
		this.foodMaterial_dietary_fiber = foodMaterial_dietary_fiber;
		this.foodMaterial_vc = foodMaterial_vc;
		this.foodMaterial_calcium = foodMaterial_calcium;
		this.foodMaterial_muriate = foodMaterial_muriate;
		this.foodMaterial_natrium = foodMaterial_natrium;
		this.foodMaterial_phosphorus = foodMaterial_phosphorus;
		this.foodMaterial_calories = foodMaterial_calories;
	}	
	
	public String getFoodMaterial_id() {
		return foodMaterial_id;
	}
	public void setFoodMaterial_id(String foodMaterial_id) {
		this.foodMaterial_id = foodMaterial_id;
	}
	public String getFoodMaterial_name() {
		return foodMaterial_name;
	}
	public void setFoodMaterial_name(String foodMaterial_name) {
		this.foodMaterial_name = foodMaterial_name;
	}
	public String getFoodMaterial_photo_link() {
		return foodMaterial_photo_link;
	}
	public void setFoodMaterial_photo_link(String foodMaterial_photo_link) {
		this.foodMaterial_photo_link = foodMaterial_photo_link;
	}
	public int getFoodMaterial_protein() {
		return foodMaterial_protein;
	}
	public void setFoodMaterial_protein(int foodMaterial_protein) {
		this.foodMaterial_protein = foodMaterial_protein;
	}
	public int getFoodMaterial_sugar() {
		return foodMaterial_sugar;
	}
	public void setFoodMaterial_sugar(int foodMaterial_sugar) {
		this.foodMaterial_sugar = foodMaterial_sugar;
	}
	public int getFoodMaterial_fat() {
		return foodMaterial_fat;
	}
	public void setFoodMaterial_fat(int foodMaterial_fat) {
		this.foodMaterial_fat = foodMaterial_fat;
	}
	public int getFoodMaterial_dietary_fiber() {
		return foodMaterial_dietary_fiber;
	}
	public void setFoodMaterial_dietary_fiber(int foodMaterial_dietary_fiber) {
		this.foodMaterial_dietary_fiber = foodMaterial_dietary_fiber;
	}
	public int getFoodMaterial_vc() {
		return foodMaterial_vc;
	}
	public void setFoodMaterial_vc(int foodMaterial_vc) {
		this.foodMaterial_vc = foodMaterial_vc;
	}
	public int getFoodMaterial_calcium() {
		return foodMaterial_calcium;
	}
	public void setFoodMaterial_calcium(int foodMaterial_calcium) {
		this.foodMaterial_calcium = foodMaterial_calcium;
	}
	public int getFoodMaterial_muriate() {
		return foodMaterial_muriate;
	}
	public void setFoodMaterial_muriate(int foodMaterial_muriate) {
		this.foodMaterial_muriate = foodMaterial_muriate;
	}
	public int getFoodMaterial_natrium() {
		return foodMaterial_natrium;
	}
	public void setFoodMaterial_natrium(int foodMaterial_natrium) {
		this.foodMaterial_natrium = foodMaterial_natrium;
	}
	public int getFoodMaterial_phosphorus() {
		return foodMaterial_phosphorus;
	}
	public void setFoodMaterial_phosphorus(int foodMaterial_phosphorus) {
		this.foodMaterial_phosphorus = foodMaterial_phosphorus;
	}
	public int getFoodMaterial_calories() {
		return foodMaterial_calories;
	}
	public void setFoodMaterial_calories(int foodMaterial_calories) {
		this.foodMaterial_calories = foodMaterial_calories;
	}
}
