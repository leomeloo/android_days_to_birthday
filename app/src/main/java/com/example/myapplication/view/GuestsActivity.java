package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.constants.Constants;
import com.example.myapplication.data.SecurityPreferences;

import java.util.ArrayList;

public class GuestsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBox = findViewById(R.id.checkbox);
        this.mViewHolder.checkBox.setOnClickListener(this);

        this.loadData();
    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();
        String presence = extras.getString(Constants.PRESENCE_KEY);
        if (presence != null && presence.equals(Constants.CONFIRMATION_YES)){
            this.mViewHolder.checkBox.setChecked(true);
        } else {
            this.mViewHolder.checkBox.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkbox){
            if (this.mViewHolder.checkBox.isChecked()){
                //Salvar Participação
                this.mSecurityPreferences.storeString(Constants.PRESENCE_KEY, Constants.CONFIRMATION_YES);
            } else{
                //Salvar ausência
                this.mSecurityPreferences.storeString(Constants.PRESENCE_KEY, Constants.CONFIRMATION_NO);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkBox;
    }
}
