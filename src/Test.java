
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
		 * - Lager3
		 */
		Company doItYourself = new Company("DoItYourself");
		Store s1 = new Store("Lager1");
		Store s2 = new Store("Lager2");
		Store s3 = new Store("Lager3");
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
		ProductGroup comp = new ProductGroup("Computer");
		ProductGroup monitor = new ProductGroup("Monitor");
		ProductGroup storage = new ProductGroup("Speicherplatten");
		ProductGroup components = new ProductGroup("Zubehoer");
		comp.setProdGroup(monitor);
		comp.setProdGroup(storage);
		comp.setProdGroup(components);
		ProductGroup laptop = new ProductGroup("Laptop");
		ProductGroup hardDisc = new ProductGroup("Festplatten");
		ProductGroup externalHardDisc = new ProductGroup("externeFestplatten");
		ProductGroup internalHardDisc = new ProductGroup("interneFestplatten");
		laptop.setProdGroup(hardDisc);
		hardDisc.setProdGroup(internalHardDisc);
		hardDisc.setProdGroup(externalHardDisc);
		
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
		
		TradeItem t20 = new Configuration("HeroEdition");
		//t20.addProduct();
		
		doItYourself.addProductGroup(comp);
		doItYourself.addProductGroup(laptop);
		
		//s1.deposit(t13, 4);
		
		
		System.out.println("Works!");
		
		
	}
}
