package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.Constants;
import com.example.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mViewHolder.checkPaticipate = findViewById(R.id.check_participate);
        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.checkPaticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String presence = extras.getString(Constants.PRESENCE_KEY);
            if (presence != null && presence.equals(Constants.CONFIRMATION_YES)){
                this.mViewHolder.checkPaticipate.setChecked(true);
            }else{
                this.mViewHolder.checkPaticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkPaticipate;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_participate){
            if (this.mViewHolder.checkPaticipate.isChecked()){
                this.mSecurityPreferences.storeString(Constants.PRESENCE_KEY,Constants.CONFIRMATION_YES);
            }else{
                this.mSecurityPreferences.storeString(Constants.PRESENCE_KEY,Constants.CONFIRMATION_NO);
            }
        }
    }


}
