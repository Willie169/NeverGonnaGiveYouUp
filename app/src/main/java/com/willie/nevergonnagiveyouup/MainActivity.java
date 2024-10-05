package com.willie.nevergonnagiveyouup;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Paint;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int displayWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView outputText = findViewById(R.id.outputText);
        EditText inputText = findViewById(R.id.inputText);
        Paint paint = new Paint();
        paint.setTextSize(outputText.getTextSize());

        outputText.setText("Please enter the number of iterations\n");

        final String theText = "We'reNoStrangersToLoveYouKnowTheRulesAndSoDoI(DoI)AFullCommitment'sWhatI'mThinkingOfYouWouldn'tGetThisFromAnyOtherGuyIJustWannaTellYouHowI'mFeelingGottaMakeYouUnderstandNeverGonnaGiveYouUpNeverGonnaLetYouDowNNeverGonnaRunAroundAndDesertYouNeverGonnaMakeYouCryNeverGonnaSayGoodbyeNeverGonnaTellALieAndHurtYouWe'veKnownEachOtherForSoLongYourHeart'sBeenAching,ButYou'reTooShyToSayIt(SayIt)Inside,WeBothKnowWhat'sBeenGoingOn(GoingOn)WeKnowTheGameAndWe'reGonnaPlayItAndIfYouAskMeHowI'mFeelingDon'tTellMeYou'reTooBlindToSeeNeverGonnaGiveYouUpNeverGonnaLetYouDowNNeverGonnaRunAroundAndDesertYouNeverGonnaMakeYouCryNeverGonnaSayGoodbyeNeverGonnaTellALieAndHurtYouNeverGonnaGiveYouUpNeverGonnaLetYouDowNNeverGonnaRunAroundAndDesertYouNeverGonnaMakeYouCryNeverGonnaSayGoodbyeNeverGonnaTellALieAndHurtYouWe'veKnownEachOtherForSoLongYourHeart'sBeenAching,ButYou'reTooShyToSayIt(ToSayIt)Inside,WeBothKnowWhat'sBeenGoingOn(GoingOn)WeKnowTheGameAndWe'reGonnaPlayItIJustWannaTellYouHowI'mFeelingGottaMakeYouUnderstandNeverGonnaGiveYouUpNeverGonnaLetYouDowNNeverGonnaRunAroundAndDesertYouNeverGonnaMakeYouCryNeverGonnaSayGoodbyeNeverGonnaTellALieAndHurtYouNeverGonnaGiveYouUpNeverGonnaLetYouDowNNeverGonnaRunAroundAndDesertYouNeverGonnaMakeYouCryNeverGonnaSayGoodbyeNeverGonnaTellALieAndHurtYouNeverGonnaGiveYouUpNeverGonnaLetYouDowNNeverGonnaRunAroundAndDesertYouNeverGonnaMakeYouCryNeverGonnaSayGoodbyeNeverGonnaTellALieAndHurtYou";

        outputText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                outputText.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                displayWidth = outputText.getWidth();
            }
        });

        inputText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String input = inputText.getText().toString().trim();
                inputText.setText("");
                byte dir = 1;
                try {
                    if (input == "") throw new IllegalArgumentException();
                    int times = Integer.parseInt(input);
                    if (times <= 0) throw new NumberFormatException();
                    outputText.setText("");

                    int currentSpaces = 0;
                    int pos = 0;
                    for (int i = 0; i < times; i++) {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String leadingSpaces = " ".repeat(currentSpaces);
                            String line = leadingSpaces + sb.toString();

                            float stringWidth = paint.measureText(line);


                            if (pos >= theText.length()) {
                                pos = 0;
                            }

                            char nextChar = theText.charAt(pos);
                            line += nextChar;

                            if (paint.measureText(line) <= displayWidth) {
                                sb.append(nextChar);
                                pos++;
                            } else {
                                if (currentSpaces > 0 || sb.length() > 0) {
                                    outputText.append(leadingSpaces + sb.toString() + "\n");
                                }
                                break;
                            }
                        }

                        currentSpaces += dir;
                        if (currentSpaces > displayWidth / (int) paint.measureText(" ")) {
                            currentSpaces -= 2;
                            dir = -1;
                        } else if (currentSpaces == -1) {
                            currentSpaces = 1;
                            dir = 1;
                        }
                    }
                } catch (NumberFormatException e) {
                    outputText.append("Invalid number, please enter a positive integer\n");
                } catch (IllegalArgumentException e) {}
            }
            return true;
        });
    }
}