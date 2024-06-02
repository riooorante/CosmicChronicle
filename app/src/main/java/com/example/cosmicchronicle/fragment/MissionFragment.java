package com.example.cosmicchronicle.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.cosmicchronicle.R;

public class MissionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission, container, false);

        WebView webViewOne = view.findViewById(R.id.missionOne);
        String videoOne = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cwZb2mqId0A?si=Uesuxp3Dcb7nholM&amp;start=2\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webViewOne.getSettings().setJavaScriptEnabled(true);
        webViewOne.setWebChromeClient(new WebChromeClient());
        webViewOne.loadData(videoOne, "text/html", "utf-8");

        WebView webViewTwo = view.findViewById(R.id.missionTwo);
        String videoTwo = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7GQnUMVyU2w?si=TQW82h7W3u-nfOPs\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webViewTwo.getSettings().setJavaScriptEnabled(true);
        webViewTwo.setWebChromeClient(new WebChromeClient());
        webViewTwo.loadData(videoTwo, "text/html", "utf-8");

        WebView webViewThree = view.findViewById(R.id.missionThree);
        String videoThree = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CC-z_aBAv6M?si=aqVXGyKeh_2AhOLE\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webViewThree.getSettings().setJavaScriptEnabled(true);
        webViewThree.setWebChromeClient(new WebChromeClient());
        webViewThree.loadData(videoThree, "text/html", "utf-8");

        return view;
    }
}
