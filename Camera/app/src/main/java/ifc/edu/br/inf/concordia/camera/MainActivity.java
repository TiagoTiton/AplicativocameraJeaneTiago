package ifc.edu.br.inf.concordia.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         img = (ImageView) findViewById(R.id.imageView);
         img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                abrirCamera();
            }
        });
    }

    public void abrirCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        InputStream stream = null;
        if(requestCode == 0 && resultCode == RESULT_OK){
            try{
                if (bitmap != null){
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
            } catch (FileNotFoundException e){
                e.printStackTrace();

            } finally {
                if (stream != null) try{
                    stream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
