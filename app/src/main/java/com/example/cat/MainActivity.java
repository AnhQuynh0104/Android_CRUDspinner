package com.example.cat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.cat.model.Cat;
import com.example.cat.model.CatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.OnItemListener {

    private Spinner spinner;
    private int[] images = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
            R.drawable.cat4, R.drawable.cat5, R.drawable.cat6};
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private EditText editName, editPrice, editDescription;
    private Button btnAdd, btnEdit;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        recyclerView = findViewById(R.id.rview);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        editName = findViewById(R.id.editName);
        editDescription = findViewById(R.id.editDescription);
        editPrice = findViewById(R.id.editPrice);

        catAdapter = new CatAdapter(new ArrayList<>());
        catAdapter.setOnItemListener(this);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(catAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setEnabled(false);
                String image = spinner.getSelectedItem().toString();
                String name = editName.getText().toString();
                String price = editPrice.getText().toString();
                String description = editDescription.getText().toString();
                int img = R.drawable.cat1;
                double p = 0;
                try{
                    img = Integer.parseInt(image);
                    p = Double.parseDouble(price);
                } catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Gia khong hop le", Toast.LENGTH_SHORT).show();
                }
                Cat cat = new Cat(img, name, description, p);
                catAdapter.add(cat);
                editName.setText("");
                editDescription.setText("");
                editPrice.setText("");
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String image = spinner.getSelectedItem().toString();
                String name = editName.getText().toString();
                String price = editPrice.getText().toString();
                String description = editDescription.getText().toString();
                int img = R.drawable.cat1;
                double p = 0;
                try{
                    img = Integer.parseInt(image);
                    p = Double.parseDouble(price);
                } catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Gia khong hop le", Toast.LENGTH_SHORT).show();
                }
                Cat cat = new Cat(img, name, description, p);
                catAdapter.edit(cat, position);
                editName.setText("");
                editDescription.setText("");
                editPrice.setText("");
                btnEdit.setEnabled(false);
                btnAdd.setEnabled(true);
            }
        });
    }

//    private List<Cat> getList() {
//        List<Cat> list = new ArrayList<>();
//        list.add(new Cat(R.drawable.cat1, "Meo con", "Cute", 34));
//        return list;
//    }

    private void initView() {
        spinner = findViewById(R.id.spinner);
        SpinnerAdapter adapter = new com.example.cat.model.SpinnerAdapter();
        spinner.setAdapter(adapter);
    }


    @Override
    public void CatClickLister(View v, int position) {
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(true);
        Cat cat = catAdapter.getItem(position);
        editName.setText(cat.getName());
        editPrice.setText(cat.getPrice()+"");
        editDescription.setText(cat.getDescription());
        int img = cat.getImageCat();
        int p = 0;
        for(int i = 0 ; i < images.length; i++){
            if(img == images[i]){
                p = i;
                break;
            }
        }
        spinner.setSelection(p);
    }
}