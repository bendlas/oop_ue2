import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Company/Store Tree:
		 * -------------------
		 * DoItYourself
		 * - Lager1
		 * - Lager2
		 * - HerwigsTraumland
		 */
		Company doItYourself = new Company("DoItYourself");
		Store s1 = new Store("Lager1");
		Store s2 = new Store("Lager2");
		Store s3 = new Store("HerwigsTraumland");
		doItYourself.addStore(s1);
		doItYourself.addStore(s2);
		doItYourself.addStore(s3);
		
		/*
		 * Product(group) Tree:
		 * --------------------
		 * Computer
		 * - XTremGeek
		 * - DreamNerdXT
		 * - HyperCipher
		 * 	- Monitor
		 * 		- R2D2
		 * 		- R3D3
		 * 		- MasterView
		 * 	- Speicherplatten
		 * 		- Ram
		 * 		- Rom
		 * 		- Cache
		 * 	- Zubehoer
		 * 		- Keyboard
		 * 		- Mouse
		 * 		- CoolingSystem
		 * Laptop
		 * - PowerPlotter3
		 * - PearNotApple
		 * - TEEBook
		 * 	- Festplatten
		 * 		- interne Festplatten
		 * 			- UFF123
		 * 			- BAM654
		 * 		- externe Festplatten
		 * 			- PUH345
		 * 			- ARG789
		 */
		
		//productgroups available
		ProductGroup comp = new ProductGroup("Computer");
		ProductGroup monitor = new ProductGroup("Monitor");
		ProductGroup storage = new ProductGroup("Speicherplatten");
		ProductGroup components = new ProductGroup("Zubehoer");
		comp.addSubProductGroup(monitor);
		comp.addSubProductGroup(storage);
		comp.addSubProductGroup(components);
		ProductGroup laptop = new ProductGroup("Laptop");
		ProductGroup hardDisc = new ProductGroup("Festplatten");
		ProductGroup externalHardDisc = new ProductGroup("externeFestplatten");
		ProductGroup internalHardDisc = new ProductGroup("interneFestplatten");
		laptop.addSubProductGroup(hardDisc);
		hardDisc.addSubProductGroup(internalHardDisc);
		hardDisc.addSubProductGroup(externalHardDisc);
		
		//products available
		TradeItem t1 = new Product("XTreamGeek", "features everything you can imagine", 700, 1080, 60);
		TradeItem t2 = new Product("DreamNerdXT", "when the reality doesn't work out", 670, 950, 55);
		TradeItem t3 = new Product("HyperCipher", "privacy++", 800, 1330, 60);
		comp.setProduct((Product)t1);
		comp.setProduct((Product)t2);
		comp.setProduct((Product)t3);
		TradeItem t4 = new Product("R2D2", "HDReady", 230, 350, 25);
		TradeItem t5 = new Product("R3D3", "FullHD", 280, 470, 30);
		TradeItem t6 = new Product("MasterView", "cristal clear", 300, 520, 30);
		monitor.setProduct((Product)t4);
		monitor.setProduct((Product)t5);
		monitor.setProduct((Product)t6);
		TradeItem t7 = new Product("Ram", "access to the world", 30, 65, 5);
		TradeItem t8 = new Product("Rom", "all you can read", 20, 50, 5);
		TradeItem t9 = new Product("Cache", "best on market", 70, 190, 5);
		storage.setProduct((Product)t7);
		storage.setProduct((Product)t8);
		storage.setProduct((Product)t9);
		TradeItem t10 = new Product("Keyboard", "press any key", 15, 25, 5);
		TradeItem t11 = new Product("Mouse", "never loose the cheese again", 10, 20, 5);
		TradeItem t12 = new Product("CoolingSystem", "Don't be afraid, stay cool", 30, 65, 5);
		components.setProduct((Product)t10);
		components.setProduct((Product)t11);
		components.setProduct((Product)t12);
		TradeItem t13 = new Product("PowerPlotter3", "plots powerfully", 600, 1000, 40);
		TradeItem t14 = new Product("PearNotApple", "the magic of the whole pear", 630, 1200, 40);
		TradeItem t15 = new Product("TEEBook", "powerful portable netbook", 250, 500, 20);
		laptop.setProduct((Product)t13);
		laptop.setProduct((Product)t14);
		laptop.setProduct((Product)t15);
		TradeItem t16 = new Product("UFF123", "1,5TB", 45, 85, 10);
		TradeItem t17 = new Product("BAM654", "1TB", 30, 70, 10);
		internalHardDisc.setProduct((Product)t16);
		internalHardDisc.setProduct((Product)t17);
		TradeItem t18 = new Product("PUH345", "black n beautiful", 45, 80, 10);
		TradeItem t19 = new Product("ARG789", "oag guad", 50, 95, 10);
		externalHardDisc.setProduct((Product)t18);
		externalHardDisc.setProduct((Product)t19);
		
		//build Configurations
		Configuration t20 = new Configuration("HeroEdition");
		t20.addTradeItem(t10, 1);
		t20.addTradeItem(t11, 1);
		t20.addTradeItem(t7, 3);
		t20.addTradeItem(t9, 1);
		t20.addTradeItem(t5, 1);
		Configuration t21 = new Configuration("Superman");
		t21.addProductGroup(monitor, 5);
		Configuration t22 = new Configuration("Wonderwoman");
		t22.addProductGroup(storage, 3);
		
		doItYourself.addProductGroup(comp);
		doItYourself.addProductGroup(laptop);
		
		//fill stores
		/*
		 * s1:
		 * - laptop t13, 4
		 * - laptop t14, 3
		 * - laptop t15, 3
		 * - monitor t4, 6
		 * - monitor t6, 4
		 * - internalHarddisc t17, 2
		 * - pG storage t22, 5
		 * s2:
		 * - comp t1, 3
		 * - comp t2, 3
		 * - comp t3, 3
		 * - monitor t5, 6
		 * - mouse t11, 7
		 * - internalHarddisc t16, 4
		 * - config t20, 4
		 * s3:
		 * - pG monitor t21, 1
		 * - keyboard t10, 4
		 * - rom t8, 3
		 * - ram t7, 5
		 * - cache t9, 7
		 * - coolingSystem t12, 5
		 * - externalHarddisc t18, 5
		 * - externalHarddisc t19, 5
		 * - monitor t5, 3
		 */
		s1.deposit(t13, 4); //laptop
		s1.deposit(t14, 3); //laptop
		s1.deposit(t15, 3); //laptop
		s1.deposit(t4, 6); //monitor
		s1.deposit(t6, 4); //monitor
		s1.deposit(t17, 2); //internal harddisc
		s1.deposit(t22, 5); //config: pG storage
		s2.deposit(t1, 3); //comp
		s2.deposit(t2, 3); //comp
		s2.deposit(t3, 3); //comp
		s2.deposit(t5, 6); //monitor
		s2.deposit(t11, 7); //mouse
		s2.deposit(t16, 4); //internal harddisc
		s2.deposit(t20, 4); //config: 5, 7, 9, 10, 11
		s3.deposit(t21, 1); //config: pG monitor
		s3.deposit(t10, 4); //keyboard
		s3.deposit(t8, 3); //Rom
		s3.deposit(t7, 5); //Ram
		s3.deposit(t9, 7); //Cache
		s3.deposit(t12, 5); //cooling system
		s3.deposit(t18, 5); //external harddisc
		s3.deposit(t19, 5); //external harddisc
		s3.deposit(t5, 3); //monitor
		
		Map<TradeItem, Integer> order1 = new HashMap<TradeItem, Integer>();
		order1.put(t20, 3);
		Order o1 = new Order(new Date(20100311), order1, s2, s2); //available
		o1.executeOrder();
		o1.delete();
		
		Map<TradeItem, Integer> order2 = new HashMap<TradeItem, Integer>();
		order2.put(t21, 1);
		Order o2 = new Order(new Date(20100311), order2, s3, s3); //available
		o2.executeOrder();
		o2.delete();
		
		Map<TradeItem, Integer> order3 = new HashMap<TradeItem, Integer>();
		order3.put(t20, 2);
		Order o3 = new Order(new Date(20100311), order2, s2, s2); //available by end of day
		o3.executeOrder();
		
		s2.deposit(t20, 4); //by end of day, 3 left
		o3.delete();
				
		Map<TradeItem, Integer> order4 = new HashMap<TradeItem, Integer>();
		order4.put(t22, 4);
		Order o4 = new Order(new Date(20100311), order2, s3, s3);
		o4.executeOrder();
		
		System.out.println("Works!");
		
		
	}
}
