package thinkinstinct.bostonhacks;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Unlock extends ActionBarActivity{

    BluetoothArduino mBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//Remove notification bar
      //  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_unlock_screen);
        setup();
        for (int i = 0; i < 999999; i++) {
            i++;
        }

        BluetoothArduino.getInstance().setCallback(this);

        //draw();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unlock_screen, menu);
        return true;
    }


    public void onClick(View view){

//        Intent intent = getIntent();
//        String num = intent.getStringExtra("number");
        Bundle b = getIntent().getExtras();
        String message = b.getString("message");
        Intent intent = new Intent(this, drunk_options.class).putExtra("message", message);
        startActivity(intent);
    }
    public void setup(){
        mBlue = BluetoothArduino.getInstance("HC-05");
        try {
            mBlue.Connect(); // Does Thread.start()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMessageRecieved(String msg) {
        TextView text = (TextView) findViewById(R.id.button);
        text.setText("Reading...");
        Log.d("BLE MESSAGE", msg);
        //String msg = mBlue.getLastMessage();
        Log.i("Bluetooth", msg);

        Log.d("tag", msg);
        if (msg.equals("False")){
            Log.i("acceptance", msg);
            text.setText("PASS");
            Intent intent = new Intent(this, unlocked.class);
            startActivity(intent);

        }
        else {
            text.setText("FAIL");
            text.setBackgroundColor(getResources().getColor(R.color.fail));

            Intent i = new Intent(this, drunk_options.class);
            startActivity(i);
        }
            //if msg is true {
        //start activity
        // else {
    //do whatever
    //}
    }


  //  public void draw(){
//
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            }, 1000);


//        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
