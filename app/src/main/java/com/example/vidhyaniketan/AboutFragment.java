package com.example.vidhyaniketan;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Get references to the TextViews
        TextView aboutUsTitle = view.findViewById(R.id.about_us_title);
        TextView aboutUsHeading = view.findViewById(R.id.about_us_heading);
        TextView aboutUsParagraph = view.findViewById(R.id.about_us_paragraph);

        // Set text from parameters
        aboutUsTitle.setText("About US"); // Assuming mParam1 is for the title

        aboutUsHeading.setText("Welcome to Vidhya Niketan");
        aboutUsParagraph.setText("Vidhya Niketan Public School (VNPS) operates on the standards of best teaching practices, providing all children with the finest education experience in Coimbatore. It is a highly sought after destination for holistic education. We believe that our children have a natural inclination towards learning and we provide the best way to educate and tune them to achieve meaningful goals in their lives. We provide quality teaching supported by a spirited environment that encourages students to build confidence in their own abilities.\n" +
                "Incepted in June 2011, the school was initiated to cater to the growing need of good quality and affordable CBSE curriculum in the city. Conceptualized and managed by the Founder, Correspondent and Secretary Mrs.Rajini Krishnamurthy; who has to her credit, a rich experience of personally managing Vidhya Niketan Matric Higher Secondary School for more than three decades.");

        // Array of titles for each CardView
        String[] titles = {
                "Committed and innovative management",
                "A School that is abreast of all the new curriculum initiatives",
                "Staffed by the best teachers and employees",
                "High academic standards",
                "A well resourced CBSE School",
                "Low teacher-pupil ratios",
                "Supportive and active Parent Teacher Association",
                "Encouraging Curricular Activities",
                "A homely, caring and safe atmosphere",
                "Integrated lesson plans",
                "Continuous teacher development programs",
                "Strong alumni network"};

        // Set titles for each CardView dynamically
        for (int i = 0; i < titles.length; i++) {
            int cardTitleResId = getResources().getIdentifier("card_title_" + (i + 1), "id", getActivity().getPackageName());
            TextView cardTitle = view.findViewById(cardTitleResId);
            if (cardTitle != null) {
                cardTitle.setText(titles[i]);
            }
        }

        return view;
    }
}