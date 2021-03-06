package com.pearson.ed.tony.download;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class YoutubeDownloadController {
	static private final String TEMP_DIRECTORY = System.getProperty("java.io.tmpdir");
	static private final String YOUTUBE_URL = "http://www.youtube.com/watch?v=";
	
	private final AsyncYouTubeDownloader downloader = new AsyncYouTubeDownloader();
	private final List<Future<String>> futureVideos = Collections.synchronizedList(new ArrayList<Future<String>>());
	private String downloadDirectory = System.getProperty("user.home"); // default to system temp
	private ExecutorService fileTransferExecutorService = Executors.newSingleThreadExecutor();
	private boolean isPolling = false;
	
	public String getDownloadDirectory() {
		return downloadDirectory;
	}

	public void setDownloadDirectory(String downloadDirectory) {
		this.downloadDirectory = downloadDirectory;
	}

	/**
	 * Download a single video with the Id
	 * @param videoId
	 */
	public void downloadVideoId(final String videoId) {
		ArrayList<String> videoIds = new ArrayList<String>();
		videoIds.add(videoId);
		downloadVideoIds(videoIds);
	}
	
	/**
	 * For downloading a buncha videos with the Ids
	 * @param videoIds
	 */
	public void downloadVideoIds(final List<String> videoIds) {
		ArrayList<String> urls = new ArrayList<String>();
		for (String videoId : videoIds) {
			urls.add(YOUTUBE_URL + videoId);
		}
		downloadVideos(urls);
	}
	
	/**
	 * For downloading a single video.
	 * 
	 * @param url
	 * @param directory
	 */
	public void downloadVideo(final String url) {
		ArrayList<String> urls = new ArrayList<String>();
		urls.add(url);
		downloadVideos(urls);
	}
	
	/**
	 * For downloading a buncha videos
	 * @param urls
	 * @param directory
	 */
	public void downloadVideos(final List<String> urls) {
		List<Future<String>> newFutureVideos = downloader.downloadVideos(urls, TEMP_DIRECTORY);
		futureVideos.addAll(newFutureVideos);
		pollForFiles();
	}
	
	/**
	 * Starts the background thread which will move any completed downloads to
	 * the download folder from the temp folder... because Shawn don't want any
	 * half-downloaded files in the download folder.
	 */
	private synchronized void pollForFiles() {
		if (!isPolling) {
			isPolling = true;
			
			fileTransferExecutorService.execute(new Runnable() {
				public void run() {
					ArrayList<Future<String>> movedFutureVideos = new ArrayList<Future<String>>();
					while (true) {
						for (Future<String> futureVideo : futureVideos) {
							try {
								if (futureVideo.isDone()) {
									String video = futureVideo.get();
									File videoFile = new File(video);
									File downLoadedVideoFile = new File(downloadDirectory + File.separator + videoFile.getName());
									if (!videoFile.getAbsolutePath().equalsIgnoreCase(downLoadedVideoFile.getAbsolutePath())) {
										videoFile.renameTo(downLoadedVideoFile);
									}
									movedFutureVideos.add(futureVideo);
								}
							} catch (Exception e) {
								System.out.println("Exception occurred for video file: " + new Date());
								System.out.println(e.getClass().getCanonicalName() + ": " + e.getMessage());
								// Fuck it, dump the thing anyway. It's just temp :-P
								movedFutureVideos.add(futureVideo);
							}
						}
						futureVideos.removeAll(movedFutureVideos);
						if (movedFutureVideos.size() > 0 && futureVideos.isEmpty()) {
							System.out.println("Video download queue is now empty.");
						}
						movedFutureVideos.clear();
					}
				}
			});
		}
	}
	
	public void shutdown() {
		downloader.shutdown();
		fileTransferExecutorService.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		YoutubeDownloadController downloader = new YoutubeDownloadController();
		downloader.setDownloadDirectory("C:\\Users\\uwestan\\Downloads");
		ArrayList<String> urls = new ArrayList<String>();
//		urls.add("http://www.youtube.com/watch?v=sTSA_sWGM44");
//		//urls.add("http://vimeo.com/52716355");
//		ArrayList<String> urls2 = new ArrayList<String>();
//		urls2.add("http://www.youtube.com/watch?v=TcxpbhM0DaA");
//		urls2.add("http://www.youtube.com/watch?v=ZzN36M0o5aM");
//		//urls2.add("http://vimeo.com/channels/staffpicks/67676694");
//		downloader.downloadVideos(urls);
//		Thread.sleep(10000);
//		downloader.downloadVideos(urls2);
		
		downloader.downloadVideoId("Dh0OuuGrzlQ");
		
    }
	
}
