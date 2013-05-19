package explorationgame;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.Color;
import java.io.File;

/**
 * Creates Terrain[] array from an xml file.
 * @author artur
 *
 */
class ReadTerrainsXML {
	
	/**
	 * Reads in file at input location as xml tree. 
	 * 
	 * @param file_location
	 * @return
	 */
	
	public static Document parse_xml(String file_location) {
		try {
			 
			File fXmlFile = new File(file_location);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 	 
			return doc;
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		return null;
	}
	
	/**
	 * Iterates over xml-document tree, assigning values to declared Terrain objects.
	 * 
	 * @param doc
	 * @return
	 */
//	public static Terrain[] make_terrains(Document doc) {
//		try {
//			NodeList nList = doc.getElementsByTagName("terrain");
//			 
//			System.out.print("Reading questions");
//			
//			Terrain[] tdb = new Terrain[nList.getLength()];
//		 
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//		 
//				Node terrainNode = nList.item(temp);
//		 		 
//				if (terrainNode.getNodeType() == Node.ELEMENT_NODE) {
//		 
//					Element terrainElement = (Element) terrainNode;
//					
//					System.out.print(".");
//					
//					// Reads in terrain elements
//					String name = terrainElement.getElementsByTagName("name").item(0).getTextContent();
//					Element colorElement = (Element) terrainElement.getElementsByTagName("color").item(0);
//					Color color = new Color(Integer.parseInt(colorElement.getElementsByTagName("red").item(0).getTextContent()), //red
//											Integer.parseInt(colorElement.getElementsByTagName("green").item(0).getTextContent()), //green
//											Integer.parseInt(colorElement.getElementsByTagName("blue").item(0).getTextContent()), //blue
//											Integer.parseInt(colorElement.getElementsByTagName("alpha").item(0).getTextContent())); //alpha
//					int weight = Integer.parseInt(terrainElement.getElementsByTagName("weight").item(0).getTextContent());
//					int hunger = Integer.parseInt(terrainElement.getElementsByTagName("hunger").item(0).getTextContent());
//					int thirst = Integer.parseInt(terrainElement.getElementsByTagName("thirst").item(0).getTextContent());
//					int wounds = Integer.parseInt(terrainElement.getElementsByTagName("wounds").item(0).getTextContent());		
//					
//					// Creates terrain from elements
//					Terrain t = new Terrain(name, color, weight, hunger, thirst, wounds);
//					
//					// Writes terrain into array
//					tdb[temp] = t;
//					
//				}	
//			}
//			
//			System.out.println("Done!");
//			System.out.println(tdb.length + " terrains loaded.");
//			return tdb;
//			
//		} catch (Exception e) {
//			e.printStackTrace();			
//		} return null;	
//	}

}
