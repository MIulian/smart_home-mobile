package com.example.myapplication.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterBoard extends RecyclerView.Adapter<CustomAdapterBoard.ViewHolder> {
    private List<Board> dataList;
    private Context context;
    private OnBoardListener mBoardListener;

    public CustomAdapterBoard(Context context,List<Board> dataList, OnBoardListener onBoardListener) {
        this.context = context;
        this.dataList = dataList;
        this.mBoardListener = onBoardListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTitle, txtHour;
        ImageView coverImage;
        OnBoardListener boardListener;

        public ViewHolder(View itemView, OnBoardListener boardListener) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.board_nameID);
            txtHour = itemView.findViewById(R.id.board_hourID);
            coverImage = itemView.findViewById(R.id.board_imageID);
            this.boardListener = boardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            boardListener.onClickListener(getAdapterPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.boardlist_row, parent, false);
        return new ViewHolder(view, mBoardListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getBoardName());
        holder.txtHour.setText(dataList.get(position).getBoardStart());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(R.drawable.boardimg)
                .placeholder((R.drawable.boardimg))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("userName", dataList.get(position).getUsername());
//                intent.putExtra("serial", dataList.get(position).getBoardSerial());
//                intent.putExtra("id", dataList.get(position).getBoardId());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnBoardListener{
        void onClickListener(int position);
    }
}
