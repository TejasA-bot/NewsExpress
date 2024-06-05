package com.example.newsexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageButton ibtn1,ibtn2;
    RecyclerView recyclerView;
    List<Article> articleList = new ArrayList<>();
    NewsRecyclerAdapter adapter;
    LinearProgressIndicator progressIndicator;

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7;
    SearchView searchView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.news_recycler_View);
        progressIndicator = findViewById(R.id.progress_bar);
        searchView= findViewById(R.id.search_view);

        ibtn1= findViewById(R.id.home);
        ibtn2= findViewById(R.id.logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);

        ibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        ibtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signout();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews("General", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        setupRecyclerView();
        getNews("General", null);

    }
    void setupRecyclerView()
    {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter=new NewsRecyclerAdapter(articleList);
            recyclerView.setAdapter(adapter);
    }

    void changeInProgress(boolean show)
    {
        if (show)
            progressIndicator.setVisibility(View.VISIBLE);
        else
            progressIndicator.setProgress(View.INVISIBLE);
    }


    void getNews(String category, String query)
    {
        changeInProgress(true);
        NewsApiClient newsApiClient= new NewsApiClient("88f53452511a4d529d116ff1f14be84f");
        newsApiClient.getTopHeadlines(
          new TopHeadlinesRequest.Builder()
                  .language("en")
                  .category(category)
//                  .country("INDIA")
                  .q(query)
                  .build(),
          new NewsApiClient.ArticlesResponseCallback(){

              @Override
              public void onSuccess(ArticleResponse response) {

                  runOnUiThread(()->{
                    changeInProgress(false);
                    articleList=response.getArticles();
                    adapter.updateData(articleList);
                    adapter.notifyDataSetChanged();
                  });

              }

              @Override
              public void onFailure(Throwable throwable) {
                Log.i("GOT Failure", throwable.getMessage());
              }
          }

        );
    }


    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String category = btn.getText().toString();
        getNews(category,null);

    }

    private void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void signout()
    {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>(){
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(MainActivity.this, splashactivity.class));

            }
        });
    }


}
