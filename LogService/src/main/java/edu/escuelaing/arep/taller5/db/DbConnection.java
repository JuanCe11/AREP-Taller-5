package edu.escuelaing.arep.taller5.db;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.bson.Document;

import com.mongodb.MongoClient;

public class DbConnection {

	/**
	 * Method that insert one row on collection taller5
	 * 
	 * @param as_log the log to insert
	 * @throws ApplicationException when the connection to DB fails.
	 */
	public static void insert(String as_log) throws ApplicationException {
		MongoClient lmc_mongoClient;
		MongoDatabase lmd_dataBase;
		MongoCollection<Document> lc_collection;
		lmc_mongoClient = null;
		try {
			lmc_mongoClient = new MongoClient("ec2-100-26-178-129.compute-1.amazonaws.com", 27017);
			if (lmc_mongoClient != null) {
				lmd_dataBase = lmc_mongoClient.getDatabase("myDb");
				if (lmd_dataBase != null) {
					lc_collection = lmd_dataBase.getCollection("logs");
					if (lc_collection != null) {
						LinkedList<Document> dataList;
						Date date;

						date = new Date();
						dataList = new LinkedList<Document>();
						dataList.add(new Document().append("log", as_log).append("date", date.toString()));
						lc_collection.insertMany(dataList);
					}
				}
			}
		} catch (Exception e) {
			throw new ApplicationException(ApplicationException.ERROR_DB);
		} finally {
			if (lmc_mongoClient != null)
				lmc_mongoClient.close();
		}

	}

	/**
	 * Method that find all rows on taller5 collection
	 * 
	 * @return String with all rows on JSON format
	 * @throws ApplicationException when the connection to DB fails.
	 */
	/**
	 * @return
	 * @throws ApplicationException
	 */
	public static String findAll() throws ApplicationException {
		MongoClient lmc_mongoClient;
		MongoDatabase lmd_dataBase;
		MongoCollection<Document> lc_collection;
		String ls_res;

		lmc_mongoClient = null;
		ls_res = "";

		try {
			lmc_mongoClient = new MongoClient("ec2-100-26-178-129.compute-1.amazonaws.com", 27017);

			if (lmc_mongoClient != null) {
				lmd_dataBase = lmc_mongoClient.getDatabase("myDb");
				if (lmd_dataBase != null) {
					lc_collection = lmd_dataBase.getCollection("logs");
					if (lc_collection != null) {
						ls_res = createJSON(lc_collection);
					}
				}
			}
		} catch (Exception e) {
			throw new ApplicationException(ApplicationException.ERROR_DB);
		} finally {
			if (lmc_mongoClient != null)
				lmc_mongoClient.close();
		}

		return ls_res;

	}

	/**
	 * Mathod that convert information to JSON format
	 * 
	 * @param ac_collection the collection to consult
	 * @return A string with the information on JSON format
	 */
	private static String createJSON(MongoCollection<Document> ac_collection) {

		FindIterable<Document> lfid_consult;
		Iterator<Document> lid_iterator;
		String ls_ans;
		ArrayList<Document> lad_array;

		lfid_consult = ac_collection.find();
		lid_iterator = lfid_consult.iterator();
		lad_array = new ArrayList<Document>();
		ls_ans = "{\"information\":[";

		while (lid_iterator.hasNext()) {
			Document ld_document;

			ld_document = (Document) lid_iterator.next();

			if (ld_document != null)
				lad_array.add(ld_document);

		}

		{
			int li_cont;

			li_cont = 0;

			while (li_cont < lad_array.size() && li_cont < 10) {
				Document ld_document;
				String ls_log;
				String ls_date;

				ld_document = lad_array.get(lad_array.size() - 1 - li_cont);
				ls_log = (String) ld_document.get("log");
				ls_date = (String) ld_document.get("date");
				if (ls_log != null && ls_date != null) {
					ls_ans += "{";
					ls_ans += "\"Log:\": \"" + ld_document.get("log") + "\",";
					ls_ans += " \"Creation Date\": \"" + ld_document.get("date") + "\"";
					ls_ans += "}";
				}

				if (li_cont < lad_array.size() - 1 && li_cont < 9)
					ls_ans += ",";
				
				li_cont++;
			}

			ls_ans += "]}";
		}

		return ls_ans;
	}
}