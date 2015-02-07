import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.StringTag;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author justcool393
 * @since 1/30/2015 4:55 PM
 */
public class Profile {
	private UUID id;
	private String name;

	public Profile(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static HashMap<UUID, String> toHashMap(Profile... profiles) {
		HashMap<UUID, String> map = new HashMap<UUID, String>();
		for (Profile p : profiles) {
			map.put(p.getId(), p.getName());
		}
		return map;
	}

	public static Profile fromFile(File f) throws IOException {
		NBTInputStream ns = new NBTInputStream(new FileInputStream(f));
		CompoundTag m = (CompoundTag) ns.readTag();

		ns.close();

		CompoundTag bm = (CompoundTag) m.getValue().get("bukkit");
		if (bm == null) throw new IllegalStateException("Bukkit tag not found");

		UUID id = UUID.fromString(f.getName().replaceAll(".dat", ""));
		String name = ((StringTag) bm.getValue().get("lastKnownName")).getValue();
		Profile p = new Profile(id, name);

		return p;
	}

	public String toString() {
		return "Profile[id=" + id.toString() + ",name=" + name + "]";
	}
}
