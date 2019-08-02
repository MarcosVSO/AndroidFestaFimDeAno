package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.Constants;
import com.example.festafimdeano.data.SecurityPreferences;
import com.example.festafimdeano.view.DetailsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        //Data Atual
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        this.mViewHolder.textDaysLeft.setText(String.format("%s %s",String.valueOf(this.getDaysLeft()),getString(R.string.dias)));

        this.verifyPreferences();
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPreferences();
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.button_confirm){
            String presence = this.mSecurityPreferences.getStoredString(Constants.PRESENCE_KEY);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(Constants.PRESENCE_KEY,presence);
            startActivity(intent);
        }
    }

    private int getDaysLeft(){
        //Dia de Hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        //Dia máximo do ano
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;
    }

    private void verifyPreferences(){
        String presence = this.mSecurityPreferences.getStoredString(Constants.PRESENCE_KEY);
        if (presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao_confirmado));
        }else if (presence.equals(Constants.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.sim));
        }else if (presence.equals(Constants.CONFIRMATION_NO)){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao));
        }
    }
}
