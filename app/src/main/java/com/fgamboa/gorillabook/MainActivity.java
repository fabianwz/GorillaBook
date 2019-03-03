package com.fgamboa.gorillabook;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.user_name)
    TextView tvUser;

    @BindView(R.id.lv_feed)
    ListView lvFeed;

    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Calendar calendar = Calendar.getInstance();

        String greetingText = getString(R.string.greeting_text);
        tvDate.setText(dateFormat.format(calendar.getTime()));
        tvUser.setText(String.format(greetingText, getString(R.string.first_name)));
        getFeedContent();
    }

    private void getFeedContent() {
        RetrofitService retrofitService =  new RetrofitService();

        Call<List<Post>> call = retrofitService.getNetworkService().getFeedContent();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        adapter = new PostAdapter(MainActivity.this, response.body());
                        lvFeed.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //TODO
            }
        });
    }
}
