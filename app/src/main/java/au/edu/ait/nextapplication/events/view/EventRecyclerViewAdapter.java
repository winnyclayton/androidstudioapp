package au.edu.ait.nextapplication.events.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import au.edu.ait.nextapplication.databinding.EventRecyclerItemViewBinding;
import au.edu.ait.nextapplication.events.Event;

public class EventRecyclerViewAdapter  extends ListAdapter<Event, EventViewHolder> {

    private EventRecyclerItemViewBinding binding;
    private OnItemClickListener onItemClickListener;

    protected EventRecyclerViewAdapter(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * This helper method check the difference between 2 EVENTS
     */
    private static final DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getStartDate().equals(newItem.getStartDate()) &&
                    oldItem.getStartTime().equals(newItem.getStartTime()) &&
                    oldItem.getEndDate().equals(newItem.getEndDate()) &&
                    oldItem.getEndTime().equals(newItem.getEndTime()) &&
                    oldItem.getAddress().equals(newItem.getAddress()) &&
                    oldItem.getPostcode().equals(newItem.getPostcode()) &&
                    oldItem.getState().equals(newItem.getState()) &&
                    oldItem.getSuburb().equals(newItem.getSuburb()) &&
                    oldItem.getType().equals(newItem.getType()) &&
                    oldItem.getPoster().equals(newItem.getPoster())
                    ;
        }
    };

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = EventRecyclerItemViewBinding.inflate(inflater, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(binding);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = getItem(position);
        holder.setValues(event);
        holder.bind(event, onItemClickListener);
    }
}
