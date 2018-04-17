package persistance;

import java.lang.ref.WeakReference;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class Mapper<O> {
	
	protected Map<String, WeakReference<O>> objects = new HashMap<>();
	
	public abstract O mapNext(ResultSet rs) throws SQLException;

}
