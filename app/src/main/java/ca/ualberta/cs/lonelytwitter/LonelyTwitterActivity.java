package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("LifeCycle ---->", "onCreate is called");
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);



		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				saveInFile(text, new Date(System.currentTimeMillis()));
				finish();

			}
		});
	}

	@Override
	protected void onStart() {

		// TODO Auto-generated method stub
		super.onStart();
		Log.i("LifeCycle --->", "onStart is called");
		String[] tweets = loadFromFile();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);

        NormalTweet normalTweet = new NormalTweet("");
        try {
            normalTweet.setMessage("Hello World!");
//            normalTweet.setMessage("aaaaaaaa123904ifkdjfhirhtiorhtherihgtjkerhgjkhergtjkherjkh49ryhui4thg754tdfjlsdkhioerhgiohruioghejiogfuioerhgfiohrjkfhasdjkhgiuhgiorgjkdfhgidfgsdkljfkldsjfklsdjklfjdklsfjkldghsk");
        }
        catch (TweetTooLongException e) {
            Log.e("Error ---->", "Tweet message too long");

        }


        ImportantTweet importantTweet1 = new ImportantTweet("Hello World! This is important");
        ImportantTweet importantTweet2 = new ImportantTweet("This is another important tweet");

        NormalTweet normalTweet1 = new NormalTweet("This is not that important");
        NormalTweet normalTweet2 = new NormalTweet("This is not that important either");

        ArrayList <Tweet> tweetList = new ArrayList<Tweet>();
        tweetList.add(normalTweet);
        tweetList.add(normalTweet1);
        tweetList.add(normalTweet2);
        tweetList.add(importantTweet1);
        tweetList.add(importantTweet2);

        for (Tweet t:
                tweetList) {
            Log.d("Tweet Polymorphism", t.isImportant().toString());

        }


        ArrayList <Tweetable> tweetableList = new ArrayList<Tweetable>();
        tweetableList.add(normalTweet);
        tweetableList.add(normalTweet1);
        tweetableList.add(normalTweet2);
        tweetableList.add(importantTweet1);
        tweetableList.add(importantTweet2);

        String messageOnScreen = "";
        for (Tweetable t:
             tweetableList) {
            messageOnScreen += t.getMessage() + "\n";
        }
        Toast.makeText(this, messageOnScreen, Toast.LENGTH_SHORT).show();

	}

	private String[] loadFromFile() {
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile(String text, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(date.toString() + " | " + text)
					.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("Lifecycle", "onDestroy is called");
	}
}