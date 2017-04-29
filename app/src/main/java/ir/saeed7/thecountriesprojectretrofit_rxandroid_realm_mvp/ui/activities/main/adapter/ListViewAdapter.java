package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.activities.main.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.R;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.config.Keys;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.model.Country;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private Activity activity;
    private List<Country> countryList;
    private LayoutInflater inflater;
    private String path;
    private String inputTitle;

    public ListViewAdapter(Activity activity, List<Country> countryList, String inputTitle) {
        this.activity = activity;
        this.countryList = countryList;
        this.inputTitle = inputTitle;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_list_countries, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViews.get(0).setText(countryList.get(position).getName());
        holder.textViews.get(1).setText(inputTitle + ": ");
        holder.textViews.get(3).setText(position + 1 + ".");
        if (inputTitle.equals(Keys.capital)) {
            holder.textViews.get(2).setText(countryList.get(position).getCapital());
        } else if (inputTitle.equals(Keys.population)) {
            holder.textViews.get(2).setText(countryList.get(position).getPopulation().toString());
        } else if (inputTitle.equals(Keys.area)) {
            holder.textViews.get(2).setText(countryList.get(position).getArea() + " " + activity.getResources().getString(R.string.square_kilometer));
        } else if (inputTitle.equals(Keys.gini)) {
            holder.textViews.get(2).setText(countryList.get(position).getGini() + " %");
        } else {
            holder.textViews.get(2).setText(countryList.get(position).getCapital());
        }


        path = "file:///android_asset/flags/" + countryList.get(position).getAlpha3Code() + ".jpg";
        Picasso.with(activity)
                .load(path)
                .resize(160,110)
                .into(holder.imageViewFlag);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_flag)
        ImageView imageViewFlag;

        @BindViews({R.id.textView_name,
                R.id.textView_title,
                R.id.textView_subtitle,
                R.id.textView_number})
        List<TextView> textViews;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
