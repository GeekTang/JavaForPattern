package come.fd.cassandra;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.Keyspace;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		Cluster myCluster = HFactory.getOrCreateCluster("TestCluster",
				"192.168.1.101:9160");
		Keyspace ksp = HFactory.createKeyspace("Twissandra", myCluster);
		String columnFamily = "User";
		ColumnFamilyTemplate<String, String> template = new ThriftColumnFamilyTemplate<String, String>(ksp, columnFamily, StringSerializer.get(),StringSerializer.get());
		try {
		    ColumnFamilyResult<String, String> res = template.queryColumns("38");
		    String value = res.getString("age");
		    System.out.println(value);
		    // value should be "www.datastax.com" as per our previous insertion.
		} catch (HectorException e) {
		    e.printStackTrace();
		}
	}
}
