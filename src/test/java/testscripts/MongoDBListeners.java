package testscripts;

import java.util.ArrayList;

import org.bson.Document;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBListeners implements ITestListener{

	MongoCollection<Document> webCollection;
	MongoClient mongoClient;
	
	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String className = result.getMethod().getRealClass().getName();
		
		Document doc = new Document();
		doc.append("mathodName", methodName);
		doc.append("className", className);
		doc.append("status", "PASS");
		
		ArrayList<Document> docsList = new ArrayList<Document>();
		docsList.add (doc);
		
		webCollection.insertMany(docsList);
	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String className = result.getMethod().getRealClass().getName();
		
		Document doc = new Document();
		doc.append("mathodName", methodName);
		doc.append("className", className);
		doc.append("status", "FAIL");
		
		ArrayList<Document> docsList = new ArrayList<Document>();
		docsList.add (doc);
		
		webCollection.insertMany(docsList);		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		//Connecting mongo db
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongoClient.getDatabase("automationdb");
		
		//Create collection
		//Name of the collection created is 'web'
		webCollection = db.getCollection("live");
	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
