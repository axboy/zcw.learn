package cn.edu.jxnu.x3104.team3.jxnu_library.parameters;

public class Gnxw { 		//馆内新闻
	public String news_url;
	public String news_title;
	public String news_date;
	public Gnxw(String id,String title,String date){
		news_url=id;			//url
		news_title=title;	//标题
		news_date=date;		//时间
	}
}