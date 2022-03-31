package Wines.tools;

import Wines.Dao.*;
import Wines.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		PersonDao personDao = PersonDao.getInstance();
		
		UsersDao userDao=UsersDao.getInstance();	
		AdminDao adminDao=AdminDao.getInstance();
		ProfessionalRatingsDao professionalRatingsDao = ProfessionalRatingsDao.getInstance();
		  UserRatingsDao userRatingsDao = UserRatingsDao.getInstance();
		
//		AdministratorsDao administratorsDao = AdministratorsDao.getInstance();
//		BlogUsersDao blogUsersDao = BlogUsersDao.getInstance();
//		BlogPostsDao blogPostsDao = BlogPostsDao.getInstance();
//		BlogCommentsDao blogCommentsDao = BlogCommentsDao.getInstance();
//		ResharesDao resharesDao = ResharesDao.getInstance();
		
		// INSERT objects from our model.
		Person person = new Person("b", "bruce", "chhay");
//		person = personDao.create(person);
//		Person person1 = new Person("b1", "bruce", "chhay");
//		person1 = personDao.create(person1);
//		Person person2 = new Person("b2", "bruce", "chhay");
//		person2 = personDao.create(person2);
		
		//Users user1=new Users();
		
		//insert tasters
		Users user1=new Users("Username4","Firstname1","lastName1","password1","email1","phone1");
		//user1=userDao.create(user1);
		
		//insert admin
		Date date1=new Date();
		Admin admin1=new Admin("name3","Firstname1","lastName1","password1",date1);
		//adminDao.create(admin1);
		
		/**
		 * Read select
		 */
//		Person p1 = personDao.getPersonFromUserName("b");
//		List<Person> pList1 = personDao.getPersonFromFirstName("bruce");
//		System.out.format("Reading person: u:%s f:%s l:%s \n",
//			p1.getUserName(), p1.getFirstName(), p1.getLastName());
//		for(Person p : pList1) {
//			System.out.format("Looping Person: u:%s f:%s l:%s \n",
//				p.getUserName(), p.getFirstName(), p.getLastName());
//		}
		
		//select users
//		Users userByFirstName=userDao.getUserFromUserName("Username4");
//		System.out.println(userByFirstName.toString());
		
		//select admin
		admin1=adminDao.getAdministratorFromUserName("name3");
		System.out.println(admin1);
		
		/**
		 * delete
		 */


		/* Wine Tester */
		WinesDao winesDao=WinesDao.getInstance();
		  List<Wines> list=winesDao.getWinesByWineryName(":Nota Bene");
		  System.out.println("There are " +list.size()+" wines from Nota Bene");
		//  for(Wines w: list) {
		//   System.out.println(w.toString());
		//  }
		  Wines wine1=winesDao.getWinesByTitle(":Nota Bene 2005 Una Notte Red (Washington)");
		  System.out.println("Wines by title from :Nota Bene 2005 Una Notte Red (Washington) "+wine1.toString());
		  
		  
		/* Taster Tester */
		  TastersDao tasterDao = TastersDao.getInstance();
//	    create
	    Tasters taster1 = new Tasters("TIM ATKIN MW","#All about wine");
	    Tasters taster2 = new Tasters("JANCIS ROBINSON MW","#Wine world");
//	    read
	    Tasters t1 = tasterDao.getTasterByTasterName("Alexander Peartree");
	    System.out.format("About this wine expert: name: %s twitter:%s\n", t1.getTasterName(), t1.getTasterTwitter());
//	    update
	    //tasterDao.updateTwitter(t1, "very new");
	    
	    
		/* variety */
	    WineryDao wineryDao = WineryDao.getInstance();
        VarietyDao varietyDao = VarietyDao.getInstance();

//        Winery winery = new Winery("Group");
//        winery = wineryDao.create(winery);
//
//        Variety variety = new Variety("1", new Wines("1"));
//        variety = varietyDao.create(variety);

        Winery w1 = wineryDao.getWineryByWineryName("Nicosia");
        System.out.format("Reading winery: id:%s wn:%s\n", w1.getWineryId(), w1.getWineryName());
        Variety v1 = varietyDao.getVarietyByType("Sideritis");
        System.out.format("Reading variety: type:%s title:%s\n", v1.getType(), v1.getWineTitle());
        
//        wineryDao.delete(winery);
//        varietyDao.delete(variety);
        
        
        
        UserRatings userRating1 = new UserRatings(90, user1, wine1);
        //userRatingsDao.create(userRating1);
        //UserRatings userRating2 = new UserRatings();
        
        ProfessionalRatings professionalRating1 = new ProfessionalRatings(90,taster1,wine1);
//        ProfessionalRatings professionalRating2 = new ProfessionalRatings();
        
        ProfessionalRatings ProfessionalRating = professionalRatingsDao.getProfessionalRatingsById(1);
        System.out.println(ProfessionalRating);
        List<ProfessionalRatings> professionalRating1list= professionalRatingsDao.getProfessionalRatingsByTasterName(taster1.getTasterName());
        for (ProfessionalRatings p : professionalRating1list) {System.out.println(p);}
        
        	
        UserRatings userRatings = userRatingsDao.getUserRatingsById(2);
        List<UserRatings> userRatings2 = userRatingsDao.getUserRatingsByUserName(user1.getUserName());
        for(UserRatings u : userRatings2) {
        	System.out.println(u);
        }
        System.out.println(userRatings);
        
        
        
        /*------------ProfessionalReviewsTester-----------*/
        ProfessionalReviewsDao professionalReviewsDao = ProfessionalReviewsDao.getInstance();
        ProfessionalReviews professionalReview1 = professionalReviewsDao.getProfessionalReviewFromProfessionalReviewId(1);
        ProfessionalReviews professionalReview2 = professionalReviewsDao.getProfessionalReviewFromProfessionalReviewId(2);
        System.out.println(professionalReview1.toString());
        System.out.println(professionalReview2.toString());
        
        /*------------UserReviewsTester-----------*/
        UserReviewsDao userReviewsDao = UserReviewsDao.getInstance();
        UserReviews ur1 = new UserReviews(date1, "content", user1, wine1);
        //userReviewsDao.create(ur1);
        UserReviews userReviews1 = userReviewsDao.getUserReviewFromUserReviewId(1);
//        UserReviews userReviews2 = userReviewsDao.getUserReviewFromUserReviewId(2);
        System.out.println(ur1.toString());
        System.out.println(userReviews1.toString());
        //System.out.println(userReviews2.toString());
        
        
        /*------------Location-----------*/
  
        	  
    	LocationDao locationDao = LocationDao.getInstance();
//    	Location location = new Location("US","WA","Seattle","SLU", w1);
//    	location = locationDao.create(location);
//        	  
//	  location = locationDao.updateLocation(location, "US", "WA","Seattle","NEU", "XXXwinery");
	  Location location = locationDao.getLocationByWineTitle(":Nota Bene 2008 Miscela Red (Columbia Valley (WA))");
	  System.out.format("Reading location: c:%s p:%s r1:%s r2:%s wn:%s \n",
	  location.getCountry(), location.getProvince(), location.getRegin1(), location.getRegin2(), location.getWineTitle().getWineTitle());
	  
	  //location = locationDao.delete(location);
	}
}
