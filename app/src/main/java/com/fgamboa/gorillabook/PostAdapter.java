package com.fgamboa.gorillabook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(Context context, List<Post> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_post, parent, false);
        }
        Post post = getItem(position);
        if(post != null) {
            TextView tvName = convertView.findViewById(R.id.tv_user);
            TextView tvDate = convertView.findViewById(R.id.tv_date);
            TextView tvContent = convertView.findViewById(R.id.tv_content);
            tvName.setText(post.getFirstName() + " " + post.getLastName());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(post.getTimestamp()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            tvDate.setText(dateFormat.format(calendar.getTime()));
            tvContent.setText(post.getPostBody());
        }
        return convertView;
    }
}
