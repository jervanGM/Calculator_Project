package com.example.calculator_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Vector;
import org.mariuszgromada.math.mxparser.*;
import pl.droidsonroids.gif.GifImageButton;

public class MainActivity extends AppCompatActivity {
    ////////////////////////////////////////////////////////////////////////////////////
                                //Asignación de variables//
    ////////////////////////////////////////////////////////////////////////////////////
    private Button botonMas,botonMenos,botonMult,botonDividir,botonPorcentage,botonCE,botonC,botonIgual,botonComa;
    private Button boton0,boton1,boton2,boton3,boton4,boton5,boton6,boton7,boton8,boton9;
    private ImageButton buttonActivity,Image,botonInfo;
    private TextView DispResultado;
    private GifImageButton mygif;
    private String string_resultado="0";
    private Double grade=1.0;
    ////////////////////////////////////////////////////////////////////////////////////
                     //Setup de los botones de la actividad principal//
    ////////////////////////////////////////////////////////////////////////////////////
    public void Setup(){
        botonMas=(Button)findViewById(R.id.buttonPlus) ;
        botonMenos=(Button)findViewById(R.id.buttonMinus) ;
        botonMult=(Button)findViewById(R.id.buttonMult) ;
        botonDividir=(Button)findViewById(R.id.buttonDivide) ;
        botonPorcentage=(Button)findViewById(R.id.buttonPercentage) ;
        botonCE=(Button)findViewById(R.id.buttonCE) ;
        botonC=(Button)findViewById(R.id.buttonC) ;
        botonIgual=(Button)findViewById(R.id.buttonEqual) ;
        botonComa=(Button)findViewById(R.id.buttonPoint) ;
        boton0=(Button)findViewById(R.id.button0) ;
        boton1=(Button)findViewById(R.id.button1) ;
        boton2=(Button)findViewById(R.id.button2) ;
        boton3=(Button)findViewById(R.id.button3) ;
        boton4=(Button)findViewById(R.id.button4) ;
        boton5=(Button)findViewById(R.id.button5) ;
        boton6=(Button)findViewById(R.id.button6) ;
        boton7=(Button)findViewById(R.id.button7) ;
        boton8=(Button)findViewById(R.id.button8) ;
        boton9=(Button)findViewById(R.id.button9) ;
        buttonActivity=(ImageButton)findViewById(R.id.buttonActivity) ;
        Image=(ImageButton)findViewById(R.id.imageButtonActivity);
        DispResultado= (TextView) findViewById(R.id.Result);
        mygif=(GifImageButton)findViewById(R.id.buttonActivity);
        botonInfo=(ImageButton) findViewById(R.id.info);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Setup();
        final Context context=this;
        ////////////////////////////////////////////////////////////////////////////////////
                                //Información de la app//
        ////////////////////////////////////////////////////////////////////////////////////
        botonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogInfo(context);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////
                                    //Operaciones Básicas//
        ////////////////////////////////////////////////////////////////////////////////////
        botonMas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addOperator("+");

            }

        });
        botonMenos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addOperator("-");
            }
        });
        botonMult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addOperator("x");
            }
        });
        botonDividir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addOperator("/");
            }
        });
        //Botón de resto
        botonPorcentage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addOperator("%");
            }
        });
        //Boton de coma anglosajona
        botonComa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               addOperator(".");
            }
        });
        //Boton de borrado parcial
        botonCE.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(!DispResultado.getText().equals("Syntax Error") && !DispResultado.getText().equals("Error")) {
                    if (!string_resultado.equals("0") && string_resultado.length() > 1) {//a==ln b==log c==sin d==cos f==tan g==asin h==acos j==atan

                        //Si los resultados son operaciones cientificas, excepto las comentadas en la parte cientifica
                        //Se borra la cantidad de carácteres que definen su string
                        if(string_resultado.length()>=3 && string_resultado.substring(string_resultado.length()-3).equals("ln(")){
                            string_resultado=string_resultado.substring(0,string_resultado.length()-3);
                        }
                        else if(string_resultado.length()>=5 &&(string_resultado.substring(string_resultado.length()-5).equals("asin(") ||
                                string_resultado.substring(string_resultado.length()-5).equals("acos(") ||
                                string_resultado.substring(string_resultado.length()-5).equals("atan("))){
                            string_resultado=string_resultado.substring(0,string_resultado.length()-5);
                        }
                        else if(string_resultado.length()>=4 &&(string_resultado.substring(string_resultado.length()-4).equals("sin(") ||
                                string_resultado.substring(string_resultado.length()-4).equals("cos(") ||
                                string_resultado.substring(string_resultado.length()-4).equals("tan(") ||
                                string_resultado.substring(string_resultado.length()-4).equals("log(")) ){
                            string_resultado=string_resultado.substring(0,string_resultado.length()-4);
                        }
                        else {
                            string_resultado = string_resultado.substring(0, string_resultado.length() - 1);
                        }
                        if("".equals(string_resultado)) string_resultado="0";
                        //Se borra cada caracter uno a uno si se pulsa el botón
                    } else if ("".equals(string_resultado) || string_resultado.length() == 1) {
                        string_resultado = "0";
                    }
                }
                else {//En caso de string vacio o 0
                    string_resultado="0";
                }
                DispResultado.setText(string_resultado);
            }
        });
        //Borrado total
        botonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                string_resultado="0";
                DispResultado.setText(string_resultado);

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////
                                //Operación de los resultados//
        ////////////////////////////////////////////////////////////////////////////////////
        botonIgual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    string_resultado=string_resultado.replace('x','*');
                    string_resultado=string_resultado.replace("π","pi");
                    string_resultado=string_resultado.replace("log","log10");
                    Expression math= new Expression(string_resultado);
                    if(grade==1) mXparser.setDegreesMode();
                    else mXparser.setRadiansMode();
                    string_resultado=String.valueOf(math.calculate());
                    if(string_resultado.equals("NaN") || string_resultado.equals("Infinity") || string_resultado.equals("-Infinity")) string_resultado="Error";
                    if(string_resultado.substring(string_resultado.length()-2).equals(".0")) string_resultado=string_resultado.substring(0,string_resultado.length()-2);
                    DispResultado.setText(string_resultado);
                }
                //Manejos de excepciones en caso de error de sintaxis
                catch (Exception e){
                    DispResultado.setText("Syntax Error");
                }


            }
        });
        ////////////////////////////////////////////////////////////////////////////////////
                                //Asignación de números//
        ////////////////////////////////////////////////////////////////////////////////////
        boton0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(0);
            }
        });
        boton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(1);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(2);
            }
        });
        boton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(3);
            }
        });
        boton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(4);
            }
        });
        boton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(5);
            }
        });
        boton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(6);
            }
        });
        boton7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(7);
            }
        });
        boton8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(8);
            }
        });
        boton9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNumber(9);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////
                         //Envio de resultados a la parte científicica//
        ////////////////////////////////////////////////////////////////////////////////////
        mygif.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent activity2 = new Intent(MainActivity.this,Scientific_Activity.class);

                String mensaje=string_resultado;
                activity2.putExtra("contador",mensaje);
                startActivityForResult(activity2,0);
            }
        });
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////
                  //Recepción de los resultados de la parte científica//
    ////////////////////////////////////////////////////////////////////////////////////
    public void onActivityResult(int requestCode,int resultCode, Intent data){

        if(data!=null){
            string_resultado=data.getStringExtra("Visual");
            grade=Double.parseDouble(data.getStringExtra("Grado"));
            DispResultado.setText(string_resultado);
        }
    }


    public void addNumber(int number){
        if(string_resultado.equals("0") || DispResultado.getText().equals("Syntax Error") || DispResultado.getText().equals("Error")){
            string_resultado=Integer.toString(number);
        }
        else{
            string_resultado=string_resultado+Integer.toString(number);

        }
        DispResultado.setText(string_resultado);
    }

    public void addOperator(String operator){
        string_resultado=string_resultado+operator;
        DispResultado.setText(string_resultado);
    }
    protected void onResume() {
        super.onResume();
    }

}
