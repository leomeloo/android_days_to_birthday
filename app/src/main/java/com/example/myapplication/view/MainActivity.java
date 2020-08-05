package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.constants.Constants;
import com.example.myapplication.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.txtToday = findViewById(R.id.txtToday);
        this.mViewHolder.txtDaysLeft = findViewById(R.id.txtDaysLeft);
        this.mViewHolder.btnConfirm = findViewById(R.id.btnConfirm);

        this.mViewHolder.btnConfirm.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);

        //Datas
        this.mViewHolder.txtToday.setText("HOJE É " + SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        this.mViewHolder.txtDaysLeft.setText("FALTAM\n" + String.format("%s dias", String.valueOf(this.getDaysLeft())) + "\nPARA O ANIVERSÁRIO DA MANU!");

        this.verifyPrefence();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifyPrefence();
    }

    private void verifyPrefence() {
        String presence = this.mSecurityPreferences.getStorageString(Constants.PRESENCE_KEY);
        if(presence.equals("")){
            this.mViewHolder.btnConfirm.setText(R.string.nao_confirmado);
        } else if(presence.equals(Constants.CONFIRMATION_YES)){
            this.mViewHolder.btnConfirm.setText(R.string.sim);
        } else {
            this.mViewHolder.btnConfirm.setText(R.string.nao);
        }
    }


    private int getDaysLeft(){
        //Dias restantes até 22/11/2020
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarBirthday = Calendar.getInstance();
        calendarBirthday.get(Calendar.YEAR);
        calendarBirthday.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendarBirthday.set(Calendar.DAY_OF_MONTH, 23);
        int birthday = calendarBirthday.get(Calendar.DAY_OF_YEAR);

        return (today - birthday) *-1;
    }

    public static class ViewHolder{
        TextView txtToday, txtDaysLeft;
        Button btnConfirm;

    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.btnConfirm){

            String presence = this.mSecurityPreferences.getStorageString(Constants.PRESENCE_KEY);

            Intent intent = new Intent(this, GuestsActivity.class);
            intent.putExtra(Constants.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }
}
