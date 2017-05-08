package com.file;

import java.util.ArrayList;
import java.util.Map;

import com.file.Storage;
import com.file.StorageUtils;
import com.pathik.server.main.WSBaseTask;
import com.pathik.service.MyService;

import android.content.Context;

public class TaskStorageList implements WSBaseTask {

	private Context context = MyService.context;
	private ArrayList<Item> items = new ArrayList<>();

	@Override
	public Object work(String[] args) {
		items.clear();
		Map<Integer, Storage> storages = StorageUtils.getStorageList(context, false);
		for (int i = 0; i < storages.size(); i++) {
			Storage storage = storages.get(i);
			
			Item item = new Item();
			item.title = storage.getTitle();
			item.root = storage.getRootPath();
			item.total = storage.getGBTotal();
			item.free = storage.getGBFree();
			item.used = storage.getGBUsed();
			item.write = storage.isWritable();
					
			items.add(item);	
			
		}
		return items;
	}

	@SuppressWarnings("unused")
	private class Item {
		String title;
		String root;
		String total;
		String free;
		String used;
		boolean write;
	}

}
