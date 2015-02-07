import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * @author Justin
 * @since 1/30/2015 4:40 PM
 */
public class Main {
	public static void main(String[] args) throws Exception {
		ArrayList<Profile> p = new ArrayList<Profile>();
		File d = new File("C:\\Users\\Justin\\Desktop\\MC\\playerdata\\");

		for (File f : d.listFiles()) {
			/*Profile pr;
			try {
				pr = Profile.fromFile(f);
			} catch (IOException e) {
				System.out.println("Error @ File: " + f.getName());
				throw e;
			}
			//System.out.println(pr);
			//p.add(pr);*/
			p.add(Profile.fromFile(f));
		}

		StringBuilder sql = new StringBuilder();

		for (Profile profile : p) {
			sql.append("INSERT INTO Name_Player (uuid, player)\nVALUES('" + profile.getId().toString() + "', " +
					"'" + profile.getName() + "');\n\n");
		}

		PrintStream f = new PrintStream(new File("uuids.sql"));
		f.print(sql);
		f.close();
	}
}
