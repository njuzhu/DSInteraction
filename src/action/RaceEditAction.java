package action;

import model.Race;
import service.RaceService;

public class RaceEditAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RaceService raceService;
	
	public String execute(){
		int race_id = Integer.parseInt(request().getParameter("race_id"));
		this.request().getSession().setAttribute("race_id", race_id);
		Race race = raceService.searchRace(race_id);
		this.request().getSession().setAttribute("race", race);
		return SUCCESS;
	}
	
	public RaceService getRaceService() {
		return raceService;
	}
	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}
	

}
