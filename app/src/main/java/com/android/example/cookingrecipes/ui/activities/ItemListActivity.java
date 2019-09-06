package com.android.example.cookingrecipes.ui.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.ApiController;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.ui.adapters.RecipeRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements Callback<List<Recipe>> {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        ApiController controller = new ApiController();
        controller.loadRecipes(this);

        recyclerView = findViewById(R.id.item_list);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Recipe> recipes) {
        recyclerView.setAdapter(new RecipeRecyclerViewAdapter(this, recipes, mTwoPane));
    }

    @Override
    public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
        if (response.isSuccessful()) {
            List<Recipe> recipes = response.body();
            setupRecyclerView(recyclerView, recipes);
        }
    }

    @Override
    public void onFailure(Call<List<Recipe>> call, Throwable t) {
        t.printStackTrace();
        Log.d(ItemListActivity.class.getSimpleName(), call.request().url().toString());
    }
}