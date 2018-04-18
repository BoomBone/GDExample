package com.dexin.mapexamplegd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationListener
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AMapLocationListener {

    //声明AMapLocationClient类对象
    lateinit var mLocationClient: AMapLocationClient
    lateinit var mLocationOption: AMapLocationClientOption

    override fun onLocationChanged(location: AMapLocation?) {
        if (location != null) {
            val code = location.errorCode
            Log.e("main", "$code")
            Log.e("main", location.address)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLocation()
        initListener()
    }

    private fun initListener() {
        mMainBtn.setOnClickListener {
            mLocationClient.startLocation()
        }
    }

    private fun initLocation() {

        //初始化client
        mLocationClient = AMapLocationClient(applicationContext)
        mLocationOption = getDefaultOption()
        //设置定位参数
        mLocationClient.setLocationOption(mLocationOption)
        //设置定位回调监听
        mLocationClient.setLocationListener(this)
    }

    private fun getDefaultOption(): AMapLocationClientOption {
        val mOption = AMapLocationClientOption()
        mOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.isGpsFirst = false//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.httpTimeOut = 30000//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.interval = 2000//可选，设置定位间隔。默认为2秒
        mOption.isNeedAddress = true//可选，设置是否返回逆地理地址信息。默认是true
        mOption.isOnceLocation = false//可选，设置是否单次定位。默认是false
        mOption.isOnceLocationLatest = false//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP)//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.isSensorEnable = false//可选，设置是否使用传感器。默认是false
        mOption.isWifiScan = true //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.isLocationCacheEnable = true //可选，设置是否使用缓存定位，默认为true
        return mOption
    }

//    private fun initLocation() {
//        //声明AMapLocationClient类对象
//        var mLocationClient: AMapLocationClient? = null
//
//        //初始化定位
//        mLocationClient = AMapLocationClient(applicationContext)
//        //设置定位回调监听
//        mLocationClient.setLocationListener(this)
//
//        //声明AMapLocationClientOption对象
//        var mLocationOption: AMapLocationClientOption? = null
//        //初始化AMapLocationClientOption对象
//        mLocationOption = AMapLocationClientOption()
//
//        val option = AMapLocationClientOption()
//        /**
//         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
//         */
//        option.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.SignIn
//        if (null != locationClient) {
//            locationClient.setLocationOption(option)
//            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//            locationClient.stopLocation()
//            locationClient.startLocation()
//        }
//
//        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//
//        //设置是否返回地址信息（默认返回地址信息）
//        mLocationOption.setNeedAddress(true);
//
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        //启动定位
//        mLocationClient.startLocation();
//    }

}
