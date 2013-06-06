package com.pearson.ed.tony.download;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VideoInfo;

public class AsyncYouTubeDownloader {

	ExecutorService youtubeDownloadExecutorService = Executors.newFixedThreadPool(2);

	/**
	 * Batch video downloading. Just calls downloadVideo() a lot.
	 * 
	 * @param urls
	 * @param fileLocation
	 * @return yo mamma
	 */
	public List<Future<VideoInfo>> downloadVideos(final List<String> urls, final String fileLocation) {
		ArrayList<Future<VideoInfo>> futureVideos = new ArrayList<Future<VideoInfo>>();
		for (String url : urls) {
			Future<VideoInfo> futureVideo = downloadVideo(url, fileLocation);
			futureVideos.add(futureVideo);
		}
		return futureVideos;
	}

	/**
	 * Downloads a Youtube (psssst... or Vimeo0 video from the given URL)
	 * 
	 * @param url
	 * @param fileLocation
	 * @return
	 */
	public Future<VideoInfo> downloadVideo(final String url, final String fileLocation) {
		Future<VideoInfo> futureVideo = youtubeDownloadExecutorService.submit(new Callable<VideoInfo> () {
			public VideoInfo call() {
				VideoInfo video = null;
				System.out.println("Download start time for video at '" + url + "': " + new Date());
				
				try {
					VGet v = new VGet(new URL(url), new File(fileLocation));
					v.download();
					video = v.getVideo();
					System.out.println("Download finish time for video at '" + url + "': " + new Date());
				} catch (Exception e) {
					System.out.println("Exception occurred for video at '" + url + "': " + new Date());
					System.out.println(e.getClass().getCanonicalName() + ": " + e.getMessage());
				}
				
				return video;
			}
		});
		
		return futureVideo;
	}
	
	/*
	public static void main(String[] args) {
		AsyncYouTubeDownloader downloader = new AsyncYouTubeDownloader();
		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://www.youtube.com/watch?v=sTSA_sWGM44");
		urls.add("http://vimeo.com/52716355");
		urls.add("http://www.youtube.com/watch?v=TcxpbhM0DaA");
		urls.add("http://www.youtube.com/watch?v=ZzN36M0o5aM");
		urls.add("http://vimeo.com/channels/staffpicks/67676694");
		downloader.downloadVideos(urls, "C:\\Users\\uwestan\\Downloads");
    }
    */
	
}
