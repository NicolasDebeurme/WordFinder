package com.example.androidprojet.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidprojet.databinding.ActivityHomeDefinitionBinding;
import com.example.androidprojet.databinding.ActivitySynonymBinding;
import com.example.androidprojet.ui.login.API.DictionaryAPI;
import com.example.androidprojet.ui.login.API.DictionaryAsync;
import com.example.androidprojet.ui.login.RecyclerView.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    List<String> Defs= new ArrayList<>();
    List<String> Syns = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeDefinitionBinding definitionBinding = ActivityHomeDefinitionBinding.inflate(getLayoutInflater());
        ActivitySynonymBinding synonymBinding = ActivitySynonymBinding.inflate(getLayoutInflater());

        final EditText wordDefinition = definitionBinding.InputTextSearchBar;
        final EditText wordSynonym = synonymBinding.InputTextSearchBar;
        final Button EnterButtonDef = definitionBinding.EnterButtonDef;
        final Button EnterButtonSyn =synonymBinding.EnterButtonSyn;
        final ConstraintLayout Synonyms = definitionBinding.SynonymsLinkTab;
        final ConstraintLayout Definitions = synonymBinding.DefinitionsLinkTab;
        final RecyclerView OutputDef = definitionBinding.DefRecycler;
        final RecyclerView OutputSyn = synonymBinding.SynRecycler;
        setContentView(definitionBinding.getRoot());



        EnterButtonDef.setOnClickListener(v -> {
            Defs= new ArrayList<>();
            Syns= new ArrayList<>();

            DictionaryAsync m=new DictionaryAsync(wordDefinition.getText().toString(),this::OnDefRetrieved);
            m.execute();

            RecyclerDefinitions(OutputDef);
        });

        EnterButtonSyn.setOnClickListener(v -> {
            Defs= new ArrayList<>();
            Syns= new ArrayList<>();

            DictionaryAsync m=new DictionaryAsync(wordSynonym.getText().toString(),this::OnDefRetrieved);
            m.execute();

            RecyclerSynonyms(OutputSyn);
        });

        Synonyms.setOnClickListener(v->{
            RecyclerSynonyms(OutputSyn);

            wordSynonym.setText(wordDefinition.getText()) ;
            setContentView(synonymBinding.getRoot());

        });
        Definitions.setOnClickListener(v->{
            RecyclerDefinitions(OutputDef);

            wordDefinition.setText(wordSynonym.getText());
            setContentView(definitionBinding.getRoot());
        });

    }

    public void OnDefRetrieved(List<DictionaryAPI> List){

        if(List!=null){ // si on a bien reçu quelque chose

            for(DictionaryAPI el : List){ // on parcours les résultats
                for (DictionaryAPI.Meanings f : el.meanings)
                {
                    for (DictionaryAPI.Definitions i : f.definitions)
                    {
                        Defs.add( " ("+f.partOfSpeech+") "+ i.definition+ "\n");
                        for(String j : i.synonyms)
                            Syns.add(j+ "\n");
                    }
                }

            }
        }
    }

    private void RecyclerSynonyms(RecyclerView SynRec)
    {
        MyAdapter myAdapter = new MyAdapter(this, Syns);
        SynRec.setAdapter(myAdapter);
        SynRec.setLayoutManager(new LinearLayoutManager(this));
    }
    private void RecyclerDefinitions(RecyclerView DefRec)
    {
        MyAdapter myAdapter = new MyAdapter(this, Defs);
        DefRec.setAdapter(myAdapter);
        DefRec.setLayoutManager(new LinearLayoutManager(this));
    }
}