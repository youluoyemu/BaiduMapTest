package com.example.youluoyemu2011.baidumaptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends AppCompatActivity {

    private MapView mMvBaidu;

    private LocationClient mLocationClient;
    private BDLocationListener mBdLocationListener = new MapLocationListener();
    private BaiduMap mBaiduMap;

    private boolean isFirstLocated = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(mBdLocationListener);
        setContentView(R.layout.activity_map);

        initView();
        initBaiduMap();
        initLocation();
        requestLocation();
    }

    private void requestLocation() {
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(3000);
    }

    private void initBaiduMap() {
        mBaiduMap=mMvBaidu.getMap();
        mBaiduMap.setMyLocationEnabled(true);
    }

    private void navigateTo(BDLocation location){
        if (isFirstLocated) {
            MapStatusUpdate update = MapStatusUpdateFactory.zoomTo(16.5f);
            mBaiduMap.animateMapStatus(update);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            update = MapStatusUpdateFactory.newLatLng(latLng);
            mBaiduMap.animateMapStatus(update);
            isFirstLocated=false;
        }
        MyLocationData.Builder builder = new MyLocationData.Builder();
        MyLocationData locationData = builder.latitude(location.getLatitude()).longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locationData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMvBaidu.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMvBaidu.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMvBaidu.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
    }

    private void initView() {
        mMvBaidu = (MapView) findViewById(R.id.mv_baidu);
    }

    public class MapLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            navigateTo(bdLocation);
        }
    }
}
