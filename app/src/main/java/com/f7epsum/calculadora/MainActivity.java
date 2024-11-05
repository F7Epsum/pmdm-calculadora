package com.f7epsum.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    double valorActual = 0;
    double valorPrevio = 0;
    String operador = "";
    boolean nuevoNumero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDecimal = findViewById(R.id.buttonDecimal);
        Button buttonMas = findViewById(R.id.buttonMas);
        Button buttonMenos = findViewById(R.id.buttonMinus);
        Button buttonMultiplicar = findViewById(R.id.buttonMultiplicacion);
        Button buttonDividir = findViewById(R.id.buttonDiv);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonCalcular = findViewById(R.id.buttonCalc);
        TextView pantalla = findViewById(R.id.pantalla);

        View.OnClickListener listenerNumeros = v -> {
          Button button = (Button) v;
          if (nuevoNumero){
              pantalla.setText(button.getText().toString());
              nuevoNumero = false;
          }else {
              pantalla.append(button.getText().toString());
          }
          valorActual = Double.parseDouble(pantalla.getText().toString());
        };

        buttonDecimal.setOnClickListener(v -> {
            String textoPantalla = pantalla.getText().toString();
            if (nuevoNumero) {
                pantalla.setText("0.");
                nuevoNumero = false;
            } else if (!textoPantalla.contains(",")) {
                pantalla.append(",");
            }
        });

        button0.setOnClickListener(listenerNumeros);
        button1.setOnClickListener(listenerNumeros);
        button2.setOnClickListener(listenerNumeros);
        button3.setOnClickListener(listenerNumeros);
        button4.setOnClickListener(listenerNumeros);
        button5.setOnClickListener(listenerNumeros);
        button6.setOnClickListener(listenerNumeros);
        button7.setOnClickListener(listenerNumeros);
        button8.setOnClickListener(listenerNumeros);
        button9.setOnClickListener(listenerNumeros);

        View.OnClickListener listenerOperators = view -> {
            Button button = (Button) view;
            operador = button.getText().toString();
            valorPrevio = valorActual;
            nuevoNumero = true;
        };

        buttonMas.setOnClickListener(listenerOperators);
        buttonMenos.setOnClickListener(listenerOperators);
        buttonMultiplicar.setOnClickListener(listenerOperators);
        buttonDividir.setOnClickListener(listenerOperators);
        buttonClear.setOnClickListener(listenerOperators);

        buttonCalcular.setOnClickListener(view -> {
            double result = 0;
            switch (operador){
                case "+":
                    result = valorPrevio + valorActual;
                    break;
                case "-":
                    result = valorPrevio - valorActual;
                    break;
                case "*":
                    result = valorPrevio * valorActual;
                    break;
                case "/":
                    if (valorActual != 0){
                        result = valorPrevio / valorActual;
                    } else {
                        pantalla.setText("Error MATH");
                        return;
                    }
                    break;

            }
            pantalla.setText(String.valueOf(result));
            valorActual = result;
            nuevoNumero = true;
        });

        buttonClear.setOnClickListener(view -> {
            valorActual = 0;
            valorPrevio = 0;
            pantalla.setText("0");
            nuevoNumero = true;
        });


    }
}