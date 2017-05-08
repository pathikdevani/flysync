package com.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.pathik.server.HTTPBaseTask;
import com.pathik.server.HTTPResponeUtil;
import com.pathik.service.MyService;
import com.util.TaskUtil;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import fi.iki.elonen.NanoHTTPD.Response;

public class TaskAppIcon implements HTTPBaseTask{
	
	private Context context = MyService.context;
	PackageManager manager = context.getPackageManager();
	InputStream def;
	InputStream is;
	Drawable logo;
	
	@Override
	public Response work(Map<String, String> args) {
		
		String pkg = args.get("package");
		if(pkg == null)
			return null;
		
		
		try {
			logo = manager.getApplicationIcon(pkg);
			if(logo != null){
				is = TaskUtil.drawbleToInputStream(logo);
			}else{
				if(def == null){
					logo = manager.getApplicationIcon(pkg);
					def = TaskUtil.drawbleToInputStream(logo);
				}	
				is = def;
			}
			try {
				return HTTPResponeUtil.newFixedFileResponse(is, "image/png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	


}
