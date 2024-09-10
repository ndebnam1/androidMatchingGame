package com.example.assignment03;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Niles Debnam
    //Assignment 03
    // Student ID #801184626
    private final int[] imageViewCards = {R.id.imageViewCard0, R.id.imageViewCard1, R.id.imageViewCard2, R.id.imageViewCard3, R.id.imageViewCard4, R.id.imageViewCard5, R.id.imageViewCard6, R.id.imageViewCard7, R.id.imageViewCard8, R.id.imageViewCard9, R.id.imageViewCard10, R.id.imageViewCard11, R.id.imageViewCard12, R.id.imageViewCard13, R.id.imageViewCard14, R.id.imageViewCard15, R.id.imageViewCard16, R.id.imageViewCard17, R.id.imageViewCard18, R.id.imageViewCard19, R.id.imageViewCard20, R.id.imageViewCard21, R.id.imageViewCard22, R.id.imageViewCard23, R.id.imageViewCard24};
    private final int[] drawableCards = {R.drawable.apple, R.drawable.lemon, R.drawable.mango, R.drawable.peach, R.drawable.strawberry, R.drawable.tomato};
    TextView topText;
    int numToFind;
    String toFind, drawableIdName;
    ArrayList<Integer> shuffledDrawableIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.resetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffledDrawableIDs.clear();
                setupNewGame();

            }
        });

        setupNewGame();
    }

private void shuffleRemainingCards() {
    CardInfo cardInfo;

    Collections.shuffle(shuffledDrawableIDs);  // Shuffle the drawable IDs

    int shuffledIndex = 0;
    for (int i = 0; i < imageViewCards.length; i++) {
        ImageView imageView = findViewById(imageViewCards[i]);
         cardInfo = (CardInfo) imageView.getTag();


        if (cardInfo.isClicked()) {
            int newDrawableId = shuffledDrawableIDs.get(shuffledIndex);
            cardInfo.setDrawableId(newDrawableId);
            imageView.setImageResource(newDrawableId);
            shuffledIndex++;
        }
    }


        }












    private void setupNewGame() {
        //Arraylist of 6+19 cards
    Log.d("demo", String.valueOf(imageViewCards.length));
        for(int i = 0; i < imageViewCards.length ; i++){
            Random random = new Random();
            int randomIndex = random.nextInt(drawableCards.length);
            shuffledDrawableIDs.add(drawableCards[randomIndex]);

        }
        Collections.shuffle(shuffledDrawableIDs);
        Log.d("demo", "shuffledDrawableIDs: " + String.valueOf(shuffledDrawableIDs.size()));
        Log.d("demo","imageViewCards: " + String.valueOf(imageViewCards.length));
        for (int i = 0; i < shuffledDrawableIDs.size() ; i++) {
            int imageViewId = imageViewCards[i];
            int drawableId = shuffledDrawableIDs.get(i);
            CardInfo cardInfo = new CardInfo(imageViewId, drawableId);
//            cards.add(cardInfo);
            ImageView imageView = findViewById(imageViewId);
            imageView.setTag(cardInfo);
            imageView.setOnClickListener(this);
            imageView.setImageResource(drawableId);
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(1);
            ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(cm);
            imageView.setColorFilter(colorFilter);
//            Log.d("demo", getResources().getResourceEntryName(drawableId));


        }
        String[] fruits = {"Apples", "Strawberries", "Mangos", "Peaches", "Tomatoes", "Lemons"};
        Random random = new Random();
        int randomIndex = random.nextInt(fruits.length);
         toFind = fruits[randomIndex];
         numToFind = 0;
        for(Integer drawableId : shuffledDrawableIDs){
             drawableIdName = getResources().getResourceEntryName(drawableId);
            char firstLetter1 = drawableIdName.charAt(0);
            char firstLetter2 = toFind.charAt(0);
            if(Character.toLowerCase(firstLetter1) == Character.toLowerCase(firstLetter2)){
                numToFind += 1;
            }
        }
        String newTopText = "Find All " + toFind + "(" + numToFind + ")";
        topText = findViewById(R.id.topTextView);
        topText.setText(newTopText);




        cardInfo1 = null;
        matchCount = 0;
    }
    CardInfo cardInfo1 = null;
    int matchCount = 0;

    @Override
    public void onClick(View view) {
        ImageView imageView = (ImageView) view;
        CardInfo cardInfo = (CardInfo) imageView.getTag();
        Log.d("demo", "onClick: " + cardInfo);
        char firstLetter1 = toFind.charAt(0);
        drawableIdName = getResources().getResourceEntryName(cardInfo.getDrawableId());
        char firstLetter2 = drawableIdName.charAt(0);

        if(cardInfo.isClicked() && (Character.toLowerCase(firstLetter1) == Character.toLowerCase(firstLetter2))){
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(cm);
            imageView.setColorFilter(colorFilter);
            cardInfo.setClicked(true);
           int drawableId = cardInfo.getDrawableId();
           int removeIndex = shuffledDrawableIDs.lastIndexOf(drawableId);
           Log.d("demo", "Size of shuffledDrawableIDS: " + shuffledDrawableIDs.size());
           shuffledDrawableIDs.remove(removeIndex);
//           removeIndex = cardInfo.getImageViewId();

            Log.d("demo", "Size of shuffledDrawableIDS: " + shuffledDrawableIDs.size());

//            cardInfo1.setClicked(true)
            numToFind -= 1;
            if(numToFind == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Congrats! Found All Shapes");
                builder.setMessage("You have found all the " + toFind);
                builder.setPositiveButton("OK",(dialog,id) ->{
                    shuffledDrawableIDs.clear();
                    setupNewGame();
                    dialog.dismiss();
                });
                AlertDialog endGame = builder.create();
                endGame.show();
            }

            String newTopText = "Find All " + toFind + "(" + numToFind + ")";
            topText = findViewById(R.id.topTextView);
            topText.setText(newTopText);
            shuffleRemainingCards();



        }

    }
}