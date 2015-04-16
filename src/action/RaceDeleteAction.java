package action;

import java.util.List;

import model.Question;
import model.Race;
import service.RaceService;

public class RaceDeleteAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RaceService raceService;
	
	public String execute() {
		int race_id = Integer.parseInt(request().getParameter("race_id"));
		raceService.deleteRaceById(race_id);
		List<Race> raceList = raceService.getRaceList();
		if(raceList != null){
			this.request().getSession().setAttribute("races", raceList);
			return SUCCESS;
		}
		return ERROR;
	}
	
	public RaceService getRaceService() {
		return raceService;
	}
	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}
	

}
