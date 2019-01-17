package com.example.apple.scrolldemo.recycle.one;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apple.scrolldemo.R;
import com.example.apple.scrolldemo.recycle.one.model.Message;
import com.example.apple.scrolldemo.recycle.one.model.MessageDirection;
import com.example.apple.scrolldemo.recycle.one.model.TextMessage;

import java.util.List;

/**
 * @author crazyZhangxl on 2019/1/15.
 * Describe: 多布局实现聊天通信 纯UI
 */
public class SessionAdapter extends RecyclerView.Adapter<OneViewHolder>{
    private Context mContext;
    private List<Message> mMessagesList;
    private static final int SEND_TEXT = R.layout.item_text_send;
    private static final int RECEIVE_TEXT = R.layout.item_text_receive;
    private static final int UNDEFINE_MSG = R.layout.item_no_support_msg_type;


    /**
     * 构造函数----
     * @param context
     * @param messagesList
     */
    public SessionAdapter(Context context, List<Message> messagesList) {
        mContext = context;
        mMessagesList = messagesList;
    }

    /**
     * 创建ViewHolder
     * @param viewGroup 父亲组件
     * @param viewType  文本布局资源文件[对应getItemViewType返回的类型]
     * @return
     */
    @NonNull
    @Override
    public OneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new OneViewHolder(LayoutInflater.from(mContext).inflate(viewType, viewGroup,false), mContext);
    }

    /**
     * 绑定ViewHolder设置初始化
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull OneViewHolder viewHolder, int position) {
        // 设置时间
        setTime(viewHolder,position);
        setName(viewHolder,position);
        setContent(viewHolder,position);
    }

    private void setName(OneViewHolder viewHolder, int position) {
        TextView tvName =  viewHolder.mConvertView.findViewById(R.id.tvName);
        tvName.setText(mMessagesList.get(position).getSendName());
    }

    private void setContent(OneViewHolder viewHolder, int position) {
        Message message = mMessagesList.get(position);
        if (message instanceof TextMessage){
            TextView tvMessage =  viewHolder.mConvertView.findViewById(R.id.tvText);
            TextMessage textMessage = (TextMessage) mMessagesList.get(position);
            tvMessage.setText(textMessage.getTextInfo());
        }
    }

    private void setTime(OneViewHolder viewHolder, int position) {
        TextView tvTime = viewHolder.mConvertView.findViewById(R.id.tvTime);
        Message message = mMessagesList.get(position);
        long sendTime = message.getSendTime();
        if (position > 0){
            long preTime = mMessagesList.get(position - 1).getSendTime();
            if (sendTime - preTime > 5*60*1000){
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(TimeUtils.getInstance().longToTime(sendTime));
            }else {
                tvTime.setVisibility(View.GONE);
            }
        }else {
            tvTime.setVisibility(View.VISIBLE);
            tvTime.setText(TimeUtils.getInstance().longToTime(sendTime));
        }
    }

    /**
     * 对应各种消息类型[多布局的核心]
     * @param position 对应的下标position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int viewType = UNDEFINE_MSG;
        Message message = mMessagesList.get(position);
        boolean isSend = message.getDirection() == MessageDirection.SEND.integerValue;
        switch (message.getMessageType()){
            case TEXT:
                viewType = isSend?SEND_TEXT:RECEIVE_TEXT;
                break;
            case IMAGE:
                break;
                default:
                    break;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        if (mMessagesList == null){
            return 0;
        }
        return mMessagesList.size();
    }
}
