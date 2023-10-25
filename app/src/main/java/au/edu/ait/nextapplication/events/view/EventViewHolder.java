package au.edu.ait.nextapplication.events.view;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import au.edu.ait.nextapplication.databinding.EventRecyclerItemViewBinding;
import au.edu.ait.nextapplication.events.Event;

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private EventRecyclerItemViewBinding binding;

    public EventViewHolder(@NonNull EventRecyclerItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setValues(Event event){

        this.binding.eventNameTextView.setText(event.getName());
        this.binding.eventStartDateTimeTextView.setText(event.getStartDate() + " " + event.getStartTime());
        this.binding.eventTypeTextView.setText(event.getType());

        int resID = binding.getRoot().getResources().getIdentifier(event.getPoster(), "drawable", binding.getRoot().getContext().getPackageName());
        this.binding.eventImageView.setImageResource(resID);
    }

    public void bind(Event event, OnItemClickListener onItemClickListener){
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(event, view);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
