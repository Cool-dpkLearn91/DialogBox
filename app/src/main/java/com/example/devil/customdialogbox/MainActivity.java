package com.example.devil.customdialogbox;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listView1;

    ListView listView2;

    TextView textView;

    ViewFlipper viewFlipper;

    public static ArrayList<Items> listItems;

    public static ArrayList<String> notIntrested;

    public static ArrayList<String> abusive;

    public static ArrayList<String> spamSensitive;

    String[] itemsName;

    int[] iconImage;

    Dialog dialog;

    LinearLayout linearLayout;

    RelativeLayout relativeLayout;

    TextView tvHeader;

    TextView tvAbusive, tvInsideHeader;

    EditText etAbusive;

    Button btnBack, btnCancel, btnDismiss;

    private Animation slide_in_left, slide_in_right, slide_out_left, slide_out_right;

    ArrayAdapter adapter1, adapter2, adapter3, adapter4;

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

                iconImage = new int[]{R.drawable.next_new, R.drawable.next_new, R.drawable.next_new, R.drawable.next_new};

                itemsName = new String[]{"I'm not interested in this Pin", "It's spam", "It displays a sensitive image", "It's abusive or harmful"};

                for (int i = 0; i < 4; i++) {

                    Items item = new Items(itemsName[i], iconImage[i]);

                    listItems.add(item);

                }

                linearLayout = (LinearLayout) dialog.findViewById(R.id.activity_main_ll_for_two_buttons);
                relativeLayout = (RelativeLayout) dialog.findViewById(R.id.activity_main_rl_cancel_only);


                face1 = Typeface.createFromAsset(getAssets(), "fonts/Gill Sans MT Bold Italic.ttf");
                face2 = Typeface.createFromAsset(getAssets(), "fonts/Gill Sans MT Bold.ttf");
                face3 = Typeface.createFromAsset(getAssets(), "fonts/Gill Sans MT Italic.ttf");
                face4 = Typeface.createFromAsset(getAssets(), "fonts/Gill Sans MT.ttf");


                listView1 = (ListView) dialog.findViewById(R.id.activity_main_lv_headers);
                listView2 = (ListView) dialog.findViewById(R.id.activity_main_lv_contents);

                btnBack = (Button) dialog.findViewById(R.id.activity_main_btn_back);
                btnBack.setTypeface(face2);

                btnCancel = (Button) dialog.findViewById(R.id.activity_main_btn_cancel);
                btnCancel.setTypeface(face2);

                btnDismiss = (Button) dialog.findViewById(R.id.activity_main_btn_single_cancel);
                btnDismiss.setTypeface(face2);


                tvHeader = (TextView) dialog.findViewById(R.id.activity_main_tv_header);

                tvHeader.setText("Help us understand the problem.\n what is going on?");
                tvHeader.setTypeface(face2);
                textView = (TextView) dialog.findViewById(R.id.activity_main_tv_items_headers);


                tvAbusive = (TextView) dialog.findViewById(R.id.activity_main_tv_abusive);
                etAbusive = (EditText) dialog.findViewById(R.id.activity_main_et_abusive);
                tvInsideHeader = (TextView) dialog.findViewById(R.id.activity_main_tv_inside_header);

                viewFlipper = (ViewFlipper) dialog.findViewById(R.id.activity_main_view_flipper);

                slide_in_left = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in_left);
                slide_in_right = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in_right);
                slide_out_left = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_out_left);
                slide_out_right = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_out_right);


                notIntrested = new ArrayList<>();
                notIntrested.add("Block");
                notIntrested.add("Mute");

                abusive = new ArrayList<String>();
                abusive.add("It's disrespectful or offensive");
                abusive.add("Includes private information");
                abusive.add("Includes targeted harassment");
                abusive.add("Threatening violence or physical harm");
                abusive.add("This person might be contemplating suicide or self-harm");
                abusive.add("Do you want us to call you personally and resolve this issue?");


                spamSensitive = new ArrayList<>();

                spamSensitive.addAll(notIntrested);
                spamSensitive.add("Edit Text");


                ItemAdapter adapter = new ItemAdapter(getBaseContext(), listItems);

                adapter2 = new ArrayAdapter(getApplication(), R.layout.mytextview, notIntrested);

                adapter3 = new ArrayAdapter(getApplication(), R.layout.mytextview, spamSensitive);

                adapter4 = new ArrayAdapter(getApplication(), R.layout.textview2, abusive);

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
                        if (s.equals("It's abusive or harmful")) {
                            textView.setTypeface(face4);
                            textView.setText("How is this Tweet abusive or harmful?");
                            listView2.setAdapter(adapter4);
                            viewFlipper.setInAnimation(slide_in_left);
                            viewFlipper.showNext();
                            linearLayout.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.INVISIBLE);
                        }
                    }
                });

                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String context = listView2.getItemAtPosition(position).toString();

                        if (context.equalsIgnoreCase("It's disrespectful or offensive")) {
                            tvAbusive.setTypeface(face4);
                            tvAbusive.setText("Me");
                            tvInsideHeader.setTypeface(face4);
                            viewFlipper.setInAnimation(slide_in_left);
                            viewFlipper.showNext();
                            linearLayout.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.INVISIBLE);
                        }
                        if (context.equalsIgnoreCase("Includes private information")) {
                            tvAbusive.setTypeface(face4);
                            tvAbusive.setText("Me");
                            tvInsideHeader.setText("Who owns this private information?");
                            tvInsideHeader.setTypeface(face4);
                            viewFlipper.setInAnimation(slide_in_left);
                            viewFlipper.showNext();
                            linearLayout.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.INVISIBLE);
                        }
                        if (context.equalsIgnoreCase("Includes targeted harassment")) {
                            tvAbusive.setTypeface(face4);
                            tvAbusive.setText("Me");
                            tvInsideHeader.setText("Who is being targeted?");
                            tvInsideHeader.setTypeface(face4);
                            viewFlipper.setInAnimation(slide_in_left);
                            viewFlipper.showNext();
                            linearLayout.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.INVISIBLE);
                        }
                        if (context.equalsIgnoreCase("Threatening violence or physical harm")) {
                            tvAbusive.setTypeface(face4);
                            tvAbusive.setText("Me");
                            tvInsideHeader.setText("Who is being targeted?");
                            tvInsideHeader.setTypeface(face4);
                            viewFlipper.setInAnimation(slide_in_left);
                            viewFlipper.showNext();
                            linearLayout.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.INVISIBLE);
                        }
                        if (context.equalsIgnoreCase("This person might be contemplating suicide or self-harm")) {
                            tvAbusive.setTypeface(face4);
                            tvAbusive.setVisibility(View.INVISIBLE);
                            tvInsideHeader.setVisibility(View.INVISIBLE);
                            viewFlipper.setInAnimation(slide_in_left);
                            viewFlipper.showNext();
                            linearLayout.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.INVISIBLE);
                        }
                        if (context.equalsIgnoreCase("Do you want us to call you personally and resolve this issue?")) {
                            tvAbusive.setTypeface(face4);
                            tvAbusive.setVisibility(View.INVISIBLE);
                            tvInsideHeader.setVisibility(View.INVISIBLE);
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
                        } else if (viewFlipper.getDisplayedChild() == 2) {
                            Log.d("flip"," 2");
                            viewFlipper.setInAnimation(slide_in_right);
                            viewFlipper.showPrevious();
                            linearLayout.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.INVISIBLE);

                        }else{
                                viewFlipper.setInAnimation(slide_in_right);
                                viewFlipper.showPrevious();
                                linearLayout.setVisibility(View.INVISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);

                            }
                        }
                    }

                    );
                    btnCancel.setOnClickListener(new View.OnClickListener()

                    {
                        @Override
                        public void onClick (View v){
                        dialog.dismiss();
                    }
                    }

                    );

                    btnDismiss.setOnClickListener(new View.OnClickListener()

                    {
                        @Override
                        public void onClick (View v){
                        dialog.dismiss();
                    }
                    }

                    );


                    dialog.show();

                }
            }

            );


        }


    }
