/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpooling_package;

import static carpooling_package.Subscribe.CancelSubscaibe;


/**
 *

 */


/** Details for class route  */
class Route{
   String SrartLocation;
   String EndLocation;
   
   /** constructor for class route with three parameters 
    *@param SrartLocation is start point    
    *@param EndLocation is end point 
    */
   public Route(String SrartLocation,String EndLocation){
       this.SrartLocation = SrartLocation;
       this.EndLocation = EndLocation;
   }
}
//------------------------------------------------------------------------------
/**  */
class Car {
    int IdCar;
    public int NumOfTrips;
    Route route;
    final int MaxCapacity = 4;
    protected String DriverName;
    public int count;
    static Car car;
   /** other constructor for class car 
    *@param IdCar for add in Id car
    *@param NumOfTrips for add in NumOfTrips
    *@param route for add in NumOfTrips
    *@param DriverName  for add in NumOfTrips
    */
        
    private Car(int IdCar,int NumOfTrips,Route route,String DriverName){

        this.route = route;
        this.IdCar = IdCar;
        this.NumOfTrips = NumOfTrips; 
        this.DriverName = DriverName;
    }
    public static Car getcar(int idCar,int numOfTrips,Route rroute,String driverName){
        if(car ==null){
            car = new Car( idCar,numOfTrips,rroute,driverName);
        }
        return car;
    }
    public static void finishcar(){
        car=null;
    }
   /** Function for get id of car
    * @return IdCar for use it in next 
    */
    public int getIdCar() {
        return IdCar;
    }
    void DisplayAllTrips(){
                System.out.println("    Car code : " + IdCar);
                System.out.println("    Your Traffic : from  " + route.SrartLocation + "   to   " +route.EndLocation);
                System.out.println("    The name of the driver : " + DriverName);
                System.out.println("    The number of times a car goes on the road : " + NumOfTrips);
                System.out.println("----------------------------------------------------------------");
    }
}
//------------------------------------------------------------------------------

/** class ticket */
class Ticket{
    int IdTicket;
    Car car;
    double Price ;

//    int CodeCar;
    public static int counter;
  
    boolean IsBooked = false;

    public void setPrice(double Price) {
        this.Price = Price;
    }
    //---------------------------------------------
/**for get price of ticket
 *@return price 
 */    
    public double getPrice() {
        return Price;
    }
    //---------------------------------------------
    Ticket(Car car){
        this.car = car;
        car.count++;
        IsBooked = true;
        counter++;
        IdTicket =counter;
        Price = 100f;
    }
} 
//------------------------------------------------------------------------------
interface rewarded {
    void Reward();
}
class getreward implements rewarded  {
        Ticket ticket;
    @Override
    /**this for first trip for passenger take for free */
    public void Reward() {
            ticket.Price=0;
    }
}

abstract class Passenger {

    Ticket ticket;
    Route route;
    Car car;
    private String PassengerName; 
    private int PassengerId;
    private int PassengerAge;
    
    boolean subscriber = false;
    
    double PriceAfterDiscount=0;
/** constructor for class passenger with three parameters 
 * @param PassengerName for booked name
 * @param PassengerAge booked age
 * @param route for select location 
 */
    
    public Passenger(String PassengerName,int PassengerAge,Route route){
        this.PassengerName = PassengerName;
        this.PassengerAge = PassengerAge;
        this.route = route;
        
    }
    //-----------------------------------------------
    
/** other constructor for class passenger with four parameters for make overriding
 * constructor for class passenger with three parameters 
 * @param PassengerName for pass it to other constructor
 * @param PassengerAge for pass it to other constructor
 * @param route for pass it to other constructor
 * @param PassengerId code of a passenger
 */
    public Passenger(String PassengerName,int PassengerId,int PassengerAge,Route route){
        this(PassengerName,PassengerAge,route);
        this.PassengerId = PassengerId;
    }     
    //---------------------------------------------

/** Function for get passenger name
 * @return PassengerName for use it in next 
 */
    public String getPassengerName() {
        return PassengerName;
    }

/** Function for get passenger id
 * @return  PassengerId for use it in next 
 */
    public int getPassengerId() {
        return PassengerId;
    }
/** Function for get passenger age
    *@return PassengerAge for use it in next 
 */
     public int getPassengerAge() {
        return PassengerAge;
    }
    //---------------------------------------------
    abstract double Discount(Ticket ticket);

/** Function subscribe make here exception for sure the passenger is subscriber or non-subscriber
 *@param xx for check
 */
    abstract public  void subscripe( boolean xx);
    
    public void Complaint(String complaint){
         try{
                throw new Exceptions(complaint);
            }
        catch(Exceptions e){
            System.out.print("Dear! ( "+ getPassengerName()+":your Complaint is arived Arrived so sorry !). ---->>>\t");
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------------");
        }
    }
/** search about route is found or not found
 * @param route for select start point and end point
 * @param car[] for get for all available cars for check any one will go in route
 * @return boolean for end check and know the route is found or not found
  */    
    final boolean SearchForRoute(Route route , Car car[]) {
          for(int i=0;i<car.length;i++){
            if(car[i].route==route){
                return true;
            }
        }
        return false;
    }
    
/** pass the route and array cars to function search     
 * @param route for select start point and end point
 * @param car[] for get for all available cars for check any one will go in route   
 * @return boolean for end check and know the car is found space or not found space
 */
    
   final boolean ReserveTicket(Route route ,Car car[]){
        if(SearchForRoute(route,car)){
            for(int i=0;i<car.length;i++){
                if(car[i].MaxCapacity > car[i].count){
                    System.out.println("    Yes,you will go.");
                     ticket = new Ticket(car[i]);
                     Display(ticket);
                     return true;
                }
            }
         }
        System.out.println("Sorry! Mr  "+(PassengerName)+"  this route in not found");
                    System.out.println("    Passenger id : "+getPassengerId());
                    System.out.println("    Passenger Name : "+getPassengerName());
                    System.out.println("    Passenger Age : "+getPassengerAge());
        System.out.println("----------------------------------------------------------------");
        return false;
   }
   final void Display(Ticket tticket){
              System.out.println("Passenger information: ");    
                    
                    System.out.println("    Passenger idTicket : "+ticket.IdTicket); 
                    System.out.println("    Price of Tiecket : " + Discount(tticket));
         
                    System.out.println("    Passenger id : "+getPassengerId());
                    System.out.println("    Passenger Name : "+getPassengerName());
                    System.out.println("    Passenger Age : "+getPassengerAge());
                    System.out.println("----------------------------------------------------------------");                    
    }
    
}
/** class for subscriber passengers for get discount 50% on trip*/
class Subscribe extends Passenger{
    public Subscribe(String PassengerName, int PassengerId, int PassengerAge, Route route) {
        super(PassengerName, PassengerId, PassengerAge, route);
                subscriber = true;
    }
    
    //--------------------------------------------------
    @Override
    /** function subscribe for get discount 50% on trip*/
    public double Discount(Ticket ticket) {
          PriceAfterDiscount = ticket.getPrice()*0.5;
          return PriceAfterDiscount;
    }
    //--------------------------------------------------
    /**function for if subscriber want the cancel his subscribe 
     *@param passenger for convert the passenger to non-subscribe and pass the passenger
     */
    public static void CancelSubscaibe(Passenger passenger){
           passenger.subscriber = false;
           NonSubscribe non = new NonSubscribe(passenger.getPassengerName(),passenger.getPassengerId(),passenger.getPassengerAge(),passenger.route);
           System.out.println("Mr: " +passenger.getPassengerName()+"your subscribe is canceled!done.");
    }

    @Override
    public final void subscripe(boolean xx) {
        try{
            if(xx)  {
            //    xx=true;
                throw new Exceptions("you is already!!!subscirper passenger !");
            }
        }catch(Exceptions e){
            System.out.print("Dear! ( "+ getPassengerName()+").\t");
            System.out.println("Error!"+e.getMessage());
            System.out.println("--------------------------------------------------");
        }
    } 
}
/** class NonSubscriber passenger */
class NonSubscribe extends Passenger{
    public NonSubscribe(String PassengerName, int PassengerId, int PassengerAge, Route route) {
        super(PassengerName, PassengerId, PassengerAge, route);
                subscriber = false;

    }    
    //---------------------------------------------------
    @Override
    double Discount(Ticket ticket) {
        if(getPassengerAge() >=  40&&getPassengerAge() <  60)//Discount 20%
             PriceAfterDiscount +=  (ticket.getPrice() - ticket.getPrice()*0.2);
        else if(getPassengerAge()>=60)//Discount 40% 
             PriceAfterDiscount +=  (ticket.getPrice() - ticket.getPrice()*0.4);
        else
            PriceAfterDiscount += ticket.getPrice();
        
        return PriceAfterDiscount;
        }
    //--------------------------------------------------- 

    @Override
    public final void subscripe(boolean xx) {
        try{
            if(!xx)  {
            //    xx=true;
                throw new Exceptions("You is already!!!unsubscirper passenger !");
            }
        }catch(Exceptions e){
            System.out.print("Dear! ( "+ getPassengerName()+").\t");
            System.out.println("Error!"+e.getMessage());
            System.out.println("--------------------------------------------------");
        }
    } 
}


/** class Exception for handling things is non-Logical */
class Exceptions extends Exception{
    public Exceptions(String messag){
        super(messag);
    }
}


/** for main */

public class CarPooling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Route route1,route2,route3,route4;
        route1 = new Route("alex","cario");    
        route2 = new Route("sharm El-Sheikh","Mersa Matruh");
        route3 = new Route("Giza","El-Harm");
        route4 = new Route("Tahrir","Ramses");

        Car car[]= new Car[3];
        car[0] = Car.getcar(1,2,route1,"Hisham");
        Car.finishcar();
        car[1] = Car.getcar(2,1,route2,"Omar");
        Car.finishcar();
        car[2] = Car.getcar(3,4,route3,"Mahmoud");
        

        Passenger passenger[] = new Passenger[4];
        passenger[0] = new Subscribe("Ktop",1,30,route1);  
        passenger[1] = new Subscribe("Nabil",2,45,route4);
        passenger[2] = new NonSubscribe("Abdo",3,50,route2);  
        passenger[3] = new NonSubscribe("Karem",4,65,route3);  
       
        
        
        passenger[0].ReserveTicket(route1,car);
        passenger[1].ReserveTicket(route4,car);
        passenger[2].ReserveTicket(route2,car);
        passenger[3].ReserveTicket(route3,car);
       
        
         
        System.out.println("the next For use the exceptions!!.subscribers, they make a second subscription, and non-subscribers, and subscribers, cancel their subscription by default");
        System.out.println("-----------------------------------------------------------");
        
        for(int i =0;i < passenger.length;i++){
                passenger[i].subscripe(passenger[i].subscriber);
            }
      
        //now abdo become subscriber
        passenger[2] = new Subscribe("Abdo",3,50,route2);  
        passenger[2].subscripe(passenger[2].subscriber);
  
        //now Nabil become non subscriber
        CancelSubscaibe(passenger[1]);
        passenger[1] = new NonSubscribe("Nabil",2,45,route4);
        
        System.out.println("Avaiable Cars : ");
        for(int i=0;i<car.length;i++){
                 car[i].DisplayAllTrips();
        }
        
        System.out.println("the next For use the exceptions!!.subscribers, they make a second subscription, and non-subscribers, and subscribers, cancel their subscription by default");
        System.out.println("-----------------------------------------------------------");
        
        for(int i =0;i < passenger.length;i++){
                passenger[i].subscripe(passenger[i].subscriber);
            }
        
        passenger[3].Complaint("the driver received me late");
    }
}
