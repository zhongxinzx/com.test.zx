package test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GroupPhoto {
	
	private void group() {
		String path = "G://Personal//photo//bak";
		File dir = new File(path);
		File photoList[] = dir.listFiles();
		if (photoList == null || photoList.length == 0) {
			return;
		}
		System.out.println("一共找到 " + photoList.length + " 张图片");
		String date = null;
		String photpPath = null;
		File dateDir = null;
		for (File photo : photoList) {
			if (photo.isDirectory()) {
				continue;
			}
			date = getLastModifyDate(photo);
			photpPath = path + File.separator + date;
			dateDir = new File(photpPath);
			if (!dateDir.exists()) {
				dateDir.mkdirs();
			}
			// movieFile(photo, dateDir);
			copyFile(photo.getPath(), photpPath + File.separator + photo.getName());
		}
	}
	
	public void copyFile(String oldPath, String newPath) {
		try {
			// int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
			System.out.println("复制文件..." + oldPath);
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}
	
	private String getLastModifyDate(File file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(file.lastModified());
		return sdf.format(cal.getTime());
	}
	
	/*private void movieFile(File file, File targetDir) {
		try {
			if (file.renameTo(targetDir)) {
				System.out.println("成功，移动文件" + file.getName());
			} else {
				System.out.println("失败。。。。。" + file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {
		new GroupPhoto().group();
	}

}
