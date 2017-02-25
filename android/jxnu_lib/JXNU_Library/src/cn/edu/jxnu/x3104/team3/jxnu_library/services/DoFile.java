package cn.edu.jxnu.x3104.team3.jxnu_library.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DoFile {
	public String getFileCookie(String username, Context context) {
		String cookieString = null;
		File cookiefile = context.getFileStreamPath(username + ".cof");
		/* 判断是否存在文件cookies */
		if (cookiefile.exists()) {
			try {
				BufferedReader bfr = new BufferedReader(new InputStreamReader(
						new FileInputStream(cookiefile), "UTF-8"));
				cookieString = bfr.readLine();
				bfr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cookieString;
	}

	public Bitmap getUserPhoto(String username,Context context) {
	//	Log.d("test",username);
		Bitmap userphoto = null;
		String userid=username.substring(2);
		String imagefilepath = context.getFileStreamPath(userid
				+ ".jpg").getPath();// 获取文件路径
	//	Log.d("dofile", imagefilepath);
		userphoto=BitmapFactory.decodeFile(imagefilepath);
		return userphoto;

	}
}
