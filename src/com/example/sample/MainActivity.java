package com.example.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.aravind.project.elf.fragments.BaseAbsListViewHelperFragment.BaseListViewListener;
import com.example.sample.framents.VideoFragment;

public class MainActivity extends FragmentActivity implements BaseListViewListener {

	private static final String VIDEO_FRAGMENT = "VIDEO_FRAGMENT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fragmentContainer, VideoFragment.newInstance("jennifer lopez"), VIDEO_FRAGMENT);
		ft.commit();
	}
}
