package action;

import java.util.List;

import model.Race;
import service.RaceService;

public class RaceListAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RaceService raceService;
	
	
	public String execute(){
		List<Race> raceList = raceService.getRaceList();
		
		if(raceList != null){
			this.request().getSession().setAttribute("races", raceList);
			System.out.println("show raceList success");
			return SUCCESS;
		} else {
			return ERROR;
		}
	}


	public RaceService getRaceService() {
		return raceService;
	}


	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}
}
