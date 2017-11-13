/*                        רשימת מקורות  
 * 
 * 
 * 
 * https://stackoverflow.com/questions/32265533/reading-contents-of-a-file-into-class-objects
 * https://www.tutorialspoint.com/java/io/bufferedreader_readline.htm
 * https://stackoverflow.com/questions/7209110/java-util-nosuchelementexception-no-line-found
 * https://stackoverflow.com/questions/12999899/getting-user-input-with-scanner
* 
 * 
 */
package matala0;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Reader_Writer {

	public static void main(String[] args) {
		
		Path file = Paths.get("C:\\Users\\shira\\eclipse-workspace\\matala0\\src\\DATE\\input.csv");
		Path outputFile = Paths.get("C:\\Users\\shira\\eclipse-workspace\\matala0\\src\\DATE\\output\\best.csv");
		Path outputFile3 = Paths.get("C:\\Users\\shira\\eclipse-workspace\\matala0\\src\\DATE\\output\\test.csv");
                            Path outputFile2 = Paths.get("C:\\Users\\shira\\eclipse-workspace\\matala0\\src\\DATE\\output\\test1.kml"); 


		try  (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\shira\\eclipse-workspace\\matala0\\src\\DATE\\input2.csv"))){

			List<Row> rows = new ArrayList<Row>(); //creates list of points
			List<Row> rows2 = new ArrayList<Row>();

			String line = "";
			while ((line = br.readLine()) != null) {

				if (!line.contains("Type")&&!line.contains("brand")&&!line.contains("GSM")) {// descareges points that are not wifi
					Row row = new Row(line);
					System.out.println(row);
					rows.add(row);	//adds point of wifi to a list

				}

			}
			Comparator com= new Comparator<Row>(){// sort for best signal

				public int compare(Row arg0, Row arg1) {
					return arg0.RSSI-arg1.RSSI;
				}

			};
			rows.sort(com);
			writeFile(rows , outputFile3); //creates file with sampels
			for(int i=0;i<10;i++)
				rows2.add(rows.get(i));
			writeFile(rows2 , outputFile); // creates file with the best resulte
			writeFileKML(rows2,outputFile2);// creates kml file

		}
		catch (IOException e) {
			e.printStackTrace();
		}


	}
	static void writeFile(List<Row> rows, Path theFile) {// builder for csv files
		try {
			PrintWriter writer = new PrintWriter(Files.newBufferedWriter(theFile));
			writer.println("MAC"+",SSID"+",AuthMode"+",FirstSeen"+",Channel"+",RSSI"+",CurrentLantitude"+",CurrentLongitude"+",AltitudeMeters"+",AccurarcyMeters"+",Type");
			for(int i=0;i<rows.size();i++) {
				writer.println(rows.get(i));

			}writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
     /***
      * 
      */
	static void writeFileKML(List<Row> rows, Path theFile) //builder for kml files
	{
		try {
			PrintWriter writer = new PrintWriter(Files.newBufferedWriter(theFile));
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
					"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>");
			Scanner scAns = new Scanner(System.in);
			System.out.println("11Do you want to filter the results? 0-no 1-by date 2-by location");
			int ans=scAns.nextInt();
			//scAns.close();

			switch(ans) {
			case 0: //defualt file
				for(int i=0;i<rows.size();i++) {
					writer.println("<Placemark>");
					writer.println("<name>"+"<![CDATA["+rows.get(i).SSID+"]]>"+"</name>");
					writer.println("<description>"+"<![CDATA[MAC: "+"<b>"+rows.get(i).MAC+"</b>"+"<br/>"
							+"AuthMode:"+"<b>"+rows.get(i).AuthMode+"</b>"+"<br/>"+
							"RSSI:"+"<b>"+rows.get(i).RSSI+"</b>"+"<br/>"+
							"Channel:"+"<b>"+rows.get(i).Channel+"</b>"+"<br/>"+
							"First seen:"+"<b>"+rows.get(i).FirstSeen+"</b>"+"<br/>"+				
							"]]>"+"</description>");
					writer.println("<Point>"+"<coordinates>"+rows.get(i).CurrentLongitude+","+rows.get(i).CurrentLatitude+"</coordinates>"+"</Point>");
					writer.println("</Placemark>");
				}

				writer.println("</Folder>"+"</Document>"+"</kml>");

				writer.close();
				break;

			case 1: //sort by date

				System.out.println("From which date, use the format: dd/mm/year  hour:min:sec ");



				String date1 = scAns.next();
				scAns.close();

				for(int i=0;i<rows.size();i++) {
					if(date1.compareTo(rows.get(i).FirstSeen)<1) {
						writer.println("<Placemark>");
						writer.println("<name>"+"<![CDATA["+rows.get(i).SSID+"]]>"+"</name>");
						writer.println("<description>"+"<![CDATA[MAC: "+"<b>"+rows.get(i).MAC+"</b>"+"<br/>"
								+"AuthMode:"+"<b>"+rows.get(i).AuthMode+"</b>"+"<br/>"+
								"RSSI:"+"<b>"+rows.get(i).RSSI+"</b>"+"<br/>"+
								"Channel:"+"<b>"+rows.get(i).Channel+"</b>"+"<br/>"+
								"First seen:"+"<b>"+rows.get(i).FirstSeen+"</b>"+"<br/>"+				
								"]]>"+"</description>");
						writer.println("<Point>"+"<coordinates>"+rows.get(i).CurrentLongitude+","+rows.get(i).CurrentLatitude+"</coordinates>"+"</Point>");
						writer.println("</Placemark>");
					}
				}
				writer.println("</Folder>"+"</Document>"+"</kml>");

				writer.close();
				break;

			case 2: //sort by area

				System.out.println("Insert Range for lantitude(from-to): ");
				double Lan1= scAns.nextInt();
				double Lan2= scAns.nextInt();

				System.out.println("Insert Range for longtitude(from-to): ");
				double Lon1= scAns.nextInt();
				double Lon2= scAns.nextInt();

				scAns.close();
				for(int i=0;i<rows.size();i++) {
					if(Lan1<=rows.get(i).CurrentLatitude && Lan2>=rows.get(i).CurrentLatitude && Lon1<=rows.get(i).CurrentLongitude && Lon2>=rows.get(i).CurrentLongitude) {
						writer.println("<Placemark>");
						writer.println("<name>"+"<![CDATA["+rows.get(i).SSID+"]]>"+"</name>");
						writer.println("<description>"+"<![CDATA[MAC: "+"<b>"+rows.get(i).MAC+"</b>"+"<br/>"
								+"AuthMode:"+"<b>"+rows.get(i).AuthMode+"</b>"+"<br/>"+
								"RSSI:"+"<b>"+rows.get(i).RSSI+"</b>"+"<br/>"+
								"Channel:"+"<b>"+rows.get(i).Channel+"</b>"+"<br/>"+
								"First seen:"+"<b>"+rows.get(i).FirstSeen+"</b>"+"<br/>"+				
								"]]>"+"</description>");
						writer.println("<Point>"+"<coordinates>"+rows.get(i).CurrentLongitude+","+rows.get(i).CurrentLatitude+"</coordinates>"+"</Point>");
						writer.println("</Placemark>");
					}
				}
				writer.println("</Folder>"+"</Document>"+"</kml>");

				writer.close();
				break;
			default:
				System.out.println("bye bye");
				break;


			}
			scAns.close();
		}



		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
/*
 * רשימת באגים :
 * כל הבאגים הרשומים נוצרו בגלל חוסר זמן והבנה לוקה של המטלה בעקבות שירות מילואים בשבוע האחרון של אחד הסטודנטים 
 * לא בוצע סינון לפי ID מכשיר  
 * קריאת הקבצים מתבצעת לפי קובץ ולא לפי תקייה
 * 
 *הסבר על המטלה :
 *התבקשנו למצוא אפליקציה אשר תאסוף נתוני מיקום זמן וכתובת , MAC
 *של רשתות WIFI
 *ולאחר מכן להכניס אותם לקובץ CSV 
 *לסדר לסנן ואסוף את ה10 הכי חזקות ומשם לייצא את הנתונים לקובץ KML 
 *אחר יעלה Google Earth 
 *ושם יוצגו כל הנקודות שאספנו שעברו את הסינון 
 *לשנות את אופן קליטת הקובץ למשהו דינמי עם אפשרות לכתובות שונות עם קליטה של סטרינג 
 *
 *:מגישים 
 *שירן אבסקר ו201299591
 *יונתן רופסוב 314471970
 * 
 * 
 * 
 * הסבר קצר לכל חלק 
 * java doc 
 * לפצל לפונקציות ולא במיין ראשי אחד 
 * */

