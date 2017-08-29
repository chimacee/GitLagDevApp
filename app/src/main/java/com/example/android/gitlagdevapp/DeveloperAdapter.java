package com.example.android.gitlagdevapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by tonero hp02 on 20-Aug-17.
 */

public class DeveloperAdapter extends ArrayAdapter<Developer> {

    private Context context;

    /**
     * Construct {@link DeveloperAdapter}
     *
     * @param context
     * @param developers
     */
    public DeveloperAdapter(Context context,int resource, ArrayList<Developer> developers) {
        super(context, resource, developers);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.developer_list_item, parent, false);
        }

        Developer currentDeveloper = getItem(position);

        TextView usernameView = (TextView) convertView.findViewById(R.id.username_text_view);
        if (currentDeveloper != null) {
            usernameView.setText(currentDeveloper.getUsername());
        }

        // Find the ImageView in the developer_list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
        // If an image is available, use Picasso library to display the provided image based on
        // the resource ID
        if (currentDeveloper != null) {
            Picasso.with(context)
                    .load(currentDeveloper.getImage())
                    .into(imageView);
        }
        // Make sure the view is visible
        imageView.setVisibility(View.VISIBLE);

        return convertView;
    }

}

