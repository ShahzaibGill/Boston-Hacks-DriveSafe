package thinkinstinct.bostonhacks;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class MainActivity extends ActionBarActivity {

    String friendsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://bostonhackslock.firebaseio.com/");
        //Switch s = (Switch) findViewById(R.id.switch1);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        friendsNum = editText.getText().toString();
        Intent intent = new Intent(this, Unlock.class);
        intent.putExtra("message", friendsNum);
        startActivity(intent);


    }

//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
//                Toast.LENGTH_SHORT).show();
//        EditText text = (EditText) findViewById(R.id.editText);
//        String txt = text.getText().toString();
//
//        if(isChecked) {
//
//
//        } else {
//            if (!txt.equals("12898925365")){
//                buttonView.setChecked(false);
//                Toast.makeText(this, "Please enter the valid infomation", Toast.LENGTH_LONG).show();
//
//            }
//            else {
//                Toast.makeText(this, "Drunk mode has been enabled", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    }



    public void overideClick(View v){
        Log.v("OverideStatus", "overide is clicked");
    }

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
