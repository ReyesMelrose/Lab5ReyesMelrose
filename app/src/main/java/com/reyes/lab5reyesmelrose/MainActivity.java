package com.reyes.lab5reyesmelrose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] comNames, counNames, indNames, ceoNames, comDescs;
    ListView lstCom;
    int[] logo = {R.drawable.icbc, R.drawable.jpmorgan_chase, R.drawable.china_construction_bank, R.drawable.agricultural_bank_of_china, R.drawable.boa,
            R.drawable.apple, R.drawable.ping_an_insurance, R.drawable.bank_of_china, R.drawable.royal_dutch_shell, R.drawable.wells_fargo,
            R.drawable.exxon_mobil, R.drawable.att, R.drawable.samsung, R.drawable.citi};
    ArrayList<AndroidCom> companies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comNames = getResources().getStringArray(R.array.comName);
        counNames = getResources().getStringArray(R.array.counName);
        indNames = getResources().getStringArray(R.array.indName);
        ceoNames = getResources().getStringArray(R.array.ceoName);
        comDescs = getResources().getStringArray(R.array.comDesc);

        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.items, companies);

        lstCom = findViewById(R.id.lvAndroid);
        lstCom.setAdapter(adapter);
        lstCom.setOnItemClickListener(this);

        for (int i = 0; i < comNames.length; i++) {
            companies.add(new AndroidCom(logo[i], comNames[i], counNames[i], indNames[i], ceoNames[i], comDescs[i]));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        final File file = new File(folder, "company.txt");
        try {

            FileOutputStream fos = new FileOutputStream(file);
            String choice = comNames[i] + "\n" + counNames[i] + "\n" + ceoNames[i] + "\n" + indNames[i];
            fos.write(choice.getBytes());

            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(comNames[i]);
            dialog.setIcon(logo[i]);
            dialog.setMessage(comDescs[i]);
            dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    try {
                        FileInputStream reader;
                        reader = new FileInputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/company.txt"));
                        int token;
                        String str = "";
                        while ((token = reader.read()) != -1) {
                            str += Character.toString((char) token);
                        }
                        reader.close();
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        Toast.makeText(MainActivity.this, "File not found...", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(MainActivity.this, "IO Error...", Toast.LENGTH_LONG).show();
                    }
                }
            });
            dialog.create().show();
        } catch (FileNotFoundException e) {
            Toast.makeText(MainActivity.this, "File not found...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "IO Error...", Toast.LENGTH_LONG).show();
        }
    }
}