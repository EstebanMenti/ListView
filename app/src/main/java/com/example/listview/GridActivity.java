package com.example.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {


    private GridView gridView;
    MyAdapter myAdapter;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);


        gridView = findViewById(R.id.gridview);

        names = new ArrayList<>();
        names.add("Esteban");
        names.add("Sergio");
        names.add("Menti");

        myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        gridView.setAdapter(myAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this,names.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(gridView);
    }
    //inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }
    //Manejamos eventos click en el menu opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.add_item:
                //agregamos item
                this.names.add("Nuevo Nombre");
                //Actualizamos lista
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //inflamos el layout del contex menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));

        menuInflater.inflate(R.menu.contex_menu, menu);
    }

    //Manejamos eventos click en el contex  menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.delete_item:
                //Borramos item
                this.names.remove(info.position);
                //Actualizamos lista
                this.myAdapter.notifyDataSetChanged();
                return true;
                default:
                    return super.onContextItemSelected(item);
        }


    }
}
