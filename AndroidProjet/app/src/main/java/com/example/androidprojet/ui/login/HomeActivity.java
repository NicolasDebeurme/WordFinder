package com.example.androidprojet.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidprojet.databinding.ActivityHomeDefinitionBinding;
import com.example.androidprojet.databinding.ActivitySynonymBinding;

import java.io.StringWriter;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeDefinitionBinding definitionBinding = ActivityHomeDefinitionBinding.inflate(getLayoutInflater());
        ActivitySynonymBinding synonymBinding = ActivitySynonymBinding.inflate(getLayoutInflater());
        setContentView(definitionBinding.getRoot());

        final EditText wordDefinition = definitionBinding.InputTextSearchBar;
        final EditText wordSynonym = synonymBinding.InputTextSearchBar;
        final Button EnterButton = definitionBinding.EnterButton;
        final ConstraintLayout Synonyms = definitionBinding.SynonymsLinkTab;
        final ConstraintLayout Definitions = synonymBinding.DefinitionsLinkTab;


        EnterButton.setOnClickListener(v -> {
            DictionaryAsync m=new DictionaryAsync(wordDefinition.getText().toString());
            m.execute();
        });
        Synonyms.setOnClickListener(v->{

            wordSynonym.setText(wordDefinition.getText()) ;
            setContentView(synonymBinding.getRoot());

        });
        Definitions.setOnClickListener(v->{
            wordDefinition.setText(wordSynonym.getText());
            setContentView(definitionBinding.getRoot());
        });

    }
}