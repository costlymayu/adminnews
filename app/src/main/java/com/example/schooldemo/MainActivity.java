package com.example.schooldemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldemo.adapter.MyAdapter;
import com.example.schooldemo.viewmodel.MyListViewModel;

import java.util.ArrayList;

/**
 * Created by Sudam Chole on 18/08/20.
 */
public class MainActivity extends AppCompatActivity {
  private RecyclerView recyclerview;
  private MyListViewModel myListViewModel;
  private MyAdapter adapter;
  Button btnSchoolLevel, btnClassLevel;
  Toolbar toolbar;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar=findViewById(R.id.toolbar);
    recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
    btnSchoolLevel = findViewById(R.id.btnSchoolLevel);
    btnClassLevel = findViewById(R.id.btnClassLevel);
    toolbar.setTitle(getString(R.string.title));
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    btnSchoolLevel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        btnSchoolLevel.setBackground(getDrawable(R.drawable.button_bg));
        btnSchoolLevel.setTextColor(getColor(R.color.colorWhite));
        btnClassLevel.setBackground(getDrawable(R.drawable.button_stroke));
        btnClassLevel.setTextColor(getColor(R.color.colorBlack));
        myListViewModel.module="school";
        getData();
      }
    });
    btnClassLevel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        btnClassLevel.setBackground(getDrawable(R.drawable.button_bg));
        btnClassLevel.setTextColor(getColor(R.color.colorWhite));
        btnSchoolLevel.setBackground(getDrawable(R.drawable.button_stroke));
        btnSchoolLevel.setTextColor(getColor(R.color.colorBlack));
        myListViewModel.module="class";
        getData();
      }
    });
    myListViewModel = ViewModelProviders.of(this).get(MyListViewModel.class);
    myListViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<MyListViewModel>>() {
      @Override
      public void onChanged(ArrayList<MyListViewModel> myListViewModels) {
        adapter = new MyAdapter(myListViewModels, MainActivity.this);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.setAdapter(adapter);
      }
    });
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    itemTouchHelper.attachToRecyclerView(recyclerview);
  }
  void getData(){
    myListViewModel = ViewModelProviders.of(MainActivity.this).get(MyListViewModel.class);
    myListViewModel.getMutableLiveData().observe(MainActivity.this, new Observer<ArrayList<MyListViewModel>>() {
      @Override
      public void onChanged(ArrayList<MyListViewModel> myListViewModels) {
        adapter = new MyAdapter(myListViewModels, MainActivity.this);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
      }
    });
  }
  ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
      Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
      return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
      Toast.makeText(MainActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
      //Remove swiped item from list and notify the RecyclerView
      int position = viewHolder.getAdapterPosition();
      //recyclerview.remove(position);
      adapter.notifyDataSetChanged();

    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
      final View foregroundView = ((RecyclerView.ViewHolder) viewHolder).itemView;
      getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
        actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
      final View foregroundView = ((RecyclerView.ViewHolder) viewHolder).itemView;
      getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
      final View foregroundView = ((RecyclerView.ViewHolder) viewHolder).itemView;

      getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
        actionState, isCurrentlyActive);
    }
  };
}
