package es.studium.pmdm_t6_practica6_loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText txt_usuario;
    EditText txt_clave;
    Switch switch_guardar;
    Button btn_acceder;


    //Preguntar como hacerlo
//    String usuarioCredencial = String.valueOf(R.string.usuarioCredencial);
//    String ClaveCredencial = String.valueOf(R.string.claveCredencial);
//    String usuarioCredencial = getString(R.string.usuarioCredencial);
//    String ClaveCredencial = getString(R.string.claveCredencial);

    //Asignamos el usuario y el nombre
    String usuarioCredencial = "Alvaro";
    String ClaveCredencial = "28835553Q";

    public static final String MyPREFERENCES = "LoginCredentials";
    public static final String Usuario = "nombreKey";
    public static final String Clave = "claveKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        txt_usuario = findViewById(R.id.txt_Usuario);
        txt_clave = findViewById(R.id.txt_Clave);
        switch_guardar = findViewById(R.id.switch_guardar);
        btn_acceder = findViewById(R.id.btn_acceder);

        //Obtenemos la informacion guardada en la SharedPreferences
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String sharedUsuario = sharedPreferences.getString(Usuario, "");
        String sharedClave = sharedPreferences.getString(Clave, "");

        //Comprobamos si la SharedPreferences corresponden a las credenciales
        if ((sharedUsuario.equals(usuarioCredencial))&&(sharedClave.equals(ClaveCredencial))) {
            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }

        btn_acceder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(v.getId() == (R.id.btn_acceder)){
                    //Comprobamos que se halla introducido el usuario y la contraseña
                    if(!txt_usuario.getText().toString().equals("") && !txt_clave.getText().toString().equals("")){
                        String usuarioIntroducido = txt_usuario.getText().toString();
                        String claveIntroducida = txt_clave.getText().toString();

                        // Si las credenciales introducidas son correctas
                        if(usuarioIntroducido.equals(usuarioCredencial) && claveIntroducida.equals(ClaveCredencial)){
                            // Comprobamos si el swicth de guardar la clave esta activo
                            if(switch_guardar.isChecked()){
                                //Abrimos el editor de SharedPreferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                //Guardamos las claves introducidas
                                editor.putString(Usuario, usuarioIntroducido);
                                editor.putString(Clave, claveIntroducida);
                                editor.commit();
                            }
                            //Iniciamos la activity principal
                            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                            startActivity(intent);
                            }
                        //Si las credenciales no son correctas mostramos un Toast indicando que no son correctas
                        else{
                            Toast.makeText(MainActivity.this, R.string.error_credenciales, Toast.LENGTH_LONG).show();
                        }
                    }
                    //Si no hemos introducido el usuario o la contraseña nos saldra un toast indicando el error
                    else{
                        Toast.makeText(MainActivity.this, R.string.error_faltacredenciales, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}