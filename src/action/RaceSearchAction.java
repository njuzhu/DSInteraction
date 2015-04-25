package action;

import java.util.List;
import model.Race;
import service.RaceService;

public class RaceSearchAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RaceService raceService;
	private Race race;
	
	public String execute(){
		
		List<Race> raceSearchList = raceService.getRaceSearchList(race.getName());
		
		if(raceSearchList != null){
			this.request().getSession().setAttribute("raceSearchList", raceSearchList);
			System.out.println("show raceSearchList success");
			return SUCCESS;
		}
		
		return "notExist";
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

	

}
