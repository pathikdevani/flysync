package com.contact;

import java.util.ArrayList;

import com.pathik.server.main.WSBaseTask;
import com.pathik.service.MyService;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by Pathik on 12/25/2015.
 */
public class TaskGroupList implements WSBaseTask{

    private Context context = MyService.context;
   

    @Override
    public Object work(String[] obj) {
        ContentResolver resolver = context.getContentResolver();
        Ans ans = new Ans();



        Cursor cursor= resolver.query(ContactsContract.Groups.CONTENT_URI,
                new String[] {
                        ContactsContract.Groups._ID,
                        ContactsContract.Groups.TITLE
                },
                null, null, null
        );
        if(cursor != null){
            final int indexID = cursor.getColumnIndex(ContactsContract.Groups._ID);
            final int indexTITLE = cursor.getColumnIndex(ContactsContract.Groups.TITLE);

            String id,title;
            while (cursor.moveToNext()){
                id = cursor.getString(indexID);
                title = cursor.getString(indexTITLE);

                Ans.Group group = ans.new Group();
                group.id = id;
                group.title = title;
                ans.groups.add(group);
            }
            cursor.close();
        }
        return ans;
    }

    @SuppressWarnings("unused")
    private class Ans{
        ArrayList<Group> groups;

        public Ans(){
            groups = new ArrayList<>();
        }
        public class Group{
			String id;
            String title;
        }
    }
}
