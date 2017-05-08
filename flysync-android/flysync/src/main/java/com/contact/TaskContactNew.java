package com.contact;

import com.pathik.server.main.WSBaseTask;
import com.pathik.service.MyService;

import android.content.ContentResolver;
import android.content.Context;

public class TaskContactNew implements WSBaseTask{
	private Context context = MyService.context;
	
	@Override
	public Object work(String[] args) {
		String _ID = args[0];
		ContentResolver resolver = context.getContentResolver();
		return null;
	}

}
