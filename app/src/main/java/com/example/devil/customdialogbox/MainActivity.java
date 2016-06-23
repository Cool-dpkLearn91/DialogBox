package com.example.devil.customdialogbox;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity{

    ListView listView1;

    ListView listView2;

    TextView textView;

    ViewFlipper viewFlipper;

    public static ArrayList<Items> listItems;

    public static ArrayList<String> notIntrested;

    public static ArrayList<String> spamSensitive;

    String[] itemsName;

    int[] iconImage;

    Dialog dialog;

    LinearLayout linearLayout;

    RelativeLayout relativeLayout;

    TextView tvHeader;

    Button btnBack , btnCancel, btnDismiss;

    private Animation slide_in_left, slide_in_right, slide_out_left, slide_out_right;

    ArrayAdapter adapter1, adapter2, adapter3;

    Button btnDialog;

    Typeface face1;

    Typeface face2;
    Typeface face3;
    Typeface face4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.activity_main);
           // dialog.setTitle("Help us understand the problem. what is going on?");

            listItems = new ArrayList<>();

            iconImage = new int[]{R.drawable.arrow, R.drawable.arrow, R.drawable.arrow};

            itemsName = new String[]{"I'm not interested in this Pin", "It's spam", "It displays a sensitive image"};

            for (int i = 0; i < 3; i++) {

                Items item = new Items(itemsName[i], iconImage[i]);

                listItems.add(item);

            }

            linearLayout = (LinearLayout)dialog.findViewById(R.id.linearLayout);
            relativeLayout = (RelativeLayout)dialog.findViewById(R.id.relativeCancel);

            face1 = Typeface.createFromAsset(getAssets(),"fonts/Gill Sans MT Bold Italic.ttf");
            face2 = Typeface.createFromAsset(getAssets(),"fonts/Gill Sans MT Bold.ttf");
            face3 = Typeface.createFromAsset(getAssets(),"fonts/Gill Sans MT Italic.ttf");
            face4 = Typeface.createFromAsset(getAssets(),"fonts/Gill Sans MT.ttf");


            listView1 = (ListView)dialog.findViewById(R.id.lvListView1);
            listView2 = (ListView) dialog.findViewById(R.id.lvListView2);

            btnBack = (Button)dialog.findViewById(R.id.lftangry_btn);
            btnBack.setTypeface(face2);

            btnCancel = (Button)dialog.findViewById(R.id.rftangry_btn);
            btnCancel.setTypeface(face2);

            btnDismiss = (Button)dialog.findViewById(R.id.btn_cancel);
            btnDismiss.setTypeface(face2);


            tvHeader = (TextView)dialog.findViewById(R.id.tvHeader);

            tvHeader.setText("Help us understand the problem.\n what is going on?");
            tvHeader.setTypeface(face2);
            textView = (TextView) dialog.findViewById(R.id.tvText);

            viewFlipper = (ViewFlipper) dialog.findViewById(R.id.flipper);

            slide_in_left = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in_left);
            slide_in_right = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in_right);
            slide_out_left = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_out_left);
            slide_out_right = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_out_right);


            notIntrested = new ArrayList<>();

            notIntrested.add("Block");
            notIntrested.add("Mute");

            spamSensitive = new ArrayList<>();

            spamSensitive.addAll(notIntrested);
            spamSensitive.add("Edit Text");


            ItemAdapter adapter = new ItemAdapter(getBaseContext(), listItems);

            adapter2 = new ArrayAdapter(getApplication(), R.layout.mytextview, notIntrested);

            adapter3 = new ArrayAdapter(getApplication(), R.layout.mytextview, spamSensitive);

            listView1.setAdapter(adapter);

            linearLayout.setVisibility(View.INVISIBLE);

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String s = ItemAdapter.itemsList.get(position).getItemName();

                    if (s.equals("I'm not interested in this Pin")) {
                        textView.setTypeface(face4);
                        textView.setText(s);
                        listView2.setAdapter(adapter2);
                        viewFlipper.setInAnimation(slide_in_left);
                        linearLayout.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.INVISIBLE);
                        viewFlipper.showNext();

                    }
                    if (s.equals("It's spam")) {
                        textView.setTypeface(face4);
                        textView.setText(s);
                        listView2.setAdapter(adapter3);
                        viewFlipper.setInAnimation(slide_in_left);
                        viewFlipper.showNext();
                        linearLayout.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.INVISIBLE);
                    }
                    if (s.equals("It displays a sensitive image")) {
                        textView.setTypeface(face4);
                        textView.setText(s);
                        listView2.setAdapter(adapter3);
                        viewFlipper.setInAnimation(slide_in_left);
                        viewFlipper.showNext();
                        linearLayout.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.INVISIBLE);
                    }
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (viewFlipper.getDisplayedChild() == 0) {
                        viewFlipper.stopFlipping();
                        linearLayout.setVisibility(View.INVISIBLE);
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else {
                        viewFlipper.setInAnimation(slide_in_right);
                        viewFlipper.showPrevious();
                        linearLayout.setVisibility(View.INVISIBLE);
                        relativeLayout.setVisibility(View.VISIBLE);

                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            btnDismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            dialog.show();

        }
    });



    }


}
