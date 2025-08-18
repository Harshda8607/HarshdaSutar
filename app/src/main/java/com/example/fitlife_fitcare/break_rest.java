package com.example.fitlife_fitcare;

import static com.example.fitlife_fitcare.Abs_advan_5.ABS_ADV_WORKOUT;
import static com.example.fitlife_fitcare.Abs_beginner_5.ABS_BEG_WORKOUT;
import static com.example.fitlife_fitcare.Abs_inter_5.ABS_INT_WORKOUT;
import static com.example.fitlife_fitcare.Arm_advan_4.ARM_ADV_WORKOUT;
import static com.example.fitlife_fitcare.Arm_begin_4.ARM_BEG_WORKOUT;
import static com.example.fitlife_fitcare.Arm_inter_4.ARM_INT_WORKOUT;
import static com.example.fitlife_fitcare.Chest_begin_3.CHEST_BEG_WORKOUT;
import static com.example.fitlife_fitcare.Faceyoga_3.FACE_YOGA_WORKOUT;
import static com.example.fitlife_fitcare.Flatstomach_3.FLAT_STOMACH_WORKOUT;
import static com.example.fitlife_fitcare.Fullbody_9.FULL_BODY_WORKOUT;
import static com.example.fitlife_fitcare.Lowerbody_9.LOWER_BODY_WORKOUT;
import static com.example.fitlife_fitcare.Thigh_advanc_5.THIGH_ADV_WORKOUT;
import static com.example.fitlife_fitcare.Thigh_begin_5.THIGH_BEG_WORKOUT;
import static com.example.fitlife_fitcare.Thigh_inter_5.THIGH_INT_WORKOUT;
import static com.example.fitlife_fitcare.anglebooty_4.ANGLEB_WORKOUT;
import static com.example.fitlife_fitcare.arm_4.ARM_WORKOUT;
import static com.example.fitlife_fitcare.easeyoga_6.EASE_WORKOUT;
import static com.example.fitlife_fitcare.flatbelly_4.BELLY_WORKOUT;
import static com.example.fitlife_fitcare.indoor_5.INDOOR_WORKOUT;
import static com.example.fitlife_fitcare.lazybed_5.LAZYBED_WORKOUT;
import static com.example.fitlife_fitcare.morning_4.MORNING_WORKOUT;
import static com.example.fitlife_fitcare.office_5.OFFICE_WORKOUT;
import static com.example.fitlife_fitcare.postrun_4.POST_WORKOUT;
import static com.example.fitlife_fitcare.prerun_4.PRE_WORKOUT;
import static com.example.fitlife_fitcare.sitting_6.SIT_WORKOUT;
import static com.example.fitlife_fitcare.sleep_4.SLEEP_WORKOUT;
import static com.example.fitlife_fitcare.slim_4.SLIM_WORKOUT;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class break_rest extends AppCompatActivity {
    private TextView timertext;
    TextView tpause,seconds,seconds2;

    private CountDownTimer countDownTimer;
    private boolean isRunning = true;
    private long timeLeft = 30000;
    private int nextWorkout;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_break_rest);
        timertext=findViewById(R.id.timer);
        tpause=findViewById(R.id.pause);
        seconds=findViewById(R.id.sec_20);
        seconds2=findViewById(R.id.sec_20_2);
        nextWorkout = getIntent().getIntExtra("NEXT_WORKOUT", 1);


        startTimer();
        tpause.setOnClickListener(v -> {
            if (isRunning) {
                pauseTimer();
            } else {
                resumeTimer();
            }
        });

        seconds.setOnClickListener(v -> {
            addTwentySeconds();
        });
        seconds2.setOnClickListener(v ->{ subTwentySeconds();});



    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                int seconds = (int) millisUntilFinished / 1000;
                timertext.setText("00:" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {
                goToNextExercise();
            }
        }.start();
        isRunning = true;
        tpause.setText("PAUSE");
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        isRunning = false;
        tpause.setText("RESUME");
    }

    private void resumeTimer() {
        startTimer();
    }
    private void addTwentySeconds() {
        if (isRunning && countDownTimer != null) {
            countDownTimer.cancel();
        }

        timeLeft += 20000;

        startTimer(); // Restart with new time
    }
    private void subTwentySeconds() {
        if (isRunning && countDownTimer != null) {
            countDownTimer.cancel();
        }

        timeLeft -= 20000;

        startTimer();
    }
    private void goToNextExercise() {
        Intent intent;
        switch (nextWorkout) {
            case FULL_BODY_WORKOUT:
                intent = new Intent(break_rest.this, Fullbody_10.class);
                break;
            case LOWER_BODY_WORKOUT:
                intent = new Intent(break_rest.this, Lowerbody_10.class);
                break;
            case FLAT_STOMACH_WORKOUT:
                intent = new Intent(break_rest.this, Flatstomach_4.class);
                break;
            case FACE_YOGA_WORKOUT:
                intent = new Intent(break_rest.this, Faceyoga_4.class);
                break;
            case ABS_BEG_WORKOUT:
                intent = new Intent(break_rest.this, Abs_beginner_6.class);
                break;
            case ABS_INT_WORKOUT:
                intent = new Intent(break_rest.this, Abs_inter_6.class);
                break;
            case ABS_ADV_WORKOUT:
                intent = new Intent(break_rest.this, Abs_advan_6.class);
                break;
            case THIGH_BEG_WORKOUT:
                intent = new Intent(break_rest.this, Thigh_begin_6.class);
                break;
            case THIGH_INT_WORKOUT:
                intent = new Intent(break_rest.this, Thigh_inter_6.class);
                break;
            case THIGH_ADV_WORKOUT:
                intent = new Intent(break_rest.this, Thigh_advanc_6.class);
                break;
            case ARM_BEG_WORKOUT:
                intent = new Intent(break_rest.this, Arm_begin_5.class);
                break;
            case ARM_INT_WORKOUT:
                intent = new Intent(break_rest.this, Arm_inter_5.class);
                break;
            case ARM_ADV_WORKOUT:
                intent = new Intent(break_rest.this, Arm_advan_5.class);
                break;
            case CHEST_BEG_WORKOUT:
                intent = new Intent(break_rest.this, Chest_begin_4.class);    //14
                break;
//            case CHEST_INT_WORKOUT:
//                intent = new Intent(break_rest.this, chest_inter_4.class);
//                break;
//            case CHEST_ADV_WORKOUT:
//                intent = new Intent(break_rest.this,chest_adv_4 .class);
//                break;
            case LAZYBED_WORKOUT:
                intent = new Intent(break_rest.this, lazybed_6.class);
                break;
            case OFFICE_WORKOUT:
                intent = new Intent(break_rest.this, office_6.class);
                break;
            case INDOOR_WORKOUT:
                intent = new Intent(break_rest.this, indoor_6.class);
                break;
            case BELLY_WORKOUT:
                intent = new Intent(break_rest.this, flatbelly_5.class);
                break;
            case ANGLEB_WORKOUT:
                intent = new Intent(break_rest.this, anglebooty_5.class);
                break;
            case ARM_WORKOUT:
                intent = new Intent(break_rest.this, arm_5.class);
                break;
            case SLIM_WORKOUT:
                intent = new Intent(break_rest.this, slim_5.class);
                break;
            case MORNING_WORKOUT:
                intent = new Intent(break_rest.this, morning_5.class);
                break;
            case PRE_WORKOUT:
                intent = new Intent(break_rest.this, prerun_5.class);
                break;
            case POST_WORKOUT:
                intent = new Intent(break_rest.this, postrun_5.class);
                break;
            case SLEEP_WORKOUT:
                intent = new Intent(break_rest.this, sleep_5.class);  //27
                break;
            case EASE_WORKOUT:
                intent = new Intent(break_rest.this, easeyoga_7.class);
                break;
            case SIT_WORKOUT:
            default:
                intent = new Intent(break_rest.this, sitting_7.class);
                break;
//            case STAND_WORKOUT:
//            default:
//                intent = new Intent(break_rest.this, stand_7.class);
//                break;
        }

        startActivity(intent);
        finish();
    }

}