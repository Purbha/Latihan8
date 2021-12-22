package com.ims_hr.latihan8;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox Chk1, Chk2, Chk3;
    Button B_Submit;
    RadioGroup RG_Makanan;
    RadioButton RB_Makanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inisial();
        Listen_B_Submit();
    }

    private void Inisial(){
        Chk1 = findViewById(R.id.checkBox_Main_Band1);
        Chk2 = findViewById(R.id.checkBox_Main_Band2);
        Chk3 = findViewById(R.id.checkBox_Main_Band3);
        B_Submit = findViewById(R.id.button_Main_Submit);
        RG_Makanan = findViewById(R.id.RadioGroup_Main_Makan);
    }

    private void Listen_B_Submit() {
        B_Submit.setOnClickListener(v -> {
            String Band = "";
            if(Chk1.isChecked()) { Band = getString(R.string.checkBox_Main_Band1); }
            if(Chk2.isChecked()) {
                if(Chk1.isChecked()) {
                    Band = Band + ", " + getString(R.string.checkBox_Main_Band2);
                } else {
                    Band = getString(R.string.checkBox_Main_Band2);
                }
            }
            if(Chk3.isChecked()) {
                if(Chk2.isChecked() || Chk1.isChecked()) {
                    Band = Band + ", " + getString(R.string.checkBox_Main_Band3);
                } else {
                    Band = getString(R.string.checkBox_Main_Band3);
                }
            }
            if(Band.equals("")) { Band = "Tidak Ada"; }
            int Sel_ID = RG_Makanan.getCheckedRadioButtonId();
            Log.d("CEK", "Listen_B_Submit: " + Sel_ID);
            String Makanan = "";
            if(Sel_ID > 0) {
                RB_Makanan = findViewById(Sel_ID);
                Makanan = RB_Makanan.getText().toString();
            } else {
                Makanan = "Anda tidak memilih makanan.";
            }
            String Pesan = "Band favorite adalah: " + Band + "\n" +
                    "Makanan favorite adalah: " + Makanan;
            AlertDialog.Builder A_Builder = new AlertDialog.Builder(MainActivity.this);
            A_Builder.setPositiveButton("OK",null);
            A_Builder.setTitle("Informasi");
            A_Builder.setMessage(Pesan);
            AlertDialog Alert = A_Builder.create();
            Alert.show();
        });
    }

}
