package action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import model.Race;
import service.RaceService;
import it.Encoder;
import it.MultimediaInfo;

public class RaceAddAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RaceService raceService;
	private Race race;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private File uploadAngle;
	private String uploadAngleFileName;
	private String uploadAngleContentType;
	

	public String execute() throws IOException {
		uploadFileName = getNo(uploadFileName);
		race.setContent(uploadFileName);
		uploadAngleFileName = getNo(uploadAngleFileName);
		race.setTxt(uploadAngleFileName);
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		if (upload != null) {
            File savefile = new File(new File(realpath), uploadFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(upload, savefile);
            
            //获取视频时长
            Encoder encoder = new Encoder();
            try {
                MultimediaInfo m = encoder.getInfo(savefile);
                int ls = (int) m.getDuration();
                race.setDuration(ls/1000);
            } catch (Exception e) {
               e.printStackTrace();
            }
	    }
		if(uploadAngle != null) {
			File savefile = new File(new File(realpath), uploadAngleFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(uploadAngle, savefile);
		}
		int race_id = raceService.addRace(race);
		List<Race> raceList = raceService.getRaceList();
	    this.request().getSession().setAttribute("races", raceList);
		return SUCCESS;
		
	}
	
	//文件名处理,来解决中文名字无法得到的问题
	public static String getNo(String filename){
		if(filename != null){
			String suffix = filename.substring(filename.lastIndexOf("."));
			SimpleDateFormat sdf=new SimpleDateFormat("yyMMddhhmmss");  
			String nowDate=sdf.format(new java.util.Date());
			String iRandom = Math.round(Math.random()*900 )+100 +""; 
			return nowDate + iRandom + suffix;
		}
		return null;
		 
	}
	
	public RaceService getRaceService() {
		return raceService;
	}
	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public File getUploadAngle() {
		return uploadAngle;
	}

	public void setUploadAngle(File uploadAngle) {
		this.uploadAngle = uploadAngle;
	}

	public String getUploadAngleFileName() {
		return uploadAngleFileName;
	}

	public void setUploadAngleFileName(String uploadAngleFileName) {
		this.uploadAngleFileName = uploadAngleFileName;
	}

	public String getUploadAngleContentType() {
		return uploadAngleContentType;
	}

	public void setUploadAngleContentType(String uploadAngleContentType) {
		this.uploadAngleContentType = uploadAngleContentType;
	}
}
