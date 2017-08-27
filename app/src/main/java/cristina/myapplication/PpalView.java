package cristina.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Cristina on 16/8/17.
 */
public class PpalView extends AppCompatActivity implements View.OnClickListener{
    ImageView outfit, outfit1, outfit2, outfit3, outfit4, outfit5, outfit6, outfit7, outfit8, outfit9;
    //Button btnCambioFondo;
    // int fondoID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // note that use read_comments.xml instead of our single_post.xml
        setContentView(R.layout.ppal_view);


        outfit = (ImageView) findViewById(R.id.outfit);
        outfit1 = (ImageView) findViewById(R.id.outfit1);
        outfit2 = (ImageView) findViewById(R.id.outfit2);
        outfit3 = (ImageView) findViewById(R.id.outfit3);
        outfit4 = (ImageView) findViewById(R.id.outfit4);
        outfit5 = (ImageView) findViewById(R.id.outfit5);
        outfit6 = (ImageView) findViewById(R.id.outfit6);
        outfit7 = (ImageView) findViewById(R.id.outfit7);
        outfit8 = (ImageView) findViewById(R.id.outfit8);
        outfit9 = (ImageView) findViewById(R.id.outfit9);
        //btnCambioFondo = (Button) findViewById(R.id.btnCambioFondo);

        outfit1.setOnClickListener(this);
        outfit2.setOnClickListener(this);
        outfit3.setOnClickListener(this);
        outfit4.setOnClickListener(this);
        outfit5.setOnClickListener(this);
        outfit6.setOnClickListener(this);
        outfit7.setOnClickListener(this);
        outfit8.setOnClickListener(this);
        outfit9.setOnClickListener(this);
        //btnCambioFondo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.outfit1:
                outfit.setImageResource(R.mipmap.outfit1);
                //fondoID = R.drawable.outfit1;
                break;
            case R.id.outfit2:
                outfit.setImageResource(R.mipmap.outfit2);
                // fondoID = R.drawable.outfit2;
                break;
            case R.id.outfit3:
                outfit.setImageResource(R.mipmap.outfit3);
                // fondoID = R.drawable.outfit3;
                break;
            case R.id.outfit4:
                outfit.setImageResource(R.mipmap.outfit4);
                //fondoID = R.drawable.outfit4;
                break;
            case R.id.outfit5:
                outfit.setImageResource(R.mipmap.outfit5);
                //fondoID = R.drawable.outfit5;
                break;
            case R.id.outfit6:
                outfit.setImageResource(R.mipmap.outfit6);
                // fondoID = R.drawable.outfit6;
                break;
            case R.id.outfit7:
                outfit.setImageResource(R.mipmap.outfit7);
                // fondoID = R.drawable.outfit7;
                break;
            case R.id.outfit8:
                outfit.setImageResource(R.mipmap.outfit8);
                //fondoID = R.drawable.outfit8;
                break;
            case R.id.outfit9:
                outfit.setImageResource(R.mipmap.outfit9);
                //fondoID = R.drawable.outfit9;
                break;
         /*  case R.id.btnCambioFondo:
               Bitmap fondo = BitmapFactory.decodeStream(getResources().openRawResource(fondoID));
               try{
                   getApplicationContext().setWallpaper(fondo);
               }catch (IOException e){
                   e.printStackTrace();
               }

               break;*/

        }
    }

    public void llamar(View view){

        Intent i = new Intent (PpalView.this, ImgCamera.class);
        startActivity(i);

    }

    public void cerrarSesion(View view){

        Intent i = new Intent (PpalView.this, LogoutFb.class);
        startActivity(i);

    }



}


