import java.util.Date;

public class Test {

	// runs testsuite
	public static void main(String[] args){
		test1();
	}
	
	// returns a Date object which lies a certain amount of time units in the future
	static Date in(long units) {
		return new Date(System.currentTimeMillis() + units * 10000);
	}
	
	static void pr(String s){
		System.out.println(s);
	}
	
	// String output if tests are successful;
	// Exception is thrown if workflow doesn't work
	public static void test1() {
		pr("### StrikeTeam Testsuite 2000 by Babz");

		Company doItYourself = new Company("DoItYourself");
		pr("Creating 3 Stores");
		Store s1 = new Store("Lager1");
		Store s2 = new Store("Lager2");
		Store s3 = new Store("HTL");
		pr(s1.toString());
		pr(s2.toString());
		pr(s3.toString());
		pr("... success");
		doItYourself.addStore(s1);
		doItYourself.addStore(s2);
		doItYourself.addStore(s3);
		
		pr("adding productgroups with prizes");
		//productgroups available
		ProductGroup comp = new ProductGroup("Computer");
		ProductGroup monitor = new ProductGroup("Monitor");
		pr(monitor.toString());
		ProductGroup storage = new ProductGroup("Speicherplatten");
		pr(storage.toString());
		ProductGroup components = new ProductGroup("Zubehoer");
		pr(components.toString());
		pr("... success");
		
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
		ProductGroup fp = new ProductGroup("Festplatte");
		ProductGroup mon = new ProductGroup("Monitor");
		pr("adding products");
		fp.addProduct(new Product("HD 100", "Samsung Festplatte", 20, 27, 1));
		fp.addProduct(new Product("HD-1337", "Seagate Festplatte", 15, 25, 1));
		fp.addProduct(new Product("HD 1", "Foo Festplatte", 22, 28, 1));
		mon.addProduct(new Product("HD 100", "Samsung Monitor", 30, 45, 2));
		mon.addProduct(new Product("HD 100", "Belinea Monitor", 28, 35, 2));
		mon.addProduct(new Product("HD 100", "Noname Monitor", 30, 40, 2));
		pr("... success");

		//products available
		TradeItem t1 = new Product("XTreamGeek", "features everything you can imagine", 700, 1080, 60);
		TradeItem t2 = new Product("DreamNerdXT", "when the reality doesn't work out", 670, 950, 55);
		TradeItem t3 = new Product("HyperCipher", "privacy++", 800, 1330, 60);
		comp.addProduct((Product)t1);
		comp.addProduct((Product)t2);
		comp.addProduct((Product)t3);
		TradeItem t4 = new Product("R2D2", "HDReady", 230, 350, 25);
		TradeItem t5 = new Product("R3D3", "FullHD", 280, 470, 30);
		TradeItem t6 = new Product("MasterView", "cristal clear", 300, 520, 30);
		monitor.addProduct((Product)t4);
		monitor.addProduct((Product)t5);
		monitor.addProduct((Product)t6);
		TradeItem t7 = new Product("Ram", "access to the world", 30, 65, 5);
		TradeItem t8 = new Product("Rom", "all you can read", 20, 50, 5);
		TradeItem t9 = new Product("Cache", "best on market", 70, 190, 5);
		storage.addProduct((Product)t7);
		storage.addProduct((Product)t8);
		storage.addProduct((Product)t9);
		TradeItem t10 = new Product("Keyboard", "press any key", 15, 25, 5);
		TradeItem t11 = new Product("Mouse", "never loose the cheese again", 10, 20, 5);
		TradeItem t12 = new Product("CoolingSystem", "Don't be afraid, stay cool", 30, 65, 5);
		components.addProduct((Product)t10);
		components.addProduct((Product)t11);
		components.addProduct((Product)t12);
		TradeItem t13 = new Product("PowerPlotter3", "plots powerfully", 600, 1000, 40);
		TradeItem t14 = new Product("PearNotApple", "the magic of the whole pear", 630, 1200, 40);
		TradeItem t15 = new Product("TEEBook", "powerful portable netbook", 250, 500, 20);
		laptop.addProduct((Product)t13);
		laptop.addProduct((Product)t14);
		laptop.addProduct((Product)t15);
		TradeItem t16 = new Product("UFF123", "1,5TB", 45, 85, 10);
		TradeItem t17 = new Product("BAM654", "1TB", 30, 70, 10);
		internalHardDisc.addProduct((Product)t16);
		internalHardDisc.addProduct((Product)t17);
		TradeItem t18 = new Product("PUH345", "black n beautiful", 45, 80, 10);
		TradeItem t19 = new Product("ARG789", "oag guad", 50, 95, 10);
		externalHardDisc.addProduct((Product)t18);
		externalHardDisc.addProduct((Product)t19);
		
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
		
		Configuration pc = new Configuration("PC");
		pc.addProductGroup(hardDisc, 2);
		pc.addProductGroup(monitor, 5);
		
		doItYourself.addProductGroup(comp);
		doItYourself.addProductGroup(laptop);

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
		s3.deposit(t16, 4); //internal harddisc
		s3.deposit(t4, 5);
		s3.deposit(t5, 50); //monitor
		
		pr("delete a configuration");
		s3.withdraw(t7, 3);
		pr("..success");
		
		pr("order an available configuration");
		ItemCollection order1 = new ItemCollection();
		order1.put(t20, 3);
		Order o1 = new Order(in(10), order1, s2, s2); //available
		pr("... success\nExecute order");
		o1.executeOrder();
		pr("... success");
		
		pr("Order an available product");
		ItemCollection order2 = new ItemCollection();
		order2.put(t21, 1);
		Order o2 = new Order(in(10), order2, s3, s3); //available
		o2.executeOrder();
		pr("... success");
				
		pr("Order something which will be available through another order arriving sooner");
		ItemCollection order4 = new ItemCollection();
		ItemCollection order5 = new ItemCollection();
		order4.put(t22, 4);
		order5.put(t22, 3);
		Order o4 = new Order(in(20), order4, s1, s2);
		Order o5 = new Order(in(30), order5, s2, s3);
		o4.executeOrder();
		o5.executeOrder();
		pr("... success");
		
		pr("Order something not in stock, expecting exception");
		String status = "... failed";
		try {
			ItemCollection order6items = new ItemCollection();
			order6items.deposit(pc, 6);
			new Order(in(0), order6items, s3, s2);
		} catch (Exception e) {
			status = "... success";
		}
		pr(status);

		pr("Build a configuration from parts using cheapest parts");
		t21.buildConfiguration(s3);
		if (s3.getAmount(t21) == 2 &&
			s3.getAmount(t4) == 0) {
			pr("... success");
		} else {
			pr("... failed");
		}
		pr("Works!");
	}
}
