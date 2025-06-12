package com.example.mysqlwebsocketmesaj;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private LinearLayout usernameLayout, messageLayout;
    private EditText usernameEditText, messageEditText;
    private Button saveUsernameButton, sendMessageButton;
    private TextView usernameTextView;
    private RecyclerView messagesRecyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messages = new ArrayList<>();
    private String username;
    private Handler handler = new Handler();
    private Runnable refreshMessagesRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupRecyclerView();
        setupButtons();

        // Her 5 saniyede bir mesajları yenile
        refreshMessagesRunnable = new Runnable() {
            @Override
            public void run() {
                refreshMessages();
                handler.postDelayed(this, 5000);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(refreshMessagesRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(refreshMessagesRunnable);
    }

    private void initializeViews() {
        usernameLayout = findViewById(R.id.usernameLayout);
        messageLayout = findViewById(R.id.messageLayout);
        usernameEditText = findViewById(R.id.usernameEditText);
        messageEditText = findViewById(R.id.messageEditText);
        saveUsernameButton = findViewById(R.id.saveUsernameButton);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        usernameTextView = findViewById(R.id.usernameTextView);
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
    }

    private void setupRecyclerView() {
        messageAdapter = new MessageAdapter(messages);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messagesRecyclerView.setAdapter(messageAdapter);
    }

    private void setupButtons() {
        saveUsernameButton.setOnClickListener(v -> saveUsername());
        sendMessageButton.setOnClickListener(v -> sendMessage());
    }

    private void saveUsername() {
        username = usernameEditText.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "Lütfen bir kullanıcı adı girin", Toast.LENGTH_SHORT).show();
            return;
        }

        usernameLayout.setVisibility(View.GONE);
        messageLayout.setVisibility(View.VISIBLE);
        usernameTextView.setVisibility(View.VISIBLE);
        usernameTextView.setText("Hoş geldiniz, " + username + "!");
    }

    private void sendMessage() {
        String messageText = messageEditText.getText().toString().trim();
        if (messageText.isEmpty()) {
            Toast.makeText(this, "Lütfen bir mesaj girin", Toast.LENGTH_SHORT).show();
            return;
        }

        Message message = new Message(username, messageText, String.valueOf(System.currentTimeMillis()));
        RetrofitClient.getApiService().sendMessage(message).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    messageEditText.setText("");
                    refreshMessages();
                } else {
                    Toast.makeText(MainActivity.this, "Mesaj gönderilemedi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hata: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshMessages() {
        RetrofitClient.getApiService().getMessages().enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messageAdapter.updateMessages(response.body());
                    messagesRecyclerView.scrollToPosition(messageAdapter.getItemCount() - 1);
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Mesajlar alınamadı: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}