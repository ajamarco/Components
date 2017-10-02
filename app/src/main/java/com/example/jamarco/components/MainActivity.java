package com.example.jamarco.components;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //create layout objects
    CompoundButton mCheckBox;
    ToggleButton mToggleButton;
    Switch mSwitch;
    SeekBar mSeekBar;
    Spinner mSpinner;
    RadioGroup mRadioGroup;
    TextView mTextView;
    Button btnShowValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiated layout objects
        mCheckBox = (CompoundButton) findViewById(R.id.checkbox_activate);
        mToggleButton = (ToggleButton) findViewById(R.id.togglebtn_onOff);
        mSwitch = (Switch) findViewById(R.id.switch_onOff);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar_value);
        mSpinner = (Spinner) findViewById(R.id.spn_names);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_options);
        mTextView = (TextView) findViewById(R.id.txt_value);
        btnShowValues = (Button) findViewById(R.id.btn_values);
        //default values
        mCheckBox.setChecked(true);
        mSeekBar.setProgress(20);
        mSpinner.setSelection(2);
        mRadioGroup.check(R.id.rb_opt2);
        //calling setup methods
        setupSeekbar();
        setupSpinner();
        setupSwitch();
        //button listener
        btnShowValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelectedRadio = mRadioGroup.getCheckedRadioButtonId();
                RadioButton radio = (RadioButton) findViewById(idSelectedRadio);

                String activated = mCheckBox.isChecked() ? "Activated" : "Not activated";
                String value = "Value: " + mSeekBar.getProgress();
                String name = "Name: " + mSpinner.getSelectedItem().toString();
                String option = "Option: " + radio.getText();

                StringBuilder message = new StringBuilder();
                message.append(activated).append("\n")
                        .append(value).append("\n")
                        .append(name).append("\n")
                        .append(option);
                Toast.makeText(MainActivity.this,message.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupSwitch(){
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCheckBox.setEnabled(buttonView.isChecked());
                mToggleButton.setEnabled(isChecked);
            }
        });
    }

    private void setupSeekbar(){
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //set the textview text to be the value of the seekbar
                mTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setupSpinner(){
        //create a String[] obj that will populate the spinner
        String[] names = new String[] {"Eric","Diana","Presto","Scheila","Bob"};
        //set a new arrayadapter to populate the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,names);
        //set how the spinner will look when it's not open
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach the adapter to the spinner
        mSpinner.setAdapter(adapter);
    }


}
