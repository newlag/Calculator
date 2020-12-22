package newlag.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private EditText input;

    private List<Float> numbersList = new ArrayList<>();
    private List<Character> symbolsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.editText);
    }

    public void onClick(View v) {
        int id = v.getId();
        switch(id) {
            case R.id.equal_button: // Результат
                equal();
            break;
            case R.id.clear_button: // Очистить
                input.getText().clear();
            break;
            default:
                action(id);
        }
        input.setSelection(input.getText().length());
    }

    private void action(int id) {
        int inputLength = input.getText().length();
        if (inputLength < 1 || input.getText().charAt(inputLength - 1) == ' ') return;
        switch (id) {
            case R.id.add_button: // Прибавить
                input.setText(input.getText() + " + ");
                break;
            case R.id.sub_button: // Вычесть
                input.setText(input.getText() + " - ");
                break;
            case R.id.multi_button: // Умножить
                input.setText(input.getText() + " * ");
                break;
            case R.id.div_button: // Поделить
                input.setText(input.getText() + " / ");
                break;
        }
    }

    private void equal() {
        String calculate = input.getText().toString();
        calculate = calculate.replaceAll("\\s+","");
        StringTokenizer symbols = new StringTokenizer(calculate, "+-*/");
        StringTokenizer numbers = new StringTokenizer(calculate, "0123456789");
        while (symbols.hasMoreTokens()) {
            symbolsList.add(symbols.nextToken().charAt(0));
        }
        while (numbers.hasMoreTokens()) {
            numbersList.add(Float.parseFloat(symbols.nextToken()));
        }

        for (int i = 0; i < symbolsList.size(); i++) {
            float result;
            if (symbolsList.get(i).equals('*')) {
                result = numbersList.get(i) * numbersList.get(i + 1);
                updateLists(i, result);
            } else if(symbolsList.get(i).equals('/')) {
                result = numbersList.get(i) * numbersList.get(i + 1);
                updateLists(i, result);
            }
        }

        for (int i = 0; i < symbolsList.size(); i++) {
            float result;
            if (symbolsList.get(i).equals('+')) {
                result = numbersList.get(i) + numbersList.get(i + 1);
                updateLists(i, result);
            } else if(symbolsList.get(i).equals('-')) {
                result = numbersList.get(i) - numbersList.get(i + 1);
                updateLists(i, result);
            }
        }

        Toast.makeText(this, "Результат: " + numbersList.get(0), Toast.LENGTH_SHORT).show();
    }

    private void updateLists(int i, float result) {
        symbolsList.remove(i);
        numbersList.remove(i + 1);
        numbersList.set(i, result);
    }
}