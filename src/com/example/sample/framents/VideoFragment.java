package com.example.sample.framents;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aravind.project.elf.adapters.CustomAdapter;
import com.aravind.project.elf.fragments.BaseAbsListViewHelperFragment;
import com.aravind.project.httputils.Logger;
import com.example.sample.R;
import com.example.sample.model.Video;
import com.example.sample.utils.HttpUtility;

public class VideoFragment extends BaseAbsListViewHelperFragment {

	private static final String KEYWORD2 = "KEYWORD";
	String keyword;
	final List<Video> videoList = new ArrayList<Video>();

	public static VideoFragment newInstance(String keyword) {

		VideoFragment fragment = new VideoFragment();

		Bundle bundle = new Bundle();
		bundle.putString(KEYWORD2, keyword);

		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View vw = inflater.inflate(R.layout.fragment_video, null);
		absListView = (ListView) vw.findViewById(R.id.listView1);
		return vw;
	}

	@Override
	public void bindAdapter() {
		keyword = getArguments().getString(KEYWORD2);
		adapter = new MyAdapter();
		final ListView listView = (ListView) absListView;
		listView.setAdapter(adapter);
		new AsyncYoutubeLoader().execute();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}
		});

	}

	@Override
	public void callServerApi() {
		new AsyncYoutubeLoader().execute();
	}

	private class AsyncYoutubeLoader extends AsyncTask<Void, Boolean, List<Video>> {

		@Override
		protected List<Video> doInBackground(Void... params) {

			try {
				Looper.prepare();
			} catch (Exception e) {
				// TODO: handle exception
			}

			HttpUtility utility = new HttpUtility(getActivity());
			return utility.getYoutubeVideos(endlessScrollListener.getPage(), keyword);
		}

		@Override
		protected void onPostExecute(List<Video> result) {

			if (result == null) {
				return;
			}

			videoList.addAll(result);
			adapter.notifyDataSetChanged();

			super.onPostExecute(result);
		}

	}

	private class MyAdapter extends CustomAdapter {

		@Override
		public int getCount() {
			return videoList.size();
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			View vw = arg1;
			ViewHolder holder;
			final Video video = videoList.get(arg0);

			if (vw == null) {
				holder = new ViewHolder();
				vw = inflater.inflate(R.layout.video_item, arg2, false);
				holder.imgBackground = (ImageView) vw.findViewById(R.id.imgRecommend);
				holder.tvTitle = (TextView) vw.findViewById(R.id.tvTitle);

				vw.setTag(holder);
			} else {
				holder = (ViewHolder) vw.getTag();
			}

			try {
				holder.tvTitle.setText(video.getName());

				aq.id(holder.imgBackground).image(video.getIconUrl(), true, true);

			} catch (Exception e) {
				Logger.e("Exception Occured", e.getMessage());
			}

			return vw;
		}
	}

	static class ViewHolder {
		TextView tvTitle;
		ImageView imgBackground;
	}

}
