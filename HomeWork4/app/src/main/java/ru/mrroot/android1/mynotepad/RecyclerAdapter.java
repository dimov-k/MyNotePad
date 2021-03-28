package ru.mrroot.android1.mynotepad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final Context gcontext;
    private final LayoutInflater inflater;
    private final List<NoteInfo> notes;

    public RecyclerAdapter(Context context, List<NoteInfo> notes) {
        this.gcontext = context;
        this.notes = notes;
        inflater = LayoutInflater.from(gcontext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_note_list, parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteInfo dnote = notes.get(position);
        holder.textcourse.setText(dnote.getCourse().getTitle());
        holder.texttitle.setText(dnote.getTitle());
        holder.CurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView textcourse;
        public final TextView texttitle;
        public int CurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textcourse = (TextView)itemView.findViewById(R.id.text_course);
            texttitle = (TextView)itemView.findViewById(R.id.text_title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(gcontext,NoteActivity.class);
                    intent.putExtra(NoteActivity.NOTE_POSITION,CurrentPosition);
                    gcontext.startActivity(intent);
                }
            });
        }
    }
}

