package com.firefly.faceEngine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firefly.arcterndemo.R;
import com.firefly.faceEngine.App;
import com.firefly.faceEngine.dblib.DBManager;
import com.firefly.faceEngine.dblib.bean.Person;
import com.firefly.faceEngine.other.Debug;
import com.firefly.faceEngine.utils.Constants;
import com.firefly.faceEngine.utils.GlideImageLoader;
import com.firefly.faceEngine.utils.SPUtil;
import com.firefly.faceEngine.utils.Tools;
import com.firefly.fireflyapidemo.MainActivity;
import com.intellif.YTLFFaceManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ArcternMainActivity extends BaseActivity {

    DBManager dbManager = App.getInstance().getDbManager();

    public void getAllPersonMain(View view){
        List<Person> personList = dbManager.getPersonList();
        for (Person person:personList) {
            Log.i("", "人脸: "+person);
        }

    }

    // 在线获取授权 API_KEY
    public final String API_KEY = "xrZEJz51qfiBI3FB";

    // 指定本地SD卡目录，用于存放models和license公钥等文件
    public static String FACE_PATH = "/sdcard/firefly/";

    // SDK
    private YTLFFaceManager YTLFFace = YTLFFaceManager.getInstance().initPath(FACE_PATH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_main);
        initSetting();

        if (!Tools.isCameraCanUse()) {
            Tools.toast("Camera occupied or unavailable");
            finish();
        } else {
            //findViewById(R.id.btn_detect).performClick();
        }
         final String TAG = "FragmentActivity";
//         调用第三方接口 post请求 添加数据
        Button add_btn = findViewById(R.id.add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String json = "{\n" +
                                    "    \"name\": \"农夫山泉\",\n" +
                                    "        \"category\": \"Stationery\",\n" +
                                    "        \"price\": 1,\n" +
                                    "        \"description\": \"A ballpoint pen used for writing records\",\n" +
                                    "        \"inventory\": 8888,\n" +
                                    "        \"picture\": \"www.baidu.com\"\n" +
                                    "}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("http://192.168.43.214:8081/goods")
                                    .post(RequestBody.create(MediaType.parse("application/json"),json))
                                    .build();
                            Response response = client.newCall(request).execute();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ArcternMainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ArcternMainActivity.this, "网络连接失败！！！", Toast.LENGTH_SHORT).show();
                                    Log.i(TAG, "run: "+e);
                                }
                            });

                        }
                    }
                }).start();
            }
        });

//    调用第三方接口 get方法 获取用户列表
        Button show_btn = findViewById(R.id.show);
        show_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
//                        String json = "{\n" +
//                                "    \"name\": \"连阳阳\",\n" +
//                                "        \"category\": \"Stationery\",\n" +
//                                "        \"price\": 1,\n" +
//                                "        \"description\": \"A ballpoint pen used for writing records\",\n" +
//                                "        \"inventory\": 8888,\n" +
//                                "        \"picture\": \"www.baidu.com\"\n" +
//                                "}";
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://192.168.43.214:8081/goods")
                                .get()
                                .build();
                        Response response = client.newCall(request).execute();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ArcternMainActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                                try {
                                    Log.i(TAG, "商品列表: "+response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }catch (Exception e){
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ArcternMainActivity.this, "网络连接失败！！！", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "run: "+e);
                            }
                        });

                    }
                }
            }).start();
        }
    });
        //    调用第三方接口 put方法 修改用户
        Button update_btn = findViewById(R.id.update);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                        String json = "{\n" +
                                "            \"id\": 7,\n" +
                                "            \"name\": \"郑嘉茜\",\n" +
                                "            \"category\": \"Stationery\",\n" +
                                "            \"price\": 1,\n" +
                                "            \"description\": \"A ballpoint pen used for writing records\",\n" +
                                "            \"inventory\": 8888,\n" +
                                "            \"picture\": \"www.baidu.com\"\n" +
                                "        }";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("http://192.168.43.214:8081/goods")
                                    .put(RequestBody.create(MediaType.parse("application/json"),json))
                                    .build();
                            Response response = client.newCall(request).execute();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ArcternMainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ArcternMainActivity.this, "网络连接失败！！！", Toast.LENGTH_SHORT).show();
                                    Log.i(TAG, "run: "+e);
                                }
                            });

                        }
                    }
                }).start();
            }
        });
}

    // 是否SDK是否已可以
    private boolean isFaceSdkReady() {
        return YTLFFaceManager.isSDKRuning && YTLFFaceManager.isLoadDB;
    }

    // 初始化SDK
    private void initSdk(Runnable runnable) {
        Tools.showLoadingProgress(this, false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (YTLFFace.initIntellif(context) && initLicenseBySecret() && startFaceSDK() && loadDB()) {
                        Tools.debugLog("initSDK finish");
                    }

                    if (isFaceSdkReady()) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    } else {
                        if (!Tools.isNetWorkConnect() && !YTLFFace.checkLicense()) {
                            Tools.toast(R.string.ytlf_dictionaries43);
                        }
                    }

                } catch (Exception e) {
                    Tools.printStackTrace(e);
                    finish();
                } finally {
                    Tools.dismissLoadingProgress();
                }
            }
        }).start();
    }

    // 检测环境，并运行
    private void runOnFaceSdkReady(Runnable runnable) {
        if (isFaceSdkReady()) {
            if (runnable != null) {
                runnable.run();
            }
        } else {
            initSdk(runnable);
        }
    }

    // 加载授权license
    public boolean initLicenseBySecret() {
        return YTLFFace.initLicense(API_KEY);
        //return YTLFFace.initLicenseBySecret();
    }

    // 启动FaceSDK
    public boolean startFaceSDK() {
        if (!YTLFFaceManager.isSDKRuning) {
            int flag = YTLFFace.startFaceSDK();
            if (flag != 0) {
                YTLFFaceManager.isSDKRuning = false;
                showShortToast(R.string.app_name);
            } else {
                YTLFFaceManager.isSDKRuning = true;
            }
        }
        return YTLFFaceManager.isSDKRuning;
    }

    // 加载人脸库
    public boolean loadDB() {
        DBManager dbManager = App.getInstance().getDbManager();
        List<Person> personList = dbManager.getPersonList();
        if (personList.size() <= 0) {
            Tools.debugLog("initSDK DB is empty");
            YTLFFaceManager.isLoadDB = true;
        } else {
            int size = personList.size();
            ArrayList<Long> idList  = new ArrayList<>();
            ArrayList<byte[]> featureList  = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                try {
                    Person person = personList.get(i);
                    if (idList.contains(person.getId()) || featureList.contains(person.getFeature()) ||
                            person.getFeature()== null || person.getFeature().length < 1) {
                        Tools.debugLog("person fail : ", person.toString());
                        continue;
                    }

                    idList.add(person.getId());
                    featureList.add(person.getFeature());
                } catch (Exception e) {
                    Tools.printStackTrace(e);
                }
            }

            long[] ids = new long[idList.size()];
            byte[][] features = new byte[idList.size()][];
            for (int i = 0; i < idList.size(); i++) {
                ids[i] = idList.get(i);
                features[i] = featureList.get(i);
            }

            YTLFFace.dataBaseAdd(ids, features);
            Tools.debugLog("initSDK loadDB size=%s", ids.length);
            YTLFFaceManager.isLoadDB = true;
        }

        return YTLFFaceManager.isLoadDB;
    }

    // 人脸检测
    public void onEnterDetect(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, FaceDetectActivity.class);
                startActivity(intent);
            }
        };

        runOnFaceSdkReady(runnable);
    }

    // 人脸注册
    public void onEnterRegister(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, DBActivity.class);
                startActivity(intent);
            }
        };

        runOnFaceSdkReady(runnable);
    }

    public void test3(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Debug.doSearch(context, "/sdcard/firefly/图1.jpg");

                Tools.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Debug.doSearch(context, "/sdcard/firefly/图2.jpg");
                    }
                }, 3000);
            }
        };

        runOnFaceSdkReady(runnable);
    }

    public void test5(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Debug.getFaceAttrs(context, "/sdcard/firefly/图1.jpg");

                Tools.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Debug.getFaceAttrs(context, "/sdcard/firefly/图2.jpg");
                    }
                }, 3000);
            }
        };

        runOnFaceSdkReady(runnable);
    }

    private void requestPermission() {
       /* final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            Toast.makeText(ArcternMainActivity.this, "Please agree to the required permissions", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });*/
    }

    //初始化ImagePicker，拍照或选图
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setMultiMode(false);
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    private void initSetting() {
        //初始化ImagePicker，拍照或选图
        initImagePicker();
        Constants.recognition_overturn_rgbcamera = SPUtil.readCameraRgb();
        Constants.recognition_overturn_ircamera = SPUtil.readCameraIr();
        Constants.face_frame_mirror = SPUtil.readFaceFrameMirror();
        Constants.face_frame_reverse = SPUtil.readFaceFrameReverse();

        // firefly设备默认值
        Constants.recognition_overturn_rgbcamera = true;

        Constants.select_screen_rotate_rgbcamera = SPUtil.readScreenRotateRgbCamera();
        Constants.select_screen_rotate_ircamera = SPUtil.readScreenRotateIrCamera();

        Tools.debugLog("Signature=%s", YTLFFace.getSignature());

    }

    private void run(Runnable... runnables) {
        int index = 1;
        for (Runnable item : runnables) {
            Tools.runOnUiThread(item, index++ * 500);
        }
    }

    public void deletePerson(View view) {
        dbManager.deletePerson(5);
    }

    public void updatePerson(View view) {

        long id = 2;
        String name = "郑嘉茜";

        int i = dbManager.updatePersonById(id, name);
        Log.i("", "updatePerson: "+i);

    }
}
