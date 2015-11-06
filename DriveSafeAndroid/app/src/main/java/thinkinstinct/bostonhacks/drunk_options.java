package thinkinstinct.bostonhacks;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.HashMap;
import java.util.Map;


public class drunk_options extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drunk_options);
        Firebase.setAndroidContext(this);

    }

    Firebase ref = new Firebase("https://bostonhackslock.firebaseio.com/");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drunk_options, menu);
        return true;
    }

    public void textFriend(View view){
        Bundle b = getIntent().getExtras();
        String phoneNumber = b.getString("message");
        String personName = "Raj";
        String myLocation = "Boston, MA 02215";
        Firebase friendObject = ref.child("Contacts");
        Map<String, String> contact1 = new HashMap<String, String>();
        contact1.put("Phone", phoneNumber);
        contact1.put("Name", personName);
        contact1.put("Location", myLocation);
        //pushFriendObject.setValue(contact1);
        friendObject.setValue(contact1);
        /*SmsManager sms = SmsManager.getDefault();
        String message = "Hey Raj I'm currently at Boston, MA 02215. I've had too much to drink and currently cannot drive. Can you please pick me up?";
        // Add webservice call
        sms.sendTextMessage(phoneNumber, null, message, null, null);*/
        HttpClient c = new DefaultHttpClient();
        HttpPost r = new HttpPost("http://drive-safe.azurewebsites.net/anna");
        try {
            c.execute(r);
            Toast.makeText(getApplicationContext(), "Your message has been sent.",
                    Toast.LENGTH_LONG).show();
        } catch(Exception e) {

        }

    }

    public void callUber(View view){

        //open uber app on android
        try{
//            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ubercab.android");
//            startActivity(launchIntent);
            Context context = getApplicationContext();
            PackageManager pm = context.getPackageManager();

            pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
            String url = "uber://?action=setPickup&pickup=my_location&client_id=YOUR_CLIENT_ID";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);

        } catch (PackageManager.NameNotFoundException e){

            String url = "https://m.uber.com/sign-up?client_id=YOUR_CLIENT_ID";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }

    public void callTaxi(View view){
        //calls the local taxi number
        Bundle b = getIntent().getExtras();
        String phoneNumber = b.getString("message");
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);


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
