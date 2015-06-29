import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Testing {
	
	@Test
	public void testGetName(){
		AmusementPark HolidayWorld = new AmusementPark("Holiday World", 50, 50, 10);
		String name = "Holiday World";
		assertEquals(name, HolidayWorld.getName());
	}
	
	@Test
	public void testCreatLink(){
		AmusementPark holidayWorld = new AmusementPark("Holiday World", 50, 50, 10);
		AmusementPark  disneyWorld= new AmusementPark("Disney World", 90, 20, 9);
		//Links link = new Links(holidayWorld, disneyWorld);
//		System.out.println(link.getDistance());
//		System.out.println(link.getTime());
//		assertEquals(name, HolidayWorld.getName());
	}
	
	@Test
	public void testLinks(){
		AmusementPark HolidayWorld = new AmusementPark("Holiday World", 50, 50, 10);
		AmusementPark DisneyLand = new AmusementPark("Disneyland", 5, 40, 8);
		//Links holDisConnection = new Links (HolidayWorld, DisneyLand);
//		HolidayWorld.addLink(holDisConnection);
//		DisneyLand.addLink(holDisConnection);
//		
//		assertEquals(holDisConnection.getDistance(), HolidayWorld.getLinks().get(0).getDistance());
		assertEquals(HolidayWorld.getLinks().get(0).getDistance(), DisneyLand.getLinks().get(0).getDistance());
	}
	
	@Test
	public void testAll(){
		AmusementPark HolidayWorld = new AmusementPark("Holiday World", 579, 285, 10);
		AmusementPark DisneyWorld = new AmusementPark("Disney World", 680, 460, 9);
		AmusementPark DisneyLand = new AmusementPark("Disneyland", 105, 328, 8);
		AmusementPark CedarPoint = new AmusementPark("Cedar point", 627, 218, 4);
		AmusementPark ConeyIsland = new AmusementPark("Coney Island", 756, 208, 2);
		AmusementPark SixFlagsOverTexas = new AmusementPark("Six Flags over Texas", 416, 395, 7);
		AmusementPark WorldsOfFun = new AmusementPark("Worlds of Fun", 466, 277, 1);
		AmusementPark GlenwoodCavernsAdventurePark= new AmusementPark("Glenwood Caverns Adventure Park", 281, 253, 3);
		AmusementPark Knoebels = new AmusementPark("Knoebels", 707, 216, 6);
		AmusementPark KnottsBerryFarm = new AmusementPark("Knotts Berry Farm", 95, 315, 5);
//				
//		Links holDisW = new Links (HolidayWorld, DisneyWorld);
//		Links holCed = new Links(HolidayWorld, CedarPoint);
//		Links holWor = new Links(HolidayWorld, WorldsOfFun);
//		Links holSix = new Links(HolidayWorld, SixFlagsOverTexas);
//		Links conKno = new Links(ConeyIsland, Knoebels);
//		Links knoCed = new Links(Knoebels, CedarPoint);
//		Links knoDisW = new Links(Knoebels, DisneyWorld);
//		Links worSix = new Links(WorldsOfFun, SixFlagsOverTexas);
//		Links worGlen = new Links(WorldsOfFun, GlenwoodCavernsAdventurePark);
//		Links sixGlen = new Links(SixFlagsOverTexas, GlenwoodCavernsAdventurePark);
//		Links sixDisL = new Links(SixFlagsOverTexas, DisneyLand);
//		Links glenKnot = new Links(GlenwoodCavernsAdventurePark, KnottsBerryFarm);
//		Links knotDisL = new Links(KnottsBerryFarm, DisneyLand);
//		
//		Links disWhol = new Links(DisneyWorld, HolidayWorld);
//		Links cedHol = new Links(CedarPoint, HolidayWorld);
//		Links worHol = new Links(WorldsOfFun, HolidayWorld);
//		Links sixhol = new Links(SixFlagsOverTexas, HolidayWorld);
//		Links knoCon = new Links(Knoebels, ConeyIsland);
//		Links cedKno = new Links(CedarPoint, Knoebels);
//		Links disWKno = new Links(DisneyWorld, Knoebels);
//		Links sixWor = new Links(SixFlagsOverTexas, WorldsOfFun);
//		Links glenWor = new Links(GlenwoodCavernsAdventurePark, WorldsOfFun);
//		Links glenSix = new Links(GlenwoodCavernsAdventurePark, SixFlagsOverTexas);
//		Links disLSix = new Links(DisneyLand, SixFlagsOverTexas);
//		Links knotGlen = new Links(KnottsBerryFarm, GlenwoodCavernsAdventurePark);
//		Links disLKnot = new Links(DisneyLand, KnottsBerryFarm);
		
//		HolidayWorld.addLink(holDisW);
//		HolidayWorld.addLink(holCed);
//		HolidayWorld.addLink(holWor);
//		HolidayWorld.addLink(holSix);
//		ConeyIsland.addLink(conKno);
//		Knoebels.addLink(knoCon);
//		Knoebels.addLink(knoCed);
//		Knoebels.addLink(knoDisW);
//		CedarPoint.addLink(cedHol);
//		CedarPoint.addLink(cedKno);
//		DisneyWorld.addLink(disWhol);
//		DisneyWorld.addLink(disWKno);
//		WorldsOfFun.addLink(worHol);
//		WorldsOfFun.addLink(worGlen);
//		WorldsOfFun.addLink(worSix);
//		SixFlagsOverTexas.addLink(sixWor);
//		SixFlagsOverTexas.addLink(sixhol);
//		SixFlagsOverTexas.addLink(sixGlen);
//		SixFlagsOverTexas.addLink(sixDisL);
//		GlenwoodCavernsAdventurePark.addLink(glenWor);
//		GlenwoodCavernsAdventurePark.addLink(glenSix);
//		GlenwoodCavernsAdventurePark.addLink(glenKnot);
//		KnottsBerryFarm.addLink(knotGlen);
//		KnottsBerryFarm.addLink(knotDisL);
//		DisneyLand.addLink(disLKnot);
//		DisneyLand.addLink(disLSix);
		
		ParkGraph graph = new ParkGraph(HolidayWorld, DisneyWorld);
		while (graph.pathsByDistance.peek().distanceCost != 0) {
			graph.travelByDistance();
		}
		Paths bestPath = graph.pathsByDistance.poll();
		for (int i = 0; i < bestPath.routeByDistance.size(); i++) {
			System.out.println(bestPath.routeByDistance.get(i).name);
		}
		//Check if priority queue is updated with proper stuff
//		graph.insert(HolidayWorld);
//		graph.insert(KnottsBerryFarm);
//		graph.insert(Knoebels);
//		graph.insert(GlenwoodCavernsAdventurePark);
//		graph.insert(WorldsOfFun);
//		graph.insert(SixFlagsOverTexas);
//		graph.insert(ConeyIsland);
//		graph.insert(CedarPoint);
//		graph.insert(DisneyLand);
//		graph.insert(DisneyWorld);
	}

}
