package com.example.fitlife_fitcare;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {
    ImageView share_next,rate_next,feedback_next,privacy_next;
    TextView tvshare,tvrate,tvfeedback,tvprivacy;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        share_next=findViewById(R.id.share_next);
        tvshare=findViewById(R.id.share);
        tvrate=findViewById(R.id.rate);
        tvfeedback=findViewById(R.id.feedback);
        feedback_next=findViewById(R.id.feedback_next);
        tvprivacy=findViewById(R.id.privacy_policy);
        privacy_next=findViewById(R.id.privacy_next);
        tvprivacy.setOnClickListener(v -> {
            Intent intent=new Intent(Settings.this, Privacy_Policy.class);
            startActivity(intent);
        });
        privacy_next.setOnClickListener(v -> {
            Intent intent=new Intent(Settings.this, Privacy_Policy.class);
            startActivity(intent);
        });
        tvfeedback.setOnClickListener(v -> {
            Intent intent=new Intent(Settings.this, Feedback.class);
            startActivity(intent);
        });
        feedback_next.setOnClickListener(v -> {
            Intent intent=new Intent(Settings.this, Feedback.class);
            startActivity(intent);
        });
        tvrate.setOnClickListener(v -> {showRateDialog();});
        findViewById(R.id.img_share).setOnClickListener(v -> {share();});
        tvshare.setOnClickListener(v -> {share();});
        rate_next=findViewById(R.id.rate_next);
        rate_next.setOnClickListener(v -> {share();});
        share_next.setOnClickListener(v -> {
           share();
        });

    }
    private void share()
    {
        try {
            String packageName = getPackageName();
            String appLink = "https://play.google.com/store/apps/details?id=" + packageName;

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app!");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, try this app: " + appLink);
            shareIntent.putExtra(Intent.EXTRA_TITLE,"Share");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showRateDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_rate_us);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        int height = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics());

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);

        ImageView imgEmoji = dialog.findViewById(R.id.imgEmoji);
        TextView txtTitle = dialog.findViewById(R.id.txtTitle);
        TextView txtSubtitle = dialog.findViewById(R.id.txtSubtitle);
        TextView txtExtra = dialog.findViewById(R.id.txtExtra);

        ImageView[] stars = {
                dialog.findViewById(R.id.star1),
                dialog.findViewById(R.id.star2),
                dialog.findViewById(R.id.star3),
                dialog.findViewById(R.id.star4),
                dialog.findViewById(R.id.star5)
        };

        final int[] rating = {0};

        // Click listeners for stars
        for (int i = 0; i < stars.length; i++) {
            int index = i;
            stars[i].setOnClickListener(v -> {
                rating[0] = index + 1;
                updateStars(stars, rating[0]);
                updateEmojiAndText(imgEmoji, txtTitle, txtSubtitle, txtExtra, rating[0]);
            });
        }

        Button btnRate = dialog.findViewById(R.id.btnRate);
        btnRate.setOnClickListener(v -> {
            String packageName = getPackageName();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        });

        dialog.show();
    }

    // Update star icons
    private void updateStars(ImageView[] stars, int rating) {
        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].setImageResource(R.drawable.ic_star_filled); // yellow star
            } else {
                stars[i].setImageResource(R.drawable.ic_star_outline); // empty star
            }
        }
    }

    // Update emoji and text based on rating
    private void updateEmojiAndText(ImageView emoji, TextView title, TextView subtitle, TextView extra, int rating) {
        switch (rating) {
            case 1:
                emoji.setImageResource(R.drawable.ic_sad_emoji);
                title.setText("Oh, no!");
                subtitle.setText("We'd love to hear your feedback.");
                extra.setText("We can do better!");
                break;
            case 2:
                emoji.setImageResource(R.drawable.ic_neutral_emoji);
                title.setText("Hmm...");
                subtitle.setText("Tell us what went wrong.");
                extra.setText("We're improving!");
                break;
            case 3:
                emoji.setImageResource(R.drawable.ic_confused_emoji);
                title.setText("Okay!");
                subtitle.setText("Glad it's not too bad.");
                extra.setText("We aim higher!");
                break;
            case 4:
                emoji.setImageResource(R.drawable.ic_happy_emoji);
                title.setText("Nice!");
                subtitle.setText("Happy you liked it.");
                extra.setText("One more star?");
                break;
            case 5:
                emoji.setImageResource(R.drawable.ic_love_emoji);
                title.setText("Awesome!");
                subtitle.setText("Thanks for your love!");
                extra.setText("The best we can get :)");
                break;
        }
    }
}