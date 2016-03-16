package com.walletdog.core.function;

import com.walletdog.core.model.*;

import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;

public class Analysis {
	
	/**
	 * compute the spending percentage on each category from raw expense data
	 * @param expenseEntryList
	 * @param categoryList
	 * @return
	 * @throws ParseException
	 */
    public static ArrayList<CategoryPercentage> getCategoryPercentage(List<ExpenseEntry> expenseEntryList, List<Category> categoryList) throws ParseException {
        double totalExpense = 0;

        //create a category id and name look-up map
        Map<Integer, String> categoryMap = new HashMap<>();

        for (Category category : categoryList){
            categoryMap.put(category.categoryid, category.name);
        }


        ArrayList<CategoryPercentage> resultList = new ArrayList<>();

        //create a map to keep track the expense in each category
        Map<Integer,Double> map = new HashMap<>();

        //iterate through all the expenses
        for (ExpenseEntry expenseEntry : expenseEntryList){
            int categoryId = expenseEntry.categoryid;

            //if category not exist, create a new entry in the map
            if (!map.containsKey(categoryId)){
                map.put(categoryId, expenseEntry.amount);
                totalExpense += expenseEntry.amount;
            }

            //if category not exist, add new expense to the existing entry
            else {
                map.put(categoryId, map.get(categoryId) + expenseEntry.amount);
                totalExpense += expenseEntry.amount;

            }
        }

        //generate result list in the form of CategotyPercentage ArrayList
        Iterator<Entry<Integer, Double>> it = map.entrySet().iterator();
        while (it.hasNext()) {
        	Map.Entry<Integer, Double> pair = it.next();
        	
        	//set value to categoryPercentage object
            CategoryPercentage categoryPercentage = new CategoryPercentage(pair.getKey(), categoryMap.get(pair.getKey()), pair.getValue()/totalExpense);

            resultList.add(categoryPercentage);

            // why call this function : remove?
            //it.remove();
        }

        return resultList;
    }
    
    /**
     * TODO compute the spending on each day from raw expense data
     * @return
     */
    public static List<Pair<String, Double>> getExpenseDaily(List<ExpenseEntry> expenseEntryList, String beginDate, String endDate) {
    	List<Pair<String, Double>> list = new ArrayList<Pair<String, Double>>();
    	
    	return list;
    }


    /*
    private static Date stringToDate(String date) throws ParseException {
        String string = date;
        //2015-11-08 14:07:59.104
        //DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSS", Locale.ENGLISH);
        Date result = format.parse(string);
        return result;
    }
    */
}
