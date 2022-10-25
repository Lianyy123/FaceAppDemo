package com.firefly.faceApi.V2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ChatAdapter extends BaseAdapter  {

  public ArrayList<String> data = new ArrayList();

    private  List<String>         dataList;
ImageView image_edit,image_delete;
    private  View.OnClickListener listener;



    public ChatAdapter(View.OnClickListener listener , ArrayList<String> dataList) {
        this.listener=listener;
        //获得布局解析器

        data=dataList;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder=new ViewHolder();
        if (view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_list,null);
//            inspect= (TextView) messageLayout.findViewById(R.id.inspect);
            //viewHolder.message=  view.findViewById(R.id.friend_message_text_view);
            viewHolder.friendName=  view.findViewById(R.id.friend_name_text_view);
            viewHolder.delete=view.findViewById(R.id.image_delete);
            view.setTag(viewHolder);




        }
        else {

            viewHolder= (ViewHolder) view.getTag();

        }
        //viewHolder.message.setText((String) data.get(i).get("message"));
        viewHolder.friendName.setText((String) data.get(i));
        viewHolder.delete.setOnClickListener(listener);
        //通过setTag 将被点击控件所在条目的位置传递出去
        viewHolder.delete.setTag(i);

        return view;
    }





    public class ViewHolder{

        ImageView delete;
        private TextView friendName;


    }

}
