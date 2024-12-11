package com.example.vidhyaniketan;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ContactUsFragment extends Fragment {
    private EditText nameInput, emailInput, mobileInput, messageInput;
    private Button submitButton;
    private ImageView instagramIcon, youtubeIcon, facebookIcon;
    private ContactDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        // Initialize database helper
        dbHelper = new ContactDatabaseHelper(requireContext());

        // Initialize views
        initializeViews(view);

        // Set up click listeners
        setupClickListeners();

        return view;
    }

    private void initializeViews(View view) {
        // Form inputs
        nameInput = view.findViewById(R.id.name_input);
        emailInput = view.findViewById(R.id.email_input);
        mobileInput = view.findViewById(R.id.mobile_number_input);
        messageInput = view.findViewById(R.id.message_input);
        submitButton = view.findViewById(R.id.submit_button);

        // Social media icons
        instagramIcon = view.findViewById(R.id.icon_instagram);
        youtubeIcon = view.findViewById(R.id.icon_youtube);
        facebookIcon = view.findViewById(R.id.icon_facebook);
    }

    private void setupClickListeners() {
        // Submit button click listener
        submitButton.setOnClickListener(v -> submitForm());

        // Social media click listeners
        instagramIcon.setOnClickListener(v -> openSocialMedia("instagram"));
        youtubeIcon.setOnClickListener(v -> openSocialMedia("youtube"));
        facebookIcon.setOnClickListener(v -> openSocialMedia("facebook"));
    }

    private void submitForm() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String phone = mobileInput.getText().toString().trim();
        String message = messageInput.getText().toString().trim();

        // Validate inputs
        if (validateInputs(name, email, phone, message)) {
            // Save to database
            saveToDatabase(name, email, phone, message);

            // Clear form
            clearForm();

            // Show success message
            showSuccessMessage();
        }
    }

    private boolean validateInputs(String name, String email, String phone, String message) {
        if (name.isEmpty()) {
            nameInput.setError("Name is required");
            return false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Valid email is required");
            return false;
        }

        if (phone.isEmpty() || phone.length() < 10) {
            mobileInput.setError("Valid phone number is required");
            return false;
        }

        if (message.isEmpty()) {
            messageInput.setError("Message is required");
            return false;
        }

        return true;
    }

    private void saveToDatabase(String name, String email, String phone, String message) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ContactDatabaseHelper.COLUMN_NAME, name);
        values.put(ContactDatabaseHelper.COLUMN_EMAIL, email);
        values.put(ContactDatabaseHelper.COLUMN_PHONE, phone);
        values.put(ContactDatabaseHelper.COLUMN_MESSAGE, message);

        try {
            db.insert(ContactDatabaseHelper.TABLE_CONTACTS, null, values);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Error saving data", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    private void clearForm() {
        nameInput.setText("");
        emailInput.setText("");
        mobileInput.setText("");
        messageInput.setText("");
    }

    private void showSuccessMessage() {
        Toast.makeText(requireContext(), "Message sent successfully!", Toast.LENGTH_SHORT).show();
    }

    private void openSocialMedia(String platform) {
        String url;
        switch (platform) {
            case "instagram":
                url = "https://www.instagram.com/vidhyaniketanpublicschool";
                break;
            case "youtube":
                url = "https://www.youtube.com/@vidhyaniketanpublicschool8587";
                break;
            case "facebook":
                url = "https://www.facebook.com/Vidhyaniketan.official";
                break;
            default:
                return;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(requireContext(), "No application can handle this request", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}