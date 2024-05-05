package com.example.audioplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.Menu;
import android.view.MenuItem;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    String songName;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_upload) {
            
            pickSong();
            
        }
        return super.onOptionsItemSelected(item);
    }

    private void pickSong() {

        Intent intentUpload = new Intent();
        intentUpload.setType("Audio/*");
        intentUpload.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentUpload,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode==1){
            if (resultCode == RESULT_OK){

                uri = data.getData();
                Cursor cursor = getApplicationContext().getContentResolver().query(uri,null,null,null,null);
                int indexedName = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                cursor.moveToFirst();
                songName = cursor.getString(indexedName);
                cursor.close();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}