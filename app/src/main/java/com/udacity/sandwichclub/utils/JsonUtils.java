package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwichJson = new JSONObject(json);

        JSONObject nameJson =sandwichJson.getJSONObject("name");

        String mainName = nameJson.getString("mainName");

        JSONArray alsoKnownAsJsonArray = nameJson.getJSONArray("alsoKnownAs");

        List<String> alsoKnownAs = new ArrayList<>();

        for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
            String alsoKnownAsStr = alsoKnownAsJsonArray.getString(i);
            alsoKnownAs.add(alsoKnownAsStr);
        }

        String origin = sandwichJson.getString("placeOfOrigin");
        String description = sandwichJson.getString("description");
        String image = sandwichJson.getString("image");

        JSONArray ingredientsJsonArray = sandwichJson.getJSONArray("ingredients");

        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsJsonArray.length(); i++) {
            String ingredientStr = ingredientsJsonArray.getString(i);
            ingredients.add(ingredientStr);
        }

        return new Sandwich(mainName, alsoKnownAs, origin, description, image, ingredients);
    }
}
