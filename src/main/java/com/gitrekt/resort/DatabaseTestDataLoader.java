
package com.gitrekt.resort;

import com.gitrekt.resort.hibernate.HibernateUtil;
import com.gitrekt.resort.model.UsState;
import com.gitrekt.resort.model.entities.Bill;
import com.gitrekt.resort.model.entities.Booking;
import com.gitrekt.resort.model.entities.Employee;
import com.gitrekt.resort.model.entities.Guest;
import com.gitrekt.resort.model.entities.Room;
import com.gitrekt.resort.model.entities.RoomCategory;
import com.gitrekt.resort.model.entities.GuestFeedback;
import com.gitrekt.resort.model.entities.MailingAddress;
import com.gitrekt.resort.model.entities.Package;
import com.gitrekt.resort.model.services.GuestFeedbackService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.scene.image.Image;
import javax.persistence.EntityManager;

/**
 * This class is responsible for preparing the database with test data for the
 * program to operate on.
 * 
 * It's a temporary solution and pretty lame, but it's the fastest way to solve
 * our problem and keep from hindering further progress. If time permits, we
 * will try to migrate to a more permanent solution like a stored SQL script,
 * but there isn't really a reason to do that right now. 
 * 
 * This class is just meant to be a quick and dirty solution to the problem.
 * 
 * It's also not finished. It doesn't create half of the data we need yet.
 */
public class DatabaseTestDataLoader {
    
    public static void initializeTestData() {
        
        // Populate database with data on all of the rooms available
        
        // Uncomment this line later once RoomService is created
        // RoomService roomService = new RoomService()
        
        Image placeholderImg = new Image("/images/temporary_hotel_room_image_placeholder.jpg");
        
        List<Room> rooms = new ArrayList<>();
        
        RoomCategory basic = new RoomCategory(
            "Basic",
            "This room is as basic as you are. Includes complimentary bedbugs and various mystery stains on the sheets.",
            placeholderImg,
            "Beds not provided"               
        );
        
        RoomCategory familyBasic = new RoomCategory(
            "Family Basic",
            "With the Family basic room, you can be treated like dirt, but now with the whole family!",
            placeholderImg,
            "2 Queen, 2 twin"               
        );
        
        RoomCategory luxury = new RoomCategory(
            "Luxury",
            "Because in 2017 being able to go to a resort at all is a luxury. You should be thanking us.",
            placeholderImg,
            "2 Queen"               
        );
        
        RoomCategory luxuryFamily = new RoomCategory(
            "Luxury Family",
            "This room is almost bearable. Too bad you have kids and you won't be able to enjoy it.",
            placeholderImg,
            "2 Queen, 2 twin"               
        );
        
        RoomCategory king = new RoomCategory(
            "King",
            "The room that says, 'I'm better than everyone else, and I want them to know it.'",
            placeholderImg,
            "2 King"               
        );
        
        EntityManager entityManager = HibernateUtil.getEntityManager();
        
        // Put the data in the database
        entityManager.getTransaction().begin();
        
        // 1st floor rooms don't exist
        
        // 2nd floor rooms
        for(int roomNumber = 200; roomNumber < 200+50; roomNumber++) {
            entityManager.persist(new Room(String.valueOf(roomNumber), basic));
        }
        
        // 3rd floor rooms
        for(int roomNumber = 300; roomNumber < 300+50; roomNumber++) {
            Room room = new Room(String.valueOf(roomNumber), basic);
            entityManager.persist(room);
        }
        
        // 4th floor rooms
        for(int roomNumber = 400; roomNumber < 400+40; roomNumber++) {
            Room room = new Room(String.valueOf(roomNumber), familyBasic);
            entityManager.persist(room);
        }
        
        // 5th floor rooms
            for(int roomNumber = 500; roomNumber < 500+30; roomNumber++) {
                Room room = new Room(String.valueOf(roomNumber), luxury);
                entityManager.persist(room);
            }
        
        // 6th floor rooms
        for(int roomNumber = 600; roomNumber < 600+20; roomNumber++) {
            Room room = new Room(String.valueOf(roomNumber), luxuryFamily);
            entityManager.persist(room);
        }
        
        // 7th floor rooms
        for(int roomNumber = 700; roomNumber < 700+10; roomNumber++) {
            Room room = new Room(String.valueOf(roomNumber), king);
            entityManager.persist(room);
        }
        
        // Generate package data.
        Package package1 = new Package("Loch-Ness monster viewing", 3.50);
        Package package2 = new Package("Basement tour", 10.00);
        Package package3 = new Package("Drug cartel abduction experience", 650.00);
        Package package4 = new Package("Surfing with sharks", 580.99);
        
        entityManager.persist(package1);
        entityManager.persist(package2);
        entityManager.persist(package3);
        entityManager.persist(package4);
        
        // Generate test guest data
        Guest g1 = new Guest("Chris", "Mailldfghoux", "mailloux.cl@gmail.com", "239-242-4256", new MailingAddress("525 fake way", null, "33969", UsState.FLORIDA, "United States"));
        Guest g2 = new Guest("Chrsfgmis", "Mailloux", "mailsfghux.cl@gmail.com", "239-242-4256", new MailingAddress("525 fake way", null, "33969", UsState.FLORIDA, "United States"));
        Guest g3 = new Guest("Chris", "Mailldfghoux", "maillsfghsfghsoux.cl@gmail.com", "239-242-4256", new MailingAddress("525 fake way", null, "33969", UsState.FLORIDA, "United States"));
        Guest g4 = new Guest("Chrawetis", "Mailloux", "maillojytfkdfux.cl@gmail.com", "239-242-4256", new MailingAddress("525 fake way", null, "33969", UsState.FLORIDA, "United States"));
        
        entityManager.persist(g1);
        entityManager.persist(g2);
        entityManager.persist(g3);
        entityManager.persist(g4);
        
        // Generate test booking data
        Calendar testCalendar = new GregorianCalendar();
        Date d1 = testCalendar.getTime();
        testCalendar.add(Calendar.DATE, 2);
        Date d2 = testCalendar.getTime();
        
        List<Package> testBookingPackages = new ArrayList<Package>();
        testBookingPackages.add(package1);
        testBookingPackages.add(package2);
        
        Booking b = new Booking(g1, d1, d2, new Bill(), null, testBookingPackages, null);
        
        entityManager.persist(b);
        
        // Load test employee data
        Employee e1 = new Employee(1L, "gitrekt", true, "Chris", "Mailloux");
        Employee e2 = new Employee(2L, "bassface", false, "Chris", "Kael");
        
        entityManager.persist(e1);
        entityManager.persist(e2);
        
        entityManager.getTransaction().commit();
        
        // Don't forget to close the entityManager when done with it
        entityManager.close();
        
        // Load test feedback data
        GuestFeedbackService s = new GuestFeedbackService();
        s.createNewGuestFeedback(new GuestFeedback("You suck.", "mailloux.cl@gmail.com"));
        s.createNewGuestFeedback(new GuestFeedback("You suck a lot.", "mailloux.cl@gmail.com"));
        s.createNewGuestFeedback(new GuestFeedback("You're the worst programmer ever and this simple feature took you all night to implement.", "mailloux.cl@gmail.com"));
        s.createNewGuestFeedback(new GuestFeedback("You're bad and you should feel bad.", "mailloux.cl@gmail.com"));
        
        // TODO: Room pricing data  
    }
    
}