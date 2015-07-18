package idv.hangermo.materialex.lazyroute;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import idv.hangermo.materialex.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<Team> teamList;
    private OnItemClickListener listener;

    public NewsAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    //建立ViewHolder，藉由ViewHolder做元件參照
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLogo;
        TextView tvName;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ivLogo = (ImageView)view.findViewById(R.id.ivLogo);
            tvName = (TextView)view.findViewById(R.id.tvName);
            cardView = (CardView)view.findViewById(R.id.cardView);
        }
    }

    //實做RecyclerView的點擊事件，因為RecyclerView並不像ListView有定義好的onItemClicker監聽器
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //將資料注入到View裡
        holder.ivLogo.setImageResource(teamList.get(position).getLogo());
        holder.tvName.setText(teamList.get(position).getName());

        //處理點擊事件
        if(listener != null) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickListener(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
