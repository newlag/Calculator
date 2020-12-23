package newlag.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.editText);
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (".".equals(source)) {
                    String inputText = input.getText().toString();
                    String[] nums = inputText.split("[+\\-/*]");
                    if (nums.length == 0 || !nums[nums.length - 1].contains(",")) {
                        if (inputText.length() > 0 && isNumber(inputText.charAt(inputText.length() - 1)))
                            input.setText(inputText + ",");
                        input.setSelection(input.getText().length());
                    }
                    return "";
                }
                return null;
            }
        };
        input.setFilters(new InputFilter[]{filter});
    }

    private boolean isNumber(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
            return true;
        } catch (Exception ex) {
            return false;
        }
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
        String text = input.getText().toString();
        int inputLength = text.length();
        if (inputLength > 0 && isNumber(text.charAt(inputLength - 1))) {
            switch (id) {
                case R.id.add_button: // Прибавить
                    inputAppend("+");
                    break;
                case R.id.sub_button: // Вычесть
                    inputAppend("-");
                    break;
                case R.id.multi_button: // Умножить
                    inputAppend("*");
                    break;
                case R.id.div_button: // Поделить
                    inputAppend("/");
                    break;
            }
        } else if (id == R.id.sub_button) {
            if (inputLength > 1 && isNumber(text.charAt(inputLength - 2)) && text.charAt(inputLength - 1) != ',' || inputLength == 0)  {
                inputAppend("-");
            }
        }
    }

    private void equal() {
        try {
            String input = this.input.getText().toString();
            Calculator calculator = new Calculator();
            Double result = calculator.calculate(input);
            String formattedDouble = new DecimalFormat("#0.00").format(result);
            this.input.setText(formattedDouble.replaceAll("[.]", ","));
        } catch (StringIndexOutOfBoundsException ex) {
            Toast.makeText(this, R.string.incorrent_input, Toast.LENGTH_SHORT).show();
        } catch (ZeroDivisionException ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void inputAppend(String append) {
        input.setText(input.getText() + append);
    }
}