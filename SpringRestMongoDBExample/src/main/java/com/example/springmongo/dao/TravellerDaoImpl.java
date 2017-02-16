package com.example.springmongo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.springmongo.dao.I.TravellerDao;
import com.example.springmongo.model.Traveller;

@Repository
public class TravellerDaoImpl implements TravellerDao {

	public static final String COLLECTION_NAME = "travellers";
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void addTraveller(Traveller traveller) {
		
		if(mongoTemplate.collectionExists(Traveller.class)){
			mongoTemplate.createCollection(Traveller.class);
		}
		
		mongoTemplate.insert(traveller, COLLECTION_NAME);
	}

	@Override
	public List<Traveller> getAllTravellers() {
		return mongoTemplate.findAll(Traveller.class, COLLECTION_NAME);
		
	}

	@Override
	public Traveller getTravellerById(int id) {
		return mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Traveller.class, COLLECTION_NAME);
	}

	@Override
	public Traveller updateTraveller(Traveller traveller) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(traveller.getId()));
		
		Update update = new Update();
		update.set("travellerName", traveller.getTravellerName());
		update.set("travellerCountry", traveller.getTravellerCountry());
		update.set("travellerPassportNumber", traveller.getTravellerPassportNumber());
		
		mongoTemplate.updateFirst(query, update, Traveller.class);
		
		return null;
	}

	@Override
	public Traveller deleteTravellerById(int id) {
		Traveller traveller = getTravellerById(id);

		mongoTemplate.remove(traveller, COLLECTION_NAME);
		
		return traveller;
	}

}
