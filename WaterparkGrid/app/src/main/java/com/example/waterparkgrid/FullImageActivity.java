package com.example.waterparkgrid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FullImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        databaseBookings = FirebaseDatabase.getInstance().getReference("bookings");

        // Get the intent and position of the image
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("id");

        // Initialize the ImageAdapter
        ImageAdapter imageAdapter = new ImageAdapter(this);

        // Set the waterpark name dynamically
        TextView waterparkName = findViewById(R.id.waterpark_name);
        String[] waterparkNames = {
                "Water World Waterpark",
                "Bangi Wonderland Waterpark",
                "Gamuda Waterpark",
                "Splashmania Waterpark",
                "Sunway Lagoon Waterpark",
                "Wetworld Waterpark"
        };
        waterparkName.setText(waterparkNames[position]);

        // Set the image in the ImageView
        ImageView imageView = findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.thumbImages[position]);

        // Set the description text dynamically
        TextView imageDescription = findViewById(R.id.image_description);
        String[] descriptions = {
                "Enjoy thrilling slides, wave pools, and a relaxing lazy river. Perfect for families with fun zones for kids and plenty of dining options. Feeling excited? Book your tickets now!:",
                "A mix of adventurous rides and family-friendly attractions, including the Spiral Splash, wave pools, and a pirate-themed play area. Feeling excited? Book your tickets now!:",
                "Experience exhilarating water slides, a lazy adventure river, and family-friendly attractions surrounded by lush greenery. Feeling excited? Book your tickets now!:",
                "Packed with modern attractions like the Aqua Loop and Splash River, this colorful park offers excitement and Instagram-worthy spots. Feeling interested? Book now!:",
                "A world-class destination with iconic rides like the Vuvuzela slide and Surf Beach, plus areas for kids and a water playground. Feeling excited? Book your tickets now!:",
                "An affordable family favorite with classic water rides, splash pools for toddlers, and shaded picnic spots for a relaxing day out. Feeling excited? Book your tickets now!:"
        };
        imageDescription.setText(descriptions[position]);

        // Handle form submission
        EditText nameInput = findViewById(R.id.name_input);
        EditText emailInput = findViewById(R.id.email_input);
        EditText phoneInput = findViewById(R.id.phone_input);
        Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBooking(nameInput, emailInput, phoneInput);
            }
        });

        // Back button click listener
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the previous activity
                finish();
            }
        });
    }

    // Declare Firebase reference

    DatabaseReference databaseBookings;

    // Function to add booking
    private void addBooking(EditText nameInput, EditText emailInput, EditText phoneInput) {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();

        // Check if all fields are filled
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone)) {
            // Generate unique ID for the booking
            String bookingId = databaseBookings.push().getKey();
            Booking booking = new Booking(bookingId, name, email, phone);

            // Save booking to Firebase
            databaseBookings.child(bookingId).setValue(booking);

            // Show success message
            Toast.makeText(this, "Booking Successful!", Toast.LENGTH_LONG).show();

            // Reset form
            nameInput.setText("");
            emailInput.setText("");
            phoneInput.setText("");
        } else {
            // If any field is empty, show an error message
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
        }
    }

    // Booking model class to store user data
    public static class Booking {
        public String id;
        public String name;
        public String email;
        public String phone;

        public Booking() {
            // Default constructor required for Firebase
        }

        public Booking(String id, String name, String email, String phone) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }
    }
}

