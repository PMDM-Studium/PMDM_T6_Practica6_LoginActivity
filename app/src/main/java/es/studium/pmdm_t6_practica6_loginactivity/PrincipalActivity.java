package es.studium.pmdm_t6_practica6_loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_borrar;

    public static final String MyPREFERENCES = "LoginCredentials";
    public static final String Usuario = "nombreKey";
    public static final String Clave = "claveKey";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        btn_borrar = findViewById(R.id.btn_borrar);
        btn_borrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Cargamos las SharedPreferences y abrimos el editor
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Borramos el usuario y la clave
        editor.remove(Usuario);
        editor.remove(Clave);
        editor.apply();
        //Mostramos un Toast indicando qque se han borrado las credenciales
        Toast.makeText(PrincipalActivity.this, R.string.borrado_datos, Toast.LENGTH_LONG).show();
        // Volvemos al activity_main
        finish();
    }
}