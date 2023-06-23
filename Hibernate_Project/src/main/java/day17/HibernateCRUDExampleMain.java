package day17;
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateCRUDExampleMain {
	private static void testEagerLoading() {
		try(
				SessionFactory hibernateFactory = 
					HibernateUtils.getSessionFactory();
				Session hibernateSession = hibernateFactory.openSession()
				){
				Class<Restaurant> restaurantClass = Restaurant.class;
				Serializable ID = 101;//Serializable ID = new Integer(102);
				Restaurant foundRestaurant = hibernateSession.load(restaurantClass, ID);
				System.out.println(foundRestaurant.getRestaurantId());
				System.out.println(foundRestaurant.getClass().getName());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	private static void testLazyLoading() {
		try(
				SessionFactory hibernateFactory = 
					HibernateUtils.getSessionFactory();
				Session hibernateSession = hibernateFactory.openSession()
				){
				Class<Restaurant> restaurantClass = Restaurant.class;
				Serializable ID = 501;//Serializable ID = new Integer(102);
				Restaurant foundRestaurant = hibernateSession.load(restaurantClass, ID);
				System.out.println(foundRestaurant.getRestaurantId());
				System.out.println(foundRestaurant.getRestaurantId());
				System.out.println(foundRestaurant.getRestaurantId());
				System.out.println(foundRestaurant.getRestaurantId());
				System.out.println(foundRestaurant.getRestaurantId());
				System.out.println(foundRestaurant.getName());
				System.out.println(foundRestaurant.getClass().getName());
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	private static void deleteRestaurant() {
		//This method deletes some restaurant.
		//Obtain the restaurant against ID
		//Obtain and Start the transaction
		//Delete the object
		//Commit the Transaction
		try(
				SessionFactory hibernateFactory = 
					HibernateUtils.getSessionFactory();
				Session hibernateSession = hibernateFactory.openSession()
				){
				Class<Restaurant> restaurantClass = Restaurant.class;
				Serializable ID = 102;
				Restaurant foundRestaurant = hibernateSession.load(restaurantClass, ID);
				
				Transaction hibernateTransaction = hibernateSession.beginTransaction();
						hibernateSession.delete(foundRestaurant);
				hibernateTransaction.commit();
				System.out.println("Record deleted");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	private static void updateRestaurant() {
		//This method updates the information of some restaurant.
		//Obtain the restaurant against ID
		//Obtain and Start the transaction
		//Modify the values using Setters
		//Commit the Transaction
		
		try(
				SessionFactory hibernateFactory = 
					HibernateUtils.getSessionFactory();
				Session hibernateSession = hibernateFactory.openSession()
				){
				Class<Restaurant> restaurantClass = Restaurant.class;
				Serializable ID = 101;
				Restaurant foundRestaurant = hibernateSession.load(restaurantClass, ID);
				
				Transaction hibernateTransaction = hibernateSession.beginTransaction();
						foundRestaurant.setName("Khana Khazana");
						foundRestaurant.setBranchCount(15);
				hibernateTransaction.commit();
				System.out.println("Record updated");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	private static void retrieveRestaurant() {
		//This method retrieves one restaurant against ID.
		try(
				SessionFactory hibernateFactory = 
					HibernateUtils.getSessionFactory();
				Session hibernateSession = hibernateFactory.openSession()
				){
				Class<Restaurant> restaurantClass = Restaurant.class;
				Serializable ID = 102;//Serializable ID = new Integer(102);
				Restaurant foundRestaurant = hibernateSession.load(restaurantClass, ID);
				//select * from restaurant_details where rest_id = 102
				System.out.println(foundRestaurant);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void createRestaurant() {
		Configuration conf = new Configuration();
		conf = conf.configure();
		
		SessionFactory hibernateFactory = conf.buildSessionFactory();
		
		Session hibernateSession = 	hibernateFactory.openSession();
		
		Restaurant rst = new Restaurant(102, "Bawarchi", "Indian", 5);
		
		Transaction hibernateTransaction = 
											hibernateSession.beginTransaction();
				hibernateSession.save(rst);
		hibernateTransaction.commit();	
		
		hibernateSession.close();
		hibernateFactory.close();
		System.out.println("Record added.");
	}

	public static void main(String[] args) {
	
		//createRestaurant();
		//retrieveRestaurant();
		//updateRestaurant();
		//deleteRestaurant();
		
		//testLazyLoading();
		testEagerLoading();

	}


}
