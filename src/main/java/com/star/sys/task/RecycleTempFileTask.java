package com.star.sys.task;

import java.io.File;

import com.star.common.fileUtil.FileUtils;
import com.star.common.utils.SystemConstant;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling  //开启定时任务
@Configurable
public class RecycleTempFileTask {
	
	/**
	 * 每天晚上12点执行
	 */
	@Scheduled(cron="0 0 0 * * ? ")
	public void recyleTempFile() {
		File file=new File(FileUtils.UPLOAD_PATH);
		deleteFile(file);
	}
	
	/**
	 * 删除图片
	 * @param file
	 */
	public void deleteFile(File file) {
		if(null!=file) {
			File[] listFiles = file.listFiles();
			if(null!=listFiles&&listFiles.length>0) {
				for (File f : listFiles) {
					if(f.isFile()) {
						if(f.getName().endsWith(SystemConstant.FILE_UPLOAD_TEMP)) {
							f.delete();
						}
					}else {
						//如果是文件夹，再递归删除
						deleteFile(f);
					}
				}
			}
		}
	}

}
