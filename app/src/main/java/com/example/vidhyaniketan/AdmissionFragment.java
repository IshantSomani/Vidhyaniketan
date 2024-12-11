package com.example.vidhyaniketan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdmissionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AdmissionFragment() {
        // Required empty public constructor
    }

    public static AdmissionFragment newInstance(String param1, String param2) {
        AdmissionFragment fragment = new AdmissionFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admission, container, false);
        // Array of titles for each CardView
        String[] titles = {
                "STEP 1: ADMISSION ENQUIRY",
                "STEP 2: INTERVIEW/ ENTRANCE EXAMINATION",
                "STEP 3: APPLICATION FORM",
                "STEP 4: PAYMENT OF FEES"};

        // Set titles for each CardView dynamically
        for (int i = 0; i < titles.length; i++) {
            int cardTitleResId = getResources().getIdentifier("card_title_" + (i + 1), "id", getActivity().getPackageName());
            TextView cardTitle = view.findViewById(cardTitleResId);
            if (cardTitle != null) {
                cardTitle.setText(titles[i]);
            }
        }

        String[] subtitles = {
                "Parents are requested to collect the enquiry form from the school office upon the submission of the applicant's report card of the previous institution.",
                "According to the marks obtained by the applicant in the previous institution, he/she will be eligible for either an interview or a written entrance examination which will include the syllabus of the grade the child has passed. \n 1. Pre-Kg to LKG : INTERVIEW (ORAL EXAMINATION) \n 2. UKG to Class IX : Written Examination",
                "After the applicant has passed the entrance examination, he/she will be issued with the prospectus and Application Form costing Rs. 300/- The filled- up Application Form should be submitted at the school office along with the following documents: \n 1. Photocopies of : Aadhaar Card, Community Certificate, Birth Certificate \n 2. Original Transfer Certificate \n 3. One passport size photograph",
                "Once the above steps are satisfactorily completed parents will receive an admission offer and may pay the applicable fees to confirm admission."
        };
        // Set subtitles for each CardView dynamically
        for (int i = 0; i < subtitles.length; i++) {
            int cardTitleResId = getResources().getIdentifier("card_subtitle_" + (i + 1), "id", getActivity().getPackageName());
            TextView cardTitle = view.findViewById(cardTitleResId);
            if (cardTitle != null) {
                cardTitle.setText(subtitles[i]);
            }
        }
        
        return view;
    }
}