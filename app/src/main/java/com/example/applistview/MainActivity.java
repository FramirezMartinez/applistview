package com.example.applistview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private ArrayList<String> names;
    private EditText n1;
    private EditText n2;
    private Button sumar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        sumar = findViewById(R.id.sumar);

        listView = findViewById(R.id.listView);
        //se crea el arreglo llamado names
        names = new ArrayList<>();

        //fuente de datos  String
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        //cuando inicie la APP que el cursor inicie en el número 1
        n1.requestFocus();

        //escuchadorres
        sumar.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Obtener el valor seleccionado de la lista
                String itemSeleccionado = names.get(position);

                // Mostrar los valores de n1 y n2 junto con el elemento seleccionado en un Toast
                Toast.makeText(MainActivity.this,  itemSeleccionado, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sumar)
         {
            String valor1String = n1.getText().toString();
            String valor2String = n2.getText().toString();

            //si los campos están vacios que muestre un mensaje e informe
            // los al usuario que no ha introducido valores
            if (valor1String.isEmpty() || valor2String.isEmpty())
            {
             Toast.makeText(MainActivity.this, "Por favor, ingrese valores en ambos campos.", Toast.LENGTH_SHORT).show();
             return;
            }


            // Obtener los valores de los EditText y realizar la suma
            int valor1 = Integer.parseInt(n1.getText().toString());
            int valor2 = Integer.parseInt(n2.getText().toString());
            int resultado = valor1 + valor2;
             String mensaje = valor1 + " + " + valor2 + " = " + resultado;


             // Agregar los valores de n1, n2 y su suma a la lista
            names.add(mensaje);
            // Notificar al adaptador para que se actualice la lista
            ((ArrayAdapter) listView.getAdapter()).notifyDataSetChanged();

            // Mostrar un Toast con el mensaje
           // Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_LONG).show();

             //una vez hacho la suma vacir los campos de n1 y n2
            n1.setText("");
            n2.setText("");
       }
     }
    }


