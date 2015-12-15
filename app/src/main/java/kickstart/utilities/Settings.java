package kickstart.utilities;


public class Settings {
	private static String noPicturePath;
	private static String uploadedPicturesPath;
	
	public static String getNoPicturePath() {
		return noPicturePath;
	}
	public static void setNoPicturePath(String noPicturePath) {
		Settings.noPicturePath = noPicturePath;
	}
	public static String getUploadedPicturesPath() {
		return uploadedPicturesPath;
	}
	public static void setUploadedPicturesPath(String uploadedPicturesPath) {
		Settings.uploadedPicturesPath = uploadedPicturesPath;
	}
}
