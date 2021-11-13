package com.example.androidprojet.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidprojet.databinding.ActivityHomeDefinitionBinding;
import com.example.androidprojet.databinding.ActivitySynonymBinding;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView Output;
    String Defs = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeDefinitionBinding definitionBinding = ActivityHomeDefinitionBinding.inflate(getLayoutInflater());
        ActivitySynonymBinding synonymBinding = ActivitySynonymBinding.inflate(getLayoutInflater());

        final EditText wordDefinition = definitionBinding.InputTextSearchBar;
        final EditText wordSynonym = synonymBinding.InputTextSearchBar;
        final Button EnterButton = definitionBinding.EnterButton;
        final ConstraintLayout Synonyms = definitionBinding.SynonymsLinkTab;
        final ConstraintLayout Definitions = synonymBinding.DefinitionsLinkTab;
        Output = definitionBinding.Output;
        setContentView(definitionBinding.getRoot());

        EnterButton.setOnClickListener(v -> {
            DictionaryAsync m=new DictionaryAsync(wordDefinition.getText().toString(),this::OnDefRetrieved);
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

    public void OnDefRetrieved(List<DictionaryAPI> List){

        if(List!=null){ // si on a bien reçu quelque chose

            for(DictionaryAPI el : List){ // on parcours les résultats
                for (DictionaryAPI.Definitions i : el.meanings.get(0).definitions)
                {
                    Defs = Defs  +i.definition+ "\n";

                }
                Output.setText(Defs);
                Log.d("resultat api", el.meanings.get(0).definitions.get(0).definition);  // et on les affiches

            }
        }
    }
}