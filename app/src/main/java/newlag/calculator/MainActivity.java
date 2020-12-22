package newlag.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_button: // Прибавить

            break;
            case R.id.sub_button: // Вычесть

            break;
            case R.id.multi_button: // Умножить

            break;
            case R.id.div_button: // Поделить
            break;
            case R.id.equal_button: // Результат
            break;
            case R.id.clear_button: // Очистить

            break;
        }
    }
}