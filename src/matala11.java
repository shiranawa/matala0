import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class matala11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String []arr=new String[20];
		int[] rssiarr=new int[10];
		int count=-1;
		String ahnasa;
		int ahnasa2=0;
		int rssi=5;
		int stam=0;
		String fileName="inn.csv";
		File file=new File(fileName);
		try{
			Scanner inputStream=new Scanner(file);
			inputStream.hasNext();
			while(inputStream.hasNext()){
				count++;
				String data=inputStream.next();
				arr[count]=data;
				String[] values=data.split(",");
				if(count>0)
				{

					rssiarr[count-1]=Integer.parseInt(values[rssi]);
					if(count>1)
					{
						if(rssiarr[count-1]>rssiarr[count-2])
						{
							stam=count-1;
							while(stam!=0)
							{
								if(rssiarr[stam]>rssiarr[stam-1])
								{
									ahnasa=arr[stam];
									arr[stam]=arr[stam+1];
									arr[stam+1]=ahnasa;
									ahnasa2=rssiarr[stam-1];
									rssiarr[stam-1]=rssiarr[stam];
									rssiarr[stam]=ahnasa2;
									stam--;
								}
								else
								{
									stam--;
								}
							}
						}
					}
				}
			}
			inputStream.close();
		}catch(FileNotFoundException e){
			System.out.println(e);
		}
	}

}
/*
	/*	static void readFolder(Path folder) {
			try {
				Files.newDirectoryStream(folder).forEach(
					file -> System.out.println(file)
				);
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}*/




/*
 * 
	/*static void writeFile(Path theFile) {
		try {
			PrintWriter writer = new PrintWriter(Files.newBufferedWriter(theFile));
			writer.println("Hello world");
			writer.println("alla wacbar");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

//readFolder(folder);
//	static void writeFileKML(List<Row> rows, Path theFile) {
	//		try {
	//			PrintWriter writer = new PrintWriter(Files.newBufferedWriter(theFile));
	//			writer.println("<kml>"+"<Placemark>"+"MAC"+"<name>"+",SSID"+"</name>"+",AuthMode"+",FirstSeen"+",Channel"+",RSSI"+"<Point>"+"<coordinates>"+",CurrentLantitude"+",CurrentLongitude"+"</coordinates>"+"</Point>"+",AltitudeMeters"+",AccurarcyMeters"+",Type"+"<Placemark>"+"</kml>");
	//			for(int i=0;i<rows.size();i++) {
	//				writer.println(rows.get(i));
	//
	//			}writer.close();
	//		}
	//		catch(Exception e) {
	//			e.printStackTrace();
	//		}
	 * 
	 * 
	 * //		try {
		//			PrintWriter writer = new PrintWriter(Files.newBufferedWriter(theFile));
		//			writer.println("<kml>Hello world</kml>");
		//			writer.close();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		//
	 */
	}*/




 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
