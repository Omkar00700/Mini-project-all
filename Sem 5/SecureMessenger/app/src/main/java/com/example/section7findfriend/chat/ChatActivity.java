package com.example.section7findfriend.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.section7findfriend.EncryptionAlgo.Algo;
import com.example.section7findfriend.R;
import com.example.section7findfriend.data.Constant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.internal.Util;
import com.google.mlkit.nl.smartreply.SmartReply;
import com.google.mlkit.nl.smartreply.SmartReplyGenerator;
import com.google.mlkit.nl.smartreply.SmartReplySuggestion;
import com.google.mlkit.nl.smartreply.SmartReplySuggestionResult;
import com.google.mlkit.nl.smartreply.TextMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//we have implemented View OnCLickListerner because we want to listen which of above click listerner called

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView ivSend;
    private EditText etMessage;

    //----------------------
    DatabaseReference mRootRef;
    FirebaseAuth firebaseAuth;
    private String currentUserId,chatUserId,chatUserName;
    //------------------------------------------


    //Chating Layout

    RecyclerView rvMessage;
    SwipeRefreshLayout srlMessage;
    MessagesAdaptor messagesAdaptor;
    List<MessageModel> messageModelList;
    List<MessageModel> messageModelList2;

    //Custom Action Bar-------------------------------------
    TextView tvUserName;
    ImageView ivProfile;
    LinearLayout ll_cab;
    //----------------------------------


    //-------------------------------------
    //we want to show only 30 message on first screen then

    int currentPage = 1;
    static final int RECOORD_PER_PAGE = 30;

    DatabaseReference databaseReferenceMessage;
    ChildEventListener childEventListener;
    ChildEventListener childEventListener2;

    //----------------------------------------


    //Smart Reply Api
    private ChipGroup cgSmartReplies;
    private List<TextMessage> conversation;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {




//****************************************************************************
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
//____________________________________________________________________
     //Custom Action Bar

        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("");
            ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.custom_action_bar,null);

     //       actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setElevation(0);

            actionBar.setCustomView(actionBarLayout);
            actionBar.setDisplayOptions(actionBar.getDisplayOptions()|ActionBar.DISPLAY_SHOW_CUSTOM);

        }

        //---------------

        cgSmartReplies=findViewById(R.id.cgSmartReply);
        conversation=new ArrayList<>();

//-----------------------------------------------
        tvUserName=findViewById(R.id.tv_cab);
        ll_cab=findViewById(R.id.ll_profile);
  //-----------------------------------------------------------
        //Binding Components
        ivSend=findViewById(R.id.ivSend);
        etMessage=findViewById(R.id.etMessage);
        rvMessage=findViewById(R.id.rvMessages);
        srlMessage=findViewById(R.id.srlMessages);





        //Called on View Click Action
        ivSend.setOnClickListener(this);

        firebaseAuth= FirebaseAuth.getInstance();  //FirebaseAuth
        mRootRef=FirebaseDatabase.getInstance().getReference();//Ref to Database root
        currentUserId=firebaseAuth.getCurrentUser().getUid();

        //check to get chatUserId from chat Activity ....
        //Extra information can put in intent thorough Put Extra....
        if(getIntent().hasExtra("user_key"))
        {
            chatUserId = getIntent().getStringExtra("user_key");
        }
        if(getIntent().hasExtra("user_Name")){
            chatUserName=getIntent().getStringExtra("user_Name");
        }
        tvUserName.setText(chatUserName);
        //set tvUserName as chatUserName



        Log.d("","User_key : After "+chatUserId);


        messageModelList = new ArrayList<>(); //intialize ModelList
        messageModelList2 = new ArrayList<>();

        messagesAdaptor = new MessagesAdaptor(this,messageModelList); //Create MessageAdaptor

        rvMessage.setLayoutManager(new LinearLayoutManager(this));  //Need to set Layout

        rvMessage.setAdapter(messagesAdaptor);  //set Adaptor on Recycle VIew

        Log.d("","Before Load Messages");
        LoadMessages();
        Log.d("","After Load Messages");
        //---------------------------------------------------------
        rvMessage.scrollToPosition(messageModelList.size()-1);
        srlMessage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage++;
                LoadMessages();
            }
        });



//*****************************************************************************************
    }


    private  void sendMesssage(String msg,String msgType, String pushId)
    {
        try{

            if(!msg.equals(""))
            {
                //HashMap created for storing Data

                HashMap messageMap = new HashMap();
                messageMap.put("messageId",pushId);
                messageMap.put("message",msg);
                messageMap.put("messageType",msgType);
                messageMap.put("messageFrom",currentUserId);
                messageMap.put("messageTime", ServerValue.TIMESTAMP);


                String currentUserRef = "messages"+"/"+currentUserId+"/"+chatUserId;

                String chatUserRef = "messages"+"/"+chatUserId+"/"+currentUserId;


                //------------------------------------------

                HashMap messageUserMap = new HashMap();

                //Structure of Storing message in firebase Datbase
                messageUserMap.put(currentUserRef+"/"+pushId,messageMap);
                messageUserMap.put(chatUserRef+"/"+pushId,messageMap);

                //after sending message message box should be empty...............
                etMessage.setText("");

          //****************************************

            if(msgType.equals("text")){
                conversation.add(TextMessage.createForLocalUser(msg,System.currentTimeMillis()));
            }

          //*************************************

//**************************************************************************************************************

         //TO Update root childern Ref .........

                mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                        if(error!=null)
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.failed_to_send_message, error.getMessage())
                             ,Toast.LENGTH_LONG).show();
                        }
                        {
                          //  Toast.makeText(getApplicationContext(),getString(R.string.message_send_sucessfully),Toast.LENGTH_LONG).show();
                        }


                    }
                });
 //**************************************************************************************************


            }


        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),getString(R.string.failed_to_send_message, ex.getMessage())
                    ,Toast.LENGTH_LONG).show();
        }
    }



    private void  LoadMessages()
    {
        Log.d("","Inside Load Messages");
        messageModelList.clear();
        conversation.clear();
        cgSmartReplies.removeAllViews();
        databaseReferenceMessage=mRootRef.child("messages").child(currentUserId).child(chatUserId);

        Query query = databaseReferenceMessage.limitToLast(currentPage * RECOORD_PER_PAGE);
//----------------------------------------------------------------------------------------------------


//---------------------------------------------------------------------------------------------------
        if(childEventListener!=null)
            query.removeEventListener(childEventListener);

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                MessageModel message1 = snapshot.getValue(MessageModel.class);
                Log.d("","Before Size"+messageModelList.size());
                messageModelList.add(message1);
                Log.d("","Size"+messageModelList.size());
                messagesAdaptor.notifyDataSetChanged();
                //we want to show latest Message.................
                rvMessage.scrollToPosition(messageModelList.size()-1);
                srlMessage.setRefreshing(false);

             showSmartReplies(message1);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                srlMessage.setRefreshing(false);

            }
        };
//-----------------------------------------------------------------------------------------------------
        query.addChildEventListener(childEventListener);
        Log.d("","Inside After Load Messages");
    }

 //*****************************************************************************************

    //Onlick Method of View OnCLick Class
    @Override
    public void onClick(View view) {

        //TO check which of the Following Onclick pressed.....

        switch (view.getId()){

            case R.id.ivSend:

                DatabaseReference userMessagePush =mRootRef.child("messages").child(currentUserId).child(chatUserId).push();
                //Push() : will generate Unique messageId

                String pushId = userMessagePush.getKey();
                String encText;
              //  encText= Algo.encrypt(String.valueOf(etMessage.getText()));
                String text= String.valueOf(etMessage.getText());
                encText=Algo.SimpleEncryption(text);
                //encText=Algo.encrypt(text);
                //encText=text;
                sendMesssage(encText,"text",pushId);

                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId= item.getItemId();
        switch (itemId)
        {
            case android.R.id.home:
                    finish();
                    break;
            default:
                    break;
        }

        return super.onOptionsItemSelected(item);
    }


        private void showSmartReplies(MessageModel messageModel){

            Log.d("Z1", "Inside showSmartReplies: ");

            DatabaseReference databaseReference = mRootRef.child("messages").child(currentUserId).child(chatUserId);
            Query lastQuery = databaseReference.orderByKey().limitToLast(1);
            lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d("Z2", "Inside onData change: ");

                    for(DataSnapshot data : snapshot.getChildren()){
                        Log.d("Z3", "inside for ");
                        MessageModel message =  data.getValue(MessageModel.class);
                        if(message.getMessageFrom().equals(chatUserId)&& messageModel.getMessageId().equals(message.getMessageId())
                                && message.equals("text"))
                        {
                            String Msg=message.getMessage();
                            String dryMsg=Algo.SimpleDecryption(Msg);
                            Log.d("Z4", dryMsg+" "+Msg);

                            conversation.add(TextMessage.createForRemoteUser(dryMsg,System.currentTimeMillis(),chatUserId));


                            SmartReplyGenerator smartReply = SmartReply.getClient();
                            smartReply.suggestReplies(conversation)
                                    .addOnSuccessListener(new OnSuccessListener<SmartReplySuggestionResult>() {
                                        @Override
                                        public void onSuccess(SmartReplySuggestionResult Result) {
                                            if(Result.getStatus() == SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE){
                                                Toast.makeText(ChatActivity.this,"Language not support",Toast.LENGTH_SHORT).show();
                                            }
                                            else if(Result.getStatus()==SmartReplySuggestionResult.STATUS_SUCCESS){
                                                for(SmartReplySuggestion suggestion :Result.getSuggestions()){
                                                    String replyText=suggestion.getText();

                                                    Chip chip = new Chip(ChatActivity.this);
                                                    ChipDrawable drawable=ChipDrawable.createFromAttributes(ChatActivity.this,null,0,
                                                            com.google.android.material.R.style.Widget_MaterialComponents_Chip_Action);

                                                    chip.setChipDrawable(drawable);

                                                     LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                             LinearLayout.LayoutParams.WRAP_CONTENT,
                                                             LinearLayout.LayoutParams.WRAP_CONTENT
                                                             );
                                                     chip.setLayoutParams(params);
                                                    //-----------

                                                    //------------
                                                    chip.setText(replyText);

                                                    chip.setTag(replyText);

                                                    Log.d("F1",replyText);

                                                    chip.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            DatabaseReference messageRef = mRootRef.child("messages")
                                                                    .child(currentUserId).child(chatUserId).push();
                                                            String newMessageId = messageRef.getKey();
                                                            sendMesssage(view.getTag().toString(),"text",newMessageId);
                                                        }
                                                    });
                                                    cgSmartReplies.addView(chip);
                                                }
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });

                        }
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


    }


}