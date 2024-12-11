package com.example.vidhyaniketan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contacts;
    private final Context context;
    private int lastPosition = -1;  // For animation

    public ContactAdapter(Context context) {
        this.context = context;
        this.contacts = new ArrayList<>();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        try {
            Contact contact = contacts.get(position);

            // Set contact details
            holder.nameText.setText(contact.getName() != null ? contact.getName() : "");
            holder.emailText.setText(contact.getEmail() != null ? contact.getEmail() : "");
            holder.phoneText.setText(contact.getPhone() != null ? contact.getPhone() : "");
            holder.messageText.setText(contact.getMessage() != null ? contact.getMessage() : "");

            // Set first letter of name as image text
            if (contact.getName() != null && !contact.getName().isEmpty()) {
                holder.contactImage.setText(String.valueOf(contact.getName().charAt(0)).toUpperCase());
            } else {
                holder.contactImage.setText("?");
            }

            // Add animation to the item
            setAnimation(holder.itemView, position);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Animation method
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ContactViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts != null ? contacts : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, emailText, phoneText, messageText, contactImage;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            emailText = itemView.findViewById(R.id.emailText);
            phoneText = itemView.findViewById(R.id.phoneText);
            messageText = itemView.findViewById(R.id.messageText);
            contactImage = itemView.findViewById(R.id.contactImage);
        }
    }
}