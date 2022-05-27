package ir.mehrdad32.cryptoviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private final List<ListItem> list;
    private final LayoutInflater mInflater;
    private final Context mContext;
    TextView cryptoName;
    TextView price;
    TextView marketCap;

    public ItemAdapter(Context context, List<ListItem> list) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =mInflater.inflate(R.layout.clist, parent, false);
        return new MyViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListItem current = list.get(position);
        cryptoName = holder.itemView.findViewById(R.id.crypto_name);
        price = holder.itemView.findViewById(R.id.crypto_price);
        marketCap = holder.itemView.findViewById(R.id.crypto_market_cap);
        cryptoName.setText(current.getName());
        price.setText(current.getPrice());
        marketCap.setText(current.getMarketCap());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ItemAdapter mAdapter;

        MyViewHolder(View itemView, ItemAdapter adapter) {
            super(itemView);

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Toast.makeText(view.getContext(), "Clicked item: " + pos, Toast.LENGTH_SHORT).show();
        }
    }
}
