package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Logit {

	private static Logit instance;
	private static BufferedWriter bw;
	private FileWriter fw;
	private Logit(String fileName) {
		File file = new File(fileName);
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void Dump(String data) {
		try {
			bw.write(data);
			bw.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Logit getInstance() {
		if (instance == null) {
			instance = new Logit("data.txt");
		}
		return instance;
	}
}
