package com.cs160.rymico.languagetranslator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private Spinner fromLanguage, phrase;
    private CheckBox toEnglish, toFrench, toSpanish, toJapanese;
    private Button translate;
    private TextView translated;
    private ArrayAdapter<CharSequence> englishAdapter, frenchAdapter, spanishAdapter, japaneseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSpinnerAdapters();
        addSpinnerOnItemSelectedListener();
        addSubmitListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addSpinnerAdapters() {
        fromLanguage = (Spinner) findViewById(R.id.fromLanguage);
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(this, R.array.fromLanguages, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromLanguage.setAdapter(langAdapter);

        englishAdapter = ArrayAdapter.createFromResource(this, R.array.englishPhrases, android.R.layout.simple_spinner_item);
        frenchAdapter = ArrayAdapter.createFromResource(this, R.array.frenchPhrases, android.R.layout.simple_spinner_item);
        spanishAdapter = ArrayAdapter.createFromResource(this, R.array.spanishPhrases, android.R.layout.simple_spinner_item);
        japaneseAdapter = ArrayAdapter.createFromResource(this, R.array.japanesePhrases, android.R.layout.simple_spinner_item);
        englishAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frenchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spanishAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        japaneseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        phrase = (Spinner) findViewById(R.id.phrase);
        phrase.setAdapter(englishAdapter);
    }

    public void addSpinnerOnItemSelectedListener() {
        fromLanguage = (Spinner) findViewById(R.id.fromLanguage);
        phrase = (Spinner) findViewById(R.id.phrase);
        toEnglish = (CheckBox) findViewById(R.id.toEnglish);
        toFrench = (CheckBox) findViewById(R.id.toFrench);
        toSpanish = (CheckBox) findViewById(R.id.toSpanish);
        toJapanese = (CheckBox) findViewById(R.id.toJapanese);
        fromLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int phrasePos = phrase.getSelectedItemPosition();
                switch(parent.getItemAtPosition(pos).toString()) {
                    case "English":
                        phrase.setAdapter(englishAdapter);
                        toEnglish.setVisibility(View.GONE);
                        toEnglish.setChecked(false);
                        toFrench.setVisibility(View.VISIBLE);
                        toSpanish.setVisibility(View.VISIBLE);
                        toJapanese.setVisibility(View.VISIBLE);
                        break;
                    case "French":
                        phrase.setAdapter(frenchAdapter);
                        toEnglish.setVisibility(View.VISIBLE);
                        toFrench.setVisibility(View.GONE);
                        toFrench.setChecked(false);
                        toSpanish.setVisibility(View.VISIBLE);
                        toJapanese.setVisibility(View.VISIBLE);
                        break;
                    case "Spanish":
                        phrase.setAdapter(spanishAdapter);
                        toEnglish.setVisibility(View.VISIBLE);
                        toFrench.setVisibility(View.VISIBLE);
                        toSpanish.setVisibility(View.GONE);
                        toSpanish.setChecked(false);
                        toJapanese.setVisibility(View.VISIBLE);
                        break;
                    case "Japanese":
                        phrase.setAdapter(japaneseAdapter);
                        toEnglish.setVisibility(View.VISIBLE);
                        toFrench.setVisibility(View.VISIBLE);
                        toSpanish.setVisibility(View.VISIBLE);
                        toJapanese.setVisibility(View.GONE);
                        toJapanese.setChecked(false);
                        break;
                }
                phrase.setSelection(phrasePos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                phrase.setAdapter(englishAdapter);
            }
        });
    }

    public void addSubmitListener() {
        fromLanguage = (Spinner) findViewById(R.id.fromLanguage);
        phrase = (Spinner) findViewById(R.id.phrase);
        toEnglish = (CheckBox) findViewById(R.id.toEnglish);
        toFrench = (CheckBox) findViewById(R.id.toFrench);
        toSpanish = (CheckBox) findViewById(R.id.toSpanish);
        toJapanese = (CheckBox) findViewById(R.id.toJapanese);
        translate = (Button) findViewById(R.id.submitButton);

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer result = new StringBuffer();

                if (toEnglish.isChecked()) {
                    String[] englishArray = getResources().getStringArray(R.array.englishPhrases);
                    result.append("English: ").append(englishArray[phrase.getSelectedItemPosition()]).append("\n");
                }
                if (toFrench.isChecked()) {
                    String[] frenchArray = getResources().getStringArray(R.array.frenchPhrases);
                    result.append("French: ").append(frenchArray[phrase.getSelectedItemPosition()]).append("\n");
                }
                if (toSpanish.isChecked()) {
                    String[] spanishArray = getResources().getStringArray(R.array.spanishPhrases);
                    result.append("Spanish: ").append(spanishArray[phrase.getSelectedItemPosition()]).append("\n");
                }
                if (toJapanese.isChecked()) {
                    String[] japaneseArray = getResources().getStringArray(R.array.japanesePhrases);
                    result.append("Japanese: ").append(japaneseArray[phrase.getSelectedItemPosition()]).append("\n");
                }
                translated = (TextView) findViewById(R.id.translated);
                translated.setText(result);
            }
        });
    }
}
