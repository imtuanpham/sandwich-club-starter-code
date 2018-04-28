
package com.udacity.sandwichclub.utils;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.sandwichclub.model.Sandwich;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Following tests
 * 1) parseSandwichJson
 */
@RunWith(AndroidJUnit4.class)
public class TestJsonUtils {


    @Before
    public void before() {
        // DO NOTHING
    }

    @Test
    public void testParseSandwichJson() {
        String json = "{\"name\":{\"mainName\":\"mainName\",\"alsoKnownAs\":[\"alsoKnownAs1\",\"alsoKnownAs2\"]},\"placeOfOrigin\":\"placeOfOrigin\"," +
                "\"description\":\"description\",\"image\":\"image\",\"ingredients\":[\"ingredient1\",\"ingredient2\"]}";
        try {
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);

            assertEquals("Incorrect mainName", "mainName", sandwich.getMainName());

            List<String> alsoKnownAs = new ArrayList<>();
            alsoKnownAs.add("alsoKnownAs1");
            alsoKnownAs.add("alsoKnownAs2");
            assertEquals(alsoKnownAs, sandwich.getAlsoKnownAs());

            assertEquals("Incorrect placeOfOrigin", "placeOfOrigin", sandwich.getPlaceOfOrigin());
            
            assertEquals("Incorrect description", "description", sandwich.getDescription());

            assertEquals("Incorrect image", "image", sandwich.getImage());


            List<String> ingredients = new ArrayList<>();
            ingredients.add("ingredient1");
            ingredients.add("ingredient2");

            assertEquals(ingredients, sandwich.getIngredients());

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}