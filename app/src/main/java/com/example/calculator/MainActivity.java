package com.example.calculator;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    private int openParenthesis = 0;
    private boolean dotUsed = false;
    private boolean equalClicked = false;
    private String lastExpression = "";
    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_OPEN_PARENTHESIS = 2;
    private final static int IS_CLOSE_PARENTHESIS = 3;
    private final static int IS_DOT = 4;

    Button buttonNumber0;
    Button buttonNumber1;
    Button buttonNumber2;
    Button buttonNumber3;
    Button buttonNumber4;
    Button buttonNumber5;
    Button buttonNumber6;
    Button buttonNumber7;
    Button buttonNumber8;
    Button buttonNumber9;

    Button buttonClear;
    Button buttonParentheses;
    Button buttonPercent;
    Button buttonDiv;
    Button buttonMul;
    Button buttonMinus;
    Button buttonAdd;
    Button buttonEqual;
    Button buttonDot;

    TextView input;
    ScriptEngine scriptEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scriptEngine = new ScriptEngineManager().getEngineByName("rhino");
        readyViewVariables();
        readyOnClickListeners();
        readyOnTouchListeners();
    }

    private void readyOnTouchListeners() {
        buttonNumber0.setOnTouchListener(this);
        buttonNumber1.setOnTouchListener(this);
        buttonNumber2.setOnTouchListener(this);
        buttonNumber3.setOnTouchListener(this);
        buttonNumber4.setOnTouchListener(this);
        buttonNumber5.setOnTouchListener(this);
        buttonNumber6.setOnTouchListener(this);
        buttonNumber7.setOnTouchListener(this);
        buttonNumber8.setOnTouchListener(this);
        buttonNumber9.setOnTouchListener(this);

        buttonClear.setOnTouchListener(this);
        buttonParentheses.setOnTouchListener(this);
        buttonPercent.setOnTouchListener(this);
        buttonDiv.setOnTouchListener(this);
        buttonMul.setOnTouchListener(this);
        buttonMinus.setOnTouchListener(this);
        buttonAdd.setOnTouchListener(this);
        buttonDot.setOnTouchListener(this);
    }

    private void readyOnClickListeners() {
        buttonNumber0.setOnClickListener(this);
        buttonNumber1.setOnClickListener(this);
        buttonNumber2.setOnClickListener(this);
        buttonNumber3.setOnClickListener(this);
        buttonNumber4.setOnClickListener(this);
        buttonNumber5.setOnClickListener(this);
        buttonNumber6.setOnClickListener(this);
        buttonNumber7.setOnClickListener(this);
        buttonNumber8.setOnClickListener(this);
        buttonNumber9.setOnClickListener(this);

        buttonClear.setOnClickListener(this);
        buttonParentheses.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    private void readyViewVariables() {
        buttonNumber0 = (Button) findViewById(R.id.button_zero);
        buttonNumber1 = (Button) findViewById(R.id.button_one);
        buttonNumber2 = (Button) findViewById(R.id.button_two);
        buttonNumber3 = (Button) findViewById(R.id.button_three);
        buttonNumber4 = (Button) findViewById(R.id.button_four);
        buttonNumber5 = (Button) findViewById(R.id.button_five);
        buttonNumber6 = (Button) findViewById(R.id.button_six);
        buttonNumber7 = (Button) findViewById(R.id.button_seven);
        buttonNumber8 = (Button) findViewById(R.id.button_eight);
        buttonNumber9 = (Button) findViewById(R.id.button_nine);

        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonParentheses = (Button) findViewById(R.id.button_parentheses);
        buttonPercent = (Button) findViewById(R.id.button_percent);
        buttonDiv = (Button) findViewById(R.id.button_div);
        buttonMul = (Button) findViewById(R.id.button_mul);
        buttonMinus = (Button) findViewById(R.id.button_minus);
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonEqual = (Button) findViewById(R.id.button_equal);
        buttonDot = (Button) findViewById(R.id.button_dot);

        input = (TextView) findViewById(R.id.txtInput);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_zero:
                if (addNum("0"))
                    equalClicked = false;
                break;

            case R.id.button_one:
                if (addNum("1"))
                    equalClicked = false;
                break;

            case R.id.button_two:
                if (addNum("2"))
                    equalClicked = false;
                break;

            case R.id.button_three:
                if (addNum("3"))
                    equalClicked = false;
                break;

            case R.id.button_four:
                if (addNum("4"))
                    equalClicked = false;
                break;

            case R.id.button_five:
                if (addNum("5"))
                    equalClicked = false;
                break;

            case R.id.button_six:
                if (addNum("6"))
                    equalClicked = false;
                break;

            case R.id.button_seven:
                if (addNum("7"))
                    equalClicked = false;
                break;

            case R.id.button_eight:
                if (addNum("8"))
                    equalClicked = false;
                break;

            case R.id.button_nine:
                if (addNum("9"))
                    equalClicked = false;
                break;

            case R.id.button_add:
                if (addOperand("+"))
                    equalClicked = false;
                break;

            case R.id.button_minus:
                if (addOperand("-"))
                    equalClicked = false;
                break;

            case R.id.button_mul:
                if (addOperand("x"))
                    equalClicked = false;
                break;

            case R.id.button_div:
                if (addOperand("\u00F7"))
                    equalClicked = false;
                break;

            case R.id.button_percent:
                if (addOperand("%"))
                    equalClicked = false;
                break;

            case R.id.button_dot:
                if (addDot())
                    equalClicked = false;
                break;

            case R.id.button_parentheses:
                if (addParenthesis())
                    equalClicked = false;
                break;

            case R.id.button_clear:
                input.setText("");
                openParenthesis = 0;
                dotUsed = false;
                equalClicked = false;
                break;

            case R.id.button_equal:
                if (input.getText().toString() != null || !input.getText().toString().equals("")){
                    calculate(input.getText().toString());
                    equalClicked = true;
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return false;
    }

    private boolean addNum(String num) {
        boolean done = false;
        int operationLength = input.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = input.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                input.setText(num);
                done = true;
            } else if (lastCharacterState == IS_OPEN_PARENTHESIS)
            {
                input.setText(input.getText() + num);
                done = true;
            } else if (lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%"))
            {
                input.setText(input.getText() + "x" + num);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT)
            {
                input.setText(input.getText() + num);
                done = true;
            }
        } else
        {
            input.setText(input.getText() + num);
            done = true;
        }
        return done;
    }

    private boolean addOperand(String operand) {
        boolean done = false;
        int operationLength = input.getText().length();
        if (operationLength > 0)
        {
            String lastInput = input.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%")))
            {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else if (operand.equals("%") && defineLastCharacter(lastInput) == IS_NUMBER)
            {
                input.setText(input.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else if (!operand.equals("%"))
            {
                input.setText(input.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else
        {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addDot() {
        boolean done = false;

        if (input.getText().length() == 0)
        {
            input.setText("0.");
            dotUsed = true;
            done = true;
        } else if (dotUsed == true)
        {
        } else if (defineLastCharacter(input.getText().charAt(input.getText().length() - 1) + "") == IS_OPERAND)
        {
            input.setText(input.getText() + "0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(input.getText().charAt(input.getText().length() - 1) + "") == IS_NUMBER)
        {
            input.setText(input.getText() + ".");
            done = true;
            dotUsed = true;
        }
        return done;
    }

    private int defineLastCharacter(String lastCharacter) {
        try
        {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e)
        {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x")
                || lastCharacter.equals("\u00F7") || lastCharacter.equals("%")))
            return IS_OPERAND;

        if (lastCharacter.equals("("))
            return IS_OPEN_PARENTHESIS;

        if (lastCharacter.equals(")"))
            return IS_CLOSE_PARENTHESIS;

        if (lastCharacter.equals("."))
            return IS_DOT;

        return -1;
    }

    private boolean addParenthesis() {
        boolean done = false;
        int operationLength = input.getText().length();

        if (operationLength == 0)
        {
            input.setText(input.getText() + "(");
            dotUsed = false;
            openParenthesis++;
            done = true;
        } else if (openParenthesis > 0 && operationLength > 0)
        {
            String lastInput = input.getText().charAt(operationLength - 1) + "";
            switch (defineLastCharacter(lastInput))
            {
                case IS_NUMBER:
                    input.setText(input.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
                case IS_OPERAND:
                    input.setText(input.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_OPEN_PARENTHESIS:
                    input.setText(input.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_CLOSE_PARENTHESIS:
                    input.setText(input.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
            }
        } else if (openParenthesis == 0 && operationLength > 0)
        {
            String lastInput = input.getText().charAt(operationLength - 1) + "";
            if (defineLastCharacter(lastInput) == IS_OPERAND)
            {
                input.setText(input.getText() + "(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            } else
            {
                input.setText(input.getText() + "x(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            }
        }
        return done;
    }

    private void calculate(String s) {

        String result = null;
        try
        {
            String temp = s;
            result = scriptEngine.eval(temp.replaceAll("%", "/100")
                            .replaceAll("x", "*")
                            .replaceAll("[^\\x00-\\x7F]", "/")
                            ).toString();
            BigDecimal decimal = new BigDecimal(result);
            result = decimal.setScale(8, BigDecimal.ROUND_HALF_UP).toString();
            equalClicked = true;
            input.setText(result);

        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.equals("Infinity"))
        {
            Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
            input.setText(s);

        } else if (result.contains("."))
        {
            result = result.replaceAll("\\.?0*$", "");
            input.setText(result);
        }
    }

}