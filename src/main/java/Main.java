import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * @author justcool393
 * @since 1/30/2015 4:40 PM
 */
public class Main {
	public static void main(String[] args) throws Exception {
		ArrayList<Profile> p = new ArrayList<Profile>();
		File d = new File("playerdata\\");

		for (File f : d.listFiles()) {
			p.add(Profile.fromFile(f));
		}

		StringBuilder sql = new StringBuilder();

		for (Profile profile : p) {
			sql.append("INSERT INTO name_player (uuid, player)\nVALUES('" + profile.getId().toString() + "', " +
					"'" + profile.getName() + "');\n\n");
		}

		PrintStream f = new PrintStream(new File("uuids.sql"));
		f.print(sql);
		f.close();
	}
}
