package com.example.myapp1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonC,buttonOpenBr,buttonCloseBr,buttonDiv;
    MaterialButton button7,button8,button9,buttonPlus,buttonMinus,buttonEquals,buttonMul;
    MaterialButton buttonAc,buttonDot;
    MaterialButton button0,button1,button2,button3,button4,button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);
        AssignId(button0,R.id.button_0);
        AssignId(button1,R.id.button_1);
        AssignId(button2,R.id.button_2);
        AssignId(button3,R.id.button_3);
        AssignId(button4,R.id.button_4);
        AssignId(button5,R.id.button_5);
        AssignId(button6,R.id.button_6);
        AssignId(button7,R.id.button_7);
        AssignId(button8,R.id.button_8);
        AssignId(button9,R.id.button_9);
        AssignId(buttonAc,R.id.button_ac);
        AssignId(buttonC,R.id.button_c);
        AssignId(buttonOpenBr,R.id.button_open_bracket);
        AssignId(buttonCloseBr,R.id.button_close_bracket);
        AssignId(buttonDiv,R.id.button_divide);
        AssignId(buttonPlus,R.id.button_addition);
        AssignId(buttonMinus,R.id.button_substract);
        AssignId(buttonMul,R.id.button_multiply);
        AssignId(buttonDot,R.id.button_dot);
        AssignId(buttonEquals,R.id.button_equal);


    }
void AssignId(MaterialButton btn, int id)
{
    btn=findViewById(id);
    btn.setOnClickListener(this);
}
    @Override
    public void onClick(View view)
    {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String CalculD=solutionTv.getText().toString();

        try
        {


        if(buttonText.equals("AC"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            if(CalculD.length()>1)
            {
                CalculD=CalculD.substring(0,CalculD.length()-1);

            }
            else
            {
                CalculD="0";
            }

        }
        else
        {
            CalculD=CalculD+buttonText;

        }
        solutionTv.setText(CalculD);
        String finalResult = getResult(CalculD);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
        }catch(Exception e)
        {

        }


    }
    String getResult(String data)
    {
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }


    }
}