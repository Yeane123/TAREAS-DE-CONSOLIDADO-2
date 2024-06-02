package com.example.app.sqlite;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList book_id, book_title, book_author, book_pages;

    Animation traslate_anim;


    CustomAdapter(Activity activity,Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.Activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;

    }

    @NonNull1
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, attach ToRoot:false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position) {

        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.book title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                public void onClick(View view) {
                    Intent intent = new Intent (context, UpdateActivity.class);
                    intent.putExtra (name: "id", String.valueOf(book_id.get(position)));
                    intent.putExtra(name: "title", String.valueOf(book_title.get(position)));
                    intent.putExtra (name: "author", String.valueOf(book_author.get(position)));
                    intent.putExtra(name: "pages", String.valueOf(book_pages.get(position)));
                    activity.startActivityForResult(intent,1 );
            }
        });
    }


    @Override
    public int getItemCount(){return book_id.size;}

   class MyViewHolder extends RecyclerView.ViewHolder {
        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            
        }
    }
}