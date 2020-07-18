package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.txtToday = findViewById(R.id.txtToday);
        this.mViewHolder.txtDaysLeft = findViewById(R.id.txtDaysLeft);
        this.mViewHolder.btnConfirm = findViewById(R.id.btnConfirm);

        this.mViewHolder.btnConfirm.setOnClickListener(this);

        this.mViewHolder.txtToday.setText("HOJE É " + SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        this.mViewHolder.txtDaysLeft.setText("FALTAM\n" + String.format("%s dias", String.valueOf(this.getDaysLeft())) + "\nPARA SEU ANIVERSÁRIO!");
    }

    private int getDaysLeft(){
        //Data de hoje
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
            Intent intent = new Intent(this, GuestsActivity.class);
            startActivity(intent);
        }
    }
}
