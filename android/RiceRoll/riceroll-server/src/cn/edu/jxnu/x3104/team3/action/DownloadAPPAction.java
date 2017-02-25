package cn.edu.jxnu.x3104.team3.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAPPAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1039499097707331929L;

	//getDownloadFile()方法返回的必须是InputStream。getResourceAsStream()方法可以通过流的方式将资源输出  
	/** 
     * 配置stream类型结果时，需指定inputName；inputName为方法名去掉get前缀，并且首字母小写的字符串； 
     * 比如此例中的inputName为 targetFile 
     *  
     * @return 
     */  
	public InputStream getDownloadFile() throws Exception {
		InputStream inputStream = 
				ServletActionContext.getServletContext().getResourceAsStream("/APK/JXNU_Library.apk");
		return   inputStream;
	}

	@Override  
	public String execute() throws Exception {  
		return SUCCESS;  
	}  

}
