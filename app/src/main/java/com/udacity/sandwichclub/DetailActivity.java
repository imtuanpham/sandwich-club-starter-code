package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
//        Log.d(TAG, json);
        try {
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);
            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }

            populateUI(sandwich);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    // use Sandwich as an input parameter
    private void populateUI(Sandwich sandwich) {

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        TextView alsoKnownAsLabelTv = findViewById(R.id.also_known_label_tv);
        if (sandwich.getAlsoKnownAs().isEmpty()) {
            alsoKnownAsLabelTv.setVisibility(View.GONE);
            alsoKnownAsTv.setVisibility(View.GONE);
        } else {
            alsoKnownAsLabelTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        }

        TextView originTv = findViewById(R.id.origin_tv);
        originTv.setText(sandwich.getPlaceOfOrigin());

        TextView descriptionTv = findViewById(R.id.description_tv);
        descriptionTv.setText(sandwich.getDescription());

        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        ingredientsTv.setText(TextUtils.join(", ", sandwich.getIngredients()));
    }
}
