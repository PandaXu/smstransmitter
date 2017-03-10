package com.pandacrm.smstransmitter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import static android.R.attr.button;

public class MainActivity extends Activity {

    private TextView number;
    private MaterialDialog setPhoneDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (TextView) findViewById(R.id.number);

        String receiverPhone = getSettingNote(this,"number");

        setPhoneDialog = new MaterialDialog.Builder(this)
                .title(R.string.input_set_receiver_phone)
                .content(R.string.input_set_receiver_tip)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER)
                .input(receiverPhone, receiverPhone, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if(input.toString().length()==11){
                            Toast.makeText(MainActivity.this,"号码设置成功:"+input.toString(),Toast.LENGTH_SHORT).show();
                            saveSettingNote(MainActivity.this,"number",input.toString());
                            number.setText(getSettingNote(MainActivity.this,"number"));
                        }else{
                            Toast.makeText(MainActivity.this,"号码输入有误",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build();

        if("".equals(receiverPhone)){
            number.setText("00000000000");
            setPhoneDialog.show();
        }

        number.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setPhoneDialog.show();
            }
        });


    }

    public static void saveSettingNote(Context context, String key, String saveData){//保存设置
        SharedPreferences.Editor note = context.getSharedPreferences("number_save", Activity.MODE_PRIVATE).edit();
        note.putString(key, saveData);
        note.apply();
    }
    public static String getSettingNote(Context context,String key){//获取保存设置
        SharedPreferences read = context.getSharedPreferences("number_save", Activity.MODE_PRIVATE);
        return read.getString(key, "");
    }
}
