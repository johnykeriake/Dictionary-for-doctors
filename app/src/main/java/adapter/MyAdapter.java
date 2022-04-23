package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.z.doctor.R;
import com.z.doctor.RecyclerViewClickInterface;

import java.util.ArrayList;
import java.util.List;

import model.OneWord;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {


    private Context context;
    private   List<OneWord> list;
    private RecyclerViewClickInterface recyclerViewClickInterface;


    public MyAdapter(Context context, List<OneWord> list,RecyclerViewClickInterface recyclerViewClickInterface){
this.context=context;
this.list=list;
this.recyclerViewClickInterface=recyclerViewClickInterface;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder( MyAdapter.MyViewHolder holder, int position) {
        OneWord m = list.get(position);
        holder.tv1.setText(m.getEnglish_word());
        holder.tv2.setText(String.valueOf(m.getArabic_word()));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                recyclerViewClickInterface.OnLongItemLongClick(m);
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  void filterList(ArrayList<OneWord>filterList){
        list=filterList;
        notifyDataSetChanged();

    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv1,tv2;
        public MyViewHolder( View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }


}
