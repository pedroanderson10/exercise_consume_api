package com.example.greenmileteste;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greenmileteste.entity.Post;
import com.example.greenmileteste.model.ConsumeAPI;
import com.example.greenmileteste.model.JSONPlaceholder;
import com.example.greenmileteste.util.PostAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    private List<Post> postList;
    private ProgressDialog progress;

    private EditText editTextID, editTextUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("GreenMileTeste - Coleção de Posts");

        buildRecyclerView();
        buscarDadosAPI();

        editTextID = findViewById(R.id.editTextId);
        editTextID.addTextChangedListener(textWatcher);

        editTextUserID = findViewById(R.id.editTextUserID);
        editTextUserID.setKeyListener(null);
        //editTextUserID.addTextChangedListener(textWatcher);
        //filtrarDadosUserID();

    }

    private  void buscarDadosAPI(){
        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Buscando dados da API...");
        progress.show();


        JSONPlaceholder jsonPlaceholder = ConsumeAPI.consumeAPI(JSONPlaceholder.class);

        Call<List<Post>> call = jsonPlaceholder.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                postList = response.body();
                //Log.d("teste", postList.get(0).getID);
                postAdapter = new PostAdapter(MainActivity.this, postList);
                recyclerView.setAdapter(postAdapter);

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

    }

    //Teste Filtro por ID
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            filter(editable.toString());
        }
    };

    private void filter(String text){
        ArrayList<Post> listAux = new ArrayList<>();

        for(Post post : postList){
            if(post.getId().toLowerCase().contains(text.toLowerCase())){
                listAux.add(post);
            }
        }

        postAdapter.filterListAux(listAux);

    }

    private void buildRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    //////////// Teste Filtro por User ID

    /*private void filtrarDadosUserID(){
        editTextUserID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterUserID(editable.toString());
            }
        });

    }

    private void filterUserID(String text){
        ArrayList<Post> listAux = new ArrayList<>();

        for(Post post : postList){
            if(post.getUserId().toLowerCase().contains(text.toLowerCase())){
                listAux.add(post);
            }
        }

        postAdapter.filterListAux(listAux);

    }*/




}