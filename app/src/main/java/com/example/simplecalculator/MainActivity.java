package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    CheckBox chkBox;
    Button btnMod, btnPower, btnDelete, btnDiv, btnMul, btnSub, btnSum, btnPoint, btnEqual;
    int i;
    String num;
    String result;
    Boolean isNum = false; // 연산자 입력 전 숫자가 입력되었는지 확인하기 위한 Boolean 변수
    int temp;
    int num1;
    int num2;
    int[] numArr;
    int numCount;
    String str;
    Boolean isResult=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numArr = new int[3];
        for (i = 0; i < numArr.length; i++) numArr[i] = 0;
        numCount = 0;

        Button[] numButtons = new Button[10];
        Integer[] numBtnIDs = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        textView = (TextView) findViewById(R.id.textView);
        chkBox = (CheckBox) findViewById(R.id.chkBox);
        btnMod = (Button) findViewById(R.id.btnMod);
        btnPower = (Button) findViewById(R.id.btnPower);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnEqual = (Button) findViewById(R.id.btnEqual);


        for (i = 0; i < numBtnIDs.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        //체크박스 체크 이벤트 리스너
        chkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkBox.isChecked()) { // 체크하면
                    btnMod.setVisibility(View.VISIBLE); // MOD 버튼 활성화
                    btnPower.setVisibility(View.VISIBLE); // ^버튼 활성화
                }
            }
        });

        // 숫자 버튼 클릭 이벤트 리스너
        for (i = 0; i < numBtnIDs.length; i++) {
            final int index;
            index = i;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isResult){ // 계산 결과 출력되어있으면 지우기
                        textView.setText("");
                        isResult=false;
                    }
                    str = textView.getText().toString() + numButtons[index].getText().toString();
                    textView.setText(str);
                    isNum = true;
                }
            });
        }
        // +버튼 클릭 이벤트 리스너
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView.getText().toString() + "+";
                textView.setText(str);
            }
        });
        // -버튼 클릭 이벤트 리스너
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView.getText().toString() + "-";
                textView.setText(str);
            }
        });
        // *버튼 클릭 이벤트 리스너
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView.getText().toString() + "*";
                textView.setText(str);
            }
        });
        // /버튼 클릭 이벤트 리스너
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView.getText().toString() + "/";
                textView.setText(str);
            }
        });
        // *버튼 클릭 이벤트 리스너
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView.getText().toString() + "%";
                textView.setText(str);
            }
        });
        // .버튼 클릭 이벤트 리스너
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView.getText().toString() + ".";
                textView.setText(str);
            }
        });
        // ^버튼 클릭 이벤트 리스너
        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView.getText().toString() + "^";
                textView.setText(str);
            }
        });
        // Delete 버튼 클릭 이벤트 리스너
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=textView.getText().toString();
                textView.setText(str.substring(0,str.length()-1)); // 마지막 글자 지우기
            }
        });
        // =버튼 클릭 이벤트 리스너
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=textView.getText().toString();
                result=Eval.cal(str);
                if(result==null){ // 수식이 잘못되었을 때 토스트 메시지 출력
                    Toast.makeText(MainActivity.this, "잘못된 수식입니다.", Toast.LENGTH_SHORT).show();
                }
                textView.setText(result);
                isResult=true;
            }
        });
    }
}