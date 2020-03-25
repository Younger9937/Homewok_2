package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class TikTokAdapter extends RecyclerView.Adapter<TikTokAdapter.NumberViewHolder> {

    private static final String TAG = "TikTokAdapter";
    private int mNumberItems;
    private final ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    private List<Message> list;//数据源

    public TikTokAdapter(int numListItems, ListItemClickListener listener,List<Message> messages) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        list = messages;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {//创建ViewHolder
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView viewHolderName;
        private final TextView viewHolderContent;
        private final TextView viewHolderDate;
        private final CircleImageView viewHolderHeadImage;
        private final ImageView viewHolderOfficial;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            viewHolderName = (TextView) itemView.findViewById(R.id.tv_title);
            viewHolderContent= (TextView) itemView.findViewById(R.id.tv_description);
            viewHolderDate = (TextView) itemView.findViewById(R.id.tv_time);
            viewHolderHeadImage = itemView.findViewById(R.id.iv_avatar);
            viewHolderOfficial =itemView.findViewById(R.id.robot_notice);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            viewHolderName.setText(list.get(position).getTitle());
            viewHolderContent.setText(list.get(position).getDescription());
            viewHolderDate.setText(list.get(position).getTime());

            String headImage = list.get(position).getIcon();
            switch (headImage){
                case "TYPE_ROBOT":viewHolderHeadImage.setImageResource(R.drawable.session_robot);break;
                case "TYPE_GAME":viewHolderHeadImage.setImageResource(R.drawable.icon_micro_game_comment);break;
                case "TYPE_SYSTEM":viewHolderHeadImage.setImageResource(R.drawable.session_system_notice);break;
                case "TYPE_STRANGER":viewHolderHeadImage.setImageResource(R.drawable.session_stranger);break;
                case "TYPE_USER":viewHolderHeadImage.setImageResource(R.drawable.icon_girl);break;
                default:break;
            }

            if(list.get(position).isOfficial()){
                viewHolderOfficial.setVisibility(View.VISIBLE);
            }
            else{
                viewHolderOfficial.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick");
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

}
