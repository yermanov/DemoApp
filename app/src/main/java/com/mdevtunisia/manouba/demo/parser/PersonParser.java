package com.mdevtunisia.manouba.demo.parser;

import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdevtunisia.manouba.demo.data.model.Person;
import com.mdevtunisia.manouba.demo.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yermanov on 17/04/16.
 */
public class PersonParser {

    private static final String TAG = PersonParser.class.getSimpleName();

    public static List<Person> parsePersonList(String json) {

        if (StringUtil.isEmpty(json)) {
            Log.w(TAG, "json is empty!");
            return null;
        }

        List<Person> personList = null;

        ObjectMapper mapper = new ObjectMapper();

        // 1er method
//        try {
//            Person[] personArray = mapper.readValue(json, Person[].class);
//
//            personList = Arrays.asList(personArray);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 2nd method
        JsonFactory jsonFactory = new JsonFactory();
        try {

            JsonParser jsonParser = jsonFactory.createParser(json);

            jsonParser.nextToken();

            while (jsonParser.nextToken() == JsonToken.START_OBJECT) {

                Person person = mapper.readValue(jsonParser, Person.class);

                if (person != null) {

                    if (personList == null) {
                        personList = new ArrayList<>();
                    }

                    personList.add(person);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return personList;
    }
}
