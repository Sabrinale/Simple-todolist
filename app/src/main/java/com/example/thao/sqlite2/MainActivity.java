
package com.example.thao.sqlite2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCongViec = (ListView) findViewById(R.id.listviewCOngViec);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, arrayCongViec);
        lvCongViec.setAdapter(adapter);

        //tao database
        database = new Database(this, "ghichu.sqlite", null, 1);
        // tao bang cong viec
        database.QueryData("CREATE TABLE IF NOT EXISTS  CongViec (Id INTEGER PRIMARY KEY AUTOINCREMENT, tenCV VARCHAR(200))");
        // INSERT DATA
        //database.QueryData("INSERT INTO CongViec VALUES(null, 'rua chen ')");
        GetDataCongViec();

    }
    public void DialogXoaCV (String ten, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Do you want to delete "+ten+ " ?");
        dialogXoa.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                database.QueryData("DELETE FROM CongViec WHERE Id ='"+id+"'");
                Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });
        dialogXoa.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();

    }

    public void DialigSuaCOngViec(String ten, final int id ){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);
        final EditText edtTenCV = (EditText) dialog.findViewById(R.id.editTextTenCVEdit);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuy);
        edtTenCV.setText(ten);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = edtTenCV.getText().toString().trim();
                database.QueryData(" UPDATE CongViec SET tenCV = '"+  tenmoi+"' WHERE Id ='"+id+"' ");
                Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
            }

        });
        dialog.show();

    }
    private void GetDataCongViec (){
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        arrayCongViec.clear();
        while (dataCongViec.moveToNext()) {
            String name = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);

            arrayCongViec.add(new CongViec(id, name));
            //Toast.makeText(this, name,Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_cong_viec, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnInstructions:
                Intent intentInstruction = new Intent(MainActivity.this, Instructions.class);
                startActivity(intentInstruction);
                Log.d("jotain", "jotain");
                return true;

            case R.id.mnList:
                Intent intentList = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentList);
                return true;
            case R.id.memuAdd:
                DialogThem();
                return super.onOptionsItemSelected(item);
        }

        return true;
        /*if (item.getItemId() == R.id.menuAdd){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);*/
    }
    private void DialogThem (){
        final Dialog dialog = new Dialog (this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView((R.layout.dialog_them_cong_viec));

        final EditText edtTen = (EditText)dialog.findViewById(R.id.editTextTenCV);
        Button btnThem = (Button) dialog.findViewById(R.id.buttonThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuy);
        btnHuy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String tencv = edtTen.getText().toString();
                if (tencv.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter your new item!", Toast.LENGTH_SHORT);
                }else {
                    database.QueryData("INSERT INTO CongViec VALUES(null, '"+tencv+"')");
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }

        });
        dialog.show();
    }
}
