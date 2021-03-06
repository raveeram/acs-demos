package org.gardler.biglittlechallenge.core.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.gardler.biglittlechallenge.core.Constants;
import org.gardler.biglittlechallenge.core.model.GameStatus;
import org.gardler.biglittlechallenge.core.model.PlayerResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.gardler.biglittlechallenge.core.model.GameStatus.State;

/**
 * An object returned via the API whenever the Game Status is needed.
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GameStatusResponse implements Serializable {
	private static final long serialVersionUID = 734742551126798205L;

	private UUID gameUUID;
	private State state = GameStatus.State.Idle;
    private ArrayList<PlayerResultsResponse> playerResults = new ArrayList<PlayerResultsResponse>();
    private String name;
	private List<GameTicket> tickets;
	private int minNumberOfPlayers;
        
    /**
     * Typically the empty constructor will only be used when deserializing.
     */
    public GameStatusResponse() {
    	super();
    }
    
    /**
     * Create a response object from a GameStatus object.
     */
    public GameStatusResponse(GameStatus status) {
    	setGameUUID(status.getGameUUID());
		setState(status.getState());
		setTickets(status.getTickets());
		setMinNumberOfPlayers(status.getMinNumberOfPlayers());
		
		Iterator<PlayerResults> itr = status.getResults().getPlayers().iterator();
		while (itr.hasNext()) {
			PlayerResults playerResult = itr.next();
			PlayerResultsResponse pResponse = new PlayerResultsResponse();
			pResponse.setId(playerResult.getTicket().getPlayerID());
			pResponse.setName(playerResult.getTicket().getPlayerName());
			pResponse.setPoints(playerResult.getPoints());
			playerResults.add(pResponse);
		}
    }
    
	private void setTickets(List<GameTicket> tickets) {
		this.tickets = tickets;
	}
	public List<GameTicket> getTickets() {
		return this.tickets;
	}

	public UUID getGameUUID() {
		return gameUUID;
	}
	public void setGameUUID(UUID gameUUID) {
		this.gameUUID = gameUUID;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public ArrayList<PlayerResultsResponse> getPlayerResults() {
		return playerResults;
	}
	public void setPlayerResults(ArrayList<PlayerResultsResponse> playerResults) {
		this.playerResults = playerResults;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getMinNumberOfPlayers() {
		return minNumberOfPlayers;
	}

	public void setMinNumberOfPlayers(int minNumberOfPlayers) {
		this.minNumberOfPlayers = minNumberOfPlayers;
	}
	
	public String getCoreVersion() {
		return Constants.CORE_VERSION;
	}
	
}
