package com.intrinsic.cid.intrinsic;

import java.util.ArrayList;
import java.util.Arrays;

public class Flavors {
    public ArrayList<String> slushie_smoothie_milkshakes_flavors
            = new ArrayList<String>(Arrays.asList("ALMOND", "BANANA", "BLUEBERRY", "CHOCOLATE", "COCONUT", "GREEN TEA",
            "HONEYDEW", "JASMINE", "MANGO", "MILK TEA", "PAPAYA", "RED BEAN", "STRAWBERRY", "TARO", "THAI", "WATERMELON",
            "KIWI", "ACAI", "CHERRY", "GREEN APPLE", "GUAVA", "KUMQUAT", "PINA COLADA", "MARGARITA", "LEMON", "LYCHEE",
            "MINT", "ORANGE", "PASSIONFRUIT", "PEACH", "POMEGRANATE", "PINEAPPLE", "YOGURT"));

    public ArrayList<String> bubble_tea_flavors =
            new ArrayList<String>(Arrays.asList("ALMOND", "BANANA", "BLUEBERRY", "CHOCOLATE", "COCONUT", "HONEYDEW",
                    "JASMINE", "MANGO", "MILK TEA", "PAPAYA", "RED BEAN", "STRAWBERRY", "TARO", "THAI", "WATERMELON"));

    public ArrayList<String> bubble_juice_flavors =
            new ArrayList<String>(Arrays.asList("KUMQUAT", "PINA COLADA", "MARGARITA", "LEMON", "LYCHEE", "MANGO",
                    "MINT", "ORANGE", "PASSIONFRUIT", "PEACH", "POMEGRANATE", "PINEAPPLE", "STRAWBERRY", "YOGURT"));

    public ArrayList<String> brewed_tea_flavors =
            new ArrayList<String>(Arrays.asList("ENGLISH BREAKFAST", "WILD RASPBERRY HIBISCUS", "POMEGRANATE RASPBERRY",
                    "WILD RASPBERRY", "CHAI SPICE", "CHAMOMILE", "LEMON GINGER", "MANGO PASSIONFRUIT", "EARL GRAY / DECAF",
                    "BLACK TEA", "GREEN TEA", "WHITE TEA", "OOLONG TEA", "JASMINE GREEN TEA"));

    public ArrayList<String> coffee_and_other_flavors =
            new ArrayList<String>(Arrays.asList("ALMOND MILK", "SOY MILK", "CREAM", "SUGAR"));

    public ArrayList<String> getBrewed_tea_flavors() {
        return brewed_tea_flavors;
    }

    public ArrayList<String> getBubble_juice_flavors() {
        return bubble_juice_flavors;
    }

    public ArrayList<String> getBubble_tea_flavors() {
        return bubble_tea_flavors;
    }

    public ArrayList<String> getCoffee_and_other_flavors() {
        return coffee_and_other_flavors;
    }

    public ArrayList<String> getSlushie_smoothie_milkshakes_flavors() {
        return slushie_smoothie_milkshakes_flavors;
    }
}
