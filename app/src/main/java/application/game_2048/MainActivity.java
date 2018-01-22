package application.game_2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {


    RelativeLayout rl;//Lab 3 Addition
    final int GAMEBOARD_DIMENSION = 1000;
    final int GAMELOOPRATE = 25; //40 fps

    GameLoop myGameLoop;
    Timer myGameLoopTimer;

    TextView tvAccelerometer;
    AccelerometerEventListener accListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        //Lab 2 Cleanup
        /*ll = (LinearLayout)findViewById(R.id.layout1);
        ll.setOrientation(LinearLayout.VERTICAL);

        graph = new LineGraphView(	getApplicationContext(), 100, Arrays.asList("x", "y", "z"));
        ll.addView(graph);
        graph.setVisibility(View.VISIBLE);
        */

        //Lab 3 Addition
        rl = (RelativeLayout) findViewById(R.id.layout1);
        rl.getLayoutParams().width = GAMEBOARD_DIMENSION;
        rl.getLayoutParams().height = GAMEBOARD_DIMENSION;

        ImageView gameBoard = new ImageView(getApplicationContext());
        gameBoard.setImageResource(R.drawable.gameboard);
        rl.addView(gameBoard);

        tvAccelerometer = new TextView(getApplicationContext());
        tvAccelerometer.setText("Accelerometer Instantaneous Readings");
        tvAccelerometer.setTextColor(Color.BLACK);
        tvAccelerometer.setTextSize(40.0f);

        //Lab 3 Addition
        rl.addView(tvAccelerometer);


        //Lab 3: Start the Game Loop (20 ms)
        myGameLoop = new GameLoop(this, rl, getApplicationContext());
        myGameLoopTimer = new Timer();
        myGameLoopTimer.schedule(myGameLoop, GAMELOOPRATE, GAMELOOPRATE);


        SensorManager sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor Accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        //Lab 2 Cleanup:  accListener = new AccelerometerEventListener(tvAccelerometer, graph);
        //Lab 3 Addition
        accListener = new AccelerometerEventListener(tvAccelerometer, myGameLoop);
        sensorManager.registerListener(accListener, Accelerometer,  SensorManager.SENSOR_DELAY_GAME);



        //Lab 2 Cleanup:  fileCount = 0;


        //Lab 2 Cleanup
        /*
        sampleButton = new Button(getApplicationContext());
        sampleButton.setText("Generate CSV Record for Acc.Sen.");
        sampleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                File file = null;
                PrintWriter writer = null;
                float[][] historyReadings = accListener.getHistoryReading();


                try {
                    file = new File(getExternalFilesDir("Recorded Readings New"), String.format("AccRecordNEW%d.csv", fileCount++));
                    writer = new PrintWriter(file);

                    for(int i = 0; i < historyReadings.length; i++)
                        writer.println(String.format("%d,%.2f,%.2f,%.2f", (i + 1), historyReadings[i][0], historyReadings[i][1], historyReadings[i][2]));
                }
                catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }
                finally{
                    if(writer != null) {
                        writer.flush();
                        writer.close();
                    }
                }
                Log.d("ECE 155 Lab 1: ", "Accelerometer Record Written in " + file);

            }
        });

        ll.addView(sampleButton);
        ll.addView(tvAccelerometer);

        */




    }

}