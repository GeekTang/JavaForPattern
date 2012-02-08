package come.fd.cassandra;

import java.util.List;

import me.prettyprint.cassandra.model.CqlQuery;
import me.prettyprint.cassandra.model.CqlRows;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.Keyspace;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		Cluster myCluster = HFactory.getOrCreateCluster("Test Cluster",
				"192.168.56.101:9160");
		Keyspace ksp = HFactory.createKeyspace("Twissandra", myCluster);
		/*
		 * String columnFamily = "User"; ColumnFamilyTemplate<String, String>
		 * template = new ThriftColumnFamilyTemplate<String, String>(ksp,
		 * columnFamily, StringSerializer.get(),StringSerializer.get()); try {
		 * ColumnFamilyResult<String, String> res = template.queryColumns("38");
		 * String value = res.getString("age"); System.out.println(value); //
		 * value should be "www.datastax.com" as per our previous insertion. }
		 * catch (HectorException e) { e.printStackTrace(); }
		 */
		Serializer<String> ss = StringSerializer.get();
		CqlQuery<String, String, String> cqlQuery = new CqlQuery<String, String, String>(
				ksp, ss, ss, ss);
		cqlQuery.setQuery("select * from User");
		QueryResult<CqlRows<String, String, String>> results = cqlQuery
				.execute();
		CqlRows<String, String, String> result = results.get();
		for (Row<String, String, String> single : result) {
			System.out.println(single.getKey());
			ColumnSlice<String, String> cs = single.getColumnSlice();
			List<HColumn<String, String>> columns = cs.getColumns();
			for(HColumn<String, String> column : columns)
			{
				System.out.print("Column: " + column.getName());
				System.out.println("     Value: " + column.getValue());
			}
		}
	}
}
