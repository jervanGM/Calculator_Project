package com.example.calculator_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class Scientific_Activity extends AppCompatActivity {
    private Button sin,cos,tan,asin,acos,atan,activity2,PI,E,invert,botonPrioridadInicio,botonPrioridadFinal,pow,log,ln,ans;
    private ImageButton image;
    private String resultado_visual;
    private double isDeg=1;
   // private GifImageButton gif;
    private TextView Result_activity;
    //Setup de los botones de la parte científica
    public void Setup2(){
        sin=(Button)findViewById(R.id.buttonSin) ;
        cos=(Button)findViewById(R.id.buttonCos) ;
        tan=(Button)findViewById(R.id.buttonTan) ;
        asin=(Button)findViewById(R.id.buttonAsin) ;
        acos=(Button)findViewById(R.id.buttonAcos) ;
        atan=(Button)findViewById(R.id.buttonAtan) ;
        botonPrioridadInicio=(Button)findViewById(R.id.buttonPStart) ;
        PI=(Button)findViewById(R.id.buttonPI) ;
        E=(Button)findViewById(R.id.buttonE) ;
        invert=(Button)findViewById(R.id.buttonInvert) ;
        botonPrioridadFinal=(Button)findViewById(R.id.buttonPend) ;
        pow=(Button)findViewById(R.id.buttonPow) ;
        log=(Button)findViewById(R.id.buttonLog) ;
        ln=(Button)findViewById(R.id.buttonLn) ;
       // activity2=(Button)findViewById(R.id.buttonActivity2) ;
        image=(ImageButton)findViewById(R.id.imageButtonActivity2);
        Result_activity= (TextView) findViewById(R.id.ResultActivity);
        ans=(Button)findViewById(R.id.buttonANS);
       // gif=(GifImageButton)findViewById(R.id.buttonActivity2);


    }
///////////////////////////////////////////////////////////////////////////////////////
                                //Método principal//
///////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_);
        Setup2();
        ((TextView)findViewById(R.id.buttonANS)).setText(Html.fromHtml("deg"));
        Intent intent =this.getIntent();
        resultado_visual=intent.getStringExtra("contador");
        Result_activity.setText(resultado_visual);

        //Envia los resultados al main para tratarlos
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity1=new Intent();
                activity1.putExtra("Grado",Double.toString(isDeg));
                activity1.putExtra("Visual",""+resultado_visual);

                setResult(MainActivity.RESULT_OK,activity1);
                finish();


            }
        });
        //Abre parentesis
        botonPrioridadInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("(");
            }
        });
        //Cierra parentesis
        botonPrioridadFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado_visual+=")";
                Result_activity.setText(resultado_visual);
            }
        });
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado_visual+="^";//Se añade como operación al número
                Result_activity.setText(resultado_visual);
            }
        });
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("ln(");
            }
        });
        asin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("asin(");
            }
        });
        acos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("acos(");
            }
        });
        atan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("atan(");
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("log(");
            }
        });
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("sin(");
            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("cos(");
            }
        });
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("tan(");
            }
        });
        invert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado_visual+="^(-1)";
                Result_activity.setText(resultado_visual);
            }
        });
        PI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("π");
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpression("e");
            }
        });
        //Define si las operaciones trigonometricas se realizan en grados o radianes
        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDeg==1){
                    ((TextView)findViewById(R.id.buttonANS)).setText(Html.fromHtml("rad"));
                    isDeg=0;
                }
                else{
                    ((TextView)findViewById(R.id.buttonANS)).setText(Html.fromHtml("deg"));
                    isDeg=1;
                }
            }
        });
    }
    public void addExpression(String xpression){
        if(!resultado_visual.equals("0")&& !resultado_visual.equals("")){
            if(Character.isDigit(resultado_visual.charAt(resultado_visual.length()-1)) || resultado_visual.charAt(resultado_visual.length()-1)==')' ) {
                resultado_visual+="x"+xpression;
            }
            else{
                resultado_visual+=xpression;
            }
        }
        else {
            resultado_visual=xpression;
        }
        Result_activity.setText(resultado_visual);
    }
}
