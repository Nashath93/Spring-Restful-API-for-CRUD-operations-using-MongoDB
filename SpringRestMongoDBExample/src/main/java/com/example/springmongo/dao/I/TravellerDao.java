package com.example.springmongo.dao.I;

import java.util.List;

import com.example.springmongo.model.Traveller;

public interface TravellerDao {

	public void addTraveller(Traveller traveller);
	public List<Traveller> getAllTravellers();
	public Traveller getTravellerById(int id);
	public Traveller updateTraveller(Traveller traveller);
	public Traveller deleteTravellerById(int id);
	
}
