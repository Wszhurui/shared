package com.example.demo.mytools;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Idea;

public class JsonTool {
	public static String toJsonstring(List<Idea> ideas)
	{
		if(ideas==null||ideas.size()==0)
		{
			return null;
		}
		int len;
		String json="[";
		for(Idea j: ideas)
		{
			json+=JSONObject.toJSONString(j);
			json+=",";
		}
		len=json.length();
		json=json.substring(0,len-1);
		json=json+"]";
		return json;
	}

}
