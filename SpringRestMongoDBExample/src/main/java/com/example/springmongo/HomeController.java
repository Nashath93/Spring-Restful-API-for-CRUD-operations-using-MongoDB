package com.example.springmongo;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.springmongo.dao.I.TravellerDao;
import com.example.springmongo.model.Traveller;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private Map<Integer, Traveller> dummyTrevellerData = new HashMap<Integer, Traveller>();

	@Autowired
	private TravellerDao travellerDao;

	public HomeController() {
		dummyTrevellerData.put(1, new Traveller(1, "Nashath", "Sri Lanka", "N5434026"));
		dummyTrevellerData.put(2, new Traveller(2, "Nancy", "USA", "HT65432"));
		dummyTrevellerData.put(3, new Traveller(3, "Rana", "Bangladesh", "GG56332"));
		dummyTrevellerData.put(4, new Traveller(4, "Kumar", "India", "NHH76543"));
	}

	public TravellerDao getTravellerDao() {
		return travellerDao;
	}

	public void setTravellerDao(TravellerDao travellerDao) {
		this.travellerDao = travellerDao;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/travellers", method = RequestMethod.GET)
	@ResponseBody
	public List<Traveller> getAllTravellers() {
		logger.info("Executing getAllTravellers method.......");
		return travellerDao.getAllTravellers();
	}

	@RequestMapping(value = "/traveller/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Traveller getTravellerById(@PathVariable("id") int id) {
		Traveller traveller = travellerDao.getTravellerById(id);

		if (traveller != null) {
			logger.info("Inside getTravellerById method. Returned details: " + traveller.toString());
		} else {
			logger.info("Inside getTravellerById method. Id: " + id + ", NOT FOUND!");
		}

		return traveller;
	}

	/*@RequestMapping(value = "/traveller/ceate", method = RequestMethod.GET)
	public String addTravellerPage(ModelMap model) {
		model.addAttribute("message-title", "add traveller");
		model.addAttribute("messageheading", "Add new Traveller");
		// model.addAttribute("addTraveller", new Traveller());
		return "usercreate";
	}*/

	@RequestMapping(value = "/traveller/addTraveller", method = RequestMethod.POST)
	@ResponseBody
	public Traveller addTraveller(@RequestBody Traveller traveller) {
		if (traveller != null) {
			logger.info("Inside addTraveller method. Added traveller: " + traveller.toString());
			travellerDao.addTraveller(traveller);
			return traveller;
		} else {
			logger.info("Inside addTraveller method. Traveller: " + traveller.toString() + ", NOT FOUND!");
			return null;
		}

	}

	@RequestMapping(value = "/traveller/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Traveller> updateTraveller(@PathVariable("id") int id, @RequestBody Traveller traveller) {
		
		Traveller travellerToUpdate = travellerDao.getTravellerById(id);
		if(travellerToUpdate == null){ 
			logger.info("Inside updateTraveller method. Id: " + id + ", NOT FOUND");
			return new ResponseEntity<Traveller>(HttpStatus.NOT_FOUND);
		}
		
		travellerToUpdate.setTravellerName(traveller.getTravellerName());
		travellerToUpdate.setTravellerCountry(traveller.getTravellerCountry());
		travellerToUpdate.setTravellerPassportNumber(traveller.getTravellerPassportNumber());
		
		travellerDao.updateTraveller(travellerToUpdate);
		
		return new ResponseEntity<Traveller>(travellerToUpdate, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/traveller/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Traveller> deleteTravellerById(@PathVariable("id") int id) {
		Traveller traveller = travellerDao.getTravellerById(id);
		if (traveller != null) {
			travellerDao.deleteTravellerById(id);
			logger.info("Inside deleteTravellerById method. Deleted Traveller: " + traveller.toString());
			return new ResponseEntity<Traveller>(HttpStatus.ACCEPTED);
		} else {
			logger.info("Inside deleteTravellerById method. Id: " + id + ", NOT FOUND!");
			return new ResponseEntity<Traveller>(HttpStatus.NOT_FOUND);
		}

	}

}
