package com.orangeskill.elate.feature.session.data;

import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.Program;
import com.orangeskill.elate.feature.session.model.Therapies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpdListDataSource {

    private HashMap<Group, List<Program>> map = new HashMap<>();


    public HashMap<Group, List<Program>> setListData(List<Therapies> therapies) {
        for (Therapies therapies1 : therapies) {
            map.put(new Group(therapies1.getSubCatagoryName(), therapies1.getPrograms().size()), therapies1.getPrograms());
        }
        return map;
    }

   public class Group {
        public String title;
        public int size;
        public Group(String title, int size) {
            this.title = title;
            this.size = size;
        }
    }

    /*public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        return expandableListDetail;
    }*/
}
