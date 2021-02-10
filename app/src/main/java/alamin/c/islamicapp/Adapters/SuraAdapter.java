package alamin.c.islamicapp.Adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alamin.c.islamicapp.DataHandeler.SuraValues;
import alamin.c.islamicapp.R;

public class SuraAdapter extends RecyclerView.Adapter<SuraAdapter.MyViewHolder> {


    private OnItemClickListner listner;
    private Context context;
private List<SuraValues> suraValues;

    public SuraAdapter(Context context, List<SuraValues> suraValues) {
        this.context = context;
        this.suraValues = suraValues;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(context).inflate(R.layout.sura_shower_sample_layoute,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SuraValues suraValues1=suraValues.get(position);

        holder.arabicTextview.setText("("+suraValues1.getAyahserial()+")"+suraValues1.getArabic());
        holder.banglaTextview.setText("অর্থঃ   "+suraValues1.getBangla()+"\n");
        holder.banglaTranslateTextview.setText("উচ্চারণঃ   "+suraValues1.getBanglaTranslate()+"\n");
        holder.englishText.setText("Translate :  "+suraValues1.getEnglish()+"\n");



    }

    @Override
    public int getItemCount() {
        return suraValues.size();
    }


    public class MyViewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView arabicTextview;
        private  TextView banglaTextview;
        private  TextView banglaTranslateTextview;
        private  TextView englishText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            arabicTextview=itemView.findViewById(R.id.arabicTextviewid);
            banglaTextview=itemView.findViewById(R.id.banglaTextviewid);
            banglaTranslateTextview=itemView.findViewById(R.id.banglaTranslateTextviewid);
            englishText=itemView.findViewById(R.id.englishTextview);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listner!=null){
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    listner.OnItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Chose an action");
            MenuItem deleteitem=menu.add(Menu.NONE,1,1,"Save to Bookmark");

            deleteitem.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(listner!=null){
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            listner.onSave(position);
                            return  true;
                    }
                }
            }

            return false;
        }
    }

    public interface  OnItemClickListner{
        void OnItemClick(int position);
        void onSave(int position);
    }

    public void setOnItemClickListener(SuraAdapter.OnItemClickListner listener){
        this.listner=listener;

    }
}

