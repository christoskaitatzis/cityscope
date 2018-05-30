package gr.crossroads.cityscope;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
//import com.facebook.FacebookSdk;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    public ImageView info;

    public TextView tx;
    public TextView vsm1;
    public TextView vsm2;
    public TextView vsm3;
    public TextView vsm4;
    public TextView vsm5;
    public TextView vsm6;

    public RelativeLayout r1;
    public RelativeLayout r2;
    public RelativeLayout r3;
    public RelativeLayout r4;
    public RelativeLayout r5;
    public RelativeLayout r6;
    public RelativeLayout r7;

    public RelativeLayout ontopstart;
    public RelativeLayout ontopAvailable;
    public ImageView ontopimage;


    public RelativeLayout rel5;
    public RelativeLayout rel4;
    public RelativeLayout rel1;
    //public Layout r2;

    public TextView tx2;
    static double forth;
    static double forth2;
    static double forth3;
    static double forth4;
    static boolean animationDone=false;
    static boolean sendToParseDone=false;



    static double MyLat;
    static double MyLong;
    static double lastKnownLatitude=0;
    static double lastKnownLongitude=0;

    //public static String points;
    static int points=0;
    static String cityName="";//=null;
    static String streetName="";//=null;
    static String countryCode;//=null;
    static String adminName;//=null;
    public TextView textc;
    static String currentLocation;
    public static double latitude=0;
    public static double longitude=0;
    static String localityName = "";
    static JSONObject jsonObj;
    static int cas=1;
    public int jsoncounter=0;

    public Spinner sp2;
    static String id;
    public ImageView refreshIcon;
    public ImageView refreshRotate;



    Boolean isInternetPresent = false;

    ProgressDialog pDialog;

    // Places Listview
    ListView lv;

    // ListItems data
    ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String,String>>();

    public static double searchlat=0;
    public static double searchlong=0;

    public static String searchStreetToLoad;
    public static String placeID="";
    static int intercount=0;


    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place
    public static String KEY_NAME = "name"; // name of the place
    public static String KEY_VICINITY = "vicinity"; // Place area name


        public RelativeLayout rl;
    public RelativeLayout relativetop;
    public RelativeLayout relativetopup;
    public RelativeLayout relativetopstatusshadow;
    public RelativeLayout shadow;
    public RelativeLayout upcorner;
    public RelativeLayout uprightrect;

    public RelativeLayout relativeWarn;

    public View viewt;
    public View viewsm1;
    public View viewsm2;
    public View viewsm3;
    public View viewsm4;
    public View viewsm5;
    public View viewsm6;
    public View viewbluein;
    public View viewsm1inbeneathsm;
    public View viewsmbeneathsmorange;
    public View inner_circle_center;


    public TextView txcirc1;
    public TextView txcirc2;
    public TextView txcirc3;
    public TextView txcirc4;
    public TextView txcirc5;
    public  TextView txcirc6;
    public TextView txcircdummy;
    public TextView txcirccenter;
    public TextView txcirccity;

    public JSONObject objectCenter=null;
    public JSONObject object1=null;
    public JSONObject object2;
    public JSONObject object3;
    public JSONObject object4;
    public JSONObject object5;
    public JSONObject object6;


    public JSONObject[] jsonObjects=new JSONObject[8];
    public ArrayList<String> streetNamesList=null;

    public static int fullscreenwidth;
    public static int fullscreenheight;




    final String GOOGLE_KEY = "AIzaxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    //public GoogleApiClient googleApiClient;

    public Menu menu2;

    public Spinner sppopup;

    public static float rot=45;
    public static float rot2=45;
    public static float rot_top=0;

    public static Location location2;

    //public static LocationRequest locationRequest;

    LocationManager lm;
    Status status;
    LocationSettingsStates state;

    LocationRequest locationRequest;
    GoogleApiClient googleApiClient;
    PendingResult<LocationSettingsResult> result;

    String[] fdf;
    static boolean internetConnection=false;
    static boolean dialogIsOn=false;
    static String[] streetnumber;
    public static Geocoder geocoder;

    public LocationManager mLocationManager;

    static boolean checkloc=false;
    static boolean flagParse=false;

    private void updateTextCenter(){
        try {


            searchlat =latitude;// location0.getLatitude();//40.74;//
            searchlong = longitude; //location0.getLongitude();//-73.98;//-73.98;
            //places[0]="bar";
            //new LoadPlaces().execute();



            if(isOnline()) {
                new LoadLocation().execute(String.valueOf(latitude), String.valueOf(longitude));
            }
        }
        catch (Exception e){}


    }

    private void call(){
        new LoadAddress().execute();
    }

    private void callPlaces(){
        new LoadPlaces().execute();
    }


        static boolean fetch=false;
        static boolean gotData=false;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("onActivityResult()", Integer.toString(resultCode));

        //final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode)
        {
            case 199:
                switch (resultCode)
                {
                    case Activity.RESULT_OK:
                    {
                        // All required changes were successfully made
                        Toast.makeText(MainActivity.this, "Location enabled", Toast.LENGTH_LONG).show();
                        locationProvider.configureIfNeeded(this);
                        //new LoadLocation().execute();
                        fetch=true;
                        if(isOnline())
                        {
                            //new fetchcoordinates
                        new FetchCordinates().execute();
                       // new PingTaskAndSetICOF().execute("www.google.com","80");
                        }

                        break;
                    }
                    case Activity.RESULT_CANCELED:
                    {
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(MainActivity.this, "Location not enabled", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("Connected", "Connected");


        try {
           // location2 = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
           // textc.setText(String.valueOf(location2.getLatitude()));
            locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
            locationRequest.setInterval(10 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);



            //requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

            //**************************
            builder.setAlwaysShow(true); //this is the key ingredient
            //**************************


            result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    status = result.getStatus();  //final Status
                    state = result.getLocationSettingsStates();   //final LocationSettingsStates
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can initialize location
                            // requests here.
                            ///////////////////////////////////////////////////////////////////////
                            //Toast.makeText(MainActivity.this, "Success loc, getting position", Toast.LENGTH_SHORT).show();
                            ////////////////////////////////////////////////////////////////////////
                            locationProvider.configureIfNeeded(MainActivity.this);
                            fetch=true;
                            //updateTextCenter();

                            if (isOnline()) {
                                new FetchCordinates().execute();
                                //new PingTaskAndSetICOF().execute("www.google.com","80");
                            }

                            ///////////////////////////////////////////////////////////////////////////
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(
                                        MainActivity.this, 199);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });


        }
        catch (Exception e){}

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            //Location apiLocation = null;
            //apiLocation = LocationServices.FusedLocationApi.getLastLocation(
                   // googleApiClient);


            if (LocationServices.FusedLocationApi.getLastLocation(googleApiClient).getLatitude() != 0)
            {
                if (LocationServices.FusedLocationApi.getLastLocation(googleApiClient).getLongitude() != 0) {
                    latitude = LocationServices.FusedLocationApi.getLastLocation(googleApiClient).getLatitude();
                    longitude = LocationServices.FusedLocationApi.getLastLocation(googleApiClient).getLongitude();

                    //Toast.makeText(MainActivity.this,"loc from apiClient",Toast.LENGTH_LONG).show();
                    updateTextCenter();
                }

            }


        }
        catch (Exception ee){}


////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    protected void onStop() {

        googleApiClient.disconnect();
        super.onStop();
    }



    public void sendToParse(){

        if(!sendToParseDone) {
           // runQuery();
        }

        sendToParseDone=true;
    }

    public static int handlerDelay = 1*60*1000;





    @Override
    public void onConnectionSuspended(int i) {
        Log.d("Connection suspened", "Connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("Connection suspened", "Connection suspended");
    }




    public void startLocationUpdate() {

    }

    public void showDialog(){

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);


        builderSingle.setTitle("More places around "+streetName+":");
        ///////////////////////////////

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                //text.setTypeface(typeFaceCircles);
                return view;
            }
        };
//final CustomAdapter arrayAdapter = new CustomAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, fdf, "OpenSans.ttf");

        if (placesnames.size()>0) {
            for (int i = 0; i < placesnames.size(); i++) {
                arrayAdapter.add(placesnames.get(i));

            }


        }



        builderSingle.setNegativeButton(
                "back",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        //listdial.setAdapter(arrayAdapter);

/////////////////////////////
        builderSingle.setAdapter(
                arrayAdapter,

                null );
//////////////////////////////////

        builderSingle.show();

    }

    public void showDialogStreet(String shop_,String street_){
        String place="";
        place=shop_;
        String streetname_="";
        streetname_=street_;

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);

        builderSingle.setTitle(place);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                return view;
            }
        };


        arrayAdapter.add(streetname_);

        builderSingle.setNegativeButton(
                "back",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

/////////////////////////////
        builderSingle.setAdapter(
                arrayAdapter,

                null );
//////////////////////////////////
        builderSingle.show();
    }



    private String[] drawerListViewItems;
    private ListView drawerListView;

    public Handler h = new Handler();
    public Handler repeatH = new Handler();
    public LocalBroadcastManager broadcaster;
    private Intent intent;
    private Intent intentUI;
    public LocationProvider locationProvider;

    /////////////////////////////////////////////////////////////////////////
    ImageView carview;
    ImageView carview2;
    public Timer timer;
    public static int viewx=1500;
    public static int viewx2=-1000;
//    private void timerMethod(){
//        this.runOnUiThread(generate);
//
//    }

    public static int delayrand=100;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
private PendingIntent mPingAlarmPendIntent;
    private static final String PING_ALARM = "gr.crossroads.PING_ALARM";
    private Intent mPingAlarmIntent = new Intent(PING_ALARM);
    private BroadcastReceiver mPingAlarmReceiver;// = new BroadcastReceiver();
    private class PingAlarmReceiver extends BroadcastReceiver {
        public void onReceive(Context ctx, Intent i) {
            // Do your work here
            sendToParse();
        }
    }




    public void graphCall(){

        if(isOnline()) {

            GraphRequest request = GraphRequest.newGraphPathRequest(
                    null,
                    "/search",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            // Insert your code here

                            Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();

                            try {

                                //GraphObject go  = response.get
                                JSONObject jso = response.getJSONObject();
                                //Toast.makeText(MainActivity.this,String.valueOf(jso.length()),Toast.LENGTH_LONG).show();
                                JSONArray arr = jso.getJSONArray("data");

                                for (int i = 0; i < (arr.length()); i++) {
                                    JSONObject json_obj = arr.getJSONObject(i);
                                    String name   = json_obj.getString("name");

                                    String categoryStr =  json_obj.getString("category");


                                    JSONObject locget = json_obj.getJSONObject("location");
                                    String placeStreet   = locget.getString("street");

                                    if(categoryStr.contains("bar")||categoryStr.contains("Bar")||categoryStr.contains("cafe")||categoryStr.contains("Cafe")||categoryStr.contains("restaurant")||categoryStr.contains("Restaurant")||categoryStr.contains("food"))
                                    {
                                        placesnames.add(name);
                                    }


                                    //Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();

                                }
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }

                            showDialog();

                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("q", "cafe");
            parameters.putString("type", "place");
            parameters.putString("center",latitude+","+longitude);
            //parameters.putString("fields","location,hours");
            //parameters.putString("fields","name,category,location");
            parameters.putString("limit", "20");
            parameters.putString("access_token", "xxxxxxxxxxxxxxxx|Xxxxxxxxxxxxxxxxxxxxxxxx");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.drawerbase);

        //FacebookSdk.sdkInitialize(getApplicationContext());
       // AppEventsLogger.activateApp(this);
        ///////////////////////////////////////////////////////
        //intent = new Intent(this, CityscopeServ.class);
        intentUI = new Intent(this, CityscopeUI.class);
        //printHashKey();


        //}
        startService(intentUI);
        //else
       // {
            //startService(intent);
       //     new CallTimer().execute();
       // }

        //System.setProperty("http.agent", "");

        /////////////////////////////////////////////////////////////////////////////////////////
       locationProvider = LocationProvider.getInstance();

        //try {
            locationProvider.configureIfNeeded(this);

        carview=(ImageView) findViewById(R.id.carview);
        carview2=(ImageView) findViewById(R.id.carview2);

//////////////////////////////////////////////////////////////////////////

//
//        LocalBroadcastManager.getInstance(this).registerReceiver(
//                new BroadcastReceiver() {
//                    @Override
//                    public void onReceive(Context context, Intent intent) {
//                        double latitude = intent.getDoubleExtra(LocationBroadcastService.EXTRA_LATITUDE, 0);
//                        double longitude = intent.getDoubleExtra(LocationBroadcastService.EXTRA_LONGITUDE, 0);
//                        textView.setText("Lat: " + latitude + ", Lng: " + longitude);
//                    }
//                }, new IntentFilter(LocationBroadcastService.ACTION_LOCATION_BROADCAST)
//        );


        /////////////////////////////////////////////////////////////////////////////////////////////
//        final Thread.UncaughtExceptionHandler oldHandler =
//                Thread.getDefaultUncaughtExceptionHandler();
//
//
//        Thread.setDefaultUncaughtExceptionHandler(
//                new Thread.UncaughtExceptionHandler() {
//                    @Override
//                    public void uncaughtException(
//                            Thread paramThread,
//                            Throwable paramThrowable
//                    ) {
//                        //Do your own error handling here
//
//                        if (oldHandler != null)
//                            oldHandler.uncaughtException(
//                                    paramThread,
//                                    paramThrowable
//                            ); //Delegates to Android's error handling
//                        else
//                            System.exit(2); //Prevents the service/app from freezing
//                    }
//                });
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        /////////////////////////////////////////////////////////////////////////////////////////////
        if(isOnline()) {
            try {
                PingTask ping = new PingTask();
                ping.execute("www.google.com", "80");

            } catch (Exception e) {
            }
        }


        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //textcenter
        textc = (TextView) findViewById(R.id.v0);

//
//                    splash.startAnimation(fadeOut);
//
//
//                //ontopstart.setVisibility(View.INVISIBLE);
//                ///////////////////////////////////////////////////////////////////////////////////
//                // Toast.makeText(MainActivity.this,"location",Toast.LENGTH_SHORT).show();
//                /////////////////////////////////////////
//
//            }
//
//
//        }.start();

        info = (ImageView) findViewById(R.id.infoImage);
        info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentCredits = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intentCredits);
            }
        });



        new CountDownTimer(3000, 3000) {
            public void onTick(long millisUntilFinished) {
                // You can monitor the progress here as well by changing the onTick() time
            }

            public void onFinish() {
                // stop task if not in progress
                ontopstart.setVisibility(View.GONE);
                //ontopstart.setVisibility(View.INVISIBLE);
                ///////////////////////////////////////////////////////////////////////////////////
                // Toast.makeText(MainActivity.this,"location",Toast.LENGTH_SHORT).show();
                /////////////////////////////////////////
            }


        }.start();

        new CountDownTimer(6000, 6000) {
            public void onTick(long millisUntilFinished) {
                // You can monitor the progress here as well by changing the onTick() time
            }

            public void onFinish() {
                // stop task if not in progress

                if(cityName.contains("Thessaloniki")) {
                    /////////////////////////////////////
                    final AlphaAnimation fadeOut = new AlphaAnimation(1.0F, 0.0F);
                    fadeOut.setDuration(700);
                    final View splash = findViewById(R.id.relativeWarnDown);
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            ontopAvailable.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    splash.startAnimation(fadeOut);
                    //ontopstart.setVisibility(View.INVISIBLE);
                    ///////////////////////////////////////////////////////////////////////////////////
                    // Toast.makeText(MainActivity.this,"location",Toast.LENGTH_SHORT).show();
                    /////////////////////////////////////////
                }

            }


        }.start();

        refreshIcon=(ImageView) findViewById(R.id.refreshIcon);
        refreshRotate=(ImageView) findViewById(R.id.refreshRotate);

        refreshRotate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (isOnline())
                {
                    new FetchCordinates().execute();
                }
//                else {
//
//                    new PingTask().execute("www.google.com", "80");
//                }

                AnimationSet animSet = new AnimationSet(true);
                animSet.setInterpolator(new DecelerateInterpolator());
                animSet.setFillAfter(true);
                animSet.setFillEnabled(true);

                final RotateAnimation animRotate = new RotateAnimation(0.0f, 360.0f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                animRotate.setDuration(1000);
                animRotate.setFillAfter(true);
                animSet.addAnimation(animRotate);

                refreshRotate.startAnimation(animSet);


            }
        });



        setStatusBarTranslucent(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//        }
        //relativetopstatusshadow = (RelativeLayout) findViewById(R.id.toprelativeupshadow);
        //relativetopstatusshadow.setY(getStatusBarHeight());

        relativeWarn=(RelativeLayout) findViewById(R.id.relativeWarn);

        ontopstart = (RelativeLayout) findViewById(R.id.ontop);
        ontopimage = (ImageView) findViewById(R.id.imageontop);
        ontopAvailable = (RelativeLayout) findViewById(R.id.relativeWarnDown);

        relativetop = (RelativeLayout) findViewById(R.id.toprelative);
        relativetop.setY(getStatusBarHeight() + 12);
        shadow = (RelativeLayout) findViewById(R.id.shadow);
        shadow.setY(getStatusBarHeight() + 12);
        upcorner = (RelativeLayout) findViewById(R.id.relativeuprightcorner);
        upcorner.setY(getStatusBarHeight() + 12);
        uprightrect = (RelativeLayout) findViewById(R.id.relativeupright);
        uprightrect.setY(getStatusBarHeight() + 12);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        params.height = getStatusBarHeight();

        relativetopup = (RelativeLayout) findViewById(R.id.toprelativeup);
        //params= relativetopup.getLayoutParams();
        relativetopup.setLayoutParams(params);


        boolean network_enabled = false;

//        try {
//            //network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch (Exception e) {
//        }


//
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                    .addApi(LocationServices.API)

                   // .addApi(Places.GEO_DATA_API)
                    //.addApi(Places.PLACE_DETECTION_API)
                    //.enableAutoManage(this, this)

                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            //googleApiClient.connect();
        }



/////////////////////////////////////////

        // get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);



        TextView myTextView = (TextView) findViewById(R.id.cityscope);
        final Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Variane.ttf");
        final Typeface typeFaceCircles = Typeface.createFromAsset(getAssets(), "fonts/OpenSans.ttf");

        txcirccenter = (TextView) findViewById(R.id.v0);
        txcirccity = (TextView) findViewById(R.id.v2);
        txcirccenter.setTypeface(typeFaceCircles);

        myTextView.setTypeface(typeFace);
        myTextView.setTextSize(32);

        TextView frontsidedown = (TextView) findViewById(R.id.cityscopedown);
        frontsidedown.setTypeface(typeFace);
        frontsidedown.setTextSize(26);




        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            final int theheight = metrics.heightPixels;
            final int thewidth = metrics.widthPixels;
            fullscreenheight = theheight;
            fullscreenwidth = thewidth;
        }




//        rl = (RelativeLayout) findViewById(R.id.inc1);
        viewt = (View) findViewById(R.id.inc1).findViewById(R.id.viewcent);
        viewt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                // new LoadAddress().execute();
                //call();
                dialogIsOn = true;
                placesnames.clear();


                    graphCall();






            }
        });



        vsm1=(TextView) findViewById(R.id.vsm1);
        vsm2=(TextView) findViewById(R.id.vsm2);
        vsm3=(TextView) findViewById(R.id.vsm3);
        vsm4=(TextView) findViewById(R.id.vsm4);
        vsm5=(TextView) findViewById(R.id.vsm5);
        vsm6=(TextView) findViewById(R.id.vsm6);

        txcirc1 = (TextView) findViewById(R.id.vsm1);
        txcirc1.setTypeface(typeFaceCircles);
        viewsm1 = (View) findViewById(R.id.inc2).findViewById(R.id.vc1);


        viewsm1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                dialogIsOn = true;

                if(getNearbyPlaces().get(0)!=null&&getNearbyPlacesStreet().get(0)!=null)
                {showDialogStreet(getNearbyPlaces().get(0), getNearbyPlacesStreet().get(0));}

            }
        });

        txcirc2 = (TextView) findViewById(R.id.vsm2);
        txcirc2.setTypeface(typeFaceCircles);
        viewsm2 = (View) findViewById(R.id.inc3).findViewById(R.id.vc2);
        viewsm2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogIsOn = true;
                if(getNearbyPlaces().get(1)!=null&&getNearbyPlacesStreet().get(1)!=null) {
                    showDialogStreet(getNearbyPlaces().get(1), getNearbyPlacesStreet().get(1));
                }

            }
        });

        txcirc3 = (TextView) findViewById(R.id.vsm3);
        txcirc3.setTypeface(typeFaceCircles);
        viewsm3 = (View) findViewById(R.id.inc4).findViewById(R.id.vc3);
        viewsm3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogIsOn = true;
                if(getNearbyPlaces().get(2)!=null&&getNearbyPlacesStreet().get(2)!=null) {
                    showDialogStreet(getNearbyPlaces().get(2), getNearbyPlacesStreet().get(2));
                }

                // your code here
            }
        });

        txcirc4 = (TextView) findViewById(R.id.vsm4);
        txcirc4.setTypeface(typeFaceCircles);
        viewsm4 = (View) findViewById(R.id.inc5).findViewById(R.id.vc4);
        viewsm4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogIsOn = true;
                if(getNearbyPlaces().get(3)!=null&&getNearbyPlacesStreet().get(3)!=null) {
                    showDialogStreet(getNearbyPlaces().get(3), getNearbyPlacesStreet().get(3));
                }

            }
        });

        txcirc5 = (TextView) findViewById(R.id.vsm5);
        txcirc5.setTypeface(typeFaceCircles);
        viewsm5 = (View) findViewById(R.id.inc6).findViewById(R.id.vc5);
        viewsm5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogIsOn = true;

                if(getNearbyPlaces().get(4)!=null&&getNearbyPlacesStreet().get(4)!=null) {
                    showDialogStreet(getNearbyPlaces().get(4), getNearbyPlacesStreet().get(4));
                }
//
            }
        });

        txcirc6 = (TextView) findViewById(R.id.vsm6);
        txcirc6.setTypeface(typeFaceCircles);
        viewsm6 = (View) findViewById(R.id.inc7).findViewById(R.id.vc6);
        viewsm6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogIsOn = true;
                if(getNearbyPlaces().get(5)!=null&&getNearbyPlacesStreet().get(5)!=null) {
                    showDialogStreet(getNearbyPlaces().get(5), getNearbyPlacesStreet().get(5));
                }
//

            }
        });


        txcircdummy = (TextView) findViewById(R.id.dummy);
        txcircdummy.setTypeface(typeFace);



        inner_circle_center = (View) findViewById(R.id.inc1).findViewById(R.id.viewcent3);

        r1 = (RelativeLayout) findViewById(R.id.inc1);//center
        r2 = (RelativeLayout) findViewById(R.id.inc2);//green
        r3 = (RelativeLayout) findViewById(R.id.inc3);//blue
        r4 = (RelativeLayout) findViewById(R.id.inc4);//orange
        r5 = (RelativeLayout) findViewById(R.id.inc5);//purple
        r6 = (RelativeLayout) findViewById(R.id.inc6);//red
        r7 = (RelativeLayout) findViewById(R.id.inc7);//yellow



        RelativeLayout.LayoutParams paramsr1 = new RelativeLayout.LayoutParams((int)(fullscreenheight*0.2), (int)(fullscreenheight*0.2));
       paramsr1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        paramsr1.height =  (int)(fullscreenheight*0.3);
        paramsr1.width=  (int)(fullscreenheight*0.3);
       r1.getLayoutParams();
        r1.setLayoutParams(paramsr1);

        RelativeLayout.LayoutParams paramsr2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        paramsr2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        paramsr2.height =  (int)(fullscreenheight*0.155);
        paramsr2.width=  (int)(fullscreenheight*0.155);
        r2.getLayoutParams();
        r2.setLayoutParams(paramsr2);//green

        RelativeLayout.LayoutParams paramsr3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        paramsr3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        paramsr3.height =  (int)(fullscreenheight*0.155);
        paramsr3.width=  (int)(fullscreenheight*0.155);
        r3.getLayoutParams();
        r3.setLayoutParams(paramsr3);//blue

        RelativeLayout.LayoutParams paramsr4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        paramsr4.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        paramsr4.height =  (int)(fullscreenheight*0.155);
        paramsr4.width=  (int)(fullscreenheight*0.155);
        r4.getLayoutParams();
        r4.setLayoutParams(paramsr4);//orange

        RelativeLayout.LayoutParams paramsr5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        paramsr5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        paramsr5.height =  (int)(fullscreenheight*0.155);
        paramsr5.width=  (int)(fullscreenheight*0.155);
        r5.getLayoutParams();
        r5.setLayoutParams(paramsr5);

        RelativeLayout.LayoutParams paramsr6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        paramsr6.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        paramsr6.height =  (int)(fullscreenheight*0.17);
        paramsr6.width=  (int)(fullscreenheight*0.17);
        r6.getLayoutParams();
        r6.setLayoutParams(paramsr6);

        RelativeLayout.LayoutParams paramsr7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        paramsr7.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        paramsr7.height =  (int)(fullscreenheight*0.17);
        paramsr7.width=  (int)(fullscreenheight*0.17);
        r7.getLayoutParams();
        r7.setLayoutParams(paramsr7);

        ///////////////////////

        setDimensions(inner_circle_center,(int)(fullscreenheight*0.18),(int)(fullscreenheight*0.18));



        rel5 = (RelativeLayout) findViewById(R.id.rel5);
        // rel5.setVisibility(View.GONE);
        rel4 = (RelativeLayout) findViewById(R.id.rel4);
        //rel4.setVisibility(View.GONE);
        // rel4 = (RelativeLayout) findViewById(R.id.rel4);
        //rel1.setVisibility(View.GONE);



        final float sx;
        final float sy;
        sx = r1.getX();
        sy = r1.getY();


        //r2.setX(sx+130);
        //r2.setY(sy + 140);

        final float sx2;
        final float sy2;

        //
        final int delay = 50; //milliseconds

        viewbluein = (View) findViewById(R.id.inc3).findViewById(R.id.vcblue);

        forth = 1;
        forth2 = 30;
        forth3 = 65;
        forth4 = 90;


        sx2 = r2.getX();
        sy2 = r2.getY();





        sp2 =(Spinner) findViewById(R.id.list2);
        placesnames.add("entry_add");
        final ArrayAdapter<String> adap =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,placesnames);

        sp2.setAdapter(adap);


    }

    private void startLocationTracker() {
        // Configure the LocationTracker's broadcast receiver to run every 5 minutes.
        //Intent intent = new Intent(this, LocationTracker.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(),
                LocationProvider.FIVE_MINUTES, pendingIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        googleApiClient.connect();

            startService(intentUI);//enabled
       // }

        //registerReceiver(broadcastReceiver, new IntentFilter(CityscopeServ.BROADCAST_ACTION));
        registerReceiver(broadcastReceiverUI, new IntentFilter(CityscopeUI.BROADCAST_ACTION_UI));
        //locationProvider.configureIfNeeded(this);
        if(isOnline()) {
            new FetchCordinates().execute();
        }


    }

    private void setDimensions(View view, int width, int height){
        android.view.ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //unregister your receiver
        this.unregisterReceiver(this.broadcastReceiverUI);
        stopService(intentUI);
    }

    public void onRestart() {
        super.onRestart();

        if(isOnline()) {
            new FetchCordinates().execute();
        }

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //updateUI(intent);
            //sendToParse();
        }
    };

    private BroadcastReceiver broadcastReceiverUI = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);

        }
    };



    private void updateUI(Intent intent) {



        boolean net_enabled = false;

        try {
            net_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            //Log.e(TAG,"Exception network_enabled");
        }


        if (rot == 361) {
            rot = 1;
        }
        rot = rot + (float) 0.1;
        rot_top = rot_top+(float)5;

        viewt.setRotation(rot);
        ///////////////////////////////////
        if (rot_top == 360) {
            rot_top = 0;
        }
        refreshIcon.setRotation(rot_top);

        if (rot2 == 361) {
            rot2 = 1;
        }
        rot2 = rot2 - (float) 0.4;




        ////////////////////////////////////////////////////////////////////////////////////////////////
        r5.setX((int)(fullscreenwidth*0.80 -(r5.getWidth()*0.76/2)+ (fullscreenwidth * (mouse[(int)forth][1]) * 0.1)));//03
//purple
        r5.setY((int)(fullscreenheight*0.59-(r5.getHeight()*0.76/2) + (fullscreenheight * (mouse[(int)forth][0]) * 0.1)));//04

                                        //0.38
        r6.setX((int)(fullscreenwidth*0.5- ((r6.getWidth()*0.76)/2) -  (fullscreenwidth * (mouse[(int)forth2][0]) * 0.1)));//03
//red
        r6.setY((int)(fullscreenheight*0.72-(r6.getHeight()*0.76/2) +  (fullscreenheight * (mouse[(int)forth2][1]) * 0.1)));//04


        r3.setX((int)((fullscreenwidth*0.16)-(r3.getWidth()*0.76/2) -  (fullscreenwidth * (mouse[(int)forth2][0]) * 0.1)));//04
//blue                                  0.28
        r3.setY((int)(fullscreenheight*0.33-(r3.getHeight()*0.76/2) +  (fullscreenheight * (mouse[(int)forth2][1]) * 0.1)));//

                                        //0.37
        r7.setX((int)(fullscreenwidth*0.5-(r7.getWidth()*0.76/2) -  (fullscreenwidth * (mouse[(int)forth3][0]) * 0.1)));//04
//yellow
        r7.setY((int)(fullscreenheight*0.24 -(r7.getHeight()*0.76/2)-  (fullscreenheight * (mouse[(int)forth3][1]) * 0.1)));//04


        r4.setX((int)(fullscreenwidth*0.80-(r4.getWidth()*0.76/2) + (fullscreenwidth * (mouse[(int)forth4][1]) * 0.1)));//04
//orange                                0.31
        r4.setY((int)(fullscreenheight*0.31-(r4.getHeight()*0.76/2) +  (fullscreenheight * (mouse[(int)forth4][0]) * 0.1)));//03


        r2.setX((int) (fullscreenwidth * 0.16 -(r2.getWidth()*0.76/2)- (fullscreenwidth * (mouse[(int) forth4][0]) * 0.1)));//04
//green
        r2.setY((int) (fullscreenheight * 0.61-(r2.getHeight()*0.76/2) + (fullscreenheight * (mouse[(int) forth4][1]) * 0.1)));//03



        forth = forth + 0.4;
        forth2 = forth2 + 0.4;
        forth3 = forth3 + 0.4;
        forth4 = forth3 + 0.4;

        if (forth > 186) {
            forth = 1;
        }
        if (forth2 > 186) {
            forth2 = 1;
        }
        if (forth3 >186) {
            forth3 = 1;
        }
        if (forth4 > 186) {
            forth3 = 1;
        }


    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }





    private double mouse2[][] =
            {


                    {	0.547222222	,	0.213541667	}	,
                    {	0.544444444	,	0.2125	}	,
                    {	0.540740741	,	0.2125	}	,
                    {	0.537037037	,	0.211979167	}	,
                    {	0.535185185	,	0.211979167	}	,
                    {	0.531481481	,	0.211979167	}	,
                    {	0.526851852	,	0.211979167	}	,
                    {	0.525	    ,	0.2125	    }	,
                    {	0.522222222	,	0.213541667	}	,
                    {	0.521296296	,	0.215104167	}	,
                    {	0.521296296	,	0.215625	}	,
                    {	0.521296296	,	0.216666667	}	,
                    {	0.521296296	,	    0.21875	}	,
                    {	0.522222222	,	0.21875	    }   ,
                    {	0.526851852	,	0.219791667	}	,
                    {	0.531481481	,	0.219791667	}	,
                    {	0.532407407	,	0.219791667	}	,
                    {	0.534259259	,	0.220833333	}	,
                    {	0.535185185	,	0.220833333	}	,
                    {	0.535185185	,	0.220833333	}	,
                    {	0.537962963	,	0.220833333	}	,
                    {	0.542592593	,	0.219791667	}	,
                    {	0.54537037	,	0.219791667	}	,
                    {	0.547222222	,	0.218229167	}	,
                    {	0.552777778	,	0.216666667	}	,
                    {	0.553703704	,	0.215104167	}	,
                    {	0.553703704	,	0.2140625	}	,
                    {	0.553703704	,	0.213541667	}	,
                    {	0.553703704	,	0.211979167	}	,
                    {	0.553703704	,	0.2109375	}	,
                    {	0.552777778	,	0.2109375	}	,
                    {	0.550925926	,	0.209895833	}	,
                    {	0.547222222	,	0.209895833	}	,
                    {	0.54537037	,	0.2109375	}	,
                    {	0.544444444	,	0.2109375	}	,
                    {	0.542592593	,	0.211979167	}	,
                    {	0.540740741	,	0.2125	}	    ,
                    {	0.539814815	,	0.213541667	}	,
                    {	0.537037037	,	0.215104167	}	,
                    {	0.537037037	,	0.215104167	}	,
                    {	0.534259259	,	0.215625	}	,
                    {	0.532407407	,	0.216666667	}	,
                    {	0.52962963	,	0.2171875	}	,
                    {	0.528703704	,	0.218229167	}	,
                    {	0.525	,	0.218229167	}	    ,
                    {	0.521296296	,	0.21875	}	    ,
                    {	0.518518519	,	0.21875	}	    ,
                    {	0.516666667	,	0.2171875	}	,
                    {	0.516666667	,	0.216666667	}	,
                    {	0.516666667	,	0.215104167	}	,
                    {	0.519444444	,	0.213541667	}	,
                    {	0.519444444	,	0.2109375	}	,
                    {	0.522222222	,	0.209895833	}	,
                    {	0.525	,	0.209375	}	    ,
                    {	0.528703704	,	0.2078125	}	,
                    {	0.52962963	,	0.2078125	}	,
                    {	0.531481481	,	0.206770833	}	,
                    {	0.531481481	,	0.206770833	}	,
                    {	0.534259259	,	0.206770833	}	,
                    {	0.535185185	,	0.206770833	}	,
                    {	0.537962963	,	0.206770833	}	,
                    {	0.542592593	,	0.20625	}	    ,
                    {	0.547222222	,	0.20625	}	    ,
                    {	0.55	,	0.205208333	}	    ,
                    {	0.553703704	,	0.20625	}	    ,
                    {	0.556481481	,	0.2078125	}	,
                    {	0.556481481	,	0.209375	}	,
                    {	0.556481481	,	0.209895833	}	,
                    {	0.552777778	,	0.2109375	}	,
                    {	0.548148148	,	0.213541667	}	,
                    {	0.544444444	,	0.2140625	}	,
                    {	0.540740741	,	0.2140625	}	,
                    {	0.537962963	,	0.2140625	}	,
                    {	0.535185185	,	0.213541667	}	,
                    {	0.531481481	,	0.213541667	}	,
                    {	0.528703704	,	0.2125	}	    ,
                    {	0.525	,	        0.2125	}   ,
                    {	0.521296296	,	0.2125	}	    ,
                    {	0.518518519	,	0.213541667	}	,
                    {	0.518518519	,	0.213541667	}	,
                    {	0.519444444	,	0.2140625	}	,
                    {	0.519444444	,	0.215625	}	,
                    {	0.522222222	,	0.2171875	}	,
                    {	0.526851852	,	0.2171875	}	,
                    {	0.52962963	,	0.218229167	}	,
                    {	0.532407407	,	0.215625	}	,
                    {	0.534259259	,	0.215625	}	,
                    {	0.535185185	,	0.215625	}	,
                    {	0.537037037	,	0.2140625	}	,
                    {	0.537962963	,	0.213541667	}	,
                    {	0.539814815	,	0.211979167	}	,
                    {	0.542592593	,	0.2109375	}	,
                    {	0.544444444	,	0.209895833	}	,
                    {	0.547222222	,	0.209895833	}	,
                    {	0.547222222	,	0.211979167	}	,
                    {	0.547222222	,	0.211979167	}	,



            };

    private double mouse[][] =
            {


                    {	0.3536	,	0.1369	}	,
                    {	0.3521	,	0.1354	}	,
                    {	0.3496	,	0.1339	}	,
                    {	0.3471	,	0.1324	}	,
                    {	0.3446	,	0.1314	}	,
                    {	0.3401	,	0.1304	}	,
                    {	0.3351	,	0.1299	}	,
                    {	0.3326	,	0.1299	}	,
                    {	0.3281	,	0.1304	}	,
                    {	0.3221	,	0.1329	}	,
                    {	0.3191	,	0.1359	}	,
                    {	0.3176	,	0.1389	}	,
                    {	0.3176	,	0.1469	}	,
                    {	0.3196	,	0.1544	}	,
                    {	0.3221	,	0.1569	}	,
                    {	0.3266	,	0.1594	}	,
                    {	0.3311	,	0.1609	}	,
                    {	0.3361	,	0.1609	}	,
                    {	0.3416	,	0.1604	}	,
                    {	0.3461	,	0.1589	}	,
                    {	0.3516	,	0.1559	}	,
                    {	0.3566	,	0.1534	}	,
                    {	0.3606	,	0.1519	}	,
                    {	0.3651	,	0.1489	}	,
                    {	0.3706	,	0.1454	}	,
                    {	0.3746	,	0.1434	}	,
                    {	0.3776	,	0.1419	}	,
                    {	0.3826	,	0.1399	}	,
                    {	0.3871	,	0.1379	}	,
                    {	0.3906	,	0.1364	}	,
                    {	0.3966	,	0.1354	}	,
                    {	0.4041	,	0.1359	}	,
                    {	0.4106	,	0.1399	}	,
                    {	0.4156	,	0.1449	}	,
                    {	0.4186	,	0.1494	}	,
                    {	0.4196	,	0.1539	}	,
                    {	0.4181	,	0.1579	}	,
                    {	0.4146	,	0.1619	}	,
                    {	0.4106	,	0.1649	}	,
                    {	0.4061	,	0.1659	}	,
                    {	0.4001	,	0.1659	}	,
                    {	0.3946	,	0.1654	}	,
                    {	0.3916	,	0.1634	}	,
                    {	0.3891	,	0.1599	}	,
                    {	0.3866	,	0.1559	}	,
                    {	0.3846	,	0.1529	}	,
                    {	0.3831	,	0.1504	}	,
                    {	0.3811	,	0.1449	}	,
                    {	0.3791	,	0.1399	}	,
                    {	0.3781	,	0.1369	}	,
                    {	0.3766	,	0.1314	}	,
                    {	0.3751	,	0.1229	}	,
                    {	0.3746	,	0.1159	}	,
                    {	0.3746	,	0.1124	}	,
                    {	0.3726	,	0.1084	}	,
                    {	0.3686	,	0.1034	}	,
                    {	0.3641	,	0.0999	}	,
                    {	0.3581	,	0.0989	}	,
                    {	0.3526	,	0.1009	}	,
                    {	0.3496	,	0.1049	}	,
                    {	0.3486	,	0.1099	}	,
                    {	0.3486	,	0.1154	}	,
                    {	0.3511	,	0.1219	}	,
                    {	0.3576	,	0.1304	}	,
                    {	0.3636	,	0.1374	}	,
                    {	0.3676	,	0.1404	}	,
                    {	0.3746	,	0.1429	}	,
                    {	0.3841	,	0.1469	}	,
                    {	0.3921	,	0.1499	}	,
                    {	0.3991	,	0.1534	}	,
                    {	0.4051	,	0.1599	}	,
                    {	0.4076	,	0.1669	}	,
                    {	0.4071	,	0.1754	}	,
                    {	0.4036	,	0.1829	}	,
                    {	0.3976	,	0.1859	}	,
                    {	0.3911	,	0.1869	}	,
                    {	0.3846	,	0.1869	}	,
                    {	0.3796	,	0.1859	}	,
                    {	0.3761	,	0.1834	}	,
                    {	0.3731	,	0.1799	}	,
                    {	0.3711	,	0.1749	}	,
                    {	0.3701	,	0.1704	}	,
                    {	0.3696	,	0.1674	}	,
                    {	0.3696	,	0.1634	}	,
                    {	0.3701	,	0.1584	}	,
                    {	0.3706	,	0.1544	}	,
                    {	0.3701	,	0.1519	}	,
                    {	0.3686	,	0.1494	}	,
                    {	0.3671	,	0.1459	}	,
                    {	0.3661	,	0.1424	}	,
                    {	0.3641	,	0.1404	}	,
                    {	0.3616	,	0.1394	}	,
                    {	0.3596	,	0.1389	}	,
                    {	0.3576	,	0.1379	}	,
                    {	0.3556	,	0.1369	}	,
                    {	0.3541	,	0.1364	}	,
                    {	0.3521	,	0.1354	}	,
                    {	0.3496	,	0.1339	}	,
                    {	0.3471	,	0.1324	}	,
                    {	0.3446	,	0.1314	}	,
                    {	0.3401	,	0.1304	}	,
                    {	0.3351	,	0.1299	}	,
                    {	0.3326	,	0.1299	}	,
                    {	0.3281	,	0.1304	}	,
                    {	0.3221	,	0.1329	}	,
                    {	0.3191	,	0.1359	}	,
                    {	0.3176	,	0.1389	}	,
                    {	0.3176	,	0.1469	}	,
                    {	0.3196	,	0.1544	}	,
                    {	0.3221	,	0.1569	}	,
                    {	0.3266	,	0.1594	}	,
                    {	0.3311	,	0.1609	}	,
                    {	0.3361	,	0.1609	}	,
                    {	0.3416	,	0.1604	}	,
                    {	0.3461	,	0.1589	}	,
                    {	0.3516	,	0.1559	}	,
                    {	0.3566	,	0.1534	}	,
                    {	0.3606	,	0.1519	}	,
                    {	0.3651	,	0.1489	}	,
                    {	0.3706	,	0.1454	}	,
                    {	0.3746	,	0.1434	}	,
                    {	0.3776	,	0.1419	}	,
                    {	0.3826	,	0.1399	}	,
                    {	0.3871	,	0.1379	}	,
                    {	0.3906	,	0.1364	}	,
                    {	0.3966	,	0.1354	}	,
                    {	0.4041	,	0.1359	}	,
                    {	0.4106	,	0.1399	}	,
                    {	0.4156	,	0.1449	}	,
                    {	0.4186	,	0.1494	}	,
                    {	0.4196	,	0.1539	}	,
                    {	0.4181	,	0.1579	}	,
                    {	0.4146	,	0.1619	}	,
                    {	0.4106	,	0.1649	}	,
                    {	0.4061	,	0.1659	}	,
                    {	0.4001	,	0.1659	}	,
                    {	0.3946	,	0.1654	}	,
                    {	0.3916	,	0.1634	}	,
                    {	0.3891	,	0.1599	}	,
                    {	0.3866	,	0.1559	}	,
                    {	0.3846	,	0.1529	}	,
                    {	0.3831	,	0.1504	}	,
                    {	0.3811	,	0.1449	}	,
                    {	0.3791	,	0.1399	}	,
                    {	0.3781	,	0.1369	}	,
                    {	0.3766	,	0.1314	}	,
                    {	0.3751	,	0.1229	}	,
                    {	0.3746	,	0.1159	}	,
                    {	0.3746	,	0.1124	}	,
                    {	0.3726	,	0.1084	}	,
                    {	0.3686	,	0.1034	}	,
                    {	0.3641	,	0.0999	}	,
                    {	0.3581	,	0.0989	}	,
                    {	0.3526	,	0.1009	}	,
                    {	0.3496	,	0.1049	}	,
                    {	0.3486	,	0.1099	}	,
                    {	0.3486	,	0.1154	}	,
                    {	0.3511	,	0.1219	}	,
                    {	0.3576	,	0.1304	}	,
                    {	0.3636	,	0.1374	}	,
                    {	0.3676	,	0.1404	}	,
                    {	0.3746	,	0.1429	}	,
                    {	0.3841	,	0.1469	}	,
                    {	0.3921	,	0.1499	}	,
                    {	0.3991	,	0.1534	}	,
                    {	0.4051	,	0.1599	}	,
                    {	0.4076	,	0.1669	}	,
                    {	0.4071	,	0.1754	}	,
                    {	0.4036	,	0.1829	}	,
                    {	0.3976	,	0.1859	}	,
                    {	0.3911	,	0.1869	}	,
                    {	0.3846	,	0.1869	}	,
                    {	0.3796	,	0.1859	}	,
                    {	0.3761	,	0.1834	}	,
                    {	0.3731	,	0.1799	}	,
                    {	0.3711	,	0.1749	}	,
                    {	0.3701	,	0.1704	}	,
                    {	0.3696	,	0.1674	}	,
                    {	0.3696	,	0.1634	}	,
                    {	0.3701	,	0.1584	}	,
                    {	0.3706	,	0.1544	}	,
                    {	0.3701	,	0.1519	}	,
                    {	0.3686	,	0.1494	}	,
                    {	0.3671	,	0.1459	}	,
                    {	0.3661	,	0.1424	}	,
                    {	0.3641	,	0.1404	}	,
                    {	0.3616	,	0.1394	}	,
                    {	0.3596	,	0.1389	}	,
                    {	0.3576	,	0.1379	}	,
                    {	0.3556	,	0.1369	}	,





            };

    public static List<String> Streets = new ArrayList<String>(){};
    public static List<String> getStreets(){return Streets;}

    public static List<String> NearbyPlaces = new ArrayList<String>(){};
    public static  List<String> getNearbyPlaces (){return NearbyPlaces;}

    public static List<String> NearbyPlacesStreet = new ArrayList<String>(){};
    public static  List<String> getNearbyPlacesStreet (){return NearbyPlacesStreet;}

    public static List<String> NearbyPlacesHoursOpen = new ArrayList<String>(){};
    public static  List<String> getNearbyPlacesHoursOpen (){return NearbyPlacesHoursOpen;}


    private List<String> listTitle2 = new ArrayList<String>();


    public static List<String> placesnames = new ArrayList<String> (){};

    //////////////////////////////////////////////////////////////

    class LoadPlaces extends AsyncTask<String, String, JSONObject> {


        private String str;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Places..."));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            //pDialog.show();

        }


        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();

           JSONObject js=null;

            try {


                JSONObject json = jParser.getJSONFromUrl( "https://graph.facebook.com/search?limit=1&type=place&center=40.48,23.1385&distance=1000&access_token=xxxxxxxxxxxx|XD2Ng3iQUSXCLLb35mY00000000");
            js=json;
            }
            catch (Exception e) {
                 Log.e("Error ->", "parser", e);
            }

            str=args[1];
            return js;

        }


        protected void onPostExecute(JSONObject json) {
            // dismiss the dialog after getting all products
            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(dialogIsOn)
            {showDialog();}
            dialogIsOn=false;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                jsoncounter=jsoncounter+1;
           // }
        }

    }



    class LoadAddress extends AsyncTask<String, String, JSONObject> {

        public LoadAddress asyncObject;


        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            asyncObject = this;
            new CountDownTimer(4000, 4000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }
                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncObject.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncObject.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                    if(!gotData)
                    {
                        new FetchCordinates().execute();
                    }

                }
            }.start();


        }

        /**
         * getting Places JSON
         * */
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();


            JSONObject json = jParser.getJSONFromUrl("https://maps.googleapis.com/maps/api/geocode/json?address="+args[0].replace(" ","%20")+",thessaloniki&key=AIzaxxxxxxxxxxxxxxxxxxxxxxxx");

            return json;
            ////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////
            //return jsonObj;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * and show the data in UI
         * Always use runOnUiThread(new Runnable()) to update UI from background
         * thread, otherwise you will get error
         * **/
        protected void onPostExecute(JSONObject json) {

            try {
                //placesnames.add(String.valueOf(json.getJSONArray("results").length()));
                JSONArray ar=json.getJSONArray("results");
                JSONObject obj=ar.getJSONObject(0);
                JSONObject obj2=obj.getJSONObject("geometry");
                JSONObject obj3=obj2.getJSONObject("location");

                        if(obj3!=null) {
                            searchlat = Double.valueOf(obj3.getString("lat").toString());
                            searchlong = Double.valueOf(obj3.getString("lng").toString());





                        }


            }
            catch (JSONException e)
            {}




            if(isOnline()) {
                new LoadPlaces().execute();
            }
        }

    }

    class LoadLocation extends AsyncTask<String, String, JSONObject> {

        public LoadLocation asyncLoc;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            asyncLoc = this;
            new CountDownTimer(5000, 5000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }
                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncLoc.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncLoc.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                }
            }.start();
           // pDialog = new ProgressDialog(MainActivity.this);
           // pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Places..."));
           // pDialog.setIndeterminate(false);
           // pDialog.setCancelable(false);
           // pDialog.show();

        }

        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();


            JSONObject json = jParser.getJSONFromUrl("https://maps.googleapis.com/maps/api/geocode/json?latlng="+args[0]+"," +args[1] +"&language=en&key=AIzaxxxxxxxxxxxxxxxxxxxxxxx");


            return json;
            ////////////////////////////////////////////////////////////

        }

        /**
         * After completing background task Dismiss the progress dialog
         * and show the data in UI
         * Always use runOnUiThread(new Runnable()) to update UI from background
         * thread, otherwise you will get error
         * **/
        protected void onPostExecute(JSONObject json) {

            try {

                JSONArray objec = json.getJSONArray("results");
                JSONObject ob=objec.getJSONObject(0);
                JSONArray objectcity = ob.getJSONArray("address_components");
/////////////////////////////////////////////////////////////////////////////////////////////////////
                for(int n=0;n<objectcity.length();n++){
                    JSONObject obcity=objectcity.getJSONObject(n);
                    JSONObject obstreet=objectcity.getJSONObject(n);
                    if(obcity.toString().contains("administrative_area_level_3")){


                        String city= obcity.getString("long_name").toString();

                        if(city.length()>0) {
                            cityName=city;

                        }




                    }
                    else if(obcity.toString().contains("administrative_area_level_2")){

                        String city= obcity.getString("long_name").toString();

                        if(city.length()>0) {
                            cityName=city;

                        }


                    }
                    else if(obcity.toString().contains("administrative_area_level_1")){

                        String city= obcity.getString("long_name").toString();

                        if(city.length()>0) {
                            cityName=city;

                        }


                    }


                    if(obstreet.toString().contains("route")){
                         {
                            //streetName = addres[0];
                            String streetOb= obstreet.getString("short_name").toString();
                            streetName=streetOb;
                             if(streetName.contains("\""))
                             {streetName.replace("\"","");}
                        }
                    }

                }

                if(ob!=null) {
                    String[] addres = ob.getString("formatted_address").split(",");



                    if (streetName != null) {
                        textc.setText(streetName);
                    }
                    if (cityName != null) {
                        txcirccity.setText(cityName);
                    }

                    graphCallinAsync();



                    relativeWarn.setVisibility(View.GONE);

                }



            }
            catch (JSONException ee) {
                //ee.printStackTrace();
            }


           // boolean internet = false;

            try {
                                  if(isOnline()) {
                        new PingTask().execute("www.google.com", "80");//and send to parse
                    }
                }

             catch (Exception e) {
                e.printStackTrace();
           }

        }

    }

    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        else{
            return false;
        }
    }

    public void graphCallinAsync(){
        if(isOnline()) {
            GraphRequest request = GraphRequest.newGraphPathRequest(
                    null,
                    "/search",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            NearbyPlaces.clear();
                            NearbyPlacesStreet.clear();

                            //placesnames.clear();
                            //den htan prin



                            try {
                                //GraphObject go  = response.get
                                JSONObject jso = response.getJSONObject();

                                JSONArray arr = jso.getJSONArray("data");

                                for (int i = 0; i < (arr.length()); i++) {
                                    JSONObject json_obj = arr.getJSONObject(i);
                                    String name = json_obj.getString("name");
                                    //String categoryStr = json_obj.getString("category");
                                    //NearbyPlaces.add(name);

                                        NearbyPlaces.add(name);
                                    placesnames.add(name);


                                }
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }

                            try {
                                //{
                                    //viewsm1.setVisibility(View.INVISIBLE);
                                    //viewsm2.setVisibility(View.INVISIBLE);
                                    //viewsm3.setVisibility(View.INVISIBLE);
                                    //viewsm4.setVisibility(View.INVISIBLE);
                                    //viewsm5.setVisibility(View.INVISIBLE);
                                    //viewsm6.setVisibility(View.INVISIBLE);
                                //}

                                if (NearbyPlaces.size() == 1) {  //==1
                                    txcirc1.setText(getNearbyPlaces().get(0));
                                    viewsm1.setVisibility(View.VISIBLE);
                                } else if (NearbyPlaces.size() == 2) {
                                    txcirc1.setText(getNearbyPlaces().get(0));
                                    txcirc2.setText(getNearbyPlaces().get(1));
                                    viewsm1.setVisibility(View.VISIBLE);
                                    viewsm2.setVisibility(View.VISIBLE);
                                } else if (NearbyPlaces.size() == 3) {
                                    txcirc1.setText(getNearbyPlaces().get(0));
                                    txcirc2.setText(getNearbyPlaces().get(1));
                                    txcirc3.setText(getNearbyPlaces().get(2));
                                    viewsm1.setVisibility(View.VISIBLE);
                                    viewsm2.setVisibility(View.VISIBLE);
                                    viewsm3.setVisibility(View.VISIBLE);
                                } else if (NearbyPlaces.size() == 4) {
                                    txcirc1.setText(getNearbyPlaces().get(0));
                                    txcirc2.setText(getNearbyPlaces().get(1));
                                    txcirc3.setText(getNearbyPlaces().get(2));
                                    txcirc4.setText(getNearbyPlaces().get(3));
                                    viewsm1.setVisibility(View.VISIBLE);
                                    viewsm2.setVisibility(View.VISIBLE);
                                    viewsm3.setVisibility(View.VISIBLE);
                                    viewsm4.setVisibility(View.VISIBLE);
                                } else if (NearbyPlaces.size() == 5) {
                                    txcirc1.setText(getNearbyPlaces().get(0));
                                    txcirc2.setText(getNearbyPlaces().get(1));
                                    txcirc3.setText(getNearbyPlaces().get(2));
                                    txcirc4.setText(getNearbyPlaces().get(3));
                                    txcirc5.setText(getNearbyPlaces().get(4));
                                    viewsm1.setVisibility(View.VISIBLE);
                                    viewsm2.setVisibility(View.VISIBLE);
                                    viewsm3.setVisibility(View.VISIBLE);
                                    viewsm4.setVisibility(View.VISIBLE);
                                    viewsm5.setVisibility(View.VISIBLE);
                                } else if (NearbyPlaces.size() > 5) {
                                    txcirc1.setText(getNearbyPlaces().get(0));
                                    txcirc2.setText(getNearbyPlaces().get(1));
                                    txcirc3.setText(getNearbyPlaces().get(2));
                                    txcirc4.setText(getNearbyPlaces().get(3));
                                    txcirc5.setText(getNearbyPlaces().get(4));
                                    txcirc6.setText(getNearbyPlaces().get(5));
                                    viewsm1.setVisibility(View.VISIBLE);
                                    viewsm2.setVisibility(View.VISIBLE);
                                    viewsm3.setVisibility(View.VISIBLE);
                                    viewsm4.setVisibility(View.VISIBLE);
                                    viewsm5.setVisibility(View.VISIBLE);
                                    viewsm6.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception e) {
                            }

                            gotData = true;

                        }
                    });

            //Toast.makeText(MainActivity.this, String.valueOf(latitude),Toast.LENGTH_SHORT).show();

            Bundle parameters = new Bundle();
            parameters.putString("q", "cafe");
            parameters.putString("type", "place");
            parameters.putString("center", latitude + "," + longitude);
            //parameters.putString("fields","location,hours");
            parameters.putString("fields", "name,category,location");
            parameters.putString("limit", "20");
            parameters.putString("access_token", "xxxxxxxxxxxxx|xxxxxxxxxxxxxxxxxxxxxxxx");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }
///////////////////////////////////////////////////////////////////////////
    public static Animation runFadeOutAnimationOn(Activity ctx, View target) {
        Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.fadeout);
        target.startAnimation(animation);
        return animation;
    }
//////////////////////////////////////////////////////////////////

    class LoadLocationOnly extends AsyncTask<String, String, JSONObject> {
        /**
         * Before starting background thread Show Progress Dialog
         * */
        public LoadLocationOnly asyncLoc;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            asyncLoc = this;
            new CountDownTimer(5000, 5000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }
                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncLoc.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncLoc.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                }
            }.start();

        }
        /**
         * getting Places JSON
         * */
        protected JSONObject doInBackground(String... args) {

//            }
            JSONParser jParser = new JSONParser();


            JSONObject json = jParser.getJSONFromUrl("https://maps.googleapis.com/maps/api/geocode/json?latlng="+args[0]+"," +args[1] +"&key=AIzaxxxxxxxxxxxxxxxxxxxxxxxx");


            return json;
            ////////////////////////////////////////////////////////////

        }

        /**
         * After completing background task Dismiss the progress dialog
         * and show the data in UI
         * Always use runOnUiThread(new Runnable()) to update UI from background
         * thread, otherwise you will get error
         * **/
        protected void onPostExecute(JSONObject json) {

            try {

                JSONArray objec = json.getJSONArray("results");
                JSONObject ob=objec.getJSONObject(0);
                if(ob!=null) {
                    String[] addres = ob.getString("formatted_address").split(",");
                    if(addres.length>0) {
                        streetName = addres[0];
                    }
                    String[] onlystreet=null;
                    String[] checkdash1=null;
                    String[] checkdash2=null;
                    String[] checkdash3=null;

                    onlystreet=addres[0].split(" ");
                    //soumela 20-26
                    // for (int i=0; i<onlystreet.length;i++)

                    if(onlystreet.length>0)
                    {
                        checkdash1=onlystreet[0].split("-");
                    }
                    if(onlystreet.length>1) {
                        checkdash2 = onlystreet[1].split("-");
                    }
                    if(onlystreet.length>2)
                    {
                        checkdash3=onlystreet[2].split("-");
                    }

                    ///////////////////////////////////////////////////////////////////////////////////
                    if (onlystreet.length>0)
                    {
                        streetName = onlystreet[0];

                        if(onlystreet.length==2){
                            if(checkdash2.length<2){streetName=streetName+onlystreet[1];}

                        }
                        if(onlystreet.length==3){
                            if(checkdash3.length<2){streetName=streetName+onlystreet[2];}

                        }
                    }



                    if(addres.length>1)
                    {
                        cityName = addres[1];
                    }
                    if(streetName!=null)
                    {
                        textc.setText(streetName);
                    }
////////////////////////////////////////////////////////////////////////////////////////////////////
                   // Toast.makeText(MainActivity.this,"location",Toast.LENGTH_SHORT).show();
                }


            }
            catch (JSONException ee) {
                //ee.printStackTrace();
            }


        }

    }


    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    ////////////////////

    public class FetchCordinates extends AsyncTask<String, Integer, String> {
        public Location loc;

        public FetchCordinates asyncFetch;

        //ProgressDialog progDailog = null;

        public double lati = 0.0;
        public double longi = 0.0;


        public VeggsterLocationListener mVeggsterLocationListener;

        @Override
        protected void onPreExecute() {

            ////////////////////////////////////////////////////////////////////////////////////////
            asyncFetch = this;
            new CountDownTimer(10000, 10000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }

                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncFetch.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncFetch.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                }
            }.start();
            ////////////////////////////////////////////////////////////////////////////////////////


            mVeggsterLocationListener = new VeggsterLocationListener();
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 1 * 1000, 50,
                    mVeggsterLocationListener);



        }

        @Override
        protected void onCancelled() {
            //System.out.println("Cancelled by user!");
            //progDialog.dismiss();
            mLocationManager.removeUpdates(mVeggsterLocationListener);
        }





        @Override
        protected void onPostExecute(String result) {
            // progDailog.dismiss();

            if ((mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude() != 0)) {
                if (mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude() != 0) {
                    latitude = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
                    longitude = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();
                }
            }



            updateTextCenter();

        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            while (this.lati == 0.0) {

            }
            return null;


        }

        public class VeggsterLocationListener implements LocationListener {

            @Override
            public void onLocationChanged(Location location) {

                try {

                    if(location.getLatitude()!=0) {
                        if (location.getLongitude() != 0)

                        {
                            lati = location.getLatitude();
                            longi = location.getLongitude();
                        }
                    }





                } catch (Exception e) {

                }

            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.i("OnProviderDisabled", "OnProviderDisabled");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.i("onProviderEnabled", "onProviderEnabled");
            }

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                Log.i("onStatusChanged", "onStatusChanged");

            }

        }

    }

    public class FindCordinates extends AsyncTask<String, Integer, String> {
        public  Location loc;

        public FindCordinates asyncFind;

        //ProgressDialog progDailog = null;

        public double lati = 0.0;
        public double longi = 0.0;


        public VeggsterLocationListener mVeggsterLocationListener;

        @Override
        protected void onPreExecute() {

            ////////////////////////////////////////////////////////////////////////////////////////
            asyncFind = this;
            new CountDownTimer(5000, 5000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }
                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncFind.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncFind.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                }
            }.start();
            ////////////////////////////////////////////////////////////////////////////////////////


            mVeggsterLocationListener = new VeggsterLocationListener();
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 60*1000, 50,
                    mVeggsterLocationListener);


        }

        @Override
        protected void onCancelled(){
            //System.out.println("Cancelled by user!");
            //progDialog.dismiss();
            mLocationManager.removeUpdates(mVeggsterLocationListener);
        }

        @Override
        protected void onPostExecute(String result) {
            // progDailog.dismiss();

            if(mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!=null) {
                latitude = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
                longitude = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();
            }
            if(isOnline()) {
                new LoadLocationOnly().execute(String.valueOf(latitude), String.valueOf(longitude));
            }
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            while (this.lati == 0.0) {

            }
            return null;


        }

        public class VeggsterLocationListener implements LocationListener {

            @Override
            public void onLocationChanged(Location location) {

                //
                try {

                    // LocatorService.myLatitude=location.getLatitude();

                    // LocatorService.myLongitude=location.getLongitude();
                    if(location!=null)
                    {
                        lati = location.getLatitude();
                        longi = location.getLongitude();
                    }

                } catch (Exception e) {
                    // progDailog.dismiss();
                    // Toast.makeText(getApplicationContext(),"Unable to get Location"
                    // , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.i("OnProviderDisabled", "OnProviderDisabled");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.i("onProviderEnabled", "onProviderEnabled");
            }

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                Log.i("onStatusChanged", "onStatusChanged");

            }

        }

    }

    class CustomAdapter extends ArrayAdapter<String> {

        Context context;
        int layoutResourceId;
        String[] data = null;
        Typeface tf;

        public CustomAdapter(Context context, int layoutResourceId, String[] data, String FONT) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
            tf = Typeface.createFromAsset(context.getAssets(), FONT);
        }
    }

    public class CustomArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public CustomArrayAdapter(Context context, String[] values) {
            super(context, R.layout.custom_dialog, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.custom_dialog, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.textdialog);
            //ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

            // Customization to your textView here
            //textView.setText("Hello");
            Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/OpenSans.ttf");
            textView.setTypeface(typeFace);
            textView.setTextSize(20);


            return rowView;
        }
    }

    private class PingTask extends AsyncTask<String, Void, Boolean> {
        public PingTask asyncPing;
        @Override
        protected void onPreExecute() {
            ////////////////////////////////////////////////////////////////////////////////////////
            asyncPing = this;
            new CountDownTimer(10000, 10000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }
                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncPing.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncPing.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                }
            }.start();
            ////////////////////////////////////////////////////////////////////////////////////////
        }

        protected Boolean doInBackground(String... params) {
            String url = params[0];
            int port =  Integer.parseInt(params[1]);
            boolean success = false;

            try {
                success = pingURL(url, port);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return success;
        }

        protected void onPostExecute(Boolean result) {
            // do something when a result comes from the async task.
            if (result) {
                internetConnection=true;
               ///////////////////////////// Toast.makeText(MainActivity.this, "internet on", Toast.LENGTH_SHORT).show();
                if(!flagParse) {
                    //callParse();
                    flagParse=true;
                }

            }
            else {internetConnection=false;}

        }

        @Override
        protected void onCancelled(){
            //internetConnection=false;

        }
    }




    private class PingTaskAndSetICOF extends AsyncTask<String, Void, Boolean> {
        public PingTaskAndSetICOF asyncPing;
        @Override
        protected void onPreExecute() {
            ////////////////////////////////////////////////////////////////////////////////////////
            asyncPing = this;
            new CountDownTimer(5000, 5000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }
                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncPing.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncPing.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                }
            }.start();
            ////////////////////////////////////////////////////////////////////////////////////////
        }

        protected Boolean doInBackground(String... params) {
            String url = params[0];
            int port =  Integer.parseInt(params[1]);
            boolean success = false;

            try {
                success = pingURL(url, port);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return success;
        }

        protected void onPostExecute(Boolean result) {
            // do something when a result comes from the async task.
            if (result) {
                internetConnection=true;
                new FindCordinates().execute();
            }
            else {internetConnection=false;}
        }

        @Override
        protected void onCancelled(){
            //internetConnection=false;
        }
    }

    public static boolean pingURL(String hostname, int port) throws UnknownHostException, IOException {
        boolean reachable = false;

        try (Socket socket = new Socket(hostname, port)) {
            reachable = true;
        }

        return reachable;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    public static String capitalizeFirstLetter(String text) {

        StringBuilder str = new StringBuilder();

        String[] tokens = text.split("\\s");// Can be space,comma or hyphen

        for (String token : tokens) {
            str.append(Character.toUpperCase(token.charAt(0))).append(token.substring(1)).append(" ");
        }
        str.toString().trim(); // Trim trailing space
        return str.toString();
        //System.out.println(str);

    }



    class LoadLocationAsync extends AsyncTask<String, String, JSONObject> {

        public String streetNameAsync="";
        public String shop="";
        public LoadLocationAsync asyncLoc;

        public String getStreetName(){return streetNameAsync;}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            asyncLoc = this;
            new CountDownTimer(10000, 10000) {
                public void onTick(long millisUntilFinished) {
                    // You can monitor the progress here as well by changing the onTick() time
                }
                public void onFinish() {
                    // stop async task if not in progress
                    if (asyncLoc.getStatus() == AsyncTask.Status.RUNNING) {
                        asyncLoc.cancel(true);
                        // Add any specific task you wish to do as your extended class variable works here as well.
                    }
                }
            }.start();

        }
        /**
         * getting Places JSON
         * */
        protected JSONObject doInBackground(String... args) {
            shop=args[2];

            JSONParser jParser = new JSONParser();


            JSONObject json = jParser.getJSONFromUrl("https://maps.googleapis.com/maps/api/geocode/json?latlng="+args[0]+"," +args[1] +"&language=en&key=AIzaSxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                                                                                                                        //lat //long
            return json;
            ////////////////////////////////////////////////////////////

        }
        /**
         * After completing background task Dismiss the progress dialog
         * and show the data in UI
         * Always use runOnUiThread(new Runnable()) to update UI from background
         * thread, otherwise you will get error
         * **/
        protected void onPostExecute(JSONObject json) {

            try {

                JSONArray objec = json.getJSONArray("results");
                JSONObject ob=objec.getJSONObject(0);
                JSONArray objectcity = ob.getJSONArray("address_components");
/////////////////////////////////////////////////////////////////////////////////////////////////////
                for(int n=0;n<objectcity.length();n++){
                    JSONObject obcity=objectcity.getJSONObject(n);
                    JSONObject obstreet=objectcity.getJSONObject(n);


                    if(obcity.toString().contains("administrative_area_level_3")){
                        String city= obcity.getString("long_name").toString();
                    }





                    if(obstreet.toString().contains("route")){
                        {

                            String streetOb= obstreet.getString("short_name").toString();
                            streetNameAsync=streetOb;
                            if(streetNameAsync.contains("\""))
                            {streetNameAsync.replace("\"","");}
                        }
                    }

                }
//////////////////////////////////////////////////////////////////////////////////
                // JSONObject obcity=objectcity.getJSONObject(5);
                //JSONObject obstreet=objectcity.getJSONObject(1);
                if(ob!=null) {
                    String[] addres = ob.getString("formatted_address").split(",");

                }


            }
            catch (JSONException ee) {
                //ee.printStackTrace();
            }

            showDialogStreet(shop,streetNameAsync);
        }

    }


    }


