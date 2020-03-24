package com.example.testpicturereptile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.testpicturereptile.dataoperation.PictureDataOperation;
import com.example.testpicturereptile.uiclass.ImageAdapter;
import com.example.testpicturereptile.uiclass.SinglePicture;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // 权限相关
    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> mPermissionList = new ArrayList<>();
    private static final int PERMISSION_REQUEST = 1;

    // UI相关
    private SearchView searchView;
    private MyOnQueryTextListener searchViewListener;
    private RecyclerView pictureRecyclerView;
    private ImageAdapter pictureRecyclerAdapter;
    private ProgressBar clearCacheBar;
    private Button nextPageButton;
    private MyOnClickListener onClickListener;

    // 数据操作相关
    private List<SinglePicture> singlePictures = new ArrayList<>();
    private PictureDataOperation pictureDataOperation;
    private Integer page = 0;
    private String scriptName = "ScriptName";
    private String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 检测权限
        checkPermission();

        // 设置标题
        this.setTitle(this.scriptName);

        // 搜索框初始化
        this.searchView = this.findViewById(R.id.search_content);
        this.searchView.setSubmitButtonEnabled(true);
        searchViewListener = new MyOnQueryTextListener();
        this.searchView.setOnQueryTextListener(searchViewListener);

        // 图片显示器初始化
        this.initPictures();
        this.pictureRecyclerView = findViewById(R.id.picture_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        this.pictureRecyclerView.setLayoutManager(layoutManager);
        this.pictureRecyclerView.setHasFixedSize(true);
        this.pictureRecyclerView.setDrawingCacheEnabled(true);
        this.pictureRecyclerView.setItemViewCacheSize(20);
        this.pictureRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        this.pictureRecyclerAdapter = new ImageAdapter(singlePictures);
        this.pictureRecyclerAdapter.setHasStableIds(true);
        this.pictureRecyclerView.setAdapter(this.pictureRecyclerAdapter);

        this.clearCacheBar = findViewById(R.id.clear_cache_bar);
        this.clearCacheBar.setVisibility(View.GONE);

        this.pictureDataOperation = new PictureDataOperation(this);
        this.nextPageButton = findViewById(R.id.next_pagr_button);
        this.onClickListener = new MyOnClickListener();
        this.nextPageButton.setOnClickListener(this.onClickListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_change) {
            this.page = 0;
        } else if (id == R.id.nav_clear_cache) {
            this.clearCacheBar.setVisibility(View.VISIBLE);
            this.pictureDataOperation.clearCache();
            this.clearCacheBar.setVisibility(View.GONE);
        }else if (id == R.id.nav_ali) {

        } else if (id == R.id.nav_wechat) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 初始化图片函数
    private void initPictures() {
        for (int i=0; i<4; i++){
            SinglePicture s2 = new SinglePicture("Name", BitmapFactory.decodeResource(this.getResources(), R.drawable.test1));
            this.singlePictures.add(s2);
            SinglePicture s4 = new SinglePicture("Name", BitmapFactory.decodeResource(this.getResources(),R.drawable.test6));
            this.singlePictures.add(s4);
            SinglePicture s5 = new SinglePicture("Name", BitmapFactory.decodeResource(this.getResources(),R.drawable.test4));
            this.singlePictures.add(s5);
            SinglePicture s6 = new SinglePicture("Name", BitmapFactory.decodeResource(this.getResources(),R.drawable.test5));
            this.singlePictures.add(s6);
            SinglePicture s7 = new SinglePicture("Name", BitmapFactory.decodeResource(this.getResources(),R.drawable.test7));
            this.singlePictures.add(s7);

        }
    }

    // 搜索框监听器
    private final class  MyOnQueryTextListener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String queryRaw) {
            singlePictures.clear();
            query = queryRaw.trim();
            singlePictures.addAll(pictureDataOperation.handleSearch(query, page, scriptName));
            page ++;
            pictureRecyclerAdapter.notifyDataSetChanged();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return true;
        }
    }

    // 权限函数
    private void checkPermission() {
        mPermissionList.clear();

        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {

        } else {
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST:
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    // 下一页监听器
    private final class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            singlePictures.addAll(pictureDataOperation.handleSearch(query, page, scriptName));
            page ++;
            pictureRecyclerAdapter.notifyDataSetChanged();
        }
    }

}
