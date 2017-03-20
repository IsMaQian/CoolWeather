package com.example.adapter;

import android.content.SearchRecentSuggestionsProvider;

public class SearchProvider extends
		SearchRecentSuggestionsProvider {

	final static String AUTHORITY="com.android.cbin.SearchSuggestionSampleProvider";
	final static int MODE=DATABASE_MODE_QUERIES;
	
	public SearchProvider(){
		super();
		setupSuggestions(AUTHORITY, MODE);
	}
}
