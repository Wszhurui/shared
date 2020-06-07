package com.example.demo.mytools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MarkTool {

	public static String analyse(String markText)
	{
		String title="";
		int tlen=0;
		markText=markText.replace(" ", "");//去空格
		markText=markText.replace("\n", "");//去换行
		markText=markText.replace("```", "");//去code段
		markText=markText.replace("hljs-center##","");//去标题的东西
		Pattern p=Pattern.compile(":::(.*?):::");
		Matcher m=p.matcher(markText);
		if(m.find())//如果有标题
		{
			title=m.group(0).replace(":::", "");
			tlen=title.length()+6;
		}
		markText=markText.substring(tlen);
		return title+"\n"+markText;
	}
}
